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

import io.github.iwyfewwnt.kreedzsdk.structs.types.EMode;
import io.github.iwyfewwnt.kreedzsdk.structs.types.ERunType;
import io.github.iwyfewwnt.kreedzsdk.structs.types.ETickrate;
import io.github.iwyfewwnt.uwretrofit.services.requests.impl.BaseRequestManager;
import io.github.iwyfewwnt.uwutils.UwSet;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A base request manager for /record_filters/{@literal **}/ endpoints.
 *
 * @param <T>	manager type
 * @param <U>	request type
 * @param <R>	response type
 */
@SuppressWarnings("unused")
public abstract class BaseRecordFiltersRequestManager<T extends BaseRecordFiltersRequestManager<T, U, R>, U extends GetRecordFiltersRequest, R>
		extends BaseRequestManager<T, U, R> {

	/**
	 * A set of identifiers.
	 */
	protected Set<Integer> ids;

	/**
	 * A set of ,ap identifiers.
	 */
	protected Set<Integer> mapIds;

	/**
	 * A set of stage identifiers.
	 */
	protected Set<Integer> stages;

	/**
	 * A set of modes.
	 */
	protected Set<EMode> modes;

	/**
	 * A set of tickrates.
	 */
	protected Set<ETickrate> tickrates;

	/**
	 * A run type.
	 */
	protected ERunType runType;

	/**
	 * An offset.
	 */
	protected Integer offset;

	/**
	 * a limit.
	 */
	protected Integer limit;

	/**
	 * Initialize a {@link BaseRecordFiltersRequestManager} instance.
	 */
	BaseRecordFiltersRequestManager() {
	}

	/**
	 * Set this set of identifiers.
	 *
	 * @param ids	set of identifiers, may be null
	 * @return		this instance
	 */
	public final T setIds(Set<Integer> ids) {
		this.ids = ids;
		return this.asT;
	}

	/**
	 * Set this set of identifiers.
	 *
	 * @param ids	collection of identifiers, may be null
	 * @return		this instance
	 */
	public final T setIds(Collection<Integer> ids) {
		return this.setIds(UwSet.createOrNull(ids));
	}

	/**
	 * Set this set of identifiers.
	 *
	 * @param ids	array of identifiers, may be null
	 * @return		this instance
	 */
	public final T setIds(Integer... ids) {
		return this.setIds(UwSet.createOrNull(ids));
	}

	/**
	 * Set this set of identifiers.
	 *
	 * @param ids	stream of identifiers, may be null
	 * @return		this instance
	 */
	public final T setIds(Stream<Integer> ids) {
		return this.setIds(UwSet.createOrNull(ids));
	}

	/**
	 * Set this set of identifiers.
	 *
	 * @param ids	integer stream identifiers, may be null
	 * @return		this instance
	 */
	public final T setIds(IntStream ids) {
		return this.setIds(UwSet.createOrNull(ids));
	}

	/**
	 * Set this set of map identifiers.
	 *
	 * @param mapIds	set of map identifiers, may be null
	 * @return			this instance
	 */
	public final T setMapIds(Set<Integer> mapIds) {
		this.mapIds = mapIds;
		return this.asT;
	}

	/**
	 * Set this set of map identifiers.
	 *
	 * @param mapIds	collection of map identifiers, may be null
	 * @return			this instance
	 */
	public final T setMapIds(Collection<Integer> mapIds) {
		return this.setMapIds(UwSet.createOrNull(mapIds));
	}

	/**
	 * Set this set of map identifiers.
	 *
	 * @param mapIds	array of map identifiers, may be null
	 * @return			this instance
	 */
	public final T setMapIds(Integer... mapIds) {
		return this.setMapIds(UwSet.createOrNull(mapIds));
	}

	/**
	 * Set this set of map identifiers.
	 *
	 * @param mapIds	stream of map identifiers, may be null
	 * @return			this instance
	 */
	public final T setMapIds(Stream<Integer> mapIds) {
		return this.setMapIds(UwSet.createOrNull(mapIds));
	}

	/**
	 * Set this set of map identifiers.
	 *
	 * @param mapIds	integer stream of map identifiers, may be null
	 * @return			this instance
	 */
	public final T setMapIds(IntStream mapIds) {
		return this.setMapIds(UwSet.createOrNull(mapIds));
	}

	/**
	 * Set this set of stage identifiers.
	 *
	 * @param stages	set of stage identifiers, may be null
	 * @return			this instance
	 */
	public final T setStages(Set<Integer> stages) {
		this.stages = stages;
		return this.asT;
	}

	/**
	 * Set this set of stage identifiers.
	 *
	 * @param stages	collection of stage identifiers, may be null
	 * @return			this instance
	 */
	public final T setStages(Collection<Integer> stages) {
		return this.setStages(UwSet.createOrNull(stages));
	}

	/**
	 * Set this set of stage identifiers.
	 *
	 * @param stages	array of stage identifiers, may be null
	 * @return			this instance
	 */
	public final T setStages(Integer... stages) {
		return this.setStages(UwSet.createOrNull(stages));
	}

	/**
	 * Set this set of stage identifiers.
	 *
	 * @param stages	stream of stage identifiers, may be null
	 * @return			this instance
	 */
	public final T setStages(Stream<Integer> stages) {
		return this.setStages(UwSet.createOrNull(stages));
	}

	/**
	 * Set this set of stage identifiers.
	 *
	 * @param stages	integer stream of stage identifiers, may be null
	 * @return			this instance
	 */
	public final T setStages(IntStream stages) {
		return this.setStages(UwSet.createOrNull(stages));
	}

	/**
	 * Set this set of modes.
	 *
	 * @param modes		set of modes, may be null
	 * @return			this instance
	 */
	public final T setModes(Set<EMode> modes) {
		this.modes = modes;
		return this.asT;
	}

	/**
	 * Set this set of modes.
	 *
	 * @param modes		collection of modes, may be null
	 * @return			this instance
	 */
	public final T setModes(Collection<EMode> modes) {
		return this.setModes(UwSet.createOrNull(modes));
	}

	/**
	 * Set this set of modes.
	 *
	 * @param modes		array of modes, may be null
	 * @return			this instance
	 */
	public final T setModes(EMode... modes) {
		return this.setModes(UwSet.createOrNull(modes));
	}

	/**
	 * Set this set of modes.
	 *
	 * @param modes		stream of modes, may be null
	 * @return			this instance
	 */
	public final T setModes(Stream<EMode> modes) {
		return this.setModes(UwSet.createOrNull(modes));
	}

	/**
	 * Set this set of tickrates.
	 *
	 * @param tickrates		set of tickrates, may be null
	 * @return				this instance
	 */
	public final T setTickrates(Set<ETickrate> tickrates) {
		this.tickrates = tickrates;
		return this.asT;
	}

	/**
	 * Set this set of tickrates.
	 *
	 * @param tickrates		collection of tickrates, may be null
	 * @return				this instance
	 */
	public final T setTickrates(Collection<ETickrate> tickrates) {
		return this.setTickrates(UwSet.createOrNull(tickrates));
	}

	/**
	 * Set this set of tickrates.
	 *
	 * @param tickrates		array of tickrates, may be null
	 * @return				this instance
	 */
	public final T setTickrates(ETickrate... tickrates) {
		return this.setTickrates(UwSet.createOrNull(tickrates));
	}

	/**
	 * Set this set of tickrates.
	 *
	 * @param tickrates		stream of tickrates, may be null
	 * @return				this instance
	 */
	public final T setTickrates(Stream<ETickrate> tickrates) {
		return this.setTickrates(UwSet.createOrNull(tickrates));
	}

	/**
	 * Set this run type.
	 *
	 * @param runType	run type, may be null
	 * @return			this instance
	 */
	public final T setRunType(ERunType runType) {
		this.runType = runType;
		return this.asT;
	}

	/**
	 * Set this offset.
	 *
	 * @param offset	offset, may be null
	 * @return			this instance
	 */
	public final T setOffset(Integer offset) {
		this.offset = offset;
		return this.asT;
	}

	/**
	 * Set this limit.
	 *
	 * @param limit		limit, may be null
	 * @return			this instance
	 */
	public final T setLimit(Integer limit) {
		this.limit = limit;
		return this.asT;
	}
}
