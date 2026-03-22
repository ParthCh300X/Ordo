package parth.appdev.ordo.domain.model

data class MenuItem(
    val id: String = "",
    val name: String = "",
    val price: Int = 0,
    val prepTime: Int = 0,
    val category: String = "",
    val imageUrl: String = "",
    val isAvailable: Boolean = true
)