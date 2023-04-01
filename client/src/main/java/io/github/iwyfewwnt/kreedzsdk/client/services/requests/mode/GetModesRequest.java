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

import java.util.List;

/**
 * A request for /modes/ endpoint.
 */
public final class GetModesRequest implements IRequest {

	private GetModesRequest() {
		throw new UnsupportedOperationException();
	}

	/**
	 * A request manager for /modes/ endpoint.
	 */
	public static final class Manager extends AbstractRequestManager<GetModesRequest, List<ModeEntity>> {

		/**
		 * A mode service.
		 */
		private final IModeService modeService;

		/**
		 * Initialize a {@link GetModesRequest.Manager} instance.
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
		 *
		 * @return 	null, always
		 */
		@Override
		public GetModesRequest build() {
			return null;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Call<List<ModeEntity>> call(GetModesRequest unused) {
			return this.modeService.getModes();
		}
	}
}
