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

import io.netty.handler.codec.http.HttpRequest;
import org.vertx.java.core.http.HttpServerRequest;

import java.util.UUID;

/**
 * Created by maxence on 07/04/14.
 * Remember should be Immutable
 */
public final class PalmRequest { //TODO implement the vert.x class to say it's shareable

    private String id = UUID.randomUUID().toString();
    private String path;
    private String moduleName;

    public PalmRequest(HttpServerRequest httpRequest) {
        path = httpRequest.path();
        moduleName = path;
    }

    public String getId() {
        return id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String toJSON() {
        return null;
    }
}
