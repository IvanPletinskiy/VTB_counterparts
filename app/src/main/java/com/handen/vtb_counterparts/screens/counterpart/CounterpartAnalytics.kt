package com.handen.vtb_counterparts.screens.counterpart

import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.betterlifeapps.std.ui.UiTextField
import com.betterlifeapps.std.ui.composables.HSpacer
import com.betterlifeapps.std.ui.composables.VSpacer
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.handen.vtb_counterparts.MainActivity
import com.handen.vtb_counterparts.R
import dagger.hilt.android.EntryPointAccessors

@Composable
fun CounterpartAnalytics() {
    val operations = listOf(
        Operation(OperationType.INCOME, "111$"),
        Operation(OperationType.EXPENSE, "228$")
    )
    val documents = listOf(
        Document("Товарный знак", "№65535")
    )
    VSpacer(height = 8)
    IncomesBlock()
    VSpacer(height = 8)
    ExpensesBlock()
    VSpacer(height = 8)
    OperationsBlock(operations)
    VSpacer(height = 8)
    DocumentsBlock(documents)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun IncomesBlock() {
    Card(
        shape = MaterialTheme.shapes.medium.copy(CornerSize(8.dp)), elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Доход", style = MaterialTheme.typography.h5)
            VSpacer(height = 4)
            val options = listOf("День", "Неделя", "Месяц")
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
            Chart()
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpensesBlock() {
    Card(
        shape = MaterialTheme.shapes.medium.copy(CornerSize(8.dp)), elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Расходы", style = MaterialTheme.typography.h5)
            VSpacer(height = 4)
            val options = listOf("День", "Неделя", "Месяц")
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
            Chart()
        }
    }
}

@Composable
fun OperationsBlock(operations: List<Operation>) {
    Card(
        shape = MaterialTheme.shapes.medium.copy(CornerSize(8.dp)), elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Операции", style = MaterialTheme.typography.h5)
            Column {
                for (operation in operations) {
                    VSpacer(height = 4)
                    ItemOperation(operation)
                    VSpacer(height = 4)
                }
            }
        }
    }
}

@Composable
fun DocumentsBlock(documents: List<Document>) {
    Card(
        shape = MaterialTheme.shapes.medium.copy(CornerSize(8.dp)),
        elevation = 4.dp,
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Документы", style = MaterialTheme.typography.h5)
            Column {
                for (document in documents) {
                    VSpacer(height = 4)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        HSpacer(width = 8)
                        Text(text = document.name, style = MaterialTheme.typography.h6)
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = document.description, style = MaterialTheme.typography.body1)
                    }
                    VSpacer(height = 4)
                }
            }
        }
    }
}

@Composable
fun ItemOperation(operation: Operation) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        val isIncome = operation.type == OperationType.INCOME
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
        Text(text = if (isIncome) "Доход" else "Расход", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = operation.amount, style = MaterialTheme.typography.body1)
    }
}

@Composable
fun Chart() {
    Box(
        Modifier
            .padding(8.dp)
    ) {
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(128.dp),
            factory = { context ->
                val lineData = generateLineData(data, context)
                val chart = LineChart(context)
                chart.apply {
                    minOffset = 0f
                    setDrawGridBackground(false)
                    description.isEnabled = false
                    setTouchEnabled(false)
                    setScaleEnabled(false)
                    axisLeft.isEnabled = false
                    axisRight.isEnabled = false
                    xAxis.isEnabled = false
                    legend.isEnabled = false
                    setDrawMarkers(false)
                    setNoDataText("")
                }
                chart.data = lineData
                return@AndroidView chart
            })
    }
}

private fun generateLineData(dataPoints: List<DataPoint>, context: Context): LineData {
    val lineEntries = dataPoints.map { dataPoint ->
        Entry(
            dataPoint.timestamp.toFloat(),
            dataPoint.value.toFloat()
        )
    }
    val lineDataSet = LineDataSet(lineEntries, "Line").apply {
        color = android.graphics.Color.parseColor("#2639F2")
        setDrawCircles(false)
        lineWidth = 5.0f
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setDrawFilled(true)
            fillDrawable = context.getDrawable(R.drawable.bg_profit_loss_chart_fill)
        }
        mode = LineDataSet.Mode.CUBIC_BEZIER
        cubicIntensity = 0.15f
        highLightColor = android.graphics.Color.parseColor("#071278")
        highlightLineWidth = 2.0f
    }
    val lineData = LineData(lineDataSet)
    lineData.setDrawValues(false)
    return lineData
}

@Composable
fun counterPartViewModel(id: Int): CounterpartViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        MainActivity.ViewModelFactoryProvider::class.java
    ).counterpartViewModelFactory()

    return viewModel(factory = CounterpartViewModel.provideFactory(factory, id))
}

private val data = listOf(
    DataPoint(timestamp = 1, value = "1000.00"),
    DataPoint(timestamp = 2, value = "3000.00"),
    DataPoint(timestamp = 3, value = "5000.00"),
    DataPoint(timestamp = 4, value = "6000.00"),
    DataPoint(timestamp = 5, value = "4000.00"),
    DataPoint(timestamp = 6, value = "7000.00"),
    DataPoint(timestamp = 7, value = "8000.00"),
    DataPoint(timestamp = 8, value = "9000.00")
)

data class DataPoint(val timestamp: Long, val value: String)