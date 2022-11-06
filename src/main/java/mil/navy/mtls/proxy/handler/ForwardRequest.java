/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mil.navy.mtls.proxy.handler;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mil.navy.mtls.proxy.SSLProperties;
import mil.navy.mtls.proxy.util.SSLConfig;
import mil.navy.mtls.proxy.util.LocalSecureHTTP;
import nl.altindag.ssl.SSLFactory;
import okhttp3.Response;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author tmoreno
 */
public class ForwardRequest implements ServiceHandler {

    private SSLFactory sslFactory = null;

    @Override
    public void process(String forwardUrl, HttpServletRequest requestServlet, HttpServletResponse responseServlet, SSLProperties sslProperties) throws Exception {
        HashMap<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = requestServlet.getHeaderNames();
        

        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = requestServlet.getHeader(headerName);
            System.out.println(String.format("%s : %s", headerName, headerValue));
            if(!headerName.equalsIgnoreCase("host")) {
                headers.put(headerName, headerValue);
                
            }
        }
        
//        String encoding = headers.getOrDefault("accept-encoding", "UTF-8");
//        if(!encoding.equalsIgnoreCase("UTF-8")) {
//            headers.put("accept-encoding", "UTF-8");
//        }

        String method = requestServlet.getMethod();

        byte[] payload = null;
        if (requestServlet.getInputStream() != null) {
            payload = IOUtils.toByteArray(requestServlet.getInputStream());

        }
        
        

        Response response = invoke(sslProperties, forwardUrl, headers, payload, method);
        responseServlet.setCharacterEncoding(requestServlet.getCharacterEncoding());

//        MediaType mediaType = response.body().contentType();
//        if(mediaType!=null) {
//            System.out.println(mediaType.toString());
//            responseServlet.setContentType(requestServlet.getContentType());
//        }
        
//        responseServlet.getWriter().write(response.body().string());
        responseServlet.setContentType("application/json");
        responseServlet.setCharacterEncoding("UTF-8");
        byte[] bytes = IOUtils.toByteArray(response.body().byteStream());
        responseServlet.getWriter().write(new String(bytes));
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
        if(keyStore != null) keyStorePath = FileSystems.getDefault().getPath(CONFIG_FOLDER, keyStore);
        Path trustStorePath = null;
        if(trustStore != null) trustStorePath = FileSystems.getDefault().getPath(CONFIG_FOLDER, trustStore);

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

}
