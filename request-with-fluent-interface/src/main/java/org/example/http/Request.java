package org.example.http;

public class Request {

    public static GetRequest get(String baseUrl) {
        return new GetRequest();
    }
}
