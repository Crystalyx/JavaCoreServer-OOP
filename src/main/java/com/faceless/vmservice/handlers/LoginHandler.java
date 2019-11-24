package com.faceless.vmservice.handlers;

import com.faceless.requests.Request;
import com.faceless.requests.RequestHandler;
import com.faceless.responses.Response;
import com.faceless.vmservice.containers.PropertyContainer;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class LoginHandler extends RequestHandler
{
    private static final HttpClientBuilder BUILDER = HttpClientBuilder.create();
    private static final String authURL = "http://localhost:8081";

    /**
     * Redirects login request to Auth Service
     *
     * @param request
     * @param response
     * @param propertyContainer
     * @throws IOException
     */
    @Override
    public void handle(Request request, Response response, PropertyContainer propertyContainer) throws IOException {
        if (!assertRightMethod("POST", request, response))
            return;

        String login = request.getArgumentValue("login");
        String password = request.getArgumentValue("password");

        if(!checkValuesNotNull(response, login,password))
            return;

        //send auth request
        HttpUriRequest authRequest = new HttpPost(authURL + "/login?login=" + login + "&password=" + password);
        CloseableHttpResponse authResponse = BUILDER.build().execute(authRequest);

        propertyContainer.setProperty("logged_in", "true");
        propertyContainer.setProperty("login", login);
        propertyContainer.setProperty("password", password);

        response.writeFromHttpResponse(authResponse);
        response.writeResponse("");
    }
}
