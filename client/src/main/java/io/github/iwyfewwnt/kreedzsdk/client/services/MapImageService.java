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

package io.github.iwyfewwnt.kreedzsdk.client.services;

import com.google.auto.service.AutoService;
import io.github.iwyfewwnt.kreedzsdk.clientapi.IKreedzService;
import io.github.iwyfewwnt.kreedzsdk.clientapi.IMapImageService;
import io.github.iwyfewwnt.kreedzsdk.client.services.requests.mapimage.GetMapImagesRequest;
import io.github.iwyfewwnt.uwretrofit.services.impl.RetrofitServiceWrapper;
import retrofit2.Retrofit;

/**
 * A map image service.
 */
@SuppressWarnings("unused")
@AutoService(IKreedzService.class)
public final class MapImageService extends RetrofitServiceWrapper<IMapImageService> implements IKreedzService {

	/**
	 * Initialize a {@link MapImageService} instance.
	 *
	 * @param retrofit	retrofit instance
	 */
	public MapImageService(Retrofit retrofit) {
		super(retrofit, IMapImageService.class);
	}

	/**
	 * Create a request manager for /public/maps.mis.json/ endpoint.
	 *
	 * @return	request manager
	 */
	public GetMapImagesRequest.Manager mapImages() {
		return new GetMapImagesRequest.Manager(this.service);
	}
}
