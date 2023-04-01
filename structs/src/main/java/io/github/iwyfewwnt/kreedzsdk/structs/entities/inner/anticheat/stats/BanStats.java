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

package io.github.iwyfewwnt.kreedzsdk.structs.entities.inner.anticheat.stats;

import io.github.iwyfewwnt.kreedzsdk.structs.entities.inner.anticheat.JumpInput;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.inner.anticheat.ScrollPattern;
import io.github.iwyfewwnt.kreedzsdk.structs.types.EPluginType;
import io.github.iwyfewwnt.uwutils.UwObject;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A kreedz API ban stats representation.
 */
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod"})
public class BanStats implements Serializable, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = BanStats.class.getSimpleName();

	/**
	 * A jump inputs regular expression.
	 */
	private static final String JUMP_INPUTS_REGEX = ".*Scroll pattern: (.*),?.*";

	/**
	 * A jump inputs pattern.
	 *
	 * <p>Wraps {@link BanStats#JUMP_INPUTS_REGEX}.
	 */
	private static final Pattern JUMP_INPUTS_PATTERN = Pattern.compile(JUMP_INPUTS_REGEX);

	/**
	 * A raw stats.
	 */
	protected final String rawStats;

	/**
	 * A plugin type.
	 */
	protected final EPluginType pluginType;

	/**
	 * A {@link BanStats#getScrollPattern()} cache.
	 */
	private transient ScrollPattern scrollPatternCache;

	/**
	 * A {@link BanStats#hashCode()} cache.
	 */
	private transient Integer hashCodeCache;

	/**
	 * A {@link BanStats#toString()} cache.
	 */
	private transient String stringCache;

	/**
	 * Initialize a {@link BanStats} instance.
	 *
	 * @param rawStats		raw stats
	 * @param pluginType	plugin type
	 */
	BanStats(String rawStats, EPluginType pluginType) {
		rawStats = rawStats != null ? rawStats.trim() : "";
		pluginType = UwObject.getIfNull(pluginType, EPluginType.UNKNOWN);

		this.rawStats = rawStats;
		this.pluginType = pluginType;
	}

	/**
	 * Initialize a {@link BanStats} instance.
	 *
	 * <p>Wraps {@link BanStats#BanStats(String, EPluginType)}
	 * w/ {@code null} as the raw stats {@literal &} plugin type.
	 */
	BanStats() {
		this(null, null);
	}

	/**
	 * Initialize a {@link BanStats} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	BanStats(BanStats that) {
		this(
				that.rawStats,
				that.pluginType
		);

		this.scrollPatternCache = that.scrollPatternCache;

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}

	/**
	 * Parse and convert text-string to a jump inputs array.
	 *
	 * @param text	text-string, always not null
	 * @return		array of jump inputs
	 */
	protected JumpInput[] parseJumpInputs(String text) {
		return null;
	}

	/**
	 * Get this raw stats.
	 *
	 * @return	raw stats, always not null
	 */
	public final String getRawStats() {
		return this.rawStats;
	}

	/**
	 * Get this plugin type.
	 *
	 * @return	plugin type
	 */
	public final EPluginType getPluginType() {
		return this.pluginType;
	}

	/**
	 * Get this scroll pattern.
	 *
	 * @return	scroll pattern
	 */
	public final ScrollPattern getScrollPattern() {
		if (this.scrollPatternCache != null) {
			return this.scrollPatternCache;
		}

		JumpInput[] jumpInputs = null;

		Matcher m = JUMP_INPUTS_PATTERN.matcher(this.rawStats);

		if (m.matches()) {
			jumpInputs = this.parseJumpInputs(m.group(1));
		}

		return (this.scrollPatternCache = new ScrollPattern(jumpInputs));
	}

	/**
	 * Check if this raw string value is empty.
	 *
	 * <p>Wraps {@link String#isEmpty()}.
	 *
	 * @return	boolean value as a result,
	 * 			true - yes, false - no
	 */
	public final boolean isEmpty() {
		return this.rawStats.isEmpty();
	}

	/**
	 * Get this list of jump inputs
	 *
	 * <p>Delegates {@link ScrollPattern#getJumpInputs()}.
	 *
	 * @return	list of jump inputs
	 */
	public final List<JumpInput> getJumpInputs() {
		return this.getScrollPattern()
				.getJumpInputs();
	}

	/**
	 * Get this total jump count.
	 *
	 * <p>Delegates {@link ScrollPattern#getTotalJumpCount()}.
	 *
	 * @return	total jump count
	 */
	public final int getTotalJumpCount() {
		return this.getScrollPattern()
				.getTotalJumpCount();
	}

	/**
	 * Get this perf-jump count.
	 *
	 * <p>Delegates {@link ScrollPattern#getPerfJumpCount()}.
	 *
	 * @return	perf-jump count
	 */
	public final int getPerfJumpCount() {
		return this.getScrollPattern()
				.getPerfJumpCount();
	}

	/**
	 * Get this perf-jump ratio.
	 *
	 * <p>Delegates {@link ScrollPattern#getPerfJumpRatio()}.
	 *
	 * @return	perf-jump ratio
	 */
	public final float getPerfJumpRatio() {
		return this.getScrollPattern()
				.getPerfJumpRatio();
	}

	/**
	 * Get this average pre-input count.
	 *
	 * <p>Delegates {@link ScrollPattern#getAvgPreInputCount()}.
	 *
	 * @return	average pre-input count
	 */
	public final float getAvgPreInputCount() {
		return this.getScrollPattern()
				.getAvgPreInputCount();
	}

	/**
	 * Get this average post-input count.
	 *
	 * <p>Delegates {@link ScrollPattern#getAvgPostInputCount()}.
	 *
	 * @return	average post-input count
	 */
	public final float getAvgPostInputCount() {
		return this.getScrollPattern()
				.getAvgPostInputCount();
	}

	/**
	 * Get this average total input count.
	 *
	 * <p>Delegates {@link ScrollPattern#getAvgTotalInputCount()}.
	 *
	 * @return	average total input count
	 */
	public final float getAvgTotalInputCount() {
		return this.getScrollPattern()
				.getAvgTotalInputCount();
	}

	/**
	 * Convert this to a GOKZ string representation.
	 *
	 * <p>Delegates {@link ScrollPattern#toGokzString()}.
	 *
	 * @return	string representation, always not null
	 */
	public final String toGokzString() {
		return this.getScrollPattern()
				.toGokzString();
	}

	/**
	 * Convert this to a KZTimer string representation.
	 *
	 * <p>Delegates {@link ScrollPattern#toKztimerString()}.
	 *
	 * @return	string representation, always not null
	 */
	public final String toKztimerString() {
		return this.getScrollPattern()
				.toKztimerString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}

		BanStats that = (BanStats) obj;

		return Objects.equals(this.rawStats, that.rawStats)
				&& this.pluginType == that.pluginType;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {
		if (this.hashCodeCache != null) {
			return this.hashCodeCache;
		}

		return (this.hashCodeCache
				= Objects.hash(
						this.rawStats,
						this.pluginType
				)
		);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		if (this.stringCache != null) {
			return this.stringCache;
		}

		return (this.stringCache = SIMPLE_NAME + "["
				+ "rawStats=\"" + this.rawStats + "\""
				+ ", pluginType=" + this.pluginType
				+ "]");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BanStats clone() {
		return new BanStats(this);
	}
}
