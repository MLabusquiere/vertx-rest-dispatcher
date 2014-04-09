package com.zenika.dispatcher.service;

import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonObject;

/**
 * Created by maxence on 09/04/14.
 */
public interface IDispatcherBehaviour<T extends Message<JsonObject>> {

    public final static String MODULE_NAME_REQ_PARAM="moduleName";

    void send404HttpError(HttpServerRequest req);

    void handleResult(T result, HttpServerRequest req);

    String getEventAddress(HttpServerRequest req);

    String createMessageToSend(HttpServerRequest req);

    String getRouteMatcher();
}
