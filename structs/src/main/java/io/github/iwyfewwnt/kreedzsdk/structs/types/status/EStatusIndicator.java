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

package io.github.iwyfewwnt.kreedzsdk.structs.types.status;

import io.github.iwyfewwnt.uwutils.*;

import java.util.Map;
import java.util.function.Supplier;

/**
 * An enumeration of kreedz API status indicators.
 */
@SuppressWarnings("unused")
public enum EStatusIndicator {

	/**
	 * A kreedz API status indicator - Critical.
	 */
	CRITICAL("critical", "Critical"),

	/**
	 * A kreedz API status indicator - Major.
	 */
	MAJOR("major", "Major"),

	/**
	 * A kreedz API status indicator - Minor.
	 */
	MINOR("minor", "Minor"),

	/**
	 * A kreedz API status indicator - Maintenance.
	 */
	MAINTENANCE("maintenance", "Maintenance"),

	/**
	 * A kreedz API status indicator - None.
	 */
	NONE("none", "None");

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = EStatusIndicator.class.getSimpleName();

	/**
	 * An array {@link EStatusIndicator} instances.
	 */
	private static final EStatusIndicator[] VALUES = UwEnum.values(EStatusIndicator.class);

	/**
	 * A map of {@link EStatusIndicator} instances by their API-name field.
	 */
	private static final Map<String, EStatusIndicator> MAP_BY_API_NAME = UwMap.newMapByFieldOrNull(
			entry -> entry.apiName, EStatusIndicator.class
	);

	/**
	 * A map of {@link EStatusIndicator} instances by their full name field.
	 */
	private static final Map<String, EStatusIndicator> MAP_BY_FULL_NAME = UwMap.newMapByFieldOrNull(
			entry -> entry.fullName, EStatusIndicator.class
	);

	/**
	 * An API-name.
	 */
	private final String apiName;

	/**
	 * A full name.
	 */
	private final String fullName;

	/**
	 * A {@link #toString()} cache.
	 */
	private String stringCache;

	/**
	 * A {@link #stringCache} mutex.
	 */
	private final Object stringCacheMutex;

	/**
	 * Initialize an {@link EStatusIndicator} instance.
	 *
	 * @param apiName	API-name
	 * @param fullName	full name
	 */
	EStatusIndicator(String apiName, String fullName) {
		if (apiName == null) {
			throw new IllegalArgumentException("API-name mustn't be <null>");
		}

		if (fullName == null) {
			throw new IllegalArgumentException("Full name mustn't be <null>");
		}

		this.apiName = apiName;
		this.fullName = fullName;

		this.stringCacheMutex = new Object();
	}

	/**
	 * Get this API-name.
	 *
	 * @return	API-name
	 */
	public String getApiName() {
		return this.apiName;
	}

