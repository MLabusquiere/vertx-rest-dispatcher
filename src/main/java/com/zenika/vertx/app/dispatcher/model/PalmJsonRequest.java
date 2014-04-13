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

package com.zenika.vertx.app.dispatcher.model;

import org.vertx.java.core.MultiMap;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

/**
 * @author M. Labusquière
 */
public final class PalmJsonRequest extends JsonObject {

	public PalmJsonRequest(HttpServerRequest request)	{

		super();
		this.putString("path",request.path());
		this.putString("method", request.method());
		this.putObject("headers", serializeMultiMap(request.headers()));
		this.putObject("params",serializeMultiMap(request.params()));

	}

	private JsonObject serializeMultiMap(MultiMap multimap)	{

		JsonObject entries = new JsonObject();

		for(String name : multimap.names()) {
			JsonArray contents = new JsonArray();
			for(String content : multimap.getAll(name))	{
				contents.add(content);
			}
			JsonObject entry = new JsonObject().putArray(name, contents);
			entries.putObject(name, entry);
		}

		return entries;

	}
}
