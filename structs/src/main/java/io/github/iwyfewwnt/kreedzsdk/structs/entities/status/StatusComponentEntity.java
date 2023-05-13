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

package io.github.iwyfewwnt.kreedzsdk.structs.entities.status;

import com.google.gson.annotations.SerializedName;
import io.github.iwyfewwnt.kreedzsdk.structs.types.status.EStatus;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Objects;

/**
 * A kreedz status API component entity.
 */
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod", "SynchronizeOnNonFinalField"})
public final class StatusComponentEntity implements Serializable, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = StatusComponentEntity.class.getSimpleName();

	/**
	 * An identifier.
	 */
	@SerializedName("id")
	private final String id;

	/**
	 * A name.
	 */
	@SerializedName("name")
	private final String name;

	/**
	 * A status.
	 */
	@SerializedName("status")
	private final EStatus status;

	/**
	 * A create date.
	 */
	@SerializedName("created_at")
	private final DateTime createDate;

	/**
	 * An update date.
	 */
	@SerializedName("updated_at")
	private final DateTime updateDate;

	/**
	 * A description.
	 */
	@SerializedName("description")
	private final String description;

	/**
	 * A page identifier.
	 */
	@SerializedName("page_id")
	private final String pageId;

	/**
	 * A {@link StatusComponentEntity#hashCode()} cache.
	 */
	private transient volatile Integer hashCodeCache;

	/**
	 * A {@link StatusComponentEntity#toString()} cache.
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
	public String getId() {
		return this.id;
	}

	/**
	 * Get this name.
	 *
	 * @return	name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Get this status
	 *
	 * @return	status
	 */
	public EStatus getStatus() {
		return this.status;
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
	 * Get this description.
	 *
	 * @return	description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Get this page identifier.
	 *
	 * @return	page identifier
	 */
	public String getPageId() {
		return this.pageId;
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

		StatusComponentEntity that = (StatusComponentEntity) obj;

		return Objects.equals(this.id, that.id)
				&& Objects.equals(this.name, that.name)
				&& this.status == that.status
				&& Objects.equals(this.createDate, that.createDate)
				&& Objects.equals(this.updateDate, that.updateDate)
				&& Objects.equals(this.description, that.description)
				&& Objects.equals(this.pageId, that.pageId);
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
							this.name,
							this.status,
							this.createDate,
							this.updateDate,
							this.description,
							this.pageId
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
					+ "id=\"" + this.id + "\""
					+ ", name=\"" + this.name + "\""
					+ ", status=" + this.status
					+ ", createDate=" + this.createDate
					+ ", updateDate=" + this.updateDate
					+ ", description=\"" + this.description + "\""
					+ ", pageId=\"" + this.pageId + "\""
					+ "]");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StatusComponentEntity clone() {
		return new StatusComponentEntity(this);
	}

	/**
	 * Initialize a {@link StatusComponentEntity} instance.
	 *
	 * @param id			identifier
	 * @param name			name
	 * @param status		status
	 * @param createDate	create date
	 * @param updateDate	update date
	 * @param description	description
	 * @param pageId		page identifier
	 */
	private StatusComponentEntity(
			String id,
			String name,
			EStatus status,
			DateTime createDate,
			DateTime updateDate,
			String description,
			String pageId
	) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.description = description;
		this.pageId = pageId;

		this.initMutexObjects();
	}

	/**
	 * Initialize a {@link StatusComponentEntity} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private StatusComponentEntity(StatusComponentEntity that) {
		this(
				that.id,
				that.name,
				that.status,
				that.createDate,
				that.updateDate,
				that.description,
				that.pageId
		);

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}
}
