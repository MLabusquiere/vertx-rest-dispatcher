/*
 * Copyright 2014 Zenika
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author M. Labusquière
 */

package com.zenika.dispatcher;

import io.netty.handler.codec.http.HttpResponseStatus;
import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.HttpServerResponse;
import org.vertx.java.core.http.RouteMatcher;
import org.vertx.java.core.logging.Logger;

/*
This is a simple Java verticle which dispatch request to other verticle
 */
public class DispatcherVerticle extends PalmVerticle {

    //TODO dicuss about if it should be static
    private static Logger logger;
    private static long timeout = 1000;

    public void start() {

        super.start();
        logger = container.logger();

        final RouteMatcher routeMatcher = new RouteMatcher()
                .all("/:moduleName/*", new Handler<HttpServerRequest>() {
                    public void handle(final HttpServerRequest req) {

                        logger.debug("Starting to handle the request : " + req.absoluteURI());
                        PalmRequest preq = httpRequestToPalmRequest(req);

                        vertx.eventBus().sendWithTimeout(req.params().get("moduleName"), preq.toJSON(), timeout,new Handler<AsyncResult<Message<String>>>() {

                            @Override
                            public void handle(AsyncResult<Message<String>> asyncResp) {
                                if(asyncResp.succeeded())   {
                                    HttpServerResponse response = req.response();
                                    Message<String> message = asyncResp.result();
                                    response.end(message.body());
                                }else{
                                    send404HttpError();
                                }
                            }

                            private void send404HttpError() {
                                req.response().setStatusCode(HttpResponseStatus.NOT_FOUND.code());
                                req.response().end("The module " + req.params().get("moduleName") + " is not installed");
                            }
                        });
                    }
                });

        vertx.createHttpServer().requestHandler(routeMatcher).listen(8090);

        logger.info("Dispatcher innitialisation finished, listening on port 8090");

        //Purpose test

        vertx.eventBus().registerHandler("helloWorld1", new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {
                logger.debug("Receipt in the controller 1 " + message);
                message.reply(new PalmResponse().setBody("Hello World 1").toJSON());

            }
        });

        vertx.eventBus().registerHandler("helloWorld2",new Handler<Message<String>>() {
            @Override
            public void handle(final Message<String> message) {
                logger.debug("Receipt in the controller 2 " + message);
                message.reply(new PalmResponse().setBody("Hello World 2").toJSON());
            }
        });

    }
}

