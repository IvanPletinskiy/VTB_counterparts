package com.handen.vtb_counterparts.screens.financial_data

import com.handen.vtb_counterparts.screens.counterpart.DataPoint

data class FinancialData(
    val accounts: List<Account>,
    val transactions: List<Transaction>,
    val totalAccountBalance: AccountBalance
)

data class AccountBalance(val dataPoints: List<DataPoint>)
