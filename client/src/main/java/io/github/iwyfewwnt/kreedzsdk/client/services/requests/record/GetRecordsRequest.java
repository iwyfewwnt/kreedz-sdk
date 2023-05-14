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

package io.github.iwyfewwnt.kreedzsdk.client.services.requests.record;

import io.github.iwyfewwnt.kreedzsdk.structs.types.ERunType;
import io.github.iwyfewwnt.kreedzsdk.structs.types.ETickrate;
import io.github.iwyfewwnt.uwretrofit.services.requests.IRequest;

import java.util.Objects;

/**
 * A base request for /records/top/{@literal **}/ endpoints.
 */
@SuppressWarnings({"MethodDoesntCallSuperMethod", "SynchronizeOnNonFinalField"})
public class GetRecordsRequest implements IRequest, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = GetRecordsRequest.class.getSimpleName();

	/**
	 * A type-64 person identifier.
	 */
	protected final Long steamId64;

	/**
	 * A map identifier.
	 */
	protected final Integer mapId;

	/**
	 * A map name.
	 */
	protected final String mapName;

	/**
	 * A run type.
	 */
	protected final ERunType runType;

	/**
	 * A tickrate.
	 */
	protected final ETickrate tickrate;

	/**
	 * A stage identifier.
	 */
	protected final Integer stage;

	/**
	 * A mode name.
	 */
	protected final String modeName;

	/**
	 * An offset.
	 */
	protected final Integer offset;

	/**
	 * A limit.
	 */
	protected final Integer limit;

	/**
	 * A {@link #hashCode()} cache.
	 */
	protected transient volatile Integer hashCodeCache;

	/**
	 * A {@link #toString()} cache.
	 */
	protected transient volatile String stringCache;

	/**
	 * A {@link #hashCodeCache} mutex.
	 */
	protected transient Object hashCodeCacheMutex;

	/**
	 * A {@link #stringCache} mutex.
	 */
	protected transient Object stringCacheMutex;

	/**
	 * Initialize this mutex objects.
	 */
	protected void initMutexObjects() {
		this.hashCodeCacheMutex = new Object();
		this.stringCacheMutex = new Object();
	}

	/**
	 * Override the {@code #readResolve} method to set up
	 * the object cache mutexes after deserialization.
	 *
	 * @return	this instance
	 */
	private Object readResolve() {
		this.initMutexObjects();

		return this;
	}

	/**
	 * Initialize a {@link GetRecordsRequest} instance.
	 *
	 * @param steamId64		type-64 person identifier
	 * @param mapId			map identifier
	 * @param mapName		map name
	 * @param runType		run type
	 * @param tickrate		tickrate
	 * @param stage			stage identifier
	 * @param modeName		mode name
	 * @param offset		offset
	 * @param limit			limit
	 */
	GetRecordsRequest(
			Long steamId64,
			Integer mapId,
			String mapName,
			ERunType runType,
			ETickrate tickrate,
			Integer stage,
			String modeName,
			Integer offset,
			Integer limit
	) {
		this.steamId64 = steamId64;
		this.mapId = mapId;
		this.mapName = mapName;
		this.runType = runType;
		this.tickrate = tickrate;
		this.stage = stage;
		this.modeName = modeName;
		this.offset = offset;
		this.limit = limit;

		this.initMutexObjects();
	}

	/**
	 * Initialize a {@link GetRecordsRequest} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	GetRecordsRequest(GetRecordsRequest that) {
		this(
				that.steamId64,
				that.mapId,
				that.mapName,
				that.runType,
				that.tickrate,
				that.stage,
				that.modeName,
				that.offset,
				that.limit
		);

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}

		GetRecordsRequest that = (GetRecordsRequest) obj;

		return Objects.equals(this.steamId64, that.steamId64)
				&& Objects.equals(this.mapId, that.mapId)
				&& Objects.equals(this.mapName, that.mapName)
				&& this.runType == that.runType
				&& this.tickrate == that.tickrate
				&& Objects.equals(this.stage, that.stage)
				&& Objects.equals(this.modeName, that.modeName)
				&& Objects.equals(this.offset, that.offset)
				&& Objects.equals(this.limit, that.limit);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		if (this.hashCodeCache != null) {
			return this.hashCodeCache;
		}

		synchronized (this.hashCodeCacheMutex) {
			if (this.hashCodeCache != null) {
				return this.hashCodeCache;
			}

			return (this.hashCodeCache
					= Objects.hash(
							this.steamId64,
							this.mapId,
							this.mapName,
							this.runType,
							this.tickrate,
							this.stage,
							this.modeName,
							this.offset,
							this.limit
					)
			);
		}
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

			return (this.stringCache = SIMPLE_NAME + "["
					+ "steamId64=" + this.steamId64
					+ ", mapId=" + this.mapId
					+ ", mapName=\"" + this.mapName + "\""
					+ ", runType=" + this.runType
					+ ", tickrate=" + this.tickrate
					+ ", stage=" + this.stage
					+ ", modeName=\"" + this.modeName + "\""
					+ ", offset=" + this.offset
					+ ", limit=" + this.limit
					+ "]");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetRecordsRequest clone() {
		return new GetRecordsRequest(this);
	}
}
