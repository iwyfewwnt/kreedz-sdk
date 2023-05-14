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

package io.github.iwyfewwnt.kreedzsdk.structs.types;

import io.github.iwyfewwnt.uwutils.*;

import java.util.Map;
import java.util.function.Supplier;

/**
 * An enumeration of kreedz API versions.
 */
@SuppressWarnings("unused")
public enum EVersion {

	/**
	 * A kreedz API version - `1.0`.
	 */
	V1_0(1, 0),

	/**
	 * A kreedz API version - `2.0`.
	 */
	V2_0(2, 0);

	/**
	 * The latest kreedz API version.
	 */
	public static final EVersion LATEST = V2_0;

	/**
	 * A kreedz API version format string.
	 *
	 * <p>Arguments in order:
	 * <ul>
	 *     <li>Integer :: Major number.
	 *     <li>Integer :: Minor number.
	 * </ul>
	 */
	public static final String FMT = "v%d.%d";

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = EVersion.class.getSimpleName();

	/**
	 * An array of {@link EVersion} instances.
	 */
	private static final EVersion[] VALUES = UwEnum.values(EVersion.class);

	/**
	 * A map of {@link EVersion} instances by their API-name field.
	 */
	private static final Map<String, EVersion> MAP_BY_API_NAME = UwMap.newMapByFieldOrNull(
			entry -> entry.apiName, EVersion.class
	);

	/**
	 * A major number.
	 */
	private final int major;

	/**
	 * A minor number.
	 */
	private final int minor;

	/**
	 * An API-name.
	 */
	private final String apiName;

	/**
	 * A {@link #toString()} cache.
	 */
	private String stringCache;

	/**
	 * A {@link #stringCache} mutex.
	 */
	private final Object stringCacheMutex;

	/**
	 * Initialize an {@link EVersion} instance.
	 *
	 * @param major		major version
	 * @param minor		minor version
	 */
	EVersion(int major, int minor) {
		this.major = major;
		this.minor = minor;

		this.apiName = String.format(FMT, this.major, this.minor);

		this.stringCacheMutex = new Object();
	}

	/**
	 * Check if this is the latest kreedz API version.
	 *
	 * @return	boolean value, {@code true} - yes, {@code false} - no
	 */
	public boolean isLatest() {
		return isLatest(this);
	}

	/**
	 * Compare to {@link EVersion} instance by major {@literal &} minor numbers.
	 *
	 * <p>Wraps {@link #compare(EVersion, EVersion)}
	 * w/ {@code this} as the left operand.
	 *
	 * @param version	right operand
	 * @return			{@code 0} if this == version,
	 * 					{@code <0} if this {@literal <} version,
	 * 					{@code >0} if this {@literal >} version
	 */
	public int compare(EVersion version) {
		return compare(this, version);
	}

	/**
	 * Get this major number.
	 *
	 * @return	major number
	 */
	public int getMajor() {
		return this.major;
	}

	/**
	 * Get this minor number.
	 *
	 * @return	minor number
	 */
	public int getMinor() {
		return this.minor;
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
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		if (this.stringCache != null) {
			return stringCache;
		}

		synchronized (this.stringCacheMutex) {
			if (this.stringCache != null) {
				return stringCache;
			}

			return (this.stringCache = SIMPLE_NAME
					+ "::" + this.name() + "["
					+ "major=" + this.major
					+ ", minor=" + this.minor
					+ ", apiName=\"" + this.apiName + "\""
					+ "]");
		}
	}

	/**
	 * Check if the provided instance is the latest kreedz API version.
	 *
	 * @param version	{@link EVersion} instance to check for
	 * @return			boolean value, {@code true} - yes, {@code false} - no
	 */
	public static boolean isLatest(EVersion version) {
		return version == LATEST;
	}

	/**
	 * Compare two {@link EVersion} instances by their major {@literal &} minor numbers.
	 *
	 * @param v1	left operand
	 * @param v2	right operand
	 * @return		{@code 0} if v1 == v2,
	 * 				{@code <0} if v1 {@literal <} v2,
	 * 				{@code >0} if v1 {@literal >} v2
	 */
	public static int compare(EVersion v1, EVersion v2) {
		if (v1 == v2) {
			return 0;
		}

		if (v1 == null) {
			return -1;
		}

		if (v2 == null) {
			return 1;
		}

		if (v1.major != v2.major) {
			return Integer.compare(v1.major, v2.major);
		}

		return Integer.compare(v1.minor, v2.minor);
	}

	/**
	 * Get a major number from the provided {@link EVersion} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EVersion} instance is {@code null}.
	 * </ul>
	 *
	 * @param version		version instance from which get the major number
	 * @param defaultValue	default value to return on failure
	 * @return				major number of the version or the default value
	 */
	public static Integer getMajorOrElse(EVersion version, Integer defaultValue) {
		return UwObject.ifNotNull(version, EVersion::getMajor, defaultValue);
	}

	/**
	 * Get a major number from the provided {@link EVersion} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EVersion} instance is {@code null}.
	 * </ul>
	 *
	 * @param version				version instance from which get the major number
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						major number of the version or the default value
	 */
	public static Integer getMajorOrElse(EVersion version, Supplier<Integer> defaultValueSupplier) {
		return UwObject.ifNull(getMajorOrNull(version), defaultValueSupplier);
	}

	/**
	 * Get a major number from the provided {@link EVersion} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EVersion} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getMajorOrElse(EVersion, Integer)}
	 * w/ {@code null} as the default value.
	 *
	 * @param version	version instance from which get the major number
	 * @return			major number of the version or {@code null}
	 */
	public static Integer getMajorOrNull(EVersion version) {
		return getMajorOrElse(version, (Integer) null);
	}

