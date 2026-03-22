package parth.appdev.ordo.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import parth.appdev.ordo.ui.home.components.*

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onCartClick: () -> Unit,
    onOrdersClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    if (state.isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                repeat(6) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {}
                }
            }
        }
        return
    }

    state.error?.let {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(it, color = MaterialTheme.colorScheme.error)
        }
        return
    }

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 12.dp, bottom = 80.dp) // space for cart bar
        ) {

            HomeTopBar(
                onOrdersClick = onOrdersClick
            )
            Spacer(Modifier.height(20.dp))

            RecommendationSection(state.recommendations)

            Spacer(Modifier.height(20.dp))

            CategoryChips(
                categories = state.categories,
                selectedCategory = state.selectedCategory,
                onCategorySelected = viewModel::onCategorySelected
            )

            Spacer(Modifier.height(16.dp))

            MenuGrid(
                items = state.filteredItems,
                onAddClick = viewModel::addToCart
            )
        }

        // 🔥 PREMIUM CART BAR
        if (state.cartItems.isNotEmpty()) {

            Card(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp) // 🔥 works everywhere
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onCartClick() } // 🔥 correct way
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column {
                        Text(
                            text = "View Cart",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Text(
                            text = "${viewModel.getTotalItems()} items",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Text(
                        text = "₹${viewModel.getTotalPrice()}",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}