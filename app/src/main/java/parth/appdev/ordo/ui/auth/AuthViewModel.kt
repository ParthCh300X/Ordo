package parth.appdev.ordo.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import parth.appdev.ordo.domain.repository.AuthRepository
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state.asStateFlow()

    fun onEvent(event: AuthEvent) {
        when (event) {

            is AuthEvent.OnEmailChange -> {
                _state.value = _state.value.copy(email = event.email)
            }

            is AuthEvent.OnPasswordChange -> {
                _state.value = _state.value.copy(password = event.password)
            }

            is AuthEvent.OnToggleMode -> {
                _state.value = _state.value.copy(
                    isLogin = !_state.value.isLogin
                )
            }

            is AuthEvent.OnSubmit -> {
                authenticate()
            }
        }
    }

    private fun authenticate() {
        viewModelScope.launch {

            _state.value = _state.value.copy(
                isLoading = true,
                error = null
            )

            val result = if (_state.value.isLogin) {
                repository.login(_state.value.email, _state.value.password)
            } else {
                repository.signup(_state.value.email, _state.value.password)
            }

            result.fold(
                onSuccess = {
                    _state.value = _state.value.copy(isLoading = false)
                },
                onFailure = {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = it.message
                    )
                }
            )
        }
    }
}