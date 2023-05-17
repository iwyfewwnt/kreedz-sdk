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

package io.github.iwyfewwnt.kreedzsdk.adapters;

import com.google.auto.service.AutoService;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import io.github.iwyfewwnt.kreedzsdk.adapterapi.IKreedzTypeAdapter;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.inner.anticheat.stats.BanStats;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.inner.anticheat.stats.GokzBanStats;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.inner.anticheat.stats.KztimerBanStats;

import java.lang.reflect.Type;

/**
 * A {@link BanStats} JSON deserializer.
 */
@SuppressWarnings("unused")
@AutoService(IKreedzTypeAdapter.class)
public final class BanStatsJsonDeserializer implements JsonDeserializer<BanStats>, IKreedzTypeAdapter {

	/**
	 * A GOKZ ban stats regular expression.
	 */
	private static final String GOKZ_STATS_REGEX = "Perfs: \\d+/\\d+, Average: \\d*\\.?\\d*, Scroll pattern: .*";

	/**
	 * A KZTimer ban stats regular expression.
	 */
	private static final String KZTIMER_STATS_REGEX = "Scroll pattern: .*, Avg\\. scroll pattern: \\d+\\.?\\d*, Avg\\. speed: \\d+\\.?\\d*, Perfect jump ratio: \\d+\\.?\\d*%";

	/**
	 * Initialize a {@link BanStatsJsonDeserializer} instance.
	 */
	public BanStatsJsonDeserializer() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BanStats deserialize(JsonElement json, Type type, JsonDeserializationContext context) {
		String stats = context.deserialize(json, String.class);

		if (stats == null) {
			return null;
		}

		if (stats.matches(GOKZ_STATS_REGEX)) {
			return new GokzBanStats(stats);
		}

		if (stats.matches(KZTIMER_STATS_REGEX)) {
			return new KztimerBanStats(stats);
		}

		return null;
	}
}
