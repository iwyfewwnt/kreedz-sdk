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

package io.github.iwyfewwnt.kreedzsdk.client.services.requests.recordfilter;

import io.github.iwyfewwnt.kreedzsdk.clientapi.IRecordFilterService;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.RecordDistributionEntity;
import io.github.iwyfewwnt.kreedzsdk.structs.types.EMode;
import io.github.iwyfewwnt.kreedzsdk.structs.types.ERunType;
import io.github.iwyfewwnt.kreedzsdk.structs.types.ETickrate;
import retrofit2.Call;

import java.util.List;
import java.util.Set;

/**
 * A request for /record_filters/distributions/ endpoint.
 */
public final class GetRecordDistributionsRequest extends GetRecordFiltersRequest implements Cloneable {

	/**
	 * Initialize a {@link GetRecordDistributionsRequest} instance.
	 *
	 * @param ids			set of identifiers
	 * @param mapIds		set of map identifiers
	 * @param stages		set of stage identifiers
	 * @param modes			set of modes
	 * @param tickrates		set of tickrates
	 * @param runType		run type
	 * @param offset		offset
	 * @param limit			limit
	 */
	private GetRecordDistributionsRequest(
			Set<Integer> ids,
			Set<Integer> mapIds,
			Set<Integer> stages,
			Set<EMode> modes,
			Set<ETickrate> tickrates,
			ERunType runType,
			Integer offset,
			Integer limit
	) {
		super(
				ids,
				mapIds,
				stages,
				modes,
				tickrates,
				runType,
				offset,
				limit
		);
	}

	/**
	 * Initialize a {@link GetRecordDistributionsRequest} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private GetRecordDistributionsRequest(GetRecordDistributionsRequest that) {
		super(that);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetRecordDistributionsRequest clone() {
		return new GetRecordDistributionsRequest(this);
	}

	/**
	 * A request manager for /record_filters/distributions/ endpoint.
	 */
	public static final class Manager extends BaseRecordFiltersRequestManager<
			Manager, GetRecordDistributionsRequest, List<RecordDistributionEntity>> {

		/**
		 * A record filter service.
		 */
		private final IRecordFilterService recordFilterService;

		/**
		 * Initialize a {@link GetRecordDistributionsRequest} instance.
		 *
		 * @param recordFilterService	record filter service
		 */
		public Manager(IRecordFilterService recordFilterService) {
			if (recordFilterService == null) {
				throw new IllegalArgumentException("Record filter service mustn't be <null>");
			}

			this.recordFilterService = recordFilterService;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public GetRecordDistributionsRequest build() {
			return new GetRecordDistributionsRequest(
					this.ids,
					this.mapIds,
					this.stages,
					this.modes,
					this.tickrates,
					this.runType,
					this.offset,
					this.limit
			);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Call<List<RecordDistributionEntity>> call(GetRecordDistributionsRequest request) {
			return this.recordFilterService.getRecordFilterDistributions(
					request.ids,
					request.mapIds,
					request.stages,
					request.modes,
					request.tickrates,
					request.runType,
					request.offset,
					request.limit
			);
		}
	}
}