	/**
	 * Get this full name.
	 *
	 * @return	full name
	 */
	public String getFullName() {
		return this.fullName;
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
					+ "apiName=\"" + this.apiName + "\""
					+ ", fullName=\"" + this.fullName + "\""
					+ "]");
		}
	}

	/**
	 * Get an API-name from the provided {@link EStatusIndicator} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EStatusIndicator} instance is {@code null}.
	 * </ul>
	 *
	 * @param indicator		status indicator instance from which get the API-name
	 * @param defaultValue	default value to return on failure
	 * @return				API-name value of the indicator or the default one
	 */
	public static String getApiNameOrElse(EStatusIndicator indicator, String defaultValue) {
		return UwObject.ifNotNull(indicator, EStatusIndicator::getApiName, defaultValue);
	}

	/**
	 * Get an API-name from the provided {@link EStatusIndicator} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EStatusIndicator} instance is {@code null}.
	 * </ul>
	 *
	 * @param indicator				status indicator instance from which get the API-name
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						API-name value of the indicator or the default one
	 */
	public static String getApiNameOrElse(EStatusIndicator indicator, Supplier<String> defaultValueSupplier) {
		return UwObject.ifNull(getApiNameOrNull(indicator), defaultValueSupplier);
	}

	/**
	 * Get an API-name from the provided {@link EStatusIndicator} instance
	 * or return an empty string if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EStatusIndicator} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getApiNameOrElse(EStatusIndicator, String)}
	 * w/ {@link UwString#EMPTY} as the default value.
	 *
	 * @param indicator		status indicator instance from which get the API-name
	 * @return				API-name value of the indicator or the empty string
	 */
	public static String getApiNameOrEmpty(EStatusIndicator indicator) {
		return getApiNameOrElse(indicator, UwString.EMPTY);
	}

	/**
	 * Get an API-name from the provided {@link EStatusIndicator} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EStatusIndicator} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getApiNameOrElse(EStatusIndicator, String)}
	 * w/ {@code null} as the default value.
	 *
	 * @param indicator		status indicator instance from which get the API-name
	 * @return				API-name value of the indicator or {@code null}
	 */
	public static String getApiNameOrNull(EStatusIndicator indicator) {
		return getApiNameOrElse(indicator, (String) null);
	}

	/**
	 * Get an full name from the provided {@link EStatusIndicator} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EStatusIndicator} instance is {@code null}.
	 * </ul>
	 *
	 * @param indicator		status indicator instance from which get the full name
	 * @param defaultValue	default value to return on failure
	 * @return				full name value of the indicator or the default one
	 */
	public static String getFullNameOrElse(EStatusIndicator indicator, String defaultValue) {
		return UwObject.ifNotNull(indicator, EStatusIndicator::getFullName, defaultValue);
	}

	/**
	 * Get an full name from the provided {@link EStatusIndicator} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EStatusIndicator} instance is {@code null}.
	 * </ul>
	 *
	 * @param indicator				status indicator instance from which get the full name
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						full name value of the indicator or the default one
	 */
	public static String getFullNameOrElse(EStatusIndicator indicator, Supplier<String> defaultValueSupplier) {
		return UwObject.ifNull(getFullNameOrNull(indicator), defaultValueSupplier);
	}

	/**
	 * Get an full name from the provided {@link EStatusIndicator} instance
	 * or return an empty string if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EStatusIndicator} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getFullNameOrElse(EStatusIndicator, String)}
	 * w/ {@link UwString#EMPTY} as the default value.
	 *
	 * @param indicator		status indicator instance from which get the full name
	 * @return				full name value of the indicator or the empty string
	 */
	public static String getFullNameOrEmpty(EStatusIndicator indicator) {
		return getFullNameOrElse(indicator, UwString.EMPTY);
	}

	/**
	 * Get an full name from the provided {@link EStatusIndicator} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EStatusIndicator} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getFullNameOrElse(EStatusIndicator, String)}
	 * w/ {@code null} as the default value.
	 *
	 * @param indicator		status indicator instance from which get the full name
	 * @return				full name value of the indicator or {@code null}
	 */
	public static String getFullNameOrNull(EStatusIndicator indicator) {
		return getFullNameOrElse(indicator, (String) null);
	}

	/**
	 * Get an {@link EStatusIndicator} instance by its API-name
	 * or return a default value if failed.
	 *
	 * @param apiName		API-name of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link EStatusIndicator} instance or the default value
	 */
	public static EStatusIndicator fromApiNameOrElse(String apiName, EStatusIndicator defaultValue) {
		return UwMap.getOrElse(apiName, MAP_BY_API_NAME, defaultValue);
	}

	/**
	 * Get an {@link EStatusIndicator} instance by its API-name
	 * or return a default value if failed.
	 *
	 * @param apiName				API-name of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link EStatusIndicator} instance or the default value
	 */
	public static EStatusIndicator fromApiNameOrElse(String apiName, Supplier<EStatusIndicator> defaultValueSupplier) {
		return UwObject.ifNull(fromApiNameOrNull(apiName), defaultValueSupplier);
	}

	/**
	 * Get an {@link EStatusIndicator} instance by its API-name
	 * or return {@code null} if failed.
	 *
	 * <p>Wraps {@link #fromApiNameOrElse(String, EStatusIndicator)}
	 * w/ {@code null} as the default value.
	 *
	 * @param apiName	API-name of the instance
	 * @return			associated {@link EStatusIndicator} instance or {@code null}
	 */
	public static EStatusIndicator fromApiNameOrNull(String apiName) {
		return fromApiNameOrElse(apiName, (EStatusIndicator) null);
	}

	/**
	 * Get an {@link EStatusIndicator} instance by its full name
	 * or return a default value if failed.
	 *
	 * @param fullName		full name of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link EStatusIndicator} instance or the default value
	 */
	public static EStatusIndicator fromFullNameOrElse(String fullName, EStatusIndicator defaultValue) {
		return UwMap.getOrElse(fullName, MAP_BY_FULL_NAME, defaultValue);
	}

	/**
	 * Get an {@link EStatusIndicator} instance by its full name
	 * or return a default value if failed.
	 *
	 * @param fullName				full name of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link EStatusIndicator} instance or the default value
	 */
	public static EStatusIndicator fromFullNameOrElse(String fullName, Supplier<EStatusIndicator> defaultValueSupplier) {
		return UwObject.ifNull(fromFullNameOrNull(fullName), defaultValueSupplier);
	}

	/**
	 * Get an {@link EStatusIndicator} instance by its full name
	 * or return {@code null} if failed.
	 *
	 * <p>Wraps {@link EStatusIndicator#fromFullNameOrElse(String, EStatusIndicator)}
	 * w/ {@code null} as the default value.
	 *
	 * @param fullName	full name of the instance
	 * @return			associated {@link EStatusIndicator} instance or {@code null}
	 */
	public static EStatusIndicator fromFullNameOrNull(String fullName) {
		return fromFullNameOrElse(fullName, (EStatusIndicator) null);
	}

	/**
	 * Get an {@link EStatusIndicator} instance by its index
	 * or return a default value if failed.
	 *
	 * @param index			index of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link EStatusIndicator} instance or the default value
	 */
	public static EStatusIndicator fromIndexOrElse(Integer index, EStatusIndicator defaultValue) {
		return UwArray.getOrElse(index, VALUES, defaultValue);
	}

	/**
	 * Get an {@link EStatusIndicator} instance by its index
	 * or return a default value if failed.
	 *
	 * @param index					index of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link EStatusIndicator} instance or the default value
	 */
	public static EStatusIndicator fromIndexOrElse(Integer index, Supplier<EStatusIndicator> defaultValueSupplier) {
		return UwObject.ifNull(fromIndexOrNull(index), defaultValueSupplier);
	}

	/**
	 * Get an {@link EStatusIndicator} instance by its index
	 * or return {@code null} if failed.
	 *
	 * @param index		index of the instance
	 * @return			associated {@link EStatusIndicator} instance or {@code null}
	 */
	public static EStatusIndicator fromIndexOrNull(Integer index) {
		return fromIndexOrElse(index, (EStatusIndicator) null);
	}
}
