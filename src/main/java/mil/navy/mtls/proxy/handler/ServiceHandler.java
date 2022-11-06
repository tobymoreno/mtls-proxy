/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mil.navy.mtls.proxy.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mil.navy.mtls.proxy.SSLProperties;

/**
 *
 * @author tmoreno
 */
public interface ServiceHandler {
    public void process(String forwardUrl, HttpServletRequest requestServlet, HttpServletResponse responseServlet, SSLProperties sslProperties) throws Exception;
}
