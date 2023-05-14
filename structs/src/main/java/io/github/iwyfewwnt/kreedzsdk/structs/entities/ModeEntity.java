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

package io.github.iwyfewwnt.kreedzsdk.structs.entities;

import com.google.gson.annotations.SerializedName;
import io.github.iwyfewwnt.kreedzsdk.structs.entities.inner.DataUpdater;
import io.github.iwyfewwnt.kreedzsdk.structs.types.ETickrate;
import io.github.iwyfewwnt.steamid.SteamId;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A kreedz API game mode entity.
 */
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod", "SynchronizeOnNonFinalField"})
public final class ModeEntity implements Serializable, Cloneable {

	/**
	 * A simple name of this class.
	 */
	private static final String SIMPLE_NAME = ModeEntity.class.getSimpleName();

	/**
	 * An identifier.
	 */
	@SerializedName("id")
	private final Integer id;

	/**
	 * A name.
	 */
	@SerializedName("name")
	private final String name;

	/**
	 * A description.
	 */
	@SerializedName("description")
	private final String description;

	/**
	 * A latest version.
	 */
	@SerializedName("latest_version")
	private final Integer latestVersion;

	/**
	 * A latest version description.
	 */
	@SerializedName("latest_version_description")
	private final String latestVersionDescription;

	/**
	 * A website domain.
	 */
	@SerializedName("website")
	private final String websiteDomain;

	/**
	 * A repository URL.
	 */
	@SerializedName("repo")
	private final String repositoryUrl;

	/**
	 * A contact person identifier.
	 */
	@SerializedName("contact_steamid64")
	private final SteamId contactSteamId;

	/**
	 * A list of supported tickrates.
	 */
	@SerializedName("supported_tickrates")
	private final List<ETickrate> supportedTickrates;

	/**
	 * A create date.
	 */
	@SerializedName("created_on")
	private final DateTime createDate;

	/**
	 * An update date.
	 */
	@SerializedName("updated_on")
	private final DateTime updateDate;

	/**
	 * A data updater.
	 */
	@SerializedName("updated_by_id")
	private final DataUpdater dataUpdater;

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
	 * Get this identifier.
	 *
	 * @return	identifier
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Get this name.
	 *
	 * @return	name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Get this description.
	 *
	 * @return	description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Get this latest version.
	 *
	 * @return	latest version
	 */
	public Integer getLatestVersion() {
		return this.latestVersion;
	}

	/**
	 * Get this latest version description.
	 *
	 * @return	latest version description
	 */
	public String getLatestVersionDescription() {
		return this.latestVersionDescription;
	}

	/**
	 * Get this website domain.
	 *
	 * @return	website domain
	 */
	public String getWebsiteDomain() {
		return this.websiteDomain;
	}

	/**
	 * Get this repository URL.
	 *
	 * @return	repository URL
	 */
	public String getRepositoryUrl() {
		return this.repositoryUrl;
	}

	/**
	 * Get this contact person identifier.
	 *
	 * @return	contact person identifier
	 */
	public SteamId getContactSteamId() {
		return this.contactSteamId;
	}

	/**
	 * Get this list of supported tickrates.
	 *
	 * @return	list of supported tickrates
	 */
	public List<ETickrate> getSupportedTickrates() {
		return this.supportedTickrates;
	}

	/**
	 * Get this create date.
	 *
	 * @return	create date
	 */
	public DateTime getCreateDate() {
		return this.createDate;
	}

	/**
	 * Get this update date.
	 *
	 * @return	update date
	 */
	public DateTime getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * Get this data updater.
	 *
	 * @return	data updater
	 */
	public DataUpdater getDataUpdater() {
		return this.dataUpdater;
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

		ModeEntity that = (ModeEntity) obj;

		return Objects.equals(this.id, that.id)
				&& Objects.equals(this.name, that.name)
				&& Objects.equals(this.description, that.description)
				&& Objects.equals(this.latestVersion, that.latestVersion)
				&& Objects.equals(this.latestVersionDescription, that.latestVersionDescription)
				&& Objects.equals(this.websiteDomain, that.websiteDomain)
				&& Objects.equals(this.repositoryUrl, that.repositoryUrl)
				&& Objects.equals(this.contactSteamId, that.contactSteamId)
				&& Objects.equals(this.supportedTickrates, that.supportedTickrates)
				&& Objects.equals(this.createDate, that.createDate)
				&& Objects.equals(this.updateDate, that.updateDate)
				&& Objects.equals(this.dataUpdater, that.dataUpdater);
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
							this.id,
							this.name,
							this.description,
							this.latestVersion,
							this.latestVersionDescription,
							this.websiteDomain,
							this.repositoryUrl,
							this.contactSteamId,
							this.supportedTickrates,
							this.createDate,
							this.updateDate,
							this.dataUpdater
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
					+ "id=" + this.id
					+ ", name=\"" + this.name + "\""
					+ ", description=\"" + this.description + "\""
					+ ", latestVersion=" + this.latestVersion
					+ ", latestVersionDescription=\"" + this.latestVersionDescription + "\""
					+ ", websiteDomain=\"" + this.websiteDomain + "\""
					+ ", repositoryUrl=\"" + this.repositoryUrl + "\""
					+ ", contactSteamId=" + this.contactSteamId
					+ ", supportedTickrates=" + this.supportedTickrates
					+ ", createDate=" + this.createDate
					+ ", updateDate=" + this.updateDate
					+ ", dataUpdater=" + this.dataUpdater
					+ "]");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ModeEntity clone() {
		return new ModeEntity(this);
	}

	/**
	 * Initialize a {@link ModeEntity} instance.
	 *
	 * @param id						identifier
	 * @param name						name
	 * @param description				description
	 * @param latestVersion				latest version
	 * @param latestVersionDescription	latest version description
	 * @param websiteDomain				website domain
	 * @param repositoryUrl				repository URL
	 * @param contactSteamId			contact person identifier
	 * @param supportedTickrates		list of supported tickrates
	 * @param createDate				create date
	 * @param updateDate				update date
	 * @param dataUpdater				data updater
	 */
	private ModeEntity(
			Integer id,
			String name,
			String description,
			Integer latestVersion,
			String latestVersionDescription,
			String websiteDomain,
			String repositoryUrl,
			SteamId contactSteamId,
			List<ETickrate> supportedTickrates,
			DateTime createDate,
			DateTime updateDate,
			DataUpdater dataUpdater
	) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.latestVersion = latestVersion;
		this.latestVersionDescription = latestVersionDescription;
		this.websiteDomain = websiteDomain;
		this.repositoryUrl = repositoryUrl;
		this.contactSteamId = contactSteamId;
		this.supportedTickrates = supportedTickrates;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.dataUpdater = dataUpdater;

		this.initMutexObjects();
	}

	/**
	 * Initialize a {@link ModeEntity} instance.
	 *
	 * <p>Define a copy constructor.
	 *
	 * @param that	instance to copy field values from
	 */
	private ModeEntity(ModeEntity that) {
		this(
				that.id,
				that.name,
				that.description,
				that.latestVersion,
				that.latestVersionDescription,
				that.websiteDomain,
				that.repositoryUrl,
				that.contactSteamId,
				that.supportedTickrates,
				that.createDate,
				that.updateDate,
				that.dataUpdater
		);

		this.hashCodeCache = that.hashCodeCache;
		this.stringCache = that.stringCache;
	}
}
