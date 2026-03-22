package parth.appdev.ordo.ui.orders

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import parth.appdev.ordo.domain.model.Order

@Composable
fun OrdersScreen(
    viewModel: OrdersViewModel = hiltViewModel(),
    onOrderClick: (String) -> Unit
) {

    val orders by viewModel.orders.collectAsState()
    val loading by viewModel.isLoading.collectAsState()

    if (loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    if (orders.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("No orders yet 👀")
        }
        return
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(orders) { order ->
            OrderCard(
                order = order,
                onClick = onOrderClick
            )
        }
    }
}