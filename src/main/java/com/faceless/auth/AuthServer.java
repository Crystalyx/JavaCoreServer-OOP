package com.faceless.auth;

import com.faceless.HttpServer;
import com.faceless.auth.handlers.AuthHandler;

public class AuthServer extends HttpServer {
    public AuthServer() {
        super(8081);
    }

    @Override
    protected void loadProperties() {
        mapper.registerHandler(Paths.login, new AuthHandler());
        super.loadProperties();
    }
}
