package com.handen.vtb_counterparts.screens.counterparts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.betterlifeapps.std.ui.composables.UiToolbar
import com.betterlifeapps.std.ui.composables.VSpacer
import com.handen.vtb_counterparts.screens.counterpart.CounterpartViewModel

@Composable
fun CounterpartsScreen(
    navController: NavController? = null,
    viewModel: CounterpartsViewModel = hiltViewModel()
) {
    val counterpartClickListener = { counterpart: Counterpart ->
        navController?.navigate("counterparts/id=${counterpart.unp}&name=${counterpart.name}")
    }

    Column(Modifier.fillMaxSize()) {
        UiToolbar(
            text = "Контрагенты",
            showBackButton = true
        ) {
            navController?.popBackStack()
        }
        val state by viewModel.state.collectAsState()
        when (state) {
            is CounterpartsViewModel.UiState.Loading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                        Text(text = "Loading...")
                    }
                }
            }
            is CounterpartsViewModel.UiState.Loaded -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    val items = (state as CounterpartsViewModel.UiState.Loaded).counterparts
                    items(items) {
                        CounterpartCard(counterpart = it) {
                            counterpartClickListener(it)
                        }
                    }
                }
            }
            is CounterpartsViewModel.UiState.Error -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            modifier = Modifier.size(32.dp),
                            imageVector = Icons.Outlined.Warning,
                            contentDescription = null
                        )
                        Text(text = "Check your internet connection")
                    }
                }
            }
        }
    }
}

@Composable
fun CounterpartCard(counterpart: Counterpart, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        shape = MaterialTheme.shapes.medium.copy(
            CornerSize(16.dp)
        ),
        elevation = 4.dp
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(counterpart.name, style = MaterialTheme.typography.h5)
            VSpacer(height = 8)
            Text(text = "УНП ${counterpart.unp}", style = MaterialTheme.typography.body1)
        }
    }
}

@Composable
@Preview
fun CounterpartCardPreview() {
    CounterpartCard(counterpart = Counterpart(11111116, "ОАО Рога и Копыта"), {})
}