package com.handen.vtb_counterparts.screens.financial_data

import com.handen.vtb_counterparts.screens.counterpart.OperationType

data class Transaction(val type: OperationType, val name: String, val amount: String)
