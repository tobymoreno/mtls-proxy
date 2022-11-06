/**
 * proxy end point if path is well defined
 */
package mil.navy.mtls.proxy;

import mil.navy.mtls.proxy.util.AccessFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author tmoreno
 */
//@RestController
public class ProxyEndpoint {

    private static final String XFORWARDHEADER = "x-forward-header-";
    private static final String XFORWARD = "X-FORWARD";

    @Autowired
    private SSLProperties sSLProperties;

    @Autowired
    private AccessFilter accessFilter;

    @GetMapping(value = "/api/hello", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello");
    }

}
