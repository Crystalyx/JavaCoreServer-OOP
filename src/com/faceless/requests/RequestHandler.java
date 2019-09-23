package com.faceless.requests;

import com.faceless.containers.PropertyContainer;
import com.faceless.responses.Response;

import java.io.IOException;

public abstract class RequestHandler
{
	public abstract void handle(Request request, Response response, PropertyContainer propertyContainer) throws IOException;
}
