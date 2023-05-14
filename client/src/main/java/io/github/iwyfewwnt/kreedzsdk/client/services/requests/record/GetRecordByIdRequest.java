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

import io.github.iwyfewwnt.kreedzsdk.clientapi.IRecordService;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.RecordEntity;
import io.github.iwyfewwnt.uwretrofit.services.requests.IRequest;
import retrofit2.Call;

import java.util.Objects;

/**
 * A request for /records/.../ endpoint.
 */
@SuppressWarnings({"MethodDoesntCallSuperMethod", "SynchronizeOnNonFinalField"})
public class GetRecordByIdRequest implements IRequest, Cloneable {

	/**
	 * An identifier.
	 */
	protected final Integer id;

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
	 * Initialize a {@link GetRecordByIdRequest} instance.
	 *
	 * @param id	identifier
	 */
	GetRecordByIdRequest(Integer id) {
		this.id = id;

		this.initMutexObjects();
	}

	/**
	 * Initialize a {@link GetRecordByIdRequest} instance/
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	GetRecordByIdRequest(GetRecordByIdRequest that) {
		this(that.id);
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

		GetRecordByIdRequest that = (GetRecordByIdRequest) obj;

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

			String simpleName = this.getClass()
					.getSimpleName();

			return (this.stringCache = simpleName + "["
					+ "id=" + this.id
					+ "]");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetRecordByIdRequest clone() {
		return new GetRecordByIdRequest(this);
	}

	/**
	 * A request manager for /records/.../ endpoint.
	 */
	@SuppressWarnings("unused")
	public static final class Manager extends BaseGetRecordByIdRequestManager<
			Manager, GetRecordByIdRequest, RecordEntity> {

		/**
		 * A record service.
		 */
		private final IRecordService recordService;

		/**
		 * Initialize a {@link GetRecordByIdRequest.Manager} instance.
		 *
		 * @param recordService		record service
		 */
		public Manager(IRecordService recordService) {
			if (recordService == null) {
				throw new IllegalArgumentException("Record service mustn't be <null>");
			}

			this.recordService = recordService;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public GetRecordByIdRequest build() {
			return new GetRecordByIdRequest(this.id);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Call<RecordEntity> call(GetRecordByIdRequest request) {
			return this.recordService.getRecordById(request.id);
		}
	}
}
