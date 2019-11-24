package com.faceless.requests;


import com.faceless.responses.Response;
import com.faceless.vmservice.containers.PropertyContainer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public abstract class RequestHandler
{
	public abstract void handle(Request request, Response response, PropertyContainer propertyContainer) throws IOException;

	public static boolean assertRightMethod(String method, Request request, Response response) throws IOException
	{
		if (!method.equalsIgnoreCase(request.getMethod()))
		{
			System.out.println("Method not allowed");
			response.setStatus("405");
			response.setDescription("Method Not Allowed");
			response.writeResponse("");
			return false;
		}
		return true;
	}

	public static void throwForbidden(Response response) throws IOException
	{
		response.setStatus("403");
		response.setDescription("NOTOK");
		response.writeResponse("");
	}

	public static boolean checkValuesNotNull(Response response, Object ...values) throws IOException {
		if(Arrays.stream(values).anyMatch(Objects::isNull)){
			throwForbidden(response);
			return false;
		}
		return true;
	}
}
