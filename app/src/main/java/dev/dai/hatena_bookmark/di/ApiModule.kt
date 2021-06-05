package dev.dai.hatena_bookmark.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.dai.hatena_bookmark.BuildConfig
import dev.dai.hatena_bookmark.api.HatenaBookmarkApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
  private const val BASE_URL = "https://b.hatena.ne.jp/"
  private const val TIME_OUT = 60L

  @Singleton
  @Provides
  fun provideHatenaBookmarkApi(): HatenaBookmarkApi {
    return Retrofit.Builder()
      .baseUrl(BASE_URL)
      .client(provideOkHttpClient())
      .addConverterFactory(SimpleXmlConverterFactory.create())
      .build()
      .create(HatenaBookmarkApi::class.java)
  }

  private fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder()
      .addInterceptor(provideLogging())
      .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
      .readTimeout(TIME_OUT, TimeUnit.SECONDS)
      .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
      .build()
  }

  private fun provideLogging() = HttpLoggingInterceptor().apply {
    if (BuildConfig.DEBUG) {
      level = HttpLoggingInterceptor.Level.BODY
    }
  }
}
