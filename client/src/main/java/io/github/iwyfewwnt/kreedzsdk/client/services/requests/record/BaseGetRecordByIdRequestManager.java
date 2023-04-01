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

import io.github.iwyfewwnt.uwretrofit.services.requests.impl.BaseRequestManager;

/**
 * A base request manager for record identifier only endpoints.
 *
 * @param <T>	manager type
 * @param <U>	request type
 * @param <R>	response type
 */
public abstract class BaseGetRecordByIdRequestManager<T extends BaseGetRecordByIdRequestManager<T, U, R>, U extends GetRecordByIdRequest, R>
		extends BaseRequestManager<T, U, R> {

	/**
	 * An identifier.
	 */
	protected Integer id;

	/**
	 * Initialize a {@link BaseGetRecordByIdRequestManager} instance.
	 */
	BaseGetRecordByIdRequestManager() {
	}

	/**
	 * Set this identifier.
	 *
	 * @param id	identifier
	 * @return		this instance
	 */
	public final T setId(Integer id) {
		this.id = id;
		return this.asT;
	}
}
