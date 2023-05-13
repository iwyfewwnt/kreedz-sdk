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

import java.io.Serializable;
import java.util.Objects;

/**
 * A map images API entity.
 */
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod", "SynchronizeOnNonFinalField"})
public final class MapImageEntity implements Serializable, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = MapImageEntity.class.getSimpleName();

	/**
	 * A map name.
	 */
	@SerializedName("name")
	private final String mapName;

	/**
	 * A source URL.
	 *
	 * <p>Format:
	 * <ul>
	 *     <li>Resolution - ≥1920x1080.
	 *     <li>Extension - *.jpg.
	 * </ul>
	 */
	@SerializedName("src")
	private final String sourceUrl;

	/**
	 * A jpg high resolution URL.
	 *
	 * <p>Format:
	 * <ul>
	 *     <li>Resolution - 1920x1080.
	 *     <li>Extension - *.jpg.
	 * </ul>
	 */
	@SerializedName("full")
	private final String jpgHighResolutionUrl;

	/**
	 * A jpg medium resolution URL.
	 *
	 * <p>Format:
	 * <ul>
	 *     <li>Resolution - 512x288.
	 *     <li>Extension - *.jpg.
	 * </ul>
	 */
	@SerializedName("medium")
	private final String jpgMediumResolutionUrl;

	/**
	 * A jpg low resolution URL.
	 *
	 * <p>Format:
	 * <ul>
	 *     <li>Resolution - 200x113.
	 *     <li>Extension - *.jpg.
	 * </ul>
	 */
	@SerializedName("thumb")
	private final String jpgLowResolutionUrl;

	/**
	 * A webp high resolution URL.
	 *
	 * <p>Format:
	 * <ul>
	 *     <li>Resolution - 1920x1080.
	 *     <li>Extension - *.webp.
	 * </ul>
	 */
	@SerializedName("webp")
	private final String webpHighResolutionUrl;

	/**
	 * A webp medium resolution URL.
	 *
	 * <p>Format:
	 * <ul>
	 *     <li>Resolution - 512x288.
	 *     <li>Extension - *.webp.
	 * </ul>
	 */
	@SerializedName("webp_medium")
	private final String webpMediumResolutionUrl;

	/**
	 * A webp low resolution URL.
	 *
	 * <p>Format:
	 * <ul>
	 *     <li>Resolution - 200x113.
	 *     <li>Extension - *.webp.
	 * </ul>
	 */
	@SerializedName("webp_thumb")
	private final String webpLowResolutionUrl;

	/**
	 * A {@link MapImageEntity#hashCode()} cache.
	 */
	private transient volatile Integer hashCodeCache;

	/**
	 * A {@link MapImageEntity#toString()} cache.
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
	 * Get this map name.
	 *
	 * @return	map name
	 */
	public String getMapName() {
		return this.mapName;
	}

	/**
	 * Get this source URL.
	 *
	 * @return	source URL
	 */
	public String getSourceUrl() {
		return this.sourceUrl;
	}

	/**
	 * Get this jpg high resolution URL.
	 *
	 * @return	jpg high resolution URL
	 */
	public String getJpgHighResolutionUrl() {
		return this.jpgHighResolutionUrl;
	}

	/**
	 * Get this jpg medium resolution URL.
	 *
	 * @return	jpg medium resolution URL
	 */
	public String getJpgMediumResolutionUrl() {
		return this.jpgMediumResolutionUrl;
	}

	/**
	 * Get this jpg low resolution URL.
	 *
	 * @return	jpg low resolution URL
	 */
	public String getJpgLowResolutionUrl() {
		return this.jpgLowResolutionUrl;
	}

	/**
	 * Get this webp high resolution URL.
	 *
	 * @return	webp high resolution URL
	 */
	public String getWebpHighResolutionUrl() {
		return this.webpHighResolutionUrl;
	}

	/**
	 * Get this webp medium resolution URL.
	 *
	 * @return	webp medium resolution URL
	 */
	public String getWebpMediumResolutionUrl() {
		return this.webpMediumResolutionUrl;
	}

	/**
	 * Get this webp low resolution URL.
	 *
	 * @return	webp low resolution URL
	 */
	public String getWebpLowResolutionUrl() {
		return this.webpLowResolutionUrl;
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

		MapImageEntity that = (MapImageEntity) obj;

		return Objects.equals(this.mapName, that.mapName)
				&& Objects.equals(this.sourceUrl, that.sourceUrl)
				&& Objects.equals(this.jpgHighResolutionUrl, that.jpgHighResolutionUrl)
				&& Objects.equals(this.jpgMediumResolutionUrl, that.jpgMediumResolutionUrl)
				&& Objects.equals(this.jpgLowResolutionUrl, that.jpgLowResolutionUrl)
				&& Objects.equals(this.webpHighResolutionUrl, that.webpHighResolutionUrl)
				&& Objects.equals(this.webpMediumResolutionUrl, that.webpMediumResolutionUrl)
				&& Objects.equals(this.webpLowResolutionUrl, that.webpLowResolutionUrl);
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
							this.mapName,
							this.sourceUrl,
							this.jpgHighResolutionUrl,
							this.jpgMediumResolutionUrl,
							this.jpgLowResolutionUrl,
							this.webpHighResolutionUrl,
							this.webpMediumResolutionUrl,
							this.webpLowResolutionUrl
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
					+ "mapName=\"" + this.mapName + "\""
					+ ", sourceUrl=\"" + this.sourceUrl + "\""
					+ ", jpgHighResolutionUrl=\"" + this.jpgHighResolutionUrl + "\""
					+ ", jpgMediumResolutionUrl=\"" + this.jpgMediumResolutionUrl + "\""
					+ ", jpgLowResolutionUrl=\"" + this.jpgLowResolutionUrl + "\""
					+ ", webpHighResolutionUrl=\"" + this.webpHighResolutionUrl + "\""
					+ ", webpMediumResolutionUrl=\"" + this.webpMediumResolutionUrl + "\""
					+ ", webpLowResolutionUrl=\"" + this.webpLowResolutionUrl + "\""
					+ "]");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MapImageEntity clone() {
		return new MapImageEntity(this);
	}

	/**
	 * Initialize a {@link MapImageEntity} instance.
	 *
	 * @param mapName					map name
	 * @param sourceUrl					source URL
	 * @param jpgHighResolutionUrl		jpg high resolution URL
	 * @param jpgMediumResolutionUrl	jpg medium resolution URL
	 * @param jpgLowResolutionUrl		jpg low resolution URL
	 * @param webpHighResolutionUrl		webp high resolution URL
	 * @param webpMediumResolutionUrl	webp medium resolution URL
	 * @param webpLowResolutionUrl		webp low resolution URL
	 */
	private MapImageEntity(
			String mapName,
			String sourceUrl,
			String jpgHighResolutionUrl,
			String jpgMediumResolutionUrl,
			String jpgLowResolutionUrl,
			String webpHighResolutionUrl,
			String webpMediumResolutionUrl,
			String webpLowResolutionUrl
	) {
		this.mapName = mapName;
		this.sourceUrl = sourceUrl;
		this.jpgHighResolutionUrl = jpgHighResolutionUrl;
		this.jpgMediumResolutionUrl = jpgMediumResolutionUrl;
		this.jpgLowResolutionUrl = jpgLowResolutionUrl;
		this.webpHighResolutionUrl = webpHighResolutionUrl;
		this.webpMediumResolutionUrl = webpMediumResolutionUrl;
		this.webpLowResolutionUrl = webpLowResolutionUrl;

		this.initMutexObjects();
	}

	/**
	 * Initialize a {@link MapImageEntity} instance.
	 *
	 * <p>Defines a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private MapImageEntity(MapImageEntity that) {
		this(
				that.mapName,
				that.sourceUrl,
				that.jpgHighResolutionUrl,
				that.jpgMediumResolutionUrl,
				that.jpgLowResolutionUrl,
				that.webpHighResolutionUrl,
				that.webpMediumResolutionUrl,
				that.webpLowResolutionUrl
		);

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}
}
