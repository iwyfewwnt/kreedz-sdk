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
 * An enumeration of kreedz API game modes.
 */
@SuppressWarnings("unused")
public enum EMode {

	/**
	 * A kreedz API game mode - [200] KZTimer.
	 */
	KZT(200, "kz_timer", "KZTimer", "KZT"),

	/**
	 * A kreedz API game mode - [201] SimpleKZ.
	 */
	SKZ(201, "kz_simple", "SimpleKZ", "SKZ"),

	/**
	 * A kreedz API game mode - [202] VanillaKZ.
	 */
	VNL(202, "kz_vanilla", "VanillaKZ", "VNL");

	/**
	 * A class instance of this class.
	 */
	private static final Class<EMode> CLASS = EMode.class;

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = CLASS.getSimpleName();

	/**
	 * An array of {@link EMode} instances.
	 */
	private static final EMode[] VALUES = UwEnum.values(CLASS);

	/**
	 * A map of {@link EMode} instances by their API-identifier field.
	 */
	private static final Map<Integer, EMode> MAP_BY_ID = UwMap.createByFieldOrNull(
			entry -> entry.id, VALUES
	);

	/**
	 * A map of {@link EMode} instances by their API-name field.
	 */
	private static final Map<String, EMode> MAP_BY_API_NAME = UwMap.createByFieldOrNull(
			entry -> entry.apiName, VALUES
	);

	/**
	 * A map of {@link EMode} instances by their full name field.
	 */
	private static final Map<String, EMode> MAP_BY_FULL_NAME = UwMap.createByFieldOrNull(
			entry -> entry.fullName, VALUES
	);

	/**
	 * A map of {@link EMode} instances by their short name field.
	 */
	private static final Map<String, EMode> MAP_BY_SHORT_NAME = UwMap.createByFieldOrNull(
			entry -> entry.shortName, VALUES
	);

	/**
	 * An API-identifier.
	 */
	private final int id;

	/**
	 * An API-name;
	 */
	private final String apiName;

	/**
	 * A full name.
	 */
	private final String fullName;

	/**
	 * A short name.
	 */
	private final String shortName;

	/**
	 * A {@link #toString()} cache.
	 */
	private String stringCache;

	/**
	 * A {@link #stringCache} mutex.
	 */
	private final Object stringCacheMutex;

	/**
	 * Initialize an {@link EMode} instance.
	 *
	 * @param id		 API-identifier
	 * @param apiName	 API-name
	 * @param fullName	 full name
	 * @param shortName	 short name
	 */
	EMode(int id, String apiName, String fullName, String shortName) {
		if (apiName == null) {
			throw new IllegalArgumentException("API-name mustn't be <null>");
		}

		if (fullName == null) {
			throw new IllegalArgumentException("Full name mustn't be <null>");
		}

		if (shortName == null) {
			throw new IllegalArgumentException("Short name mustn't be <null>");
		}

		this.id = id;
		this.apiName = apiName;
		this.fullName = fullName;
		this.shortName = shortName;

		this.stringCacheMutex = new Object();
	}

