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

package io.github.iwyfewwnt.kreedzsdk.client.services.requests.record;

import io.github.iwyfewwnt.kreedzsdk.clientapi.IRecordService;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.RecordCountEntity;
import io.github.iwyfewwnt.kreedzsdk.structs.types.EMode;
import io.github.iwyfewwnt.kreedzsdk.structs.types.ERunType;
import io.github.iwyfewwnt.kreedzsdk.structs.types.ETickrate;
import io.github.iwyfewwnt.uwretrofit.services.requests.IRequest;
import io.github.iwyfewwnt.uwretrofit.services.requests.impl.AbstractRequestManager;
import io.github.iwyfewwnt.uwutils.UwSet;
import retrofit2.Call;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A request for /records/top/world_records/ endpoint.
 */
@SuppressWarnings("MethodDoesntCallSuperMethod")
public final class GetWorldRecordsTopRequest implements IRequest, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = GetWorldRecordsTopRequest.class.getSimpleName();

	/**
	 * A set of identifiers.
	 */
	private final Set<Integer> ids;

	/**
	 * A set of map identifiers.
	 */
	private final Set<Integer> mapIds;

	/**
	 * A set of stage identifiers.
	 */
	private final Set<Integer> stages;

	/**
	 * A set of modes.
	 */
	private final Set<EMode> modes;

	/**
	 * A set of tickrates.
	 */
	private final Set<ETickrate> tickrates;

	/**
	 * A run type.
	 */
	private final ERunType runType;

	/**
	 * A map tag.
	 */
	private final String mapTag;

	/**
	 * An offset.
	 */
	private final Integer offset;

	/**
	 * A limit.
	 */
	private final Integer limit;

	/**
	 * A {@link GetWorldRecordsTopRequest#hashCode()} cache.
	 */
	private transient Integer hashCodeCache;

	/**
	 * A {@link GetWorldRecordsTopRequest#toString()} cache.
	 */
	private transient String stringCache;

	/**
	 * Initialize a {@link GetWorldRecordsTopRequest} instance.
	 *
	 * @param ids			set of identifiers
	 * @param mapIds		set of map identifiers
	 * @param stages		set of stage identifiers
	 * @param modes			set of modes
	 * @param tickrates		set of tickrates
	 * @param runType		run type
	 * @param mapTag		map tag
	 * @param offset		offset
	 * @param limit			limit
	 */
	private GetWorldRecordsTopRequest(
			Set<Integer> ids,
			Set<Integer> mapIds,
			Set<Integer> stages,
			Set<EMode> modes,
			Set<ETickrate> tickrates,
			ERunType runType,
			String mapTag,
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
		this.mapTag = mapTag;
		this.offset = offset;
		this.limit = limit;
	}

	/**
	 * Initialize a {@link GetWorldRecordsTopRequest} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private GetWorldRecordsTopRequest(GetWorldRecordsTopRequest that) {
		this(
				that.ids,
				that.mapIds,
				that.stages,
				that.modes,
				that.tickrates,
				that.runType,
				that.mapTag,
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

		GetWorldRecordsTopRequest that = (GetWorldRecordsTopRequest) obj;

		return Objects.equals(this.ids, that.ids)
				&& Objects.equals(this.mapIds, that.mapIds)
				&& Objects.equals(this.stages, that.stages)
				&& Objects.equals(this.modes, that.modes)
				&& Objects.equals(this.tickrates, that.tickrates)
				&& this.runType == that.runType
				&& Objects.equals(this.mapTag, that.mapTag)
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

		return (this.hashCodeCache
				= Objects.hash(
						this.ids,
						this.mapIds,
						this.stages,
						this.modes,
						this.tickrates,
						this.runType,
						this.mapTag,
						this.offset,
						this.limit
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
				+ "ids=" + this.ids
				+ ", mapIds=" + this.mapIds
				+ ", stages=" + this.stages
				+ ", modes=" + this.modes
				+ ", tickrates=" + this.tickrates
				+ ", runType=" + this.runType
				+ ", mapTag=\"" + this.mapTag + "\""
				+ ", offset=" + this.offset
				+ ", limit=" + this.limit
				+ "]");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetWorldRecordsTopRequest clone() {
		return new GetWorldRecordsTopRequest(this);
	}

	/**
	 * A request manager for /records/top/world_records/ endpoint.
	 */
	@SuppressWarnings("unused")
	public static final class Manager extends AbstractRequestManager<GetWorldRecordsTopRequest, List<RecordCountEntity>> {

		/**
		 * A record service.
		 */
		private final IRecordService recordService;

		/**
		 * A set of identifiers.
		 */
		private Set<Integer> ids;

		/**
		 * A set of map identifiers.
		 */
		private Set<Integer> mapIds;

		/**
		 * A set of stage identifiers.
		 */
		private Set<Integer> stages;

		/**
		 * A set of modes.
		 */
		private Set<EMode> modes;

		/**
		 * A set of tickrates.
		 */
		private Set<ETickrate> tickrates;

		/**
		 * A run type.
		 */
		private ERunType runType;

		/**
		 * A map tag.
		 */
		private String mapTag;

		/**
		 * An offset.
		 */
		private Integer offset;

		/**
		 * A limit.
		 */
		private Integer limit;

		/**
		 * Initialize a {@link GetWorldRecordsTopRequest.Manager} instance.
		 *
		 * @param recordService		record service
		 */
		public Manager(IRecordService recordService) {
			if (recordService == null) {
				throw new IllegalArgumentException("Record service mustn't be <null>");
			}

			this.recordService = recordService;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public GetWorldRecordsTopRequest build() {
			return new GetWorldRecordsTopRequest(
					this.ids,
					this.mapIds,
					this.stages,
					this.modes,
					this.tickrates,
					this.runType,
					this.mapTag,
					this.offset,
					this.limit
			);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Call<List<RecordCountEntity>> call(GetWorldRecordsTopRequest request) {
			return this.recordService.getWorldRecordsTop(
					request.ids,
					request.mapIds,
					request.stages,
					request.modes,
					request.tickrates,
					request.runType,
					request.mapTag,
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
		 * Set this set of map identifiers.
		 *
		 * @param mapIds	set of map identifiers, may be null
		 * @return			this instance
		 */
		public Manager setMapIds(Set<Integer> mapIds) {
			this.mapIds = mapIds;
			return this;
		}

		/**
		 * Set this set of map identifiers.
		 *
		 * @param mapIds	collection of map identifiers, may be null
		 * @return			this instance
		 */
		public Manager setMapIds(Collection<Integer> mapIds) {
			return this.setMapIds(UwSet.toSetOrNull(mapIds));
		}

		/**
		 * Set this set of map identifiers.
		 *
		 * @param mapIds	array of map identifiers, may be null
		 * @return			this instance
		 */
		public Manager setMapIds(Integer... mapIds) {
			return this.setMapIds(UwSet.toSetOrNull(mapIds));
		}

		/**
		 * Set this set of map identifiers.
		 *
		 * @param mapIds	stream of map identifiers, may be null
		 * @return			this instance
		 */
		public Manager setMapIds(Stream<Integer> mapIds) {
			return this.setMapIds(UwSet.toSetOrNull(mapIds));
		}

		/**
		 * Set this set of map identifiers.
		 *
		 * @param mapIds	integer stream of map identifiers, may be null
		 * @return			this instance
		 */
		public Manager setMapIds(IntStream mapIds) {
			return this.setMapIds(UwSet.toSetOrNull(mapIds));
		}

		/**
		 * Set this set of stage identifiers.
		 *
		 * @param stages	set of stage identifiers, may be null
		 * @return			this instance
		 */
		public Manager setStages(Set<Integer> stages) {
			this.stages = stages;
			return this;
		}

		/**
		 * Set this set of stage identifiers.
		 *
		 * @param stages	collection of stage identifiers, may be null
		 * @return			this instance
		 */
		public Manager setStages(Collection<Integer> stages) {
			return this.setStages(UwSet.toSetOrNull(stages));
		}

		/**
		 * Set this set of stage identifiers.
		 *
		 * @param stages	array of stage identifiers, may be null
		 * @return			this instance
		 */
		public Manager setStages(Integer... stages) {
			return this.setStages(UwSet.toSetOrNull(stages));
		}

		/**
		 * Set this set of stage identifiers.
		 *
		 * @param stages	stream of stage identifiers, may be null
		 * @return			this instance
		 */
		public Manager setStages(Stream<Integer> stages) {
			return this.setStages(UwSet.toSetOrNull(stages));
		}

		/**
		 * Set this set of stage identifiers.
		 *
		 * @param stages	integer stream of stage identifiers, may be null
		 * @return			this instance
		 */
		public Manager setStages(IntStream stages) {
			return this.setStages(UwSet.toSetOrNull(stages));
		}

		/**
		 * Set this set of modes.
		 *
		 * @param modes		set of modes, may be null
		 * @return			this instance
		 */
		public Manager setModes(Set<EMode> modes) {
			this.modes = modes;
			return this;
		}

		/**
		 * Set this set of modes.
		 *
		 * @param modes		collection of modes, may be null
		 * @return			this instance
		 */
		public Manager setModes(Collection<EMode> modes) {
			return this.setModes(UwSet.toSetOrNull(modes));
		}

		/**
		 * Set this set of modes.
		 *
		 * @param modes		array of modes, may be null
		 * @return			this instance
		 */
		public Manager setModes(EMode... modes) {
			return this.setModes(UwSet.toSetOrNull(modes));
		}

		/**
		 * Set this set of modes.
		 *
		 * @param modes		stream of modes, may be null
		 * @return			this instance
		 */
		public Manager setModes(Stream<EMode> modes) {
			return this.setModes(UwSet.toSetOrNull(modes));
		}

		/**
		 * Set this set of tickrates.
		 *
		 * @param tickrates		set of tickrates, may be null
		 * @return				this instance
		 */
		public Manager setTickrates(Set<ETickrate> tickrates) {
			this.tickrates = tickrates;
			return this;
		}

		/**
		 * Set this set of tickrates.
		 *
		 * @param tickrates		collection of tickrates, may be null
		 * @return				this instance
		 */
		public Manager setTickrates(Collection<ETickrate> tickrates) {
			return this.setTickrates(UwSet.toSetOrNull(tickrates));
		}

		/**
		 * Set this set of tickrates.
		 *
		 * @param tickrates		array of tickrates, may be null
		 * @return				this instance
		 */
		public Manager setTickrates(ETickrate... tickrates) {
			return this.setTickrates(UwSet.toSetOrNull(tickrates));
		}

		/**
		 * Set this set of tickrates.
		 *
		 * @param tickrates		stream of tickrates, may be null
		 * @return				this instance
		 */
		public Manager setTickrates(Stream<ETickrate> tickrates) {
			return this.setTickrates(UwSet.toSetOrNull(tickrates));
		}

		/**
		 * Set this run type.
		 *
		 * @param runType	run type, may be null
		 * @return			this instance
		 */
		public Manager setRunType(ERunType runType) {
			this.runType = runType;
			return this;
		}

		/**
		 * Set this map tag.
		 *
		 * @param mapTag	map tag, may be null
		 * @return			this instance
		 */
		public Manager setMapTag(String mapTag) {
			this.mapTag = mapTag;
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
