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

package io.github.iwyfewwnt.kreedzsdk.structs.types.health;

import io.github.iwyfewwnt.uwutils.*;

import java.util.Map;
import java.util.function.Supplier;

/**
 * An enumeration of health API endpoints.
 */
@SuppressWarnings("unused")
public enum EHealthEndpoint {

	/**
	 * A health API endpoint - GlobalAPI.
	 */
	GLOBAL_API("GlobalAPI", "", "globalapi");

	/**
	 * Key format string.
	 *
	 * <p>Arguments in order:
	 * <ul>
	 *     <li>String :: API-group.
	 *     <li>String :: API-endpoint.
	 * </ul>
	 */
	public static final String KEY_FMT = "%s_%s";

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = EHealthEndpoint.class.getSimpleName();

	/**
	 * An array of {@link EHealthEndpoint} instances.
	 */
	private static final EHealthEndpoint[] VALUES = UwEnum.values(EHealthEndpoint.class);

	/**
	 * A map of {@link EHealthEndpoint} instances by their API-name field.
	 */
	private static final Map<String, EHealthEndpoint> MAP_BY_NAME = UwMap.newMapByFieldOrNull(
			entry -> entry.name, EHealthEndpoint.class
	);

	/**
	 * A map of {@link EHealthEndpoint} instances by their API-key field.
	 */
	private static final Map<String, EHealthEndpoint> MAP_BY_KEY = UwMap.newMapByFieldOrNull(
			entry -> entry.key, EHealthEndpoint.class
	);

	/**
	 * An API-name.
	 */
	private final String name;

	/**
	 * An API-group.
	 */
	private final String group;

	/**
	 * An API-endpoint.
	 */
	private final String endpoint;

	/**
	 * An API-key.
	 */
	private final String key;

	/**
	 * A {@link #toString()} cache.
	 */
	private volatile String stringCache;

	/**
	 * A {@link #stringCache} mutex.
	 */
	private final Object stringCacheMutex;

	/**
	 * Initialize an {@link EHealthEndpoint} instance.
	 *
	 * @param name		API-name
	 * @param group		API-group
	 * @param endpoint	API-endpoint
	 */
	EHealthEndpoint(String name, String group, String endpoint) {
		if (name == null) {
			throw new IllegalArgumentException("Name mustn't be <null>");
		}

		if (group == null) {
			throw new IllegalArgumentException("Group mustn't be <null>");
		}

		if (endpoint == null) {
			throw new IllegalArgumentException("Endpoint mustn't be <null>");
		}

		this.name = name;
		this.group = group;
		this.endpoint = endpoint;

		this.key = String.format(KEY_FMT, this.group, this.endpoint);

		this.stringCacheMutex = new Object();
	}

	/**
	 * Get this API-name.
	 *
	 * @return	API-name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Get this API-group.
	 *
	 * @return	API-group
	 */
	public String getGroup() {
		return this.group;
	}

	/**
	 * Get this API-endpoint.
	 *
	 * @return	API-endpoint
	 */
	public String getEndpoint() {
		return this.endpoint;
	}

