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

package io.github.iwyfewwnt.kreedzsdk.client;

import com.google.gson.*;
import io.github.iwyfewwnt.gsonnative.GsonNativeTypeAdapterFactory;
import io.github.iwyfewwnt.kreedzsdk.adapterapi.IKreedzTypeAdapter;
import io.github.iwyfewwnt.kreedzsdk.adapterapi.IKreedzTypeAdapterFactory;
import io.github.iwyfewwnt.kreedzsdk.client.services.*;
import io.github.iwyfewwnt.kreedzsdk.clientapi.*;
import io.github.iwyfewwnt.kreedzsdk.clientapi.interceptors.ServiceBaseUrlInterceptor;
import io.github.iwyfewwnt.kreedzsdk.clientapi.interceptors.MethodVersionInterceptor;
import io.github.iwyfewwnt.kreedzsdk.converterapi.IKreedzConverterFactory;
import io.github.iwyfewwnt.kreedzsdk.converterapi.IKreedzQueryConverter;
import io.github.iwyfewwnt.kreedzsdk.structs.types.EVersion;
import io.github.iwyfewwnt.uwgson.UwTypeAdapterFactory;
import io.github.iwyfewwnt.uwretrofit.IRetrofitClient;
import io.github.iwyfewwnt.uwretrofit.converters.QueryConverterFactory;
import io.github.iwyfewwnt.uwretrofit.services.IServiceWrapper;
import io.github.iwyfewwnt.uwretrofit.services.impl.RetrofitServiceWrapper;
import io.github.iwyfewwnt.uwutils.*;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * A kreedz API client.
 */
@SuppressWarnings({"unused", "unchecked"})
public class KreedzClient implements IKreedzClient, IRetrofitClient {

	/**
	 * An initialized {@code VavrTypeAdapterFactory} class.
	 *
	 * @see <a href="https://vk.cc/co9mRa">vavr-gson by iwyfewwnt on GitHub</a>
	 */
	private static final Class<?> VAVR_TYPE_ADAPTER_FACTORY_CLASS = initVavrTypeAdapterFactoryClass();

	/**
	 * Initialize the {@code VavrTypeAdapterFactory} class
	 * or return {@code null} if failed.
	 *
	 * <p>Possible failure cases:
	 * <ul>
	 *     <li>There is no such class in the classpath.
	 * </ul>
	 *
	 * @return	{@code VavrTypeAdapterFactory} class or {@code null}
	 */
	private static Class<?> initVavrTypeAdapterFactoryClass() {
		try {
			return Class.forName("io.github.iwyfewwnt.vavrgson.VavrTypeAdapterFactory");
		} catch (LinkageError e) {
			e.printStackTrace();
		} catch (ClassNotFoundException ignored) {
		}

		return null;
	}

	/**
	 * A map of service wrapper/implementation classes by their service interface class.
	 */
	private static final Map<Class<?>, Class<? extends IKreedzService>> SERVICE_CLASSES = initServiceSpi();

	/**
	 * A map of service wrapper/implementation instances by their service interface class.
	 */
	private static final Map<Class<?>, IKreedzService> SERVICE_CACHE = new ConcurrentHashMap<>();

	/**
	 * A kreedz API base URL format string.
	 *
	 * <p>Arguments in order:
	 * <ul>
	 *     <li>String :: Kreedz API version.
	 * </ul>
	 */
	private static final String BASE_URL_FMT = "https://kztimerglobal.com/api/%s/";

	/**
	 * An API version.
	 */
	protected final EVersion version;

	/**
	 * A base URL.
	 */
	protected final String baseUrl;

	/**
	 * A retrofit.
	 */
	protected final Retrofit retrofit;

	/**
	 * Initialize a {@link KreedzClient} instance.
	 *
	 * @param version	API version
	 */
	public KreedzClient(EVersion version) {
		this.version = UwObject.ifNull(version, EVersion.LATEST);

		this.baseUrl = String.format(BASE_URL_FMT, this.version.getApiName());

		this.retrofit = this.initRetrofit();
	}

