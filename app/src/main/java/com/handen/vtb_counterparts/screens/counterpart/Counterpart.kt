package com.handen.vtb_counterparts.screens.counterpart

import com.google.gson.annotations.SerializedName

data class Counterpart(
    val incomes: Incomes,
    val expenses: Expenses,
    val operations: List<Operation>,
    val documents: List<Document>,
    val statusColor: String,
    val courts: List<Court>,
    val bankruptcy: Bankruptcy,
    val liquidation: Liquidation,
    val tenders: List<Tender>,
    val licenses: List<License>
)

data class Incomes(val week: List<DataPoint>, val month: List<DataPoint>, val year: List<DataPoint>)
data class Expenses(
    val week: List<DataPoint>,
    val month: List<DataPoint>,
    val year: List<DataPoint>
)

data class Operation(val type: OperationType, val amount: String)

enum class OperationType {
    @SerializedName("INCOME")
    INCOME,

    @SerializedName("EXPENSE")
    EXPENSE
}