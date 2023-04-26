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
import io.github.iwyfewwnt.kreedzsdk.structs.entities.JumpstatEntity;
import io.github.iwyfewwnt.kreedzsdk.structs.types.EVersion;
import io.github.iwyfewwnt.kreedzsdk.structs.types.EJumpType;
import org.joda.time.DateTime;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;
import java.util.Set;

/**
 * A jumpstat service interface.
 */
public interface IJumpstatService {

	/**
	 * GET request to /jumpstats/ endpoint.
	 */
	@MethodVersion(EVersion.V1_0)
	@GET("jumpstats")
	Call<List<JumpstatEntity>> getJumpstats(
			@Query("id") Integer id,
			@Query("server_id") Integer serverId,
			@Query("steamid64_list") Set<Long> steamId64s,
			@Query("jumptype") EJumpType jumpType,
			@Query("greater_than_distance") Float distanceGreaterThan,
			@Query("less_than_distance") Float distanceLessThan,
			@Query("is_msl") Boolean isMsl,
			@Query("is_crouch_bind") Boolean isCrouchBind,
			@Query("is_forward_bind") Boolean isForwardBind,
			@Query("is_crouch_boost") Boolean isCrouchBoost,
			@Query("updated_by_id") Long updaterId,
			@Query("created_since") DateTime createdSinceDate,
			@Query("updated_since") DateTime updatedSinceDate,
			@Query("offset") Integer offset,
			@Query("limit") Integer limit
	);

	/**
	 * GET request to /jumpstats/.../top/ endpoint.
	 */
	@MethodVersion(EVersion.V1_0)
	@GET("jumpstats/{jumpType}/top")
	Call<List<JumpstatEntity>> getJumpstatsTop(
			@Path("jumpType") EJumpType jumpType,
			@Query("id") Integer id,
			@Query("server_id") Integer serverId,
			@Query("steamid64_list") Set<Long> steamId64s,
			@Query("greater_than_distance") Float distanceGreaterThan,
			@Query("less_than_distance") Float distanceLessThan,
			@Query("is_msl") Boolean isMsl,
			@Query("is_crouch_bind") Boolean isCrouchBind,
			@Query("is_forward_bind") Boolean isForwardBind,
			@Query("is_crouch_boost") Boolean isCrouchBoost,
			@Query("updated_by_id") Long updaterId,
			@Query("created_since") DateTime createdSinceDate,
			@Query("updated_since") DateTime updatedSinceDate,
			@Query("offset") Integer offset,
			@Query("limit") Integer limit
	);
}
