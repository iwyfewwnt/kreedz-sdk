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
import io.github.iwyfewwnt.kreedzsdk.client.services.requests.status.*;
import io.github.iwyfewwnt.kreedzsdk.clientapi.IKreedzService;
import io.github.iwyfewwnt.kreedzsdk.clientapi.IStatusService;
import io.github.iwyfewwnt.uwretrofit.services.impl.RetrofitServiceWrapper;
import retrofit2.Retrofit;

/**
 * A status service.
 */
@SuppressWarnings("unused")
@AutoService(IKreedzService.class)
public final class StatusService extends RetrofitServiceWrapper<IStatusService> implements IKreedzService {

	/**
	 * Initialize a {@link StatusService} instance
	 *
	 * @param retrofit	retrofit instance
	 */
	public StatusService(Retrofit retrofit) {
		super(retrofit, IStatusService.class);
	}

	/**
	 * Create a request manager for /summary.json/ endpoint.
	 *
	 * @return	request manager
	 */
	public GetStatusSummaryRequest.Manager summary() {
		return new GetStatusSummaryRequest.Manager(this.service);
	}

	/**
	 * Create a request manager for /status.json/ endpoint.
	 *
	 * @return	request manager
	 */
	public GetStatusRequest.Manager status() {
		return new GetStatusRequest.Manager(this.service);
	}

	/**
	 * Create a request manager for /components.json/ endpoint.
	 *
	 * @return	request manager
	 */
	public GetStatusComponentsRequest.Manager components() {
		return new GetStatusComponentsRequest.Manager(this.service);
	}

	/**
	 * Create a request manager for /incidents/unresolved.json/ endpoint.
	 *
	 * @return	request manager
	 */
	public GetStatusUnresolvedIncidentsRequest.Manager unresolvedIncidents() {
		return new GetStatusUnresolvedIncidentsRequest.Manager(this.service);
	}

	/**
	 * Create a request manager for /incidents.json/ endpoint.
	 *
	 * @return	request manager
	 */
	public GetStatusRecentIncidentsRequest.Manager recentIncidents() {
		return new GetStatusRecentIncidentsRequest.Manager(this.service);
	}

	/**
	 * Create a request manager for /scheduled-maintenances/upcoming.json/ endpoint.
	 *
	 * @return	request manager
	 */
	public GetStatusUpcomingScheduledIncidentsRequest.Manager upcomingScheduledIncidents() {
		return new GetStatusUpcomingScheduledIncidentsRequest.Manager(this.service);
	}

	/**
	 * Create a request manager for /scheduled-maintenances/active.json/ endpoint.
	 *
	 * @return	request manager
	 */
	public GetStatusActiveScheduledIncidentsRequest.Manager activeScheduledIncidents() {
		return new GetStatusActiveScheduledIncidentsRequest.Manager(this.service);
	}

	/**
	 * Create a request manager for /scheduled-maintenances.json/ endpoint.
	 *
	 * @return	request manager
	 */
	public GetStatusRecentScheduledIncidentsRequest.Manager recentScheduledIncidents() {
		return new GetStatusRecentScheduledIncidentsRequest.Manager(this.service);
	}
}
