/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mil.navy.mtls.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import mil.navy.mtls.proxy.handler.ServiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author tmoreno
 */
@Configuration
public class ServiceConfig implements WebMvcConfigurer {

    @Autowired
    private SSLProperties sSLProperties;

    @Override
    /**
     * add dynamic interceptors 
     */
    public void addInterceptors(InterceptorRegistry registry) {
        try {
            String rootPath = "/**";
            if (sSLProperties.getRootPath() != null) {
                rootPath = sSLProperties.getRootPath();
            }
            
            
            HashMap<String, String> interceptors = sSLProperties.getHandlerInterceptors();
            for (Map.Entry<String, String> entry : interceptors.entrySet()) {
                String key = entry.getKey();
                String path = entry.getValue();
                String className = key.substring(key.indexOf("mil.navy.mtls.proxy"));
                System.out.println(className + ":" + path);
                if(className != null && path != null) {
                    HandlerInterceptor handlerInterceptor = createInterceptor(className, sSLProperties);
                    InterceptorRegistration interceptorRegistration = registry.addInterceptor(handlerInterceptor);
                    interceptorRegistration.addPathPatterns(path); //("/int/ifsapplications/projection/v1/**"); ///**");                    
                } 
                else {
                    throw new Exception("Cannot find interceptor");
                }
            }
            
//        registry.addInterceptor(new RouteInterceptor(sSLProperties)).addPathPatterns(rootPath); //("/int/ifsapplications/projection/v1/**"); ///**");
        } catch (Exception ex) {
            Logger.getLogger(ServiceConfig.class.getName()).log(Level.SEVERE, "%s", ex.getMessage());
        }
    }

    /**
     * create interceptor
     *
     * @param className
     * @param sSLProperties
     * @return
     */
    HandlerInterceptor createInterceptor(String className, SSLProperties sSLProperties) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        HandlerInterceptor handlerInterceptor = null;
        Class<?> c = Class.forName(className);
        Constructor<?> cons = c.getDeclaredConstructors()[0];
        handlerInterceptor = (HandlerInterceptor) cons.newInstance(sSLProperties);
        return handlerInterceptor;
    }

}
