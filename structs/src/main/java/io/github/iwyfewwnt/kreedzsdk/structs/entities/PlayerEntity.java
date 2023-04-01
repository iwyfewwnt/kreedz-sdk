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
import io.github.iwyfewwnt.steamid.SteamId;

import java.io.Serializable;
import java.util.Objects;

/**
 * A kreedz API player entity.
 */
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod"})
public final class PlayerEntity implements Serializable, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = PlayerEntity.class.getSimpleName();

	/**
	 * A person identifier.
	 */
	@SerializedName("steamid64")
	private final SteamId steamId;

	/**
	 * An "isBanned" boolean value.
	 */
	@SerializedName("is_banned")
	private final Boolean isBanned;

	/**
	 * A record count.
	 */
	@SerializedName("total_records")
	private final Integer recordCount;

	/**
	 * A person name.
	 */
	@SerializedName("name")
	private final String name;

	/**
	 * A {@link PlayerEntity#hashCode()} cache.
	 */
	private transient Integer hashCodeCache;

	/**
	 * A {@link PlayerEntity#toString()} cache.
	 */
	private transient String stringCache;

	/**
	 * Get this person identifier.
	 *
	 * @return	person identifier
	 */
	public SteamId getSteamId() {
		return this.steamId;
	}

	/**
	 * Get this "isBanned" boolean value.
	 *
	 * @return	"isBanned" boolean value
	 */
	public Boolean getIsBanned() {
		return this.isBanned;
	}

	/**
	 * Get this record count.
	 *
	 * @return	record count
	 */
	public Integer getRecordCount() {
		return this.recordCount;
	}

	/**
	 * Get this person name.
	 *
	 * @return	person name
	 */
	public String getName() {
		return this.name;
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

		PlayerEntity that = (PlayerEntity) obj;

		return Objects.equals(this.steamId, that.steamId)
				&& Objects.equals(this.isBanned, that.isBanned)
				&& Objects.equals(this.recordCount, that.recordCount)
				&& Objects.equals(this.name, that.name);
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
						this.steamId,
						this.isBanned,
						this.recordCount,
						this.name
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
				+ "steamId=" + this.steamId
				+ ", isBanned=" + this.isBanned
				+ ", recordCount=" + this.recordCount
				+ ", name=\"" + this.name + "\""
				+ "]");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PlayerEntity clone() {
		return new PlayerEntity(this);
	}

	/**
	 * Initialize a {@link PlayerEntity} instance.
	 *
	 * @param steamId		person identifier
	 * @param isBanned		"isBanned" boolean value
	 * @param recordCount	record count
	 * @param name			person name
	 */
	private PlayerEntity(
			SteamId steamId,
			Boolean isBanned,
			Integer recordCount,
			String name
	) {
		this.steamId = steamId;
		this.isBanned = isBanned;
		this.recordCount = recordCount;
		this.name = name;
	}

	/**
	 * Initialize a {@link PlayerEntity} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private PlayerEntity(PlayerEntity that) {
		this(
				that.steamId,
				that.isBanned,
				that.recordCount,
				that.name
		);

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}
}
