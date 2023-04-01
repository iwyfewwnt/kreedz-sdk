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

package io.github.iwyfewwnt.kreedzsdk.client.services.requests.mapsinfo;

import io.github.iwyfewwnt.kreedzsdk.clientapi.IMapInfoService;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.map.MapInfoEntity;
import io.github.iwyfewwnt.uwretrofit.services.requests.IRequest;
import io.github.iwyfewwnt.uwretrofit.services.requests.impl.AbstractRequestManager;
import retrofit2.Call;

import java.util.List;

/**
 * A request for /main/non-global.min.json/ endpoint.
 */
public final class GetNonGlobalMapsInfoRequest implements IRequest {

	private GetNonGlobalMapsInfoRequest() {
		throw new UnsupportedOperationException();
	}

	/**
	 * A request manager for /main/non-global.min.json/ endpoint.
	 */
	public static final class Manager extends AbstractRequestManager<GetNonGlobalMapsInfoRequest, List<MapInfoEntity>> {

		/**
		 * A map information service.
		 */
		private final IMapInfoService mapInfoService;

		/**
		 * Initialize a {@link GetNonGlobalMapsInfoRequest.Manager} instance.
		 *
		 * @param mapInfoService	map information service
		 */
		public Manager(IMapInfoService mapInfoService) {
			if (mapInfoService == null) {
				throw new IllegalArgumentException("Map information service mustn't be <null>");
			}

			this.mapInfoService = mapInfoService;
		}


		/**
		 * {@inheritDoc}
		 *
		 * @return	null, always
		 */
		@Override
		public GetNonGlobalMapsInfoRequest build() {
			return null;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Call<List<MapInfoEntity>> call(GetNonGlobalMapsInfoRequest unused) {
			return this.mapInfoService.getNonGlobalMapsInfo();
		}
	}
}
