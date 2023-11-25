package com.example.pokedexapp.di

import android.content.Context
import com.example.pokedexapp.BuildConfig
import com.google.gson.Gson
import com.example.pokedexapp.data.NetworkManager
import com.example.pokedexapp.data.api.SampleApi
import com.example.pokedexapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkManager(
        @ApplicationContext application: Context,
        gsonProvider: Provider<Gson>
    ) = NetworkManager(application, gsonProvider)

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .let {
            if (BuildConfig.DEBUG) {
                it.addInterceptor(
                    HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)
                )
            } else
                it
        }
        .callTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .pingInterval(5, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideRetrofitClient(
        client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Provides
    @Singleton
    fun provideSampleApi(client: Retrofit): SampleApi =
        client.create(SampleApi::class.java)
}