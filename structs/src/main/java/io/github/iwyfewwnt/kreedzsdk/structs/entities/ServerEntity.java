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

package io.github.iwyfewwnt.kreedzsdk.structs.entities;

import com.google.gson.annotations.SerializedName;
import io.github.iwyfewwnt.steamid.SteamId;

import java.io.Serializable;
import java.util.Objects;

/**
 * A kreedz API server entity.
 */
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod"})
public final class ServerEntity implements Serializable, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = ServerEntity.class.getSimpleName();

	/**
	 * An identifier.
	 */
	@SerializedName("id")
	private final Integer id;

	/**
	 * A port.
	 */
	@SerializedName("port")
	private final Integer port;

	/**
	 * An IP address.
	 */
	@SerializedName("ip")
	private final String ip;

	/**
	 * A name.
	 */
	@SerializedName("name")
	private final String name;

	/**
	 * An owner's identifier.
	 */
	@SerializedName("owner_steamid64")
	private final SteamId ownerSteamId;

	/**
	 * A {@link ServerEntity#hashCode()} cache.
	 */
	private transient Integer hashCodeCache;

	/**
	 * A {@link ServerEntity#toString()} cache.
	 */
	private transient String stringCache;

	/**
	 * Get this identifier.
	 *
	 * @return	identifier
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Get this port.
	 *
	 * @return	port
	 */
	public Integer getPort() {
		return this.port;
	}

	/**
	 * Get this IP address.
	 *
	 * @return	IP address
	 */
	public String getIp() {
		return this.ip;
	}

	/**
	 * Get this name.
	 *
	 * @return	name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Get this owner's identifier.
	 *
	 * @return	owner's identifier
	 */
	public SteamId getOwnerSteamId() {
		return this.ownerSteamId;
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

		ServerEntity that = (ServerEntity) obj;

		return Objects.equals(id, that.id)
				&& Objects.equals(port, that.port)
				&& Objects.equals(ip, that.ip)
				&& Objects.equals(name, that.name)
				&& Objects.equals(ownerSteamId, that.ownerSteamId);
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
						this.id,
						this.port,
						this.ip,
						this.name,
						this.ownerSteamId
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
				+ "id=" + this.id
				+ ", port=" + this.port
				+ ", ip=\"" + this.ip + "\""
				+ ", name=\"" + this.name + "\""
				+ ", ownerSteamId=" + this.ownerSteamId
				+ "]");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ServerEntity clone() {
		return new ServerEntity(this);
	}

	/**
	 * Initialize a {@link ServerEntity} instance.
	 *
	 * @param id			identifier
	 * @param port			port
	 * @param ip			IP address
	 * @param name			name
	 * @param ownerSteamId	owner's identifier
	 */
	private ServerEntity(
			Integer id,
			Integer port,
			String ip,
			String name,
			SteamId ownerSteamId
	) {
		this.id = id;
		this.port = port;
		this.ip = ip;
		this.name = name;
		this.ownerSteamId = ownerSteamId;
	}

	/**
	 * Initialize a {@link ServerEntity} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private ServerEntity(ServerEntity that) {
		this(
				that.id,
				that.port,
				that.ip,
				that.name,
				that.ownerSteamId
		);

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}
}
