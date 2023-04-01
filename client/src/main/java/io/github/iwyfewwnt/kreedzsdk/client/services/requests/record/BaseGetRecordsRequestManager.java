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

package io.github.iwyfewwnt.kreedzsdk.client.services.requests.record;

import io.github.iwyfewwnt.kreedzsdk.structs.entities.RecordEntity;
import io.github.iwyfewwnt.kreedzsdk.structs.types.EMode;
import io.github.iwyfewwnt.kreedzsdk.structs.types.ERunType;
import io.github.iwyfewwnt.kreedzsdk.structs.types.ETickrate;
import io.github.iwyfewwnt.steamid.SteamId;
import io.github.iwyfewwnt.uwretrofit.services.requests.impl.BaseRequestManager;

import java.util.List;

/**
 * A base request manager for /records/top/{@literal **}/ endpoints.
 *
 * @param <T>	manager type
 * @param <U>	request type
 */
@SuppressWarnings("unused")
public abstract class BaseGetRecordsRequestManager<T extends BaseGetRecordsRequestManager<T, U>, U extends GetRecordsRequest>
		extends BaseRequestManager<T, U, List<RecordEntity>> {

	/**
	 * A person identifier.
	 */
	protected SteamId steamId;

	/**
	 * A map identifier.
	 */
	protected Integer mapId;

	/**
	 * A map name.
	 */
	protected String mapName;

	/**
	 * A run type.
	 */
	protected ERunType runType;

	/**
	 * A tickrate.
	 */
	protected ETickrate tickrate;

	/**
	 * A stage identifier.
	 */
	protected Integer stage;

	/**
	 * A mode.
	 */
	protected EMode mode;

	/**
	 * An offset.
	 */
	protected Integer offset;

	/**
	 * A limit.
	 */
	protected Integer limit;

	/**
	 * Initialize a {@link BaseGetRecordsRequestManager} instance.
	 */
	BaseGetRecordsRequestManager() {
	}

	/**
	 * Set this person identifier.
	 *
	 * @param steamId	person identifier, may be null
	 * @return			this instance
	 */
	public final T setSteamId(SteamId steamId) {
		this.steamId = steamId;
		return this.asT;
	}

	/**
	 * Set this map identifier.
	 *
	 * @param mapId		map identifier, may be null
	 * @return			this instance
	 */
	public final T setMapId(Integer mapId) {
		this.mapId = mapId;
		return this.asT;
	}

	/**
	 * Set this map name.
	 *
	 * @param mapName	map name, may be null
	 * @return			this instance
	 */
	public final T setMapName(String mapName) {
		this.mapName = mapName;
		return this.asT;
	}

	/**
	 * Set this run type.
	 *
	 * @param runType	run type, may be null
	 * @return			this instance
	 */
	public final T setRunType(ERunType runType) {
		this.runType = runType;
		return this.asT;
	}

	/**
	 * Set this tickrate.
	 *
	 * @param tickrate	tickrate, may be null
	 * @return			this instance
	 */
	public final T setTickrate(ETickrate tickrate) {
		this.tickrate = tickrate;
		return this.asT;
	}

	/**
	 * Set this stage identifier.
	 *
	 * @param stage		stage identifier, may be null
	 * @return			this instance
	 */
	public final T setStage(Integer stage) {
		this.stage = stage;
		return this.asT;
	}

	/**
	 * Set this mode.
	 *
	 * @param mode	mode, may be null
	 * @return		this instance
	 */
	public final T setMode(EMode mode) {
		this.mode = mode;
		return this.asT;
	}

	/**
	 * Set this offset.
	 *
	 * @param offset	offset, may be null
	 * @return			this instance
	 */
	public final T setOffset(Integer offset) {
		this.offset = offset;
		return this.asT;
	}

	/**
	 * Set this limit.
	 *
	 * @param limit		limit, may be null
	 * @return			this instance
	 */
	public final T setLimit(Integer limit) {
		this.limit = limit;
		return this.asT;
	}
}
