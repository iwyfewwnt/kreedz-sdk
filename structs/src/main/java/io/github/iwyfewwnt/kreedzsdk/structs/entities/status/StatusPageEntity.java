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
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Objects;

/**
 * A kreedz status API page entity.
 */
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod"})
public final class StatusPageEntity implements Serializable, Cloneable {

	/**
	 * A simple name of this class
	 */
	private static final String SIMPLE_NAME = StatusPageEntity.class.getSimpleName();

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
	 * A URL.
	 */
	@SerializedName("url")
	private final String url;

	/**
	 * An update date.
	 */
	@SerializedName("updated_at")
	private final DateTime updateDate;

	/**
	 * A {@link StatusPageEntity#hashCode()} cache.
	 */
	private transient Integer hashCodeCache;

	/**
	 * A {@link StatusPageEntity#toString()} cache.
	 */
	private transient String stringCache;

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
	 * Get this URL.
	 *
	 * @return	URL
	 */
	public String getUrl() {
		return this.url;
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

		StatusPageEntity that = (StatusPageEntity) obj;

		return Objects.equals(this.id, that.id)
				&& Objects.equals(this.name, that.name)
				&& Objects.equals(this.url, that.url)
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
						this.name,
						this.url,
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
				+ "id=\"" + this.id + "\""
				+ ", name=\"" + this.name + "\""
				+ ", url=\"" + this.url + "\""
				+ ", updateDate=" + this.updateDate
				+ "]");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StatusPageEntity clone() {
		return new StatusPageEntity(this);
	}

	/**
	 * Initialize a {@link StatusPageEntity} instance.
	 *
	 * @param id			identifier
	 * @param name			name
	 * @param url			URL
	 * @param updateDate	update date
	 */
	private StatusPageEntity(
			String id,
			String name,
			String url,
			DateTime updateDate
	) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.updateDate = updateDate;
	}

	/**
	 * Initialize a {@link StatusPageEntity} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private StatusPageEntity(StatusPageEntity that) {
		this(
				that.id,
				that.name,
				that.url,
				that.updateDate
		);

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}
}
