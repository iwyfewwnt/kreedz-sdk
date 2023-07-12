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

package io.github.iwyfewwnt.kreedzsdk.client.services.requests.ban;

import io.github.iwyfewwnt.kreedzsdk.clientapi.IBanService;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.BanEntity;
import io.github.iwyfewwnt.kreedzsdk.structs.types.EBanType;
import io.github.iwyfewwnt.steamid.SteamId;
import io.github.iwyfewwnt.uwretrofit.services.requests.IRequest;
import io.github.iwyfewwnt.uwretrofit.services.requests.impl.AbstractRequestManager;
import io.github.iwyfewwnt.uwutils.UwObject;
import io.github.iwyfewwnt.uwutils.UwSet;
import org.joda.time.DateTime;

import retrofit2.Call;

import java.util.*;
import java.util.stream.Stream;

/**
 * A request for /bans/ endpoint.
 */
@SuppressWarnings({"MethodDoesntCallSuperMethod", "SynchronizeOnNonFinalField"})
public final class GetBansRequest implements IRequest, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = GetBansRequest.class.getSimpleName();

	/**
	 * A set of ban types.
	 */
	private final Set<EBanType> banTypes;

	/**
	 * A type-64 person identifier.
	 */
	private final Long steamId64;

	/**
	 * An "isExpired" boolean value.
	 */
	private final Boolean isExpired;

	/**
	 * A notes.
	 */
	private final String notes;

	/**
	 * A stats.
	 */
	private final String stats;

	/**
	 * A server identifier.
	 */
	private final Integer serverId;

	/**
	 * A created since date.
	 */
	private final DateTime createdSinceDate;

	/**
	 * An updated since date.
	 */
	private final DateTime updatedSinceDate;

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
	 * Initialize a {@link GetBansRequest} instance.
	 *
	 * @param banTypes			set of ban types
	 * @param steamId64			type-64 person identifier
	 * @param isExpired			"isExpired" boolean value
	 * @param notes				notes
	 * @param stats				stats
	 * @param serverId			server identifier
	 * @param createdSinceDate	created since date
	 * @param updatedSinceDate	updated since date
	 * @param offset			offset
	 * @param limit				limit
	 */
	private GetBansRequest(
			Set<EBanType> banTypes,
			Long steamId64,
			Boolean isExpired,
			String notes,
			String stats,
			Integer serverId,
			DateTime createdSinceDate,
			DateTime updatedSinceDate,
			Integer offset,
			Integer limit
	) {
		banTypes = UwSet.toUnmodifiable(banTypes);

		this.banTypes = banTypes;
		this.steamId64 = steamId64;
		this.isExpired = isExpired;
		this.notes = notes;
		this.stats = stats;
		this.serverId = serverId;
		this.createdSinceDate = createdSinceDate;
		this.updatedSinceDate = updatedSinceDate;
		this.offset = offset;
		this.limit = limit;

		this.initMutexObjects();
	}

	/**
	 * Initialize a {@link GetBansRequest} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private GetBansRequest(GetBansRequest that) {
		this(
				that.banTypes,
				that.steamId64,
				that.isExpired,
				that.notes,
				that.stats,
				that.serverId,
				that.createdSinceDate,
				that.updatedSinceDate,
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

		GetBansRequest that = (GetBansRequest) obj;

		return Objects.equals(this.banTypes, that.banTypes)
				&& Objects.equals(this.steamId64, that.steamId64)
				&& Objects.equals(this.isExpired, that.isExpired)
				&& Objects.equals(this.notes, that.notes)
				&& Objects.equals(this.stats, that.stats)
				&& Objects.equals(this.serverId, that.serverId)
				&& Objects.equals(this.createdSinceDate, that.createdSinceDate)
				&& Objects.equals(this.updatedSinceDate, that.updatedSinceDate)
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
							this.banTypes,
							this.steamId64,
							this.isExpired,
							this.notes,
							this.stats,
							this.serverId,
							this.createdSinceDate,
							this.updatedSinceDate,
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
					+ "banTypes=" + this.banTypes
					+ ", steamId64=" + this.steamId64
					+ ", isExpired=" + this.isExpired
					+ ", notes=\"" + this.notes + "\""
					+ ", stats=\"" + this.stats + "\""
					+ ", serverId=" + this.serverId
					+ ", createdSinceDate=" + this.createdSinceDate
					+ ", updatedSinceDate=" + this.updatedSinceDate
					+ ", offset=" + this.offset
					+ ", limit=" + this.limit
					+ "]");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetBansRequest clone() {
		return new GetBansRequest(this);
	}

	/**
	 * A request manager for /bans/ endpoint.
	 */
	@SuppressWarnings("unused")
	public static final class Manager extends AbstractRequestManager<
			GetBansRequest, List<BanEntity>> {

		/**
		 * A ban service.
		 */
		private final IBanService banService;

		/**
		 * A set of ban types.
		 */
		private Set<EBanType> banTypes;

		/**
		 * A person identifier.
		 */
		private SteamId steamId;

		/**
		 * An "isExpired" boolean value.
		 */
		private Boolean isExpired;

		/**
		 * A notes.
		 */
		private String notes;

		/**
		 * A stats.
		 */
		private String stats;

		/**
		 * A server identifier.
		 */
		private Integer serverId;

		/**
		 * A created since date.
		 */
		private DateTime createdSinceDate;

		/**
		 * An updated since date.
		 */
		private DateTime updatedSinceDate;

		/**
		 * An offset.
		 */
		private Integer offset;

		/**
		 * A limit.
		 */
		private Integer limit;

		/**
		 * Initialize a {@link GetBansRequest.Manager} instance.
		 *
		 * @param banService	ban service
		 */
		public Manager(IBanService banService) {
			if (banService == null) {
				throw new IllegalArgumentException("Ban service mustn't be <null>");
			}

			this.banService = banService;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public GetBansRequest build() {
			Long steamId64 = UwObject.ifNotNull(this.steamId, SteamId::toSteam64OrNull);

			return new GetBansRequest(
					this.banTypes,
					steamId64,
					this.isExpired,
					this.notes,
					this.stats,
					this.serverId,
					this.createdSinceDate,
					this.updatedSinceDate,
					this.offset,
					this.limit
			);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Call<List<BanEntity>> call(GetBansRequest request) {
			return this.banService.getBans(
					request.banTypes,
					request.steamId64,
					request.isExpired,
					request.notes,
					request.stats,
					request.serverId,
					request.createdSinceDate,
					request.updatedSinceDate,
					request.offset,
					request.limit
			);
		}

		/**
		 * Set this set of ban types.
		 *
		 * @param banTypes	set of ban types, may be null
		 * @return			this instance
		 */
		public Manager setBanTypes(Set<EBanType> banTypes) {
			this.banTypes = banTypes;
			return this;
		}

		/**
		 * Set this set of ban types.
		 *
		 * @param banTypes	collection of ban types, may be null
		 * @return			this instance
		 */
		public Manager setBanTypes(Collection<EBanType> banTypes) {
			return this.setBanTypes(UwSet.createOrNull(banTypes));
		}

		/**
		 * Set this set of ban types.
		 *
		 * @param banTypes	array of ban types, may be null
		 * @return			this instance
		 */
		public Manager setBanTypes(EBanType... banTypes) {
			return this.setBanTypes(UwSet.createOrNull(banTypes));
		}

		/**
		 * Set this set of ban types.
		 *
		 * @param banTypes	stream of ban types, may be null
		 * @return			this instance
		 */
		public Manager setBanTypes(Stream<EBanType> banTypes) {
			return this.setBanTypes(UwSet.createOrNull(banTypes));
		}

		/**
		 * Set this person identifier.
		 *
		 * @param steamId	person identifier, may be null
		 * @return			this instance
		 */
		public Manager setSteamId(SteamId steamId) {
			this.steamId = steamId;
			return this;
		}

		/**
		 * Set this "isExpired" boolean value.
		 *
		 * @param isExpired		"isExpired" boolean value, may be null
		 * @return				this instance
		 */
		public Manager setIsExpired(Boolean isExpired) {
			this.isExpired = isExpired;
			return this;
		}

		/**
		 * Set this notes.
		 *
		 * @param notes		notes, may be null
		 * @return			this instance
		 */
		public Manager setNotes(String notes) {
			this.notes = notes;
			return this;
		}

		/**
		 * Set this stats.
		 *
		 * @param stats		stats, may be null
		 * @return			this instance
		 */
		public Manager setStats(String stats) {
			this.stats = stats;
			return this;
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
		 * Set this created since date.
		 *
		 * @param createdSinceDate	created since date, may be null
		 * @return					this instance
		 */
		public Manager setCreatedSinceDate(DateTime createdSinceDate) {
			this.createdSinceDate = createdSinceDate;
			return this;
		}

		/**
		 * Set this updated since date.
		 *
		 * @param updatedSinceDate	updated since date, may be null
		 * @return					this instance
		 */
		public Manager setUpdatedSinceDate(DateTime updatedSinceDate) {
			this.updatedSinceDate = updatedSinceDate;
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
