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

import java.io.Serializable;
import java.util.Objects;

/**
 * A kreedz health API condition entity.
 */
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod", "SynchronizeOnNonFinalField"})
public final class HealthConditionEntity implements Serializable, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = HealthConditionEntity.class.getSimpleName();

	/**
	 * A condition.
	 */
	@SerializedName("condition")
	private final String condition;

	/**
	 * An "isSuccessful" boolean value.
	 *
	 * <p>Determines whether the condition was met successfully.
	 */
	@SerializedName("success")
	private final Boolean isSuccessful;

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
	 * Get this condition.
	 *
	 * @return	condition
	 */
	public String getCondition() {
		return this.condition;
	}

	/**
	 * Get this "isSuccessful" boolean value
	 *
	 * @return	"isSuccessful" boolean value
	 */
	public Boolean getIsSuccessful() {
		return this.isSuccessful;
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

		HealthConditionEntity that = (HealthConditionEntity) obj;

		return Objects.equals(this.condition, that.condition)
				&& Objects.equals(this.isSuccessful, that.isSuccessful);
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
							this.condition,
							this.isSuccessful
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
					+ "condition=\"" + this.condition + "\""
					+ ", isSuccessful=" + this.isSuccessful
					+ "]");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HealthConditionEntity clone() {
		return new HealthConditionEntity(this);
	}

	/**
	 * Initialize a {@link HealthConditionEntity} instance.
	 *
	 * @param condition		condition
	 * @param isSuccessful	"isSuccessful" boolean value
	 */
	private HealthConditionEntity(
			String condition,
			Boolean isSuccessful
	) {
		this.condition = condition;
		this.isSuccessful = isSuccessful;

		this.initMutexObjects();
	}

	/**
	 * Initialize a {@link HealthConditionEntity} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private HealthConditionEntity(HealthConditionEntity that) {
		this(
				that.condition,
				that.isSuccessful
		);

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}
}
