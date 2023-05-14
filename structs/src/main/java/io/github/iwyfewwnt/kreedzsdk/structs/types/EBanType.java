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
 * An enumeration of kreedz API ban types.
 */
@SuppressWarnings("unused")
public enum EBanType {

	/**
	 * A kreedz API ban type - Bhop Macro.
	 */
	BHOP_MACRO(Group.MACRO, "bhop_macro", "Bhop Macro", "Macro"),

	/**
	 * A kreedz API ban type - Bhop Hack.
	 */
	BHOP_HACK(Group.HACK, "bhop_hack", "Bhop Hack", "Hack"),

	/**
	 * A kreedz API ban type - Strafe Macro.
	 */
	STRAFE_MACRO(Group.MACRO, "strafe_macro", "Strafe Macro", "Macro"),

	/**
	 * A kreedz API ban type - Strafe Hack.
	 */
	STRAFE_HACK(Group.HACK, "strafe_hack", "Strafe Hack", "Hack"),

	/**
	 * A kreedz API ban type - Ban Evasion.
	 */
	BAN_EVASION(Group.OTHER, "ban_evasion", "Ban Evasion", "Evasion"),

	/**
	 * A kreedz API ban type - Exploiting.
	 */
	EXPLOITING(Group.OTHER, "exploiting", "Exploiting", "Exploit");

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = EBanType.class.getSimpleName();

	/**
	 * An array of {@link EBanType} instances.
	 */
	private static final EBanType[] VALUES = UwEnum.values(EBanType.class);

	/**
	 * A map of {@link EBanType} instances by their API-name field.
	 */
	private static final Map<String, EBanType> MAP_BY_API_NAME = UwMap.newMapByFieldOrNull(
			entry -> entry.apiName, EBanType.class
	);

	/**
	 * A map of {@link EBanType} instances by their full name field.
	 */
	private static final Map<String, EBanType> MAP_BY_FULL_NAME = UwMap.newMapByFieldOrNull(
			entry -> entry.fullName, EBanType.class
	);

