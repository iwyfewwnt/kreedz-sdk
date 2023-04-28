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

package io.github.iwyfewwnt.kreedzsdk.client.services.requests.playerrank;

import io.github.iwyfewwnt.kreedzsdk.client.internal.UKreedzCommon;
import io.github.iwyfewwnt.kreedzsdk.clientapi.IPlayerRankService;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.PlayerRankEntity;
import io.github.iwyfewwnt.kreedzsdk.structs.types.EMode;
import io.github.iwyfewwnt.kreedzsdk.structs.types.ERunType;
import io.github.iwyfewwnt.kreedzsdk.structs.types.ETickrate;
import io.github.iwyfewwnt.steamid.SteamId;
import io.github.iwyfewwnt.uwretrofit.services.requests.IRequest;
import io.github.iwyfewwnt.uwretrofit.services.requests.impl.AbstractRequestManager;
import io.github.iwyfewwnt.uwutils.UwSet;
import retrofit2.Call;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A request for /player_ranks/ endpoint.
 */
@SuppressWarnings("MethodDoesntCallSuperMethod")
public final class GetPlayerRanksRequest implements IRequest, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = GetPlayerRanksRequest.class.getSimpleName();

	/**
	 * A left points threshold.
	 */
	private final Integer pointsGreaterThan;

	/**
	 * A left average points threshold.
	 */
	private final Integer avgPointsGreaterThan;

	/**
	 * A left rating threshold.
	 */
	private final Integer ratingGreaterThan;

	/**
	 * A left finish count threshold.
	 */
	private final Integer finishCountGreaterThan;

	/**
	 * A set of type-64 person identifiers.
	 */
	private final Set<Long> steamId64s;

	/**
	 * A set of record filter identifiers.
	 */
	private final Set<Integer> recordFilterIds;

	/**
	 * A set of map identifiers.
	 */
	private final Set<Integer> mapIds;

	/**
	 * A set of stage identifiers.
	 */
	private final Set<Integer> stages;

	/**
	 * A set of modes.
	 */
	private final Set<EMode> modes;

	/**
	 * A set of tickrates.
	 */
	private final Set<ETickrate> tickrates;

	/**
	 * A run type.
	 */
	private final ERunType runType;

	/**
	 * A map tag.
	 */
	private final String mapTag;

	/**
	 * An offset.
	 */
	private final Integer offset;

	/**
	 * A limit.
	 */
	private final Integer limit;

	/**
	 * A {@link GetPlayerRanksRequest#hashCode()} cache.
	 */
	private transient Integer hashCodeCache;

	/**
	 * A {@link GetPlayerRanksRequest#toString()} cache.
	 */
	private transient String stringCache;

	/**
	 * Initialize a {@link GetPlayerRanksRequest} instance.
	 *
	 * @param pointsGreaterThan			left points threshold
	 * @param avgPointsGreaterThan		left average points threshold
	 * @param ratingGreaterThan			left rating threshold
	 * @param finishCountGreaterThan	left finish count threshold
	 * @param steamId64s				set of type-64 person identifiers
	 * @param recordFilterIds			set of record filter identifiers
	 * @param mapIds					set of map identifiers
	 * @param stages					set of stage identifiers
	 * @param modes						set of modes
	 * @param tickrates					set of tickrates
	 * @param runType					run type
	 * @param mapTag					map tag
	 * @param offset					offset
	 * @param limit						limit
	 */
	private GetPlayerRanksRequest(
			Integer pointsGreaterThan,
			Integer avgPointsGreaterThan,
			Integer ratingGreaterThan,
			Integer finishCountGreaterThan,
			Set<Long> steamId64s,
			Set<Integer> recordFilterIds,
			Set<Integer> mapIds,
			Set<Integer> stages,
			Set<EMode> modes,
			Set<ETickrate> tickrates,
			ERunType runType,
			String mapTag,
			Integer offset,
			Integer limit
	) {
		steamId64s = UwSet.toUnmodifiable(steamId64s);
		recordFilterIds = UwSet.toUnmodifiable(recordFilterIds);
		mapIds = UwSet.toUnmodifiable(mapIds);
		stages = UwSet.toUnmodifiable(stages);
		modes = UwSet.toUnmodifiable(modes);
		tickrates = UwSet.toUnmodifiable(tickrates);

		this.pointsGreaterThan = pointsGreaterThan;
		this.avgPointsGreaterThan = avgPointsGreaterThan;
		this.ratingGreaterThan = ratingGreaterThan;
		this.finishCountGreaterThan = finishCountGreaterThan;
		this.steamId64s = steamId64s;
		this.recordFilterIds = recordFilterIds;
		this.mapIds = mapIds;
		this.stages = stages;
		this.modes = modes;
		this.tickrates = tickrates;
		this.runType = runType;
		this.mapTag = mapTag;
		this.offset = offset;
		this.limit = limit;
	}

	/**
	 * Initialize a {@link GetPlayerRanksRequest} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private GetPlayerRanksRequest(GetPlayerRanksRequest that) {
		this(
				that.pointsGreaterThan,
				that.avgPointsGreaterThan,
				that.ratingGreaterThan,
				that.finishCountGreaterThan,
				that.steamId64s,
				that.recordFilterIds,
				that.mapIds,
				that.stages,
				that.modes,
				that.tickrates,
				that.runType,
				that.mapTag,
				that.offset,
				that.limit
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

		GetPlayerRanksRequest that = (GetPlayerRanksRequest) obj;

		return Objects.equals(this.pointsGreaterThan, that.pointsGreaterThan)
				&& Objects.equals(this.avgPointsGreaterThan, that.avgPointsGreaterThan)
				&& Objects.equals(this.ratingGreaterThan, that.ratingGreaterThan)
				&& Objects.equals(this.finishCountGreaterThan, that.finishCountGreaterThan)
				&& Objects.equals(this.steamId64s, that.steamId64s)
				&& Objects.equals(this.recordFilterIds, that.recordFilterIds)
				&& Objects.equals(this.mapIds, that.mapIds)
				&& Objects.equals(this.stages, that.stages)
				&& Objects.equals(this.modes, that.modes)
				&& Objects.equals(this.tickrates, that.tickrates)
				&& this.runType == that.runType
				&& Objects.equals(this.mapTag, that.mapTag)
				&& Objects.equals(this.offset, that.offset)
				&& Objects.equals(this.limit, that.limit);
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
						this.pointsGreaterThan,
						this.avgPointsGreaterThan,
						this.ratingGreaterThan,
						this.finishCountGreaterThan,
						this.steamId64s,
						this.recordFilterIds,
						this.mapIds,
						this.stages,
						this.modes,
						this.tickrates,
						this.runType,
						this.mapTag,
						this.offset,
						this.limit
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
				+ "pointsGreaterThan=" + this.pointsGreaterThan
				+ ", avgPointsGreaterThan=" + this.avgPointsGreaterThan
				+ ", ratingGreaterThan=" + this.ratingGreaterThan
				+ ", finishCountGreaterThan=" + this.finishCountGreaterThan
				+ ", steamId64s=" + this.steamId64s
				+ ", recordFilterIds=" + this.recordFilterIds
				+ ", mapIds=" + this.mapIds
				+ ", stages=" + this.stages
				+ ", modes=" + this.modes
				+ ", tickrates=" + this.tickrates
				+ ", runType=" + this.runType
				+ ", mapTag=\"" + this.mapTag + "\""
				+ ", offset=" + this.offset
				+ ", limit=" + this.limit
				+ "]");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetPlayerRanksRequest clone() {
		return new GetPlayerRanksRequest(this);
	}

	/**
	 * A request manager for /player_ranks/ endpoint.
	 */
	@SuppressWarnings("unused")
	public static final class Manager extends AbstractRequestManager<GetPlayerRanksRequest, List<PlayerRankEntity>> {

		/**
		 * A player rank service.
		 */
		private final IPlayerRankService playerRankService;

		/**
		 * A left points threshold.
		 */
		private Integer pointsGreaterThan;

		/**
		 * A left average points threshold.
		 */
		private Integer avgPointsGreaterThan;

		/**
		 * A left rating threshold.
		 */
		private Integer ratingGreaterThan;

		/**
		 * A left finish count threshold.
		 */
		private Integer finishCountGreaterThan;

		/**
		 * A set of person identifiers.
		 */
		private Set<SteamId> steamIds;

		/**
		 * A set of record filter identifiers.
		 */
		private Set<Integer> recordFilterIds;

		/**
		 * A set of map identifiers.
		 */
		private Set<Integer> mapIds;

		/**
		 * A set of stage identifiers.
		 */
		private Set<Integer> stages;

		/**
		 * A set of modes.
		 */
		private Set<EMode> modes;

		/**
		 * A set of tickrates.
		 */
		private Set<ETickrate> tickrates;

		/**
		 * A run type.
		 */
		private ERunType runType;

		/**
		 * A map tag.
		 */
		private String mapTag;

		/**
		 * An offset.
		 */
		private Integer offset;

		/**
		 * A limit.
		 */
		private Integer limit;

		/**
		 * Initialize a {@link GetPlayerRanksRequest.Manager} instance.
		 *
		 * @param playerRankService		player rank service
		 */
		public Manager(IPlayerRankService playerRankService) {
			if (playerRankService == null) {
				throw new IllegalArgumentException("Player rank service mustn't be <null>");
			}

			this.playerRankService = playerRankService;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public GetPlayerRanksRequest build() {
			Set<Long> steamId64s = UKreedzCommon.mapSteamIdsToId64Set(this.steamIds);

			return new GetPlayerRanksRequest(
					this.pointsGreaterThan,
					this.avgPointsGreaterThan,
					this.ratingGreaterThan,
					this.finishCountGreaterThan,
					steamId64s,
					this.recordFilterIds,
					this.mapIds,
					this.stages,
					this.modes,
					this.tickrates,
					this.runType,
					this.mapTag,
					this.offset,
					this.limit
			);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Call<List<PlayerRankEntity>> call(GetPlayerRanksRequest request) {
			return this.playerRankService.getPlayerRanks(
					request.pointsGreaterThan,
					request.avgPointsGreaterThan,
					request.ratingGreaterThan,
					request.finishCountGreaterThan,
					request.steamId64s,
					request.recordFilterIds,
					request.mapIds,
					request.stages,
					request.modes,
					request.tickrates,
					request.runType,
					request.mapTag,
					request.offset,
					request.limit
			);
		}

		/**
		 * Set this left points threshold.
		 *
		 * @param pointsGreaterThan		left points threshold, may be null
		 * @return						this instance
		 */
		public Manager setPointsGreaterThan(Integer pointsGreaterThan) {
			this.pointsGreaterThan = pointsGreaterThan;
			return this;
		}

		/**
		 * Set this left average points threshold.
		 *
		 * @param avgPointsGreaterThan	left average points threshold, may be null
		 * @return						this instance
		 */
		public Manager setAvgPointsGreaterThan(Integer avgPointsGreaterThan) {
			this.avgPointsGreaterThan = avgPointsGreaterThan;
			return this;
		}

		/**
		 * Set this left rating threshold.
		 *
		 * @param ratingGreaterThan		left rating threshold, may be null
		 * @return						this instance
		 */
		public Manager setRatingGreaterThan(Integer ratingGreaterThan) {
			this.ratingGreaterThan = ratingGreaterThan;
			return this;
		}

		/**
		 * Set this left finish count threshold.
		 *
		 * @param finishCountGreaterThan	left finish count threshold, may be null
		 * @return							this instance
		 */
		public Manager setFinishCountGreaterThan(Integer finishCountGreaterThan) {
			this.finishCountGreaterThan = finishCountGreaterThan;
			return this;
		}

		/**
		 * Set this set of person identifiers.
		 *
		 * @param steamIds	set of person identifiers, may be null
		 * @return			this instance
		 */
		public Manager setSteamIds(Set<SteamId> steamIds) {
			this.steamIds = steamIds;
			return this;
		}

		/**
		 * Set this set of person identifiers.
		 *
		 * @param steamIds	collection of person identifiers, may be null
		 * @return			this instance
		 */
		public Manager setSteamIds(Collection<SteamId> steamIds) {
			return this.setSteamIds(UwSet.toSetOrNull(steamIds));
		}

		/**
		 * Set this set of person identifiers.
		 *
		 * @param steamIds	array of person identifiers, may be null
		 * @return			this instance
		 */
		public Manager setSteamIds(SteamId... steamIds) {
			return this.setSteamIds(UwSet.toSetOrNull(steamIds));
		}

		/**
		 * Set this set of record filter identifiers.
		 *
		 * @param recordFilterIds	set of record filter identifiers, may be null
		 * @return					this instance
		 */
		public Manager setRecordFilterIds(Set<Integer> recordFilterIds) {
			this.recordFilterIds = recordFilterIds;
			return this;
		}

		/**
		 * Set this set of record filter identifiers.
		 *
		 * @param recordFilterIds	collection of record filter identifiers, may be null
		 * @return					this instance
		 */
		public Manager setRecordFilterIds(Collection<Integer> recordFilterIds) {
			return this.setRecordFilterIds(UwSet.toSetOrNull(recordFilterIds));
		}

		/**
		 * Set this set of record filter identifiers.
		 *
		 * @param recordFilterIds	array of record filter identifiers, may be null
		 * @return					this instance
		 */
		public Manager setRecordFilterIds(Integer... recordFilterIds) {
			return this.setRecordFilterIds(UwSet.toSetOrNull(recordFilterIds));
		}

		/**
		 * Set this set of record filter identifiers.
		 *
		 * @param recordFilterIds	stream of record filter identifiers, may be null
		 * @return					this instance
		 */
		public Manager setRecordFilterIds(Stream<Integer> recordFilterIds) {
			return this.setRecordFilterIds(UwSet.toSetOrNull(recordFilterIds));
		}

		/**
		 * Set this set of record filter identifiers.
		 *
		 * @param recordFilterIds	integer stream of record filter identifiers, may be null
		 * @return					this instance
		 */
		public Manager setRecordFilterIds(IntStream recordFilterIds) {
			return this.setRecordFilterIds(UwSet.toSetOrNull(recordFilterIds));
		}

		/**
		 * Set this set of map identifiers.
		 *
		 * @param mapIds	set of map identifiers, may be null
		 * @return			this instance
		 */
		public Manager setMapIds(Set<Integer> mapIds) {
			this.mapIds = mapIds;
			return this;
		}

		/**
		 * Set this set of map identifiers.
		 *
		 * @param mapIds	collection of map identifiers, may be null
		 * @return			this instance
		 */
		public Manager setMapIds(Collection<Integer> mapIds) {
			return this.setMapIds(UwSet.toSetOrNull(mapIds));
		}

		/**
		 * Set this set of map identifiers.
		 *
		 * @param mapIds	array of map identifiers, may be null
		 * @return			this instance
		 */
		public Manager setMapIds(Integer... mapIds) {
			return this.setMapIds(UwSet.toSetOrNull(mapIds));
		}

		/**
		 * Set this set of map identifiers.
		 *
		 * @param mapIds	stream of map identifiers, may be null
		 * @return			this instance
		 */
		public Manager setMapIds(Stream<Integer> mapIds) {
			return this.setMapIds(UwSet.toSetOrNull(mapIds));
		}

		/**
		 * Set this set of map identifiers.
		 *
		 * @param mapIds	integer stream of map identifiers, may be null
		 * @return			this instance
		 */
		public Manager setMapIds(IntStream mapIds) {
			return this.setMapIds(UwSet.toSetOrNull(mapIds));
		}

		/**
		 * Set this set of stage identifiers.
		 *
		 * @param stages	set of stage identifiers, may be null
		 * @return			this instance
		 */
		public Manager setStages(Set<Integer> stages) {
			this.stages = stages;
			return this;
		}

		/**
		 * Set this set of stage identifiers.
		 *
		 * @param stages	collection of stage identifiers, may be null
		 * @return			this instance
		 */
		public Manager setStages(Collection<Integer> stages) {
			return this.setStages(UwSet.toSetOrNull(stages));
		}

		/**
		 * Set this set of stage identifiers.
		 *
		 * @param stages	array of stage identifiers, may be null
		 * @return			this instance
		 */
		public Manager setStages(Integer... stages) {
			return this.setStages(UwSet.toSetOrNull(stages));
		}

		/**
		 * Set this set of stage identifiers.
		 *
		 * @param stages	stream of stage identifiers, may be null
		 * @return			this instance
		 */
		public Manager setStages(Stream<Integer> stages) {
			return this.setStages(UwSet.toSetOrNull(stages));
		}

		/**
		 * Set this set of stage identifiers.
		 *
		 * @param stages	integer stream of stage identifiers, may be null
		 * @return			this instance
		 */
		public Manager setStages(IntStream stages) {
			return this.setStages(UwSet.toSetOrNull(stages));
		}

		/**
		 * Set this set of modes.
		 *
		 * @param modes		set of modes, may be null
		 * @return			this instance
		 */
		public Manager setModes(Set<EMode> modes) {
			this.modes = modes;
			return this;
		}

		/**
		 * Set this set of modes.
		 *
		 * @param modes		collection of modes, may be null
		 * @return			this instance
		 */
		public Manager setModes(Collection<EMode> modes) {
			return this.setModes(UwSet.toSetOrNull(modes));
		}

		/**
		 * Set this set of modes.
		 *
		 * @param modes		array of modes, may be null
		 * @return			this instance
		 */
		public Manager setModes(EMode... modes) {
			return this.setModes(UwSet.toSetOrNull(modes));
		}

		/**
		 * Set this set of modes.
		 *
		 * @param modes		stream of modes, may be null
		 * @return			this instance
		 */
		public Manager setModes(Stream<EMode> modes) {
			return this.setModes(UwSet.toSetOrNull(modes));
		}

		/**
		 * Set this set of tickrates.
		 *
		 * @param tickrates		set of tickrates, may be null
		 * @return				this instance
		 */
		public Manager setTickrates(Set<ETickrate> tickrates) {
			this.tickrates = tickrates;
			return this;
		}

		/**
		 * Set this set of tickrates.
		 *
		 * @param tickrates		collection of tickrates, may be null
		 * @return				this instance
		 */
		public Manager setTickrates(Collection<ETickrate> tickrates) {
			return this.setTickrates(UwSet.toSetOrNull(tickrates));
		}

		/**
		 * Set this set of tickrates.
		 *
		 * @param tickrates		array of tickrates, may be null
		 * @return				this instance
		 */
		public Manager setTickrates(ETickrate... tickrates) {
			return this.setTickrates(UwSet.toSetOrNull(tickrates));
		}

		/**
		 * Set this set of tickrates.
		 *
		 * @param tickrates		stream of tickrates, may be null
		 * @return				this instance
		 */
		public Manager setTickrates(Stream<ETickrate> tickrates) {
			return this.setTickrates(UwSet.toSetOrNull(tickrates));
		}

		/**
		 * Set this run type.
		 *
		 * @param runType	run type, may be null
		 * @return			this instance
		 */
		public Manager setRunType(ERunType runType) {
			this.runType = runType;
			return this;
		}

		/**
		 * Set this map tag.
		 *
		 * @param mapTag	map tag, may be null
		 * @return			this instance
		 */
		public Manager setMapTag(String mapTag) {
			this.mapTag = mapTag;
			return this;
		}

		/**
		 * Set this offset.
		 *
		 * @param offset	offset, may be null
		 * @return			this instance
		 */
		public Manager setOffset(Integer offset) {
			this.offset = offset;
			return this;
		}

		/**
		 * Set this limit.
		 *
		 * @param limit		limit, may be null
		 * @return			this instance
		 */
		public Manager setLimit(Integer limit) {
			this.limit = limit;
			return this;
		}
	}
}
