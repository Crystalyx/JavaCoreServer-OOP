package com.faceless.responses;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Response {
	private ResponseWriter writer;
    private String status;
    private String description;
	private Map<String, String> headers = new HashMap<>();

    public Response(ResponseWriter writer) {
		this.writer = writer;
	}

    public void setStatus(String status) {
		this.status = status;
	}

    public void setDescription(String description) {
		this.description = description;
	}

    private void addHeader(String name, String value) {
		headers.put(name, value);
	}

    public void removeHeader(String name) {
		headers.remove(name);
	}

    public void clearHeaders() {
		headers.clear();
	}

    private void writeStartLineAndHeaders() throws IOException {
		writer.writeStartLine(status, description);
		writer.writeHeaders(headers);
		writer.flush();
	}

    private void writeBody(String body) throws IOException {
		writer.writeBody(body);
		writer.flush();
	}

    public void writeResponse(String body) throws IOException {
		writeStartLineAndHeaders();
		writeBody(body);
	}

    public void setJsonResponse() {
		addHeader("Content-Type", "application/json");
	}

    public void writeFromHttpResponse(CloseableHttpResponse authResponse) {
        String[] statusWords = authResponse.getStatusLine().toString().split(" ");
        this.status = statusWords[0];
        this.description = Arrays.stream(statusWords).skip(1).reduce((a, b) -> a + ' ' + b).orElse("");
        for (Header header : authResponse.getAllHeaders()) {
            this.headers.put(header.getName(), header.getValue());
        }
    }
}
