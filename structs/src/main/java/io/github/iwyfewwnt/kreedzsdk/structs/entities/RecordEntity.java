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
import io.github.iwyfewwnt.kreedzsdk.structs.entities.inner.RunTime;
import io.github.iwyfewwnt.kreedzsdk.structs.types.EMode;
import io.github.iwyfewwnt.kreedzsdk.structs.types.ETickrate;
import io.github.iwyfewwnt.steamid.SteamId;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Objects;

/**
 * A kreedz API record entity.
 */
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod", "SynchronizeOnNonFinalField"})
public final class RecordEntity implements Serializable, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = RecordEntity.class.getSimpleName();

	/**
	 * An identifier.
	 */
	@SerializedName("id")
	private final Integer id;

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
	 * A server identifier.
	 */
	@SerializedName("server_id")
	private final Integer serverId;

	/**
	 * A map identifier.
	 */
	@SerializedName("map_id")
	private final Integer mapId;

	/**
	 * A stage identifier.
	 */
	@SerializedName("stage")
	private final Integer stage;

	/**
	 * A game mode.
	 */
	@SerializedName("mode")
	private final EMode mode;

	/**
	 * A tickrate.
	 */
	@SerializedName("tickrate")
	private final ETickrate tickrate;

	/**
	 * A run time.
	 */
	@SerializedName("time")
	private final RunTime time;

	/**
	 * A teleport count.
	 */
	@SerializedName("teleports")
	private final Integer teleportCount;

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
	 * A data updater.
	 */
	@SerializedName("updated_by")
	private final DataUpdater dataUpdater;

	/**
	 * A record filter identifier.
	 */
	@SerializedName("record_filter_id")
	private final Integer recordFilterId;

	/**
	 * A server name.
	 */
	@SerializedName("server_name")
	private final String serverName;

	/**
	 * A map name.
	 */
	@SerializedName("map_name")
	private final String mapName;

	/**
	 * A point count.
	 */
	@SerializedName("points")
	private final Integer pointCount;

	/**
	 * A replay identifier.
	 */
	@SerializedName("replay_id")
	private final Integer replayId;

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
	 * Get this identifier.
	 *
	 * @return	identifier
	 */
	public Integer getId() {
		return this.id;
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
	 * Get this server identifier.
	 *
	 * @return	server identifier
	 */
	public Integer getServerId() {
		return this.serverId;
	}

	/**
	 * Get this map identifier.
	 *
	 * @return	map identifier
	 */
	public Integer getMapId() {
		return this.mapId;
	}

	/**
	 * Get this stage identifier.
	 *
	 * @return	stage identifier
	 */
	public Integer getStage() {
		return this.stage;
	}

	/**
	 * Get this mode.
	 *
	 * @return	mode
	 */
	public EMode getMode() {
		return this.mode;
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
	 * Get this run time.
	 *
	 * @return	run time
	 */
	public RunTime getTime() {
		return this.time;
	}

	/**
	 * Get this teleport count.
	 *
	 * @return	teleport count
	 */
	public Integer getTeleportCount() {
		return this.teleportCount;
	}

	/**
	 * Get this crate date.
	 *
	 * @return	crate date
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
	 * Get this data updater.
	 *
	 * @return	data updater
	 */
	public DataUpdater getDataUpdater() {
		return this.dataUpdater;
	}

	/**
	 * Get this record filter identifier.
	 *
	 * @return	record filter identifier
	 */
	public Integer getRecordFilterId() {
		return this.recordFilterId;
	}

	/**
	 * Get this server name.
	 *
	 * @return	server name
	 */
	public String getServerName() {
		return this.serverName;
	}

	/**
	 * Get this map name.
	 *
	 * @return	map name
	 */
	public String getMapName() {
		return this.mapName;
	}

	/**
	 * Get this point count.
	 *
	 * @return	point count
	 */
	public Integer getPointCount() {
		return this.pointCount;
	}

	/**
	 * Get this replay identifier.
	 *
	 * @return	replay identifier
	 */
	public Integer getReplayId() {
		return this.replayId;
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

		RecordEntity that = (RecordEntity) obj;

		return Objects.equals(this.id, that.id)
				&& Objects.equals(this.steamId, that.steamId)
				&& Objects.equals(this.playerName, that.playerName)
				&& Objects.equals(this.serverId, that.serverId)
				&& Objects.equals(this.mapId, that.mapId)
				&& Objects.equals(this.stage, that.stage)
				&& this.mode == that.mode
				&& this.tickrate == that.tickrate
				&& Objects.equals(this.time, that.time)
				&& Objects.equals(this.teleportCount, that.teleportCount)
				&& Objects.equals(this.createDate, that.createDate)
				&& Objects.equals(this.updateDate, that.updateDate)
				&& Objects.equals(this.dataUpdater, that.dataUpdater)
				&& Objects.equals(this.recordFilterId, that.recordFilterId)
				&& Objects.equals(this.serverName, that.serverName)
				&& Objects.equals(this.mapName, that.mapName)
				&& Objects.equals(this.pointCount, that.pointCount)
				&& Objects.equals(this.replayId, that.replayId);
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
							this.steamId,
							this.playerName,
							this.serverId,
							this.mapId,
							this.stage,
							this.mode,
							this.tickrate,
							this.time,
							this.teleportCount,
							this.createDate,
							this.updateDate,
							this.dataUpdater,
							this.recordFilterId,
							this.serverName,
							this.mapName,
							this.pointCount,
							this.replayId
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
					+ "id=" + this.id
					+ ", steamId=" + this.steamId
					+ ", playerName=\"" + this.playerName + "\""
					+ ", serverId=" + this.serverId
					+ ", mapId=" + this.mapId
					+ ", stage=" + this.stage
					+ ", mode=" + this.mode
					+ ", tickrate=" + this.tickrate
					+ ", time=" + this.time
					+ ", teleportCount=" + this.teleportCount
					+ ", createDate=" + this.createDate
					+ ", updateDate=" + this.updateDate
					+ ", dataUpdater=" + this.dataUpdater
					+ ", recordFilterId=" + this.recordFilterId
					+ ", serverName=\"" + this.serverName + "\""
					+ ", mapName=\"" + this.mapName + "\""
					+ ", pointCount=" + this.pointCount
					+ ", replayId=" + this.replayId
					+ "]");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RecordEntity clone() {
		return new RecordEntity(this);
	}

	/**
	 * Initialize a {@link RecordEntity} instance.
	 *
	 * @param id				identifier
	 * @param steamId			person identifier
	 * @param playerName		person name
	 * @param serverId			server identifier
	 * @param mapId				map identifier
	 * @param stage				stage identifier
	 * @param mode				game mode
	 * @param tickrate			tickrate
	 * @param time				tun time
	 * @param teleportCount		teleport count
	 * @param createDate		create date
	 * @param updateDate		update date
	 * @param dataUpdater		data updater
	 * @param recordFilterId	record filter identifier
	 * @param serverName		server name
	 * @param mapName			map name
	 * @param pointCount		point count
	 * @param replayId			replay identifier
	 */
	private RecordEntity(
			Integer id,
			SteamId steamId,
			String playerName,
			Integer serverId,
			Integer mapId,
			Integer stage,
			EMode mode,
			ETickrate tickrate,
			RunTime time,
			Integer teleportCount,
			DateTime createDate,
			DateTime updateDate,
			DataUpdater dataUpdater,
			Integer recordFilterId,
			String serverName,
			String mapName,
			Integer pointCount,
			Integer replayId
	) {
		this.id = id;
		this.steamId = steamId;
		this.playerName = playerName;
		this.serverId = serverId;
		this.mapId = mapId;
		this.stage = stage;
		this.mode = mode;
		this.tickrate = tickrate;
		this.time = time;
		this.teleportCount = teleportCount;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.dataUpdater = dataUpdater;
		this.recordFilterId = recordFilterId;
		this.serverName = serverName;
		this.mapName = mapName;
		this.pointCount = pointCount;
		this.replayId = replayId;

		this.initMutexObjects();
	}

	/**
	 * Initialize a {@link RecordEntity} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private RecordEntity(RecordEntity that) {
		this(
				that.id,
				that.steamId,
				that.playerName,
				that.serverId,
				that.mapId,
				that.stage,
				that.mode,
				that.tickrate,
				that.time,
				that.teleportCount,
				that.createDate,
				that.updateDate,
				that.dataUpdater,
				that.recordFilterId,
				that.serverName,
				that.mapName,
				that.pointCount,
				that.replayId
		);

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}
}
