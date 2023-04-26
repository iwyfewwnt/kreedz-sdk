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
import io.github.iwyfewwnt.kreedzsdk.structs.entities.PlayerRankEntity;
import io.github.iwyfewwnt.kreedzsdk.structs.types.EVersion;
import io.github.iwyfewwnt.kreedzsdk.structs.types.EMode;
import io.github.iwyfewwnt.kreedzsdk.structs.types.ERunType;
import io.github.iwyfewwnt.kreedzsdk.structs.types.ETickrate;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;
import java.util.Set;

/**
 * A player rank service interface.
 */
public interface IPlayerRankService {

	/**
	 * GET request to /player_ranks/ endpoint.
	 */
	@MethodVersion(EVersion.V1_0)
	@GET("player_ranks")
	Call<List<PlayerRankEntity>> getPlayerRanks(
			@Query("points_greater_than") Integer pointsGreaterThan,
			@Query("average_greater_than") Integer averageGreaterThan,
			@Query("rating_greater_than") Integer ratingGreaterThan,
			@Query("finishes_greater_than") Integer finishCountGreaterThan,
			@Query("steamid64s") Set<Long> steamId64s,
			@Query("record_filter_ids") Set<Integer> recordFilterIds,
			@Query("map_ids") Set<Integer> mapIds,
			@Query("stages") Set<Integer> stages,
			@Query("mode_ids") Set<EMode> modes,
			@Query("tickrates") Set<ETickrate> tickrates,
			@Query("has_teleports") ERunType runType,
			@Query("mapTag") String mapTag,
			@Query("offset") Integer offset,
			@Query("limit") Integer limit
	);
}
