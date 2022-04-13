package com.handen.vtb_counterparts.screens.account_statement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.betterlifeapps.std.ui.UiTextField
import com.betterlifeapps.std.ui.composables.UiToolbar
import com.betterlifeapps.std.ui.composables.VSpacer
import com.betterlifeapps.std.ui.theme.Grey_Light

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AccountStatementScreen(navController: NavController) {
    Column(
        Modifier
            .fillMaxSize()
    ) {
        UiToolbar(
            text = "Выписка по счёту",
            showBackButton = true
        ) {
            navController.popBackStack()
        }
        Column(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Card(
                shape = MaterialTheme.shapes.medium.copy(CornerSize(8.dp)), elevation = 4.dp
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Расходы", style = MaterialTheme.typography.h5)
                    VSpacer(height = 4)
                    val options =
                        listOf(
                            "Все виды расходов",
                            "Развлечения",
                            "Путешествия",
                            "Продукты",
                            "ЕРИП"
                        )
                    var expanded by remember { mutableStateOf(false) }
                    var selectedOptionText by remember { mutableStateOf(options[0]) }
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = {
                            expanded = !expanded
                        }
                    ) {
                        UiTextField(
                            readOnly = true,
                            value = selectedOptionText,
                            onValueChange = { },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded = expanded
                                )
                            },
                            colors = ExposedDropdownMenuDefaults.textFieldColors(backgroundColor = Color.Transparent)
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = {
                                expanded = false
                            }
                        ) {
                            options.forEach { selectionOption ->
                                DropdownMenuItem(
                                    onClick = {
                                        selectedOptionText = selectionOption
                                        expanded = false
                                    }
                                ) {
                                    Text(text = selectionOption)
                                }
                            }
                        }
                    }
                    VSpacer(height = 8)
                    val filteredItems = when (selectedOptionText) {
                        "Все виды расходов" -> items
                        "Развлечения" -> items.filter { it.category == Category.ENTERTAINMENT }
                        "Путешествия" -> items.filter { it.category == Category.TRAVEL }
                        "Продукты" -> items.filter { it.category == Category.PRODUCTS }
                        "ЕРИП" -> items.filter { it.category == Category.PAYMENT }
                        else -> throw Exception()
                    }
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())) {
                        for (item in filteredItems) {
                            Divider(color = Grey_Light)
                            VSpacer(height = 4)
                            Column(Modifier.fillMaxWidth()) {
                                Text(text = item.details, style = MaterialTheme.typography.h6)
                                VSpacer(height = 8)
                                Row(Modifier.fillMaxWidth()) {
                                    Text(
                                        text = item.date,
                                        style = MaterialTheme.typography.body1,
                                        modifier = Modifier.weight(
                                            1f
                                        )
                                    )
                                    Text(
                                        text = item.amount,
                                        style = MaterialTheme.typography.body1,
                                        modifier = Modifier.weight(
                                            1f
                                        ), textAlign = TextAlign.End
                                    )
                                }
                            }
                            VSpacer(height = 4)
                        }
                    }
                }
            }
        }
    }
}

enum class Category {
    ENTERTAINMENT,
    TRAVEL,
    PRODUCTS,
    PAYMENT
}

data class ItemOperation(
    val date: String,
    val details: String,
    val amount: String,
    val category: Category
)

private val items = listOf(
    ItemOperation("13.04.2022", "20220411 MINSK DODOPIZZA", "-4,90 BYN", Category.ENTERTAINMENT),
    ItemOperation("13.04.2022", "20220408 MINSK STOLOVAYA", "-9.20 BYN", Category.ENTERTAINMENT),
    ItemOperation("13.04.2022", "20220412 INTERNET-BANK ERIP", "-20,48 BYN", Category.PAYMENT),
    ItemOperation("12.04.2022", "20220412 MINSK BELAVIA", "-2006,76 BYN", Category.TRAVEL),
    ItemOperation("12.04.2022", "20220412 MINSK SOSEDI", "-13,19 BYN", Category.PRODUCTS),
    ItemOperation("08.04.2022", "20220411 MINSK McDonald's", "-9,70 BYN", Category.ENTERTAINMENT),
    ItemOperation("07.04.2022", "20220408 MINSK TERRI", "-7.00 BYN", Category.ENTERTAINMENT),
    ItemOperation("07.04.2022", "20220412 INTERNET-BANK ERIP", "-20,48 BYN", Category.PAYMENT),
    ItemOperation("06.04.2022", "20220412 MINSK TEZTOUR", "-535,20 BYN", Category.TRAVEL),
    ItemOperation("05.04.2022", "20220412 MINSK SOSEDI", "-17,66 BYN", Category.PRODUCTS),
)