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

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.HttpServerResponse;
import org.vertx.java.platform.Verticle;

import java.util.Map;

/**
 * Created by maxence on 08/04/14.
 */
public class PalmVerticle extends Verticle {

    protected PalmRequest httpRequestToPalmRequest(HttpServerRequest req){
        return new PalmRequest(req);
    }

    protected void palmResponseToRequestResponse(HttpServerResponse resp, PalmResponse presp) {
        resp.setStatusCode(presp.getStatusCode());
        Map<String, String> headers = PalmResponse.getHeaders();
        for(String headerKey : headers.keySet())
            resp.putHeader(headerKey,headers.get(headerKey));
        resp.write(presp.body());
    }

}
