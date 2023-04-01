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

import io.github.iwyfewwnt.kreedzsdk.structs.entities.map.MapInfoEntity;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

/**
 * A maps information service interface.
 */
@SuppressWarnings("unused")
public interface IMapInfoService {

	/**
	 * GET request to /main/maps.min.json/ endpoint.
	 */
	@GET("https://raw.githubusercontent.com/iwyfewwnt/maps-info/main/maps.min.json")
	Call<List<MapInfoEntity>> getMapsInfo();

	/**
	 * GET request to /main/global.min.json/ endpoint.
	 */
	@GET("https://raw.githubusercontent.com/iwyfewwnt/maps-info/main/global.min.json")
	Call<List<MapInfoEntity>> getGlobalMapsInfo();

	/**
	 * GET request to /main/non-global.min.json/ endpoint.
	 */
	@GET("https://raw.githubusercontent.com/iwyfewwnt/maps-info/main/non-global.min.json")
	Call<List<MapInfoEntity>> getNonGlobalMapsInfo();

	/**
	 * GET request to /main/uncompleted.min.json/ endpoint.
	 */
	@GET("https://raw.githubusercontent.com/iwyfewwnt/maps-info/main/uncompleted.min.json")
	Call<List<MapInfoEntity>> getUncompletedMapsInfo();
}
