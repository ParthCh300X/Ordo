package parth.appdev.ordo.data.remote.firebase

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import parth.appdev.ordo.domain.model.Order

class OrderService(
    private val firestore: FirebaseFirestore
) {

    // 🔥 PLACE ORDER
    suspend fun placeOrder(order: Order): String {

        val docRef = firestore.collection("orders").document()

        val orderWithId = order.copy(
            id = docRef.id,
            timestamp = System.currentTimeMillis() // ensure timestamp exists
        )

        docRef.set(orderWithId).await()

        return docRef.id
    }

    // 🔥 LISTEN TO SINGLE ORDER (TRACKING)
    fun listenToOrder(
        orderId: String,
        onUpdate: (Order?) -> Unit
    ) {
        firestore.collection("orders")
            .document(orderId)
            .addSnapshotListener { snapshot, _ ->
                val order = snapshot?.toObject(Order::class.java)
                onUpdate(order)
            }
    }

    // 🔥 REAL-TIME QUEUE LISTENER (CORE FEATURE)
    fun listenToQueue(
        currentOrder: Order,
        onUpdate: (Int) -> Unit
    ) {
        firestore.collection("orders")
            .whereNotEqualTo("status", "READY")
            .addSnapshotListener { snapshot, _ ->

                val orders = snapshot?.documents?.mapNotNull {
                    it.toObject(Order::class.java)
                } ?: emptyList()

                val position = orders.count {
                    it.timestamp < currentOrder.timestamp
                } + 1

                onUpdate(position)
            }
    }

    // 🔥 GET USER ORDERS (HISTORY)
    suspend fun getUserOrders(userId: String): List<Order> {

        val snapshot = firestore.collection("orders")
            .whereEqualTo("userId", userId)
            .get()
            .await()

        return snapshot.documents.mapNotNull { doc ->
            doc.toObject(Order::class.java)?.copy(id = doc.id)
        }
    }
}