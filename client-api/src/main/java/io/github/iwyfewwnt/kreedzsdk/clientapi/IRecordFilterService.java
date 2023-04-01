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
import io.github.iwyfewwnt.kreedzsdk.structs.entities.RecordDistributionEntity;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.RecordFilterEntity;
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
 * A record filter service interface.
 */
public interface IRecordFilterService {

	/**
	 * GET request to /record_filters/ endpoint.
	 */
	@MethodApiVersion(EVersion.V1_0)
	@GET("record_filters")
	Call<List<RecordFilterEntity>> getRecordFilters(
			@Query("ids") Set<Integer> ids,
			@Query("map_ids") Set<Integer> mapIds,
			@Query("stages") Set<Integer> stages,
			@Query("mode_ids") Set<EMode> modes,
			@Query("tickrates") Set<ETickrate> tickrates,
			@Query("has_teleports") ERunType runType,
			@Query("offset") Integer offset,
			@Query("limit") Integer limit
	);

	/**
	 * GET request to /record_filters/distributions/ endpoint.
	 */
	@MethodApiVersion(EVersion.V1_0)
	@GET("record_filters/distributions")
	Call<List<RecordDistributionEntity>> getRecordFilterDistributions(
			@Query("ids") Set<Integer> ids,
			@Query("map_ids") Set<Integer> mapIds,
			@Query("stages") Set<Integer> stages,
			@Query("mode_ids") Set<EMode> modes,
			@Query("tickrates") Set<ETickrate> tickrates,
			@Query("has_teleports") ERunType runType,
			@Query("offset") Integer offset,
			@Query("limit") Integer limit
	);
}
