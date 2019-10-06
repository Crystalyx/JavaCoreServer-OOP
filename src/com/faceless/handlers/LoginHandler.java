package com.faceless.handlers;

import com.faceless.Application;
import com.faceless.containers.PropertyContainer;
import com.faceless.requests.Request;
import com.faceless.requests.RequestHandler;
import com.faceless.responses.Response;

import java.io.IOException;

public class LoginHandler extends RequestHandler
{
	@Override
	public void handle(Request request, Response response, PropertyContainer propertyContainer) throws IOException
	{
		if (!"POST".equalsIgnoreCase(request.getMethod()))
		{
			System.out.println("Method not allowed");
			response.setStatus("405");
			response.setDescription("Method Not Allowed");
			response.writeResponse("");
			return;
		}

		String login    = request.getArgumentValue("login");
		String password = request.getArgumentValue("password");
		propertyContainer.setProperty("logged_in", "true");
		propertyContainer.setProperty("login", login);
		propertyContainer.setProperty("password", password);
		response.setStatus("200");
		response.setDescription("OK");
		response.writeResponse(Application.server.loginPageDocument.toString());
	}
}
