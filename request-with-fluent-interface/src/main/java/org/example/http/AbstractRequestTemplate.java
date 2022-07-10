package org.example.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public abstract class AbstractRequestTemplate implements Request {
    protected final HttpURLConnection connection;

    protected AbstractRequestTemplate(String baseUrl) {
        this.connection = getConnection(baseUrl);
    }

    @Override
    public AbstractRequestTemplate addHeader(String key, String value) {
        connection.setRequestProperty(key, value);
        return this;
    }

    @Override
    public <T> ResponseResource<T> execute(Class<T> returnType) {
        return toResponseResource(returnType);
    }

    private HttpURLConnection getConnection(String baseUrl) {
        try {
            URL url = new URL(baseUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> ResponseResource<T> toResponseResource(Class<T> returnType) {
        int responseCode = getResponseCode();
        ResponseHeaders responseHeaders = getResponseHeaders();
        T responseBody = getResponseBody(returnType);
        connection.disconnect();
        return new ResponseResource<>(responseCode, responseHeaders, responseBody);
    }

    private int getResponseCode() {
        try {
            return connection.getResponseCode();
        } catch (IOException e) {
            connection.disconnect();
            throw new RuntimeException(e);
        }
    }

    private ResponseHeaders getResponseHeaders() {
        Map<String, List<String>> headerFields = connection.getHeaderFields();
        return new ResponseHeaders(headerFields);
    }

    private <T> T getResponseBody(Class<T> returnType) {
        try (
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            String inputLine;
            StringBuilder responseBodyString = new StringBuilder();
            while ((inputLine = bufferedReader.readLine()) != null) {
                responseBodyString.append(inputLine);
            }
            return toResponseType(returnType, responseBodyString);
        } catch (IOException e) {
            connection.disconnect();
            throw new RuntimeException(e);
        }
    }

    private <T> T toResponseType(Class<T> returnType, StringBuilder responseBodyString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseBodyString.toString(), returnType);
    }
}
