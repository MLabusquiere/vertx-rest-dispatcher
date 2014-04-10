package com.zenika.dispatcher.service;

import com.zenika.dispatcher.model.PalmJsonRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.HttpServerResponse;
import org.vertx.java.core.json.JsonObject;

/**
 * @author M. Labusquière
 */
public class DispatcherBehaviourService implements IDispatcherBehaviour< Message<JsonObject>> {


    @Override
    public void send404HttpError(HttpServerRequest req) {
        String moduleName = req.params().get(MODULE_NAME_REQ_PARAM);
        req.response().setStatusCode(HttpResponseStatus.NOT_FOUND.code());
        req.response().end("The module " + moduleName + " is not installed");
    }

    @Override
    public void handleResult(Message<JsonObject> result, HttpServerRequest req) {
        HttpServerResponse response = req.response();
        JsonObject body = result.body();
        response.setStatusCode(body.getInteger("statusCode"));
        response.end(body.getObject("body").toString());
    }

    @Override
    public String getModuleAddress(HttpServerRequest req) {
        return req.params().get(MODULE_NAME_REQ_PARAM);
    }

    @Override
    public JsonObject createMessageToSend(HttpServerRequest req) {
        return new PalmJsonRequest(req);
    }

    @Override
    public String getRouteMatcherPattern() {
        return "/:" + MODULE_NAME_REQ_PARAM + "/*";
    }
}
