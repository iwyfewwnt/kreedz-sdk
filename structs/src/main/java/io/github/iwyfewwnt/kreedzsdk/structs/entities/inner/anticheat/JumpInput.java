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

package io.github.iwyfewwnt.kreedzsdk.structs.entities.inner.anticheat;

import java.io.Serializable;
import java.util.Objects;

/**
 * A jump input representation.
 */
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod", "SynchronizeOnNonFinalField"})
public final class JumpInput implements Serializable, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = JumpInput.class.getSimpleName();

	/**
	 * A perf-jump character.
	 */
	public static final char PERF_JUMP_CHAR = '*';

	/**
	 * A normal jump character.
	 */
	public static final char NORMAL_JUMP_CHAR = ' ';

	/**
	 * A perf-jump character as a string.
	 */
	public static final String PERF_JUMP_CHAR_STRING = String.valueOf(PERF_JUMP_CHAR);

	/**
	 * A normal jump character as a string.
	 */
	public static final String NORMAL_JUMP_CHAR_STRING = String.valueOf(NORMAL_JUMP_CHAR);

	/**
	 * A GOKZ jump input format string.
	 *
	 * <p>Arguments in order:
	 * <ul>
	 *     <li>Integer :: Pre-input count.
	 *     <li>Character :: Jump character.
	 *     <li>Integer :: Post-input count.
	 * </ul>
	 */
	public static final String GOKZ_FMT = "(%d%c%d)";

	/**
	 * A KZTimer jump input format string.
	 *
	 * <p>Arguments in order:
	 * <ul>
	 *     <li>Integer :: Pre-input count.
	 * </ul>
	 */
	public static final String KZTIMER_FMT = "%d";

	/**
	 * A pre-input count.
	 */
	private final int preInputCount;

	/**
	 * A post-input count.
	 */
	private final int postInputCount;

	/**
	 * An "isPerf" boolean value.
	 *
	 * <p>Determines if this jump is a perf-jump.
	 */
	private final boolean isPerf;

	/**
	 * A {@link #toGokzString()} cache.
	 */
	private transient volatile String gokzStringCache;

	/**
	 * A {@link #toKztimerString()} cache.
	 */
	private transient volatile String kztimerStringCache;

	/**
	 * A {@link #hashCode()} cache.
	 */
	private transient volatile Integer hashCodeCache;

	/**
	 * A {@link #toString()} cache.
	 */
	private transient volatile String stringCache;

	/**
	 * A {@link #gokzStringCache} mutex.
	 */
	private transient Object gokzStringCacheMutex;

	/**
	 * A {@link #kztimerStringCache} mutex.
	 */
	private transient Object kztimerStringCacheMutex;

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
		this.gokzStringCacheMutex = new Object();
		this.kztimerStringCacheMutex = new Object();
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
	 * Initialize a {@link JumpInput} instance.
	 *
	 * @param preInputCount		pre-input count
	 * @param postInputCount	post-input count
	 * @param isPerf			"isPerf" boolean value
	 */
	public JumpInput(int preInputCount, int postInputCount, boolean isPerf) {
		this.preInputCount = preInputCount;
		this.postInputCount = postInputCount;
		this.isPerf = isPerf;

		this.initMutexObjects();
	}

	/**
	 * Initialize a {@link JumpInput} instance.
	 *
	 * <p>Wraps {@link JumpInput#JumpInput(int, int, boolean)}
	 * w/ {@code 0} {@literal &} {@code true} as the post-input
	 * count and "isPerf" boolean value.
	 *
	 * @param inputCount	pre-input count
	 */
	public JumpInput(int inputCount) {
		this(inputCount, 0, true);
	}

	/**
	 * Initialize a {@link JumpInput} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private JumpInput(JumpInput that) {
		this(
				that.preInputCount,
				that.postInputCount,
				that.isPerf
		);

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}

	/**
	 * Get this pre-input count.
	 *
	 * @return	pre-input count
	 */
	public int getPreInputCount() {
		return this.preInputCount;
	}

	/**
	 * Get this post-input count.
	 *
	 * @return	post-input count
	 */
	public int getPostInputCount() {
		return this.postInputCount;
	}

	/**
	 * Check if this jump is a perf-jump.
	 *
	 * @return	boolean value as a result,
	 * 			true - yes, false - no
	 */
	public boolean isPerf() {
		return this.isPerf;
	}

	/**
	 * Check if this jump is a bind-jump.
	 *
	 * @return	boolean value as a result,
	 * 			true - yes, false - no
	 */
	public boolean isBind() {
		return this.preInputCount == -1
				&& this.postInputCount == 0;
	}

	/**
	 * Get this jump character.
	 *
	 * @return	jump character.
	 */
	public char getJumpChar() {
		return this.isPerf ? PERF_JUMP_CHAR
				: NORMAL_JUMP_CHAR;
	}

	/**
	 * Convert this instance to a GOKZ string representation.
	 *
	 * @return	string representation
	 */
	public String toGokzString() {
		if (this.gokzStringCache != null) {
			return this.gokzStringCache;
		}

		synchronized (this.gokzStringCacheMutex) {
			if (this.gokzStringCache != null) {
				return this.gokzStringCache;
			}

			char jumpChar = this.getJumpChar();

			return (this.gokzStringCache = String.format(GOKZ_FMT, this.preInputCount, jumpChar, this.postInputCount));
		}
	}

	/**
	 * Convert this instance to a KZTimer string representation.
	 *
	 * @return	string representation
	 */
	public String toKztimerString() {
		if (this.kztimerStringCache != null) {
			return this.kztimerStringCache;
		}

		synchronized (this.kztimerStringCacheMutex) {
			if (this.kztimerStringCache != null) {
				return this.kztimerStringCache;
			}

			return (this.kztimerStringCache = String.format(KZTIMER_FMT, this.preInputCount));
		}
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

		JumpInput jumpInput = (JumpInput) obj;

		return this.preInputCount == jumpInput.preInputCount
				&& this.postInputCount == jumpInput.postInputCount
				&& this.isPerf == jumpInput.isPerf;
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
							this.preInputCount,
							this.postInputCount,
							this.isPerf
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
					+ "preInputCount=" + this.preInputCount
					+ ", postInputCount=" + this.postInputCount
					+ ", isPerf=" + this.isPerf
					+ "]");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JumpInput clone() {
		return new JumpInput(this);
	}
}
