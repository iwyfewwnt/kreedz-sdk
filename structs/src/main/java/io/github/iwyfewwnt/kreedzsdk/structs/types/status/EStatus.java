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
 * An enumeration of kreedz API statuses.
 */
@SuppressWarnings("unused")
public enum EStatus {

	/**
	 * A kreedz API status - Operational.
	 */
	OPERATIONAL("operational", "Operational"),

	/**
	 * A kreedz API status - Partial Outage.
	 */
	PARTIAL_OUTAGE("partial_outage", "Partial Outage"),

	/**
	 * A kreedz API status - Major Outage.
	 */
	MAJOR_OUTAGE("major_outage", "Major Outage"),

	/**
	 * A kreedz API status - Identified.
	 */
	IDENTIFIED("identified", "Identified"),

	/**
	 * A kreedz API status - Scheduled.
	 */
	SCHEDULED("scheduled", "Scheduled"),

	/**
	 * A kreedz API status - Under Maintenance.
	 */
	UNDER_MAINTENANCE("under_maintenance", "Under Maintenance"),

	/**
	 * A kreedz API status - In Progress.
	 */
	IN_PROGRESS("in_progress", "In Progress"),

	/**
	 * A kreedz API status - Resolved.
	 */
	RESOLVED("resolved", "Resolved"),

	/**
	 * A kreedz API status - Completed.
	 */
	COMPLETED("completed", "Completed"),

	/**
	 * A kreedz API status - Investigating.
	 */
	INVESTIGATING("investigating", "Investigating"),

	/**
	 * A kreedz API status - Monitoring.
	 */
	MONITORING("monitoring", "Monitoring"),

	/**
	 * A kreedz API status - Verifying.
	 */
	VERIFYING("verifying", "Verifying");

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = EStatus.class.getSimpleName();

	/**
	 * An array of {@link EStatus} instances.
	 */
	private static final EStatus[] VALUES = UwEnum.values(EStatus.class);

	/**
	 * A map of {@link EStatus} instances by their API-name field.
	 */
	private static final Map<String, EStatus> MAP_BY_API_NAME = UwMap.newMapByFieldOrNull(
			entry -> entry.apiName, EStatus.class
	);

