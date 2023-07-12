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

import io.github.iwyfewwnt.kreedzsdk.structs.entities.JumpstatEntity;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.inner.DataUpdater;
import io.github.iwyfewwnt.kreedzsdk.structs.types.EJumpType;
import io.github.iwyfewwnt.steamid.SteamId;
import io.github.iwyfewwnt.uwretrofit.services.requests.impl.BaseRequestManager;
import io.github.iwyfewwnt.uwutils.UwObject;
import io.github.iwyfewwnt.uwutils.UwSet;
import org.joda.time.DateTime;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * A base request manager for /jumpstats/{@literal **}/ endpoints.
 *
 * @param <T>	manager type
 * @param <U>	request type
 */
@SuppressWarnings("unused")
public abstract class BaseGetJumpstatsRequestManager<T extends BaseGetJumpstatsRequestManager<T, U>, U extends GetJumpstatsRequest>
		extends BaseRequestManager<T, U, List<JumpstatEntity>> {

	/**
	 * An identifier.
	 */
	protected Integer id;

	/**
	 * A server identifier.
	 */
	protected Integer serverId;

	/**
	 * A set of person identifiers.
	 */
	protected Set<SteamId> steamIds;

	/**
	 * A jump type.
	 */
	protected EJumpType jumpType;

	/**
	 * A left distance threshold.
	 */
	protected Float distanceGreaterThan;

	/**
	 * A right distance threshold.
	 */
	protected Float distanceLessThan;

	/**
	 * An "isMsl" boolean value.
	 */
	protected Boolean isMsl;

	/**
	 * An "isCrouchBind" boolean value.
	 */
	protected Boolean isCrouchBind;

	/**
	 * An "isForwardBind" boolean value.
	 */
	protected Boolean isForwardBind;

	/**
	 * An "isCrouchBoost" boolean value.
	 */
	protected Boolean isCrouchBoost;

	/**
	 * A data updater identifier.
	 */
	protected Long dataUpdaterId;

	/**
	 * A created since date.
	 */
	protected DateTime createdSinceDate;

	/**
	 * An updated since date.
	 */
	protected DateTime updatedSinceDate;

	/**
	 * An offset.
	 */
	protected Integer offset;

	/**
	 * A limit.
	 */
	protected Integer limit;

	/**
	 * Initialize a {@link BaseGetJumpstatsRequestManager} instance.
	 */
	BaseGetJumpstatsRequestManager() {
	}

	/**
	 * Set this identifier
	 *
	 * @param id	identifier, may be null
	 * @return		this instance
	 */
	public final T setId(Integer id) {
		this.id = id;
		return this.asT;
	}

	/**
	 * Set this server identifier.
	 *
	 * @param serverId	server identifier, may be null
	 * @return			this instance
	 */
	public final T setServerId(Integer serverId) {
		this.serverId = serverId;
		return this.asT;
	}

	/**
	 * Set this set of person identifiers.
	 *
	 * @param steamIds	set of person identifiers, may be null
	 * @return			this instance
	 */
	public final T setSteamIds(Set<SteamId> steamIds) {
		this.steamIds = steamIds;
		return this.asT;
	}

	/**
	 * Set this set of person identifiers.
	 *
	 * @param steamIds	set of person identifiers, may be null
	 * @return			this instance
	 */
	public final T setSteamIds(Collection<SteamId> steamIds) {
		return this.setSteamIds(UwSet.createOrNull(steamIds));
	}

	/**
	 * Set this set of person identifiers.
	 *
	 * @param steamIds	array of person identifiers, may be null
	 * @return			this instance
	 */
	public final T setSteamIds(SteamId... steamIds) {
		return this.setSteamIds(UwSet.createOrNull(steamIds));
	}

	/**
	 * Set this set of person identifiers.
	 *
	 * @param steamIds	stream of person identifiers, may be null
	 * @return			this instance
	 */
	public final T setSteamIds(Stream<SteamId> steamIds) {
		return this.setSteamIds(UwSet.createOrNull(steamIds));
	}

	/**
	 * Set this jump type.
	 *
	 * @param jumpType	jump type, may be null
	 * @return			this instance
	 */
	public final T setJumpType(EJumpType jumpType) {
		this.jumpType = jumpType;
		return this.asT;
	}

	/**
	 * Set this left distance threshold.
	 *
	 * @param distanceGreaterThan	left distance threshold, may be null
	 * @return						this instance
	 */
	public final T setDistanceGreaterThan(Float distanceGreaterThan) {
		this.distanceGreaterThan = distanceGreaterThan;
		return this.asT;
	}

	/**
	 * Set this left distance threshold.
	 *
	 * @param distanceGreaterThan	left distance threshold, may be null
	 * @return						this instance
	 */
	public final T setDistanceGreaterThan(Integer distanceGreaterThan) {
		return this.setDistanceGreaterThan(UwObject.ifNotNull(distanceGreaterThan, Integer::floatValue));
	}

	/**
	 * Set this right distance threshold.
	 *
	 * @param distanceLessThan	right distance threshold, may be null
	 * @return					this instance
	 */
	public final T setDistanceLessThan(Float distanceLessThan) {
		this.distanceLessThan = distanceLessThan;
		return this.asT;
	}

	/**
	 * Set this right distance threshold.
	 *
	 * @param distanceLessThan	right distance threshold, may be null
	 * @return					this instance
	 */
	public final T setDistanceLessThan(Integer distanceLessThan) {
		return this.setDistanceLessThan(UwObject.ifNotNull(distanceLessThan, Integer::floatValue));
	}

	/**
	 * Set this "isMsl" boolean value.
	 *
	 * @param isMsl		"isMsl" boolean value, may be null
	 * @return			this instance
	 */
	public final T setIsMsl(Boolean isMsl) {
		this.isMsl = isMsl;
		return this.asT;
	}

	/**
	 * Set this "isCrouchBind" boolean value.
	 *
	 * @param isCrouchBind	"isCrouchBind" boolean value, may be null
	 * @return				this instance
	 */
	public final T setIsCrouchBind(Boolean isCrouchBind) {
		this.isCrouchBind = isCrouchBind;
		return this.asT;
	}

	/**
	 * Set this "isForwardBind" boolean value.
	 *
	 * @param isForwardBind		"isForwardBind" boolean value, may be null
	 * @return					this instance
	 */
	public final T setIsForwardBind(Boolean isForwardBind) {
		this.isForwardBind = isForwardBind;
		return this.asT;
	}

	/**
	 * Set this "isCrouchBoost" boolean value.
	 *
	 * @param isCrouchBoost		"isCrouchBoost" boolean value, may be null
	 * @return					this instance
	 */
	public final T setIsCrouchBoost(Boolean isCrouchBoost) {
		this.isCrouchBoost = isCrouchBoost;
		return this.asT;
	}

	/**
	 * Set this data updater identifier.
	 *
	 * @param dataUpdaterId		data updater identifier, may be null
	 * @return					this instance
	 */
	public final T setDataUpdater(Long dataUpdaterId) {
		this.dataUpdaterId = dataUpdaterId;
		return this.asT;
	}

	/**
	 * Set this data updater identifier.
	 *
	 * @param dataUpdater	data updater, may be null
	 * @return				this instance
	 */
	public final T setDataUpdater(DataUpdater dataUpdater) {
		return this.setDataUpdater(UwObject.ifNotNull(dataUpdater, DataUpdater::getId));
	}

	/**
	 * Set this data updater identifier.
	 *
	 * @param steamId	person identifier, may be null
	 * @return			this instance
	 */
	public final T setDataUpdater(SteamId steamId) {
		return this.setDataUpdater(UwObject.ifNotNull(steamId, SteamId::toSteam64OrNull));
	}

	/**
	 * Set this created since date.
	 *
	 * @param createdSinceDate	created since date, may be null
	 * @return					this instance
	 */
	public final T setCreatedSinceDate(DateTime createdSinceDate) {
		this.createdSinceDate = createdSinceDate;
		return this.asT;
	}

	/**
	 * Set this updated since date.
	 *
	 * @param updatedSinceDate	updated since date, may be null
	 * @return					this instance
	 */
	public final T setUpdatedSinceDate(DateTime updatedSinceDate) {
		this.updatedSinceDate = updatedSinceDate;
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
