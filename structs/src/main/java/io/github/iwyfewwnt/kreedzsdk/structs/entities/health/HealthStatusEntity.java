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

package io.github.iwyfewwnt.kreedzsdk.structs.entities.health;

import com.google.gson.annotations.SerializedName;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A kreedz health API status entity.
 */
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod", "SynchronizeOnNonFinalField"})
public final class HealthStatusEntity implements Serializable, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = HealthStatusEntity.class.getSimpleName();

	/**
	 * A response code.
	 */
	@SerializedName("status")
	private final Integer code;

	/**
	 * A host name.
	 */
	@SerializedName("hostname")
	private final String hostName;

	/**
	 * A duration.
	 */
	@SerializedName("duration")
	private final Long duration;

	/**
	 * A list of conditions.
	 */
	@SerializedName("conditionResults")
	private final List<HealthConditionEntity> conditions;

	/**
	 * An "isSuccessful" boolean value.
	 *
	 * <p>Determines whether the conditions were met successfully.
	 */
	@SerializedName("success")
	private final Boolean isSuccessful;

	/**
	 * A check date.
	 */
	@SerializedName("timestamp")
	private final DateTime date;

	/**
	 * A {@link HealthStatusEntity#hashCode()} cache.
	 */
	private transient volatile Integer hashCodeCache;

	/**
	 * A {@link HealthStatusEntity#toString()} cache.
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
	 * Get this response code.
	 *
	 * @return	response code
	 */
	public Integer getCode() {
		return this.code;
	}

	/**
	 * Get this host name.
	 *
	 * @return	host name
	 */
	public String getHostName() {
		return this.hostName;
	}

	/**
	 * Get this duration
	 *
	 * @return	duration
	 */
	public Long getDuration() {
		return this.duration;
	}

	/**
	 * Get this list of conditions.
	 *
	 * @return	list of conditions
	 */
	public List<HealthConditionEntity> getConditions() {
		return this.conditions;
	}

	/**
	 * Get this "isSuccessful" boolean value.
	 *
	 * @return	"isSuccessful" boolean value
	 */
	public Boolean getIsSuccessful() {
		return this.isSuccessful;
	}

	/**
	 * Get this check date.
	 *
	 * @return	check date
	 */
	public DateTime getDate() {
		return this.date;
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

		HealthStatusEntity that = (HealthStatusEntity) obj;

		return Objects.equals(this.code, that.code)
				&& Objects.equals(this.hostName, that.hostName)
				&& Objects.equals(this.duration, that.duration)
				&& Objects.equals(this.conditions, that.conditions)
				&& Objects.equals(this.isSuccessful, that.isSuccessful)
				&& Objects.equals(this.date, that.date);
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
							this.code,
							this.hostName,
							this.duration,
							this.conditions,
							this.isSuccessful,
							this.date
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
					+ "code=" + this.code
					+ ", hostName=\"" + this.hostName + "\""
					+ ", duration=" + this.duration
					+ ", conditions=" + this.conditions
					+ ", isSuccessful=" + this.isSuccessful
					+ ", date=" + this.date
					+ "]");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HealthStatusEntity clone() {
		return new HealthStatusEntity(this);
	}

	/**
	 * Initialize a {@link HealthStatusEntity} instance.
	 *
	 * @param code			response code
	 * @param hostName		host name
	 * @param duration		duration
	 * @param conditions	list of conditions
	 * @param isSuccessful	"isSuccessful" boolean value
	 * @param date			check date
	 */
	private HealthStatusEntity(
			Integer code,
			String hostName,
			Long duration,
			List<HealthConditionEntity> conditions,
			Boolean isSuccessful,
			DateTime date
	) {
		this.code = code;
		this.hostName = hostName;
		this.duration = duration;
		this.conditions = conditions;
		this.isSuccessful = isSuccessful;
		this.date = date;

		this.initMutexObjects();
	}

	/**
	 * Initialize a {@link HealthStatusEntity} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private HealthStatusEntity(HealthStatusEntity that) {
		this(
				that.code,
				that.hostName,
				that.duration,
				that.conditions,
				that.isSuccessful,
				that.date
		);

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}
}
