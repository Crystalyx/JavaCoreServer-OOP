package com.faceless;

import com.faceless.auth.AuthServer;
import com.faceless.vmservice.VmServer;

public class Application {
	public static HttpServer server;
    public static String authURL = "http://localhost:8081";
    public static String databasePort = "25565";
    public static String databaseURL = "jdbc:mysql://localhost?login=root&password=root&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&maxReconnects=20&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public static void main(String[] args) {
        run(args);
    }

    private static void run(String[] args) {
        try {
            if (args[0].equals("--authservice"))
                server = new AuthServer();
            if (args[0].equals("--vmservice"))
                server = new VmServer();

            String ip = args[1];
            String databasePort = args[2];
            authURL = "http://" + ip + ":8081";
            databaseURL = "jdbc:mysql://" + ip + ":" + databasePort + "?login=root&password=root&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&maxReconnects=20&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"

            if (server != null)
                server.runServer();
        } catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}
}