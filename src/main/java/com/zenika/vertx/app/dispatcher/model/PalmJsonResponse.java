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

import org.vertx.java.core.json.JsonObject;

/**
 * @author M. Labusquière
 */
public class PalmJsonResponse extends JsonObject implements PalmResponse	{

	public static final String UNIQUE_STRING_FIELD_NAME = "message";

	public PalmJsonResponse(JsonObject content, int status) {
		putNumber(STATUS_FIELD_NAME, Integer.valueOf(status));
		putObject(CONTENT_FIELD_NAME, content);
	}

	public PalmJsonResponse(JsonObject body) {
		this(body, 200);
	}

	public PalmJsonResponse(String body, int status) {
		this(new JsonObject().putString(UNIQUE_STRING_FIELD_NAME,body), status);
	}

	public PalmJsonResponse(String body) {
		this(new JsonObject().putString(UNIQUE_STRING_FIELD_NAME,body), 200);
	}

	@Override
	public int getStatusCode() {
		return getInteger(STATUS_FIELD_NAME);
	}

	@Override
	public JsonObject getContent()	{
		return getObject(CONTENT_FIELD_NAME);
	}

}
