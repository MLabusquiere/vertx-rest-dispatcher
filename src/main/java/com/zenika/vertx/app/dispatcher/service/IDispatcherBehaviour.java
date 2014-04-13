package com.zenika.vertx.app.dispatcher.service;

import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonObject;

/**
 * Created by maxence on 09/04/14.
 */
public interface IDispatcherBehaviour {

    public final static String MODULE_NAME_REQ_PARAM = "moduleName";

    void send404HttpError(HttpServerRequest req);

    void handleResult(Message<JsonObject> result, HttpServerRequest req);

    String getModuleAddress(HttpServerRequest req);

    JsonObject createMessageToSend(HttpServerRequest req);

    String getRouteMatcherPattern();

}
