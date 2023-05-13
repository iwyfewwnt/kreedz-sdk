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
import io.github.iwyfewwnt.kreedzsdk.structs.types.status.EStatusIndicator;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Objects;

/**
 * A kreedz status API scheduled incident entity.
 */
@SuppressWarnings({"unused", "SynchronizeOnNonFinalField"})
public final class StatusScheduledIncidentEntity extends StatusIncidentEntity implements Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = StatusScheduledIncidentEntity.class.getSimpleName();

	/**
	 * A schedule for date.
	 */
	@SerializedName("scheduled_for")
	private final DateTime scheduleForDate;

	/**
	 * A schedule until date.
	 */
	@SerializedName("scheduled_until")
	private final DateTime scheduleUntilDate;

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
	 * Get this schedule for date.
	 *
	 * @return	schedule for date
	 */
	public DateTime getScheduleForDate() {
		return this.scheduleForDate;
	}

	/**
	 * Get this schedule until date.
	 *
	 * @return	schedule until date
	 */
	public DateTime getScheduleUntilDate() {
		return this.scheduleUntilDate;
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

		StatusScheduledIncidentEntity that = (StatusScheduledIncidentEntity) obj;

		return super.equals(that)
				&& Objects.equals(this.scheduleForDate, that.scheduleForDate)
				&& Objects.equals(this.scheduleUntilDate, that.scheduleUntilDate);
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
							this.monitorDate,
							this.resolveDate,
							this.impact,
							this.shortUrl,
							this.pageId,
							this.updates,
							this.components,
							this.scheduleForDate,
							this.scheduleUntilDate
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
					+ ", monitorDate=" + this.monitorDate
					+ ", resolveDate=" + this.resolveDate
					+ ", impact=" + this.impact
					+ ", shortUrl=\"" + this.shortUrl + "\""
					+ ", pageId=\"" + this.pageId + "\""
					+ ", updates=" + this.updates
					+ ", components=" + this.components
					+ ", scheduleForDate=" + this.scheduleForDate
					+ ", scheduleUntilDate=" + this.scheduleUntilDate
					+ "]");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StatusScheduledIncidentEntity clone() {
		return new StatusScheduledIncidentEntity(this);
	}

	/**
	 * Initialize a {@link StatusScheduledIncidentEntity} instance.
	 *
	 * @param id					identifier
	 * @param name					name
	 * @param status				status
	 * @param createDate			create date
	 * @param updateDate			update date
	 * @param monitorDate			monitor date
	 * @param resolveDate			resolve date
	 * @param impact				impact
	 * @param shortUrl				short URL
	 * @param pageId				page identifier
	 * @param updates				list of updates
	 * @param components			list of components
	 * @param scheduleForDate		schedule for date
	 * @param scheduleUntilDate		schedule until date
	 */
	private StatusScheduledIncidentEntity(
			String id,
			String name,
			EStatus status,
			DateTime createDate,
			DateTime updateDate,
			DateTime monitorDate,
			DateTime resolveDate,
			EStatusIndicator impact,
			String shortUrl,
			String pageId,
			List<StatusUpdateEntity> updates,
			List<StatusComponentEntity> components,
			DateTime scheduleForDate,
			DateTime scheduleUntilDate
	) {
		super(
				id,
				name,
				status,
				createDate,
				updateDate,
				monitorDate,
				resolveDate,
				impact,
				shortUrl,
				pageId,
				updates,
				components
		);

		this.scheduleForDate = scheduleForDate;
		this.scheduleUntilDate = scheduleUntilDate;
	}

	/**
	 * Initialize a {@link StatusScheduledIncidentEntity} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private StatusScheduledIncidentEntity(StatusScheduledIncidentEntity that) {
		this(
				that.id,
				that.name,
				that.status,
				that.createDate,
				that.updateDate,
				that.monitorDate,
				that.resolveDate,
				that.impact,
				that.shortUrl,
				that.pageId,
				that.updates,
				that.components,
				that.scheduleForDate,
				that.scheduleUntilDate
		);

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}
}
