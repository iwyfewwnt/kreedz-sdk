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

package io.github.iwyfewwnt.kreedzsdk.client.services.requests.mapimage;

import io.github.iwyfewwnt.kreedzsdk.clientapi.IMapImageService;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.map.MapImageEntity;
import io.github.iwyfewwnt.uwretrofit.services.requests.IRequest;
import io.github.iwyfewwnt.uwretrofit.services.requests.impl.AbstractRequestManager;
import retrofit2.Call;

import java.util.List;

/**
 * A request for /public/maps.mis.json/ endpoint.
 */
public final class GetMapImagesRequest implements IRequest {

	private GetMapImagesRequest() {
		throw new UnsupportedOperationException();
	}

	/**
	 * A request manager for /public/maps.mis.json/ endpoint.
	 */
	public static final class Manager extends AbstractRequestManager<GetMapImagesRequest, List<MapImageEntity>> {

		/**
		 * A map image service.
		 */
		private final IMapImageService mapImageService;

		/**
		 * Initialize a {@link GetMapImagesRequest.Manager} instance.
		 *
		 * @param mapImageService	map image service
		 */
		public Manager(IMapImageService mapImageService) {
			if (mapImageService == null) {
				throw new IllegalArgumentException("Map image service mustn't be <null>");
			}

			this.mapImageService = mapImageService;
		}

		/**
		 * {@inheritDoc}
		 *
		 * @return	null, always
		 */
		@Override
		public GetMapImagesRequest build() {
			return null;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Call<List<MapImageEntity>> call(GetMapImagesRequest unused) {
			return this.mapImageService.getMapImages();
		}
	}
}
