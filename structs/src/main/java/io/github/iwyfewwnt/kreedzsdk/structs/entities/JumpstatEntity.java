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
import io.github.iwyfewwnt.kreedzsdk.structs.types.EJumpType;
import io.github.iwyfewwnt.kreedzsdk.structs.types.ETickrate;
import io.github.iwyfewwnt.steamid.SteamId;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Objects;

/**
 * A kreedz API jumpstat entity.
 */
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod"})
public final class JumpstatEntity implements Serializable, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = JumpstatEntity.class.getSimpleName();

	/**
	 * An identifier.
	 */
	@SerializedName("id")
	private final Integer id;

	/**
	 * A server identifier.
	 */
	@SerializedName("server_id")
	private final Integer serverId;

	/**
	 * A person identifier.
	 */
	@SerializedName("steamid64")
	private final SteamId steamId;

	/**
	 * A person name.
	 */
	@SerializedName("player_name")
	private final String playerName;

	/**
	 * A jump type.
	 */
	@SerializedName("jump_type")
	private final EJumpType jumpType;

	/**
	 * A distance.
	 */
	@SerializedName("distance")
	private final Float distance;

	/**
	 * A tickrate.
	 */
	@SerializedName("tickrate")
	private final ETickrate tickrate;

	/**
	 * A MSL count.
	 */
	@SerializedName("msl_count")
	private final Integer mslCount;

	/**
	 * A strafe count.
	 */
	@SerializedName("strafe_count")
	private final Integer strafeCount;

	/**
	 * An "isCrouchBind" boolean value.
	 */
	@SerializedName("is_crouch_bind")
	private final Boolean isCrouchBind;

	/**
	 * An "isForwardBind" boolean value.
	 */
	@SerializedName("is_forward_bind")
	private final Boolean isForwardBind;

	/**
	 * An "isCrouchBoost" boolean value.
	 */
	@SerializedName("is_crouch_boost")
	private final Boolean isCrouchBoost;

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
	 * A {@link JumpstatEntity#hashCode()} cache.
	 */
	private transient Integer hashCodeCache;

	/**
	 * A {@link JumpstatEntity#toString()} cache.
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
	 * Get this server identifier.
	 *
	 * @return	server identifier
	 */
	public Integer getServerId() {
		return this.serverId;
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
	 * Get this person name.
	 *
	 * @return	person name
	 */
	public String getPlayerName() {
		return this.playerName;
	}

	/**
	 * Get this jump type.
	 *
	 * @return	jump type
	 */
	public EJumpType getJumpType() {
		return this.jumpType;
	}

	/**
	 * Get this distance.
	 *
	 * @return	distance
	 */
	public Float getDistance() {
		return this.distance;
	}

	/**
	 * Get this tickrate.
	 *
	 * @return	tickrate
	 */
	public ETickrate getTickrate() {
		return this.tickrate;
	}

	/**
	 * Get this MSL count.
	 *
	 * @return	MSL count
	 */
	public Integer getMslCount() {
		return this.mslCount;
	}

	/**
	 * Get this strafe count.
	 *
	 * @return	strafe count
	 */
	public Integer getStrafeCount() {
		return this.strafeCount;
	}

	/**
	 * Get this "isCrouchBind" boolean value.
	 *
	 * @return	"isCrouchBind" boolean value
	 */
	public Boolean getIsCrouchBind() {
		return this.isCrouchBind;
	}

	/**
	 * Get this "isForwardBind" boolean value.
	 *
	 * @return	"isForwardBind" boolean value
	 */
	public Boolean getIsForwardBind() {
		return this.isForwardBind;
	}

	/**
	 * Get this "isCrouchBoost" boolean value.
	 *
	 * @return	"isCrouchBoost" boolean value
	 */
	public Boolean getIsCrouchBoost() {
		return this.isCrouchBoost;
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

		JumpstatEntity that = (JumpstatEntity) obj;

		return Objects.equals(this.id, that.id)
				&& Objects.equals(this.serverId, that.serverId)
				&& Objects.equals(this.steamId, that.steamId)
				&& Objects.equals(this.playerName, that.playerName)
				&& this.jumpType == that.jumpType
				&& Objects.equals(this.distance, that.distance)
				&& this.tickrate == that.tickrate
				&& Objects.equals(this.mslCount, that.mslCount)
				&& Objects.equals(this.strafeCount, that.strafeCount)
				&& Objects.equals(this.isCrouchBind, that.isCrouchBind)
				&& Objects.equals(this.isForwardBind, that.isForwardBind)
				&& Objects.equals(this.isCrouchBoost, that.isCrouchBoost)
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
						this.serverId,
						this.steamId,
						this.playerName,
						this.jumpType,
						this.distance,
						this.tickrate,
						this.mslCount,
						this.strafeCount,
						this.isCrouchBind,
						this.isForwardBind,
						this.isCrouchBoost,
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
				+ ", serverId=" + this.serverId
				+ ", steamId=" + this.steamId
				+ ", playerName=\"" + this.playerName + "\""
				+ ", jumpType=" + this.jumpType
				+ ", distance=" + this.distance
				+ ", tickrate=" + this.tickrate
				+ ", mslCount=" + this.mslCount
				+ ", strafeCount=" + this.strafeCount
				+ ", isCrouchBind=" + this.isCrouchBind
				+ ", isForwardBind=" + this.isForwardBind
				+ ", isCrouchBoost=" + this.isCrouchBoost
				+ ", dataUpdater=" + this.dataUpdater
				+ ", createDate=" + this.createDate
				+ ", updateDate=" + this.updateDate
				+ "]");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JumpstatEntity clone() {
		return new JumpstatEntity(this);
	}

	/**
	 * Initialize a {@link JumpstatEntity} instance.
	 *
	 * @param id				identifier
	 * @param serverId			server identifier
	 * @param steamId			person identifier
	 * @param playerName		person name
	 * @param jumpType			jump type
	 * @param distance			distance
	 * @param tickrate			tickrate
	 * @param mslCount			MSL count
	 * @param strafeCount		strafe count
	 * @param isCrouchBind		"isCrouchBind" boolean value
	 * @param isForwardBind		"isForwardBind" boolean value
	 * @param isCrouchBoost		"isCrouchBoost" boolean value
	 * @param dataUpdater		data updater
	 * @param createDate		create date
	 * @param updateDate		update date
	 */
	private JumpstatEntity(
			Integer id,
			Integer serverId,
			SteamId steamId,
			String playerName,
			EJumpType jumpType,
			Float distance,
			ETickrate tickrate,
			Integer mslCount,
			Integer strafeCount,
			Boolean isCrouchBind,
			Boolean isForwardBind,
			Boolean isCrouchBoost,
			DataUpdater dataUpdater,
			DateTime createDate,
			DateTime updateDate
	) {
		this.id = id;
		this.serverId = serverId;
		this.steamId = steamId;
		this.playerName = playerName;
		this.jumpType = jumpType;
		this.distance = distance;
		this.tickrate = tickrate;
		this.mslCount = mslCount;
		this.strafeCount = strafeCount;
		this.isCrouchBind = isCrouchBind;
		this.isForwardBind = isForwardBind;
		this.isCrouchBoost = isCrouchBoost;
		this.dataUpdater = dataUpdater;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	/**
	 * Initialize a {@link JumpstatEntity} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private JumpstatEntity(JumpstatEntity that) {
		this(
				that.id,
				that.serverId,
				that.steamId,
				that.playerName,
				that.jumpType,
				that.distance,
				that.tickrate,
				that.mslCount,
				that.strafeCount,
				that.isCrouchBind,
				that.isForwardBind,
				that.isCrouchBoost,
				that.dataUpdater,
				that.createDate,
				that.updateDate
		);

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}
}
