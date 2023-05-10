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

package io.github.iwyfewwnt.kreedzsdk.structs.types.mapimage;

import io.github.iwyfewwnt.uwutils.UwArray;
import io.github.iwyfewwnt.uwutils.UwEnum;
import io.github.iwyfewwnt.uwutils.UwObject;
import io.github.iwyfewwnt.uwutils.UwString;

import java.util.function.Supplier;

/**
 * An enumeration of kreedz map image format types.
 *
 * @see <a href="https://vk.cc/ckedm3">map-images by KZGlobalTeam on GitHub</a>
 */
@SuppressWarnings("unused")
public enum EMapImageFormat {

	/**
	 * A kreedz map image format type - [JPG # ≥1920x1080] Source.
	 */
	SOURCE(EMapImageBranch.MASTER, "images", EMapImageExtension.JPG),

	/**
	 * A kreedz map image format type - [JPG # 1920x1080] Jpg High.
	 */
	JPG_HIGH(EMapImageBranch.PUBLIC, "images", EMapImageExtension.JPG),

	/**
	 * A kreedz map image format type - [JPG # 512x288] Jpg Medium.
	 */
	JPG_MEDIUM(EMapImageBranch.PUBLIC, "mediums", EMapImageExtension.JPG),

	/**
	 * A kreedz map image format type - [JPG # 200x113] Jpg Low.
	 */
	JPG_LOW(EMapImageBranch.PUBLIC, "thumbnails", EMapImageExtension.JPG),

	/**
	 * A kreedz map image format type - [WEBP # 1920x1080] Webp High.
	 */
	WEBP_HIGH(EMapImageBranch.PUBLIC, "webp", EMapImageExtension.WEBP),

	/**
	 * A kreedz map image format type - [WEBP # 512x288] Webp Medium.
	 */
	WEBP_MEDIUM(EMapImageBranch.PUBLIC, "webp/mediums", EMapImageExtension.WEBP),

	/**
	 * A kreedz map image format type - [WEBP # 200x113] Webp Low.
	 */
	WEBP_LOW(EMapImageBranch.PUBLIC, "webp/thumbs", EMapImageExtension.WEBP);

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = EMapImageFormat.class.getSimpleName();

	/**
	 * An array {@link EMapImageFormat} instances.
	 */
	private static final EMapImageFormat[] VALUES = UwEnum.values(EMapImageFormat.class);

	/**
	 * A branch.
	 */
	private final String branch;

	/**
	 * An endpoint.
	 */
	private final String endpoint;

	/**
	 * An extension.
	 */
	private final String extension;

	/**
	 * A {@link EMapImageFormat#toString()} cache.
	 */
	private String stringCache;

	/**
	 * Initialize an {@link EMapImageFormat} instance.
	 *
	 * @param branch		branch
	 * @param endpoint		endpoint
	 * @param extension		extension
	 */
	EMapImageFormat(EMapImageBranch branch, String endpoint, EMapImageExtension extension) {
		if (branch == null) {
			throw new IllegalArgumentException("Branch mustn't be <null>");
		}

		if (endpoint == null) {
			throw new IllegalArgumentException("Endpoint mustn't be <null>");
		}

		if (extension == null) {
			throw new IllegalArgumentException("Extension mustn't be <null>");
		}

		this.branch = branch.value;
		this.endpoint = endpoint;
		this.extension = extension.value;
	}

	/**
	 * Get this branch.
	 *
	 * @return	branch
	 */
	public String getBranch() {
		return this.branch;
	}

	/**
	 * Get this endpoint.
	 *
	 * @return	endpoint
	 */
	public String getEndpoint() {
		return this.endpoint;
	}

