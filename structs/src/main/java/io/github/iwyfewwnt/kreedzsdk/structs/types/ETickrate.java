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
import io.github.iwyfewwnt.uwutils.UwMap;
import io.github.iwyfewwnt.uwutils.UwObject;

import java.util.Map;
import java.util.function.Supplier;

/**
 * An enumeration of kreedz API tickrates.
 */
@SuppressWarnings("unused")
public enum ETickrate {

	/**
	 * A kreedz API tickrate - 128.0 t/s
	 */
	T128(128.0f),

	/**
	 * A kreedz API tickrate - 102.4 t/s
	 */
	T102(102.4f),

	/**
	 * A kreedz API tickrate - 64.0 t/s
	 */
	T64(64.0f);

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = ETickrate.class.getSimpleName();

	/**
	 * An array of {@link ETickrate} instances.
	 */
	private static final ETickrate[] VALUES = UwEnum.values(ETickrate.class);

	/**
	 * A map of {@link ETickrate} instances by their float value field.
	 */
	private static final Map<Float, ETickrate> MAP_BY_FLOAT = UwMap.newMapByFieldOrNull(
			entry -> entry.fltVal, ETickrate.class
	);

	/**
	 * A map of {@link ETickrate} instances by their integer value field.
	 */
	private static final Map<Integer, ETickrate> MAP_BY_INT = UwMap.newMapByFieldOrNull(
			entry -> entry.intVal, ETickrate.class
	);

	/**
	 * A float value.
	 */
	private final float fltVal;

	/**
	 * An integer value.
	 */
	private final int intVal;

	/**
	 * A {@link ETickrate#toString()} cache.
	 */
	private String stringCache;

	/**
	 * A {@link #stringCache} mutex.
	 */
	private final Object stringCacheMutex;

	/**
	 * Initialize an {@link ETickrate} instance.
	 *
	 * @param value		float value
	 */
	ETickrate(float value) {
		this.fltVal = value;
		this.intVal = (int) value;

		this.stringCacheMutex = new Object();
	}

	/**
	 * Get this float value.
	 *
	 * @return	float value
	 */
	public float getAsFloat() {
		return this.fltVal;
	}

