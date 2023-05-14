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
import io.github.iwyfewwnt.kreedzsdk.structs.entities.RecordFilterEntity;
import io.github.iwyfewwnt.kreedzsdk.structs.types.EMode;
import io.github.iwyfewwnt.kreedzsdk.structs.types.ERunType;
import io.github.iwyfewwnt.kreedzsdk.structs.types.ETickrate;
import io.github.iwyfewwnt.uwretrofit.services.requests.IRequest;
import io.github.iwyfewwnt.uwutils.UwSet;
import retrofit2.Call;

import java.util.*;

/**
 * A request for /record_filters/ endpoint.
 */
@SuppressWarnings({"MethodDoesntCallSuperMethod", "SynchronizeOnNonFinalField"})
public class GetRecordFiltersRequest implements IRequest, Cloneable {

	/**
	 * A set of identifiers.
	 */
	protected final Set<Integer> ids;

	/**
	 * A set of map identifiers.
	 */
	protected final Set<Integer> mapIds;

	/**
	 * A set of stage identifiers.
	 */
	protected final Set<Integer> stages;

	/**
	 * A set of modes.
	 */
	protected final Set<EMode> modes;

	/**
	 * A set of tickrates.
	 */
	protected final Set<ETickrate> tickrates;

	/**
	 * A run type.
	 */
	protected final ERunType runType;

	/**
	 * An offset.
	 */
	protected final Integer offset;

	/**
	 * A limit.
	 */
	protected final Integer limit;

	/**
	 * A {@link #hashCode()} cache.
	 */
	protected transient volatile Integer hashCodeCache;

	/**
	 * A {@link #toString()} cache.
	 */
	protected transient volatile String stringCache;

	/**
	 * A {@link #hashCodeCache} mutex.
	 */
	protected transient Object hashCodeCaheMutex;

	/**
	 * A {@link #stringCache} mutex.
	 */
	protected transient Object stringCacheMutex;

	/**
	 * Initialize this mutex objects.
	 */
	protected void initMutexObjects() {
		this.hashCodeCaheMutex = new Object();
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
	 * Initialize a {@link GetRecordFiltersRequest} instance.
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
	GetRecordFiltersRequest(
			Set<Integer> ids,
			Set<Integer> mapIds,
			Set<Integer> stages,
			Set<EMode> modes,
			Set<ETickrate> tickrates,
			ERunType runType,
			Integer offset,
			Integer limit
	) {
		ids = UwSet.toUnmodifiable(ids);
		mapIds = UwSet.toUnmodifiable(mapIds);
		stages = UwSet.toUnmodifiable(stages);
		modes = UwSet.toUnmodifiable(modes);
		tickrates = UwSet.toUnmodifiable(tickrates);

		this.ids = ids;
		this.mapIds = mapIds;
		this.stages = stages;
		this.modes = modes;
		this.tickrates = tickrates;
		this.runType = runType;
		this.offset = offset;
		this.limit = limit;

		this.initMutexObjects();
	}

	/**
	 * Initialize a {@link GetRecordFiltersRequest} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	GetRecordFiltersRequest(GetRecordFiltersRequest that) {
		this(
				that.ids,
				that.mapIds,
				that.stages,
				that.modes,
				that.tickrates,
				that.runType,
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

		GetRecordFiltersRequest that = (GetRecordFiltersRequest) obj;

		return Objects.equals(this.ids, that.ids)
				&& Objects.equals(this.mapIds, that.mapIds)
				&& Objects.equals(this.stages, that.stages)
				&& Objects.equals(this.modes, that.modes)
				&& Objects.equals(this.tickrates, that.tickrates)
				&& this.runType == that.runType
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

		synchronized (this.hashCodeCaheMutex) {
			if (this.hashCodeCache != null) {
				return this.hashCodeCache;
			}

			return (this.hashCodeCache
					= Objects.hash(
							this.ids,
							this.mapIds,
							this.stages,
							this.modes,
							this.tickrates,
							this.runType,
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

			String simpleName = this.getClass()
					.getSimpleName();

			return (this.stringCache = simpleName + "["
					+ "ids=" + this.ids
					+ ", mapIds=" + this.mapIds
					+ ", stages=" + this.stages
					+ ", modes=" + this.modes
					+ ", tickrates=" + this.tickrates
					+ ", runType=" + this.runType
					+ ", offset=" + this.offset
					+ ", limit=" + this.limit
					+ "]");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetRecordFiltersRequest clone() {
		return new GetRecordFiltersRequest(this);
	}

	/**
	 * A request manager for /record_filters/ endpoint.
	 */
	public static final class Manager extends BaseRecordFiltersRequestManager<
				Manager, GetRecordFiltersRequest, List<RecordFilterEntity>> {

		/**
		 * A record filter service.
		 */
		private final IRecordFilterService recordFilterService;

		/**
		 * Initialize a {@link GetRecordFiltersRequest.Manager} instance.
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
		public GetRecordFiltersRequest build() {
			return new GetRecordFiltersRequest(
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
		public Call<List<RecordFilterEntity>> call(GetRecordFiltersRequest request) {
			return this.recordFilterService.getRecordFilters(
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
