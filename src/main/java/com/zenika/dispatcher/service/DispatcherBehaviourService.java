package com.zenika.dispatcher.service;

import com.zenika.dispatcher.model.PalmRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.HttpServerResponse;

/**
 * Created by maxence on 09/04/14.
 */
public class DispatcherBehaviourService<T extends Message<String>> implements IDispatcherBehaviour<T> {

    @Override
    public void send404HttpError(HttpServerRequest req) {
        String moduleName = req.params().get("moduleName");
        req.response().setStatusCode(HttpResponseStatus.NOT_FOUND.code());
        req.response().end("The module " + moduleName + " is not installed");
    }

    @Override
    public void handleResult(T result, HttpServerRequest req) {
        HttpServerResponse response = req.response();
        response.write(result.body());
    }

    @Override
    public String getEventAddress(HttpServerRequest req) {
        return req.params().get("moduleName");
    }

    @Override
    public String createMessageToSend(HttpServerRequest req) {
        return new PalmRequest(req).toJSON();
    }

    @Override
    public String getRouteMatcher() {
        return "/:moduleName/*";
    }
}
