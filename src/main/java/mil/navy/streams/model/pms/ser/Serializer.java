/*
 */
package mil.navy.streams.model.pms.ser;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import mil.navy.streams.model.pms.ConfigurationDetailInput;
import mil.navy.streams.model.pms.HeaderElement;
import mil.navy.streams.model.pms.PmsInputMessage;

/**
 *
 * @author tmoreno
 */
public class Serializer {
    
    public String serialize(PmsInputMessage pmsInputMessage) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        JsonPropertyOrder jsonPropertyOrder = ConfigurationDetailInput.class.getAnnotation(JsonPropertyOrder.class);
        String[] values = jsonPropertyOrder.value();

        List<ConfigurationDetailInput> list = pmsInputMessage.getConfigurationInput().getConfigurationDetailInput();

        StringBuilder headers = new StringBuilder();
        StringBuilder body = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            ConfigurationDetailInput configurationDetailInput = list.get(i);

            for (String value : values) {

                String getMethodName = String.format("get%s", value);

                Method m = configurationDetailInput.getClass().getMethod(getMethodName);
//                Class c = m.getReturnType();

                if (i == 0) {
                    if (m.getAnnotations().length == 2) {
                        // get the header element
                        HeaderElement headerElement = m.getAnnotation(HeaderElement.class);
                        if (headerElement != null) {
                            String headerDelim = "|";
                            if (headerElement.last()) {
                                headerDelim = "";
                            }
                            headers.append( String.format("%s%s", headerElement.value(), headerDelim));
                        }
                    }
                }
                
                String invokedValue = (String)m.invoke(configurationDetailInput);
                body.append(invokedValue).append("|");
            }
            body.append("\n");
        }
                
        StringBuilder converged = new StringBuilder();
        converged.append(headers.toString()).append("\n");
        converged.append(body.toString());   
        
        return converged.toString();
    }
    
}
