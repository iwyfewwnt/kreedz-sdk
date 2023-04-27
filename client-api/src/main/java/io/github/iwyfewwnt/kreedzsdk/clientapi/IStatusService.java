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

package io.github.iwyfewwnt.kreedzsdk.clientapi;

import io.github.iwyfewwnt.kreedzsdk.clientapi.annotations.ServiceBaseUrl;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.status.responses.*;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * A status service interface.
 */
@ServiceBaseUrl("status.global-api.com/api/v2")
public interface IStatusService {

	/**
	 * GET request to /summary.json/ endpoint.
	 */
	@GET("summary.json")
	Call<StatusSummaryResponseEntity> getSummary();

	/**
	 * GET request to /status.json/ endpoint.
	 */
	@GET("status.json")
	Call<StatusResponseEntity> getStatus();

	/**
	 * GET request to /components.json/ endpoint.
	 */
	@GET("components.json")
	Call<StatusComponentsResponseEntity> getComponents();

	/**
	 * GET request to /incidents/unresolved.json/ endpoint.
	 */
	@GET("incidents/unresolved.json")
	Call<StatusIncidentsResponseEntity> getUnresolvedIncidents();

	/**
	 * GET request to /incidents.json/ endpoint.
	 */
	@GET("incidents.json")
	Call<StatusIncidentsResponseEntity> getRecentIncidents();

	/**
	 * GET request to /scheduled-maintenances/upcoming.json/ endpoint.
	 */
	@GET("scheduled-maintenances/upcoming.json")
	Call<StatusScheduledIncidentsResponseEntity> getUpcomingScheduledIncidents();

	/**
	 * GET request to /scheduled-maintenances/active.json/ endpoint.
	 */
	@GET("scheduled-maintenances/active.json")
	Call<StatusScheduledIncidentsResponseEntity> getActiveScheduledIncidents();

	/**
	 * GET request to /scheduled-maintenances.json/ endpoint.
	 */
	@GET("scheduled-maintenances.json")
	Call<StatusScheduledIncidentsResponseEntity> getRecentScheduledIncidents();
}
