/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mil.navy.mtls.proxy.util;

import static java.lang.StrictMath.log;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import mil.navy.mtls.proxy.SSLProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author tmoreno
 */
@Component
public class AccessFilter {

    public void doFilter(SSLProperties sSLProperties, HttpServletRequest servletRequest) throws CertificateParsingException, Exception {
        X509Certificate[] certs = (X509Certificate[]) servletRequest.getAttribute("javax.servlet.request.X509Certificate");
        if (certs != null) {
            boolean allowed = false;
            for (X509Certificate x509Certificate : certs) {
                System.out.println(x509Certificate.getSubjectDN().getName());
                getSubjectAlternativeNames(x509Certificate);

                Pattern pattern = Pattern.compile("CN=(.*?),", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(x509Certificate.getSubjectDN().getName());
                boolean matchFound = matcher.find();

                if (matchFound) {
                    // proceed
                    String sn = matcher.group(1);
                    System.out.println(sn);
                    // after authentication in the future will put this as an ldap call to for authorization (rbac)
                    String clientSubjectName = sSLProperties.getClientSubjectName();
                    System.out.println(clientSubjectName);
                    if (clientSubjectName != null) {
                        if (clientSubjectName.contains(sn)) {
                            allowed = true;
                            break;
                        }
                    }
                    else {
                        // bypass the validation
                        allowed = true;
                    }
                }

            }

            if (!allowed) {
                throw new Exception("Cert not allowed.");
            }

        }

    }

    public List<String> getSubjectAlternativeNames(X509Certificate cert) throws CertificateParsingException {
        ArrayList<String> toReturn = new ArrayList<>();
        Collection<List<?>> sans = cert.getSubjectAlternativeNames();
        if (sans != null) {
            System.out.println("Subject Alternative Names: " + sans.toString());
            for (List<?> l : sans) {
                if (l.size() == 2) {
                    Integer type = (Integer) l.get(0);
                    if (type.equals(new Integer(2))) {
                        //DNS SAN
                        String value = (String) l.get(1);
                        toReturn.add("dns:" + value);
                    } else {
                        String message = "SAN type '" + type + "' not implemented";
                        System.out.println(message);
                    }
                } else {
                    System.out.println("expected subject alternatives names object to have only 2 elements but " + l.size()
                            + " elements were present");
                }
            }
        }

        return toReturn;
    }

}
