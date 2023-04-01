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
import java.util.List;
import java.util.Objects;

/**
 * A kreedz status API incident update entity.
 */
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod"})
public final class StatusUpdateEntity implements Serializable, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = StatusUpdateEntity.class.getSimpleName();

	/**
	 * An identifier.
	 */
	@SerializedName("id")
	private final String id;

	/**
	 * A status.
	 */
	@SerializedName("status")
	private final EStatus status;

	/**
	 * A body.
	 */
	@SerializedName("body")
	private final String body;

	/**
	 * An incident identifier.
	 */
	@SerializedName("incident_id")
	private final String incidentId;

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
	 * A display date.
	 */
	@SerializedName("display_at")
	private final DateTime displayDate;

	/**
	 * A list of affected components.
	 */
	@SerializedName("affected_components")
	private final List<StatusAffectedComponentEntity> affectedComponents;

	/**
	 * A {@link StatusUpdateEntity#hashCode()} cache.
	 */
	private transient Integer hashCodeCache;

	/**
	 * A {@link StatusUpdateEntity#toString()} cache.
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
	 * Get this status.
	 *
	 * @return	status
	 */
	public EStatus getStatus() {
		return this.status;
	}

	/**
	 * Get this body.
	 *
	 * @return	body
	 */
	public String getBody() {
		return this.body;
	}

	/**
	 * Get this incident identifier.
	 *
	 * @return	incident identifier
	 */
	public String getIncidentId() {
		return this.incidentId;
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
	 * Get this display date.
	 *
	 * @return	display date
	 */
	public DateTime getDisplayDate() {
		return this.displayDate;
	}

	/**
	 * Get this list of affected components.
	 *
	 * @return	list of affected components
	 */
	public List<StatusAffectedComponentEntity> getAffectedComponents() {
		return this.affectedComponents;
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

		StatusUpdateEntity that = (StatusUpdateEntity) obj;

		return Objects.equals(this.id, that.id)
				&& this.status == that.status
				&& Objects.equals(this.body, that.body)
				&& Objects.equals(this.incidentId, that.incidentId)
				&& Objects.equals(this.createDate, that.createDate)
				&& Objects.equals(this.updateDate, that.updateDate)
				&& Objects.equals(this.displayDate, that.displayDate)
				&& Objects.equals(this.affectedComponents, that.affectedComponents);
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
						this.status,
						this.body,
						this.incidentId,
						this.createDate,
						this.updateDate,
						this.displayDate,
						this.affectedComponents
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
				+ ", status=" + this.status
				+ ", body=\"" + this.body + "\""
				+ ", incidentId=\"" + this.incidentId + "\""
				+ ", createDate=" + this.createDate
				+ ", updateDate=" + this.updateDate
				+ ", displayDate=" + this.displayDate
				+ ", affectedComponents=" + this.affectedComponents
				+ "]");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StatusUpdateEntity clone() {
		return new StatusUpdateEntity(this);
	}

	/**
	 * Initialize a {@link StatusUpdateEntity} instance.
	 *
	 * @param id					identifier
	 * @param status				status
	 * @param body					body
	 * @param incidentId			incident identifier
	 * @param createDate			create date
	 * @param updateDate			update date
	 * @param displayDate			display date
	 * @param affectedComponents	list of affected components
	 */
	private StatusUpdateEntity(
			String id,
			EStatus status,
			String body,
			String incidentId,
			DateTime createDate,
			DateTime updateDate,
			DateTime displayDate,
			List<StatusAffectedComponentEntity> affectedComponents
	) {
		this.id = id;
		this.status = status;
		this.body = body;
		this.incidentId = incidentId;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.displayDate = displayDate;
		this.affectedComponents = affectedComponents;
	}

	/**
	 * Initialize a {@link StatusUpdateEntity} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private StatusUpdateEntity(StatusUpdateEntity that) {
		this(
				that.id,
				that.status,
				that.body,
				that.incidentId,
				that.createDate,
				that.updateDate,
				that.displayDate,
				that.affectedComponents
		);

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}
}
