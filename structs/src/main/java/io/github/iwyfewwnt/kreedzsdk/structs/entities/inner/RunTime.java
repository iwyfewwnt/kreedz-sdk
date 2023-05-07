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
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod"})
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
	 * A {@link RunTime#getTotalMilliseconds()} cache.
	 */
	private transient Integer totalMillisecondsCache;

	/**
	 * A {@link RunTime#getTotalSeconds()} cache.
	 */
	private transient Integer totalSecondsCache;

	/**
	 * A {@link RunTime#getTotalMinutes()} cache.
	 */
	private transient Integer totalMinutesCache;

	/**
	 * A {@link RunTime#getTotalHours()} cache.
	 */
	private transient Integer totalHoursCache;

	/**
	 * A {@link RunTime#getMilliseconds()} cache.
	 */
	private transient Integer millisecondsCache;

	/**
	 * A {@link RunTime#getSeconds()} cache.
	 */
	private transient Integer secondsCache;

	/**
	 * A {@link RunTime#getMinutes()} cache.
	 */
	private transient Integer minutesCache;

	/**
	 * A {@link RunTime#getHours()} cache.
	 */
	private transient Integer hoursCache;

	/**
	 * A {@link RunTime#hashCode()} cache.
	 */
	private transient Integer hashCodeCache;

	/**
	 * A {@link RunTime#toString()} cache.
	 */
	private transient String stringCache;

	/**
	 * Initialize a {@link RunTime} instance.
	 *
	 * @param time	total time in seconds
	 */
	public RunTime(Float time) {
		this.time = UwObject.ifNull(time, 0f);
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

		this.totalMillisecondsCache = that.totalMillisecondsCache;
		this.totalSecondsCache = that.totalSecondsCache;
		this.totalMinutesCache = that.totalMinutesCache;
		this.totalHoursCache = that.totalHoursCache;

		this.millisecondsCache = that.millisecondsCache;
		this.secondsCache = that.secondsCache;
		this.minutesCache = that.minutesCache;
		this.hoursCache = that.hoursCache;

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
		if (this.totalMillisecondsCache != null) {
			return this.totalMillisecondsCache;
		}

		return (this.totalMillisecondsCache = (int) (this.time * 1000));
	}

	/**
	 * Get this total seconds.
	 *
	 * <p>From 0 to +inf.
	 *
	 * @return	total seconds
	 */
	public int getTotalSeconds() {
		if (this.totalSecondsCache != null) {
			return this.totalSecondsCache;
		}

		return (this.totalSecondsCache = (int) this.time);
	}

	/**
	 * Get this total minutes.
	 *
	 * <p>From 0 to +inf.
	 *
	 * @return	total minutes
	 */
	public int getTotalMinutes() {
		if (this.totalMinutesCache != null) {
			return this.totalMinutesCache;
		}

		int totalSeconds = this.getTotalSeconds();
		return (this.totalMinutesCache = totalSeconds / 60);
	}

	/**
	 * Get this total hours.
	 *
	 * <p>From 0 to +inf.
	 *
	 * @return	total hours
	 */
	public int getTotalHours() {
		if (this.totalHoursCache != null) {
			return this.totalHoursCache;
		}

		int totalMinutes = this.getTotalMinutes();
		return (this.totalHoursCache = totalMinutes / 60);
	}

	/**
	 * Get this milliseconds.
	 *
	 * <p>From 0 to 1000.
	 *
	 * @return	milliseconds
	 */
	public int getMilliseconds() {
		if (this.millisecondsCache != null) {
			return this.millisecondsCache;
		}

		int totalMilliseconds = this.getTotalMilliseconds();
		return (this.millisecondsCache = totalMilliseconds % 1000);
	}

	/**
	 * Get this seconds.
	 *
	 * <p>From 0 to 60.
	 *
	 * @return	seconds
	 */
	public int getSeconds() {
		if (this.secondsCache != null) {
			return this.secondsCache;
		}

		int totalSeconds = this.getTotalSeconds();
		return (this.secondsCache = totalSeconds % 60);
	}

	/**
	 * Get this minutes.
	 *
	 * <p>From 0 to 60.
	 *
	 * @return	minutes
	 */
	public int getMinutes() {
		if (this.minutesCache != null) {
			return this.minutesCache;
		}

		int totalMinutes = this.getTotalMinutes();
		return (this.minutesCache = totalMinutes % 60);
	}

	/**
	 * Get this hours.
	 *
	 * <p>From 0 to 24.
	 *
	 * @return	hours
	 */
	public int getHours() {
		if (this.hoursCache != null) {
			return this.hoursCache;
		}

		int totalHours = this.getTotalHours();
		return (this.hoursCache = totalHours % 24);
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

		return (this.hashCodeCache
				= Objects.hash(this.time));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RunTime clone() {
		return new RunTime(this);
	}
}
