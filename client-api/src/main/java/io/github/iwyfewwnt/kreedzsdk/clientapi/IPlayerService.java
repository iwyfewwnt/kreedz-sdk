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
import io.github.iwyfewwnt.kreedzsdk.structs.entities.PlayerEntity;
import io.github.iwyfewwnt.kreedzsdk.structs.types.EVersion;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;
import java.util.Set;

/**
 * A player service interface.
 */
public interface IPlayerService {

	/**
	 * GET request to /players/ endpoint.
	 */
	@MethodVersion(EVersion.V1_0)
	@GET("players")
	Call<List<PlayerEntity>> getPlayers(
			@Query("name") String playerName,
			@Query("is_banned") Boolean isBanned,
			@Query("total_records") Integer recordCount,
			@Query("steamid64_list") Set<Long> steamId64s,
			@Query("offset") Integer offset,
			@Query("limit") Integer limit
	);
}
