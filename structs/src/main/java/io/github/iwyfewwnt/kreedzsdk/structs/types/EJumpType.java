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

import java.io.Serializable;
import java.util.Map;
import java.util.function.Supplier;

/**
 * An enumeration of kreedz API jump types.
 */
@SuppressWarnings("unused")
public enum EJumpType implements Serializable {

	/**
	 * A kreedz API jump type - [1] LongJump.
	 */
	LJ(1, "longjump", "LongJump", "LJ"),

	/**
	 * A kreedz API jump type - [2] BunnyHop.
	 */
	BH(2, "bhop", "BunnyHop", "BH"),

	/**
	 * A kreedz API jump type - [3] Multi-BunnyHop.
	 */
	MBH(3, "multibhop", "Multi-BunnyHop", "MBH"),

	/**
	 * A kreedz API jump type - [4] WeirdJump.
	 */
	WJ(4, "weirdjump", "WeirdJump", "WJ"),

	/**
	 * A kreedz API jump type - [5] Drop-BunnyHop.
	 */
	DBH(5, "dropbhop", "Drop-BunnyHop", "DBH"),

	/**
	 * A kreedz API jump type - [6] CountJump.
	 */
	CJ(6, "countjump", "CountJump", "CJ"),

	/**
	 * A kreedz API jump type - [7] LadderJump.
	 */
	LAJ(7, "ladderjump", "LadderJump", "LAJ");

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = EJumpType.class.getSimpleName();

	/**
	 * An array of {@link EJumpType} instances.
	 */
	private static final EJumpType[] VALUES = UwEnum.values(EJumpType.class);

	/**
	 * A map of {@link EJumpType} instances by their API-identifier field.
	 */
	private static final Map<Integer, EJumpType> MAP_BY_ID = UwMap.newMapByFieldOrNull(
			entry -> entry.id, EJumpType.class
	);

	/**
	 * A map of {@link EJumpType} instances by their API-name field.
	 */
	private static final Map<String, EJumpType> MAP_BY_API_NAME = UwMap.newMapByFieldOrNull(
			entry -> entry.apiName, EJumpType.class
	);

	/**
	 * A map of {@link EJumpType} instances by their full name field.
	 */
	private static final Map<String, EJumpType> MAP_BY_FULL_NAME = UwMap.newMapByFieldOrNull(
			entry -> entry.fullName, EJumpType.class
	);

	/**
	 * A map of {@link EJumpType} instances by their short name field.
	 */
	private static final Map<String, EJumpType> MAP_BY_SHORT_NAME = UwMap.newMapByFieldOrNull(
			entry -> entry.shortName, EJumpType.class
	);

	/**
	 * An API-identifier.
	 */
	private final int id;

	/**
	 * An API-name.
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
	 * A {@link EJumpType#toString()} cache.
	 */
	private transient String stringCache;

