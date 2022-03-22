package com.handen.vtb_counterparts.screens.financial_data

import com.handen.vtb_counterparts.network.BaseService
import com.handen.vtb_counterparts.network.VtbServiceApi
import javax.inject.Inject


interface FinancialDataService {
    suspend fun getFinancialData(): FinancialData

}

class FinancialDataServiceImpl @Inject constructor(private val api: VtbServiceApi) : BaseService(),
    FinancialDataService {

    override suspend fun getFinancialData(): FinancialData {
        return makeCall(api.getFinancialData())
    }
}