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
 * A kreedz common utilities.
 *
 * <p><b>For internal usage only.</b>
 */
public final class UKreedzCommon {

	/**
	 * Transform collection of {@link SteamId} instances to set of 64-type identifiers.
	 *
	 * <p><b>For internal usage only.</b>
	 *
	 * @param steamIds	collection of {@code SteamId} instances
	 * @return			set of 64-type identifiers
	 */
	public static Set<Long> transformToSteamId64Set(Collection<SteamId> steamIds) {
		return transformToSet(steamIds, SteamId::toSteam64OrNull);
	}

	/**
	 * Transform collection of object to a set.
	 *
	 * @param collection	collection of objects to transform
	 * @param function		function to use for transformation
	 * @param <T>			initial object type
	 * @param <R>			result object type
	 * @return				set of transformed objects
	 */
	private static <T, R> Set<R> transformToSet(Collection<T> collection, Function<T, R> function) {
		if (collection == null || function == null
				|| collection.isEmpty()) {
			return null;
		}

		Set<R> set = collection.stream()
				.map(function)
				.filter(Objects::nonNull)
				.collect(Collectors.toSet());

		if (set.isEmpty()) {
			return null;
		}

		return set;
	}

	private UKreedzCommon() {
		throw new UnsupportedOperationException();
	}
}
