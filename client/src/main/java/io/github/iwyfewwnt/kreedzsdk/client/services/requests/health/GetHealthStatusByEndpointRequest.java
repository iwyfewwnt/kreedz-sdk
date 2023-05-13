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

package io.github.iwyfewwnt.kreedzsdk.client.services.requests.health;

import io.github.iwyfewwnt.kreedzsdk.clientapi.IHealthService;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.health.responses.HealthStatusResponseEntity;
import io.github.iwyfewwnt.kreedzsdk.structs.types.health.EHealthEndpoint;
import io.github.iwyfewwnt.uwretrofit.services.requests.IRequest;
import io.github.iwyfewwnt.uwretrofit.services.requests.impl.AbstractRequestManager;
import retrofit2.Call;

import java.util.Objects;

/**
 * A request for /endpoints/.../statuses/ endpoint.
 */
@SuppressWarnings({"MethodDoesntCallSuperMethod", "SynchronizeOnNonFinalField"})
public final class GetHealthStatusByEndpointRequest implements IRequest, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = GetHealthStatusByEndpointRequest.class.getSimpleName();

	/**
	 * A group.
	 */
	private final String group;

	/**
	 * An endpoint.
	 */
	private final String endpoint;

	/**
	 * A {@link GetHealthStatusByEndpointRequest#hashCode()} cache.
	 */
	private transient volatile Integer hashCodeCache;

	/**
	 * A {@link GetHealthStatusByEndpointRequest#toString()} cache.
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
	 * Initialize a {@link GetHealthStatusByEndpointRequest} instance.
	 *
	 * @param group		group
	 * @param endpoint	endpoint
	 */
	private GetHealthStatusByEndpointRequest(
			String group,
			String endpoint
	) {
		this.group = group;
		this.endpoint = endpoint;

		this.initMutexObjects();
	}

	/**
	 * Initialize a {@link GetHealthStatusByEndpointRequest} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private GetHealthStatusByEndpointRequest(GetHealthStatusByEndpointRequest that) {
		this(
				that.group,
				that.endpoint
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

		GetHealthStatusByEndpointRequest that = (GetHealthStatusByEndpointRequest) obj;

		return Objects.equals(this.group, that.group)
				&& Objects.equals(this.endpoint, that.endpoint);
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
							this.group,
							this.endpoint
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
					+ "group=\"" + this.group + "\""
					+ ", endpoint=\"" + this.endpoint + "\""
					+ "]");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetHealthStatusByEndpointRequest clone()	{
		return new GetHealthStatusByEndpointRequest(this);
	}

	/**
	 * A request manager for /endpoints/.../statuses/ endpoint.
	 */
	@SuppressWarnings("unused")
	public static final class Manager extends AbstractRequestManager<
			GetHealthStatusByEndpointRequest, HealthStatusResponseEntity> {

		/**
		 * A health service.
		 */
		private final IHealthService healthService;

		/**
		 * An endpoint.
		 */
		private EHealthEndpoint endpoint;

		/**
		 * Initialize a {@link GetHealthStatusByEndpointRequest.Manager} instance.
		 *
		 * @param healthService		health service
		 */
		public Manager(IHealthService healthService) {
			if (healthService == null) {
				throw new IllegalArgumentException("Health service mustn't be <null>");
			}

			this.healthService = healthService;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public GetHealthStatusByEndpointRequest build() {
			String group = EHealthEndpoint.getGroupOrNull(this.endpoint);
			String endpoint = EHealthEndpoint.getEndpointOrNull(this.endpoint);

			return new GetHealthStatusByEndpointRequest(
					group,
					endpoint
			);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Call<HealthStatusResponseEntity> call(GetHealthStatusByEndpointRequest request) {
			return this.healthService.getHealthStatusByEndpoint(
					request.group,
					request.endpoint
			);
		}

		/**
		 * Set this endpoint.
		 *
		 * @param endpoint	endpoint, may be null
		 * @return			this instance
		 */
		public Manager setEndpoint(EHealthEndpoint endpoint) {
			this.endpoint = endpoint;
			return this;
		}
	}
}
