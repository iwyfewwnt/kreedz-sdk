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

package io.github.iwyfewwnt.kreedzsdk.clientapi.annotations;

import io.github.iwyfewwnt.kreedzsdk.structs.types.EVersion;

import java.lang.annotation.*;

/**
 * A method API version annotation
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MethodVersion {

	/**
	 * Get this method API version.
	 *
	 * @return	method API version
	 */
	EVersion value();

	/**
	 * Get this "isExplicit" boolean value.
	 *
	 * <p>Defines operators to compare method {@literal &} client API versions.
	 * If {@code true} will use "equals" operator else "less than".
	 *
	 * @return	"isExplicit" boolean value
	 */
	boolean explicit() default false;
}
