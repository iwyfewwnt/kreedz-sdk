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

package io.github.iwyfewwnt.kreedzsdk.client.services.requests.map;

import io.github.iwyfewwnt.kreedzsdk.clientapi.IMapService;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.MapEntity;
import io.github.iwyfewwnt.uwretrofit.services.requests.IRequest;
import io.github.iwyfewwnt.uwretrofit.services.requests.impl.AbstractRequestManager;
import retrofit2.Call;

import java.util.Objects;

/**
 * A request for /maps/name/.../ endpoint.
 */
@SuppressWarnings({"MethodDoesntCallSuperMethod", "SynchronizeOnNonFinalField"})
public final class GetMapByNameRequest implements IRequest, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = GetMapByNameRequest.class.getSimpleName();

	/**
	 * A map name.
	 */
	private final String mapName;

	/**
	 * A {@link #hashCode()} cache.
	 */
	private transient volatile Integer hashCodeCache;

	/**
	 * A {@link #toString()} cache.
	 */
	private transient volatile String stringCache;

	/**
	 * A {@link #hashCodeCache} mutex.
	 */
	private transient Object hashCodeCacheMutex;

	/**
	 * A {@link #stringCache} mutex.
	 */
	private transient Object stringCacheMutex;

	/**
	 * Initialize this mutex objects.
	 */
	private void initMutexObjects() {
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
	 * Initialize a {@link GetMapByNameRequest} instance.
	 *
	 * @param mapName	map name
	 */
	private GetMapByNameRequest(String mapName) {
		this.mapName = mapName;

		this.initMutexObjects();
	}

	/**
	 * Initialize a {@link GetMapByNameRequest} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private GetMapByNameRequest(GetMapByNameRequest that) {
		this(that.mapName);

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

		GetMapByNameRequest that = (GetMapByNameRequest) obj;

		return Objects.equals(this.mapName, that.mapName);
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
					= Objects.hash(this.mapName));
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
					+ "mapName=\"" + this.mapName + "\""
					+ "]");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetMapByNameRequest clone()	{
		return new GetMapByNameRequest(this);
	}

	/**
	 * A request manager for /maps/name/.../ endpoint.
	 */
	@SuppressWarnings("unused")
	public static final class Manager extends AbstractRequestManager<GetMapByNameRequest, MapEntity> {

		/**
		 * A map service.
		 */
		private final IMapService mapService;

		/**
		 * A map name.
		 */
		private String mapName;

		/**
		 * Initialize a {@link GetMapByNameRequest.Manager} instance.
		 *
		 * @param mapService	map service
		 */
		public Manager(IMapService mapService) {
			if (mapService == null) {
				throw new IllegalArgumentException("Map service mustn't be <null>");
			}

			this.mapService = mapService;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public GetMapByNameRequest build() {
			return new GetMapByNameRequest(this.mapName);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Call<MapEntity> call(GetMapByNameRequest request) {
			return this.mapService.getMap(request.mapName);
		}

		/**
		 * Set this map name.
		 *
		 * @param mapName	map name, may be null
		 * @return			this instance
		 */
		public Manager setMapName(String mapName) {
			this.mapName = mapName;
			return this;
		}
	}
}
