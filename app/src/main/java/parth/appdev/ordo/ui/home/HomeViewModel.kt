package parth.appdev.ordo.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import parth.appdev.ordo.domain.model.*
import parth.appdev.ordo.domain.repository.*
import javax.inject.Inject
import parth.appdev.ordo.data.remote.firebase.OrderService

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val menuRepository: MenuRepository,
    private val orderRepository: OrderRepository,
    private val orderService: OrderService
) : ViewModel() {

    private val _currentOrder = MutableStateFlow<Order?>(null)
    val currentOrder: StateFlow<Order?> = _currentOrder

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    private val _queuePosition = MutableStateFlow<Int?>(null)
    val queuePosition: StateFlow<Int?> = _queuePosition

    init {
        loadMenu()
    }

    private fun loadMenu() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            try {
                val menu = menuRepository.getMenu()

                _state.value = _state.value.copy(
                    menuItems = menu,
                    filteredItems = menu,
                    recommendations = menu.take(3),
                    isLoading = false
                )

            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = "Failed to load menu"
                )
            }
        }
    }

    fun addToCart(menuItem: MenuItem) {
        val cart = _state.value.cartItems.toMutableList()

        val existing = cart.find { it.item.id == menuItem.id }

        if (existing != null) {
            val updated = existing.copy(quantity = existing.quantity + 1)
            cart[cart.indexOf(existing)] = updated
        } else {
            cart.add(CartItem(menuItem, 1))
        }

        _state.value = _state.value.copy(cartItems = cart)
    }

    fun getTotalPrice(): Int =
        _state.value.cartItems.sumOf { it.item.price * it.quantity }

    fun getTotalItems(): Int =
        _state.value.cartItems.sumOf { it.quantity }

    fun placeOrder(onSuccess: (String) -> Unit) {
        viewModelScope.launch {

            val state = _state.value
            if (state.cartItems.isEmpty()) return@launch

            val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

            val order = Order(
                userId = userId,
                items = state.cartItems,
                totalPrice = getTotalPrice()
            )

            val orderId = orderRepository.placeOrder(order)

            onSuccess(orderId)

            _state.value = _state.value.copy(cartItems = emptyList())
        }
    }

    // 🔥 REAL TRACKING + REAL QUEUE (FIXED)
    fun startTracking(orderId: String) {

        orderService.listenToOrder(orderId) { order ->
            _currentOrder.value = order

            if (order != null) {

                // 🔥 REAL-TIME QUEUE LISTENER
                orderService.listenToQueue(order) { position ->
                    _queuePosition.value = position
                }
            }
        }
    }

    // 🔥 DYNAMIC WAIT TIME (STATE-DRIVEN)
    fun getDynamicWaitTime(): Int {

        val order = _currentOrder.value ?: return 0

        val base = order.items.sumOf {
            it.item.prepTime * it.quantity
        }

        val queue = _queuePosition.value ?: 0

        return when (order.status) {
            "PLACED" -> base + (queue * 5)
            "PREPARING" -> base / 2
            "READY" -> 0
            else -> base
        }
    }

    fun getCartWaitTime(): Int {

        val prepTime = _state.value.cartItems.sumOf {
            it.item.prepTime * it.quantity
        }

        val queue = 3 // placeholder (can be real later)

        return prepTime + (queue * 5)
    }

    fun onCategorySelected(category: String) {

        val allItems = _state.value.menuItems

        val filtered = if (category == "All") {
            allItems
        } else {
            allItems.filter { it.category == category }
        }

        _state.value = _state.value.copy(
            selectedCategory = category,
            filteredItems = filtered
        )
    }
}