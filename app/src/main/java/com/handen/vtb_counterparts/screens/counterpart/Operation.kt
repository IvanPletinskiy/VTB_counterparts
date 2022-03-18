package com.handen.vtb_counterparts.screens.counterpart

data class Operation(val type: OperationType, val amount: String)

enum class OperationType {
    INCOME,
    EXPENSE
}
