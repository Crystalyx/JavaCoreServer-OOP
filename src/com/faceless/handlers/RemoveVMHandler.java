package com.faceless.handlers;

import com.faceless.Application;
import com.faceless.containers.PropertyContainer;
import com.faceless.requests.Request;
import com.faceless.requests.RequestHandler;
import com.faceless.responses.Response;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RemoveVMHandler extends RequestHandler
{
	@Override
	public void handle(Request request, Response response, PropertyContainer propertyContainer) throws IOException
	{
		if (!"DELETE".equalsIgnoreCase(request.getMethod()))
		{
			System.out.println("Method not allowed");
			response.setStatus("405");
			response.setDescription("Method Not Allowed");
			response.writeResponse("");
			return;
		}

		String login = request.getArgumentValue("login");
		int    index = Integer.parseInt(request.getArgumentValue("index"));
		ResultSet rs = Application.server.database.executeQuery("SELECT owner,\n" +
																"       vmname,\n" +
																"       cpuvendor,\n" +
																"       cpufrequency,\n" +
																"       cpucorecount,\n" +
																"       ramvolume,\n" +
																"       hddvolume,\n" +
																"       monitor,\n" +
																"       os\n" +
																"from vms\n" +
																"WHERE owner = '" + login + "';");
		try
		{
			StringBuilder result = new StringBuilder();
			int           i      = 1;
			String vm_name = null,
					cpu_vendor = null,
					cpu_frequency = null,
					cpu_core_count = null,
					ram_volume = null,
					hdd_volume = null,
					monitor_enabled = null,
					os = null;
			while (rs.next() && i <= index)
			{
				//Retrieve by column name
				vm_name = rs.getString("vmname");
				cpu_vendor = rs.getString("cpuvendor");
				cpu_frequency = rs.getString("cpufrequency");
				cpu_core_count = rs.getString("cpucorecount");
				ram_volume = rs.getString("ramvolume");
				hdd_volume = rs.getString("hddvolume");
				monitor_enabled = rs.getString("monitor");
				os = rs.getString("os");
				i++;
			}
			result.append("DELETE FROM vms ")
				  .append("WHERE owner='").append(login)
				  .append("' AND vmname='").append(vm_name)
				  .append("' AND cpuvendor='").append(cpu_vendor)
				  .append("' AND cpufrequency='").append(cpu_frequency)
				  .append("' AND cpucorecount='").append(cpu_core_count)
				  .append("' AND ramvolume='").append(ram_volume)
				  .append("' AND hddvolume='").append(hdd_volume)
				  .append("' AND monitor='").append(monitor_enabled)
				  .append("' AND os='").append(os)
				  .append("';");
			String stmt = result.toString();
			System.out.println(result);
			Application.server.database.executeUpdate(stmt);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		response.setStatus("200");
		response.setDescription("OK");
		response.writeResponse("");
	}
}
