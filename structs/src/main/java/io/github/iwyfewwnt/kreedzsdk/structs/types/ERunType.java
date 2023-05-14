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

import io.github.iwyfewwnt.uwutils.UwArray;
import io.github.iwyfewwnt.uwutils.UwEnum;
import io.github.iwyfewwnt.uwutils.UwObject;

import java.util.function.Supplier;

/**
 * An enumeration of kreedz API map run types.
 */
@SuppressWarnings("unused")
public enum ERunType {

	/**
	 * A kreedz API map run type - PRO.
	 *
	 * <p>Does not include teleports.
	 */
	PRO(false),

	/**
	 * A kreedz API map run type - TP.
	 *
	 * <p>Does include teleports.
	 */
	TP(true),

	/**
	 * A kreedz API map run type - NUB.
	 *
	 * <p>Union type of the {@link #PRO}
	 * and {@link #TP}.
	 */
	NUB(null);

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = ERunType.class.getSimpleName();

	/**
	 * An array of {@link ETickrate} instances.
	 */
	private static final ERunType[] VALUES = UwEnum.values(ERunType.class);

	/**
	 * Has teleports boolean value.
	 *
	 * <p>Determines whether the run had a teleports.
	 */
	private final Boolean hasTeleports;

	/**
	 * A {@link #toString()} cache.
	 */
	private String stringCache;

	/**
	 * A {@link #stringCache} mutex.
	 */
	private final Object stringCacheMutex;

	/**
	 * Initialize an {@link ERunType} instance.
	 *
	 * @param hasTeleports	has teleports boolean value
	 */
	ERunType(Boolean hasTeleports) {
		this.hasTeleports = hasTeleports;

		this.stringCacheMutex = new Object();
	}

	/**
	 * Get this has teleports boolean value.
	 *
	 * @return	has teleports boolean value
	 */
	public Boolean getHasTeleports() {
		return this.hasTeleports;
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
					+ "hasTeleports=" + this.hasTeleports
					+ "]");
		}
	}

	/**
	 * Get a has teleports boolean value from the provided {@link ERunType} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link ERunType} instance is {@code null}.
	 * </ul>
	 *
	 * @param runType		run type instance from which get the has teleports boolean value
	 * @param defaultValue	default value to return on failure
	 * @return				has teleports boolean value or the default one
	 */
	public static Boolean getHasTeleportsOrElse(ERunType runType, Boolean defaultValue) {
		return UwObject.ifNotNull(runType, ERunType::getHasTeleports, defaultValue);
	}

	/**
	 * Get a has teleports boolean value from the provided {@link ERunType} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link ERunType} instance is {@code null}.
	 * </ul>
	 *
	 * @param runType				run type instance from which get the has teleports boolean value
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						has teleports boolean value or the default one
	 */
	public static Boolean getHasTeleportsOrElse(ERunType runType, Supplier<Boolean> defaultValueSupplier) {
		return UwObject.ifNull(getHasTeleportsOrNull(runType), defaultValueSupplier);
	}

	/**
	 * Get a has teleports boolean value from the provided {@link ERunType} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link ERunType} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getHasTeleportsOrElse(ERunType, Boolean)}
	 * w/ {@code null} as the default value.
	 *
	 * @param runType	run type instance from which get the has teleports boolean value
	 * @return			has teleports boolean value or {@code null}
	 */
	public static Boolean getHasTeleportsOrNull(ERunType runType) {
		return getHasTeleportsOrElse(runType, (Boolean) null);
	}

	/**
	 * Get an {@link ERunType} instance by its has teleports boolean value.
	 *
	 * @param hasTeleports	has teleports boolean value of the instance
	 * @return				associated {@link ERunType} instance
	 */
	public static ERunType fromHasTeleports(Boolean hasTeleports) {
		if (hasTeleports == null) {
			return NUB;
		}

		return hasTeleports ? TP : PRO;
	}

	/**
	 * Get an {@link ERunType} instance by its index
	 * or return a default value if failed.
	 *
	 * @param index			index of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link ERunType} instance or the default value
	 */
	public static ERunType fromIndexOrElse(Integer index, ERunType defaultValue) {
		return UwArray.getOrElse(index, VALUES, defaultValue);
	}

	/**
	 * Get an {@link ERunType} instance by its index
	 * or return a default value if failed.
	 *
	 * @param index					index of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link ERunType} instance or the default value
	 */
	public static ERunType fromIndexOrElse(Integer index, Supplier<ERunType> defaultValueSupplier) {
		return UwObject.ifNull(fromIndexOrNull(index), defaultValueSupplier);
	}

	/**
	 * Get an {@link ERunType} instance by its index
	 * or return a default value if failed.
	 *
	 * <p>Wraps {@link #fromIndexOrElse(Integer, ERunType)}
	 * w/ {@code null} as the default value.
	 *
	 * @param index		index of the instance
	 * @return			associated {@link ERunType} instance or {@code null}
	 */
	public static ERunType fromIndexOrNull(Integer index) {
		return fromIndexOrElse(index, (ERunType) null);
	}
}
