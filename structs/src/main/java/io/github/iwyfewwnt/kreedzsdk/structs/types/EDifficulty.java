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
 * An enumeration of kreedz API difficulties.
 */
@SuppressWarnings("unused")
public enum EDifficulty {

	/**
	 * A kreedz API difficulty - [1] Very Easy.
	 */
	VERY_EASY(Group.GREEN, 1, "Very Easy"),

	/**
	 * A kreedz API difficulty - [2] Easy.
	 */
	EASY(Group.GREEN, 2, "Easy"),

	/**
	 * A kreedz API difficulty - [3] Medium.
	 */
	MEDIUM(Group.YELLOW, 3, "Medium"),

	/**
	 * A kreedz API difficulty - [4] Hard.
	 */
	HARD(Group.YELLOW, 4, "Hard"),

	/**
	 * A kreedz API difficulty - [5] Very Hard.
	 */
	VERY_HARD(Group.RED, 5, "Very Hard"),

	/**
	 * A kreedz API difficulty - [6] Extreme.
	 */
	EXTREME(Group.RED, 6, "Extreme"),

	/**
	 * A kreedz API difficulty - [7] Death.
	 */
	DEATH(Group.PURPLE, 7, "Death");

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = EDifficulty.class.getSimpleName();

	/**
	 * An array of {@link EDifficulty} instances.
	 */
	private static final EDifficulty[] VALUES = UwEnum.values(EDifficulty.class);

	/**
	 * A map of {@link EDifficulty} instances by their API-identifier field.
	 */
	private static final Map<Integer, EDifficulty> MAP_BY_ID = UwMap.newMapByFieldOrNull(
			entry -> entry.id, EDifficulty.class
	);

	/**
	 * A map of {@link EDifficulty} instances by their full name field.
	 */
	private static final Map<String, EDifficulty> MAP_BY_FULL_NAME = UwMap.newMapByFieldOrNull(
			entry -> entry.fullName, EDifficulty.class
	);

	/**
	 * A group.
	 */
	private final Group group;

	/**
	 * An API-identifier.
	 */
	private final int id;

	/**
	 * A full name.
	 */
	private final String fullName;

	/**
	 * A {@link EDifficulty#toString()} cache.
	 */
	private String stringCache;

	/**
	 * A {@link #stringCache} mutex.
	 */
	private final Object stringCacheMutex;

