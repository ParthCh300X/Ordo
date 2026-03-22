package parth.appdev.ordo.domain.model

data class CartItem(
    val item: MenuItem = MenuItem(),
    val quantity: Int = 0
)