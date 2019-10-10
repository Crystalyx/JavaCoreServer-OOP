package com.faceless.handlers;

import com.faceless.Application;
import com.faceless.containers.PropertyContainer;
import com.faceless.requests.Request;
import com.faceless.requests.RequestHandler;
import com.faceless.responses.Response;

import java.io.IOException;

public class CreateVMHandler extends RequestHandler
{
	@Override
	public void handle(Request request, Response response, PropertyContainer propertyContainer) throws IOException
	{
		if (!"PUT".equalsIgnoreCase(request.getMethod()))
		{
			System.out.println("Method not allowed");
			response.setStatus("405");
			response.setDescription("Method Not Allowed");
			response.writeResponse("");
			return;
		}

		String login           = propertyContainer.getProperty("login");
		String vm_name         = request.getArgumentValue("vm_name");
		String cpu_vendor      = request.getArgumentValue("cpu_vendor");
		String cpu_frequency   = request.getArgumentValue("cpu_frequency");
		String cpu_core_count  = request.getArgumentValue("cpu_core_count");
		String ram_volume      = request.getArgumentValue("ram_volume");
		String hdd_volume      = request.getArgumentValue("hdd_volume");
		String monitor_enabled = request.getArgumentValue("monitor_enabled");
		String os              = request.getArgumentValue("os");

		String stmt =
				"INSERT INTO vms(owner, vmname, cpuvendor, cpufrequency, cpucorecount, ramvolume, hddvolume, monitor, os) " +
				"VALUE ('" + login + "','"
				+ vm_name + "','"
				+ cpu_vendor + "','"
				+ cpu_frequency + "','"
				+ cpu_core_count + "','"
				+ ram_volume + "','"
				+ hdd_volume + "','"
				+ monitor_enabled + "','"
				+ os + "');";
		Application.server.database.executeUpdate(stmt);
		response.setStatus("200");
		response.setDescription("OK");
		response.writeResponse(Application.server.loginPageDocument.toString());
	}
}
