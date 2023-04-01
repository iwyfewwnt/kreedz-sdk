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
import io.github.iwyfewwnt.kreedzsdk.clientapi.IMapService;
import io.github.iwyfewwnt.kreedzsdk.client.services.requests.map.GetMapByIdRequest;
import io.github.iwyfewwnt.kreedzsdk.client.services.requests.map.GetMapByNameRequest;
import io.github.iwyfewwnt.kreedzsdk.client.services.requests.map.GetMapsRequest;
import io.github.iwyfewwnt.uwretrofit.services.impl.RetrofitServiceWrapper;
import retrofit2.Retrofit;

/**
 * A map service.
 */
@SuppressWarnings("unused")
@AutoService(IKreedzService.class)
public final class MapService extends RetrofitServiceWrapper<IMapService> implements IKreedzService {

	/**
	 * Initialize a {@link MapService} instance.
	 *
	 * @param retrofit	retrofit instance
	 */
	public MapService(Retrofit retrofit) {
		super(retrofit, IMapService.class);
	}

	/**
	 * Create a request manager for /maps/ endpoint.
	 *
	 * @return	request manager
	 */
	public GetMapsRequest.Manager maps() {
		return new GetMapsRequest.Manager(this.service);
	}

	/**
	 * Create a request manager for /maps/.../ endpoint.
	 *
	 * @return	request manager
	 */
	public GetMapByIdRequest.Manager mapById() {
		return new GetMapByIdRequest.Manager(this.service);
	}

	/**
	 * Create a request manager for /maps/name/.../ endpoint.
	 *
	 * @return	request manager
	 */
	public GetMapByNameRequest.Manager mapByName() {
		return new GetMapByNameRequest.Manager(this.service);
	}
}
