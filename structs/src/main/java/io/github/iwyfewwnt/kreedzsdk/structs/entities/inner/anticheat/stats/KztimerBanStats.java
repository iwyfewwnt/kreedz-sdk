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

import java.util.Objects;
import java.util.stream.Stream;

/**
 * A kreedz API KZTimer plugin ban stats representation.
 */
@SuppressWarnings("unused")
public final class KztimerBanStats extends BanStats implements Cloneable {

	/**
	 * A jump input separator regular expression.
	 */
	private static final String JUMP_INPUT_SEPARATOR_REGEX = " ";

	/**
	 * Initialize a {@link KztimerBanStats} instance.
	 *
	 * @param rawStats	raw stats
	 */
	public KztimerBanStats(String rawStats) {
		super(rawStats, EPluginType.KZTIMER);
	}

	/**
	 * Initialize a {@link KztimerBanStats} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private KztimerBanStats(KztimerBanStats that) {
		super(that.rawStats, that.pluginType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected JumpInput[] parseJumpInputs(String text) {
		return Stream.of(text.split(JUMP_INPUT_SEPARATOR_REGEX))
				.map(str -> {
					try {
						int inputCount = Integer.parseInt(str);

						return new JumpInput(inputCount);
					} catch (NumberFormatException e) {
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
	public KztimerBanStats clone() {
		return new KztimerBanStats(this);
	}
}
