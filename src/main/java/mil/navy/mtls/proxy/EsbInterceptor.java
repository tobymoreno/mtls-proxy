/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mil.navy.mtls.proxy;

import java.lang.reflect.Constructor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mil.navy.mtls.proxy.handler.ServiceHandler;
import mil.navy.mtls.proxy.util.AccessFilter;
import mil.navy.mtls.proxy.util.AccessHeaderFilter;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 *
 * @author tmoreno
 */
public class EsbInterceptor implements HandlerInterceptor {

    private final SSLProperties sslProperties;
    private final AccessHeaderFilter accessFilter;
    private ServiceHandler serviceHandler;

    public EsbInterceptor(SSLProperties sSLProperties) {
        this.sslProperties = sSLProperties;
        accessFilter = new AccessHeaderFilter();
    }

    @Override
    public boolean preHandle(HttpServletRequest requestServlet, HttpServletResponse responseServlet, Object handler) throws Exception {
        System.out.println("MINIMAL: INTERCEPTOR PREHANDLE CALLED");
        accessFilter.doFilter(sslProperties, requestServlet);

        String servletPath = (requestServlet.getServletPath());

        if (servletPath != null && servletPath.startsWith("/error")) {
//            throw new Exception("Internal Error");
        } else {
            String queryString = (requestServlet.getQueryString());

            String forwardUrl = String.format("%s:%s%s", sslProperties.getForwardAddress(), sslProperties.getForwardPort(), servletPath);
            
            String classPath = sslProperties.getProxyClass();

            if (serviceHandler == null) {
//                serviceHandler = (ServiceHandler) Class.forName(classPath).newInstance();
                Class<?> c = Class.forName(classPath);
                Constructor<?> cons = c.getDeclaredConstructors()[0];
                serviceHandler = (ServiceHandler) cons.newInstance();
            }

            if (queryString != null) {
                forwardUrl = String.format("%s:%s%s?%s", sslProperties.getForwardAddress(), sslProperties.getForwardPort(), servletPath, queryString);
            }

            System.out.println(forwardUrl);

            serviceHandler.process(forwardUrl, requestServlet, responseServlet, sslProperties);
        }

        return false;
    }
}
