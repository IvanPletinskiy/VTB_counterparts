package com.handen.vtb_counterparts.screens.counterparts

import com.handen.vtb_counterparts.network.BaseService
import com.handen.vtb_counterparts.network.CounterpartsServiceApi
import com.handen.vtb_counterparts.screens.counterpart.Counterpart

interface CounterpartsService {
    suspend fun getCounterparts(): List<ItemCounterpart>
    suspend fun getCounterpart(id: String): Counterpart
}

class CounterpartsServiceImpl(private val counterpartsServiceApi: CounterpartsServiceApi) :
    BaseService(), CounterpartsService {
    override suspend fun getCounterparts(): List<ItemCounterpart> {
        return makeCall(counterpartsServiceApi.getCounterparts())
    }

    override suspend fun getCounterpart(id: String): Counterpart {
        return makeCall(counterpartsServiceApi.getCounterpart(id))
    }
}