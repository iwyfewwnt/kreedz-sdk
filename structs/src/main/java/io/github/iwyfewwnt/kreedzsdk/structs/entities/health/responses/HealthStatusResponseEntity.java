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

package io.github.iwyfewwnt.kreedzsdk.structs.entities.health.responses;

import com.google.gson.annotations.SerializedName;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.health.HealthStatusEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A kreedz health API status response entity.
 */
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod", "SynchronizeOnNonFinalField"})
public final class HealthStatusResponseEntity implements Serializable, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = HealthStatusResponseEntity.class.getSimpleName();

	/**
	 * A name.
	 */
	@SerializedName("name")
	private final String name;

	/**
	 * A key.
	 */
	@SerializedName("key")
	private final String key;

	/**
	 * A list of statuses.
	 */
	@SerializedName("results")
	private final List<HealthStatusEntity> results;

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
	 * Get this name
	 *
	 * @return	name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Get this key
	 *
	 * @return	key
	 */
	public String getKey() {
		return this.key;
	}

	/**
	 * Get this list of statuses.
	 *
	 * @return	list of statuses
	 */
	public List<HealthStatusEntity> getResults() {
		return this.results;
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

		HealthStatusResponseEntity that = (HealthStatusResponseEntity) obj;

		return Objects.equals(this.name, that.name)
				&& Objects.equals(this.key, that.key)
				&& Objects.equals(this.results, that.results);
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
							this.name,
							this.key,
							this.results
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
					+ "name=\"" + this.name + "\""
					+ ", key=\"" + this.key + "\""
					+ ", results=" + this.results
					+ "]");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HealthStatusResponseEntity clone() {
		return new HealthStatusResponseEntity(this);
	}

	/**
	 * Initialize a {@link HealthStatusResponseEntity} instance.
	 *
	 * @param name		name
	 * @param key		key
	 * @param results	list of statuses
	 */
	private HealthStatusResponseEntity(
			String name,
			String key,
			List<HealthStatusEntity> results
	) {
		this.name = name;
		this.key = key;
		this.results = results;

		this.initMutexObjects();
	}

	/**
	 * Initialize a {@link HealthStatusResponseEntity} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private HealthStatusResponseEntity(HealthStatusResponseEntity that) {
		this(
				that.name,
				that.key,
				that.results
		);

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}
}
