package com.handen.vtb_counterparts.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.betterlifeapps.std.ui.composables.UiButton
import com.betterlifeapps.std.ui.composables.VSpacer

@Composable
fun MainScreen(navController: NavHostController? = null) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 64.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        UiButton(
            text = "Контрагенты",
            onClick = { navController?.navigate("counterparts") },
            modifier = Modifier.fillMaxWidth()
        )
        VSpacer(height = 8)
        UiButton(
            text = "Фин. Аналитика",
            onClick = { navController?.navigate("financial_data") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}