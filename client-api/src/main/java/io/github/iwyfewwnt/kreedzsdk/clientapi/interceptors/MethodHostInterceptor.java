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

import io.github.iwyfewwnt.kreedzsdk.clientapi.IHealthService;
import io.github.iwyfewwnt.kreedzsdk.clientapi.IMapImageService;
import io.github.iwyfewwnt.kreedzsdk.clientapi.IMapInfoService;
import io.github.iwyfewwnt.kreedzsdk.clientapi.IStatusService;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Invocation;
import retrofit2.http.*;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * A method API host interceptor.
 */
@SuppressWarnings("NullableProblems")
public final class MethodHostInterceptor implements Interceptor {

	/**
	 * A map of host strings by their service class.
	 */
	private static final Map<Class<?>, String> HOST_MAP = new HashMap<>();

	static {
		HOST_MAP.put(IHealthService.class, "health.global-api.com/api/v1");
		HOST_MAP.put(IMapImageService.class, "raw.githubusercontent.com/KZGlobalTeam/map-images");
		HOST_MAP.put(IMapInfoService.class, "raw.githubusercontent.com/iwyfewwnt/maps-info/main");
		HOST_MAP.put(IStatusService.class, "status.global-api.com/api/v2");
	}

	/**
	 * Initialize a {@link MethodHostInterceptor} instance.
	 */
	public MethodHostInterceptor() {
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
		Class<?> clazz = method.getDeclaringClass();

		String host = HOST_MAP.get(clazz);
		if (host == null) {
			return chain.proceed(request);
		}

		String endpoint = null;

		Annotation[] annotations = method.getAnnotations();
		for (Annotation annotation : annotations) {
			if (annotation instanceof GET
					|| annotation instanceof HEAD
					|| annotation instanceof POST
					|| annotation instanceof PUT
					|| annotation instanceof DELETE
					|| annotation instanceof OPTIONS
					|| annotation instanceof PATCH) {
				try {
					endpoint = (String) annotation.getClass()
							.getMethod("value")
							.invoke(annotation);
				} catch (IllegalAccessException
						| InvocationTargetException
						| NoSuchMethodException
						| ClassCastException e) {
					throw new UnsupportedOperationException(e);
				}
			}
		}

		if (endpoint == null) {
			return chain.proceed(request);
		}

		request = request.newBuilder()
				.url("https://" + host + "/" + endpoint)
				.build();

		return chain.proceed(request);
	}
}
