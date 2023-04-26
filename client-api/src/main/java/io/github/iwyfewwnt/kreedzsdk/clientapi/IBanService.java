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
import io.github.iwyfewwnt.kreedzsdk.structs.entities.BanEntity;
import io.github.iwyfewwnt.kreedzsdk.structs.types.EVersion;
import io.github.iwyfewwnt.kreedzsdk.structs.types.EBanType;
import org.joda.time.DateTime;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;
import java.util.Set;

/**
 * A ban service interface.
 */
public interface IBanService {

	/**
	 * GET request to /bans/ endpoint.
	 */
	@MethodVersion(EVersion.V1_0)
	@GET("bans")
	Call<List<BanEntity>> getBans(
			@Query("ban_types_list") Set<EBanType> banTypes,
			@Query("steamid64") Long steamId64,
			@Query("is_expired") Boolean isExpired,
			@Query("notes_contains") String notes,
			@Query("stats_contains") String stats,
			@Query("server_id") Integer serverId,
			@Query("created_since") DateTime createdSinceDate,
			@Query("updated_since") DateTime updatedSinceDate,
			@Query("offset") Integer offset,
			@Query("limit") Integer limit
	);
}
