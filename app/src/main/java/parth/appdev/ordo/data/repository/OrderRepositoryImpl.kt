package parth.appdev.ordo.data.repository

import parth.appdev.ordo.data.remote.firebase.OrderService
import parth.appdev.ordo.domain.model.Order
import parth.appdev.ordo.domain.repository.OrderRepository

class OrderRepositoryImpl(
    private val service: OrderService
) : OrderRepository {

    override suspend fun placeOrder(order: Order): String {
        return service.placeOrder(order)
    }

    override suspend fun getUserOrders(userId: String): List<Order> {
        return service.getUserOrders(userId)
    }
}