package parth.appdev.ordo.ui.auth

data class AuthState(
    val email: String = "",
    val password: String = "",
    val isLogin: Boolean = true,
    val isLoading: Boolean = false,
    val error: String? = null
)