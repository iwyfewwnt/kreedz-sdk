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

package io.github.iwyfewwnt.kreedzsdk.converters;

import com.google.auto.service.AutoService;
import io.github.iwyfewwnt.kreedzsdk.converterapi.IKreedzQueryConverter;
import io.github.iwyfewwnt.kreedzsdk.structs.types.EBanType;
import retrofit2.Converter;

/**
 * An {@link EBanType} query converter.
 */
@SuppressWarnings("unused")
@AutoService(IKreedzQueryConverter.class)
public final class BanTypeQueryConverter implements Converter<EBanType, String>, IKreedzQueryConverter {

	/**
	 * Initialize a {@link BanTypeQueryConverter} instance.
	 */
	public BanTypeQueryConverter() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String convert(EBanType banType) {
		return banType.getApiName();
	}
}