	/**
	 * Get this API-key.
	 *
	 * @return	API-key
	 */
	public String getKey() {
		return this.key;
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
					+ "::" + this.name() + "["
					+ "name=\"" + this.name + "\""
					+ ", group=\"" + this.group + "\""
					+ ", endpoint=\"" + this.endpoint + "\""
					+ ", key=\"" + this.key + "\""
					+ "]");
		}
	}

	/**
	 * Get an API-name from the provided {@link EHealthEndpoint} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EHealthEndpoint} instance is {@code null}.
	 * </ul>
	 *
	 * @param endpoint		health endpoint instance from which get the API-name
	 * @param defaultValue	default value to return on failure
	 * @return				API-name value of the health endpoint or the default one
	 */
	public static String getNameOrElse(EHealthEndpoint endpoint, String defaultValue) {
		return UwObject.ifNotNull(endpoint, EHealthEndpoint::getName, defaultValue);
	}

	/**
	 * Get an API-name from the provided {@link EHealthEndpoint} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EHealthEndpoint} instance is {@code null}.
	 * </ul>
	 *
	 * @param endpoint				health endpoint instance from which get the API-name
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						API-name value of the health endpoint or the default one
	 */
	public static String getNameOrElse(EHealthEndpoint endpoint, Supplier<String> defaultValueSupplier) {
		return UwObject.ifNull(getNameOrNull(endpoint), defaultValueSupplier);
	}

	/**
	 * Get an API-name from the provided {@link EHealthEndpoint} instance
	 * or return an empty string if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EHealthEndpoint} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getNameOrElse(EHealthEndpoint, String)}
	 * w/ {@link UwString#EMPTY} as the default value.
	 *
	 * @param endpoint	health endpoint instance from which get the API-name
	 * @return			API-name value of the health endpoint or the empty string
	 */
	public static String getNameOrEmpty(EHealthEndpoint endpoint) {
		return getNameOrElse(endpoint, UwString.EMPTY);
	}

	/**
	 * Get an API-name from the provided {@link EHealthEndpoint} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EHealthEndpoint} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getNameOrElse(EHealthEndpoint, String)}
	 * w/ {@code null} as the default value.
	 *
	 * @param endpoint	health endpoint instance from which get the API-name
	 * @return			API-name value of the health endpoint or {@code null}
	 */
	public static String getNameOrNull(EHealthEndpoint endpoint) {
		return getNameOrElse(endpoint, (String) null);
	}

	/**
	 * Get an API-group from the provided {@link EHealthEndpoint} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EHealthEndpoint} instance is {@code null}.
	 * </ul>
	 *
	 * @param endpoint		health endpoint instance from which get the API-group
	 * @param defaultValue	default value to return on failure
	 * @return				API-group value of the health endpoint or the default one
	 */
	public static String getGroupOrElse(EHealthEndpoint endpoint, String defaultValue) {
		return UwObject.ifNotNull(endpoint, EHealthEndpoint::getGroup, defaultValue);
	}

	/**
	 * Get an API-group from the provided {@link EHealthEndpoint} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EHealthEndpoint} instance is {@code null}.
	 * </ul>
	 *
	 * @param endpoint				health endpoint instance from which get the API-group
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						API-group value of the health endpoint or the default one
	 */
	public static String getGroupOrElse(EHealthEndpoint endpoint, Supplier<String> defaultValueSupplier) {
		return UwObject.ifNull(getGroupOrNull(endpoint), defaultValueSupplier);
	}

	/**
	 * Get an API-group from the provided {@link EHealthEndpoint} instance
	 * or return an empty string if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EHealthEndpoint} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getGroupOrElse(EHealthEndpoint, String)}
	 * w/ {@link UwString#EMPTY} as the default value.
	 *
	 * @param endpoint	health endpoint instance from which get the API-group
	 * @return			API-group value of the health endpoint or the empty string
	 */
	public static String getGroupOrEmpty(EHealthEndpoint endpoint) {
		return getGroupOrElse(endpoint, UwString.EMPTY);
	}

	/**
	 * Get an API-group from the provided {@link EHealthEndpoint} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EHealthEndpoint} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getGroupOrElse(EHealthEndpoint, String)}
	 * w/ {@code null} as the default value.
	 *
	 * @param endpoint	health endpoint instance from which get the API-group
	 * @return			API-group value of the health endpoint or {@code null}
	 */
	public static String getGroupOrNull(EHealthEndpoint endpoint) {
		return getGroupOrElse(endpoint, (String) null);
	}

	/**
	 * Get an API-endpoint from the provided {@link EHealthEndpoint} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EHealthEndpoint} instance is {@code null}.
	 * </ul>
	 *
	 * @param endpoint		health endpoint instance from which get the API-endpoint
	 * @param defaultValue	default value to return on failure
	 * @return				API-endpoint value of the health endpoint or the default one
	 */
	public static String getEndpointOrElse(EHealthEndpoint endpoint, String defaultValue) {
		return UwObject.ifNotNull(endpoint, EHealthEndpoint::getEndpoint, defaultValue);
	}

	/**
	 * Get an API-endpoint from the provided {@link EHealthEndpoint} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EHealthEndpoint} instance is {@code null}.
	 * </ul>
	 *
	 * @param endpoint				health endpoint instance from which get the API-endpoint
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						API-endpoint value of the health endpoint or the default one
	 */
	public static String getEndpointOrElse(EHealthEndpoint endpoint, Supplier<String> defaultValueSupplier) {
		return UwObject.ifNull(getEndpointOrNull(endpoint), defaultValueSupplier);
	}

	/**
	 * Get an API-endpoint from the provided {@link EHealthEndpoint} instance
	 * or return an empty string if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EHealthEndpoint} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getEndpointOrElse(EHealthEndpoint, String)}
	 * w/ {@link UwString#EMPTY} as the default value.
	 *
	 * @param endpoint	health endpoint instance from which get the API-endpoint
	 * @return			API-endpoint value of the health endpoint or the empty string
	 */
	public static String getEndpointOrEmpty(EHealthEndpoint endpoint) {
		return getEndpointOrElse(endpoint, UwString.EMPTY);
	}

	/**
	 * Get an API-endpoint from the provided {@link EHealthEndpoint} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EHealthEndpoint} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getEndpointOrElse(EHealthEndpoint, String)}
	 * w/ {@code null} as the default value.
	 *
	 * @param endpoint	health endpoint instance from which get the API-endpoint
	 * @return			API-endpoint value of the health endpoint or {@code null}
	 */
	public static String getEndpointOrNull(EHealthEndpoint endpoint) {
		return getEndpointOrElse(endpoint, (String) null);
	}

	/**
	 * Get an API-key from the provided {@link EHealthEndpoint} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EHealthEndpoint} instance is {@code null}.
	 * </ul>
	 *
	 * @param endpoint		health endpoint instance from which get the API-key
	 * @param defaultValue	default value to return on failure
	 * @return				API-key value of the health endpoint or the default one
	 */
	public static String getKeyOrElse(EHealthEndpoint endpoint, String defaultValue) {
		return UwObject.ifNotNull(endpoint, EHealthEndpoint::getKey, defaultValue);
	}

	/**
	 * Get an API-key from the provided {@link EHealthEndpoint} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EHealthEndpoint} instance is {@code null}.
	 * </ul>
	 *
	 * @param endpoint				health endpoint instance from which get the API-key
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						API-key value of the health endpoint or the default one
	 */
	public static String getKeyOrElse(EHealthEndpoint endpoint, Supplier<String> defaultValueSupplier) {
		return UwObject.ifNull(getKeyOrNull(endpoint), defaultValueSupplier);
	}

	/**
	 * Get an API-key from the provided {@link EHealthEndpoint} instance
	 * or return an empty string if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EHealthEndpoint} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getKeyOrElse(EHealthEndpoint, String)}
	 * w/ {@link UwString#EMPTY} as the default value.
	 *
	 * @param endpoint	health endpoint instance from which get the API-key
	 * @return			API-key value of the health endpoint or the empty string
	 */
	public static String getKeyOrEmpty(EHealthEndpoint endpoint) {
		return getKeyOrElse(endpoint, UwString.EMPTY);
	}

	/**
	 * Get an API-key from the provided {@link EHealthEndpoint} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EHealthEndpoint} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getKeyOrElse(EHealthEndpoint, String)}
	 * w/ {@code null} as the default value.
	 *
	 * @param endpoint	health endpoint instance from which get the API-key
	 * @return			API-key value of the health endpoint or {@code null}
	 */
	public static String getKeyOrNull(EHealthEndpoint endpoint) {
		return getKeyOrElse(endpoint, (String) null);
	}

	/**
	 * Get an {@link EHealthEndpoint} instance by its API-name
	 * or return a default value if failed.
	 *
	 * @param name			API-name of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link EHealthEndpoint} instance or the default value
	 */
	public static EHealthEndpoint fromNameOrElse(String name, EHealthEndpoint defaultValue) {
		return UwMap.getOrElse(name, MAP_BY_NAME, defaultValue);
	}

	/**
	 * Get an {@link EHealthEndpoint} instance by its API-name
	 * or return a default value if failed.
	 *
	 * @param name					API-name of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link EHealthEndpoint} instance or the default value
	 */
	public static EHealthEndpoint fromNameOrElse(String name, Supplier<EHealthEndpoint> defaultValueSupplier) {
		return UwObject.ifNull(fromNameOrNull(name), defaultValueSupplier);
	}

	/**
	 * Get an {@link EHealthEndpoint} instance by its API-name
	 * or return {@code null} if failed.
	 *
	 * <p>Wraps {@link #fromNameOrElse(String, EHealthEndpoint)}
	 * w/ {@code null} as the default value.
	 *
	 * @param name	API-name of the instance
	 * @return		associated {@link EHealthEndpoint} instance or {@code null}
	 */
	public static EHealthEndpoint fromNameOrNull(String name) {
		return fromNameOrElse(name, (EHealthEndpoint) null);
	}

	/**
	 * Get an {@link EHealthEndpoint} instance by its API-key
	 * or return a default value if failed.
	 *
	 * @param key			API-key of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link EHealthEndpoint} instance or the default value
	 */
	public static EHealthEndpoint fromKeyOrElse(String key, EHealthEndpoint defaultValue) {
		return UwMap.getOrElse(key, MAP_BY_KEY, defaultValue);
	}

	/**
	 * Get an {@link EHealthEndpoint} instance by its API-key
	 * or return a default value if failed.
	 *
	 * @param key					API-key of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link EHealthEndpoint} instance or the default value
	 */
	public static EHealthEndpoint fromKeyOrElse(String key, Supplier<EHealthEndpoint> defaultValueSupplier) {
		return UwObject.ifNull(fromKeyOrNull(key), defaultValueSupplier);
	}

	/**
	 * Get an {@link EHealthEndpoint} instance by its API-key
	 * or return {@code null} if failed.
	 *
	 * <p>Wraps {@link #fromKeyOrElse(String, EHealthEndpoint)}
	 * w/ {@code null} as the default value.
	 *
	 * @param key	API-key of the instance
	 * @return		associated {@link EHealthEndpoint} instance or {@code null}
	 */
	public static EHealthEndpoint fromKeyOrNull(String key) {
		return fromKeyOrElse(key, (EHealthEndpoint) null);
	}

	/**
	 * Get an {@link EHealthEndpoint} instance by its index
	 * or return a default value if failed.
	 *
	 * @param index			index of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link EHealthEndpoint} instance or the default value
	 */
	public static EHealthEndpoint fromIndexOrElse(Integer index, EHealthEndpoint defaultValue) {
		return UwArray.getOrElse(index, VALUES, defaultValue);
	}
	/**
	 * Get an {@link EHealthEndpoint} instance by its index
	 * or return a default value if failed.
	 *
	 * @param index					index of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link EHealthEndpoint} instance or the default value
	 */
	public static EHealthEndpoint fromIndexOrElse(Integer index, Supplier<EHealthEndpoint> defaultValueSupplier) {
		return UwObject.ifNull(fromIndexOrNull(index), defaultValueSupplier);
	}

	/**
	 * Get an {@link EHealthEndpoint} instance by its index
	 * or return a default value if failed.
	 *
	 * <p>Wraps {@link #fromIndexOrElse(Integer, EHealthEndpoint)}
	 * w/ {@code null} as the default value.
	 *
	 * @param index		index of the instance
	 * @return			associated {@link EHealthEndpoint} instance or {@code null}
	 */
	public static EHealthEndpoint fromIndexOrNull(Integer index) {
		return fromIndexOrElse(index, (EHealthEndpoint) null);
	}
}