	/**
	 * Initialize a {@link KreedzClient} instance.
	 *
	 * <p>Wraps {@link #KreedzClient(EVersion)}
	 * w/ {@code null} as the API version.
	 */
	public KreedzClient() {
		this(null);
	}

	/**
	 * Get this API version.
	 *
	 * @return	API version
	 */
	public final EVersion getVersion() {
		return this.version;
	}

	/**
	 * Get this base URL.
	 *
	 * @return	base URL
	 */
	public final String getBaseUrl() {
		return this.baseUrl;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Object getService(Class<?> clazz) {
		IKreedzService service = SERVICE_CACHE.get(clazz);

		if (service != null) {
			return service;
		}

		Class<? extends IKreedzService> serviceClass = SERVICE_CLASSES.get(clazz);

		Objects.requireNonNull(serviceClass, "Unable to find a <IKreedzService> implementation");

		service = UwReflect.newInstanceOrNull(serviceClass, new Object[] {this.retrofit});

		if (service == null) {
			service = UwReflect.newInstanceOrNull(serviceClass);
		}

		Objects.requireNonNull(service, "Unable to create a <"
				+ serviceClass.getSimpleName() + "> instance");

		SERVICE_CACHE.put(clazz, service);

		return service;
	}

	/**
	 * Get this ban service.
	 *
	 * <p>Wraps {@link #getService(Class)}
	 * and casts result to {@link BanService}.
	 *
	 * @return	ban service
	 */
	public final BanService getBanService() {
		return (BanService) this.getService(IBanService.class);
	}

	/**
	 * Get this jumpstat service.
	 *
	 * <p>Wraps {@link #getService(Class)}
	 * and casts result to {@link JumpstatService}.
	 *
	 * @return	jumpstat service
	 */
	public final JumpstatService getJumpstatService() {
		return (JumpstatService) this.getService(IJumpstatService.class);
	}

	/**
	 * Get this map service.
	 *
	 * <p>Wraps {@link #getService(Class)}
	 * and casts result to {@link MapService}.
	 *
	 * @return	map service
	 */
	public final MapService getMapService() {
		return (MapService) this.getService(IMapService.class);
	}

	/**
	 * Get this mode service.
	 *
	 * <p>Wraps {@link #getService(Class)}
	 * and casts result to {@link ModeService}.
	 *
	 * @return	mode service
	 */
	public final ModeService getModeService() {
		return (ModeService) this.getService(IModeService.class);
	}

	/**
	 * Get this player rank service.
	 *
	 * <p>Wraps {@link #getService(Class)}
	 * and casts result to {@link PlayerRankService}.
	 *
	 * @return	player rank service
	 */
	public final PlayerRankService getPlayerRankService() {
		return (PlayerRankService) this.getService(IPlayerRankService.class);
	}

	/**
	 * Get this player service.
	 *
	 * <p>Wraps {@link #getService(Class)}
	 * and casts result to {@link PlayerService}.
	 *
	 * @return	player service
	 */
	public final PlayerService getPlayerService() {
		return (PlayerService) this.getService(IPlayerService.class);
	}

	/**
	 * Get this record filter service.
	 *
	 * <p>Wraps {@link #getService(Class)}
	 * and casts result to {@link RecordFilterService}.
	 *
	 * @return	record filter service
	 */
	public final RecordFilterService getRecordFilterService() {
		return (RecordFilterService) this.getService(IRecordFilterService.class);
	}

	/**
	 * Get this server service.
	 *
	 * <p>Wraps {@link #getService(Class)}
	 * and casts result to {@link ServerService}.
	 *
	 * @return	server service
	 */
	public final ServerService getServerService() {
		return (ServerService) this.getService(IServerService.class);
	}

	/**
	 * Get this record service.
	 *
	 * <p>Wraps {@link #getService(Class)}
	 * and casts result to {@link RecordService}.
	 *
	 * @return	record service
	 */
	public final RecordService getRecordService() {
		return (RecordService) this.getService(IRecordService.class);
	}

	/**
	 * Get this map image service.
	 *
	 * <p>Wraps {@link #getService(Class)}
	 * and casts result to {@link MapImageService}.
	 *
	 * @return	map image service
	 */
	public final MapImageService getMapImageService() {
		return (MapImageService) this.getService(IMapImageService.class);
	}

	/**
	 * Get this maps info service.
	 *
	 * <p>Wraps {@link #getService(Class)}
	 * and casts result to {@link MapInfoService}.
	 *
	 * @return	maps info service
	 */
	public final MapInfoService getMapInfoService() {
		return (MapInfoService) this.getService(IMapInfoService.class);
	}

	/**
	 * Get this status service.
	 *
	 * <p>Wraps {@link #getService(Class)}
	 * and casts result to {@link StatusService}.
	 *
	 * @return	status service
	 */
	public final StatusService getStatusService() {
		return (StatusService) this.getService(IStatusService.class);
	}

	/**
	 * Get this health service.
	 *
	 * <p>Wraps {@link #getService(Class)}
	 * and casts result to {@link HealthService}.
	 *
	 * @return	health service
	 */
	public final HealthService getHealthService() {
		return (HealthService) this.getService(IHealthService.class);
	}

	/**
	 * Initialize a {@link Retrofit} instance.
	 *
	 * @return	retrofit instance
	 */
	private Retrofit initRetrofit() {
		Retrofit.Builder builder = new Retrofit.Builder()
				.baseUrl(this.baseUrl)
				.client(this.initHttpClient())
				.addConverterFactory(this.initGsonConverterFactory())
				.addConverterFactory(this.initQueryConverterFactory());

		initConvertFactorySpi().forEach(builder::addConverterFactory);

		return builder.build();
	}

	/**
	 * Initialize an {@link OkHttpClient} instance.
	 *
	 * @return	{@code OkHttpClient} instance
	 */
	private OkHttpClient initHttpClient() {
		OkHttpClient.Builder builder = new OkHttpClient.Builder()
				.addInterceptor(new MethodVersionInterceptor(this.version))
				.addInterceptor(new ServiceBaseUrlInterceptor());

		initInterceptorSpi().forEach(builder::addInterceptor);

		return builder.writeTimeout(Duration.ZERO)
				.readTimeout(Duration.ZERO)
				.connectTimeout(Duration.ZERO)
				.callTimeout(Duration.ZERO)
				.build();
	}

	/**
	 * Initialize a {@link GsonConverterFactory} instance.
	 *
	 * @return	{@code GsonConverterFactory} instance
	 */
	private Converter.Factory initGsonConverterFactory() {
		GsonBuilder builder = new GsonBuilder()
				.registerTypeAdapterFactory(new UwTypeAdapterFactory());

		initTypeAdapterFactorySpi().forEach(builder::registerTypeAdapterFactory);
		initTypeAdapterSpi().forEach(builder::registerTypeAdapter);

		Object vavrTypeAdapterFactory
				= UwReflect.newInstanceOrNull(VAVR_TYPE_ADAPTER_FACTORY_CLASS);

		if (vavrTypeAdapterFactory instanceof TypeAdapterFactory) {
			builder.registerTypeAdapterFactory((TypeAdapterFactory) vavrTypeAdapterFactory);
		}

		Gson gson = builder.registerTypeAdapterFactory(new GsonNativeTypeAdapterFactory())
				.create();

		return GsonConverterFactory.create(gson);
	}

	/**
	 * Initialize a {@link QueryConverterFactory} instance.
	 *
	 * @return	{@code QueryConverterFactory} instance
	 */
	private Converter.Factory initQueryConverterFactory() {
		QueryConverterFactory factory = QueryConverterFactory.create();

		initQueryConverterSpi().forEach(factory::registerStringConverter);

		return factory;
	}

	/**
	 * Initialize a kreedz service SPI module.
	 */
	private static Map<Class<?>, Class<? extends IKreedzService>> initServiceSpi() {
		List<Class<? extends IKreedzService>> classes
				= UwBean.findSpiTypesOrNull(IKreedzService.class);

		if (classes == null) {
			return UwMap.EMPTY;
		}

		Map<Class<?>, Class<? extends IKreedzService>> result
				= new HashMap<>(classes.size());

		classes.forEach(clazz -> {
			String className = clazz.getSimpleName();

			Class<?> assignType = clazz;

			if (RetrofitServiceWrapper.class.isAssignableFrom(clazz)) {
				assignType = UwReflect.getGenericTypeOrNull(clazz.getGenericSuperclass());
			} else {
				Type interfaceType = Stream.of(clazz.getGenericInterfaces())
						.filter(type -> type instanceof ParameterizedType)
						.filter(type -> {
							Class<?> iClass = (Class<?>) ((ParameterizedType) type).getRawType();

							return IServiceWrapper.class.isAssignableFrom(iClass);
						})
						.findAny()
						.orElse(null);

				if (interfaceType != null) {
					assignType = UwReflect.getGenericTypeOrNull(interfaceType);
				}
			}

			Objects.requireNonNull(assignType, "Unable to find a <"
					+ className + "> generic type");

			result.put(assignType, clazz);
		});

		return Collections.unmodifiableMap(result);
	}

	/**
	 * Initialize a kreedz type adapter SPI module.
	 *
	 * @return 	map of kreedz type adapter instances and their associated types
	 */
	private static Map<Class<?>, Object> initTypeAdapterSpi() {
		List<Class<? extends IKreedzTypeAdapter>> classes
				= UwBean.findSpiTypesOrNull(IKreedzTypeAdapter.class);

		if (classes == null) {
			return UwMap.EMPTY;
		}

		Map<Class<?>, Object> result = new HashMap<>(classes.size());

		classes.forEach(clazz -> {
			String className = clazz.getSimpleName();

			Class<?> genericType;

			if (TypeAdapter.class.isAssignableFrom(clazz)) {
				genericType = UwReflect.getGenericTypeOrNull(clazz.getGenericSuperclass());
			} else {
				Type[] interfaceTypes = Stream.of(clazz.getGenericInterfaces())
						.filter(type -> type instanceof ParameterizedType)
						.filter(type -> {
							Class<?> iClass = (Class<?>) ((ParameterizedType) type).getRawType();

							return JsonDeserializer.class.isAssignableFrom(iClass)
									|| JsonSerializer.class.isAssignableFrom(iClass);
						})
						.toArray(Type[]::new);

				if (interfaceTypes.length == 0) {
					throw new IllegalStateException(
							"Unable to find a <TypeAdapter|JsonDeserializer|JsonSerializer>"
									+ " implementation for <" + className + "> class"
					);
				}

				genericType = UwReflect.getGenericTypeOrNull(interfaceTypes[0]);

				if (interfaceTypes.length == 2) {
					Class<?> genericType1 = UwReflect.getGenericTypeOrNull(interfaceTypes[1]);

					if (genericType != genericType1) {
						throw new IllegalStateException("Generic types of the <"
								+ className + "> doesn't match each other");
					}
				}
			}

			Objects.requireNonNull(genericType, "Unable to find a <"
					+ className + "> generic type");

			IKreedzTypeAdapter adapter = UwReflect.newInstanceOrNull(clazz);

			Objects.requireNonNull(adapter, "Unable to create a <"
					+ className + "> instance");

			result.put(genericType, adapter);
		});

		return Collections.unmodifiableMap(result);
	}

	/**
	 * Initialize a kreedz type adapter factory SPI module.
	 *
	 * @return 	list of type adapter factory instances
	 */
	private static List<TypeAdapterFactory> initTypeAdapterFactorySpi() {
		return initSimpleSpi(IKreedzTypeAdapterFactory.class, TypeAdapterFactory.class);
	}

	/**
	 * Initialize a kreedz query converter SPI module.
	 *
	 * @return	map of kreedz query converter instances and their associated types
	 */
	private static Map<Class<Object>, Converter<Object, String>> initQueryConverterSpi() {
		List<Class<? extends IKreedzQueryConverter>> classes
				= UwBean.findSpiTypesOrNull(IKreedzQueryConverter.class);

		if (classes == null) {
			return UwMap.EMPTY;
		}

		Map<Class<Object>, Converter<Object, String>> result
				= new HashMap<>(classes.size());

		classes.forEach(clazz -> {
			String className = clazz.getSimpleName();

			Type interfaceType = Stream.of(clazz.getGenericInterfaces())
					.filter(type -> type instanceof ParameterizedType)
					.filter(type -> {
						Class<?> iClass = (Class<?>) ((ParameterizedType) type).getRawType();

						return Converter.class.isAssignableFrom(iClass);
					})
					.findAny()
					.orElse(null);

			Objects.requireNonNull(interfaceType, "Unable to find a <Converter>"
					+ " implementation for <" + className + "> class"
			);

			Class<?> genericType0 = UwReflect.getGenericTypeOrNull(interfaceType, 0);
			Class<?> genericType1 = UwReflect.getGenericTypeOrNull(interfaceType, 1);

			if (genericType0 == null || genericType1 == null) {
				throw new NullPointerException("Unable to find a <" + className + "> generic types");
			}

			if (genericType1 != String.class) {
				throw new IllegalStateException("Unable to find a <Convert<?, String>>"
						+ " implementation for <" + className + "> class"
				);
			}

			IKreedzQueryConverter converter = UwReflect.newInstanceOrNull(clazz);

			Objects.requireNonNull(converter, "Unable to create a <"
					+ className + "> instance");

			result.put((Class<Object>) genericType0, (Converter<Object, String>) converter);
		});

		return Collections.unmodifiableMap(result);
	}

	/**
	 * Initialize a kreedz converter factory SPI module.
	 *
	 * @return	list of converter factory instances
	 */
	private static List<Converter.Factory> initConvertFactorySpi() {
		return initSimpleSpi(IKreedzConverterFactory.class, Converter.Factory.class);
	}

	/**
	 * Initialize a kreedz interceptor SPI module.
	 *
	 * @return	list of interceptor instances
	 */
	private static List<Interceptor> initInterceptorSpi() {
		return initSimpleSpi(IKreedzInterceptor.class, Interceptor.class);
	}

	/**
	 * Initialize a simple SPI module.
	 *
	 * @param iClass	interface class
	 * @param tClass	original class
	 * @param <T>		result type
	 * @param <U>		interface type
	 * @return			list of {@literal <T>} instances
	 */
	private static <T, U> List<T> initSimpleSpi(Class<U> iClass, Class<T> tClass) {
		List<Class<? extends U>> classes
				= UwBean.findSpiTypesOrNull(iClass);

		if (classes == null) {
			return UwList.EMPTY;
		}

		List<T> result = new ArrayList<>(classes.size());

		String tName = tClass.getSimpleName();

		classes.forEach(clazz -> {
			String className = clazz.getSimpleName();

			if (!tClass.isAssignableFrom(clazz)) {
				throw new IllegalStateException("Unable to find a <"
						+ tName + "> implementation for <" + className + "> class");
			}

			U instance = UwReflect.newInstanceOrNull(clazz);

			Objects.requireNonNull(instance, "Unable to create a <"
					+ className + "> instance");

			result.add((T) instance);
		});

		return Collections.unmodifiableList(result);
	}
}
