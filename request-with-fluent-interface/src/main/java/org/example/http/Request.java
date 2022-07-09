package org.example.http;

public interface Request {
    static GetRequest get(String baseUrl) {
        return new GetRequest(baseUrl);
    }

    Request addHeader(String key, String value);

    <T> ResponseResource<T> execute(Class<T> returnType);
}
