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
import io.github.iwyfewwnt.kreedzsdk.clientapi.IServerService;
import io.github.iwyfewwnt.kreedzsdk.client.services.requests.server.GetServerByIdRequest;
import io.github.iwyfewwnt.kreedzsdk.client.services.requests.server.GetServersRequest;
import io.github.iwyfewwnt.uwretrofit.services.impl.RetrofitServiceWrapper;
import retrofit2.Retrofit;

/**
 * A server service.
 */
@SuppressWarnings("unused")
@AutoService(IKreedzService.class)
public final class ServerService extends RetrofitServiceWrapper<IServerService> implements IKreedzService {

	/**
	 * Initialize a {@link ServerService} instance.
	 *
	 * @param retrofit	retrofit instance
	 */
	public ServerService(Retrofit retrofit) {
		super(retrofit, IServerService.class);
	}

	/**
	 * Create a request manager for /servers/ endpoint.
	 *
	 * @return	request manager
	 */
	public GetServersRequest.Manager servers() {
		return new GetServersRequest.Manager(this.service);
	}

	/**
	 * Create a request manager for /servers/.../ endpoint.
	 *
	 * @return	request manager
	 */
	public GetServerByIdRequest.Manager serverById() {
		return new GetServerByIdRequest.Manager(this.service);
	}
}
