package com.handen.vtb_counterparts.screens.counterpart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.betterlifeapps.std.ui.composables.CheckConnectionView
import com.betterlifeapps.std.ui.composables.LoadingView
import com.betterlifeapps.std.ui.composables.UiToolbar
import com.betterlifeapps.std.ui.theme.Grey
import com.betterlifeapps.std.ui.theme.Purple500

@Composable
fun CounterpartScreen(
    navController: NavController? = null,
    counterpartName: String,
    viewModel: CounterpartViewModel
) {
    val items = listOf(
        NavigationItem("Аналитика", "Аналитика", Icons.Filled.Info),
        NavigationItem("Надёжность", "Надёжность", Icons.Outlined.ThumbUp)
    )

    var selectedItem by remember { mutableStateOf(items[0]) }
    Column(
        Modifier
            .fillMaxSize()
    ) {
        UiToolbar(
            text = counterpartName,
            showBackButton = true
        ) {
            navController?.popBackStack()
        }
        val state by viewModel.state.collectAsState()
        when (state) {
            is CounterpartViewModel.UiState.Loading -> {
                LoadingView()
            }
            is CounterpartViewModel.UiState.Loaded -> {
                val counterpart = (state as CounterpartViewModel.UiState.Loaded).counterpart
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .weight(1f)
                ) {
                    Column(Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)) {
                        when (selectedItem.title) {
                            "Аналитика" -> CounterpartAnalyticsScreen(counterpart)
                            "Надёжность" -> CounterpartReliabilityScreen(counterpart)
                        }
                    }
                }
                BottomNavigationBar(
                    items = items,
                    selectedTitle = selectedItem.title,
                    onItemSelected = {
                        selectedItem = it
                    })
            }
            is CounterpartViewModel.UiState.Error -> {
                CheckConnectionView()
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    items: List<NavigationItem>,
    selectedTitle: String,
    onItemSelected: (NavigationItem) -> Unit
) {
    BottomNavigation(
        backgroundColor = Color.White,
        modifier = Modifier.padding(top = 2.dp),
        elevation = 16.dp
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = Grey.copy(0.6f),
                alwaysShowLabel = true,
                selected = item.title == selectedTitle,
                onClick = {
                    onItemSelected(item)
                }
            )
        }
    }
}

data class NavigationItem(val title: String, val route: String, val icon: ImageVector)