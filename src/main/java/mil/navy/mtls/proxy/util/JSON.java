/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mil.navy.mtls.proxy.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 *
 * @author tmoreno
 */
public class JSON<T> {

    private ObjectMapper mapper = new ObjectMapper();
    private Class<T> t;

    private JSON() {
    }

    public void set(Class<T> t) {
        this.t = t;
    }

    public Class<T> get() {
        return t;
    }

    public static JSON getInstance() {
        return JSONHolder.INSTANCE;
    }

    private static class JSONHolder {

        private static final JSON INSTANCE = new JSON<>();
    }

    public String stringify(T src) throws JsonProcessingException {
        return mapper.writeValueAsString(src);
    }

    public String stringify(T src, boolean beautify) throws JsonProcessingException {
        if (beautify) {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(src);
        } else {
            return stringify(src);
        }
    }

    public T parse(String jsonStr, Class<T> t) throws IOException {
        System.out.println(t);
        return mapper.readValue(jsonStr,t);
    }


}
