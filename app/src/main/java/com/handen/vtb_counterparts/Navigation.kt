package com.handen.vtb_counterparts

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.handen.vtb_counterparts.screens.MainScreen
import com.handen.vtb_counterparts.screens.counterpart.CounterpartScreen
import com.handen.vtb_counterparts.screens.counterpart.counterPartViewModel
import com.handen.vtb_counterparts.screens.counterparts.CounterpartsScreen

const val START_DESTINATION = "main"

fun NavGraphBuilder.appGraph(navController: NavHostController) {
    composable("main") {
        MainScreen(navController)
    }
    composable(
        "counterparts/id={id}&name={name}",
        arguments = listOf(
            navArgument("id") { type = NavType.IntType },
            navArgument("name") { type = NavType.StringType }
        )
    ) {
        CounterpartScreen(
            navController,
            it.arguments?.getString("name") ?: "",
            viewModel = counterPartViewModel(id = it.arguments?.getInt("id") ?: 0)
        )
    }
    composable("counterparts") {
        CounterpartsScreen(navController)
    }
}