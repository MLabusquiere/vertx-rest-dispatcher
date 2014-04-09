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

import java.io.Serializable;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by maxence on 08/04/14.
 */
public class PalmResponse implements Serializable {
    private int statusCode = 200;
    private static Map<String, String> headers;
    private String body = "";

    public static Map<String, String> getHeaders() {
        return headers;
    }

    public String body() {
        return body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public PalmResponse setBody(String body) {
        this.body = body;
        return this;
    }

    public String toJSON() {
        //TODO Implement
        return body;
    }
}
