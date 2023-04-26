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

package io.github.iwyfewwnt.kreedzsdk.clientapi.interceptors;

import io.github.iwyfewwnt.kreedzsdk.clientapi.annotations.MethodVersion;
import io.github.iwyfewwnt.kreedzsdk.structs.types.EVersion;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Invocation;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * A method API version interceptor.
 */
@SuppressWarnings("NullableProblems")
public final class MethodVersionInterceptor implements Interceptor {

	/**
	 * A kreedz client version.
	 */
	private final EVersion clientVersion;
	/**
	 * Initialize a {@link MethodVersionInterceptor} instance.
	 *
	 * @param clientVersion		kreedz client version
	 */
	public MethodVersionInterceptor(EVersion clientVersion) {
		if (clientVersion == null) {
			throw new IllegalArgumentException("Client version mustn't be <null>");
		}

		this.clientVersion = clientVersion;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response intercept(Chain chain) throws IOException {
		Request request = chain.request();

		Invocation invocation = request.tag(Invocation.class);
		if (invocation == null) {
			return chain.proceed(request);
		}

		Method method = invocation.method();

		MethodVersion annotation = method.getAnnotation(MethodVersion.class);
		if (annotation == null) {
			return chain.proceed(request);
		}

		EVersion methodVersion = annotation.value();
		boolean isExplicit = annotation.explicit();

		int compareResult = EVersion.compare(this.clientVersion, methodVersion);
		if (isExplicit && compareResult != 0 || compareResult < 0) {
			String compareVersionString = isExplicit ? "=" : "≥";
			String methodVersionString = methodVersion.getApiName();
			String clientVersionString = this.clientVersion.getApiName();

			throw new UnsupportedOperationException(
					"[" + compareVersionString + "]"
							+ " Method supported API version - [" + methodVersionString + "]"
							+ " / Current client API version - [" + clientVersionString + "]"
			);
		}

		return chain.proceed(request);
	}
}
