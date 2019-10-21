package com.faceless.filters;

import com.faceless.containers.PropertyContainer;
import com.faceless.requests.Request;
import com.faceless.requests.RequestFilter;


public class PermissionFilter extends RequestFilter
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
