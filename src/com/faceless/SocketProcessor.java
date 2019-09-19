package com.faceless;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import static com.faceless.Utilities.readFile;

public class SocketProcessor implements Runnable
{

	private String       homePage;// non-static to enable changes to file while server is working
	private Socket       s;
	private InputStream  is;
	private OutputStream os;

	SocketProcessor(Socket s) throws Throwable
	{
		this.s = s;
		this.is = s.getInputStream();
		this.os = s.getOutputStream();
		homePage = readFile("mainpage.html");
	}

	public void run()
	{
		try
		{
			readInputHeaders();
			writeResponse(homePage);
		}
		catch (Throwable t)
		{
			/*do nothing*/
		}
		finally
		{
			try
			{
				this.s.close();
			}
			catch (Throwable t)
			{
				/*do nothing*/
			}
		}
		System.err.println("[INFO]Client processing finished");
	}

	private void writeResponse(String s) throws Throwable
	{
		//response header
		String response = "HTTP/1.1 200 OK\r\n" +
						  "Server: YarServer/2009-09-09\r\n" +
						  "Content-Type: text/html\r\n" +
						  "Content-Length: " + s.length() + "\r\n" +
						  "Connection: close\r\n\r\n";
		String result = response + s;
		os.write(result.getBytes());
		os.flush();
	}

	private void readInputHeaders() throws Throwable
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(this.is));
		while (true)
		{
			String s = br.readLine();
			//reading header lines from stream until is ends(empty line is border)
			if (s == null || s.trim().length() == 0)
			{
				break;
			}
		}
	}
}