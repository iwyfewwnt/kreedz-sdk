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

package io.github.iwyfewwnt.kreedzsdk.client.services.requests.player;

import io.github.iwyfewwnt.kreedzsdk.client.internal.UKreedzCommon;
import io.github.iwyfewwnt.kreedzsdk.clientapi.IPlayerService;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.PlayerEntity;
import io.github.iwyfewwnt.steamid.SteamId;
import io.github.iwyfewwnt.uwretrofit.services.requests.IRequest;
import io.github.iwyfewwnt.uwretrofit.services.requests.impl.AbstractRequestManager;
import io.github.iwyfewwnt.uwutils.UwSet;
import retrofit2.Call;

import java.util.*;
import java.util.stream.Stream;

/**
 * A request for /players/ endpoint.
 */
@SuppressWarnings({"MethodDoesntCallSuperMethod", "SynchronizeOnNonFinalField"})
public final class GetPlayersRequest implements IRequest, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = GetPlayersRequest.class.getSimpleName();

	/**
	 * A player name.
	 */
	private final String playerName;

	/**
	 * An "isBanned" boolean value.
	 */
	private final Boolean isBanned;

	/**
	 * A record count.
	 */
	private final Integer recordCount;

	/**
	 * A set of type-64 person identifiers.
	 */
	private final Set<Long> steamId64s;

	/**
	 * An offset.
	 */
	private final Integer offset;

	/**
	 * A limit.
	 */
	private final Integer limit;

	/**
	 * A {@link #hashCode()} cache.
	 */
	private transient volatile Integer hashCodeCache;

	/**
	 * A {@link #toString()} cache.
	 */
	private transient volatile String stringCache;

	/**
	 * A {@link #hashCodeCache} mutex.
	 */
	private transient Object hashCodeCacheMutex;

	/**
	 * A {@link #stringCache} mutex.
	 */
	private transient Object stringCacheMutex;

	/**
	 * Initialize this mutex objects.
	 */
	private void initMutexObjects() {
		this.hashCodeCacheMutex = new Object();
		this.stringCacheMutex = new Object();
	}

	/**
	 * Override the {@code #readResolve} method to set up
	 * the object cache mutexes after deserialization.
	 *
	 * @return	this instance
	 */
	private Object readResolve() {
		this.initMutexObjects();

		return this;
	}

	/**
	 * Initialize a {@link GetPlayersRequest} instance.
	 *
	 * @param playerName	player name
	 * @param isBanned		"isBanned" boolean value
	 * @param recordCount	record count
	 * @param steamId64s	set of type-64 person identifiers
	 * @param offset		offset
	 * @param limit			limit
	 */
	private GetPlayersRequest(
			String playerName,
			Boolean isBanned,
			Integer recordCount,
			Set<Long> steamId64s,
			Integer offset,
			Integer limit
	) {
		steamId64s = UwSet.toUnmodifiable(steamId64s);

		this.playerName = playerName;
		this.isBanned = isBanned;
		this.recordCount = recordCount;
		this.steamId64s = steamId64s;
		this.offset = offset;
		this.limit = limit;

		this.initMutexObjects();
	}

	/**
	 * Initialize a {@link GetPlayersRequest} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private GetPlayersRequest(GetPlayersRequest that) {
		this(
				that.playerName,
				that.isBanned,
				that.recordCount,
				that.steamId64s,
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

		GetPlayersRequest that = (GetPlayersRequest) obj;

		return Objects.equals(this.playerName, that.playerName)
				&& Objects.equals(this.isBanned, that.isBanned)
				&& Objects.equals(this.recordCount, that.recordCount)
				&& Objects.equals(this.steamId64s, that.steamId64s)
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

		synchronized (this.hashCodeCacheMutex) {
			if (this.hashCodeCache != null) {
				return this.hashCodeCache;
			}

			return (this.hashCodeCache
					= Objects.hash(
							this.playerName,
							this.isBanned,
							this.recordCount,
							this.steamId64s,
							this.offset,
							this.limit
					)
			);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		if (this.stringCache != null) {
			return this.stringCache;
		}

		synchronized (this.stringCacheMutex) {
			if (this.stringCache != null) {
				return this.stringCache;
			}

			return (this.stringCache = SIMPLE_NAME + "["
					+ "playerName=\"" + this.playerName + "\""
					+ ", isBanned=" + this.isBanned
					+ ", recordCount=" + this.recordCount
					+ ", steamId64s=" + this.steamId64s
					+ ", offset=" + this.offset
					+ ", limit=" + this.limit
					+ "]");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetPlayersRequest clone() {
		return new GetPlayersRequest(this);
	}

	/**
	 * A request manager for /players/ endpoint.
	 */
	@SuppressWarnings("unused")
	public static final class Manager extends AbstractRequestManager<GetPlayersRequest, List<PlayerEntity>> {

		/**
		 * A player service.
		 */
		private final IPlayerService playerService;

		/**
		 * A player name.
		 */
		private String playerName;

		/**
		 * An "isBanned" boolean value.
		 */
		private Boolean isBanned;

		/**
		 * A record count.
		 */
		private Integer recordCount;

		/**
		 * A person identifiers.
		 */
		private Set<SteamId> steamIds;

		/**
		 * An offset.
		 */
		private Integer offset;

		/**
		 * A limit.
		 */
		private Integer limit;

		/**
		 * Initialize a {@link GetPlayersRequest.Manager} instance.
		 *
		 * @param playerService		player service
		 */
		public Manager(IPlayerService playerService) {
			if (playerService == null) {
				throw new IllegalArgumentException("Player service mustn't be <null>");
			}

			this.playerService = playerService;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public GetPlayersRequest build() {
			Set<Long> steamId64s = UKreedzCommon.transformToSteamId64Set(this.steamIds);

			return new GetPlayersRequest(
					this.playerName,
					this.isBanned,
					this.recordCount,
					steamId64s,
					this.offset,
					this.limit
			);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Call<List<PlayerEntity>> call(GetPlayersRequest request) {
			return this.playerService.getPlayers(
					request.playerName,
					request.isBanned,
					request.recordCount,
					request.steamId64s,
					request.offset,
					request.limit
			);
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

		/**
		 * Set this "isBanned" boolean value.
		 *
		 * @param isBanned	"isBanned" boolean value, may be null
		 * @return			this instance
		 */
		public Manager setIsBanned(Boolean isBanned) {
			this.isBanned = isBanned;
			return this;
		}

		/**
		 * Set this record count.
		 *
		 * @param recordCount	record count, may be null
		 * @return				this instance
		 */
		public Manager setRecordCount(Integer recordCount) {
			this.recordCount = recordCount;
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
			return this.setSteamIds(UwSet.createOrNull(steamIds));
		}

		/**
		 * Set this set of person identifiers.
		 *
		 * @param steamIds	array of person identifiers, may be null
		 * @return			this instance
		 */
		public Manager setSteamIds(SteamId... steamIds) {
			return this.setSteamIds(UwSet.createOrNull(steamIds));
		}

		/**
		 * Set this set of person identifiers.
		 *
		 * @param steamIds	stream of person identifiers, may be null
		 * @return			this instance
		 */
		public Manager setSteamIds(Stream<SteamId> steamIds) {
			return this.setSteamIds(UwSet.createOrNull(steamIds));
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
