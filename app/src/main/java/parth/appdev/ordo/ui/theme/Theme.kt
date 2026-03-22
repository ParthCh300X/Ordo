package parth.appdev.ordo.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    background = Background,
    surface = Surface,

    onPrimary = Color.White,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    error = ErrorRed
)

@Composable
fun OrdoTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}