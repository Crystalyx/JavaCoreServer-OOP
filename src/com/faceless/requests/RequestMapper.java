package com.faceless.requests;

import com.faceless.HttpServer;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class RequestMapper
{
	private Hashtable<String, RequestHandler>      handlers = new Hashtable<>();
	private Hashtable<String, List<RequestFilter>> filters  = new Hashtable<>();

	public void registerHandler(HttpServer.Paths path, RequestHandler handler)
	{
		handlers.put(path.path, handler);
	}

	public void registerFilter(RequestFilter filter, HttpServer.Paths... paths)
	{
		for (HttpServer.Paths pathEnum : paths)
		{
			String path = pathEnum.path;
			if (!filters.containsKey(path))
				filters.put(path, new ArrayList<>());
			filters.get(path).add(filter);
		}
	}

	public RequestHandler getHandler(String path)
	{
		return handlers.get(path);
	}

	public List<RequestFilter> getFilters(String path)
	{
		if (filters.containsKey(path))
			return filters.get(path);
		return new ArrayList<>();
	}
}
