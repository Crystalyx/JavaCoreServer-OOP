package com.faceless.auth.handlers;

import com.faceless.AuthServiceApplication;
import com.faceless.requests.Request;
import com.faceless.requests.RequestHandler;
import com.faceless.responses.Response;
import com.faceless.vmservice.containers.PropertyContainer;

import java.io.IOException;

public class AuthHandler extends RequestHandler {
    @Override
    public void handle(Request request, Response response, PropertyContainer propertyContainer) throws IOException {
        String login = request.getArgumentValue("login");
        String password = request.getArgumentValue("password");

        if (!checkValuesNotNull(response, login, password))
            return;

        String stmt =//if not here
                "INSERT IGNORE INTO users(login, password)\n" +
                        "    VALUE ('" + login + "', '" + password + "')\n" +
                        "ON DUPLICATE KEY UPDATE login=login;";
        AuthServiceApplication.server.database.executeUpdate(stmt);

        response.setStatus("200");
        response.setDescription("OK");
        response.writeResponse("");
    }
}
