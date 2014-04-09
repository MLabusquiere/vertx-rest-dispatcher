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

import com.zenika.dispatcher.service.IDispatcherBehaviour;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonObject;

/**
 * Created by maxence on 07/04/14.
 */
public final class PalmRequest { //TODO find a better solution

    JsonObject jsonObject = new JsonObject();

    //Helper
    public PalmRequest(HttpServerRequest httpRequest) {
        setModuleName(httpRequest.params().get(IDispatcherBehaviour.MODULE_NAME_REQ_PARAM));
    }

    public String getModuleName() {
        return jsonObject.getString("moduleName");
    }

    public void setModuleName(String moduleName)  {
        jsonObject.putString("moduleName", moduleName);
    }

    public String toJSON() {
        return jsonObject.toString();
    }

}
