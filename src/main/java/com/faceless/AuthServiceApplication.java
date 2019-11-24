package com.faceless;

import com.faceless.auth.AuthServer;

public class AuthServiceApplication {
    public static HttpServer server;

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        try {
            server = new AuthServer();
            server.runServer();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
