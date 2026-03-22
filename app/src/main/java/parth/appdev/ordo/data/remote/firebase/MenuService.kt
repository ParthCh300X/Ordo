package parth.appdev.ordo.data.remote.firebase

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import parth.appdev.ordo.data.remote.dto.MenuItemDto

class MenuService(
    private val firestore: FirebaseFirestore
) {

    suspend fun getMenu(): List<MenuItemDto> {
        return try {
            val snapshot = firestore.collection("menu").get().await()
            snapshot.documents.mapNotNull {
                it.toObject(MenuItemDto::class.java)
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}