	/**
	 * Get this integer value.
	 *
	 * @return	integer value
	 */
	public int getAsInt() {
		return this.intVal;
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
					+ "fltVal=" + this.fltVal
					+ ", intVal=" + this.intVal
					+ "]");
		}
	}

	/**
	 * Get a float value from the provided {@link ETickrate} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link ETickrate} instance is {@code null}.
	 * </ul>
	 *
	 * @param tickrate		tickrate instance from which get the float value
	 * @param defaultValue	default value to return on failure
	 * @return				float value of the tickrate or the default one
	 */
	public static Float getAsFloatOrElse(ETickrate tickrate, Float defaultValue) {
		if (tickrate == null) {
			return defaultValue;
		}

		return tickrate.getAsFloat();
	}

	/**
	 * Get a float value from the provided {@link ETickrate} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link ETickrate} instance is {@code null}.
	 * </ul>
	 *
	 * @param tickrate				tickrate instance from which get the float value
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						float value of the tickrate or the default one
	 */
	public static Float getAsFloatOrElse(ETickrate tickrate, Supplier<Float> defaultValueSupplier) {
		return UwObject.ifNull(getAsFloatOrNull(tickrate), defaultValueSupplier);
	}

	/**
	 * Get a float value from the provided {@link ETickrate} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link ETickrate} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link ETickrate#getAsFloatOrElse(ETickrate, Float)}
	 * w/ {@code null} as the default value.
	 *
	 * @param tickrate	tickrate instance from which get the float value
	 * @return			float value of the tickrate or {@code null}
	 */
	public static Float getAsFloatOrNull(ETickrate tickrate) {
		return getAsFloatOrElse(tickrate, (Float) null);
	}

	/**
	 * Get an integer value from the provided {@link ETickrate} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link ETickrate} instance is {@code null}.
	 * </ul>
	 *
	 * @param tickrate		tickrate instance from which get the integer value
	 * @param defaultValue	default value to return on failure
	 * @return				integer value of the tickrate or the default one
	 */
	public static Integer getAsIntOrElse(ETickrate tickrate, Integer defaultValue) {
		if (tickrate == null) {
			return defaultValue;
		}

		return tickrate.getAsInt();
	}

	/**
	 * Get an integer value from the provided {@link ETickrate} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link ETickrate} instance is {@code null}.
	 * </ul>
	 *
	 * @param tickrate				tickrate instance from which get the integer value
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						integer value of the tickrate or the default one
	 */
	public static Integer getAsIntOrElse(ETickrate tickrate, Supplier<Integer> defaultValueSupplier) {
		return UwObject.ifNull(getAsIntOrNull(tickrate), defaultValueSupplier);
	}

	/**
	 * Get an integer value from the provided {@link ETickrate} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link ETickrate} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link ETickrate#getAsIntOrElse(ETickrate, Integer)}
	 * w/ {@code null} as the default value.
	 *
	 * @param tickrate	tickrate instance from which get the integer value
	 * @return			integer value of the tickrate or {@code null}
	 */
	public static Integer getAsIntOrNull(ETickrate tickrate) {
		return getAsIntOrElse(tickrate, (Integer) null);
	}

	/**
	 * Get an {@link ETickrate} instance by its float value
	 * or return a default value if failed.
	 *
	 * @param fltVal		float value of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link ETickrate} instance or the default value
	 */
	public static ETickrate fromFloatOrElse(Float fltVal, ETickrate defaultValue) {
		return UwMap.getOrElse(fltVal, MAP_BY_FLOAT, defaultValue);
	}

	/**
	 * Get an {@link ETickrate} instance by its float value
	 * or return a default value if failed.
	 *
	 * @param fltVal				float value of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link ETickrate} instance or the default value
	 */
	public static ETickrate fromFloatOrElse(Float fltVal, Supplier<ETickrate> defaultValueSupplier) {
		return UwObject.ifNull(fromFloatOrNull(fltVal), defaultValueSupplier);
	}

	/**
	 * Get an {@link ETickrate} instance by its float value
	 * or return a default value if failed.
	 *
	 * <p>Wraps {@link ETickrate#fromFloatOrElse(Float, ETickrate)}
	 * w/ {@code null} as the default value.
	 *
	 * @param fltVal	float value of the instance
	 * @return			associated {@link ETickrate} instance or {@code null}
	 */
	public static ETickrate fromFloatOrNull(Float fltVal) {
		return fromFloatOrElse(fltVal, (ETickrate) null);
	}

	/**
	 * Get an {@link ETickrate} instance by its integer value
	 * or return a default value if failed.
	 *
	 * @param intVal		integer value of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link ETickrate} instance or the default value
	 */
	public static ETickrate fromIntOrElse(Integer intVal, ETickrate defaultValue) {
		return UwMap.getOrElse(intVal, MAP_BY_INT, defaultValue);
	}

	/**
	 * Get an {@link ETickrate} instance by its integer value
	 * or return a default value if failed.
	 *
	 * @param intVal				integer value of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link ETickrate} instance or the default value
	 */
	public static ETickrate fromIntOrElse(Integer intVal, Supplier<ETickrate> defaultValueSupplier) {
		return UwObject.ifNull(fromIntOrNull(intVal), defaultValueSupplier);
	}

	/**
	 * Get an {@link ETickrate} instance by its integer value
	 * or return a default value if failed.
	 *
	 * <p>Wraps {@link ETickrate#fromIntOrElse(Integer, ETickrate)}
	 * w/ {@code null} as the default value.
	 *
	 * @param intVal	integer value of the instance
	 * @return			associated {@link ETickrate} instance or {@code null}
	 */
	public static ETickrate fromIntOrNull(Integer intVal) {
		return fromIntOrElse(intVal, (ETickrate) null);
	}

	/**
	 * Get an {@link ETickrate} instance by its index
	 * or return a default value if failed.
	 *
	 * @param index			index of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link ETickrate} instance or the default value
	 */
	public static ETickrate fromIndexOrElse(Integer index, ETickrate defaultValue) {
		return UwArray.getOrElse(index, VALUES, defaultValue);
	}

	/**
	 * Get an {@link ETickrate} instance by its index
	 * or return a default value if failed.
	 *
	 * @param index					index of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link ETickrate} instance or the default value
	 */
	public static ETickrate fromIndexOrElse(Integer index, Supplier<ETickrate> defaultValueSupplier) {
		return UwObject.ifNull(fromIndexOrNull(index), defaultValueSupplier);
	}

	/**
	 * Get an {@link ETickrate} instance by its index
	 * or return a default value if failed.
	 *
	 * <p>Wraps {@link ETickrate#fromIndexOrElse(Integer, ETickrate)}
	 * w/ {@code null} as the default value.
	 *
	 * @param index		index of the instance
	 * @return			associated {@link ETickrate} instance or the default value
	 */
	public static ETickrate fromIndexOrNull(Integer index) {
		return fromIndexOrElse(index, (ETickrate) null);
	}
}
