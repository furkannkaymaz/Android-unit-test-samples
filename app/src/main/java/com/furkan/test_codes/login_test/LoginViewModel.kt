package com.furkan.test_codes.login_test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LoginViewModel(
    private val getLoginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<Boolean> = MutableStateFlow(value = false)

    fun login(username : String, password: String, confirmPassword : String) : StateFlow<Boolean> {
        viewModelScope.launch{
            getLoginUseCase.invoke(username,password,confirmPassword).collectLatest {
                _uiState.emit(it)
            }
        }
        return _uiState
    }
}