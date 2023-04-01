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

import io.github.iwyfewwnt.steamid.SteamId;

import java.io.Serializable;
import java.util.Objects;

/**
 * A kreedz API data updater representation.
 */
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod"})
public final class DataUpdater implements Serializable, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = DataUpdater.class.getSimpleName();

	/**
	 * An entity identifier.
	 */
	private final Long id;

	/**
	 * A {@link DataUpdater#getAsServerId()} cache.
	 */
	private transient Integer serverIdCache;

	/**
	 * A {@link DataUpdater#getAsSteamId()} cache.
	 */
	private transient SteamId steamIdCache;

	/**
	 * A {@link DataUpdater#hashCode()} cache.
	 */
	private transient Integer hashCodeCache;

	/**
	 * A {@link DataUpdater#toString()} cache.
	 */
	private transient String stringCache;

	/**
	 * Initialize a {@link DataUpdater} instance.
	 *
	 * @param id	entity identifier, may be null
	 */
	public DataUpdater(Long id) {
		this.id = id;
	}

	/**
	 * Initialize a {@link DataUpdater} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private DataUpdater(DataUpdater that) {
		this(that.id);

		this.serverIdCache = that.serverIdCache;
		this.steamIdCache = that.steamIdCache;

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}

	/**
	 * Get this entity identifier.
	 *
	 * @return	entity identifier
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Get this entity identifier as a server identifier.
	 *
	 * @return	server identifier or {@code null}
	 */
	public Integer getAsServerId() {
		if (this.serverIdCache != null) {
			return this.serverIdCache;
		}

		if (!this.isServer()) {
			new UnsupportedOperationException("Not a server identifier")
					.printStackTrace();

			return null;
		}

		return (this.serverIdCache = this.id.intValue());
	}

	/**
	 * Get this entity identifier as a person identifier.
	 *
	 * @return	person identifier or {@code null}
	 */
	public SteamId getAsSteamId() {
		if (this.steamIdCache != null) {
			return this.steamIdCache;
		}

		this.steamIdCache = SteamId.fromSteam64OrNull(this.id);
		if (this.steamIdCache == null) {
			new UnsupportedOperationException("Not a person identifier")
					.printStackTrace();
		}

		return this.steamIdCache;
	}

	/**
	 * Check if this entity identifier is a server identifier.
	 *
	 * @return	boolean value as a result,
	 * 			true - yes, false - no
	 */
	public boolean isServer() {
		return this.id != null
				&& this.id >= 0
				&& this.id <= Integer.MAX_VALUE;
	}

	/**
	 * Check if this entity identifier is a person identifier.
	 *
	 * @return	boolean value as a result,
	 * 			true - yes, false - no
	 */
	public boolean isPerson() {
		return SteamId.isSteamId64Valid(this.id);
	}

	/**
	 * Check if this entity identifier is valid.
	 *
	 * @return	boolean value as a result,
	 * 			true - yes, false - no
	 */
	public boolean isValid() {
		return this.isServer()
				|| this.isPerson();
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

		DataUpdater that = (DataUpdater) obj;

		return Objects.equals(this.id, that.id);
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
				= Objects.hash(this.id));
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
				+ "id=" + this.id
				+ "]");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DataUpdater clone() {
		return new DataUpdater(this);
	}
}
