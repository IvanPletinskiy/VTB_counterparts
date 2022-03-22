package com.handen.vtb_counterparts.network

import com.handen.vtb_counterparts.screens.counterpart.Counterpart
import com.handen.vtb_counterparts.screens.counterparts.ItemCounterpart
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CounterpartsServiceApi {
    @GET("/counterparts")
    suspend fun getCounterparts(): Response<List<ItemCounterpart>>

    @GET("/counterparts/{id}")
    suspend fun getCounterpart(@Path("id") id: String): Response<Counterpart>
}