	/**
	 * Get this extension.
	 *
	 * @return	extension
	 */
	public String getExtension() {
		return this.extension;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		if (this.stringCache != null) {
			return this.stringCache;
		}

		return (this.stringCache = SIMPLE_NAME
				+ "::" + this.name() + "["
				+ "branch=\"" + this.branch + "\""
				+ ", endpoint=\"" + this.endpoint + "\""
				+ ", extension=\"" + this.extension + "\""
				+ "]");
	}

	/**
	 * Get a branch from the provided {@link EMapImageFormat} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EMapImageFormat} instance is {@code null}.
	 * </ul>
	 *
	 * @param format		map image format instance from which get the branch
	 * @param defaultValue	default value to return on failure
	 * @return				branch value of the map image format or the default one
	 */
	public static String getBranchOrElse(EMapImageFormat format, String defaultValue) {
		if (format == null) {
			return defaultValue;
		}

		return format.getBranch();
	}

	/**
	 * Get a branch from the provided {@link EMapImageFormat} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EMapImageFormat} instance is {@code null}.
	 * </ul>
	 *
	 * @param format				map image format instance from which get the branch
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						branch value of the map image format or the default one
	 */
	public static String getBranchOrElse(EMapImageFormat format, Supplier<String> defaultValueSupplier) {
		return UwObject.ifNull(getBranchOrNull(format), defaultValueSupplier);
	}

	/**
	 * Get a branch from the provided {@link EMapImageFormat} instance
	 * or return an empty string if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EMapImageFormat} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link EMapImageFormat#getBranchOrElse(EMapImageFormat, String)}
	 * w/ {@link UwString#EMPTY} as the default value.
	 *
	 * @param format	map image format instance from which get the branch
	 * @return			branch value of the map image format or the empty string
	 */
	public static String getBranchOrEmpty(EMapImageFormat format) {
		return getBranchOrElse(format, UwString.EMPTY);
	}

	/**
	 * Get a branch from the provided {@link EMapImageFormat} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EMapImageFormat} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link EMapImageFormat#getBranchOrElse(EMapImageFormat, String)}
	 * w/ {@code null} as the default value.
	 *
	 * @param format	map image format instance from which get the branch
	 * @return			branch value of the map image format or {@code null}
	 */
	public static String getBranchOrNull(EMapImageFormat format) {
		return getBranchOrElse(format, (String) null);
	}

	/**
	 * Get an endpoint from the provided {@link EMapImageFormat} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EMapImageFormat} instance is {@code null}.
	 * </ul>
	 *
	 * @param format		map image format instance from which get the endpoint
	 * @param defaultValue	default value to return on failure
	 * @return				endpoint value of the map image format or the default one
	 */
	public static String getEndpointOrElse(EMapImageFormat format, String defaultValue) {
		if (format == null) {
			return defaultValue;
		}

		return format.getEndpoint();
	}

	/**
	 * Get an endpoint from the provided {@link EMapImageFormat} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EMapImageFormat} instance is {@code null}.
	 * </ul>
	 *
	 * @param format				map image format instance from which get the endpoint
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						endpoint value of the map image format or the default one
	 */
	public static String getEndpointOrElse(EMapImageFormat format, Supplier<String> defaultValueSupplier) {
		return UwObject.ifNull(getEndpointOrNull(format), defaultValueSupplier);
	}

	/**
	 * Get an endpoint from the provided {@link EMapImageFormat} instance
	 * or return an empty string if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EMapImageFormat} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link EMapImageFormat#getEndpointOrElse(EMapImageFormat, String)}
	 * w/ {@link UwString#EMPTY} as the default value.
	 *
	 * @param format	map image format instance from which get the endpoint
	 * @return			endpoint value of the map image format or the empty string
	 */
	public static String getEndpointOrEmpty(EMapImageFormat format) {
		return getEndpointOrElse(format, UwString.EMPTY);
	}

