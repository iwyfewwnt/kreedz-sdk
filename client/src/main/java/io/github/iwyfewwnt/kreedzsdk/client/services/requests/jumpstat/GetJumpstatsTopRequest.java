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

package io.github.iwyfewwnt.kreedzsdk.client.services.requests.jumpstat;

import io.github.iwyfewwnt.kreedzsdk.client.internal.UKreedzCommon;
import io.github.iwyfewwnt.kreedzsdk.clientapi.IJumpstatService;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.JumpstatEntity;
import io.github.iwyfewwnt.kreedzsdk.structs.types.EJumpType;
import org.joda.time.DateTime;
import retrofit2.Call;

import java.util.List;
import java.util.Set;

/**
 * A request for /jumpstats/.../top/ endpoint.
 */
public final class GetJumpstatsTopRequest extends GetJumpstatsRequest implements Cloneable {

	/**
	 * Initialize a {@code GetJumpstatsTopRequest} instance.
	 *
	 * @param id					identifier
	 * @param serverId				server identifier
	 * @param steamId64s			set of type-64 person identifiers
	 * @param jumpType				jump type
	 * @param distanceGreaterThan	left distance threshold
	 * @param distanceLessThan		right distance threshold
	 * @param isMsl					"isMsl" boolean value
	 * @param isCrouchBind			"isCrouchBind" boolean value
	 * @param isForwardBind			"isForwardBind" boolean value
	 * @param isCrouchBoost			"isCrouchBoost" boolean value
	 * @param dataUpdaterId			data updater identifier
	 * @param createdSinceDate		created since date
	 * @param updatedSinceDate		updated since date
	 * @param offset				offset
	 * @param limit					limit
	 */
	private GetJumpstatsTopRequest(
			Integer id,
			Integer serverId,
			Set<Long> steamId64s,
			EJumpType jumpType,
			Float distanceGreaterThan,
			Float distanceLessThan,
			Boolean isMsl,
			Boolean isCrouchBind,
			Boolean isForwardBind,
			Boolean isCrouchBoost,
			Long dataUpdaterId,
			DateTime createdSinceDate,
			DateTime updatedSinceDate,
			Integer offset,
			Integer limit
	) {
		super(
				id,
				serverId,
				steamId64s,
				jumpType,
				distanceGreaterThan,
				distanceLessThan,
				isMsl,
				isCrouchBind,
				isForwardBind,
				isCrouchBoost,
				dataUpdaterId,
				createdSinceDate,
				updatedSinceDate,
				offset,
				limit
		);
	}

	/**
	 * Initialize a {@link GetJumpstatsTopRequest} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private GetJumpstatsTopRequest(GetJumpstatsTopRequest that) {
		super(that);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetJumpstatsTopRequest clone() {
		return new GetJumpstatsTopRequest(this);
	}

	/**
	 * A request manager for /jumpstats/.../top/ endpoint.
	 */
	public static final class Manager extends BaseGetJumpstatsRequestManager<
				Manager, GetJumpstatsTopRequest> {

		/**
		 * A jumpstat service.
		 */
		private final IJumpstatService jumpstatService;

		/**
		 * Initialize a {@link GetJumpstatsTopRequest.Manager} instance.
		 *
		 * @param jumpstatService	jumpstat service
		 */
		public Manager(IJumpstatService jumpstatService) {
			if (jumpstatService == null) {
				throw new IllegalArgumentException("Jumpstat service mustn't be <null>");
			}

			this.jumpstatService = jumpstatService;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public GetJumpstatsTopRequest build() {
			Set<Long> steamId64s = UKreedzCommon.mapSteamIdsToId64Set(this.steamIds);

			return new GetJumpstatsTopRequest(
					this.id,
					this.serverId,
					steamId64s,
					this.jumpType,
					this.distanceGreaterThan,
					this.distanceLessThan,
					this.isMsl,
					this.isCrouchBind,
					this.isForwardBind,
					this.isCrouchBoost,
					this.dataUpdaterId,
					this.createdSinceDate,
					this.updatedSinceDate,
					this.offset,
					this.limit
			);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Call<List<JumpstatEntity>> call(GetJumpstatsTopRequest request) {
			return this.jumpstatService.getJumpstatsTop(
					request.jumpType,
					request.id,
					request.serverId,
					request.steamId64s,
					request.distanceGreaterThan,
					request.distanceLessThan,
					request.isMsl,
					request.isCrouchBind,
					request.isForwardBind,
					request.isCrouchBoost,
					request.dataUpdaterId,
					request.createdSinceDate,
					request.updatedSinceDate,
					request.offset,
					request.limit
			);
		}
	}
}
