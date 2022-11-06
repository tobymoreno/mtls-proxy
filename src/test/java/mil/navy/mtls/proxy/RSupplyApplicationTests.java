package mil.navy.mtls.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import mil.navy.mtls.proxy.handler.PMSTransformerRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

class RSupplyApplicationTests {

    @BeforeEach
    public void init() throws Exception {
        // make a mock for kafkatopic adapter
//        kadapter.initialize();
    }

    @Test
    void contextLoads() throws UnknownHostException {

    }

    @Test
    public void testPMSFRB() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {

        InputStream inputStream = RSupplyApplicationTests.class.getClassLoader().getResourceAsStream("./pmsfrb/pmsfrb.xml");

        byte[] targetArray = new byte[inputStream.available()];

        inputStream.read(targetArray);
        
        PMSTransformerRequest pMSTransformerRequest = new PMSTransformerRequest();
        String transformedPayload = pMSTransformerRequest.transform(targetArray);
        
        System.out.println(transformedPayload);
    }
    
    @Test
    public void testHttpClient() {
        
    }

}
