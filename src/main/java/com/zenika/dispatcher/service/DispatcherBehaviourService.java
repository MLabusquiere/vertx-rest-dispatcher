package com.zenika.dispatcher.service;

import com.zenika.dispatcher.model.PalmJsonRequest;
import com.zenika.dispatcher.model.PalmJsonResponse;
import com.zenika.dispatcher.model.PalmJsonResponseReader;
import com.zenika.dispatcher.model.PalmResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.HttpServerResponse;
import org.vertx.java.core.json.JsonObject;

/**
 * @author M. Labusquière
 */
public class DispatcherBehaviourService implements IDispatcherBehaviour {


    @Override
    public void send404HttpError(HttpServerRequest req) {
        String moduleName = req.params().get(MODULE_NAME_REQ_PARAM);
        req.response().setStatusCode(HttpResponseStatus.NOT_FOUND.code());
        req.response().end("The module " + moduleName + " is not installed");
    }

    @Override
    public void handleResult(Message<JsonObject> busResult, HttpServerRequest req) {
        HttpServerResponse response = req.response();
		PalmResponse jsonResponse = new PalmJsonResponseReader(busResult.body());
		response.setStatusCode(jsonResponse.getStatusCode());
        response.end(jsonResponse.getContent().toString());
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
