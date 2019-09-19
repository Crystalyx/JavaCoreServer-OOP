package com.faceless;

import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer
{

	public static void main(String[] args)
	{
		try
		{
			runServer(args);
		}
		catch (Throwable throwable)
		{
			throwable.printStackTrace();
		}
	}

	private static void runServer(String... args) throws Throwable
	{
		ServerSocket ss = new ServerSocket(8080);//select socket to accept requests from
		//noinspection InfiniteLoopStatement
		while (true)
		{
			Socket s = ss.accept();//lock until request accepted
			System.err.println("[INFO]Client accepted");
			new Thread(new SocketProcessor(s)).start();//starting new thread to process request
		}
	}
}