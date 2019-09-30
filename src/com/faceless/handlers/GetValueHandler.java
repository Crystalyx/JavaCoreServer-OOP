package com.faceless.handlers;

import com.faceless.Application;
import com.faceless.containers.PropertyContainer;
import com.faceless.requests.Request;
import com.faceless.requests.RequestHandler;
import com.faceless.responses.Response;

import java.io.IOException;

public class GetValueHandler extends RequestHandler
{
	@Override
	public void handle(Request request, Response response, PropertyContainer propertyContainer) throws IOException
	{
		if (!"GET".equalsIgnoreCase(request.getMethod()))
		{
			System.out.println("Method not allowed");
			response.setStatus("405");
			response.setDescription("Method Not Allowed");
			response.writeResponse("Method Not Allowed");
			return;
		}

		String valueName = request.getArgumentValue("name");
		if (Application.server.propertyContainer.hasProperty(valueName))
		{
			response.setStatus("200");
			response.setDescription("OK");
			response.setJsonResponse();
			response.writeResponse("{\n\t\"" + valueName + "\" : \"" +
								   Application.server.propertyContainer.getProperty(valueName) + "\"\n}");
		}
		else
		{
			response.setStatus("404");
			response.setDescription("Value not found");
		}
	}
}