	/**
	 * Initialize an {@link EDifficulty} instance.
	 *
	 * @param group		group
	 * @param id		API-identifier
	 * @param fullName	full name
	 */
	EDifficulty(Group group, int id, String fullName) {
		if (group == null) {
			throw new IllegalArgumentException("Group mustn't be <null>");
		}

		if (fullName == null) {
			throw new IllegalArgumentException("Full name mustn't be <null>");
		}

		this.group = group;
		this.id = id;
		this.fullName = fullName;

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
	 * Get this API-identifier.
	 *
	 * @return	API-identifier
	 */
	public int getId() {
		return this.id;
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
					+ "group=" + this.group
					+ ", id=" + this.id
					+ ", fullName=\"" + this.fullName + "\""
					+ "]");
		}
	}

	/**
	 * Get a group of the provided {@link EDifficulty} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EDifficulty} instance is {@code null}.
	 * </ul>
	 *
	 * @param difficulty	difficulty instance from which get the group
	 * @param defaultValue	default value to return on failure
	 * @return				group value of the difficulty or the default one
	 */
	public static Group getGroupOrElse(EDifficulty difficulty, Group defaultValue) {
		return UwObject.ifNotNull(difficulty, EDifficulty::getGroup, defaultValue);
	}

	/**
	 * Get a group of the provided {@link EDifficulty} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EDifficulty} instance is {@code null}.
	 * </ul>
	 *
	 * @param difficulty			difficulty instance from which get the group
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						group value of the difficulty or the default one
	 */
	public static Group getGroupOrElse(EDifficulty difficulty, Supplier<Group> defaultValueSupplier) {
		return UwObject.ifNull(getGroupOrNull(difficulty), defaultValueSupplier);
	}

	/**
	 * Get a group of the provided {@link EDifficulty} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EDifficulty} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link EDifficulty#getGroupOrElse(EDifficulty, Group)}
	 * w/ {@code null} as the default value.
	 *
	 * @param difficulty	difficulty instance from which get the group
	 * @return				group value of the difficulty or {@code null}
	 */
	public static Group getGroupOrNull(EDifficulty difficulty) {
		return getGroupOrElse(difficulty, (Group) null);
	}

	/**
	 * Get an API-identifier of the provided {@link EDifficulty} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EDifficulty} instance is {@code null}.
	 * </ul>
	 *
	 * @param difficulty	difficulty instance from which get the API-identifier
	 * @param defaultValue	default value to return on failure
	 * @return				API-identifier value of the difficulty or the default one
	 */
	public static Integer getIdOrElse(EDifficulty difficulty, Integer defaultValue) {
		return UwObject.ifNotNull(difficulty, EDifficulty::getId, defaultValue);
	}

	/**
	 * Get an API-identifier of the provided {@link EDifficulty} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EDifficulty} instance is {@code null}.
	 * </ul>
	 *
	 * @param difficulty			difficulty instance from which get the API-identifier
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						API-identifier value of the difficulty or the default one
	 */
	public static Integer getIdOrElse(EDifficulty difficulty, Supplier<Integer> defaultValueSupplier) {
		return UwObject.ifNull(getIdOrNull(difficulty), defaultValueSupplier);
	}

	/**
	 * Get an API-identifier of the provided {@link EDifficulty} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EDifficulty} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link EDifficulty#getIdOrElse(EDifficulty, Integer)}
	 * w/ {@code null} as the default value.
	 *
	 * @param difficulty	difficulty instance from which get the API-identifier
	 * @return				API-identifier value of the difficulty or {@code null}
	 */
	public static Integer getIdOrNull(EDifficulty difficulty) {
		return getIdOrElse(difficulty, (Integer) null);
	}

	/**
	 * Get a full name of the provided {@link EDifficulty} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EDifficulty} instance is {@code null}.
	 * </ul>
	 *
	 * @param difficulty	difficulty instance from which get the full name
	 * @param defaultValue	default value to return on failure
	 * @return				full name value of the difficulty or the default one
	 */
	public static String getFullNameOrElse(EDifficulty difficulty, String defaultValue) {
		return UwObject.ifNotNull(difficulty, EDifficulty::getFullName, defaultValue);
	}

	/**
	 * Get a full name of the provided {@link EDifficulty} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EDifficulty} instance is {@code null}.
	 * </ul>
	 *
	 * @param difficulty			difficulty instance from which get the full name
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						full name value of the difficulty or the default one
	 */
	public static String getFullNameOrElse(EDifficulty difficulty, Supplier<String> defaultValueSupplier) {
		return UwObject.ifNull(getFullNameOrNull(difficulty), defaultValueSupplier);
	}

	/**
	 * Get a full name of the provided {@link EDifficulty} instance
	 * or return an empty string if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EDifficulty} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link EDifficulty#getFullNameOrElse(EDifficulty, String)}
	 * w/ {@link UwString#EMPTY} as the default value.
	 *
	 * @param difficulty	difficulty instance from which get the full name
	 * @return				full name value of the difficulty or the empty string
	 */
	public static String getFullNameOrEmpty(EDifficulty difficulty) {
		return getFullNameOrElse(difficulty, UwString.EMPTY);
	}

	/**
	 * Get a full name of the provided {@link EDifficulty} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EDifficulty} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link EDifficulty#getFullNameOrElse(EDifficulty, String)}
	 * w/ {@code null} as the default value.
	 *
	 * @param difficulty	difficulty instance from which get the full name
	 * @return				full name value of the difficulty or {@code null}
	 */
	public static String getFullNameOrNull(EDifficulty difficulty) {
		return getFullNameOrElse(difficulty, (String) null);
	}

	/**
	 * Get an {@link EDifficulty} instance by its API-identifier
	 * or return a default value if failed.
	 *
	 * @param id			API-identifier of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link EDifficulty} instance or the default value
	 */
	public static EDifficulty fromIdOrElse(Integer id, EDifficulty defaultValue) {
		return UwMap.getOrElse(id, MAP_BY_ID, defaultValue);
	}

	/**
	 * Get an {@link EDifficulty} instance by its API-identifier
	 * or return a default value if failed.
	 *
	 * @param id					API-identifier of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link EDifficulty} instance or the default value
	 */
	public static EDifficulty fromIdOrElse(Integer id, Supplier<EDifficulty> defaultValueSupplier) {
		return UwObject.ifNull(fromIdOrNull(id), defaultValueSupplier);
	}

	/**
	 * Get an {@link EDifficulty} instance by its API-identifier
	 * or return {@code null} if failed.
	 *
	 * <p>Wraps {@link EJumpType#fromIdOrElse(Integer, EJumpType)}
	 * w/ {@code null} as the default value.
	 *
	 * @param id	API-identifier of the instance
	 * @return		associated {@link EDifficulty} instance or {@code null}
	 */
	public static EDifficulty fromIdOrNull(Integer id) {
		return fromIdOrElse(id, (EDifficulty) null);
	}

	/**
	 * Get an {@link EDifficulty} instance by its full name
	 * or return a default value if failed.
	 *
	 * @param fullName		full name of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link EDifficulty} instance or the default value
	 */
	public static EDifficulty fromFullNameOrElse(String fullName, EDifficulty defaultValue) {
		return UwMap.getOrElse(fullName, MAP_BY_FULL_NAME, defaultValue);
	}

	/**
	 * Get an {@link EDifficulty} instance by its full name
	 * or return a default value if failed.
	 *
	 * @param fullName				full name of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link EDifficulty} instance or the default value
	 */
	public static EDifficulty fromFullNameOrElse(String fullName, Supplier<EDifficulty> defaultValueSupplier) {
		return UwObject.ifNull(fromFullNameOrNull(fullName), defaultValueSupplier);
	}

	/**
	 * Get an {@link EDifficulty} instance by its full name
	 * or return {@code null} if failed.
	 *
	 * <p>Wraps {@link EDifficulty#fromFullNameOrElse(String, EDifficulty)}
	 * w/ {@code null} as the default value.
	 *
	 * @param fullName	full name of the instance
	 * @return			associated {@link EDifficulty} instance or {@code null}
	 */
	public static EDifficulty fromFullNameOrNull(String fullName) {
		return fromFullNameOrElse(fullName, (EDifficulty) null);
	}

	/**
	 * Get an {@link EDifficulty} instance by its index
	 * or return a default value if failed.
	 *
	 * @param index			index of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link EDifficulty} instance or the default value
	 */
	public static EDifficulty fromIndexOrElse(Integer index, EDifficulty defaultValue) {
		return UwArray.getOrElse(index, VALUES, defaultValue);
	}

	/**
	 * Get an {@link EDifficulty} instance by its index
	 * or return a default value if failed.
	 *
	 * @param index					index of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link EDifficulty} instance or the default value
	 */
	public static EDifficulty fromIndexOrElse(Integer index, Supplier<EDifficulty> defaultValueSupplier) {
		return UwObject.ifNull(fromIndexOrNull(index), defaultValueSupplier);
	}

	/**
	 * Get an {@link EDifficulty} instance by its index
	 * or return {@code null} if failed.
	 *
	 * <p>Wraps {@link EDifficulty#fromIndexOrElse(Integer, EDifficulty)}
	 * w/ {@code null} as the default value.
	 *
	 * @param index		index of the instance
	 * @return			associated {@link EDifficulty} instance or {@code null}
	 */
	public static EDifficulty fromIndexOrNull(Integer index) {
		return fromIndexOrElse(index, (EDifficulty) null);
	}

	/**
	 * An enumeration of kreedz API difficulty groups.
	 */
	public enum Group {

		/**
		 * A kreedz API difficulty group - Green.
		 *
		 * <p>Includes {@link EDifficulty#VERY_EASY}
		 * and {@link EDifficulty#EASY}.
		 */
		GREEN,

		/**
		 * A kreedz API difficulty group - Yellow.
		 *
		 * <p>Includes {@link EDifficulty#MEDIUM}
		 * and {@link EDifficulty#HARD}.
		 */
		YELLOW,

		/**
		 * A kreedz API difficulty group - Red.
		 *
		 * <p>Includes {@link EDifficulty#VERY_HARD}
		 * and {@link EDifficulty#EXTREME}.
		 */
		RED,

		/**
		 * A kreedz API difficulty group - Purple.
		 *
		 * <p>Includes {@link EDifficulty#DEATH}.
		 */
		PURPLE;

		/**
		 * A simple name of this class.
		 */
		private static final String SIMPLE_NAME = EDifficulty.class.getSimpleName()
				+ ":" + Group.class.getSimpleName();

		/**
		 * A {@link Group#toString()} cache.
		 */
		private String stringCache;

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String toString() {
			if (this.stringCache != null) {
				return this.stringCache;
			}

			return (this.stringCache = SIMPLE_NAME
					+ "::" + this.name());
		}
	}
}
