package parth.appdev.ordo.ui.home

import parth.appdev.ordo.domain.model.CartItem
import parth.appdev.ordo.domain.model.MenuItem

data class HomeState(
    val menuItems: List<MenuItem> = emptyList(),
    val filteredItems: List<MenuItem> = emptyList(), // 🔥 important
    val recommendations: List<MenuItem> = emptyList(),
    val categories: List<String> = listOf("All", "Meals", "Snacks", "Drinks"),
    val selectedCategory: String = "All",
    val isLoading: Boolean = true,
    val error: String? = null,
    val cartItems: List<CartItem> = emptyList()
)