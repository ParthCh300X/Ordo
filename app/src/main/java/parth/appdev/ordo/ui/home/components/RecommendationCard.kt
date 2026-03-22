package parth.appdev.ordo.ui.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import parth.appdev.ordo.domain.model.MenuItem

@Composable
fun RecommendationCard(item: MenuItem) {

    Card(
        modifier = Modifier.size(width = 160.dp, height = 200.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column {

            AsyncImage(
                model = if (item.imageUrl.isNotBlank()) item.imageUrl
                else "https://via.placeholder.com/300",
                contentDescription = item.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(12.dp)) {

                Text(item.name, style = MaterialTheme.typography.titleSmall)

                Spacer(Modifier.height(4.dp))

                Text("₹${item.price}")

                Text(
                    "${item.prepTime} mins",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}