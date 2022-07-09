package org.example.http;

public record ResponseResource<T>(int statusCode, ResponseHeaders headers, T body) {

    @Override
    public String toString() {
        return "ResponseResource{" +
                "statusCode=" + statusCode +
                ", headers=" + headers +
                ", body=" + body +
                '}';
    }
}
