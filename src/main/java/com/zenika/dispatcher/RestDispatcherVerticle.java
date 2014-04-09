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

import com.zenika.dispatcher.model.PalmResponse;
import com.zenika.dispatcher.service.DispatcherBehaviourService;
import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.RouteMatcher;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.platform.Verticle;


public class RestDispatcherVerticle extends Verticle {

    private static final int DEFAULT_PORT = 8090;
    private int port;

    private static final long DEFAULT_TIME_OUT = 1000L;
    private long timeout;

    //TODO dicuss about if it should be static
    private static Logger logger;

    private DispatcherBehaviourService<Message<String>> behaviourService;

    //TODO see with a injection framework, if we can do better
    public RestDispatcherVerticle() {
        this.behaviourService = new DispatcherBehaviourService<Message<String>>();
    }

    public RestDispatcherVerticle(DispatcherBehaviourService<Message<String>> behaviourService) {
        this.behaviourService = behaviourService;
    }

    private void loadConfig() {

        JsonObject config = container.config().getObject("rest-dispatcher-verticle");

        timeout = (config.containsField("timeout")) ? config.getLong("timeout") : DEFAULT_TIME_OUT;
        port = (config.containsField("port")) ? config.getInteger("port") : DEFAULT_PORT;

    }

    public void start() {

        super.start();
        logger = container.logger();

        loadConfig();

        final RouteMatcher routeMatcher = new RouteMatcher()
                .all(behaviourService.getRouteMatcher(), new Handler<HttpServerRequest>() {
                    public void handle(final HttpServerRequest req) {

                        logger.debug("Starting to handle the request : " + req.absoluteURI());

                        vertx.eventBus().sendWithTimeout(behaviourService.getEventAddress(req), behaviourService.createMessageToSend(req), timeout,new Handler<AsyncResult<Message<String>>>() {

                            @Override
                            public void handle(AsyncResult<Message<String>> asyncResp) {
                                if(asyncResp.succeeded())   {
                                    behaviourService.handleResult(asyncResp.result(),req);
                                    req.response().end();
                                }else{
                                    behaviourService.send404HttpError(req);
                                    logger.warn("A client asked for a module not installed " );
                                }
                            }

                        });

                    }

                });

        vertx.createHttpServer().requestHandler(routeMatcher).listen(port);

        logger.info("Dispatcher innitialisation finished, listening on port " + port);

        //Test Purpose

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

