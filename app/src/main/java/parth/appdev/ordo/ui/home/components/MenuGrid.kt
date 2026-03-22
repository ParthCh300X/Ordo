package parth.appdev.ordo.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.grid.items
import parth.appdev.ordo.domain.model.MenuItem

@Composable
fun MenuGrid(
    items: List<MenuItem>,
    onAddClick: (MenuItem) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items) { item ->
            MenuItemCard(item, onAddClick)
        }
    }
}