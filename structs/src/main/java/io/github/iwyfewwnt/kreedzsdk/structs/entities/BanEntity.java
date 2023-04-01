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

package io.github.iwyfewwnt.kreedzsdk.structs.entities;

import com.google.gson.annotations.SerializedName;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.inner.DataUpdater;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.inner.anticheat.stats.BanStats;
import io.github.iwyfewwnt.kreedzsdk.structs.types.EBanType;
import io.github.iwyfewwnt.steamid.SteamId;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Objects;

/**
 * A kreedz API ban entity.
 */
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod"})
public final class BanEntity implements Serializable, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = BanEntity.class.getSimpleName();

	/**
	 * An identifier.
	 */
	@SerializedName("id")
	private final Integer id;

	/**
	 * A ban type.
	 */
	@SerializedName("ban_type")
	private final EBanType banType;

	/**
	 * An expire date.
	 */
	@SerializedName("expires_on")
	private final DateTime expireDate;

	/**
	 * A person identifier.
	 */
	@SerializedName("steamid64")
	private final SteamId steamId;

	/**
	 * A notes.
	 */
	@SerializedName("notes")
	private final String notes;

	/**
	 * A stats.
	 */
	@SerializedName("stats")
	private final BanStats stats;

	/**
	 * A server identifier.
	 */
	@SerializedName("server_id")
	private final Integer serverId;

	/**
	 * A data updater.
	 */
	@SerializedName("updated_by_id")
	private final DataUpdater dataUpdater;

	/**
	 * A create date.
	 */
	@SerializedName("created_on")
	private final DateTime createDate;

	/**
	 * An update date.
	 */
	@SerializedName("updated_on")
	private final DateTime updateDate;

	/**
	 * A {@link BanEntity#hashCode()} cache.
	 */
	private transient Integer hashCodeCache;

	/**
	 * A {@link BanEntity#toString()} cache.
	 */
	private transient String stringCache;

	/**
	 * Get this identifier.
	 *
	 * @return	identifier
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Get this ban type.
	 *
	 * @return	ban type
	 */
	public EBanType getBanType() {
		return this.banType;
	}

	/**
	 * Get this expire date.
	 *
	 * @return	expire date
	 */
	public DateTime getExpireDate() {
		return this.expireDate;
	}

	/**
	 * Get this person identifier.
	 *
	 * @return	person identifier
	 */
	public SteamId getSteamId() {
		return this.steamId;
	}

	/**
	 * Get this notes.
	 *
	 * @return	notes
	 */
	public String getNotes() {
		return this.notes;
	}

	/**
	 * Get this stats.
	 *
	 * @return	stats
	 */
	public BanStats getStats() {
		return this.stats;
	}

	/**
	 * Get this server identifier.
	 *
	 * @return	server identifier
	 */
	public Integer getServerId() {
		return this.serverId;
	}

	/**
	 * Get this data updater.
	 *
	 * @return	data updater
	 */
	public DataUpdater getDataUpdater() {
		return this.dataUpdater;
	}

	/**
	 * Get this create date.
	 *
	 * @return	create date
	 */
	public DateTime getCreateDate() {
		return this.createDate;
	}

	/**
	 * Get this update date.
	 *
	 * @return	update date
	 */
	public DateTime getUpdateDate() {
		return this.updateDate;
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

		BanEntity that = (BanEntity) obj;

		return Objects.equals(this.id, that.id)
				&& this.banType == that.banType
				&& Objects.equals(this.expireDate, that.expireDate)
				&& Objects.equals(this.steamId, that.steamId)
				&& Objects.equals(this.notes, that.notes)
				&& Objects.equals(this.stats, that.stats)
				&& Objects.equals(this.serverId, that.serverId)
				&& Objects.equals(this.dataUpdater, that.dataUpdater)
				&& Objects.equals(this.createDate, that.createDate)
				&& Objects.equals(this.updateDate, that.updateDate);
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
						this.id,
						this.banType,
						this.expireDate,
						this.steamId,
						this.notes,
						this.stats,
						this.serverId,
						this.dataUpdater,
						this.createDate,
						this.updateDate
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
				+ "id=" + this.id
				+ ", banType=" + this.banType
				+ ", expireDate=" + this.expireDate
				+ ", steamId=" + this.steamId
				+ ", notes=\"" + this.notes + "\""
				+ ", stats=" + this.stats
				+ ", serverId=" + this.serverId
				+ ", dataUpdater=" + this.dataUpdater
				+ ", createDate=" + this.createDate
				+ ", updateDate=" + this.updateDate
				+ "]");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BanEntity clone() {
		return new BanEntity(this);
	}

	/**
	 * Initialize a {@link BanEntity} instance.
	 *
	 * @param id			identifier
	 * @param banType		ban type
	 * @param expireDate	expire date
	 * @param steamId		person identifier
	 * @param notes			notes
	 * @param stats			stats
	 * @param serverId		server identifier
	 * @param dataUpdater	data updater
	 * @param createDate	create date
	 * @param updateDate	update date
	 */
	private BanEntity(
			Integer id,
			EBanType banType,
			DateTime expireDate,
			SteamId steamId,
			String notes,
			BanStats stats,
			Integer serverId,
			DataUpdater dataUpdater,
			DateTime createDate,
			DateTime updateDate
	) {
		this.id = id;
		this.banType = banType;
		this.expireDate = expireDate;
		this.steamId = steamId;
		this.notes = notes;
		this.stats = stats;
		this.serverId = serverId;
		this.dataUpdater = dataUpdater;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	/**
	 * Initialize a {@link BanEntity} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private BanEntity(BanEntity that) {
		this(
				that.id,
				that.banType,
				that.expireDate,
				that.steamId,
				that.notes,
				that.stats,
				that.serverId,
				that.dataUpdater,
				that.createDate,
				that.updateDate
		);

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}
}
