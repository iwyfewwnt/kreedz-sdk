/*
 * Copyright 2023 iwyfewwnt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.iwyfewwnt.kreedzsdk.adapters;

import com.google.auto.service.AutoService;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import io.github.iwyfewwnt.kreedzsdk.adapterapi.IKreedzTypeAdapter;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.inner.RunTime;

import java.lang.reflect.Type;

/**
 * A {@link RunTime} JSON deserializer.
 */
@SuppressWarnings("unused")
@AutoService(IKreedzTypeAdapter.class)
public final class RunTimeJsonDeserializer implements JsonDeserializer<RunTime>, IKreedzTypeAdapter {

	/**
	 * Initialize a {@link RunTimeJsonDeserializer} instance.
	 */
	public RunTimeJsonDeserializer() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RunTime deserialize(JsonElement json, Type type, JsonDeserializationContext context) {
		return new RunTime(context.deserialize(json, Float.class));
	}
}
