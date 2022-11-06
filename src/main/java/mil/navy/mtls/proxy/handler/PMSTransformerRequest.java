/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mil.navy.mtls.proxy.handler;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import mil.navy.mtls.proxy.SSLProperties;
import mil.navy.mtls.proxy.util.JSON;
import mil.navy.mtls.proxy.util.SSLConfig;
import mil.navy.mtls.proxy.util.LocalSecureHTTP;
import mil.navy.streams.model.pms.ConfigurationDetailInput;
import mil.navy.streams.model.pms.PmsInputMessage;
import mil.navy.streams.model.pms.Records;
import mil.navy.streams.model.pms.Wrapper;
import nl.altindag.ssl.SSLFactory;
import okhttp3.MediaType;
import okhttp3.Response;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author tmoreno
 */
public class PMSTransformerRequest implements ServiceHandler {

    private SSLFactory sslFactory = null;

    @Override
    public void process(String forwardUrl, HttpServletRequest requestServlet, HttpServletResponse responseServlet, SSLProperties sslProperties) throws Exception {
        HashMap<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = requestServlet.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = requestServlet.getHeader(headerName);
            System.out.println(String.format("%s : %s", headerName, headerValue));
            if (!headerName.equalsIgnoreCase("host")) {
                headers.put(headerName, headerValue);

            }
        }

//        headers.put("content-type", "application/vnd.kafka.json.v2+json");
//        headers.put("accept", "application/vnd.kafka.v2+json");
        Set<String> keys = sslProperties.getHeaderInjections().keySet();
        keys.forEach(key -> {
            String[] arr = key.split("\\.");
            String headerName = arr[1];
            String value = sslProperties.getHeaderInjections().getOrDefault(key, "");
            headers.put(headerName, value);
        });

        System.out.println(JSON.getInstance().stringify(headers));

        String method = requestServlet.getMethod();

        byte[] payload = null;
        if (requestServlet.getInputStream() != null) {
            payload = IOUtils.toByteArray(requestServlet.getInputStream());
            System.out.println(new String(payload));
            String transformedPayload = transform(payload);
            System.out.println(transformedPayload);


            Response response = invoke(sslProperties, forwardUrl, headers, transformedPayload.getBytes(), method);
            responseServlet.setCharacterEncoding(requestServlet.getCharacterEncoding());

            MediaType mediaType = response.body().contentType();
            if (mediaType != null) {
                System.out.println(mediaType.toString());
                responseServlet.setContentType(mediaType.toString());
            }

            responseServlet.getWriter().write(response.body().string());
        }

    }

    public Response invoke(SSLProperties sslProperties, String url, HashMap<String, String> headers, byte[] payload, String method) throws Exception {
        SSLConfig sslConfig = new SSLConfig();

        String keyStore = sslProperties.getKeyStore();
        String keyStorePassword = sslProperties.getKeyStorePassword();
        String trustStore = sslProperties.getTrustStore();
        String trustStorePassword = sslProperties.getTrustStorePassword();
        boolean oneWay = sslProperties.isOneWay();
        boolean twoWay = sslProperties.isTwoWay();

        Path keyStorePath = null;
        if (keyStore != null) {
            keyStorePath = FileSystems.getDefault().getPath(CONFIG_FOLDER, keyStore);
        }
        Path trustStorePath = null;
        if (trustStore != null) {
            trustStorePath = FileSystems.getDefault().getPath(CONFIG_FOLDER, trustStore);
        }

        if (keyStore != null) {
            if (sslFactory == null) {
                sslFactory = sslConfig.sslFactory(oneWay, twoWay,
                        keyStorePath, keyStorePassword.toCharArray(), trustStorePath, trustStorePassword.toCharArray());
            }
        }

        String contentType = headers.get("content-type");

        okhttp3.MediaType mediaType = null;

        if (contentType != null) {
            mediaType = okhttp3.MediaType.parse(contentType);
        }

        Response response = LocalSecureHTTP.getResponse(sslFactory,
                url,
                payload,
                mediaType,
                headers, method);

        return response;
    }
    private static final String CONFIG_FOLDER = "./config";

    public String transform(byte[] payload) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {

        InputStream inputStream = new ByteArrayInputStream(payload);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true); // never forget this!
        Document doc = factory.newDocumentBuilder().parse(inputStream);

        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();

        XPathExpression expr = xpath.compile("//ATTRIBUTES/ATTRIBUTE");
        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < nodes.getLength(); i++) {
//            System.out.println(nodes.item(i).getNodeValue());
            NodeList childNodes = nodes.item(i).getChildNodes();
            String key = null;
            for (int j = 0; j < childNodes.getLength(); j++) {
                if (childNodes.item(j).getNodeName().equals("NAME")) {
                    String clean = childNodes.item(j).getTextContent().trim(); //.stripLeading().stripTrailing();
//                    System.out.println(clean.length());
                    key = clean;
                } else if (childNodes.item(j).getNodeName().equals("VALUE")) {
                    String clean = childNodes.item(j).getTextContent().trim(); //stripLeading().stripTrailing();
                    map.put(key, clean);
                }
            }

        }

