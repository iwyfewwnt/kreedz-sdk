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

package io.github.iwyfewwnt.kreedzsdk.client.services.requests.server;

import io.github.iwyfewwnt.kreedzsdk.clientapi.IServerService;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.ServerEntity;
import io.github.iwyfewwnt.kreedzsdk.structs.types.EApprovalStatus;
import io.github.iwyfewwnt.steamid.SteamId;
import io.github.iwyfewwnt.uwretrofit.services.requests.IRequest;
import io.github.iwyfewwnt.uwretrofit.services.requests.impl.AbstractRequestManager;
import io.github.iwyfewwnt.uwutils.UwObject;
import io.github.iwyfewwnt.uwutils.UwSet;
import retrofit2.Call;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A request for /servers/ endpoint.
 */
@SuppressWarnings({"MethodDoesntCallSuperMethod", "SynchronizeOnNonFinalField"})
public final class GetServersRequest implements IRequest, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = GetServersRequest.class.getSimpleName();

	/**
	 * A set of identifiers.
	 */
	private final Set<Integer> ids;

	/**
	 * A server port.
	 */
	private final Integer serverPort;

	/**
	 * A server IP address.
	 */
	private final String serverIp;

	/**
	 * A server name.
	 */
	private final String serverName;

	/**
	 * An owners type-64 person identifier.
	 */
	private final Long ownersSteamId64;

	/**
	 * An approval status.
	 */
	private final EApprovalStatus approvalStatus;

	/**
	 * An offset.
	 */
	private final Integer offset;

	/**
	 * A limit.
	 */
	private final Integer limit;

	/**
	 * A {@link GetServersRequest#hashCode()} cache.
	 */
	private transient volatile Integer hashCodeCache;

	/**
	 * A {@link GetServersRequest#toString()} cache.
	 */
	private transient volatile String stringCache;

	/**
	 * A {@link #hashCodeCache} mutex.
	 */
	private transient Object hashCodeCacheMutex;

	/**
	 * A {@link #stringCache} mutex.
	 */
	private transient Object stringCacheMutex;

	/**
	 * Initialize this mutex objects.
	 */
	private void initMutexObjects() {
		this.hashCodeCacheMutex = new Object();
		this.stringCacheMutex = new Object();
	}

	/**
	 * Override the {@code #readResolve} method to set up
	 * the object cache mutexes after deserialization.
	 *
	 * @return	this instance
	 */
	private Object readResolve() {
		this.initMutexObjects();

		return this;
	}

	/**
	 * Initialize a {@link GetServersRequest} instance.
	 *
	 * @param ids				set of identifiers
	 * @param serverPort		server port
	 * @param serverIp			server IP address
	 * @param serverName		server name
	 * @param ownersSteamId64	owners type-64 person identifier
	 * @param approvalStatus	approval status
	 * @param offset			offset
	 * @param limit				limit
	 */
	private GetServersRequest(
			Set<Integer> ids,
			Integer serverPort,
			String serverIp,
			String serverName,
			Long ownersSteamId64,
			EApprovalStatus approvalStatus,
			Integer offset,
			Integer limit
	) {
		ids = UwSet.toUnmodifiable(ids);

		this.ids = ids;
		this.serverPort = serverPort;
		this.serverIp = serverIp;
		this.serverName = serverName;
		this.ownersSteamId64 = ownersSteamId64;
		this.approvalStatus = approvalStatus;
		this.offset = offset;
		this.limit = limit;

		this.initMutexObjects();
	}

	/**
	 * Initialize a {@link GetServersRequest} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private GetServersRequest(GetServersRequest that) {
		this(
				that.ids,
				that.serverPort,
				that.serverIp,
				that.serverName,
				that.ownersSteamId64,
				that.approvalStatus,
				that.offset,
				that.limit
		);

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
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

		GetServersRequest that = (GetServersRequest) obj;

		return Objects.equals(this.ids, that.ids)
				&& Objects.equals(this.serverPort, that.serverPort)
				&& Objects.equals(this.serverIp, that.serverIp)
				&& Objects.equals(this.serverName, that.serverName)
				&& Objects.equals(this.ownersSteamId64, that.ownersSteamId64)
				&& this.approvalStatus == that.approvalStatus
				&& Objects.equals(this.offset, that.offset)
				&& Objects.equals(this.limit, that.limit);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		if (this.hashCodeCache != null) {
			return this.hashCodeCache;
		}

		synchronized (this.hashCodeCacheMutex) {
			if (this.hashCodeCache != null) {
				return this.hashCodeCache;
			}

			return (this.hashCodeCache
					= Objects.hash(
							this.ids,
							this.serverPort,
							this.serverIp,
							this.serverName,
							this.ownersSteamId64,
							this.approvalStatus,
							this.offset,
							this.limit
					)
			);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		if (this.stringCache != null) {
			return this.stringCache;
		}

		synchronized (this.stringCacheMutex) {
			if (this.stringCache != null) {
				return this.stringCache;
			}

			return (this.stringCache = SIMPLE_NAME + "["
					+ "ids=" + this.ids
					+ ", serverPort=" + this.serverPort
					+ ", serverIp=\"" + this.serverIp + "\""
					+ ", serverName=\"" + this.serverName + "\""
					+ ", ownersSteamId64=" + this.ownersSteamId64
					+ ", approvalStatus=" + this.approvalStatus
					+ ", offset=" + this.offset
					+ ", limit=" + this.limit
					+ "]");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetServersRequest clone() {
		return new GetServersRequest(this);
	}

	/**
	 * A request manager for /servers/ endpoint.
	 */
	@SuppressWarnings("unused")
	public static final class Manager extends AbstractRequestManager<GetServersRequest, List<ServerEntity>> {

		/**
		 * A server service.
		 */
		private final IServerService serverService;

		/**
		 * A set of identifiers.
		 */
		private Set<Integer> ids;

		/**
		 * A server port.
		 */
		private Integer serverPort;

		/**
		 * A server IP address.
		 */
		private String serverIp;

		/**
		 * A server name.
		 */
		private String serverName;

		/**
		 * An owners person identifier.
		 */
		private SteamId ownersSteamId;

		/**
		 * An approval status.
		 */
		private EApprovalStatus approvalStatus;

		/**
		 * An offset.
		 */
		private Integer offset;

		/**
		 * A limit.
		 */
		private Integer limit;

		/**
		 * Initialize a {@link GetServersRequest.Manager} instance.
		 *
		 * @param serverService		server service
		 */
		public Manager(IServerService serverService) {
			if (serverService == null) {
				throw new IllegalArgumentException("Server service mustn't be <null>");
			}

			this.serverService = serverService;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public GetServersRequest build() {
			Long ownersSteamId64 = UwObject.ifNotNull(this.ownersSteamId, SteamId::toSteam64OrNull);

			return new GetServersRequest(
					this.ids,
					this.serverPort,
					this.serverIp,
					this.serverName,
					ownersSteamId64,
					this.approvalStatus,
					this.offset,
					this.limit
			);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Call<List<ServerEntity>> call(GetServersRequest request) {
			return this.serverService.getServers(
					request.ids,
					request.serverPort,
					request.serverIp,
					request.serverName,
					request.ownersSteamId64,
					request.approvalStatus,
					request.offset,
					request.limit
			);
		}

		/**
		 * Set this set of identifiers.
		 *
		 * @param ids	set of identifiers, may be null
		 * @return		this instance
		 */
		public Manager setIds(Set<Integer> ids) {
			this.ids = ids;
			return this;
		}

		/**
		 * Set this set of identifiers.
		 *
		 * @param ids	collection of identifiers, may be null
		 * @return		this instance
		 */
		public Manager setIds(Collection<Integer> ids) {
			return this.setIds(UwSet.toSetOrNull(ids));
		}

		/**
		 * Set this set of identifiers.
		 *
		 * @param ids	array of identifiers, may be null
		 * @return		this instance
		 */
		public Manager setIds(Integer... ids) {
			return this.setIds(UwSet.toSetOrNull(ids));
		}

		/**
		 * Set this set of identifiers.
		 *
		 * @param ids	stream of identifiers, may be null
		 * @return		this instance
		 */
		public Manager setIds(Stream<Integer> ids) {
			return this.setIds(UwSet.toSetOrNull(ids));
		}

		/**
		 * Set this set of identifiers.
		 *
		 * @param ids	integer stream of identifiers, may be null
		 * @return		this instance
		 */
		public Manager setIds(IntStream ids) {
			return this.setIds(UwSet.toSetOrNull(ids));
		}

		/**
		 * Set this server port.
		 *
		 * @param serverPort	server port, may be null
		 * @return				this instance
		 */
		public Manager setServerPort(Integer serverPort) {
			this.serverPort = serverPort;
			return this;
		}

		/**
		 * Set this server IP address.
		 *
		 * @param serverIp	server IP address, may be null
		 * @return			this instance
		 */
		public Manager setServerIp(String serverIp) {
			this.serverIp = serverIp;
			return this;
		}

		/**
		 * Set this server name.
		 *
		 * @param serverName	server name, may be null
		 * @return				this instance
		 */
		public Manager setServerName(String serverName) {
			this.serverName = serverName;
			return this;
		}

		/**
		 * Set this owners person identifier.
		 *
		 * @param ownersSteamId	person identifier, may be null
		 * @return				this instance
		 */
		public Manager setOwnersSteamId(SteamId ownersSteamId) {
			this.ownersSteamId = ownersSteamId;
			return this;
		}

		/**
		 * Set this approval status.
		 *
		 * @param approvalStatus	approval status, may be null
		 * @return					this instance
		 */
		public Manager setApprovalStatus(EApprovalStatus approvalStatus) {
			this.approvalStatus = approvalStatus;
			return this;
		}

		/**
		 * Set this offset.
		 *
		 * @param offset	offset, may be null
		 * @return			this instance
		 */
		public Manager setOffset(Integer offset) {
			this.offset = offset;
			return this;
		}

		/**
		 * Set this limit.
		 *
		 * @param limit		limit, may be null
		 * @return			this instance
		 */
		public Manager setLimit(Integer limit) {
			this.limit = limit;
			return this;
		}
	}
}
