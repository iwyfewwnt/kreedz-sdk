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

import io.github.iwyfewwnt.uwutils.UwList;
import io.github.iwyfewwnt.uwutils.UwObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A scroll pattern representation.
 */
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod", "SynchronizeOnNonFinalField"})
public final class ScrollPattern implements Serializable, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = ScrollPattern.class.getSimpleName();

	/**
	 * A list of jump inputs.
	 */
	private final List<JumpInput> jumpInputs;

	/**
	 * A {@link #getPerfJumpCount()} cache.
	 */
	private transient volatile Integer perfJumpCountCache;

	/**
	 * A {@link #getPerfJumpRatio()} cache.
	 */
	private transient volatile Float perfJumpRatioCache;

	/**
	 * A {@link #getTotalPreInputCount()} cache.
	 */
	private transient volatile Integer totalPreInputCountCache;

	/**
	 * A {@link #getTotalPostInputCount()} cache.
	 */
	private transient volatile Integer totalPostInputCountCache;

	/**
	 * A {@link #getTotalInputCount()} cache.
	 */
	private transient volatile Integer totalInputCountCache;

	/**
	 * A {@link #getAvgPreInputCount()} cache.
	 */
	private transient volatile Float avgPreInputCountCache;

	/**
	 * A {@link #getAvgPostInputCount()} cache.
	 */
	private transient volatile Float avgPostInputCountCache;

	/**
	 * A {@link #getTotalJumpCount()} cache.
	 */
	private transient volatile Float avgTotalInputCountCache;

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
	 * A {@link #perfJumpCountCache} mutex.
	 */
	private transient Object perfJumpCountCacheMutex;

	/**
	 * A {@link #perfJumpRatioCache} mutex.
	 */
	private transient Object perfJumpRatioCacheMutex;

	/**
	 * A {@link #totalPreInputCountCache} mutex.
	 */
	private transient Object totalPreInputCountCacheMutex;

	/**
	 * A {@link #totalPostInputCountCache} mutex.
	 */
	private transient Object totalPostInputCountCacheMutex;

	/**
	 * A {@link #totalInputCountCache} mutex.
	 */
	private transient Object totalInputCountCacheMutex;

	/**
	 * A {@link #avgPreInputCountCache} mutex.
	 */
	private transient Object avgPreInputCountCacheMutex;

	/**
	 * A {@link #avgPostInputCountCache} mutex.
	 */
	private transient Object avgPostInputCountCacheMutex;

	/**
	 * A {@link #avgTotalInputCountCache} mutex.
	 */
	private transient Object avgTotalInputCountCacheMutex;

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
		this.perfJumpCountCacheMutex = new Object();
		this.perfJumpRatioCacheMutex = new Object();
		this.totalPreInputCountCacheMutex = new Object();
		this.totalPostInputCountCacheMutex = new Object();
		this.totalInputCountCacheMutex = new Object();
		this.avgPreInputCountCacheMutex = new Object();
		this.avgPostInputCountCacheMutex = new Object();
		this.avgTotalInputCountCacheMutex = new Object();
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
	 * Initialize a {@link ScrollPattern} instance.
	 *
	 * <p>Wraps {@link #ScrollPattern(List)}
	 * w/ {@code null} as the list of jump inputs.
	 */
	public ScrollPattern() {
		this((List<JumpInput>) null);
	}

	/**
	 * Initialize a {@link ScrollPattern} instance.
	 *
	 * <p>Wraps {@link #ScrollPattern(List)}.
	 *
	 * @param jumpInputs	array of jump inputs
	 */
	public ScrollPattern(JumpInput[] jumpInputs) {
		this((List<JumpInput>) UwObject.ifNotNull(jumpInputs, Arrays::asList));
	}

	/**
	 * Initialize a {@link ScrollPattern} instance.
	 *
	 * @param jumpInputs	list of jump inputs
	 */
	public ScrollPattern(List<JumpInput> jumpInputs) {
		jumpInputs = UwObject.ifNull(jumpInputs, ArrayList::new);
		jumpInputs = UwList.toUnmodifiable(jumpInputs);

		this.jumpInputs = jumpInputs;

		this.initMutexObjects();
	}

	/**
	 * Initialize a {@link ScrollPattern} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private ScrollPattern(ScrollPattern that) {
		this(that.jumpInputs);

		this.perfJumpCountCache = that.perfJumpCountCache;
		this.perfJumpRatioCache = that.perfJumpRatioCache;
		this.totalPreInputCountCache = that.totalPreInputCountCache;
		this.totalPostInputCountCache = that.totalPostInputCountCache;
		this.totalInputCountCache = that.totalInputCountCache;
		this.avgPreInputCountCache = that.avgPreInputCountCache;
		this.avgPostInputCountCache = that.avgPostInputCountCache;
		this.avgTotalInputCountCache = that.avgTotalInputCountCache;
		this.gokzStringCache = that.gokzStringCache;
		this.kztimerStringCache = that.kztimerStringCache;

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}

	/**
	 * Get this list of jump inputs.
	 *
	 * @return	list of jump inputs
	 */
	public List<JumpInput> getJumpInputs() {
		return this.jumpInputs;
	}

	/**
	 * Get this total jump count.
	 *
	 * @return	total jump count
	 */
	public int getTotalJumpCount() {
		return this.jumpInputs.size();
	}

	/**
	 * Get this perf-jump count.
	 *
	 * @return	perf-jump count
	 */
	public int getPerfJumpCount() {
		if (this.perfJumpCountCache != null) {
			return this.perfJumpCountCache;
		}

		synchronized (this.perfJumpCountCacheMutex) {
			if (this.perfJumpCountCache != null) {
				return this.perfJumpCountCache;
			}

			return (this.perfJumpCountCache = (int) this.jumpInputs.stream()
					.filter(JumpInput::isPerf)
					.count());
		}
	}

	/**
	 * Get this perf-jump ratio.
	 *
	 * <p>From 0 to 1.
	 *
	 * @return	perf-jump ratio
	 */
	public float getPerfJumpRatio() {
		if (this.perfJumpRatioCache != null) {
			return this.perfJumpRatioCache;
		}

		synchronized (this.perfJumpRatioCacheMutex) {
			if (this.perfJumpRatioCache != null) {
				return this.perfJumpRatioCache;
			}

			int totalJumpCount = this.getTotalJumpCount();
			if (totalJumpCount == 0) {
				return (this.perfJumpRatioCache = 0f);
			}

			int perfJumpCount = this.getPerfJumpCount();
			return (this.perfJumpRatioCache = (float) perfJumpCount / totalJumpCount);
		}
	}

	/**
	 * Get this total pre-input count.
	 *
	 * @return	total pre-input count.
	 */
	public int getTotalPreInputCount() {
		if (this.totalPreInputCountCache != null) {
			return this.totalPreInputCountCache;
		}

		synchronized (this.totalPreInputCountCacheMutex) {
			if (this.totalPreInputCountCache != null) {
				return this.totalPreInputCountCache;
			}

			return (this.totalPreInputCountCache = this.jumpInputs.stream()
					.mapToInt(JumpInput::getPreInputCount)
					.sum());
		}
	}

	/**
	 * Get this total post-input count.
	 *
	 * @return	total post-input count.
	 */
	public int getTotalPostInputCount() {
		if (this.totalPostInputCountCache != null) {
			return this.totalPostInputCountCache;
		}

		synchronized (this.totalPostInputCountCacheMutex) {
			if (this.totalPostInputCountCache != null) {
				return this.totalPostInputCountCache;
			}

			return (this.totalPostInputCountCache = this.jumpInputs.stream()
					.mapToInt(JumpInput::getPostInputCount)
					.sum());
		}
	}

	/**
	 * Get this total input count.
	 *
	 * @return	total input count
	 */
	public int getTotalInputCount() {
		if (this.totalInputCountCache != null) {
			return this.totalInputCountCache;
		}

		synchronized (this.totalInputCountCacheMutex) {
			if (this.totalInputCountCache != null) {
				return this.totalInputCountCache;
			}

			int totalPreInputCount = this.getTotalPreInputCount();
			int totalPostInputCount = this.getTotalPostInputCount();

			return (this.totalInputCountCache = totalPreInputCount + totalPostInputCount);
		}
	}

	/**
	 * Get this average pre-input count.
	 *
	 * @return	average pre-input count
	 */
	public float getAvgPreInputCount() {
		if (this.avgPreInputCountCache != null) {
			return this.avgPreInputCountCache;
		}

		synchronized (this.avgPreInputCountCacheMutex) {
			if (this.avgPreInputCountCache != null) {
				return this.avgPreInputCountCache;
			}

			int totalJumpCount = this.getTotalJumpCount();
			if (totalJumpCount == 0) {
				return (this.avgPreInputCountCache = 0f);
			}

			int totalPreInputCount = this.getTotalPreInputCount();
			return (this.avgPreInputCountCache = totalPreInputCount / (float) totalJumpCount);
		}
	}

	/**
	 * Get this average post-input count.
	 *
	 * @return	average post-input count
	 */
	public float getAvgPostInputCount() {
		if (this.avgPostInputCountCache != null) {
			return this.avgPostInputCountCache;
		}

		synchronized (this.avgPostInputCountCacheMutex) {
			if (this.avgPostInputCountCache != null) {
				return this.avgPostInputCountCache;
			}

			int totalJumpCount = this.getTotalJumpCount();
			if (totalJumpCount == 0) {
				return (this.avgPostInputCountCache = 0f);
			}

			int totalPostInputCount = this.getTotalPostInputCount();
			return (this.avgPostInputCountCache = totalPostInputCount / (float) totalJumpCount);
		}
	}

	/**
	 * Get this average total input count.
	 *
	 * @return	average total input count
	 */
	public float getAvgTotalInputCount() {
		if (this.avgTotalInputCountCache != null) {
			return this.avgTotalInputCountCache;
		}

		synchronized (this.avgTotalInputCountCacheMutex) {
			if (this.avgTotalInputCountCache != null) {
				return this.avgTotalInputCountCache;
			}

			int totalJumpCount = this.getTotalJumpCount();
			if (totalJumpCount == 0) {
				return (this.avgTotalInputCountCache = 0f);
			}

			int totalInputCount = this.getTotalInputCount();
			return (this.avgTotalInputCountCache = totalInputCount / (float) totalJumpCount);
		}
	}

	/**
	 * Convert this to a GOKZ string representation.
	 *
	 * @return	string representation, always not null
	 */
	public String toGokzString() {
		if (this.gokzStringCache != null) {
			return this.gokzStringCache;
		}

		synchronized (this.gokzStringCacheMutex) {
			if (this.gokzStringCache != null) {
				return this.gokzStringCache;
			}

			return (this.gokzStringCache = this.jumpInputs.stream()
					.map(JumpInput::toGokzString)
					.collect(Collectors.joining()));
		}
	}

	/**
	 * Convert this to a KZTimer string representation.
	 *
	 * @return	string representation, always not null
	 */
	public String toKztimerString() {
		if (this.kztimerStringCache != null) {
			return this.kztimerStringCache;
		}

		synchronized (this.kztimerStringCacheMutex) {
			if (this.kztimerStringCache != null) {
				return this.kztimerStringCache;
			}

			return (this.kztimerStringCache = this.jumpInputs.stream()
					.map(JumpInput::toKztimerString)
					.collect(Collectors.joining(" ")));
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

		ScrollPattern that = (ScrollPattern) obj;

		return Objects.equals(this.jumpInputs, that.jumpInputs);
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
					= Objects.hash(this.jumpInputs));
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
					+ "jumpInputs=" + this.jumpInputs
					+ "]");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ScrollPattern clone() {
		return new ScrollPattern(this);
	}
}
