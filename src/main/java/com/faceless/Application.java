package com.faceless;

import com.faceless.vmservice.VmServer;

public class Application
{
	public static HttpServer server;

	public static void main(String[] args)
	{
		run();
	}

	private static void run()
	{
		try
		{
            server = new VmServer();
			server.runServer();
		}
		catch (Throwable throwable)
		{
			throwable.printStackTrace();
		}
	}
}
