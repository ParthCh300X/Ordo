package parth.appdev.ordo.domain.repository

import parth.appdev.ordo.domain.model.MenuItem

interface MenuRepository {
    suspend fun getMenu(): List<MenuItem>
}