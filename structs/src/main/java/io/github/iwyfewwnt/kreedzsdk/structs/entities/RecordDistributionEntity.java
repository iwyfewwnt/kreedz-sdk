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
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Objects;

/**
 * A kreedz API record distribution entity.
 */
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod", "SynchronizeOnNonFinalField"})
public final class RecordDistributionEntity implements Serializable, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = RecordDistributionEntity.class.getSimpleName();

	/**
	 * A record filter identifier.
	 */
	@SerializedName("record_filter_id")
	private final Integer recordFilterId;

	/**
	 * A `c` parameter.
	 */
	@SerializedName("c")
	private final Double c;

	/**
	 * A `d` parameter.
	 */
	@SerializedName("d")
	private final Double d;

	/**
	 * A location parameter.
	 */
	@SerializedName("loc")
	private final Double location;

	/**
	 * A scale parameter.
	 */
	@SerializedName("scale")
	private final Double scale;

	/**
	 * A top scale parameter.
	 */
	@SerializedName("top_scale")
	private final Double topScale;

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
	 * A {@link RecordDistributionEntity#hashCode()} cache.
	 */
	private transient volatile Integer hashCodeCache;

	/**
	 * A {@link RecordDistributionEntity#toString()} cache.
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
	 * Get this record filter identifier.
	 *
	 * @return	record filter identifier
	 */
	public Integer getRecordFilterId() {
		return this.recordFilterId;
	}

	/**
	 * Get this `c` parameter.
	 *
	 * @return	`c` parameter
	 */
	public Double getC() {
		return this.c;
	}

	/**
	 * Get this `d` parameter.
	 *
	 * @return	`d` parameter
	 */
	public Double getD() {
		return this.d;
	}

	/**
	 * Get this location parameter.
	 *
	 * @return	location parameter
	 */
	public Double getLocation() {
		return this.location;
	}

	/**
	 * Get this scale parameter.
	 *
	 * @return	scale parameter
	 */
	public Double getScale() {
		return this.scale;
	}

	/**
	 * Get this top scale parameter.
	 *
	 * @return	top scale parameter
	 */
	public Double getTopScale() {
		return this.topScale;
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

		RecordDistributionEntity that = (RecordDistributionEntity) obj;

		return Objects.equals(this.recordFilterId, that.recordFilterId)
				&& Objects.equals(this.c, that.c)
				&& Objects.equals(this.d, that.d)
				&& Objects.equals(this.location, that.location)
				&& Objects.equals(this.scale, that.scale)
				&& Objects.equals(this.topScale, that.topScale)
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

		synchronized (this.hashCodeCacheMutex) {
			if (this.hashCodeCache != null) {
				return this.hashCodeCache;
			}

			return (this.hashCodeCache
					= Objects.hash(
							this.recordFilterId,
							this.c,
							this.d,
							this.location,
							this.scale,
							this.topScale,
							this.createDate,
							this.updateDate,
							this.dataUpdater
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
					+ "recordFilterId=" + this.recordFilterId
					+ ", c=" + this.c
					+ ", d=" + this.d
					+ ", location=" + this.location
					+ ", scale=" + this.scale
					+ ", topScale=" + this.topScale
					+ ", createDate=" + this.createDate
					+ ", updateDate=" + this.updateDate
					+ ", dataUpdater=" + this.dataUpdater
					+ "]");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RecordDistributionEntity clone() {
		return new RecordDistributionEntity(this);
	}

	/**
	 * Initialize a {@link RecordDistributionEntity} instance.
	 *
	 * @param recordFilterId	record filter identifier
	 * @param c					`c` parameter
	 * @param d					`d` parameter
	 * @param location			location parameter
	 * @param scale				scale parameter
	 * @param topScale			top scale parameter
	 * @param createDate		create date
	 * @param updateDate		update date
	 * @param dataUpdater		data updater
	 */
	private RecordDistributionEntity(
			Integer recordFilterId,
			Double c,
			Double d,
			Double location,
			Double scale,
			Double topScale,
			DateTime createDate,
			DateTime updateDate,
			DataUpdater dataUpdater
	) {
		this.recordFilterId = recordFilterId;
		this.c = c;
		this.d = d;
		this.location = location;
		this.scale = scale;
		this.topScale = topScale;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.dataUpdater = dataUpdater;

		this.initMutexObjects();
	}

	/**
	 * Initialize a {@link RecordDistributionEntity} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private RecordDistributionEntity(RecordDistributionEntity that) {
		this(
				that.recordFilterId,
				that.c,
				that.d,
				that.location,
				that.scale,
				that.topScale,
				that.createDate,
				that.updateDate,
				that.dataUpdater
		);

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}
}
