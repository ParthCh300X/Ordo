package parth.appdev.ordo.ui.cart

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CartItemRow(
    name: String,
    price: Int,
    quantity: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                Text(name)
                Text("₹$price")
            }

            Row {
                IconButton(onClick = onDecrease) {
                    Text("-")
                }

                Text("$quantity", modifier = Modifier.padding(8.dp))

                IconButton(onClick = onIncrease) {
                    Text("+")
                }
            }
        }
    }
}