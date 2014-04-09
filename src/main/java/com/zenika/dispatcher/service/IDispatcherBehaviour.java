package com.zenika.dispatcher.service;

import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;

/**
 * Created by maxence on 09/04/14.
 */
public interface IDispatcherBehaviour<T extends Message<String>> {
    void send404HttpError(HttpServerRequest req);

    /**
     * Should not end the response
     * @param result
     * @param req
     */
    void handleResult(T result, HttpServerRequest req);

    String getEventAddress(HttpServerRequest req);

    String createMessageToSend(HttpServerRequest req);

    String getRouteMatcher();
}
