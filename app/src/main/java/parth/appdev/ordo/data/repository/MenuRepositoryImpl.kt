package parth.appdev.ordo.data.repository

import parth.appdev.ordo.data.remote.firebase.MenuService
import parth.appdev.ordo.domain.model.MenuItem
import parth.appdev.ordo.domain.repository.MenuRepository

class MenuRepositoryImpl(
    private val service: MenuService
) : MenuRepository {

    override suspend fun getMenu(): List<MenuItem> {
        return service.getMenu().map {
            MenuItem(
                id = "", // optional for now
                name = it.name,
                price = it.price,
                prepTime = it.prepTime,
                category = it.category,
                imageUrl = it.imageUrl,
                isAvailable = it.isAvailable
            )
        }
    }
}