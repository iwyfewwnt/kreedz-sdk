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

package io.github.iwyfewwnt.kreedzsdk.client.services.requests.map;

import io.github.iwyfewwnt.kreedzsdk.clientapi.IMapService;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.MapEntity;
import io.github.iwyfewwnt.kreedzsdk.structs.types.EDifficulty;
import io.github.iwyfewwnt.uwretrofit.services.requests.IRequest;
import io.github.iwyfewwnt.uwretrofit.services.requests.impl.AbstractRequestManager;
import io.github.iwyfewwnt.uwutils.UwSet;
import org.joda.time.DateTime;
import retrofit2.Call;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A request for /maps/ endpoint.
 */
@SuppressWarnings({"MethodDoesntCallSuperMethod", "SynchronizeOnNonFinalField"})
public final class GetMapsRequest implements IRequest, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = GetMapsRequest.class.getSimpleName();

	/**
	 * A set of identifiers.
	 */
	private final Set<Integer> ids;

	/**
	 * A map name.
	 */
	private final String mapName;

	/**
	 * A left file size threshold.
	 */
	private final Integer fileSizeLargerThan;

	/**
	 * A right file size threshold.
	 */
	private final Integer fileSizeSmallerThan;

	/**
	 * Am "isValidated" boolean value.
	 */
	private final Boolean isValidated;

	/**
	 * A difficulty.
	 */
	private final EDifficulty difficulty;

	/**
	 * A create since date.
	 */
	private final DateTime createdSinceDate;

	/**
	 * An updated since date.
	 */
	private final DateTime updatedSinceDate;

	/**
	 * An offset.
	 */
	private final Integer offset;

	/**
	 * A limit.
	 */
	private final Integer limit;

	/**
	 * A {@link #hashCode()} cache.
	 */
	private transient volatile Integer hashCodeCache;

	/**
	 * A {@link #toString()} cache.
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
	 * Initialize a {@link GetMapsRequest} instance.
	 *
	 * @param ids					set of identifiers
	 * @param mapName				map name
	 * @param fileSizeLargerThan	left file size threshold
	 * @param fileSizeSmallerThan	right file size threshold
	 * @param isValidated			"isValidated" boolean value
	 * @param difficulty			difficulty
	 * @param createdSinceDate		created since date
	 * @param updatedSinceDate		updated since date
	 * @param offset				offset
	 * @param limit					limit
	 */
	private GetMapsRequest(
			Set<Integer> ids,
			String mapName,
			Integer fileSizeLargerThan,
			Integer fileSizeSmallerThan,
			Boolean isValidated,
			EDifficulty difficulty,
			DateTime createdSinceDate,
			DateTime updatedSinceDate,
			Integer offset,
			Integer limit
	) {
		ids = UwSet.toUnmodifiable(ids);

		this.ids = ids;
		this.mapName = mapName;
		this.fileSizeLargerThan = fileSizeLargerThan;
		this.fileSizeSmallerThan = fileSizeSmallerThan;
		this.isValidated = isValidated;
		this.difficulty = difficulty;
		this.createdSinceDate = createdSinceDate;
		this.updatedSinceDate = updatedSinceDate;
		this.offset = offset;
		this.limit = limit;

		this.initMutexObjects();
	}

	/**
	 * Initialize a {@link GetMapsRequest} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private GetMapsRequest(GetMapsRequest that) {
		this(
				that.ids,
				that.mapName,
				that.fileSizeLargerThan,
				that.fileSizeSmallerThan,
				that.isValidated,
				that.difficulty,
				that.createdSinceDate,
				that.updatedSinceDate,
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

		GetMapsRequest that = (GetMapsRequest) obj;

		return Objects.equals(this.ids, that.ids)
				&& Objects.equals(this.mapName, that.mapName)
				&& Objects.equals(this.fileSizeLargerThan, that.fileSizeLargerThan)
				&& Objects.equals(this.fileSizeSmallerThan, that.fileSizeSmallerThan)
				&& Objects.equals(this.isValidated, that.isValidated)
				&& this.difficulty == that.difficulty
				&& Objects.equals(this.createdSinceDate, that.createdSinceDate)
				&& Objects.equals(this.updatedSinceDate, that.updatedSinceDate)
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
							this.mapName,
							this.fileSizeLargerThan,
							this.fileSizeSmallerThan,
							this.isValidated,
							this.difficulty,
							this.createdSinceDate,
							this.updatedSinceDate,
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
					+ ", mapName=\"" + this.mapName + "\""
					+ ", fileSizeLargerThan=" + this.fileSizeLargerThan
					+ ", fileSizeSmallerThan=" + this.fileSizeSmallerThan
					+ ", isValidated=" + this.isValidated
					+ ", difficulty=" + this.difficulty
					+ ", createdSinceDate=" + this.createdSinceDate
					+ ", updatedSinceDate=" + this.updatedSinceDate
					+ ", offset=" + this.offset
					+ ", limit=" + this.limit
					+ "]");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetMapsRequest clone() {
		return new GetMapsRequest(this);
	}

	/**
	 * A request manager for /maps/ endpoint.
	 */
	@SuppressWarnings("unused")
	public static final class Manager extends AbstractRequestManager<GetMapsRequest, List<MapEntity>> {

		/**
		 * A map service.
		 */
		private final IMapService mapService;

		/**
		 * A set of identifiers.
		 */
		private Set<Integer> ids;

		/**
		 * A map name.
		 */
		private String mapName;

		/**
		 * A left file size threshold.
		 */
		private Integer fileSizeLargerThan;

		/**
		 * A right file size threshold.
		 */
		private Integer fileSizeSmallerThan;

		/**
		 * An "isValidated" boolean value.
		 */
		private Boolean isValidated;

		/**
		 * A difficulty tier.
		 */
		private EDifficulty difficulty;

		/**
		 * A created since date.
		 */
		private DateTime createdSinceDate;

		/**
		 * An updated since date.
		 */
		private DateTime updatedSinceDate;

		/**
		 * An offset.
		 */
		private Integer offset;

		/**
		 * A limit.
		 */
		private Integer limit;

		/**
		 * Initialize a {@link GetMapsRequest.Manager} instance.
		 *
		 * @param mapService	map service
		 */
		public Manager(IMapService mapService) {
			if (mapService == null) {
				throw new IllegalArgumentException("Map service mustn't be <null>");
			}

			this.mapService = mapService;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public GetMapsRequest build() {
			return new GetMapsRequest(
					this.ids,
					this.mapName,
					this.fileSizeLargerThan,
					this.fileSizeSmallerThan,
					this.isValidated,
					this.difficulty,
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
		public Call<List<MapEntity>> call(GetMapsRequest request) {
			return this.mapService.getMaps(
					request.ids,
					request.mapName,
					request.fileSizeLargerThan,
					request.fileSizeSmallerThan,
					request.isValidated,
					request.difficulty,
					request.createdSinceDate,
					request.updatedSinceDate,
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
			return this.setIds(UwSet.createOrNull(ids));
		}

		/**
		 * Set this set of identifiers.
		 *
		 * @param ids	array of identifiers, may be null
		 * @return		this instance
		 */
		public Manager setIds(Integer... ids) {
			return this.setIds(UwSet.createOrNull(ids));
		}

		/**
		 * Set this set of  identifiers.
		 *
		 * @param ids	stream of identifiers, may be null
		 * @return		this instance
		 */
		public Manager setIds(Stream<Integer> ids) {
			return this.setIds(UwSet.createOrNull(ids));
		}

		/**
		 * Set this set of identifiers.
		 *
		 * @param ids	integer stream of identifiers, may be null
		 * @return		this instance
		 */
		public Manager setIds(IntStream ids) {
			return this.setIds(UwSet.createOrNull(ids));
		}

		/**
		 * Set this map name.
		 *
		 * @param mapName	map name, may be null
		 * @return			this instance
		 */
		public Manager setMapName(String mapName) {
			this.mapName = mapName;
			return this;
		}

		/**
		 * Set this left file size threshold.
		 *
		 * @param fileSizeLargerThan	left file size threshold, may be null
		 * @return						this instance
		 */
		public Manager setFileSizeLargerThan(Integer fileSizeLargerThan) {
			this.fileSizeLargerThan = fileSizeLargerThan;
			return this;
		}

		/**
		 * Set this right file size threshold.
		 *
		 * @param fileSizeSmallerThan	right file size threshold, may be null
		 * @return						this instance
		 */
		public Manager setFileSizeSmallerThan(Integer fileSizeSmallerThan) {
			this.fileSizeSmallerThan = fileSizeSmallerThan;
			return this;
		}

		/**
		 * Set this is validated boolean value.
		 *
		 * @param isValidated	"isValidated" boolean value, may be null
		 * @return				this instance
		 */
		public Manager setIsValidated(Boolean isValidated) {
			this.isValidated = isValidated;
			return this;
		}

		/**
		 * Set this difficulty.
		 *
		 * @param difficulty	difficulty, may be null
		 * @return				this instance
		 */
		public Manager setDifficulty(EDifficulty difficulty) {
			this.difficulty = difficulty;
			return this;
		}

		/**
		 * Set this created since date.
		 *
		 * @param createdSinceDate	created since date, may be null
		 * @return					this instance
		 */
		public Manager setCreatedSinceDate(DateTime createdSinceDate) {
			this.createdSinceDate = createdSinceDate;
			return this;
		}

		/**
		 * Set this updated since date.
		 *
		 * @param updatedSinceDate	updated since date, may be null
		 * @return					this instance
		 */
		public Manager setUpdatedSinceDate(DateTime updatedSinceDate) {
			this.updatedSinceDate = updatedSinceDate;
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
