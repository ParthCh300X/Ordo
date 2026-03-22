package parth.appdev.ordo.ui.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.Alignment

@Composable
fun HomeTopBar(
    onOrdersClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column {
            Text(
                text = "Good Afternoon, Parth",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Hungry? Let’s fix that.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        IconButton(onClick = onOrdersClick) {
            Icon(
                imageVector = Icons.Default.List,
                contentDescription = "Orders"
            )
        }
    }
}