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
import org.joda.time.DateTime;
import retrofit2.Call;

import java.util.List;
import java.util.Objects;

/**
 * A request for /records/top/recent/ endpoint.
 */
public final class GetRecentRecordsRequest extends GetRecordsRequest implements Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = GetRecentRecordsRequest.class.getSimpleName();

	/**
	 * A left place threshold.
	 */
	private final Integer minPlace;

	/**
	 * A left overall place threshold.
	 */
	private final Integer minOverallPlace;

	/**
	 * A created since date.
	 */
	private final DateTime createdSinceDate;

	// Should be "this#updatedSinceDate" as well,
	//  but there is nothing on the kreedz API swagger page.

	/**
	 * A {@link GetRecentRecordsRequest#hashCode()} cache.
	 */
	private transient Integer hashCodeCache;

	/**
	 * A {@link GetRecentRecordsRequest#toString()} cache.
	 */
	private transient String stringCache;

	/**
	 * Initialize a {@link GetRecentRecordsRequest} instance.
	 *
	 * @param steamId64			type-64 person identifier
	 * @param mapId				map identifier
	 * @param mapName			map name
	 * @param runType			run type
	 * @param tickrate			tickrate
	 * @param stage				stage identifier
	 * @param modeName			mode name
	 * @param offset			offset
	 * @param limit				limit
	 * @param minPlace			left place threshold
	 * @param minOverallPlace	left overall place threshold
	 * @param createdSinceDate	crated since date
	 */
	private GetRecentRecordsRequest(
			Long steamId64,
			Integer mapId,
			String mapName,
			ERunType runType,
			ETickrate tickrate,
			Integer stage,
			String modeName,
			Integer offset,
			Integer limit,
			Integer minPlace,
			Integer minOverallPlace,
			DateTime createdSinceDate
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

		this.minPlace = minPlace;
		this.minOverallPlace = minOverallPlace;
		this.createdSinceDate = createdSinceDate;
	}

	/**
	 * Initialize a {@link GetRecentRecordsRequest} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private GetRecentRecordsRequest(GetRecentRecordsRequest that) {
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
				that.minPlace,
				that.minOverallPlace,
				that.createdSinceDate
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

		GetRecentRecordsRequest that = (GetRecentRecordsRequest) obj;

		return super.equals(that)
				&& Objects.equals(this.minPlace, that.minPlace)
				&& Objects.equals(this.minOverallPlace, that.minOverallPlace)
				&& Objects.equals(this.createdSinceDate, that.createdSinceDate);
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
						this.minPlace,
						this.minOverallPlace,
						this.createdSinceDate
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
				+ ", minPlace=" + this.minPlace
				+ ", minOverallPlace=" + this.minOverallPlace
				+ ", createdSinceDate=" + this.createdSinceDate
				+ "]");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetRecentRecordsRequest clone() {
		return new GetRecentRecordsRequest(this);
	}

	/**
	 * A request manager for /records/top/recent/ endpoint.
	 */
	@SuppressWarnings("unused")
	public static final class Manager extends BaseGetRecordsRequestManager<Manager, GetRecentRecordsRequest> {

		/**
		 * A record service.
		 */
		private final IRecordService recordService;

		/**
		 * A left place threshold.
		 */
		private Integer minPlace;

		/**
		 * A left overall place threshold.
		 */
		private Integer minOverallPlace;

		/**
		 * A created since date.
		 */
		private DateTime createdSinceDate;

		/**
		 * Initialize a {@link GetRecentRecordsRequest.Manager} instance.
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
		public GetRecentRecordsRequest build() {
			Long steamId64 = UwObject.ifNotNull(this.steamId, SteamId::toSteam64OrNull);
			String modeName = UwObject.ifNotNull(this.mode, EMode::getApiName);

			return new GetRecentRecordsRequest(
					steamId64,
					this.mapId,
					this.mapName,
					this.runType,
					this.tickrate,
					this.stage,
					modeName,
					this.offset,
					this.limit,
					this.minPlace,
					this.minOverallPlace,
					this.createdSinceDate
			);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Call<List<RecordEntity>> call(GetRecentRecordsRequest request) {
			return this.recordService.getRecentRecords(
					request.steamId64,
					request.mapId,
					request.mapName,
					request.runType,
					request.tickrate,
					request.stage,
					request.modeName,
					request.minPlace,
					request.minOverallPlace,
					request.createdSinceDate,
					request.offset,
					request.limit
			);
		}

		/**
		 * Set this left place threshold.
		 *
		 * @param minPlace	left place threshold, may be null
		 * @return			this instance
		 */
		public Manager setMinPlace(Integer minPlace) {
			this.minPlace = minPlace;
			return this;
		}

		/**
		 * Set this left overall place threshold.
		 *
		 * @param minOverallPlace	left overall place threshold, may be null
		 * @return					this instance
		 */
		public Manager setMinOverallPlace(Integer minOverallPlace) {
			this.minOverallPlace = minOverallPlace;
			return this;
		}

		/**
		 * Set this created since date.
		 *
		 * @param createdSinceDate	created since date, may be null
		 * @return					this instance
		 */
		public Manager setCreatedSinceDate(DateTime createdSinceDate) {
			this.createdSinceDate = createdSinceDate;
			return this;
		}
	}
}
