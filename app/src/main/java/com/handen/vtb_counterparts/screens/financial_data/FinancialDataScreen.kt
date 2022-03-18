package com.handen.vtb_counterparts.screens.financial_data

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.betterlifeapps.std.ui.composables.HSpacer
import com.betterlifeapps.std.ui.composables.UiToolbar
import com.betterlifeapps.std.ui.composables.VSpacer
import com.betterlifeapps.std.ui.theme.Grey_Light
import com.handen.vtb_counterparts.screens.counterpart.OperationType

@Composable
fun FinancialDataScreen(navController: NavHostController? = null) {
    val accounts = listOf(
        Account("BYN", "Жизнь", "245,4"),
        Account("USD", "Savings", "877,5"),
        Account("EUR", "Trip", "123,8")
    )
    val transactions = listOf(
        Transaction(OperationType.INCOME, "Жизнь", "245BYN"),
        Transaction(OperationType.EXPENSE, "Savings", "-89USD")
    )

    Column(Modifier.fillMaxSize()) {
        UiToolbar(
            text = "Контрагенты",
            showBackButton = true
        ) {
            navController?.popBackStack()
        }
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Column(Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)) {
                VSpacer(height = 8)
                Card(
                    shape = MaterialTheme.shapes.medium.copy(CornerSize(8.dp)), elevation = 4.dp
                ) {
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = "Счета", style = MaterialTheme.typography.h5)
                        VSpacer(height = 16)
                        Row(Modifier.fillMaxWidth()) {
                            for (account in accounts) {
                                HSpacer(width = 8)
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Box(
                                        Modifier
                                            .background(
                                                brush = Brush.radialGradient(
                                                    colors = listOf(
                                                        Color(0xFFf8bbd0),
                                                        Color(0xFFffbedf)
                                                    )
                                                ),
                                                shape = CircleShape
                                            )
                                            .size(48.dp)
                                            .padding(6.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = account.currency,
                                            style = MaterialTheme.typography.subtitle1.copy(color = Color.White)
                                        )
                                    }
                                    VSpacer(height = 4)
                                    Text(text = account.name, style = MaterialTheme.typography.h6)
                                    VSpacer(height = 4)
                                    Box(
                                        modifier = Modifier
                                            .background(Grey_Light, shape = RectangleShape)
                                            .padding(4.dp), contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = account.balance,
                                            style = MaterialTheme.typography.caption.copy(
                                                color = Color(
                                                    0xFFF5F5F5
                                                )
                                            )
                                        )
                                    }
                                }
                                HSpacer(width = 8)
                            }
                        }
                    }
                }
                VSpacer(height = 8)
                TransactionsBlock(transactions = transactions)
            }
        }
    }
}

@Composable
fun TransactionsBlock(transactions: List<Transaction>) {
    Card(
        shape = MaterialTheme.shapes.medium.copy(CornerSize(8.dp)), elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Транзакции", style = MaterialTheme.typography.h5)
            Column {
                for (operation in transactions) {
                    VSpacer(height = 4)
                    ItemTransaction(operation)
                    VSpacer(height = 4)
                }
            }
        }
    }
}

@Composable
fun ItemTransaction(transaction: Transaction) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        val isIncome = transaction.type == OperationType.INCOME
        val tint =
            if (isIncome) Color.Green else Color.Red
        val rotation = if (isIncome) 90f else 270f
        Icon(
            modifier = Modifier
                .size(32.dp)
                .rotate(rotation),
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = null,
            tint = tint
        )
        HSpacer(width = 8)
        Text(text = transaction.name, style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = transaction.amount, style = MaterialTheme.typography.body1)
    }
}