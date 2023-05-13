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

import java.io.Serializable;
import java.util.Objects;

/**
 * A kreedz status API affected component entity.
 */
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod", "SynchronizeOnNonFinalField"})
public final class StatusAffectedComponentEntity implements Serializable, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = StatusAffectedComponentEntity.class.getSimpleName();

	/**
	 * An identifier.
	 */
	@SerializedName("code")
	private final String id;

	/**
	 * A name.
	 */
	@SerializedName("name")
	private final String name;

	/**
	 * A previous status.
	 */
	@SerializedName("old_status")
	private final EStatus prevStatus;

	/**
	 * A current status.
	 */
	@SerializedName("new_status")
	private final EStatus currStatus;

	/**
	 * A {@link StatusAffectedComponentEntity#hashCode()} cache.
	 */
	private transient volatile Integer hashCodeCache;

	/**
	 * A {@link StatusAffectedComponentEntity#toString()} cache.
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
	 * Get this previous status.
	 *
	 * @return	previous status
	 */
	public EStatus getPreviousStatus() {
		return this.prevStatus;
	}

	/**
	 * Get this current status.
	 *
	 * @return	current status
	 */
	public EStatus getCurrentStatus() {
		return this.currStatus;
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

		StatusAffectedComponentEntity that = (StatusAffectedComponentEntity) obj;

		return Objects.equals(this.id, that.id)
				&& Objects.equals(this.name, that.name)
				&& this.prevStatus == that.prevStatus
				&& this.currStatus == that.currStatus;
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
							this.prevStatus,
							this.currStatus
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
					+ ", prevStatus=" + this.prevStatus
					+ ", currStatus=" + this.currStatus
					+ "]");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StatusAffectedComponentEntity clone() {
		return new StatusAffectedComponentEntity(this);
	}

	/**
	 * Initialize a {@link StatusAffectedComponentEntity} instance.
	 *
	 * @param id			identifier
	 * @param name			name
	 * @param prevStatus	previous status
	 * @param currStatus	current status
	 */
	private StatusAffectedComponentEntity(
			String id,
			String name,
			EStatus prevStatus,
			EStatus currStatus
	) {
		this.id = id;
		this.name = name;
		this.prevStatus = prevStatus;
		this.currStatus = currStatus;

		this.initMutexObjects();
	}

	/**
	 * Initialize a {@link StatusAffectedComponentEntity} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private StatusAffectedComponentEntity(StatusAffectedComponentEntity that) {
		this(
				that.id,
				that.name,
				that.prevStatus,
				that.currStatus
		);

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}
}
