package parth.appdev.ordo.ui.tracking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import parth.appdev.ordo.ui.home.HomeViewModel

@Composable
fun TrackingScreen(
    orderId: String,
    viewModel: HomeViewModel
) {

    val order by viewModel.currentOrder.collectAsState()
    val queuePosition by viewModel.queuePosition.collectAsState()

    LaunchedEffect(orderId) {
        viewModel.startTracking(orderId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // 🔥 PRIMARY TITLE
        Text(
            text = "Order Tracking",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (order == null) {

            // 🔥 BETTER SKELETON (CONSISTENT HEIGHTS)
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                repeat(4) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    )
                }
            }

        } else {

            val current = order!!

            // 🔹 SECONDARY INFO (de-emphasized)
            Text(
                text = "Order ID: ${current.id}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 🔥 MOST IMPORTANT (HIGHLIGHT)
            Text(
                text = "Estimated time: ~${viewModel.getDynamicWaitTime()} mins",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 🔹 CONTEXT INFO
            Text(
                text = if (queuePosition != null)
                    "You are #$queuePosition in queue"
                else
                    "Calculating queue...",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            Divider(modifier = Modifier.padding(vertical = 16.dp)) // 🔥 visual separation

            TrackingTimeline(current.status)
        }
    }
}

@Composable
fun TrackingTimeline(status: String) {

    val steps = listOf("PLACED", "PREPARING", "READY")

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        steps.forEachIndexed { index, step ->

            val isActive = steps.indexOf(status) >= index

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                // 🔥 ACTIVE STATE VISUAL
                Text(if (isActive) "🟢" else "⚪")

                Spacer(modifier = Modifier.height(4.dp))

                // 🔥 BETTER LABELS (humanized)
                val label = when (step) {
                    "PLACED" -> "Order Placed"
                    "PREPARING" -> "Being Prepared"
                    "READY" -> "Ready for Pickup"
                    else -> step
                }

                Text(
                    text = label,
                    style = MaterialTheme.typography.labelSmall,
                    color = if (isActive)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // 🔥 CONNECTOR LINE
            if (index != steps.lastIndex) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(2.dp)
                        .padding(horizontal = 6.dp)
                        .background(MaterialTheme.colorScheme.outlineVariant)
                )
            }
        }
    }
}