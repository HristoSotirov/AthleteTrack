package com.athletetrack.router;

import java.util.Map;

public class HttpRequest {
    private final String method;
    private final String path;
    private final String body;
    private final Map<String, String> pathVariables;
    private final Map<String, String> headers;

    public HttpRequest(String method, String path, String body, Map<String, String> pathVariables, Map<String, String> headers) {
        this.method = method;
        this.path = path;
        this.body = body;
        this.pathVariables = pathVariables;
        this.headers = headers;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getBody() {
        return body;
    }

    public Map<String, String> getPathVariables() {
        return pathVariables;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}