	/**
	 * A map of {@link EStatus} instances by their full name field.
	 */
	private static final Map<String, EStatus> MAP_BY_FULL_NAME = UwMap.newMapByFieldOrNull(
			entry -> entry.fullName, EStatus.class
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
	 * Initialize an {@link EStatus} instance.
	 *
	 * @param apiName	API-name
	 * @param fullName	full name
	 */
	EStatus(String apiName, String fullName) {
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
	 * Get an API-name from the provided {@link EStatus} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EStatus} instance is {@code null}.
	 * </ul>
	 *
	 * @param status		status instance from which get the API-name
	 * @param defaultValue	default value to return on failure
	 * @return				API-name value of the status or the default one
	 */
	public static String getApiNameOrElse(EStatus status, String defaultValue) {
		return UwObject.ifNotNull(status, EStatus::getApiName, defaultValue);
	}

	/**
	 * Get an API-name from the provided {@link EStatus} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EStatus} instance is {@code null}.
	 * </ul>
	 *
	 * @param status				status instance from which get the API-name
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						API-name value of the status or the default one
	 */
	public static String getApiNameOrElse(EStatus status, Supplier<String> defaultValueSupplier) {
		return UwObject.ifNull(getApiNameOrNull(status), defaultValueSupplier);
	}

	/**
	 * Get an API-name from the provided {@link EStatus} instance
	 * or return an empty string if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EStatus} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getApiNameOrElse(EStatus, String)}
	 * w/ {@link UwString#EMPTY} as the default value.
	 *
	 * @param status	status instance from which get the API-name
	 * @return			API-name value of the status or the empty string
	 */
	public static String getApiNameOrEmpty(EStatus status) {
		return getApiNameOrElse(status, UwString.EMPTY);
	}

	/**
	 * Get an API-name from the provided {@link EStatus} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EStatus} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getApiNameOrElse(EStatus, String)}
	 * w/ {@code null} as the default value.
	 *
	 * @param status	status instance from which get the API-name
	 * @return			API-name value of the status or {@code null}
	 */
	public static String getApiNameOrNull(EStatus status) {
		return getApiNameOrElse(status, (String) null);
	}

	/**
	 * Get a full name from the provided {@link EStatus} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EStatus} instance is {@code null}.
	 * </ul>
	 *
	 * @param status		status instance from which get the full name
	 * @param defaultValue	default value to return on failure
	 * @return				full name value of the status or the default one
	 */
	public static String getFullNameOrElse(EStatus status, String defaultValue) {
		return UwObject.ifNotNull(status, EStatus::getFullName, defaultValue);
	}

	/**
	 * Get a full name from the provided {@link EStatus} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EStatus} instance is {@code null}.
	 * </ul>
	 *
	 * @param status				status instance from which get the full name
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						full name value of the status or the default one
	 */
	public static String getFullNameOrElse(EStatus status, Supplier<String> defaultValueSupplier) {
		return UwObject.ifNull(getFullNameOrNull(status), defaultValueSupplier);
	}

	/**
	 * Get a full name from the provided {@link EStatus} instance
	 * or return an empty string if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EStatus} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getFullNameOrElse(EStatus, String)}
	 * w/ {@link UwString#EMPTY} as the default value.
	 *
	 * @param status	status instance from which get the full name
	 * @return			full name value of the status or the empty string
	 */
	public static String getFullNameOrEmpty(EStatus status) {
		return getFullNameOrElse(status, UwString.EMPTY);
	}

	/**
	 * Get a full name from the provided {@link EStatus} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EStatus} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getFullNameOrElse(EStatus, String)}
	 * w/ {@code null} as the default value.
	 *
	 * @param status	status instance from which get the full name
	 * @return			full name value of the status or {@code null}
	 */
	public static String getFullNameOrNull(EStatus status) {
		return getFullNameOrElse(status, (String) null);
	}

	/**
	 * Get an {@link EStatus} instance by its API-name
	 * or return a default value if failed.
	 *
	 * @param apiName		API-name of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link EStatus} instance or the default value
	 */
	public static EStatus fromApiNameOrElse(String apiName, EStatus defaultValue) {
		return UwMap.getOrElse(apiName, MAP_BY_API_NAME, defaultValue);
	}

	/**
	 * Get an {@link EStatus} instance by its API-name
	 * or return a default value if failed.
	 *
	 * @param apiName				API-name of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link EStatus} instance or the default value
	 */
	public static EStatus fromApiNameOrElse(String apiName, Supplier<EStatus> defaultValueSupplier) {
		return UwObject.ifNull(fromApiNameOrNull(apiName), defaultValueSupplier);
	}

	/**
	 * Get an {@link EStatus} instance by its API-name
	 * or return {@code null} if failed.
	 *
	 * <p>Wraps {@link #fromApiNameOrElse(String, EStatus)}
	 * w/ {@code null} as the default value.
	 *
	 * @param apiName	API-name of the instance
	 * @return			associated {@link EStatus} instance or {@code null}
	 */
	public static EStatus fromApiNameOrNull(String apiName) {
		return fromApiNameOrElse(apiName, (EStatus) null);
	}

	/**
	 * Get an {@link EStatus} instance by its full name
	 * or return a default value if failed.
	 *
	 * @param fullName		full name of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link EStatus} instance or the default value
	 */
	public static EStatus fromFullNameOrElse(String fullName, EStatus defaultValue) {
		return UwMap.getOrElse(fullName, MAP_BY_FULL_NAME, defaultValue);
	}

	/**
	 * Get an {@link EStatus} instance by its full name
	 * or return a default value if failed.
	 *
	 * @param fullName				full name of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link EStatus} instance or the default value
	 */
	public static EStatus fromFullNameOrElse(String fullName, Supplier<EStatus> defaultValueSupplier) {
		return UwObject.ifNull(fromFullNameOrNull(fullName), defaultValueSupplier);
	}

	/**
	 * Get an {@link EStatus} instance by its full name
	 * or return {@code null} if failed.
	 *
	 * <p>Wraps {@link #fromFullNameOrElse(String, EStatus)}
	 * w/ {@code null} as the default value.
	 *
	 * @param fullName	full name of the instance
	 * @return			associated {@link EStatus} instance or {@code null}
	 */
	public static EStatus fromFullNameOrNull(String fullName) {
		return fromFullNameOrElse(fullName, (EStatus) null);
	}

	/**
	 * Get an {@link EStatus} instance by its index
	 * or return a default value if failed.
	 *
	 * @param index			index of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link EStatus} instance or the default value
	 */
	public static EStatus fromIndexOrElse(Integer index, EStatus defaultValue) {
		return UwArray.getOrElse(index, VALUES, defaultValue);
	}

	/**
	 * Get an {@link EStatus} instance by its index
	 * or return a default value if failed.
	 *
	 * @param index					index of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link EStatus} instance or the default value
	 */
	public static EStatus fromIndexOrElse(Integer index, Supplier<EStatus> defaultValueSupplier) {
		return UwObject.ifNull(fromIndexOrNull(index), defaultValueSupplier);
	}

	/**
	 * Get an {@link EStatus} instance by its index
	 * or return {@code null} if failed.
	 *
	 * <p>Wraps {@link #fromIndexOrElse(Integer, EStatus)}
	 * w/ {@code null} as the default value.
	 *
	 * @param index		index of the instance
	 * @return			associated {@link EStatus} instance or {@code null}
	 */
	public static EStatus fromIndexOrNull(Integer index) {
		return fromIndexOrElse(index, (EStatus) null);
	}
}
