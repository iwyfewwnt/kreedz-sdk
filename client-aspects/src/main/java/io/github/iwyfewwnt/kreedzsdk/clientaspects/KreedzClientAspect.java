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
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * An IKreedzClient aspect.
 */
@Aspect
public class KreedzClientAspect {

	/**
	 * Is created boolean value.
	 *
	 * <p>Defines that even one IKreedzClient instance was created.
	 */
	private final AtomicBoolean isCreated;

	/**
	 * Initialize a {@code KreedzClientAspect} instance.
	 */
	public KreedzClientAspect() {
		this.isCreated = new AtomicBoolean(false);
	}

	/**
	 * Pointcut for {@link IKreedzClient} initialization.
	 */
	@Pointcut("initialization(io.github.iwyfewwnt.kreedzsdk.clientapi.IKreedzClient+.new(..))")
	public void clientInitPoint() {
	}

	/**
	 * Check if already one instance was created.
	 *
	 * @throws IllegalStateException if this is created boolean value is true
	 */
	@Before("clientInitPoint()")
	public void beforeClientInit() {
		if (this.isCreated.get()) {
			throw new IllegalStateException(
					"Only one <IKreedzClient> instance should be created"
			);
		}
	}

	/**
	 * Set this is created boolean value to true.
	 */
	@AfterReturning("clientInitPoint()")
	public void afterClientInit() {
		this.isCreated.set(true);
	}
}
