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

import com.google.gson.annotations.SerializedName;
import io.github.iwyfewwnt.steamid.SteamId;

import java.io.Serializable;
import java.util.Objects;

/**
 * A kreedz maps information API mapper representation.
 */
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod"})
public final class Mapper implements Serializable, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = Mapper.class.getSimpleName();

	/**
	 * A mapper name.
	 */
	@SerializedName("name")
	private final String name;

	/**
	 * A mapper identifier.
	 */
	@SerializedName("id64")
	private final SteamId steamId;

	/**
	 * A {@link Mapper#hashCode()} cache.
	 */
	private transient Integer hashCodeCache;

	/**
	 * A {@link Mapper#toString()} cache.
	 */
	private transient String stringCache;

	/**
	 * Get this mapper name.
	 *
	 * @return	mapper name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Get this mapper identifier.
	 *
	 * @return	mapper identifier
	 */
	public SteamId getSteamId() {
		return this.steamId;
	}

	/**
	 * Check if this instance is fully filled
	 * w/ mapper name {@literal &} Steam ID.
	 *
	 * @return	boolean value as a result,
	 * 			true - yes, false - no
	 */
	public boolean isCompleted() {
		return this.name != null
				&& this.steamId != null;
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

		Mapper that = (Mapper) obj;

		return Objects.equals(this.name, that.name)
				&& Objects.equals(this.steamId, that.steamId);
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
				= Objects.hash(
						this.name,
						this.steamId
				)
		);
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
				+ "name=\"" + this.name + "\""
				+ ", steamId=" + this.steamId
				+ "]");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Mapper clone() {
		return new Mapper(this);
	}

	/**
	 * Initialize a {@link Mapper} instance.
	 *
	 * @param name		mapper name
	 * @param steamId	mapper identifier
	 */
	private Mapper(
			String name,
			SteamId steamId
	) {
		this.name = name;
		this.steamId = steamId;
	}

	/**
	 * Initialize a {@link Mapper} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private Mapper(Mapper that) {
		this(
				that.name,
				that.steamId
		);

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}
}
