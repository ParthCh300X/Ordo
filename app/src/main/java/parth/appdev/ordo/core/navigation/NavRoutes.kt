package parth.appdev.ordo.core.navigation

object NavRoutes {
    const val AUTH = "auth"
    const val HOME = "home"
    const val CART = "cart"
    const val TRACKING = "tracking/{orderId}"
    fun trackingRoute(orderId: String) = "tracking/$orderId"
    const val ORDERS = "orders"
}