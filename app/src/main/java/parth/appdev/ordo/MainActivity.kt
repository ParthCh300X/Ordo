package parth.appdev.ordo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import com.google.firebase.firestore.FirebaseFirestore
import parth.appdev.ordo.ui.theme.OrdoTheme
import dagger.hilt.android.AndroidEntryPoint
import parth.appdev.ordo.ui.auth.AuthScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = FirebaseFirestore.getInstance()

    setContent {
        OrdoTheme {
            val navController = androidx.navigation.compose.rememberNavController()
            parth.appdev.ordo.core.navigation.NavGraph(navController)
        }
    }
    }
}