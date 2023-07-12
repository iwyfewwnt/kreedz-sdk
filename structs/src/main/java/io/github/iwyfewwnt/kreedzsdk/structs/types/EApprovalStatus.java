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
 * An enumeration of kreedz API approval statuses.
 */
@SuppressWarnings("unused")
public enum EApprovalStatus {

	/**
	 * A kreedz API approval status - [0] Approved.
	 */
	APPROVED(0),

	/**
	 * A kreedz API approval status - [1] Queued.
	 */
	QUEUED(1);

	/**
	 * A class instance of this class.
	 */
	private static final Class<EApprovalStatus> CLASS = EApprovalStatus.class;

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = CLASS.getSimpleName();

	/**
	 * An array of {@link EApprovalStatus} instances.
	 */
	private static final EApprovalStatus[] VALUES = UwEnum.values(CLASS);

	/**
	 * A map of {@link EApprovalStatus} instances by their API-identifier field.
	 */
	private static final Map<Integer, EApprovalStatus> MAP_BY_ID = UwMap.createByFieldOrNull(
			entry -> entry.id, VALUES
	);

	/**
	 * An API-identifier.
	 */
	private final int id;

	/**
	 * A {@link #toString()} cache.
	 */
	private String stringCache;

	/**
	 * A {@link #stringCache} mutex.
	 */
	private final Object stringCacheMutex;

	/**
	 * Initialize an {@link EApprovalStatus} instance.
	 *
	 * @param id	API-identifier
	 */
	EApprovalStatus(int id) {
		this.id = id;

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
					+ "id=" + this.id
					+ "]");
		}
	}

	/**
	 * Get an API-identifier from the provided {@link EApprovalStatus} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EApprovalStatus} instance is {@code null}.
	 * </ul>
	 *
	 * @param approvalStatus	approval status instance from which get the API-identifier
	 * @param defaultValue		default value to return on failure
	 * @return					API-identifier value of the approval status or the default one
	 */
	public static Integer getIdOrElse(EApprovalStatus approvalStatus, Integer defaultValue) {
		return UwObject.ifNotNull(approvalStatus, EApprovalStatus::getId, defaultValue);
	}

	/**
	 * Get an API-identifier from the provided {@link EApprovalStatus} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EApprovalStatus} instance is {@code null}.
	 * </ul>
	 *
	 * @param approvalStatus		approval status instance from which get the API-identifier
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						API-identifier value of the approval status or the default one
	 */
	public static Integer getIdOrElse(EApprovalStatus approvalStatus, Supplier<Integer> defaultValueSupplier) {
		return UwObject.ifNull(getIdOrNull(approvalStatus), defaultValueSupplier);
	}

	/**
	 * Get an API-identifier from the provided {@link EApprovalStatus} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EApprovalStatus} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link #getIdOrElse(EApprovalStatus, Integer)}
	 * w/ {@code null} as the default value.
	 *
	 * @param approvalStatus	approval status instance from which get the API-identifier
	 * @return					API-identifier value of the approval status or {@code null}
	 */
	public static Integer getIdOrNull(EApprovalStatus approvalStatus) {
		return getIdOrElse(approvalStatus, (Integer) null);
	}

	/**
	 * Get an {@link EApprovalStatus} by its API-identifier
	 * or return a default value if failed.
	 *
	 * @param id			API-identifier of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link EApprovalStatus} instance or the default value
	 */
	public static EApprovalStatus fromIdOrElse(Integer id, EApprovalStatus defaultValue) {
		return UwMap.getOrElse(id, MAP_BY_ID, defaultValue);
	}

	/**
	 * Get an {@link EApprovalStatus} by its API-identifier
	 * or return a default value if failed.
	 *
	 * @param id					API-identifier of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link EApprovalStatus} instance or the default value
	 */
	public static EApprovalStatus fromIdOrElse(Integer id, Supplier<EApprovalStatus> defaultValueSupplier) {
		return UwObject.ifNull(fromIdOrNull(id), defaultValueSupplier);
	}

	/**
	 * Get an {@link EApprovalStatus} by its API-identifier
	 * or return {@code null} if failed.
	 *
	 * <p>Wraps {@link #fromIdOrElse(Integer, EApprovalStatus)}
	 * w/ {@code null} as the default value.
	 *
	 * @param id	API-identifier of the instance
	 * @return		associated {@link EApprovalStatus} instance or {@code null}
	 */
	public static EApprovalStatus fromIdOrNull(Integer id) {
		return fromIdOrElse(id, (EApprovalStatus) null);
	}

	/**
	 * Get an {@link EApprovalStatus} by its index
	 * or return a default value if failed.
	 *
	 * @param index			index of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link EApprovalStatus} instance or the default value
	 */
	public static EApprovalStatus fromIndexOrElse(Integer index, EApprovalStatus defaultValue) {
		return UwArray.getOrElse(index, VALUES, defaultValue);
	}

	/**
	 * Get an {@link EApprovalStatus} by its index
	 * or return a default value if failed.
	 *
	 * @param index					index of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link EApprovalStatus} instance or the default value
	 */
	public static EApprovalStatus fromIndexOrElse(Integer index, Supplier<EApprovalStatus> defaultValueSupplier) {
		return UwObject.ifNull(fromIndexOrNull(index), defaultValueSupplier);
	}

	/**
	 * Get an {@link EApprovalStatus} by its index
	 * or return {@code null} if failed.
	 *
	 * <p>Wraps {@link #fromIndexOrElse(Integer, EApprovalStatus)}
	 * w/ {@code null} as the default value.
	 *
	 * @param index		index of the instance
	 * @return			associated {@link EApprovalStatus} instance or {@code null}
	 */
	public static EApprovalStatus fromIndexOrNull(Integer index) {
		return fromIndexOrElse(index, (EApprovalStatus) null);
	}
}
