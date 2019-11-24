package com.faceless.vmservice.handlers;

import com.faceless.Application;
import com.faceless.requests.Request;
import com.faceless.requests.RequestHandler;
import com.faceless.responses.Response;
import com.faceless.vmservice.containers.PropertyContainer;

import java.io.IOException;

public class OrderVMPageHandler extends RequestHandler
{
	@Override
	public void handle(Request request, Response response, PropertyContainer propertyContainer) throws IOException
	{
		if (!assertRightMethod("GET", request, response))
			return;

		response.setStatus("200");
		response.setDescription("OK");
		response.writeResponse(Application.server.orderVMPageDocument.toString());
	}
}
