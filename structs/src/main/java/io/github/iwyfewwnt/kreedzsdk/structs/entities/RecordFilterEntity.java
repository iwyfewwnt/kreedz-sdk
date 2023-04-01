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
import io.github.iwyfewwnt.kreedzsdk.structs.types.ERunType;
import io.github.iwyfewwnt.kreedzsdk.structs.types.ETickrate;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Objects;

/**
 * A kreedz API record filter entity.
 */
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod"})
public final class RecordFilterEntity implements Serializable, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = RecordFilterEntity.class.getSimpleName();

	/**
	 * An identifier.
	 */
	@SerializedName("id")
	private final Integer id;

	/**
	 * A map identifier.
	 */
	@SerializedName("map_id")
	private final Integer mapId;

	/**
	 * A tickrate.
	 */
	@SerializedName("tickrate")
	private final ETickrate tickrate;

	/**
	 * A run type.
	 */
	@SerializedName("has_teleports")
	private final ERunType runType;

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
	@SerializedName("updated_by_id")
	private final DataUpdater dataUpdater;

	/**
	 * A {@link RecordFilterEntity#hashCode()} cache.
	 */
	private transient Integer hashCodeCache;

	/**
	 * A {@link RecordFilterEntity#toString()} cache.
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
	 * Get this map identifier.
	 *
	 * @return	map identifier
	 */
	public Integer getMapId() {
		return this.mapId;
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
	 * Get this run type.
	 *
	 * @return	run type
	 */
	public ERunType getRunType() {
		return this.runType;
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
	 * Get this data updater.
	 *
	 * @return	data updater
	 */
	public DataUpdater getDataUpdater() {
		return this.dataUpdater;
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

		RecordFilterEntity that = (RecordFilterEntity) obj;

		return Objects.equals(this.id, that.id)
				&& Objects.equals(this.mapId, that.mapId)
				&& this.tickrate == that.tickrate
				&& this.runType == that.runType
				&& Objects.equals(this.createDate, that.createDate)
				&& Objects.equals(this.updateDate, that.updateDate)
				&& Objects.equals(this.dataUpdater, that.dataUpdater);
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
						this.mapId,
						this.tickrate,
						this.runType,
						this.createDate,
						this.updateDate,
						this.dataUpdater
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
				+ ", mapId=" + this.mapId
				+ ", tickrate=" + this.tickrate
				+ ", runType=" + this.runType
				+ ", createDate=" + this.createDate
				+ ", updateDate=" + this.updateDate
				+ ", dataUpdater=" + this.dataUpdater
				+ "]");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RecordFilterEntity clone() {
		return new RecordFilterEntity(this);
	}

	/**
	 * Initialize a {@link RecordFilterEntity} instance.
	 *
	 * @param id			identifier
	 * @param mapId			map identifier
	 * @param tickrate		tickrate
	 * @param runType		run type
	 * @param createDate	create date
	 * @param updateDate	update date
	 * @param dataUpdater	data updater
	 */
	private RecordFilterEntity(
			Integer id,
			Integer mapId,
			ETickrate tickrate,
			ERunType runType,
			DateTime createDate,
			DateTime updateDate,
			DataUpdater dataUpdater
	) {
		this.id = id;
		this.mapId = mapId;
		this.tickrate = tickrate;
		this.runType = runType;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.dataUpdater = dataUpdater;
	}

	/**
	 * Initialize a {@link RecordFilterEntity} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private RecordFilterEntity(RecordFilterEntity that) {
		this(
				that.id,
				that.mapId,
				that.tickrate,
				that.runType,
				that.createDate,
				that.updateDate,
				that.dataUpdater
		);

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}
}
