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

package io.github.iwyfewwnt.kreedzsdk.clientaspects;

import io.github.iwyfewwnt.kreedzsdk.clientapi.IKreedzClient;
import io.github.iwyfewwnt.kreedzsdk.clientapi.annotations.MethodApiVersion;
import io.github.iwyfewwnt.kreedzsdk.structs.types.EVersion;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.concurrent.atomic.AtomicReference;

/**
 * A {@literal @MethodApiVersion} annotation aspect.
 */
@Aspect
public class MethodVersionAspect {

	/**
	 * Client API version.
	 */
	private final AtomicReference<EVersion> clientApiVersion;

	/**
	 * Initialize a {@code MethodVersionAspect} instance.
	 */
	public MethodVersionAspect() {
		this.clientApiVersion = new AtomicReference<>();
	}

	/**
	 * Set this client API version from intercepted kreedz client instance.
	 *
	 * @param client	kreedz client instance
	 */
	@AfterReturning(
			value = "io.github.iwyfewwnt.kreedzsdk.clientaspects.KreedzClientAspect.clientInitPoint() && this(client)",
			argNames = "client"
	)
	public void afterClientInit(IKreedzClient client) {
		this.clientApiVersion.set(client.getVersion());
	}

	/**
	 * Pointcut for {@link MethodApiVersion} annotation.
	 */
	@Pointcut("@annotation(io.github.iwyfewwnt.kreedzsdk.clientapi.annotations.MethodApiVersion)")
	public void methodApiCallPoint() {
	}

	/**
	 * Compare method API version with the current client API version.
	 *
	 * @param annotation					method API version annotation
	 * @throws IllegalStateException		if the method {@literal &} client version differs
	 */
	@Before(
			value = "methodApiCallPoint() && this(annotation)",
			argNames = "annotation"
	)
	public void beforeMethodApiCall(MethodApiVersion annotation) {
		EVersion clientApiVersion = this.clientApiVersion.get();

		if (clientApiVersion == null) {
			return;
		}

		EVersion methodApiVersion = annotation.value();
		boolean isExplicit = annotation.explicit();

		int compareResult = EVersion.compare(clientApiVersion, methodApiVersion);

		if (isExplicit && compareResult != 0 || compareResult < 0) {
			String compareVersionString = isExplicit ? "=" : "≥";
			String methodVersionString = methodApiVersion.getApiName();
			String clientVersionString = clientApiVersion.getApiName();

			throw new UnsupportedOperationException(
					"[" + compareVersionString + "]" +
							" Method supported API version - [" + methodVersionString + "]" +
							" / Current client API version - [" + clientVersionString + "]"
			);
		}
	}
}
