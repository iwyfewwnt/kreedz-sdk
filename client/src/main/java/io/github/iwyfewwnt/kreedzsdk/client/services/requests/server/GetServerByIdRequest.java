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

package io.github.iwyfewwnt.kreedzsdk.client.services.requests.server;

import io.github.iwyfewwnt.kreedzsdk.clientapi.IServerService;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.ServerEntity;
import io.github.iwyfewwnt.uwretrofit.services.requests.IRequest;
import io.github.iwyfewwnt.uwretrofit.services.requests.impl.AbstractRequestManager;
import retrofit2.Call;

import java.util.Objects;

/**
 * A request for /servers/.../ endpoint.
 */
@SuppressWarnings({"MethodDoesntCallSuperMethod", "SynchronizeOnNonFinalField"})
public final class GetServerByIdRequest implements IRequest, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = GetServerByIdRequest.class.getSimpleName();

	/**
	 * An identifier.
	 */
	private final Integer id;

	/**
	 * A {@link GetServerByIdRequest#hashCode()} cache.
	 */
	private transient volatile Integer hashCodeCache;

	/**
	 * A {@link GetServerByIdRequest#toString()} cache.
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

	private Object readResolve() {
		this.initMutexObjects();

		return this;
	}

	/**
	 * Initialize a {@link GetServerByIdRequest} instance.
	 *
	 * @param id	identifier
	 */
	private GetServerByIdRequest(Integer id) {
		this.id = id;

		this.initMutexObjects();
	}

	/**
	 * Initialize a {@link GetServerByIdRequest} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private GetServerByIdRequest(GetServerByIdRequest that) {
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

		GetServerByIdRequest that = (GetServerByIdRequest) obj;

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
		//noinspection DuplicatedCode
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
	public GetServerByIdRequest clone() {
		return new GetServerByIdRequest(this);
	}

	/**
	 * A request manager for /servers/.../ endpoint.
	 */
	@SuppressWarnings("unused")
	public static final class Manager extends AbstractRequestManager<GetServerByIdRequest, ServerEntity> {

		/**
		 * A server service.
		 */
		private final IServerService serverService;

		/**
		 * An identifier.
		 */
		private Integer id;

		/**
		 * Initialize a {@link GetServerByIdRequest.Manager} instance.
		 *
		 * @param serverService		server service
		 */
		public Manager(IServerService serverService) {
			if (serverService == null) {
				throw new IllegalArgumentException("Server service mustn't be <null>");
			}

			this.serverService = serverService;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public GetServerByIdRequest build() {
			return new GetServerByIdRequest(this.id);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Call<ServerEntity> call(GetServerByIdRequest request) {
			return this.serverService.getServerById(request.id);
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