	/**
	 * A group.
	 */
	private final Group group;

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
	 * A {@link #toString()} cache.
	 */
	private String stringCache;

	/**
	 * A {@link #stringCache} mutex.
	 */
	private final Object stringCacheMutex;

	/**
	 * Initialize an {@link EBanType} instance.
	 *
	 * @param group			group
	 * @param apiName		API-name
	 * @param fullName		full name
	 * @param shortName		short name
	 */
	EBanType(Group group, String apiName, String fullName, String shortName) {
		if (group == null) {
			throw new IllegalArgumentException("Group mustn't be <null>");
		}

		if (apiName == null) {
			throw new IllegalArgumentException("API-name mustn't be <null>");
		}

		if (fullName == null) {
			throw new IllegalArgumentException("Full name mustn't be <null>");
		}

		if (shortName == null) {
			throw new IllegalArgumentException("Short name mustn't be <null>");
		}

		this.group = group;
		this.apiName = apiName;
		this.fullName = fullName;
		this.shortName = shortName;

		this.stringCacheMutex = new Object();
	}

	/**
	 * Get this group.
	 *
	 * @return	group
	 */
	public Group getGroup() {
		return this.group;
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

		synchronized (this.stringCacheMutex) {
			if (this.stringCache != null) {
				return this.stringCache;
			}

			return (this.stringCache = SIMPLE_NAME
					+ "::" + this.name() + "["
					+ "group=" + this.group
					+ ", apiName=\"" + this.apiName + "\""
					+ ", fullName=\"" + this.fullName + "\""
					+ ", shortName=\"" + this.shortName + "\""
					+ "]");
		}
	}

	/**
	 * Get a group from the provided {@link EBanType} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EBanType} instance is {@code null}.
	 * </ul>
	 *
	 * @param banType		ban type instance from which get the group
	 * @param defaultValue	default value to return on failure
	 * @return				group value of the ban type or the default one
	 */
	public static Group getGroupOrElse(EBanType banType, Group defaultValue) {
		return UwObject.ifNotNull(banType, EBanType::getGroup, defaultValue);
	}

	/**
	 * Get a group from the provided {@link EBanType} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EBanType} instance is {@code null}.
	 * </ul>
	 *
	 * @param banType				ban type instance from which get the group
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						group value of the ban type or the default one
	 */
	public static Group getGroupOrElse(EBanType banType, Supplier<Group> defaultValueSupplier) {
		return UwObject.ifNull(getGroupOrNull(banType), defaultValueSupplier);
	}

	/**
	 * Get a group from the provided {@link EBanType} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EBanType} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getGroupOrElse(EBanType, Group)}
	 * w/ {@code null} as the default value.
	 *
	 * @param banType	ban type instance from which get the group
	 * @return			group value of the ban type or {@code null}
	 */
	public static Group getGroupOrNull(EBanType banType) {
		return getGroupOrElse(banType, (Group) null);
	}

	/**
	 * Get an API-name from the provided {@link EBanType} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EBanType} instance is {@code null}.
	 * </ul>
	 *
	 * @param banType		ban type instance from which get the API-name
	 * @param defaultValue	default value to return on failure
	 * @return				API-name value of the ban type or the default one
	 */
	public static String getApiNameOrElse(EBanType banType, String defaultValue) {
		return UwObject.ifNotNull(banType, EBanType::getApiName, defaultValue);
	}

	/**
	 * Get an API-name from the provided {@link EBanType} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EBanType} instance is {@code null}.
	 * </ul>
	 *
	 * @param banType				ban type instance from which get the API-name
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						API-name value of the ban type or the default one
	 */
	public static String getApiNameOrElse(EBanType banType, Supplier<String> defaultValueSupplier) {
		return UwObject.ifNull(getApiNameOrNull(banType), defaultValueSupplier);
	}

	/**
	 * Get an API-name from the provided {@link EBanType} instance
	 * or return an empty string if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EBanType} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getApiNameOrElse(EBanType, String)}
	 * w/ {@link UwString#EMPTY} as the default value.
	 *
	 * @param banType	ban type instance from which get the API-name
	 * @return			API-name value of the ban type or the empty string
	 */
	public static String getApiNameOrEmpty(EBanType banType) {
		return getApiNameOrElse(banType, UwString.EMPTY);
	}

	/**
	 * Get an API-name from the provided {@link EBanType} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EBanType} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getApiNameOrElse(EBanType, String)}
	 * w/ {@code null} as the default value.
	 *
	 * @param banType	ban type instance from which get the API-name
	 * @return			API-name value of the ban type or {@code null}
	 */
	public static String getApiNameOrNull(EBanType banType) {
		return getApiNameOrElse(banType, (String) null);
	}

	/**
	 * Get a full name from the provided {@link EBanType} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EBanType} instance is {@code null}.
	 * </ul>
	 *
	 * @param banType		ban type instance from which get the full name
	 * @param defaultValue	default value to return on failure
	 * @return				full name value of the ban type or the default one
	 */
	public static String getFullNameOrElse(EBanType banType, String defaultValue) {
		return UwObject.ifNotNull(banType, EBanType::getFullName, defaultValue);
	}

	/**
	 * Get a full name from the provided {@link EBanType} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EBanType} instance is {@code null}.
	 * </ul>
	 *
	 * @param banType				ban type instance from which get the full name
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						full name value of the ban type or the default one
	 */
	public static String getFullNameOrElse(EBanType banType, Supplier<String> defaultValueSupplier) {
		return UwObject.ifNull(getFullNameOrNull(banType), defaultValueSupplier);
	}

	/**
	 * Get a full name from the provided {@link EBanType} instance
	 * or return an empty string if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EBanType} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getFullNameOrElse(EBanType, String)}
	 * w/ {@link UwString#EMPTY} as the default value.
	 *
	 * @param banType	ban type instance from which get the full name
	 * @return			full name value of the ban type or the empty string
	 */
	public static String getFullNameOrEmpty(EBanType banType) {
		return getFullNameOrElse(banType, UwString.EMPTY);
	}

	/**
	 * Get a full name from the provided {@link EBanType} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EBanType} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getFullNameOrElse(EBanType, String)}
	 * w/ {@code null} as the default value.
	 *
	 * @param banType	ban type instance from which get the full name
	 * @return			full name value of the ban type or {@code null}
	 */
	public static String getFullNameOrNull(EBanType banType) {
		return getFullNameOrElse(banType, (String) null);
	}

	/**
	 * Get a short name from the provided {@link EBanType} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EBanType} instance is {@code null}.
	 * </ul>
	 *
	 * @param banType		ban type instance from which get the short name
	 * @param defaultValue	default value to return on failure
	 * @return				short name value of the ban type or the default one
	 */
	public static String getShortNameOrElse(EBanType banType, String defaultValue) {
		return UwObject.ifNotNull(banType, EBanType::getShortName, defaultValue);
	}

	/**
	 * Get a short name from the provided {@link EBanType} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EBanType} instance is {@code null}.
	 * </ul>
	 *
	 * @param banType				ban type instance from which get the short name
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						short name value of the ban type or the default one
	 */
	public static String getShortNameOrElse(EBanType banType, Supplier<String> defaultValueSupplier) {
		return UwObject.ifNull(getShortNameOrNull(banType), defaultValueSupplier);
	}

	/**
	 * Get a short name from the provided {@link EBanType} instance
	 * or return an empty string if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EBanType} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getShortNameOrElse(EBanType, String)}
	 * w/ {@link UwString#EMPTY} as the default value.
	 *
	 * @param banType	ban type instance from which get the short name
	 * @return			short name value of the ban type or the empty string
	 */
	public static String getShortNameOrEmpty(EBanType banType) {
		return getShortNameOrElse(banType, UwString.EMPTY);
	}

	/**
	 * Get a short name from the provided {@link EBanType} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EBanType} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getShortNameOrElse(EBanType, String)}
	 * w/ {@code null} as the default value.
	 *
	 * @param banType	ban type instance from which get the short name
	 * @return			short name value of the ban type or {@code null}
	 */
	public static String getShortNameOrNull(EBanType banType) {
		return getShortNameOrElse(banType, (String) null);
	}

	/**
	 * Get an {@link EBanType} instance by its API-name
	 * or return a default value if failed.
	 *
	 * @param apiName		API-name of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link EBanType} instance or the default value
	 */
	public static EBanType fromApiNameOrElse(String apiName, EBanType defaultValue) {
		return UwMap.getOrElse(apiName, MAP_BY_API_NAME, defaultValue);
	}

	/**
	 * Get an {@link EBanType} instance by its API-name
	 * or return a default value if failed.
	 *
	 * @param apiName				API-name of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link EBanType} instance or the default value
	 */
	public static EBanType fromApiNameOrElse(String apiName, Supplier<EBanType> defaultValueSupplier) {
		return UwObject.ifNull(fromApiNameOrNull(apiName), defaultValueSupplier);
	}

	/**
	 * Get an {@link EBanType} instance by its API-name
	 * or return {@code null} if failed.
	 *
	 * <p>Wraps {@link #fromApiNameOrElse(String, EBanType)}
	 * w/ {@code null} as the default value.
	 *
	 * @param apiName	API-name of the instance
	 * @return			associated {@link EBanType} instance or {@code null}
	 */
	public static EBanType fromApiNameOrNull(String apiName) {
		return fromApiNameOrElse(apiName, (EBanType) null);
	}

	/**
	 * Get an {@link EBanType} instance by its full name
	 * or return a default value if failed.
	 *
	 * @param fullName		full name of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link EBanType} instance or the default value
	 */
	public static EBanType fromFullNameOrElse(String fullName, EBanType defaultValue) {
		return UwMap.getOrElse(fullName, MAP_BY_FULL_NAME, defaultValue);
	}

	/**
	 * Get an {@link EBanType} instance by its full name
	 * or return a default value if failed.
	 *
	 * @param fullName				full name of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link EBanType} instance or the default value
	 */
	public static EBanType fromFullNameOrElse(String fullName, Supplier<EBanType> defaultValueSupplier) {
		return UwObject.ifNull(fromFullNameOrNull(fullName), defaultValueSupplier);
	}

	/**
	 * Get an {@link EBanType} instance by its full name
	 * or return a default value if failed.
	 *
	 * <p>Wraps {@link #fromFullNameOrElse(String, EBanType)}
	 * w/ {@code null} as the default value.
	 *
	 * @param fullName	full name of the instance
	 * @return			associated {@link EBanType} instance or {@code null}
	 */
	public static EBanType fromFullNameOrNull(String fullName) {
		return fromFullNameOrElse(fullName, (EBanType) null);
	}

	/**
	 * Get an {@link EBanType} instance by its index
	 * or return a default value if failed.
	 *
	 * @param index			index of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link EBanType} instance or the default value
	 */
	public static EBanType fromIndexOrElse(Integer index, EBanType defaultValue) {
		return UwArray.getOrElse(index, VALUES, defaultValue);
	}

	/**
	 * Get an {@link EBanType} instance by its index
	 * or return a default value if failed.
	 *
	 * @param index					index of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link EBanType} instance or the default value
	 */
	public static EBanType fromIndexOrElse(Integer index, Supplier<EBanType> defaultValueSupplier) {
		return UwObject.ifNull(fromIndexOrNull(index), defaultValueSupplier);
	}

	/**
	 * Get an {@link EBanType} instance by its index
	 * or return {@code null} if failed.
	 *
	 * <p>Wraps {@link EBanType#fromIndexOrElse(Integer, EBanType)}
	 * w/ {@code null} as the default value.
	 *
	 * @param index		index of the instance
	 * @return			associated {@link EBanType} instance or {@code null}
	 */
	public static EBanType fromIndexOrNull(Integer index) {
		return fromIndexOrElse(index, (EBanType) null);
	}

	/**
	 * An enumeration of kreedz API ban type groups.
	 */
	public enum Group {

		/**
		 * A kreedz API ban type group - Macro.
		 *
		 * <p>Includes {@link EBanType#BHOP_MACRO}
		 * and {@link EBanType#STRAFE_MACRO}.
		 */
		MACRO,

		/**
		 * A kreedz API ban type group - Hack.
		 *
		 * <p>Includes {@link EBanType#BHOP_HACK}
		 * and {@link EBanType#STRAFE_HACK}.
		 */
		HACK,

		/**
		 * A kreedz API ban type group - Other.
		 *
		 * <p>Includes {@link EBanType#BAN_EVASION}
		 * and {@link EBanType#EXPLOITING}.
		 */
		OTHER;

		/**
		 * A simple name of this class.
		 */
		private static final String SIMPLE_NAME = EBanType.class.getSimpleName()
				+ ":" + Group.class.getSimpleName();

		/**
		 * A {@link #toString()} cache.
		 */
		private String stringCache;

		/**
		 * A {@link #stringCache} mutex.
		 */
		private final Object stringCacheMutex;

		Group() {
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
}
