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
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod"})
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
	 * A {@link ScrollPattern#getPerfJumpCount()} cache.
	 */
	private transient Integer perfJumpCountCache;

	/**
	 * A {@link ScrollPattern#getPerfJumpRatio()} cache.
	 */
	private transient Float perfJumpRatioCache;

	/**
	 * A {@link ScrollPattern#getAvgPreInputCount()} cache.
	 */
	private transient Float avgPreInputCountCache;

	/**
	 * A {@link ScrollPattern#getAvgPostInputCount()} cache.
	 */
	private transient Float avgPostInputCountCache;

	/**
	 * A {@link ScrollPattern#getTotalJumpCount()} cache.
	 */
	private transient Float avgTotalInputCountCache;

	/**
	 * A {@link ScrollPattern#toGokzString()} cache.
	 */
	private transient String gokzStringCache;

	/**
	 * A {@link ScrollPattern#toKztimerString()} cache.
	 */
	private transient String kztimerStringCache;

	/**
	 * A {@link ScrollPattern#hashCode()} cache.
	 */
	private transient Integer hashCodeCache;

	/**
	 * A {@link ScrollPattern#toString()} cache.
	 */
	private transient String stringCache;

	/**
	 * Initialize a {@link ScrollPattern} instance.
	 *
	 * <p>Wraps {@link ScrollPattern#ScrollPattern(List)}
	 * w/ {@code null} as the list of jump inputs.
	 */
	public ScrollPattern() {
		this((List<JumpInput>) null);
	}

	/**
	 * Initialize a {@link ScrollPattern} instance.
	 *
	 * <p>Wraps {@link ScrollPattern#ScrollPattern(List)}.
	 *
	 * @param jumpInputs	array of jump inputs
	 */
	public ScrollPattern(JumpInput[] jumpInputs) {
		this((List<JumpInput>) UwObject.applyIfNotNull(jumpInputs, Arrays::asList));
	}

	/**
	 * Initialize a {@link ScrollPattern} instance.
	 *
	 * @param jumpInputs	list of jump inputs
	 */
	public ScrollPattern(List<JumpInput> jumpInputs) {
		jumpInputs = UwObject.getIfNull(jumpInputs, ArrayList::new);
		jumpInputs = UwList.toUnmodifiable(jumpInputs);

		this.jumpInputs = jumpInputs;
	}

	private ScrollPattern(ScrollPattern that) {
		this(that.jumpInputs);

		this.perfJumpCountCache = that.perfJumpCountCache;
		this.perfJumpRatioCache = that.perfJumpRatioCache;
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

		return (this.perfJumpCountCache = (int) this.jumpInputs.stream()
				.filter(JumpInput::isPerf)
				.count());
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

		int totalJumpCount = this.getTotalJumpCount();
		if (totalJumpCount == 0) {
			return (this.perfJumpRatioCache = 0f);
		}

		int perfJumpCount = this.getPerfJumpCount();
		return (this.perfJumpRatioCache = (float) perfJumpCount / totalJumpCount);
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

		int totalJumpCount = this.getTotalJumpCount();
		if (totalJumpCount == 0) {
			return (this.avgPreInputCountCache = 0f);
		}

		return (this.avgPreInputCountCache = this.jumpInputs.stream()
				.mapToInt(JumpInput::getPreInputCount)
				.sum() / (float) totalJumpCount);
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

		int totalJumpCount = this.getTotalJumpCount();
		if (totalJumpCount == 0) {
			return (this.avgPostInputCountCache = 0f);
		}

		return (this.avgPostInputCountCache = this.jumpInputs.stream()
				.mapToInt(JumpInput::getPostInputCount)
				.sum() / (float) totalJumpCount);
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

		float avgPreInputCount = this.getAvgPreInputCount();
		float avgPostInputCount = this.getAvgPostInputCount();

		return (this.avgTotalInputCountCache = avgPreInputCount + avgPostInputCount);
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

		return (this.gokzStringCache = this.jumpInputs.stream()
				.map(JumpInput::toGokzString)
				.collect(Collectors.joining()));
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

		return (this.kztimerStringCache = this.jumpInputs.stream()
				.map(JumpInput::toKztimerString)
				.collect(Collectors.joining(" ")));
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

		return (this.hashCodeCache
				= Objects.hash(this.jumpInputs));
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
				+ "jumpInputs=" + this.jumpInputs
				+ "]");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ScrollPattern clone() {
		return new ScrollPattern(this);
	}
}
