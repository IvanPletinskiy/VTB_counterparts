package com.handen.vtb_counterparts.screens.counterpart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.unit.dp
import com.betterlifeapps.std.ui.composables.VSpacer

@Composable
fun CounterpartReliabilityScreen(counterpart: Counterpart) {
    VSpacer(height = 8)
    StatusBlock(counterpart.statusColor)
    VSpacer(height = 8)
    CourtsBlock(counterpart.courts)
    VSpacer(height = 8)
    BankruptcyBlock(counterpart.bankruptcy)
    VSpacer(height = 8)
    LiquidationBlock(counterpart.liquidation)
    VSpacer(height = 8)
    TendersBlock(counterpart.tenders)
    VSpacer(height = 8)
    LicensesBlock(counterpart.licenses)
}

@Composable
fun StatusBlock(statusColor: String) {
    Card(
        shape = MaterialTheme.shapes.medium.copy(CornerSize(8.dp)), elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Статус", style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = ColorPainter(Color(android.graphics.Color.parseColor(statusColor))),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(24.dp)
            )
        }
    }
}

@Composable
fun CourtsBlock(courts: List<Court>) {
    Card(
        shape = MaterialTheme.shapes.medium.copy(CornerSize(8.dp)), elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Суды", style = MaterialTheme.typography.h5)
            Column {
                for (court in courts) {
                    VSpacer(height = 4)
                    Column(Modifier.fillMaxWidth()) {
                        Text(text = court.name, style = MaterialTheme.typography.h6)
                        VSpacer(height = 8)
                        Row(Modifier.fillMaxWidth()) {
                            Text(
                                text = court.role,
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier.weight(
                                    1f
                                )
                            )
                            Text(
                                text = court.date,
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier.weight(
                                    1f
                                )
                            )
                        }
                    }
                    VSpacer(height = 4)
                }
            }
        }
    }
}

@Composable
fun BankruptcyBlock(bankruptcy: Bankruptcy) {
    Card(
        shape = MaterialTheme.shapes.medium.copy(CornerSize(8.dp)), elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Банкротство", style = MaterialTheme.typography.h5)
            VSpacer(height = 8)
            Text(text = bankruptcy.text, style = MaterialTheme.typography.body1)
        }
    }
}

@Composable
fun LiquidationBlock(liquidation: Liquidation) {
    Card(
        shape = MaterialTheme.shapes.medium.copy(CornerSize(8.dp)), elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Ликвидация", style = MaterialTheme.typography.h5)
            VSpacer(height = 8)
            Text(text = liquidation.text, style = MaterialTheme.typography.body1)
        }
    }
}

@Composable
fun TendersBlock(tenders: List<Tender>) {
    Card(
        shape = MaterialTheme.shapes.medium.copy(CornerSize(8.dp)), elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Участие в тендерах", style = MaterialTheme.typography.h5)
            Column {
                for (tender in tenders) {
                    VSpacer(height = 4)
                    Column(Modifier.fillMaxWidth()) {
                        Text(text = tender.name, style = MaterialTheme.typography.h6)
                        VSpacer(height = 8)
                        Row(Modifier.fillMaxWidth()) {
                            Text(
                                text = tender.number,
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier.weight(
                                    1f
                                )
                            )
                            Text(
                                text = tender.date,
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier.weight(
                                    1f
                                )
                            )
                        }
                    }
                    VSpacer(height = 4)
                }
            }
        }
    }
}

@Composable
fun LicensesBlock(licenses: List<License>) {
    Card(
        shape = MaterialTheme.shapes.medium.copy(CornerSize(8.dp)), elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Лицензии", style = MaterialTheme.typography.h5)
            Column {
                for (license in licenses) {
                    VSpacer(height = 4)
                    Column(Modifier.fillMaxWidth()) {
                        Row(Modifier.fillMaxWidth()) {
                            Text(
                                text = license.name,
                                style = MaterialTheme.typography.h6,
                                modifier = Modifier.weight(
                                    1f
                                )
                            )
                            Text(
                                text = license.number,
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier.weight(
                                    1f
                                )
                            )
                        }
                        VSpacer(height = 8)
                        Text(text = license.date, style = MaterialTheme.typography.body1)
                    }
                    VSpacer(height = 4)
                }
            }
        }
    }
}