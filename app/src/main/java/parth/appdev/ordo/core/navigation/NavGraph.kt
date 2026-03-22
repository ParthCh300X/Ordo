package parth.appdev.ordo.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import parth.appdev.ordo.ui.auth.AuthScreen
import parth.appdev.ordo.ui.cart.CartScreen
import parth.appdev.ordo.ui.home.HomeScreen
import parth.appdev.ordo.ui.home.HomeViewModel
import parth.appdev.ordo.ui.orders.OrdersScreen
import parth.appdev.ordo.ui.tracking.TrackingScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = NavRoutes.AUTH
    ) {

        composable(NavRoutes.AUTH) {
            AuthScreen(
                onAuthSuccess = {
                    navController.navigate(NavRoutes.HOME) {
                        popUpTo(NavRoutes.AUTH) { inclusive = true }
                    }
                }
            )
        }

        composable(NavRoutes.HOME) { backStackEntry ->

            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(NavRoutes.HOME)
            }

            val viewModel: HomeViewModel = hiltViewModel(parentEntry)

            HomeScreen(
                viewModel = viewModel,
                onCartClick = {
                    navController.navigate(NavRoutes.CART) {
                        launchSingleTop = true
                    }
                },
                onOrdersClick = {
                    navController.navigate(NavRoutes.ORDERS) {
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(NavRoutes.CART) { backStackEntry ->

            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(NavRoutes.HOME)
            }

            val viewModel: HomeViewModel = hiltViewModel(parentEntry)

            CartScreen(
                viewModel = viewModel,
                navController = navController
            )
        }
        composable(NavRoutes.TRACKING) { backStackEntry ->

            val orderId = backStackEntry.arguments?.getString("orderId") ?: ""

            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(NavRoutes.HOME)
            }

            val viewModel: HomeViewModel = hiltViewModel(parentEntry)

            TrackingScreen(orderId = orderId, viewModel = viewModel)
        }
        composable(NavRoutes.ORDERS) {
            OrdersScreen(
                onOrderClick = { orderId ->
                    navController.navigate(NavRoutes.trackingRoute(orderId)) {
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}