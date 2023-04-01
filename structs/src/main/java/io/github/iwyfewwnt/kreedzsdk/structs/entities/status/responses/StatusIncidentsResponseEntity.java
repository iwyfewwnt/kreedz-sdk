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

package io.github.iwyfewwnt.kreedzsdk.structs.entities.status.responses;

import com.google.gson.annotations.SerializedName;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.status.StatusIncidentEntity;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.status.StatusPageEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A kreedz status API incidents response entity.
 */
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod"})
public final class StatusIncidentsResponseEntity implements Serializable, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = StatusIncidentsResponseEntity.class.getSimpleName();

	/**
	 * A page.
	 */
	@SerializedName("page")
	private final StatusPageEntity page;

	/**
	 * A list of incidents.
	 */
	@SerializedName("incidents")
	private final List<StatusIncidentEntity> incidents;

	/**
	 * A {@link StatusIncidentsResponseEntity#hashCode()} cache.
	 */
	private transient Integer hashCodeCache;

	/**
	 * A {@link StatusIncidentsResponseEntity#toString()} cache.
	 */
	private transient String stringCache;

	/**
	 * Get this page.
	 *
	 * @return	page
	 */
	public StatusPageEntity getPage() {
		return this.page;
	}

	/**
	 * Get this list of incidents.
	 *
	 * @return	list of incidents
	 */
	public List<StatusIncidentEntity> getIncidents() {
		return this.incidents;
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

		StatusIncidentsResponseEntity that = (StatusIncidentsResponseEntity) obj;

		return Objects.equals(this.page, that.page)
				&& Objects.equals(this.incidents, that.incidents);
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
						this.page,
						this.incidents
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
				+ "page=" + this.page
				+ ", incidents=" + this.incidents
				+ "]");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StatusIncidentsResponseEntity clone() {
		return new StatusIncidentsResponseEntity(this);
	}

	/**
	 * Initialize a {@link StatusIncidentsResponseEntity} instance.
	 *
	 * @param page			page
	 * @param incidents		list of incidents
	 */
	private StatusIncidentsResponseEntity(
			StatusPageEntity page,
			List<StatusIncidentEntity> incidents
	) {
		this.page = page;
		this.incidents = incidents;
	}

	/**
	 * Initialize a {@link StatusIncidentsResponseEntity} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private StatusIncidentsResponseEntity(StatusIncidentsResponseEntity that) {
		this(
				that.page,
				that.incidents
		);

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}
}
