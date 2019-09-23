package com.faceless.requests;

import java.io.IOException;
import java.util.Map;
import java.util.StringTokenizer;

public class Request
{
	private RequestReader       reader;
	private String              method;
	private String              path;
	private Map<String, String> headers;
	private String              body;

	public Request(RequestReader reader) throws IOException
	{
		this.reader = reader;
		readStartLine();
		readHeaders();
	}

	private void readStartLine() throws IOException
	{
		String          startLine = reader.readStartLine();
		StringTokenizer tok       = new StringTokenizer(startLine, " ");
		method = tok.nextToken();
		path = tok.nextToken();
	}

	private void readHeaders() throws IOException
	{
		headers = reader.readHeaders();
	}

	public String getMethod()
	{
		return method;
	}

	public String getPath()
	{
		return path;
	}

	public Map<String, String> getHeaders()
	{
		return headers;
	}

	public String getBody() throws IOException
	{
		if (body == null)
		{
			body = reader.readBody();
		}
		return body;
	}
}
