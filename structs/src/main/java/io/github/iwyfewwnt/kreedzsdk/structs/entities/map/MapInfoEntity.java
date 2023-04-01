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

package io.github.iwyfewwnt.kreedzsdk.structs.entities.map;

import com.google.gson.annotations.SerializedName;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.inner.Mapper;
import io.github.iwyfewwnt.kreedzsdk.structs.types.EDifficulty;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A maps information API entity.
 */
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod"})
public final class MapInfoEntity implements Serializable, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = MapInfoEntity.class.getSimpleName();

	/**
	 * A map identifier.
	 */
	@SerializedName("id")
	private final Integer id;

	/**
	 * A map name.
	 */
	@SerializedName("name")
	private final String name;

	/**
	 * A map difficulty.
	 */
	@SerializedName("difficulty")
	private final EDifficulty difficulty;

	/**
	 * A workshop URL.
	 */
	@SerializedName("workshop_url")
	private final String workshopUrl;

	/**
	 * A list of mappers.
	 */
	@SerializedName("mappers")
	private final List<Mapper> mappers;

	/**
	 * A {@link MapInfoEntity#isCompleted} cache.
	 */
	private transient Boolean isCompletedCache;

	/**
	 * A {@link MapInfoEntity#hashCode()} cache.
	 */
	private transient Integer hashCodeCache;

	/**
	 * A {@link MapInfoEntity#toString()} cache.
	 */
	private transient String stringCache;

	/**
	 * Get this map identifier.
	 *
	 * @return	map identifier
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Get this map name.
	 *
	 * @return	map name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Get this map difficulty.
	 *
	 * @return	map difficulty
	 */
	public EDifficulty getDifficulty() {
		return this.difficulty;
	}

	/**
	 * Get this workshop URL.
	 *
	 * @return	workshop URL
	 */
	public String getWorkshopUrl() {
		return this.workshopUrl;
	}

	/**
	 * Get this mappers list.
	 *
	 * @return	list of mappers
	 */
	public List<Mapper> getMappers() {
		return this.mappers;
	}

	/**
	 * Check if this instance is fully filled.
	 *
	 * @return		boolean value as a result,
	 * 				true - yes, false - no
	 */
	public boolean isCompleted() {
		if (this.isCompletedCache != null) {
			return this.isCompletedCache;
		}

		// Other fields besides #mappers may be omitted,
		//  but we will keep them for future compatibility.
		if (this.id == null
				|| this.name == null
				|| this.difficulty == null
				|| this.workshopUrl == null
				|| this.mappers == null) {
			return (this.isCompletedCache = false);
		}

		return (this.isCompletedCache = this.mappers.stream()
				.allMatch(Mapper::isCompleted));
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

		MapInfoEntity that = (MapInfoEntity) obj;

		return Objects.equals(this.id, that.id)
				&& Objects.equals(this.name, that.name)
				&& this.difficulty == that.difficulty
				&& Objects.equals(this.workshopUrl, that.workshopUrl)
				&& Objects.equals(this.mappers, that.mappers);
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
						this.name,
						this.difficulty,
						this.workshopUrl,
						this.mappers
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
				+ ", name=\"" + this.name + "\""
				+ ", difficulty=" + this.difficulty
				+ ", workshopUrl=\"" + this.workshopUrl + "\""
				+ ", mappers=" + this.mappers
				+ "]");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MapInfoEntity clone() {
		return new MapInfoEntity(this);
	}

	/**
	 * Initialize a {@link MapInfoEntity} instance.
	 *
	 * @param id			map identifier
	 * @param name			map name
	 * @param difficulty	map difficulty
	 * @param workshopUrl	workshop URL
	 * @param mappers		list of mappers
	 */
	private MapInfoEntity(
			Integer id,
			String name,
			EDifficulty difficulty,
			String workshopUrl,
			List<Mapper> mappers
	) {
		this.id = id;
		this.name = name;
		this.difficulty = difficulty;
		this.workshopUrl = workshopUrl;
		this.mappers = mappers;
	}

	/**
	 * Initialize a {@link MapInfoEntity} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private MapInfoEntity(MapInfoEntity that) {
		this(
				that.id,
				that.name,
				that.difficulty,
				that.workshopUrl,
				that.mappers
		);

		this.isCompletedCache = that.isCompletedCache;

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}
}
