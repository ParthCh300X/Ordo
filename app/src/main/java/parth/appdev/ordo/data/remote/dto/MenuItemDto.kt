package parth.appdev.ordo.data.remote.dto

data class MenuItemDto(
    val name: String = "",
    val price: Int = 0,
    val prepTime: Int = 0,
    val category: String = "",
    val imageUrl: String = "",
    val isAvailable: Boolean = true
)