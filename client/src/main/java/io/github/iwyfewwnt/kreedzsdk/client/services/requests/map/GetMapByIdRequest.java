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
 * A request for /maps/.../ endpoint.
 */
@SuppressWarnings({"MethodDoesntCallSuperMethod", "SynchronizeOnNonFinalField"})
public final class GetMapByIdRequest implements IRequest, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = GetMapByIdRequest.class.getSimpleName();

	/**
	 * An identifier.
	 */
	private final Integer id;

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
	 * Initialize a {@link GetMapByIdRequest} instance.
	 *
	 * @param id	identifier
	 */
	private GetMapByIdRequest(Integer id) {
		this.id = id;

		this.initMutexObjects();
	}

	/**
	 * Initialize a {@link GetMapByIdRequest} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private GetMapByIdRequest(GetMapByIdRequest that) {
		this(that.id);

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

		GetMapByIdRequest that = (GetMapByIdRequest) obj;

		return Objects.equals(this.id, that.id);
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
					= Objects.hash(this.id));
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
					+ "id=" + this.id
					+ "]");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetMapByIdRequest clone() {
		return new GetMapByIdRequest(this);
	}

	/**
	 * A request manager for /maps/.../ endpoint.
	 */
	@SuppressWarnings("unused")
	public static final class Manager extends AbstractRequestManager<GetMapByIdRequest, MapEntity> {

		/**
		 * A map service.
		 */
		private final IMapService mapService;

		/**
		 * An identifier.
		 */
		private Integer id;

		/**
		 * Initialize a {@link GetMapByIdRequest.Manager} instance.
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
		public GetMapByIdRequest build() {
			return new GetMapByIdRequest(this.id);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Call<MapEntity> call(GetMapByIdRequest request) {
			return this.mapService.getMap(request.id);
		}

		/**
		 * Set this identifier.
		 *
		 * @param id	identifier, may be null
		 * @return		this instance
		 */
		public Manager setId(Integer id) {
			this.id = id;
			return this;
		}
	}
}