	/**
	 * Get an endpoint from the provided {@link EMapImageFormat} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EMapImageFormat} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link EMapImageFormat#getEndpointOrElse(EMapImageFormat, String)}
	 * w/ {@code null} as the default value.
	 *
	 * @param format	map image format instance from which get the endpoint
	 * @return			endpoint value of the map image format or {@code null}
	 */
	public static String getEndpointOrNull(EMapImageFormat format) {
		return getEndpointOrElse(format, (String) null);
	}

	/**
	 * Get an extension from the provided {@link EMapImageFormat} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EMapImageFormat} instance is {@code null}.
	 * </ul>
	 *
	 * @param format		map image format instance from which get the extension
	 * @param defaultValue	default value to return on failure
	 * @return				extension value of the map image format or the default one
	 */
	public static String getExtensionOrElse(EMapImageFormat format, String defaultValue) {
		if (format == null) {
			return defaultValue;
		}

		return format.getExtension();
	}

	/**
	 * Get an extension from the provided {@link EMapImageFormat} instance
	 * or return a default value if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EMapImageFormat} instance is {@code null}.
	 * </ul>
	 *
	 * @param format				map image format instance from which get the extension
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						extension value of the map image format or the default one
	 */
	public static String getExtensionOrElse(EMapImageFormat format, Supplier<String> defaultValueSupplier) {
		return UwObject.ifNull(getExtensionOrNull(format), defaultValueSupplier);
	}

	/**
	 * Get an extension from the provided {@link EMapImageFormat} instance
	 * or return an empty string if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EMapImageFormat} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link EMapImageFormat#getExtensionOrElse(EMapImageFormat, String)}
	 * w/ {@link UwString#EMPTY} as the default value.
	 *
	 * @param format	map image format instance from which get the extension
	 * @return			extension value of the map image format or the empty string
	 */
	public static String getExtensionOrEmpty(EMapImageFormat format) {
		return getExtensionOrElse(format, UwString.EMPTY);
	}

	/**
	 * Get an extension from the provided {@link EMapImageFormat} instance
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>{@link EMapImageFormat} instance is {@code null}.
	 * </ul>
	 *
	 * <p>Wraps {@link EMapImageFormat#getExtensionOrElse(EMapImageFormat, String)}
	 * w/ {@code null} as the default value.
	 *
	 * @param format	map image format instance from which get the extension
	 * @return			extension value of the map image format or {@code null}
	 */
	public static String getExtensionOrNull(EMapImageFormat format) {
		return getExtensionOrElse(format, (String) null);
	}

	/**
	 * Get an {@link EMapImageFormat} instance by its index
	 * or return a default value if failed.
	 *
	 * @param index			index of the instance
	 * @param defaultValue	default value to return on failure
	 * @return				associated {@link EMapImageFormat} instance or the default value
	 */
	public static EMapImageFormat fromIndexOrElse(Integer index, EMapImageFormat defaultValue) {
		return UwArray.getOrElse(index, VALUES, defaultValue);
	}

	/**
	 * Get an {@link EMapImageFormat} instance by its index
	 * or return a default value if failed.
	 *
	 * @param index					index of the instance
	 * @param defaultValueSupplier	supplier from which get the default value
	 * @return						associated {@link EMapImageFormat} instance or the default value
	 */
	public static EMapImageFormat fromIndexOrElse(Integer index, Supplier<EMapImageFormat> defaultValueSupplier) {
		return UwObject.ifNull(fromIndexOrNull(index), defaultValueSupplier);
	}

	/**
	 * Get an {@link EMapImageFormat} instance by its index
	 * or return a default value if failed.
	 *
	 * <p>Wraps {@link EMapImageFormat#fromIndexOrElse(Integer, EMapImageFormat)}
	 * w/ {@code null} as the default value.
	 *
	 * @param index		index of the instance
	 * @return			associated {@link EMapImageFormat} instance or {@code null}
	 */
	public static EMapImageFormat fromIndexOrNull(Integer index) {
		return fromIndexOrElse(index, (EMapImageFormat) null);
	}
}
