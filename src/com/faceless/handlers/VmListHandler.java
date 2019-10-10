package com.faceless.handlers;

import com.faceless.Application;
import com.faceless.containers.PropertyContainer;
import com.faceless.requests.Request;
import com.faceless.requests.RequestHandler;
import com.faceless.responses.Response;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VmListHandler extends RequestHandler
{

	@Override
	public void handle(Request request, Response response, PropertyContainer propertyContainer) throws IOException
	{
		if (!"GET".equalsIgnoreCase(request.getMethod()))
		{
			System.out.println("Method not allowed");
			response.setStatus("405");
			response.setDescription("Method Not Allowed");
			response.writeResponse("");
			return;
		}

		String login = request.getArgumentValue("login");
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
			while (rs.next())
			{
				//Retrieve by column name
				String vm_name         = rs.getString("vmname");
				String cpu_vendor      = rs.getString("cpuvendor");
				String cpu_frequency   = rs.getString("cpufrequency");
				String cpu_core_count  = rs.getString("cpucorecount");
				String ram_volume      = rs.getString("ramvolume");
				String hdd_volume      = rs.getString("hddvolume");
				String monitor_enabled = rs.getString("monitor");
				String os              = rs.getString("os");

				result.append("<tr><td>").append(i)
					  .append("</td><td>").append(vm_name)
					  .append("</td><td>").append(cpu_vendor.substring(0, 1).toUpperCase()).append(cpu_vendor.substring(1))
					  .append("</td><td>").append(cpu_frequency
						.replace("khz", "KHz")
						.replace("mhz", "MHz")
						.replace("ghz", "GHz"))
					  .append("</td><td>").append(cpu_core_count)
					  .append("</td><td>").append(ram_volume).append(" GB")
					  .append("</td><td>").append(hdd_volume).append(" GB")
					  .append("</td><td>").append(monitor_enabled.equals("1"))
					  .append("</td><td>").append(os)
					  .append("<td><button id=\"edit_vm\" onclick=\"edit_vm(")
					  .append(i).append(")\">Edit VM</button>")
					  .append("</td><td><button id=\"remove_vm\" onclick=\"remove_vm(")
					  .append(i++).append(")\">Remove VM</button></td>")
					  .append("</td></tr>");
			}

			response.setStatus("200");
			response.setDescription("OK");
			response.writeResponse(result.toString());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
