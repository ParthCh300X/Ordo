package parth.appdev.ordo.domain.repository

import parth.appdev.ordo.domain.model.Order

interface OrderRepository {
    suspend fun placeOrder(order: Order): String
    suspend fun getUserOrders(userId: String): List<Order>
}