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

package com.zenika.dispatcher.model;

import io.netty.handler.codec.http.HttpResponseStatus;
import org.vertx.java.core.json.JsonObject;

/**
 * Created by maxence on 08/04/14.
 */
public class PalmResponse {//TODO find a better solution
    JsonObject jsonObject = new JsonObject();

    public PalmResponse() {
        setStatusCode(HttpResponseStatus.OK);

    }

    public JsonObject body() {
        return  jsonObject.getObject("body");
    }

    public PalmResponse setBody(JsonObject body) {
        jsonObject.putObject("body",body);
        return this;
    }

    public int getStatusCode() {
        return jsonObject.getInteger("statusCode");
    }

    public PalmResponse setStatusCode(HttpResponseStatus statusCode) {
        jsonObject.putNumber("statusCode", statusCode.code());
        return this;
    }

    public JsonObject toJSON() {
        return jsonObject;
    }

}
