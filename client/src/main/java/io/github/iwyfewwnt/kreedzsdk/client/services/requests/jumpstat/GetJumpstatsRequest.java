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

package io.github.iwyfewwnt.kreedzsdk.client.services.requests.jumpstat;

import io.github.iwyfewwnt.kreedzsdk.client.internal.UKreedzCommon;
import io.github.iwyfewwnt.kreedzsdk.clientapi.IJumpstatService;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.JumpstatEntity;
import io.github.iwyfewwnt.kreedzsdk.structs.types.EJumpType;
import io.github.iwyfewwnt.uwretrofit.services.requests.IRequest;
import io.github.iwyfewwnt.uwutils.UwSet;
import org.joda.time.DateTime;
import retrofit2.Call;

import java.util.*;

/**
 * A request for /jumpstats/ endpoint.
 */
@SuppressWarnings({"MethodDoesntCallSuperMethod", "SynchronizeOnNonFinalField"})
public class GetJumpstatsRequest implements IRequest, Cloneable {

	/**
	 * An identifier.
	 */
	protected final Integer id;

	/**
	 * A server identifier.
	 */
	protected final Integer serverId;

	/**
	 * A set of type-64 person identifiers.
	 */
	protected final Set<Long> steamId64s;

	/**
	 * A jump type.
	 */
	protected final EJumpType jumpType;

	/**
	 * A left distance threshold.
	 */
	protected final Float distanceGreaterThan;

	/**
	 * A right distance threshold.
	 */
	protected final Float distanceLessThan;

	/**
	 * An "isMsl" boolean value.
	 */
	protected final Boolean isMsl;

	/**
	 * An "isCrouchBind" boolean value.
	 */
	protected final Boolean isCrouchBind;

	/**
	 * An "isForwardBind" boolean value.
	 */
	protected final Boolean isForwardBind;

	/**
	 * An "isCrouchBoost" boolean value.
	 */
	protected final Boolean isCrouchBoost;

	/**
	 * A data updater identifier.
	 */
	protected final Long dataUpdaterId;

	/**
	 * A created since date.
	 */
	protected final DateTime createdSinceDate;

	/**
	 * An updated since date.
	 */
	protected final DateTime updatedSinceDate;

	/**
	 * An offset.
	 */
	protected final Integer offset;

	/**
	 * A limit.
	 */
	protected final Integer limit;

	/**
	 * A {@link #hashCode()} cache.
	 */
	protected transient volatile Integer hashCodeCache;

	/**
	 * A {@link #toString()} cache.
	 */
	protected transient volatile String stringCache;

	/**
	 * A {@link #hashCodeCache} mutex.
	 */
	protected transient Object hashCodeCacheMutex;

	/**
	 * A {@link #stringCache} mutex.
	 */
	protected transient Object stringCacheMutex;

