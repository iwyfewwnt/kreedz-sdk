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

package io.github.iwyfewwnt.kreedzsdk.structs.types.mapimage;

import java.io.Serializable;

/**
 * An enumeration of map image extensions.
 */
@SuppressWarnings("unused")
enum EMapImageExtension implements Serializable {

	/**
	 * A map image extension - Webp.
	 */
	WEBP("webp"),

	/**
	 * A map image extension - Jpg.
	 */
	JPG("jpg");

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = EMapImageExtension.class.getSimpleName();

	/**
	 * String value.
	 */
	final String value;

	/**
	 * A {@link EMapImageExtension#toString()} cache.
	 */
	private String stringCache;

	/**
	 * A {@link #stringCache} mutex.
	 */
	private final Object stringCacheMutex;

	/**
	 * Initialize an {@link EMapImageExtension} instance.
	 *
	 * @param value		string value
	 */
	EMapImageExtension(String value) {
		this.value = value;

		this.stringCacheMutex = new Object();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		if (this.stringCache != null) {
			return this.stringCache;
		}

		synchronized (this.stringCacheMutex) {
			if (this.stringCache != null) {
				return this.stringCache;
			}

			return (this.stringCache = SIMPLE_NAME
					+ "::" + this.name());
		}
	}
}
