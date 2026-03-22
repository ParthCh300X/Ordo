package parth.appdev.ordo.ui.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import parth.appdev.ordo.core.navigation.NavRoutes
import parth.appdev.ordo.ui.home.HomeViewModel

@Composable
fun CartScreen(
    viewModel: HomeViewModel,
    navController: NavHostController
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Your Cart",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 🔥 EMPTY STATE (FIXED)
        if (state.cartItems.isEmpty()) {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Your cart is waiting 🛒",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Add something delicious",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

        } else {

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(state.cartItems) { cartItem ->
                    CartItemRow(
                        name = cartItem.item.name,
                        price = cartItem.item.price,
                        quantity = cartItem.quantity,
                        onIncrease = {},
                        onDecrease = {}
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    Text(
                        text = "Total: ₹${viewModel.getTotalPrice()}",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "You’ll get this in ~${viewModel.getCartWaitTime()} mins",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = {
                            viewModel.placeOrder { orderId ->
                                navController.navigate(
                                    NavRoutes.trackingRoute(orderId)
                                ) {
                                    launchSingleTop = true // 🔥 FIX
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(50)
                    ) {
                        Text("Checkout")
                    }
                }
            }
        }
    }
}