package parth.appdev.ordo.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import parth.appdev.ordo.domain.model.MenuItem

@Composable
fun MenuItemCard(
    item: MenuItem,
    onAddClick: (MenuItem) -> Unit
) {

    var added by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {

        Column {

            // 🔥 IMAGE WITH OVERLAY BADGE
            Box {

                AsyncImage(
                    model = if (item.imageUrl.isNotBlank()) item.imageUrl
                    else "https://picsum.photos/300",
                    contentDescription = item.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp),
                    contentScale = ContentScale.Crop
                )

                // 🔥 PREP TIME BADGE (TOP RIGHT)
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(
                            MaterialTheme.colorScheme.surface.copy(alpha = 0.9f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "${item.prepTime} min",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }

            Column(
                modifier = Modifier.padding(12.dp)
            ) {

                // 🔥 ITEM NAME (PRIMARY)
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(4.dp))

                // 🔥 PRICE (SECONDARY BUT IMPORTANT)
                Text(
                    text = "₹${item.price}",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(10.dp))

                // 🔥 ADD BUTTON (INTERACTIVE FEEDBACK)
                Button(
                    onClick = {
                        onAddClick(item)
                        added = true
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = if (added) "Added ✓" else "Add"
                    )
                }
            }
        }
    }
}