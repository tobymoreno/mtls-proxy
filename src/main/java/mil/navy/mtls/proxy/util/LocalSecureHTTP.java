package mil.navy.mtls.proxy.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import nl.altindag.ssl.SSLFactory;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;


public class LocalSecureHTTP {
    
    public static String getResponse(SSLFactory sslFactory, String server, String bodyMessage, MediaType mediaType) throws IOException {
        OkHttpClient okHttpClient = null;
        if(sslFactory != null) {
            okHttpClient = okHttpClient(sslFactory);
        }
        else {
            okHttpClient = new OkHttpClient();
        }

        RequestBody body = RequestBody.create(bodyMessage, mediaType);

        Request request = new Request.Builder()
                .url(server)       
                .post(body)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }
    
    public static String getResponse(SSLFactory sslFactory, String server, String bodyMessage, MediaType mediaType, HashMap<String, String> headers, String method) throws IOException {
        OkHttpClient okHttpClient = null;
        if(sslFactory != null) {
            okHttpClient = okHttpClient(sslFactory);
        }
        else {
            okHttpClient = new OkHttpClient();
        }

        
        RequestBody body = null;
        if(bodyMessage != null) {
            body = RequestBody.create(bodyMessage, mediaType);
        }
        
        Builder b = new Request.Builder();

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            b.addHeader(key, value);
        }
        
        if(method != null && method.equalsIgnoreCase("POST") && body != null) {
            b.post(body);
        }
        else {
            b.get();
        }

        Request request = b
                .url(server)
                .build();


        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }    

    public static Response getResponse(SSLFactory sslFactory, String server, byte[] bodyMessage, MediaType mediaType, HashMap<String, String> headers, String method) throws IOException {
        OkHttpClient okHttpClient = null;
        if(sslFactory != null) {
            okHttpClient = okHttpClient(sslFactory);
        }
        else {
            okHttpClient = new OkHttpClient();
        }

        RequestBody body = null;
        if(bodyMessage != null) {
            body = RequestBody.create(bodyMessage, mediaType);
        }
        
        Builder b = new Request.Builder();

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            b.addHeader(key, value);
        }
        
        if(method != null && method.equalsIgnoreCase("POST") && body != null) {
            b.post(body);
        }
        else {
            b.get();
        }

        Request request = b
                .url(server)
                .build();

        

        Response response = okHttpClient.newCall(request).execute();
        return response;
    }    
    
    

    public static String getResponse(String server, String bodyMessage, MediaType mediaType) throws IOException {
        SSLConfig sslConfig = new SSLConfig();

        // we have to make this not hard coded
        String keyStorePath = "server.keystore";
        String keyStorePassword = "secret";
        String trustStorePath = "truststore.jks";
        String trustStorePassword = "changeit";

        SSLFactory sslFactory = sslConfig.sslFactory(true, true,
                keyStorePath, keyStorePassword.toCharArray(), trustStorePath, trustStorePassword.toCharArray());

        OkHttpClient okHttpClient = null;
        if(sslFactory != null) {
            okHttpClient = okHttpClient(sslFactory);
        }
        else {
            okHttpClient = new OkHttpClient();
        }
        
        RequestBody body = RequestBody.create(bodyMessage, mediaType);

        Builder b = new Request.Builder();
        Request request = b
                .url(server)
                .post(body)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }

    /**
     * get the https response from soap server
     * @param server
     * @param bodyMessage
     * @param mediaType
     * @param keyStorePath
     * @param keyStorePassword
     * @param trustStorePath
     * @param trustStorePassword
     * @return
     * @throws IOException 
     */
    public static String getResponse(
            String server, 
            String bodyMessage, 
            MediaType mediaType, 
            String keyStorePath, 
            String keyStorePassword,
            String trustStorePath, 
            String trustStorePassword,
            boolean oneWay,
            boolean twoWay) throws IOException {
        SSLConfig sslConfig = new SSLConfig();

        SSLFactory sslFactory = sslConfig.sslFactory(oneWay, twoWay,
                keyStorePath, keyStorePassword.toCharArray(), trustStorePath, trustStorePassword.toCharArray());

        OkHttpClient okHttpClient = null;
        if(sslFactory != null) {
            okHttpClient = okHttpClient(sslFactory);
        }
        else {
            okHttpClient = new OkHttpClient();
        }
        
        RequestBody body = RequestBody.create(bodyMessage, mediaType);

        Builder b = new Request.Builder();
        Request request = b
                .url(server)
                .post(body)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }    
    
    public static OkHttpClient okHttpClient(SSLFactory sslFactory) {
        return new OkHttpClient.Builder()
                .sslSocketFactory(sslFactory.getSslSocketFactory(), sslFactory.getTrustManager().get())
                .hostnameVerifier(sslFactory.getHostnameVerifier())
                .build();
    }    

    
}
