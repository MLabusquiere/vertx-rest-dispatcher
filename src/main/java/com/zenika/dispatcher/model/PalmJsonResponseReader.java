/*
 * Copyright 2014 Zenika
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zenika.dispatcher.model;

import org.vertx.java.core.json.JsonObject;

/**
 * @author M. Labusquière
 */
public class PalmJsonResponseReader implements PalmResponse {

	JsonObject wrappedJson;

	public PalmJsonResponseReader(JsonObject wrappedJson) {
		this.wrappedJson = wrappedJson;
	}

	@Override
	public int getStatusCode() {
		return wrappedJson.getInteger(STATUS_FIELD_NAME);
	}

	@Override
	public String getContent()	{
		return wrappedJson.getString(CONTENT_FIELD_NAME);
	}

}