package parth.appdev.ordo.ui.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import parth.appdev.ordo.domain.model.MenuItem

@Composable
fun RecommendationSection(items: List<MenuItem>) {

    if (items.isEmpty()) return

    Column {

        // 🔥 SMART TITLE (decision-oriented)
        Text(
            text = "⚡ Quick Picks (<10 mins)",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(4.dp))

        // 🔥 SUBTEXT (adds intelligence feel)
        Text(
            text = "Fastest options right now",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow {
            items(items) { item ->
                RecommendationCard(item)
                Spacer(modifier = Modifier.width(12.dp))
            }
        }
    }
}