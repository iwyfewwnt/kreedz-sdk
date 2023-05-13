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

package io.github.iwyfewwnt.kreedzsdk.structs.entities.inner;

import io.github.iwyfewwnt.uwutils.UwObject;

import java.io.Serializable;
import java.util.Objects;

/**
 * A kreedz API map run time representation.
 */
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod", "SynchronizeOnNonFinalField"})
public final class RunTime implements Serializable, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = RunTime.class.getSimpleName();

	/**
	 * A total time in seconds.
	 */
	private final float time;

	/**
	 * A {@link RunTime#hashCode()} cache.
	 */
	private transient volatile Integer hashCodeCache;

	/**
	 * A {@link RunTime#toString()} cache.
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
	 * Initialize a {@link RunTime} instance.
	 *
	 * @param time	total time in seconds
	 */
	public RunTime(Float time) {
		this.time = UwObject.ifNull(time, 0f);

		this.initMutexObjects();
	}

	/**
	 * Initialize a {@code Time} instance.
	 *
	 * <p>Wraps {@link RunTime#RunTime(Float)}
	 * w/ {@code null} as the time.
	 */
	public RunTime() {
		this((Float) null);
	}

	/**
	 * Initialize a {@link}
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private RunTime(RunTime that) {
		this(that.time);

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}

	/**
	 * Check if this time is valid.
	 *
	 * @return	boolean value, {@code true} - yes, {@code false} - no
	 */
	public boolean isValid() {
		return this.time != 0;
	}

	/**
	 * Get this total milliseconds.
	 *
	 * <p>From 0 to +inf.
	 *
	 * @return	total milliseconds
	 */
	public int getTotalMilliseconds() {
		return (int) (this.time * 1000);
	}

	/**
	 * Get this total seconds.
	 *
	 * <p>From 0 to +inf.
	 *
	 * @return	total seconds
	 */
	public int getTotalSeconds() {
		return (int) this.time;
	}

	/**
	 * Get this total minutes.
	 *
	 * <p>From 0 to +inf.
	 *
	 * @return	total minutes
	 */
	public int getTotalMinutes() {
		return this.getTotalSeconds() / 60;
	}

	/**
	 * Get this total hours.
	 *
	 * <p>From 0 to +inf.
	 *
	 * @return	total hours
	 */
	public int getTotalHours() {
		return this.getTotalMinutes() / 60;
	}

	/**
	 * Get this milliseconds.
	 *
	 * <p>From 0 to 1000.
	 *
	 * @return	milliseconds
	 */
	public int getMilliseconds() {
		return this.getTotalMilliseconds() % 1000;
	}

	/**
	 * Get this seconds.
	 *
	 * <p>From 0 to 60.
	 *
	 * @return	seconds
	 */
	public int getSeconds() {
		return this.getTotalSeconds() % 60;
	}

	/**
	 * Get this minutes.
	 *
	 * <p>From 0 to 60.
	 *
	 * @return	minutes
	 */
	public int getMinutes() {
		return this.getTotalMinutes() % 60;
	}

	/**
	 * Get this hours.
	 *
	 * <p>From 0 to 24.
	 *
	 * @return	hours
	 */
	public int getHours() {
		return this.getTotalHours() % 24;
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

		RunTime that = (RunTime) obj;

		return this.time == that.time;
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
					= Objects.hash(this.time));
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

			int totalHours = this.getTotalHours();
			int totalMinutes = this.getTotalMinutes();

			int minutes = this.getMinutes();
			int seconds = this.getSeconds();
			int milliseconds = this.getMilliseconds();

			if (totalHours != 0) {
				return (this.stringCache = String.format("%d:%02d:%02d.%03d", totalHours, minutes, seconds, milliseconds));
			}

			if (totalMinutes != 0) {
				return (this.stringCache = String.format("%d:%02d.%03d", minutes, seconds, milliseconds));
			}

			return (this.stringCache = String.format("%d.%03d", seconds, milliseconds));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RunTime clone() {
		return new RunTime(this);
	}
}