	/**
	 * Get a minor number from the provided {@link EVersion} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EVersion} instance is {@code null}.
	 * </ul>
	 *
	 * @param version		version instance from which get the minor number
	 * @param defaultValue	default value to return on failure
	 * @return				minor number of the version or the default value
	 */
	public static Integer getMinorOrElse(EVersion version, Integer defaultValue) {
		return UwObject.ifNotNull(version, EVersion::getMinor, defaultValue);
	}

	/**
	 * Get a minor number from the provided {@link EVersion} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EVersion} instance is {@code null}.
	 * </ul>
	 *
	 * @param version				version instance from which get the minor number
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						minor number of the version or the default value
	 */
	public static Integer getMinorOrElse(EVersion version, Supplier<Integer> defaultValueSupplier) {
		return UwObject.ifNull(getMinorOrNull(version), defaultValueSupplier);
	}

	/**
	 * Get a minor number from the provided {@link EVersion} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EVersion} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getMinorOrElse(EVersion, Integer)}
	 * w/ {@code null} as the default value.
	 *
	 * @param version	version instance from which get the minor number
	 * @return			minor number of the version or {@code null}
	 */
	public static Integer getMinorOrNull(EVersion version) {
		return getMinorOrElse(version, (Integer) null);
	}

	/**
	 * Get an API-name from the provided {@link EVersion} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EVersion} instance is {@code null}.
	 * </ul>
	 *
	 * @param version		version instance from which get the API-name
	 * @param defaultValue	default value to return on failure
	 * @return				API-name of the version or the default value
	 */
	public static String getApiNameOrElse(EVersion version, String defaultValue) {
		return UwObject.ifNotNull(version, EVersion::getApiName, defaultValue);
	}

	/**
	 * Get an API-name from the provided {@link EVersion} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EVersion} instance is {@code null}.
	 * </ul>
	 *
	 * @param version				version instance from which get the API-name
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						API-name of the version or the default value
	 */
	public static String getApiNameOrElse(EVersion version, Supplier<String> defaultValueSupplier) {
		return UwObject.ifNull(getApiNameOrNull(version), defaultValueSupplier);
	}

	/**
	 * Get an API-name from the provided {@link EVersion} instance
	 * or return an empty string if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EVersion} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getApiNameOrElse(EVersion, String)}
	 * w/ {@link UwString#EMPTY} as the default value.
	 *
	 * @param version	version instance from which get the API-name
	 * @return			API-name of the version or the empty string
	 */
	public static String getApiNameOrEmpty(EVersion version) {
		return getApiNameOrElse(version, UwString.EMPTY);
	}

	/**
	 * Get an API-name from the provided {@link EVersion} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EVersion} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getApiNameOrElse(EVersion, String)}
	 * w/ {@code null} as the default value.
	 *
	 * @param version	version instance from which get the API-name
	 * @return			API-name of the version or {@code null}
	 */
	public static String getApiNameOrNull(EVersion version) {
		return getApiNameOrElse(version, (String) null);
	}

	/**
	 * Get an {@link EVersion} instance by its API-name
	 * or return a default value if failed.
	 *
	 * @param apiName		API-name of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link EVersion} instance or the default value
	 */
	public static EVersion fromApiNameOrElse(String apiName, EVersion defaultValue) {
		return UwMap.getOrElse(apiName, MAP_BY_API_NAME, defaultValue);
	}

	/**
	 * Get an {@link EVersion} instance by its API-name
	 * or return a default value if failed.
	 *
	 * @param apiName				API-name of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link EVersion} instance or the default value
	 */
	public static EVersion fromApiNameOrElse(String apiName, Supplier<EVersion> defaultValueSupplier) {
		return UwObject.ifNull(fromApiNameOrNull(apiName), defaultValueSupplier);
	}

	/**
	 * Get an {@link EVersion} instance by its API-name
	 * or return {@code null} if failed.
	 *
	 * <p>Wraps {@link #getApiNameOrElse(EVersion, String)}
	 * w/ {@code null} as the default value.
	 *
	 * @param apiName	API-name of the instance
	 * @return			associated {@link EVersion} instance or {@code null}
	 */
	public static EVersion fromApiNameOrNull(String apiName) {
		return fromApiNameOrElse(apiName, (EVersion) null);
	}

	/**
	 * Get an {@link EVersion} instance by its index
	 * or return a default value if failed.
	 *
	 * @param index			index of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link EVersion} instance or the default value
	 */
	public static EVersion fromIndexOrElse(Integer index, EVersion defaultValue) {
		return UwArray.getOrElse(index, VALUES, defaultValue);
	}

	/**
	 * Get an {@link EVersion} instance by its index
	 * or return a default value if failed.
	 *
	 * @param index					index of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link EVersion} instance or the default value
	 */
	public static EVersion fromIndexOrElse(Integer index, Supplier<EVersion> defaultValueSupplier) {
		return UwObject.ifNull(fromIndexOrNull(index), defaultValueSupplier);
	}

	/**
	 * Get an {@link EVersion} instance by its index
	 * or return {@code null} if failed.
	 *
	 * <p>Wraps {@link #fromIndexOrElse(Integer, EVersion)}
	 * w/ {@code null} as the default value.
	 *
	 * @param index		index of the instance
	 * @return			associated {@link EVersion} instance or {@code null}
	 */
	public static EVersion fromIndexOrNull(Integer index) {
		return fromIndexOrElse(index, (EVersion) null);
	}
}
