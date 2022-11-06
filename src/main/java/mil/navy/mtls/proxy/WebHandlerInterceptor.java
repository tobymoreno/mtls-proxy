/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mil.navy.mtls.proxy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mil.navy.mtls.proxy.handler.ServiceHandler;
import mil.navy.mtls.proxy.util.AccessHeaderFilter;
import mil.navy.mtls.proxy.util.JSON;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 *
 * @author tmoreno
 */
public class WebHandlerInterceptor implements HandlerInterceptor {

    private final SSLProperties sslProperties;
    private final AccessHeaderFilter accessFilter;
    private ServiceHandler serviceHandler;

    public WebHandlerInterceptor(SSLProperties sSLProperties) {
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
            System.out.println("I'm processing");
            String[] response = new String[]{"Loopback Test"};
            responseServlet.getWriter().write(JSON.getInstance().stringify(response));
        }

        return false;
    }
}
