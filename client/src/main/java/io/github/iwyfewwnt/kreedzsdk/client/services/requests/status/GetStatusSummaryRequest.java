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

package io.github.iwyfewwnt.kreedzsdk.client.services.requests.status;

import io.github.iwyfewwnt.kreedzsdk.clientapi.IStatusService;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.status.responses.StatusSummaryResponseEntity;
import io.github.iwyfewwnt.uwretrofit.services.requests.IRequest;
import io.github.iwyfewwnt.uwretrofit.services.requests.impl.AbstractRequestManager;
import retrofit2.Call;

/**
 * A request for /summary.json/ endpoint.
 */
public final class GetStatusSummaryRequest implements IRequest {

	private GetStatusSummaryRequest() {
		throw new UnsupportedOperationException();
	}

	/**
	 * A request manager for /summary.json/ endpoint.
	 */
	public static final class Manager extends AbstractRequestManager<
			GetStatusSummaryRequest, StatusSummaryResponseEntity> {

		/**
		 * A status service.
		 */
		private final IStatusService statusService;

		/**
		 * Initialize a {@link GetStatusSummaryRequest.Manager} instance.
		 *
		 * @param statusService		status service
		 */
		public Manager(IStatusService statusService) {
			if (statusService == null) {
				throw new IllegalArgumentException("Status service mustn't be <null>");
			}

			this.statusService = statusService;
		}

		/**
		 * {@inheritDoc}
		 *
		 * @return	null, always
		 */
		@Override
		public GetStatusSummaryRequest build() {
			return null;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Call<StatusSummaryResponseEntity> call(GetStatusSummaryRequest unused) {
			return this.statusService.getSummary();
		}
	}
}
