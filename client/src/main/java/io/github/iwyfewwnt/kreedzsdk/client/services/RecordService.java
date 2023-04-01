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
import io.github.iwyfewwnt.kreedzsdk.client.services.requests.record.*;
import io.github.iwyfewwnt.kreedzsdk.clientapi.IKreedzService;
import io.github.iwyfewwnt.kreedzsdk.clientapi.IRecordService;
import io.github.iwyfewwnt.uwretrofit.services.impl.RetrofitServiceWrapper;
import retrofit2.Retrofit;

/**
 * A record service.
 */
@SuppressWarnings("unused")
@AutoService(IKreedzService.class)
public final class RecordService extends RetrofitServiceWrapper<IRecordService> implements IKreedzService {

	/**
	 * Initialize a {@link RecordService} instance.
	 *
	 * @param retrofit	retrofit instance
	 */
	public RecordService(Retrofit retrofit) {
		super(retrofit, IRecordService.class);
	}

	/**
	 * Create a request manager for /records/place/.../ endpoint.
	 *
	 * @return	request manager
	 */
	public GetRecordPlaceByIdRequest.Manager recordPlaceById() {
		return new GetRecordPlaceByIdRequest.Manager(this.service);
	}

	/**
	 * Create a request manager for /records/top/ endpoint.
	 *
	 * @return	request manager
	 */
	public GetRecordsTopRequest.Manager recordsTop() {
		return new GetRecordsTopRequest.Manager(this.service);
	}

	/**
	 * Create a request manager for /records/top/world_records/ endpoint.
	 *
	 * @return	request manager
	 */
	public GetWorldRecordsTopRequest.Manager worldRecordsTop() {
		return new GetWorldRecordsTopRequest.Manager(this.service);
	}

	/**
	 * Create a request manager for /records/top/recent/ endpoint.
	 *
	 * @return	request manager
	 */
	public GetRecentRecordsRequest.Manager recentRecords() {
		return new GetRecentRecordsRequest.Manager(this.service);
	}

	/**
	 * Create a request manager for /records/../ endpoint.
	 *
	 * @return	request manager
	 */
	public GetRecordByIdRequest.Manager recordById() {
		return new GetRecordByIdRequest.Manager(this.service);
	}
}
