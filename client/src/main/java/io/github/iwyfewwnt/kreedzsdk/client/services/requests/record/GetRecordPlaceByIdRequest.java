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
import retrofit2.Call;

/**
 * A request for /records/place/.../ endpoint.
 */
public final class GetRecordPlaceByIdRequest extends GetRecordByIdRequest implements Cloneable {

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
	 * Initialize a {@link GetRecordPlaceByIdRequest} instance.
	 *
	 * @param id	identifier
	 */
	private GetRecordPlaceByIdRequest(Integer id) {
		super(id);
	}

	/**
	 * Initialize a {@link GetRecordPlaceByIdRequest} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private GetRecordPlaceByIdRequest(GetRecordPlaceByIdRequest that) {
		super(that);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetRecordPlaceByIdRequest clone() {
		return new GetRecordPlaceByIdRequest(this);
	}

	/**
	 * A request manager for /records/place/.../ endpoint.
	 */
	@SuppressWarnings("unused")
	public static final class Manager extends BaseGetRecordByIdRequestManager<
			Manager, GetRecordPlaceByIdRequest, Integer> {

		/**
		 * A record service.
		 */
		private final IRecordService recordService;

		/**
		 * Initialize a {@link GetRecordPlaceByIdRequest.Manager} instance.
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
		public GetRecordPlaceByIdRequest build() {
			return new GetRecordPlaceByIdRequest(this.id);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Call<Integer> call(GetRecordPlaceByIdRequest request) {
			return this.recordService.getRecordPlaceById(request.id);
		}
	}
}
