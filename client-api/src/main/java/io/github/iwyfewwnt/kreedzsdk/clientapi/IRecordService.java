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

import io.github.iwyfewwnt.kreedzsdk.clientapi.annotations.MethodApiVersion;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.RecordCountEntity;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.RecordEntity;
import io.github.iwyfewwnt.kreedzsdk.structs.types.EVersion;
import io.github.iwyfewwnt.kreedzsdk.structs.types.EMode;
import io.github.iwyfewwnt.kreedzsdk.structs.types.ERunType;
import io.github.iwyfewwnt.kreedzsdk.structs.types.ETickrate;
import org.joda.time.DateTime;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;
import java.util.Set;

/**
 * A record service interface.
 */
public interface IRecordService {

	/**
	 * GET request to /records/place/.../ endpoint.
	 */
	@MethodApiVersion(EVersion.V1_0)
	@GET("records/place/{id}")
	Call<Integer> getRecordPlaceById(
			@Path("id") Integer id
	);

	/**
	 * GET request to /records/top/ endpoint.
	 */
	@MethodApiVersion(EVersion.V1_0)
	@GET("records/top")
	Call<List<RecordEntity>> getRecordsTop(
			@Query("server_id") Integer serverId,
			@Query("steamid64") Long steamId64,
			@Query("map_id") Integer mapId,
			@Query("map_name") String mapName,
			@Query("tickrate") ETickrate tickrate,
			@Query("overall") Boolean isOverall,
			@Query("stage") Integer stage,
			@Query("modes_list") String modeName,
			@Query("has_teleports") ERunType runType,
			@Query("player_name") String playerName,
			@Query("offset") Integer offset,
			@Query("limit") Integer limit
	);

	/**
	 * GET request to /records/top/world_records/ endpoint.
	 */
	@MethodApiVersion(EVersion.V1_0)
	@GET("records/top/world_records")
	Call<List<RecordCountEntity>> getWorldRecordsTop(
			@Query("ids") Set<Integer> ids,
			@Query("map_ids") Set<Integer> mapIds,
			@Query("stages") Set<Integer> stages,
			@Query("modes_ids") Set<EMode> modes,
			@Query("tickrates") Set<ETickrate> tickrates,
			@Query("has_teleports") ERunType runType,
			@Query("mapTag") String mapTag,
			@Query("offset") Integer offset,
			@Query("limit") Integer limit
	);

	/**
	 * GET request to /records/top/recent/ endpoint.
	 */
	@MethodApiVersion(EVersion.V1_0)
	@GET("records/top/recent")
	Call<List<RecordEntity>> getRecentRecords(
			@Query("steamid64") Long steamId64,
			@Query("map_id") Integer mapId,
			@Query("map_name") String mapName,
			@Query("has_teleports") ERunType runType,
			@Query("tickrate") ETickrate tickrate,
			@Query("stage") Integer stage,
			@Query("modes_list") String modeName,
			@Query("place_top_at_least") Integer minPlace,
			@Query("place_top_overall_at_least") Integer minOverallPlace,
			@Query("created_since") DateTime createdSinceDate,
			@Query("offset") Integer offset,
			@Query("limit") Integer limit
	);

	/**
	 * GET request to /records/../ endpoint.
	 */
	@MethodApiVersion(EVersion.V2_0)
	@GET("records/{id}")
	Call<RecordEntity> getRecordById(
			@Path("id") Integer id
	);
}
