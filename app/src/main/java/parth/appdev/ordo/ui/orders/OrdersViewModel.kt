package parth.appdev.ordo.ui.orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import parth.appdev.ordo.domain.model.Order
import parth.appdev.ordo.domain.repository.OrderRepository
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val repository: OrderRepository
) : ViewModel() {

    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders: StateFlow<List<Order>> = _orders.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadOrders()
    }

    private fun loadOrders() {
        viewModelScope.launch {

            val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

            try {
                val result = repository.getUserOrders(userId)
                _orders.value = result.sortedByDescending { it.timestamp }
            } catch (e: Exception) {
                _orders.value = emptyList()
            }

            _isLoading.value = false
        }
    }
}