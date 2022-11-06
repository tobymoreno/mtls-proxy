/*
 * Header filter mechanism to secure api calls based on cert information
 */
package mil.navy.mtls.proxy.util;

import java.util.Base64;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import mil.navy.mtls.proxy.SSLProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author tmoreno
 */
@Component
public class AccessHeaderFilter {

    public void doFilter(SSLProperties sSLProperties, HttpServletRequest servletRequest) throws Exception {
        boolean allowed = true;

        Enumeration<String> headerNames = servletRequest.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = servletRequest.getHeader(headerName);
            Logger.getLogger(AccessHeaderFilter.class.getName()).log(Level.INFO, "{0}:{1}", new String[]{headerName, headerValue});
            String headerAccessControl = sSLProperties.getHeaderAccessControl();
            if (headerAccessControl != null) {
                byte[] decodedBytes = Base64.getDecoder().decode(headerAccessControl.getBytes());
                String strValue = new String(decodedBytes);
                String[] arrValues = strValue.split("=");

                if (arrValues.length > 1) {
                    String propertyKey = arrValues[0];
                    if (propertyKey.equalsIgnoreCase(headerName)) {
                        allowed = false;
                        Logger.getLogger(AccessHeaderFilter.class.getName()).log(Level.INFO, "{0}:{1}", new String[]{"header match", propertyKey});
                        String[] propertyValues = arrValues[1].split(",");
                        for (String propertyValue : propertyValues) {
                            if (headerValue.contains(propertyValue)) {
                                allowed = true;
                            }
                        }

                    }
                }
            }

        }

        if (!allowed) {
            throw new Exception("Cert not allowed.");
        }

    }

}
