/**
 * Configuration properties for forward ssl settings
 */
package mil.navy.mtls.proxy;

import java.util.HashMap;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author tmoreno
 */
@Component
@ConfigurationProperties("proxy-settings")
public class SSLProperties {

    /**
     * @return the handlerInterceptors
     */
    public HashMap<String, String> getHandlerInterceptors() {
        return handlerInterceptors;
    }

    /**
     * @param handlerInterceptors the handlerInterceptors to set
     */
    public void setHandlerInterceptors(HashMap<String, String> handlerInterceptors) {
        this.handlerInterceptors = handlerInterceptors;
    }


    /**
     * @return the clientSubjectName
     */
    public String getClientSubjectName() {
        return clientSubjectName;
    }

    /**
     * @param clientSubjectName the clientSubjectName to set
     */
    public void setClientSubjectName(String clientSubjectName) {
        this.clientSubjectName = clientSubjectName;
    }

    /**
     * @return the oneWay
     */
    public boolean isOneWay() {
        return oneWay;
    }

    /**
     * @param oneWay the oneWay to set
     */
    public void setOneWay(boolean oneWay) {
        this.oneWay = oneWay;
    }

    /**
     * @return the twoWay
     */
    public boolean isTwoWay() {
        return twoWay;
    }

    /**
     * @param twoWay the twoWay to set
     */
    public void setTwoWay(boolean twoWay) {
        this.twoWay = twoWay;
    }

    /**
     * @return the forwardAddress
     */
    public String getForwardAddress() {
        return forwardAddress;
    }

    /**
     * @param forwardAddress the forwardAddress to set
     */
    public void setForwardAddress(String forwardAddress) {
        this.forwardAddress = forwardAddress;
    }

    /**
     * @return the forwardPort
     */
    public String getForwardPort() {
        return forwardPort;
    }

    /**
     * @param forwardPort the forwardPort to set
     */
    public void setForwardPort(String forwardPort) {
        this.forwardPort = forwardPort;
    }

    private String proxyClass;
    private String rootPath;
    private String keyStore;
    private String keyStorePassword;
    private String trustStore;
    private String trustStorePassword;
    private boolean oneWay = false;
    private boolean twoWay = false;
    private String forwardAddress;
    private String forwardPort;
    private String clientSubjectName;
    private String headerAccessControl;
    private HashMap<String, String> handlerInterceptors;
    private HashMap<String, String> headerInjections;
    
    private class KeyValuePair {

        /**
         * @return the key
         */
        public String getKey() {
            return key;
        }

        /**
         * @param key the key to set
         */
        public void setKey(String key) {
            this.key = key;
        }

        /**
         * @return the value
         */
        public String getValue() {
            return value;
        }

        /**
         * @param value the value to set
         */
        public void setValue(String value) {
            this.value = value;
        }
        private String key;
        private String value;
    }

    public HashMap<String, String> getHeaderInjections() {
        return headerInjections;
    }

    public void setHeaderInjections(HashMap<String, String> headerInjections) {
        this.headerInjections = headerInjections;
    }

    /**
     * @return the keyStore
     */
    public String getKeyStore() {
        return keyStore;
    }

    /**
     * @param keyStore the keyStore to set
     */
    public void setKeyStore(String keyStore) {
        this.keyStore = keyStore;
    }

    /**
     * @return the keyStorePassword
     */
    public String getKeyStorePassword() {
        return keyStorePassword;
    }

    /**
     * @param keyStorePassword the keyStorePassword to set
     */
    public void setKeyStorePassword(String keyStorePassword) {
        this.keyStorePassword = keyStorePassword;
    }

    /**
     * @return the trustStore
     */
    public String getTrustStore() {
        return trustStore;
    }

    /**
     * @param trustStore the trustStore to set
     */
    public void setTrustStore(String trustStore) {
        this.trustStore = trustStore;
    }

    /**
     * @return the trustStorePassword
     */
    public String getTrustStorePassword() {
        return trustStorePassword;
    }

    /**
     * @param trustStorePassword the trustStorePassword to set
     */
    public void setTrustStorePassword(String trustStorePassword) {
        this.trustStorePassword = trustStorePassword;
    }

    /**
     * @return the proxyClass
     */
    public String getProxyClass() {
        return proxyClass;
    }

    /**
     * @param proxyClass the proxyClass to set
     */
    public void setProxyClass(String proxyClass) {
        this.proxyClass = proxyClass;
    }

    /**
     * @return the rootPath
     */
    public String getRootPath() {
        return rootPath;
    }

    /**
     * @param rootPath the rootPath to set
     */
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    /**
     * @return the headerAccessControl
     */
    public String getHeaderAccessControl() {
        return headerAccessControl;
    }

    /**
     * @param headerAccessControl the headerAccessControl to set
     */
    public void setHeaderAccessControl(String headerAccessControl) {
        this.headerAccessControl = headerAccessControl;
    }

}
