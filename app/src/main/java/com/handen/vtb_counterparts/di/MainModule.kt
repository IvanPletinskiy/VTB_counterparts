package com.handen.vtb_counterparts.di

import com.google.gson.GsonBuilder
import com.handen.vtb_counterparts.network.VtbServiceApi
import com.handen.vtb_counterparts.screens.counterparts.CounterpartsService
import com.handen.vtb_counterparts.screens.counterparts.CounterpartsServiceImpl
import com.handen.vtb_counterparts.screens.financial_data.FinancialDataService
import com.handen.vtb_counterparts.screens.financial_data.FinancialDataServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    private const val BASE_URL = "https://87b0d8ee-5acb-4e3d-a328-c12eef43b1ee.mock.pstmn.io"

    @Provides
    @Singleton
    fun provideOkhttp() = OkHttpClient.Builder()
        .apply {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(loggingInterceptor)
        }
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    @Provides
    fun provideCounterpartsApi(retrofit: Retrofit) = retrofit.create(VtbServiceApi::class.java)

    @Provides
    fun provideCounterpartsService(vtbServiceApi: VtbServiceApi): CounterpartsService = CounterpartsServiceImpl(vtbServiceApi)

    @Provides
    fun provideFinancialDataService(vtbServiceApi: VtbServiceApi): FinancialDataService = FinancialDataServiceImpl(vtbServiceApi)
}