	/**
	 * Get this API-identifier.
	 *
	 * @return	API-identifier
	 */
	public int getId() {
		return this.id;
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
	 * Get this short name.
	 *
	 * @return	short name
	 */
	public String getShortName() {
		return this.shortName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		//noinspection DuplicatedCode
		if (this.stringCache != null) {
			return this.stringCache;
		}

		synchronized (this.stringCacheMutex) {
			if (this.stringCache != null) {
				return this.stringCache;
			}

			return (this.stringCache = SIMPLE_NAME
					+ "::" + this.name() + "["
					+ "id=" + this.id
					+ ", apiName=\"" + this.apiName + "\""
					+ ", fullName=\"" + this.fullName + "\""
					+ ", shortName=\"" + this.shortName + "\""
					+ "]");
		}
	}

	/**
	 * Get an API-identifier from the provided {@link EMode} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EMode} instance is {@code null}.
	 * </ul>
	 *
	 * @param mode			mode instance from which get the API-identifier
	 * @param defaultValue	default value to return on failure
	 * @return				API-identifier value of the mode or the default one
	 */
	public static Integer getIdOrElse(EMode mode, Integer defaultValue) {
		return UwObject.ifNotNull(mode, EMode::getId, defaultValue);
	}

	/**
	 * Get an API-identifier from the provided {@link EMode} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EMode} instance is {@code null}.
	 * </ul>
	 *
	 * @param mode					mode instance from which get the API-identifier
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						API-identifier value of the mode or the default one
	 */
	public static Integer getIdOrElse(EMode mode, Supplier<Integer> defaultValueSupplier) {
		return UwObject.ifNull(getIdOrNull(mode), defaultValueSupplier);
	}

	/**
	 * Get an API-identifier from the provided {@link EMode} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EMode} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getIdOrElse(EMode, Integer)}
	 * w/ {@code null} as the default value.
	 *
	 * @param mode	mode instance from which get the API-identifier
	 * @return		API-identifier value of the mode or {@code null}
	 */
	public static Integer getIdOrNull(EMode mode) {
		return getIdOrElse(mode, (Integer) null);
	}

	/**
	 * Get an API-name from the provided {@link EMode} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EMode} instance is {@code null}.
	 * </ul>
	 *
	 * @param mode			mode instance from which get the API-name
	 * @param defaultValue	default value to return on failure
	 * @return				API-name value of the mode or the default one
	 */
	public static String getApiNameOrElse(EMode mode, String defaultValue) {
		return UwObject.ifNotNull(mode, EMode::getApiName, defaultValue);
	}

	/**
	 * Get an API-name from the provided {@link EMode} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EMode} instance is {@code null}.
	 * </ul>
	 *
	 * @param mode					mode instance from which get the API-name
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						API-name value of the mode or the default one
	 */
	public static String getApiNameOrElse(EMode mode, Supplier<String> defaultValueSupplier) {
		return UwObject.ifNull(getApiNameOrNull(mode), defaultValueSupplier);
	}

	/**
	 * Get an API-name from the provided {@link EMode} instance
	 * or return an empty string if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EMode} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getApiNameOrElse(EMode, String)}
	 * w/ {@link UwString#EMPTY} as the default value.
	 *
	 * @param mode	mode instance from which get the API-name
	 * @return		API-name value of the mode or the empty string
	 */
	public static String getApiNameOrEmpty(EMode mode) {
		return getApiNameOrElse(mode, UwString.EMPTY);
	}

	/**
	 * Get an API-name from the provided {@link EMode} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EMode} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getApiNameOrElse(EMode, String)}
	 * w/ {@code null} as the default value.
	 *
	 * @param mode	mode instance from which get the API-name
	 * @return		API-name value of the mode or {@code null}
	 */
	public static String getApiNameOrNull(EMode mode) {
		return getApiNameOrElse(mode, (String) null);
	}

	/**
	 * Get a full name from the provided {@link EMode} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EMode} instance is {@code null}.
	 * </ul>
	 *
	 * @param mode			mode instance from which get the full name
	 * @param defaultValue	default value to return on failure
	 * @return				full name value of the mode or the default one
	 */
	public static String getFullNameOrElse(EMode mode, String defaultValue) {
		return UwObject.ifNotNull(mode, EMode::getFullName, defaultValue);
	}

	/**
	 * Get a full name from the provided {@link EMode} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EMode} instance is {@code null}.
	 * </ul>
	 *
	 * @param mode					mode instance from which get the full name
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						full name value of the mode or the default one
	 */
	public static String getFullNameOrElse(EMode mode, Supplier<String> defaultValueSupplier) {
		return UwObject.ifNull(getFullNameOrNull(mode), defaultValueSupplier);
	}

	/**
	 * Get a full name from the provided {@link EMode} instance
	 * or return an empty string if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EMode} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getFullNameOrElse(EMode, String)}
	 * w/ {@link UwString#EMPTY} as the default value.
	 *
	 * @param mode	mode instance from which get the full name
	 * @return		full name value of the mode or the empty string
	 */
	public static String getFullNameOrEmpty(EMode mode) {
		return getFullNameOrElse(mode, UwString.EMPTY);
	}

	/**
	 * Get a full name from the provided {@link EMode} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EMode} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getFullNameOrElse(EMode, String)}
	 * w/ {@code null} as the default value.
	 *
	 * @param mode	mode instance from which get the full name
	 * @return		full name value of the mode or {@code null}
	 */
	public static String getFullNameOrNull(EMode mode) {
		return getFullNameOrElse(mode, (String) null);
	}

	/**
	 * Get a short name from the provided {@link EMode} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EMode} instance is {@code null}.
	 * </ul>
	 *
	 * @param mode			mode instance from which get the short name
	 * @param defaultValue	default value to return on failure
	 * @return				short name value of the mode or the default one
	 */
	public static String getShortNameOrElse(EMode mode, String defaultValue) {
		return UwObject.ifNotNull(mode, EMode::getShortName, defaultValue);
	}

	/**
	 * Get a short name from the provided {@link EMode} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EMode} instance is {@code null}.
	 * </ul>
	 *
	 * @param mode					mode instance from which get the short name
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						short name value of the mode or the default one
	 */
	public static String getShortNameOrElse(EMode mode, Supplier<String> defaultValueSupplier) {
		return UwObject.ifNull(getShortNameOrNull(mode), defaultValueSupplier);
	}

	/**
	 * Get a short name from the provided {@link EMode} instance
	 * or return an empty string if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EMode} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getShortNameOrElse(EMode, String)}
	 * w/ {@link UwString#EMPTY} as the default value.
	 *
	 * @param mode	mode instance from which get the short name
	 * @return		short name value of the mode or the empty string
	 */
	public static String getShortNameOrEmpty(EMode mode) {
		return getShortNameOrElse(mode, UwString.EMPTY);
	}

	/**
	 * Get a short name from the provided {@link EMode} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EMode} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getShortNameOrElse(EMode, String)}
	 * w/ {@code null} as the default value.
	 *
	 * @param mode	mode instance from which get the short name
	 * @return		short name value of the mode or {@code null}
	 */
	public static String getShortNameOrNull(EMode mode) {
		return getShortNameOrElse(mode, (String) null);
	}

	/**
	 * Get an {@link EMode} instance by its API-identifier
	 * or return a default value if failed.
	 *
	 * @param id			API-identifier of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link EMode} instance or the default value
	 */
	public static EMode fromIdOrElse(Integer id, EMode defaultValue) {
		return UwMap.getOrElse(id, MAP_BY_ID, defaultValue);
	}

	/**
	 * Get an {@link EMode} instance by its API-identifier
	 * or return a default value if failed.
	 *
	 * @param id					API-identifier of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link EMode} instance or the default value
	 */
	public static EMode fromIdOrElse(Integer id, Supplier<EMode> defaultValueSupplier) {
		return UwObject.ifNull(fromIdOrNull(id), defaultValueSupplier);
	}

	/**
	 * Get an {@link EMode} instance by its API-identifier
	 * or return {@code null} if failed.
	 *
	 * <p>Wraps {@link #fromIdOrElse(Integer, EMode)}
	 * w/ {@code null} as the default value.
	 *
	 * @param id	API-identifier of the instance
	 * @return		associated {@link EMode} instance or {@code null}
	 */
	public static EMode fromIdOrNull(Integer id) {
		return fromIdOrElse(id, (EMode) null);
	}

	/**
	 * Get an {@link EMode} instance by its API-name
	 * or return a default value if failed.
	 *
	 * @param apiName		API-name of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link EMode} instance or the default value
	 */
	public static EMode fromApiNameOrElse(String apiName, EMode defaultValue) {
		return UwMap.getOrElse(apiName, MAP_BY_API_NAME, defaultValue);
	}

	/**
	 * Get an {@link EMode} instance by its API-name
	 * or return a default value if failed.
	 *
	 * @param apiName				API-name of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link EMode} instance or the default value
	 */
	public static EMode fromApiNameOrElse(String apiName, Supplier<EMode> defaultValueSupplier) {
		return UwObject.ifNull(fromApiNameOrNull(apiName), defaultValueSupplier);
	}

	/**
	 * Get an {@link EMode} instance by its API-name
	 * or return {@code null} if failed.
	 *
	 * <p>Wraps {@link #fromApiNameOrElse(String, EMode)}
	 * w/ {@code null} as the default value.
	 *
	 * @param apiName	API-name of the instance
	 * @return			associated {@link EMode} instance or {@code null}
	 */
	public static EMode fromApiNameOrNull(String apiName) {
		return fromApiNameOrElse(apiName, (EMode) null);
	}

	/**
	 * Get an {@link EMode} instance by its full name
	 * or return a default value if failed.
	 *
	 * @param fullName		full name of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link EMode} instance or the default value
	 */
	public static EMode fromFullNameOrElse(String fullName, EMode defaultValue) {
		return UwMap.getOrElse(fullName, MAP_BY_FULL_NAME, defaultValue);
	}

	/**
	 * Get an {@link EMode} instance by its full name
	 * or return a default value if failed.
	 *
	 * @param fullName				full name of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link EMode} instance or the default value
	 */
	public static EMode fromFullNameOrElse(String fullName, Supplier<EMode> defaultValueSupplier) {
		return UwObject.ifNull(fromFullNameOrNull(fullName), defaultValueSupplier);
	}

	/**
	 * Get an {@link EMode} instance by its full name
	 * or return {@code null} if failed.
	 *
	 * <p>Wraps {@link #fromFullNameOrElse(String, EMode)}
	 * w/ {@code null} as the default value.
	 *
	 * @param fullName	full name of the instance
	 * @return			associated {@link EMode} instance or {@code null}
	 */
	public static EMode fromFullNameOrNull(String fullName) {
		return fromFullNameOrElse(fullName, (EMode) null);
	}

	/**
	 * Get an {@link EMode} instance by its short name
	 * or return a default value if failed.
	 *
	 * @param shortName		short name of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link EMode} instance or the default value.
	 */
	public static EMode fromShortNameOrElse(String shortName, EMode defaultValue) {
		return UwMap.getOrElse(shortName, MAP_BY_SHORT_NAME, defaultValue);
	}

	/**
	 * Get an {@link EMode} instance by its short name
	 * or return a default value if failed.
	 *
	 * @param shortName				short name of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link EMode} instance or the default value.
	 */
	public static EMode fromShortNameOrElse(String shortName, Supplier<EMode> defaultValueSupplier) {
		return UwObject.ifNull(fromShortNameOrNull(shortName), defaultValueSupplier);
	}

	/**
	 * Get an {@link EMode} instance by its short name
	 * or return {@code null} if failed.
	 *
	 * <p>Wraps {@link #fromShortNameOrElse(String, EMode)}
	 * w/ {@code null} as the default value.
	 *
	 * @param shortName		short name of the instance
	 * @return				associated {@link EMode} instance or {@code null}.
	 */
	public static EMode fromShortNameOrNull(String shortName) {
		return fromShortNameOrElse(shortName, (EMode) null);
	}

	/**
	 * Get an {@link EMode} instance by its index
	 * or return a default value if failed.
	 *
	 * @param index			index of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link EMode} instance or the default value
	 */
	public static EMode fromIndexOrElse(Integer index, EMode defaultValue) {
		return UwArray.getOrElse(index, VALUES, defaultValue);
	}

	/**
	 * Get an {@link EMode} instance by its index
	 * or return a default value if failed.
	 *
	 * @param index					index of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link EMode} instance or the default value
	 */
	public static EMode fromIndexOrElse(Integer index, Supplier<EMode> defaultValueSupplier) {
		return UwObject.ifNull(fromIndexOrNull(index), defaultValueSupplier);
	}

	/**
	 * Get an {@link EMode} instance by its index
	 * or return a default value if failed.
	 *
	 * <p>Wraps {@link #fromIndexOrElse(Integer, EMode)}
	 * w/ {@code null} as the default value.
	 *
	 * @param index		index of the instance
	 * @return			associated {@link EMode} instance or the default value
	 */
	public static EMode fromIndexOrNull(Integer index) {
		return fromIndexOrElse(index, (EMode) null);
	}
}