	/**
	 * Initialize an {@link EJumpType} instance.
	 *
	 * @param id			API-identifier
	 * @param apiName		API-name
	 * @param fullName		full name
	 * @param shortName		short name
	 */
	EJumpType(int id, String apiName, String fullName, String shortName) {
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

	/**
	 * Get an API-identifier of the provided {@link EJumpType} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EJumpType} instance is {@code null}.
	 * </ul>
	 *
	 * @param jumpType		jump type instance from which get the API-identifier
	 * @param defaultValue	default value to return on failure
	 * @return				API-identifier value of the jump type or the default one
	 */
	public static Integer getIdOrElse(EJumpType jumpType, Integer defaultValue) {
		if (jumpType == null) {
			return defaultValue;
		}

		return jumpType.getId();
	}

	/**
	 * Get an API-identifier of the provided {@link EJumpType} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EJumpType} instance is {@code null}.
	 * </ul>
	 *
	 * @param jumpType				jump type instance from which get the API-identifier
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						API-identifier value of the jump type or the default one
	 */
	public static Integer getIdOrElse(EJumpType jumpType, Supplier<Integer> defaultValueSupplier) {
		return UwObject.ifNull(getIdOrNull(jumpType), defaultValueSupplier);
	}

	/**
	 * Get an API-identifier of the provided {@link EJumpType} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EJumpType} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link EJumpType#getIdOrElse(EJumpType, Integer)}
	 * w/ {@code null} as the default value.
	 *
	 * @param jumpType	jump type instance from which get the API-identifier
	 * @return			API-identifier value of the jump type or {@code null}
	 */
	public static Integer getIdOrNull(EJumpType jumpType) {
		return getIdOrElse(jumpType, (Integer) null);
	}

	/**
	 * Get an API-name of the provided {@link EJumpType} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EJumpType} instance is {@code null}.
	 * </ul>
	 *
	 * @param jumpType		jump type instance from which get the API-name
	 * @param defaultValue	default value to return on failure
	 * @return				API-name value of the jump type or the default one
	 */
	public static String getApiNameOrElse(EJumpType jumpType, String defaultValue) {
		if (jumpType == null) {
			return defaultValue;
		}

		return jumpType.getApiName();
	}

	/**
	 * Get an API-name of the provided {@link EJumpType} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EJumpType} instance is {@code null}.
	 * </ul>
	 *
	 * @param jumpType				jump type instance from which get the API-name
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						API-name value of the jump type or the default one
	 */
	public static String getApiNameOrElse(EJumpType jumpType, Supplier<String> defaultValueSupplier) {
		return UwObject.ifNull(getApiNameOrNull(jumpType), defaultValueSupplier);
	}

	/**
	 * Get an API-name of the provided {@link EJumpType} instance
	 * or return an empty string if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EJumpType} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link EJumpType#getApiNameOrElse(EJumpType, String)}
	 * w/ {@link UwString#EMPTY} as the default value.
	 *
	 * @param jumpType	jump type instance from which get the API-name
	 * @return			API-name value of the jump type or the empty string
	 */
	public static String getApiNameOrEmpty(EJumpType jumpType) {
		return getApiNameOrElse(jumpType, UwString.EMPTY);
	}

	/**
	 * Get an API-name of the provided {@link EJumpType} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EJumpType} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link EJumpType#getApiNameOrElse(EJumpType, String)}
	 * w/ {@code null} as the default value.
	 *
	 * @param jumpType	jump type instance from which get the API-name
	 * @return			API-name value of the jump type or {@code null}
	 */
	public static String getApiNameOrNull(EJumpType jumpType) {
		return getApiNameOrElse(jumpType, (String) null);
	}

	/**
	 * Get a full name of the provided {@link EJumpType} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EJumpType} instance is {@code null}.
	 * </ul>
	 *
	 * @param jumpType		jump type instance from which get the full name
	 * @param defaultValue	default value to return on failure
	 * @return				full name value of the jump type or the default one
	 */
	public static String getFullNameOrElse(EJumpType jumpType, String defaultValue) {
		if (jumpType == null) {
			return defaultValue;
		}

		return jumpType.getFullName();
	}

	/**
	 * Get a full name of the provided {@link EJumpType} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EJumpType} instance is {@code null}.
	 * </ul>
	 *
	 * @param jumpType				jump type instance from which get the full name
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						full name value of the jump type or the default one
	 */
	public static String getFullNameOrElse(EJumpType jumpType, Supplier<String> defaultValueSupplier) {
		return UwObject.ifNull(getFullNameOrNull(jumpType), defaultValueSupplier);
	}

	/**
	 * Get a full name of the provided {@link EJumpType} instance
	 * or return an empty string if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EJumpType} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link EJumpType#getFullNameOrElse(EJumpType, String)}
	 * w/ {@link UwString#EMPTY} as the default value.
	 *
	 * @param jumpType	jump type instance from which get the full name
	 * @return			full name value of the jump type or the empty string
	 */
	public static String getFullNameOrEmpty(EJumpType jumpType) {
		return getFullNameOrElse(jumpType, UwString.EMPTY);
	}

	/**
	 * Get a full name of the provided {@link EJumpType} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EJumpType} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link EJumpType#getFullNameOrElse(EJumpType, String)}
	 * w/ {@code null} as the default value.
	 *
	 * @param jumpType	jump type instance from which get the full name
	 * @return			full name value of the jump type or {@code null}
	 */
	public static String getFullNameOrNull(EJumpType jumpType) {
		return getFullNameOrElse(jumpType, (String) null);
	}

	/**
	 * Get a short name of the provided {@link EJumpType} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EJumpType} instance is {@code null}.
	 * </ul>
	 *
	 * @param jumpType		jump type instance from which get the short name
	 * @param defaultValue	default value to return on failure
	 * @return				short name value of the jump type or the default one
	 */
	public static String getShortNameOrElse(EJumpType jumpType, String defaultValue) {
		if (jumpType == null) {
			return defaultValue;
		}

		return jumpType.getShortName();
	}

	/**
	 * Get a short name of the provided {@link EJumpType} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EJumpType} instance is {@code null}.
	 * </ul>
	 *
	 * @param jumpType				jump type instance from which get the short name
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						short name value of the jump type or the default one
	 */
	public static String getShortNameOrElse(EJumpType jumpType, Supplier<String> defaultValueSupplier) {
		return UwObject.ifNull(getShortNameOrNull(jumpType), defaultValueSupplier);
	}

	/**
	 * Get a short name of the provided {@link EJumpType} instance
	 * or return an empty string if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EJumpType} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link EJumpType#getShortNameOrElse(EJumpType, String)}
	 * w/ {@link UwString#EMPTY} as the default value.
	 *
	 * @param jumpType	jump type instance from which get the short name
	 * @return			short name value of the jump type or the empty string
	 */
	public static String getShortNameOrEmpty(EJumpType jumpType) {
		return getShortNameOrElse(jumpType, UwString.EMPTY);
	}

	/**
	 * Get a short name of the provided {@link EJumpType} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EJumpType} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link EJumpType#getShortNameOrElse(EJumpType, String)}
	 * w/ {@code null} as the default value.
	 *
	 * @param jumpType	jump type instance from which get the short name
	 * @return			short name value of the jump type or {@code null}
	 */
	public static String getShortNameOrNull(EJumpType jumpType) {
		return getShortNameOrElse(jumpType, (String) null);
	}

	/**
	 * Get an {@link EJumpType} instance by its API-identifier
	 * or return a default value if failed.
	 *
	 * @param id			API-identifier of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link EJumpType} instance or the default value
	 */
	public static EJumpType fromIdOrElse(Integer id, EJumpType defaultValue) {
		return UwMap.getOrElse(id, MAP_BY_ID, defaultValue);
	}

	/**
	 * Get an {@link EJumpType} instance by its API-identifier
	 * or return a default value if failed.
	 *
	 * @param id					API-identifier of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link EJumpType} instance or the default value
	 */
	public static EJumpType fromIdOrElse(Integer id, Supplier<EJumpType> defaultValueSupplier) {
		return UwObject.ifNull(fromIdOrNull(id), defaultValueSupplier);
	}

	/**
	 * Get an {@link EJumpType} instance by its API-identifier
	 * or return {@code null} if failed.
	 *
	 * <p>Wraps {@link EJumpType#fromIdOrElse(Integer, EJumpType)}
	 * w/ {@code null} as the default value.
	 *
	 * @param id	API-identifier of the instance
	 * @return		associated {@link EJumpType} instance or {@code null}
	 */
	public static EJumpType fromIdOrNull(Integer id) {
		return fromIdOrElse(id, (EJumpType) null);
	}

	/**
	 * Get an {@link EJumpType} instance by its API-name
	 * or return a default value if failed.
	 *
	 * @param apiName		API-name of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link EJumpType} instance or the default value
	 */
	public static EJumpType fromApiNameOrElse(String apiName, EJumpType defaultValue) {
		return UwMap.getOrElse(apiName, MAP_BY_API_NAME, defaultValue);
	}

	/**
	 * Get an {@link EJumpType} instance by its API-name
	 * or return a default value if failed.
	 *
	 * @param apiName				API-name of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link EJumpType} instance or the default value
	 */
	public static EJumpType fromApiNameOrElse(String apiName, Supplier<EJumpType> defaultValueSupplier) {
		return UwObject.ifNull(fromApiNameOrNull(apiName), defaultValueSupplier);
	}

	/**
	 * Get an {@link EJumpType} instance by its API-name
	 * or return {@code null} if failed.
	 *
	 * <p>Wraps {@link EJumpType#fromApiNameOrElse(String, EJumpType)}
	 * w/ {@code null} as the default value.
	 *
	 * @param apiName	API-name of the instance
	 * @return			associated {@link EJumpType} instance or {@code null}
	 */
	public static EJumpType fromApiNameOrNull(String apiName) {
		return fromApiNameOrElse(apiName, (EJumpType) null);
	}

	/**
	 * Get an {@link EJumpType} instance by its full name
	 * or return a default value if failed.
	 *
	 * @param fullName		full name of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link EJumpType} instance or the default value
	 */
	public static EJumpType fromFullNameOrElse(String fullName, EJumpType defaultValue) {
		return UwMap.getOrElse(fullName, MAP_BY_FULL_NAME, defaultValue);
	}

	/**
	 * Get an {@link EJumpType} instance by its full name
	 * or return a default value if failed.
	 *
	 * @param fullName				full name of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link EJumpType} instance or the default value
	 */
	public static EJumpType fromFullNameOrElse(String fullName, Supplier<EJumpType> defaultValueSupplier) {
		return UwObject.ifNull(fromFullNameOrNull(fullName), defaultValueSupplier);
	}

	/**
	 * Get an {@link EJumpType} instance by its full name
	 * or return {@code null} if failed.
	 *
	 * <p>Wraps {@link EJumpType#fromFullNameOrElse(String, EJumpType)}
	 * w/ {@code null} as the default value.
	 *
	 * @param fullName	full name of the instance
	 * @return			associated {@link EJumpType} instance or {@code null}
	 */
	public static EJumpType fromFullNameOrNull(String fullName) {
		return fromFullNameOrElse(fullName, (EJumpType) null);
	}

	/**
	 * Get an {@link EJumpType} instance by its short name
	 * or return a default value if failed.
	 *
	 * @param shortName		short name of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link EJumpType} instance or the default value
	 */
	public static EJumpType fromShortNameOrElse(String shortName, EJumpType defaultValue) {
		return UwMap.getOrElse(shortName, MAP_BY_SHORT_NAME, defaultValue);
	}

	/**
	 * Get an {@link EJumpType} instance by its short name
	 * or return a default value if failed.
	 *
	 * @param shortName				short name of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link EJumpType} instance or the default value
	 */
	public static EJumpType fromShortNameOrElse(String shortName, Supplier<EJumpType> defaultValueSupplier) {
		return UwObject.ifNull(fromShortNameOrNull(shortName), defaultValueSupplier);
	}

	/**
	 * Get an {@link EJumpType} instance by its short name
	 * or return {@code null} if failed.
	 *
	 * <p>Wraps {@link EJumpType#fromShortNameOrElse(String, EJumpType)}
	 * w/ {@code null} as the default value.
	 *
	 * @param shortName		short name of the instance
	 * @return				associated {@link EJumpType} instance or {@code null}
	 */
	public static EJumpType fromShortNameOrNull(String shortName) {
		return fromShortNameOrElse(shortName, (EJumpType) null);
	}

	/**
	 * Get an {@link EJumpType} instance by its index
	 * or return a default value if failed.
	 *
	 * @param index			index of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link EJumpType} instance or the default value
	 */
	public static EJumpType fromIndexOrElse(Integer index, EJumpType defaultValue) {
		return UwArray.getOrElse(index, VALUES, defaultValue);
	}

	/**
	 * Get an {@link EJumpType} instance by its index
	 * or return a default value if failed.
	 *
	 * @param index					index of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link EJumpType} instance or the default value
	 */
	public static EJumpType fromIndexOrElse(Integer index, Supplier<EJumpType> defaultValueSupplier) {
		return UwObject.ifNull(fromIndexOrNull(index), defaultValueSupplier);
	}

	/**
	 * Get an {@link EJumpType} instance by its index
	 * or return {@code null} if failed.
	 *
	 * <p>Wraps {@link EJumpType#fromIndexOrElse(Integer, EJumpType)}
	 * w/ {@code null} as the default value.
	 *
	 * @param index		index of the instance
	 * @return			associated {@link EJumpType} instance or {@code null}
	 */
	public static EJumpType fromIndexOrNull(Integer index) {
		return fromIndexOrElse(index, (EJumpType) null);
	}
}
