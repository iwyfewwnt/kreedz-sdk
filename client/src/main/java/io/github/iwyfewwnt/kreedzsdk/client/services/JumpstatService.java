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
import io.github.iwyfewwnt.kreedzsdk.clientapi.IJumpstatService;
import io.github.iwyfewwnt.kreedzsdk.clientapi.IKreedzService;
import io.github.iwyfewwnt.kreedzsdk.client.services.requests.jumpstat.GetJumpstatsRequest;
import io.github.iwyfewwnt.kreedzsdk.client.services.requests.jumpstat.GetJumpstatsTopRequest;
import io.github.iwyfewwnt.uwretrofit.services.impl.RetrofitServiceWrapper;
import retrofit2.Retrofit;

/**
 * A jumpstat service.
 */
@SuppressWarnings("unused")
@AutoService(IKreedzService.class)
public final class JumpstatService extends RetrofitServiceWrapper<IJumpstatService> implements IKreedzService {

	/**
	 * Initialize a {@link JumpstatService} instance.
	 *
	 * @param retrofit	retrofit instance
	 */
	public JumpstatService(Retrofit retrofit) {
		super(retrofit, IJumpstatService.class);
	}

	/**
	 * Create a request manager for /jumpstats/ endpoint.
	 *
	 * @return	request manager
	 */
	public GetJumpstatsRequest.Manager jumpstats() {
		return new GetJumpstatsRequest.Manager(this.service);
	}

	/**
	 * Create a request manager for /jumpstats/.../top/ endpoint.
	 *
	 * @return	request manager
	 */
	public GetJumpstatsTopRequest.Manager jumpstatsTop() {
		return new GetJumpstatsTopRequest.Manager(this.service);
	}
}
