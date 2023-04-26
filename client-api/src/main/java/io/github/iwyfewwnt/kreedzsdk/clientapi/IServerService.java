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

import io.github.iwyfewwnt.kreedzsdk.clientapi.annotations.MethodVersion;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.ServerEntity;
import io.github.iwyfewwnt.kreedzsdk.structs.types.EVersion;
import io.github.iwyfewwnt.kreedzsdk.structs.types.EApprovalStatus;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;
import java.util.Set;

/**
 * A server service interface.
 */
public interface IServerService {

	/**
	 * GET request to /servers/ endpoint.
	 */
	@MethodVersion(EVersion.V1_0)
	@GET("servers")
	Call<List<ServerEntity>> getServers(
			@Query("ids") Set<Integer> ids,
			@Query("port") Integer serverPort,
			@Query("ip") String serverIp,
			@Query("name") String serverName,
			@Query("owner_steamid64") Long ownersSteamId64,
			@Query("approval_status") EApprovalStatus approvalStatus,
			@Query("offset") Integer offset,
			@Query("limit") Integer limit
	);

	/**
	 * GET request to /servers/.../ endpoint.
	 */
	@MethodVersion(EVersion.V1_0)
	@GET("servers/{id}")
	Call<ServerEntity> getServerById(
			@Path("id") Integer id
	);
}
