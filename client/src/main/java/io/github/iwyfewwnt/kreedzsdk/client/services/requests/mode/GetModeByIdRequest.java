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

package io.github.iwyfewwnt.kreedzsdk.client.services.requests.mode;

import io.github.iwyfewwnt.kreedzsdk.clientapi.IModeService;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.ModeEntity;
import io.github.iwyfewwnt.uwretrofit.services.requests.IRequest;
import io.github.iwyfewwnt.uwretrofit.services.requests.impl.AbstractRequestManager;
import retrofit2.Call;

import java.util.Objects;

/**
 * A request for /modes/id/.../ endpoint.
 */
@SuppressWarnings("MethodDoesntCallSuperMethod")
public final class GetModeByIdRequest implements IRequest, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = GetModeByIdRequest.class.getSimpleName();

	/**
	 * An identifier.
	 */
	private final Integer id;

	/**
	 * A {@link GetModeByIdRequest#hashCode()} cache.
	 */
	private transient Integer hashCodeCache;

	/**
	 * A {@link GetModeByIdRequest#toString()} cache.
	 */
	private transient String stringCache;

	/**
	 * Initialize a {@link GetModeByIdRequest} instance.
	 *
	 * @param id	identifier
	 */
	private GetModeByIdRequest(Integer id) {
		this.id = id;
	}

	/**
	 * Initialize a {@link GetModeByIdRequest} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private GetModeByIdRequest(GetModeByIdRequest that) {
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

		GetModeByIdRequest that = (GetModeByIdRequest) obj;

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

		return (this.hashCodeCache
				= Objects.hash(this.id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		if (this.stringCache != null) {
			return this.stringCache;
		}

		return (this.stringCache = SIMPLE_NAME + "["
				+ "id=" + this.id
				+ "]");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetModeByIdRequest clone() {
		return new GetModeByIdRequest(this);
	}

	/**
	 * A request manager for /modes/id/.../ endpoint.
	 */
	@SuppressWarnings("unused")
	public static final class Manager extends AbstractRequestManager<GetModeByIdRequest, ModeEntity> {

		/**
		 * A mode service.
		 */
		private final IModeService modeService;

		/**
		 * An identifier.
		 */
		private Integer id;

		/**
		 * Initialize a {@link GetModeByIdRequest.Manager} instance.
		 *
		 * @param modeService	mode service
		 */
		public Manager(IModeService modeService) {
			if (modeService == null) {
				throw new IllegalArgumentException("Mode service mustn't be <null>");
			}

			this.modeService = modeService;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public GetModeByIdRequest build() {
			return new GetModeByIdRequest(this.id);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Call<ModeEntity> call(GetModeByIdRequest request) {
			return this.modeService.getMode(request.id);
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