	/**
	 * Initialize this mutex objects.
	 */
	protected void initMutexObjects() {
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
	 * Initialize a {@link GetJumpstatsRequest} instance.
	 *
	 * @param id					identifier
	 * @param serverId				server identifier
	 * @param steamId64s			set of type-64 person identifiers
	 * @param jumpType				jump type
	 * @param distanceGreaterThan	left distance threshold
	 * @param distanceLessThan		right distance threshold
	 * @param isMsl					"isMsl" boolean value
	 * @param isCrouchBind			"isCrouchBind" boolean value
	 * @param isForwardBind			"isForwardBind" boolean value
	 * @param isCrouchBoost			"isCrouchBoost" boolean value
	 * @param dataUpdaterId			data updater identifier
	 * @param createdSinceDate		created since date
	 * @param updatedSinceDate		updated since date
	 * @param offset				offset
	 * @param limit					limit
	 */
	GetJumpstatsRequest(
			Integer id,
			Integer serverId,
			Set<Long> steamId64s,
			EJumpType jumpType,
			Float distanceGreaterThan,
			Float distanceLessThan,
			Boolean isMsl,
			Boolean isCrouchBind,
			Boolean isForwardBind,
			Boolean isCrouchBoost,
			Long dataUpdaterId,
			DateTime createdSinceDate,
			DateTime updatedSinceDate,
			Integer offset,
			Integer limit
	) {
		steamId64s = UwSet.toUnmodifiable(steamId64s);

		this.id = id;
		this.serverId = serverId;
		this.steamId64s = steamId64s;
		this.jumpType = jumpType;
		this.distanceGreaterThan = distanceGreaterThan;
		this.distanceLessThan = distanceLessThan;
		this.isMsl = isMsl;
		this.isCrouchBind = isCrouchBind;
		this.isForwardBind = isForwardBind;
		this.isCrouchBoost = isCrouchBoost;
		this.dataUpdaterId = dataUpdaterId;
		this.createdSinceDate = createdSinceDate;
		this.updatedSinceDate = updatedSinceDate;
		this.offset = offset;
		this.limit = limit;

		this.initMutexObjects();
	}

	/**
	 * Initialize a {@link GetJumpstatsRequest} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	GetJumpstatsRequest(GetJumpstatsRequest that) {
		this(
				that.id,
				that.serverId,
				that.steamId64s,
				that.jumpType,
				that.distanceGreaterThan,
				that.distanceLessThan,
				that.isMsl,
				that.isCrouchBind,
				that.isForwardBind,
				that.isCrouchBoost,
				that.dataUpdaterId,
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

		GetJumpstatsRequest that = (GetJumpstatsRequest) obj;

		return Objects.equals(this.id, that.id)
				&& Objects.equals(this.serverId, that.serverId)
				&& Objects.equals(this.steamId64s, that.steamId64s)
				&& this.jumpType == that.jumpType
				&& Objects.equals(this.distanceGreaterThan, that.distanceGreaterThan)
				&& Objects.equals(this.distanceLessThan, that.distanceLessThan)
				&& Objects.equals(this.isMsl, that.isMsl)
				&& Objects.equals(this.isCrouchBind, that.isCrouchBind)
				&& Objects.equals(this.isForwardBind, that.isForwardBind)
				&& Objects.equals(this.isCrouchBoost, that.isCrouchBoost)
				&& Objects.equals(this.dataUpdaterId, that.dataUpdaterId)
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
							this.id,
							this.serverId,
							this.steamId64s,
							this.jumpType,
							this.distanceGreaterThan,
							this.distanceLessThan,
							this.isMsl,
							this.isCrouchBind,
							this.isForwardBind,
							this.isCrouchBoost,
							this.dataUpdaterId,
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

			String simpleName = this.getClass()
					.getSimpleName();

			return (this.stringCache = simpleName + "["
					+ "id=" + this.id
					+ ", serverId=" + this.serverId
					+ ", steamId64s=" + this.steamId64s
					+ ", jumpType=" + this.jumpType
					+ ", distanceGreaterThan=" + this.distanceGreaterThan
					+ ", distanceLessThan=" + this.distanceLessThan
					+ ", isMsl=" + this.isMsl
					+ ", isCrouchBind=" + this.isCrouchBind
					+ ", isForwardBind=" + this.isForwardBind
					+ ", isCrouchBoost=" + this.isCrouchBoost
					+ ", dataUpdaterId=" + this.dataUpdaterId
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
	public GetJumpstatsRequest clone() {
		return new GetJumpstatsRequest(this);
	}

	/**
	 * A request manager for /jumpstats/ endpoint.
	 */
	public static final class Manager extends BaseGetJumpstatsRequestManager<
				Manager, GetJumpstatsRequest> {

		/**
		 * A jumpstat service.
		 */
		private final IJumpstatService jumpstatService;

		/**
		 * Initialize a {@link GetJumpstatsRequest.Manager} instance.
		 *
		 * @param jumpstatService	jumpstat service
		 */
		public Manager(IJumpstatService jumpstatService) {
			if (jumpstatService == null) {
				throw new IllegalArgumentException("Jumpstats service mustn't be <null>");
			}

			this.jumpstatService = jumpstatService;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public GetJumpstatsRequest build() {
			Set<Long> steamId64s = UKreedzCommon.transformToSteamId64Set(this.steamIds);

			return new GetJumpstatsRequest(
					this.id,
					this.serverId,
					steamId64s,
					this.jumpType,
					this.distanceGreaterThan,
					this.distanceLessThan,
					this.isMsl,
					this.isCrouchBind,
					this.isForwardBind,
					this.isCrouchBoost,
					this.dataUpdaterId,
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
		public Call<List<JumpstatEntity>> call(GetJumpstatsRequest request) {
			return this.jumpstatService.getJumpstats(
					request.id,
					request.serverId,
					request.steamId64s,
					request.jumpType,
					request.distanceGreaterThan,
					request.distanceLessThan,
					request.isMsl,
					request.isCrouchBind,
					request.isForwardBind,
					request.isCrouchBoost,
					request.dataUpdaterId,
					request.createdSinceDate,
					request.updatedSinceDate,
					request.offset,
					request.limit
			);
		}
	}
}