//        System.out.println(JSON2.getInstance().stringify(map));

        Set<String> keys = map.keySet();
        keys.stream().map(key -> {
//            System.out.println(key);
            return key;
        }).forEachOrdered(key -> {
            String sout = String.format("configurationDetailInput.set (map.getOrDefault(\"%s\", \"\"));", key);
//            System.out.println(String.format("value = \"%s\"", map.getOrDefault(key, "")));

            System.out.println(sout);
        });

        PmsInputMessage pmsInputMessage = new PmsInputMessage();
        pmsInputMessage.setConfigurationInput(new mil.navy.streams.model.pms.ConfigurationInput());

        SimpleDateFormat dateFormatter = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = new Date();
        String formattedDate = dateFormatter.format(date);

        pmsInputMessage.getConfigurationInput().setMessageDate(formattedDate);
        pmsInputMessage.getConfigurationInput().setMessageType("NMRO_PMSMIS_TFR");
        pmsInputMessage.getConfigurationInput().setMessageId(UUID.randomUUID().toString());

        List<ConfigurationDetailInput> list = new ArrayList<>();

        ConfigurationDetailInput configurationDetailInput = new ConfigurationDetailInput();
        configurationDetailInput.setFBRSerialNumber(map.getOrDefault("FBR_SERIAL_NUMBER", ""));
        configurationDetailInput.setOriginatorEmail(map.getOrDefault("ORIGINATOR_EMAIL", ""));
        configurationDetailInput.setDateOut(map.getOrDefault("DATE_OUT", ""));
        configurationDetailInput.setActivityCode(map.getOrDefault("ACTIVITY_CODE", ""));
        configurationDetailInput.setRemarks(map.getOrDefault("REMARKS", ""));
        configurationDetailInput.setEquipmentSerialNum(map.getOrDefault("EQUIPMENT_SERIAL_NUM", ""));
        configurationDetailInput.setEquipmentLocation(map.getOrDefault("EQUIPMENT_LOCATION", ""));
        configurationDetailInput.set3MCEmail(map.getOrDefault("3MC_EMAIL", ""));
        configurationDetailInput.setEquipmentNomenclature(map.getOrDefault("EQUIPMENT_NOMENCLATURE", ""));
//        configurationDetailInput.setEq(map.getOrDefault("EQUIPMENT_IDENTIFIER", ""));
        configurationDetailInput.setOriginatorEmail(map.getOrDefault("ORIGINATOR_SIGNATURE", ""));
        configurationDetailInput.setOriginatorSignature(map.getOrDefault("URGENT", ""));
        configurationDetailInput.setEquipmentRIN(map.getOrDefault("EQUIPMENT_RIN", ""));
        configurationDetailInput.setMIP(map.getOrDefault("MIP", ""));
        configurationDetailInput.setFeedbackTitle(map.getOrDefault("FEEDBACK_TITLE", ""));
        configurationDetailInput.setEquipmentESWBS(map.getOrDefault("EQUIPMENT_ESWBS", ""));
        configurationDetailInput.set3MCSignature(map.getOrDefault("3MC_SIGNATURE", ""));
        configurationDetailInput.setEquipmentAPL(map.getOrDefault("EQUIPMENT_APL", ""));
        configurationDetailInput.setUrgentDateTime(map.getOrDefault("URGENT_DATE_TIME", ""));
        configurationDetailInput.setWorkCenter(map.getOrDefault("WORK_CENTER", ""));
        configurationDetailInput.setFeedbackType(map.getOrDefault("FEEDBACK_TYPE", ""));
        configurationDetailInput.setMRC(map.getOrDefault("MRC", ""));

        list.add(configurationDetailInput);

        pmsInputMessage.getConfigurationInput().setConfigurationDetailInput(list);
        
        List<Records> records = new ArrayList<>();
        Records r1 = new Records();
        r1.setKey(null);
        r1.setValue(pmsInputMessage);
        records.add(r1);
        
        Wrapper wrapper = new Wrapper();
        wrapper.setRecords(records);

        return ""; //(JSON2.getInstance().stringify(wrapper, true));
    }

}
