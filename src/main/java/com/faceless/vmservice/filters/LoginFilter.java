package com.faceless.vmservice.filters;

import com.faceless.requests.Request;
import com.faceless.requests.RequestFilter;
import com.faceless.vmservice.containers.PropertyContainer;


public class LoginFilter extends RequestFilter
{
	@Override
	public boolean filter(Request request, PropertyContainer propertyContainer)
	{
		return propertyContainer.getProperty("logged_in").equals("true");
	}

	@Override
	public String getErrorString()
	{
		return "Not logged in";
	}

	@Override
	public int getErrorCode()
	{
		return 403;
	}
}
