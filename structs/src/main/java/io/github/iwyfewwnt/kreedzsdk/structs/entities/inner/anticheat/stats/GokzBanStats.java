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
import io.github.iwyfewwnt.kreedzsdk.structs.types.EPluginType;
import io.github.iwyfewwnt.uwutils.UwString;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * A kreedz API GOKZ plugin ban stats representation.
 */
@SuppressWarnings("unused")
public final class GokzBanStats extends BanStats implements Cloneable {

	/**
	 * A jump character regular expression.
	 *
	 * <p>Includes {@link JumpInput#PERF_JUMP_CHAR}
	 * {@literal &} {@link JumpInput#NORMAL_JUMP_CHAR}.
	 */
	private static final String JUMP_CHAR_REGEX = String.format("[%c%c]",
			JumpInput.PERF_JUMP_CHAR,
			JumpInput.NORMAL_JUMP_CHAR
	);

	/**
	 * A jump input separator regular expression.
	 */
	private static final String JUMP_INPUT_SEPARATOR_REGEX = "\\)\\(";

	/**
	 * Initialize a {@link GokzBanStats} instance.
	 *
	 * @param rawStats	raw stats
	 */
	public GokzBanStats(String rawStats) {
		super(rawStats, EPluginType.GOKZ);
	}

	/**
	 * Initialize a {@link GokzBanStats} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private GokzBanStats(GokzBanStats that) {
		super(that.rawStats, that.pluginType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected JumpInput[] parseJumpInputs(String text) {
		text = UwString.trimOrEmpty(text, 1);

		return Stream.of(text.split(JUMP_INPUT_SEPARATOR_REGEX))
				.map(str -> {
					boolean isPerf = str.contains(JumpInput.PERF_JUMP_CHAR_STRING);
					boolean isJump = str.contains(JumpInput.NORMAL_JUMP_CHAR_STRING);

					if (isPerf && isJump || !isPerf && !isJump) {
						new IllegalStateException("isPerf & isJump boolean values are equal")
								.printStackTrace();

						return null;
					}

					String[] split = str.split(JUMP_CHAR_REGEX, 2);

					try {
						int preInputCount = Integer.parseInt(split[0]);
						int postInputCount = Integer.parseInt(split[1]);

						return new JumpInput(preInputCount, postInputCount, isPerf);
					} catch (IndexOutOfBoundsException | NumberFormatException e) {
						e.printStackTrace();
					}

					return null;
				})
				.filter(Objects::nonNull)
				.toArray(JumpInput[]::new);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GokzBanStats clone() {
		return new GokzBanStats(this);
	}
}
