package parth.appdev.ordo.domain.model

data class Order(
    val id: String = "",
    val userId: String = "",   // 🔥 ADD THIS
    val items: List<CartItem> = emptyList(),
    val totalPrice: Int = 0,
    val status: String = "PLACED",
    val timestamp: Long = System.currentTimeMillis()
)