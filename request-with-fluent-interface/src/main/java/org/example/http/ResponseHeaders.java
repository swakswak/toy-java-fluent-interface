package org.example.http;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ResponseHeaders {
    private final Map<String, List<String>> headerMap;

    public ResponseHeaders(Map<String, List<String>> headerMap) {
        this.headerMap = headerMap;
    }

    public Optional<String> get(String key) {
        return Optional.ofNullable(headerMap.getOrDefault(key, null))
                .map(strings -> String.join(", ", strings));
    }

    public boolean contains(String key) {
        return headerMap.containsKey(key);
    }
}
