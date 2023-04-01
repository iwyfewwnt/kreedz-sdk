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

import io.github.iwyfewwnt.kreedzsdk.clientapi.IRecordService;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.RecordEntity;
import io.github.iwyfewwnt.kreedzsdk.structs.types.EMode;
import io.github.iwyfewwnt.kreedzsdk.structs.types.ERunType;
import io.github.iwyfewwnt.kreedzsdk.structs.types.ETickrate;
import io.github.iwyfewwnt.steamid.SteamId;
import io.github.iwyfewwnt.uwutils.UwObject;
import retrofit2.Call;

import java.util.List;
import java.util.Objects;

/**
 * A request for /records/top/ endpoint.
 */
public final class GetRecordsTopRequest extends GetRecordsRequest implements Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = GetRecordsTopRequest.class.getSimpleName();

	/**
	 * A server identifier.
	 */
	private final Integer serverId;

	/**
	 * An "isOverall" boolean value.
	 */
	private final Boolean isOverall;

	/**
	 * A player name.
	 */
	private final String playerName;

	/**
	 * A {@link GetRecordsTopRequest#hashCode()} cache.
	 */
	private transient Integer hashCodeCache;

	/**
	 * A {@link GetRecordsTopRequest#toString()} cache.
	 */
	private transient String stringCache;

	/**
	 * Initialize a {@link GetRecordsTopRequest} instance.
	 *
	 * @param steamId64		type-64 person identifier
	 * @param mapId			map identifier
	 * @param mapName		map name
	 * @param runType		run type
	 * @param tickrate		tickrate
	 * @param stage			stage identifier
	 * @param modeName		mode name
	 * @param offset		offset
	 * @param limit			limit
	 * @param serverId		server identifier
	 * @param isOverall		"isOverall" boolean value
	 * @param playerName	player name
	 */
	private GetRecordsTopRequest(
			Long steamId64,
			Integer mapId,
			String mapName,
			ERunType runType,
			ETickrate tickrate,
			Integer stage,
			String modeName,
			Integer offset,
			Integer limit,
			Integer serverId,
			Boolean isOverall,
			String playerName
	) {
		super(
				steamId64,
				mapId,
				mapName,
				runType,
				tickrate,
				stage,
				modeName,
				offset,
				limit
		);

		this.serverId = serverId;
		this.isOverall = isOverall;
		this.playerName = playerName;
	}

	/**
	 * Initialize a {@link GetRecordsTopRequest} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private GetRecordsTopRequest(GetRecordsTopRequest that) {
		this(
				that.steamId64,
				that.mapId,
				that.mapName,
				that.runType,
				that.tickrate,
				that.stage,
				that.modeName,
				that.offset,
				that.limit,
				that.serverId,
				that.isOverall,
				that.playerName
		);

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}

		GetRecordsTopRequest that = (GetRecordsTopRequest) obj;

		return super.equals(that)
				&& Objects.equals(this.serverId, that.serverId)
				&& Objects.equals(this.isOverall, that.isOverall)
				&& Objects.equals(this.playerName, that.playerName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		if (this.hashCodeCache != null) {
			return this.hashCodeCache;
		}

		return (this.hashCodeCache
				= Objects.hash(
						super.hashCode(),
						this.serverId,
						this.isOverall,
						this.playerName
				)
		);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		if (this.stringCache != null) {
			return this.stringCache;
		}

		return (this.stringCache = SIMPLE_NAME + "["
				+ "steamId64=" + this.steamId64
				+ ", mapId=" + this.mapId
				+ ", mapName=\"" + this.mapName + "\""
				+ ", runType=" + this.runType
				+ ", tickrate=" + this.tickrate
				+ ", stage=" + this.stage
				+ ", modeName=\"" + this.modeName + "\""
				+ ", offset=" + this.offset
				+ ", limit=" + this.limit
				+ ", serverId=" + this.serverId
				+ ", isOverall=" + this.isOverall
				+ ", playerName=\"" + this.playerName + "\""
				+ "]");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetRecordsTopRequest clone() {
		return new GetRecordsTopRequest(this);
	}

	/**
	 * A request manager for /records/top/ endpoint.
	 */
	@SuppressWarnings("unused")
	public static final class Manager extends BaseGetRecordsRequestManager<Manager, GetRecordsTopRequest> {

		/**
		 * A record service.
		 */
		private final IRecordService recordService;

		/**
		 * A server identifier.
		 */
		private Integer serverId;

		/**
		 * A player name.
		 */
		private String playerName;

		/**
		 * Initialize a {@link GetRecordsTopRequest.Manager} instance.
		 *
		 * @param recordService		record service
		 */
		public Manager(IRecordService recordService) {
			if (recordService == null) {
				throw new IllegalArgumentException("Record service mustn't be <null>");
			}

			this.recordService = recordService;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public GetRecordsTopRequest build() {
			Long steamId64 = UwObject.applyIfNotNull(this.steamId, SteamId::toSteam64OrNull);
			String modeName = UwObject.applyIfNotNull(this.mode, EMode::getApiName);

			// "isOverall" should always be set to a value to avoid undefined behavior.
			Boolean isOverall = this.runType == null || this.runType == ERunType.NUB;

			return new GetRecordsTopRequest(
					steamId64,
					this.mapId,
					this.mapName,
					this.runType,
					this.tickrate,
					this.stage,
					modeName,
					this.offset,
					this.limit,
					this.serverId,
					isOverall,
					this.playerName
			);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Call<List<RecordEntity>> call(GetRecordsTopRequest request) {
			return this.recordService.getRecordsTop(
					request.serverId,
					request.steamId64,
					request.mapId,
					request.mapName,
					request.tickrate,
					request.isOverall,
					request.stage,
					request.modeName,
					request.runType,
					request.playerName,
					request.offset,
					request.limit
			);
		}

		/**
		 * Set this server identifier.
		 *
		 * @param serverId	server identifier, may be null
		 * @return			this instance
		 */
		public Manager setServerId(Integer serverId) {
			this.serverId = serverId;
			return this;
		}

		/**
		 * Set this player name.
		 *
		 * @param playerName	player name, may be null
		 * @return				this instance
		 */
		public Manager setPlayerName(String playerName) {
			this.playerName = playerName;
			return this;
		}
	}
}
