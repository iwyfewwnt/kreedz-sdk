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

package io.github.iwyfewwnt.kreedzsdk.client.internal;

import io.github.iwyfewwnt.steamid.SteamId;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A common utilities.
 *
 * <p><b>For internal usage only.</b>
 */
public final class UCommon {

	/**
	 * Map collection of {@link SteamId} instances to set of 64-type identifiers.
	 *
	 * <p><b>For internal usage only.</b>
	 *
	 * @param steamIds	collection of identifier instances
	 * @return			set of 64-type identifiers
	 */
	public static Set<Long> mapSteamIdsToId64Set(Collection<SteamId> steamIds) {
		return mapSteamIdsToSet(steamIds, SteamId::toSteam64OrNull);
	}

	/**
	 * Map collection of {@link SteamId} instances to set.
	 *
	 * <p><b>For internal usage only.</b>
	 *
	 * @param steamIds	collection of identifier instances
	 * @param function	mapping function
	 * @param <T>		type to map to
	 * @return			set of mapped identifier instances
	 */
	public static <T> Set<T> mapSteamIdsToSet(Collection<SteamId> steamIds, Function<SteamId, T> function) {
		if (steamIds == null || function == null
				|| steamIds.isEmpty()) {
			return null;
		}

		Set<T> ids = steamIds.stream()
				.map(function)
				.filter(Objects::nonNull)
				.collect(Collectors.toSet());

		if (ids.isEmpty()) {
			return null;
		}

		return ids;
	}

	private UCommon() {
		throw new UnsupportedOperationException();
	}
}
