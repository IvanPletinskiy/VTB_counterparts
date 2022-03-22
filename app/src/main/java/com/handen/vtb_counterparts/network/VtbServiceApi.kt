package com.handen.vtb_counterparts.network

import com.handen.vtb_counterparts.screens.counterpart.Counterpart
import com.handen.vtb_counterparts.screens.counterparts.ItemCounterpart
import com.handen.vtb_counterparts.screens.financial_data.FinancialData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface VtbServiceApi {
    @GET("/counterparts")
    suspend fun getCounterparts(): Response<List<ItemCounterpart>>

    @GET("/counterparts/{id}")
    suspend fun getCounterpart(@Path("id") id: String): Response<Counterpart>

    @GET("/financial_data")
    suspend fun getFinancialData(): Response<FinancialData>
}