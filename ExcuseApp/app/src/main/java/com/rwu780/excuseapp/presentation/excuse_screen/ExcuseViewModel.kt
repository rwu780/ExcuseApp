package com.rwu780.excuseapp.presentation.excuse_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rwu780.excuseapp.data.remote.ExcuseDto
import com.rwu780.excuseapp.domain.usecases.GetExcuse
import com.rwu780.excuseapp.util.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExcuseViewModel @Inject constructor(
    val getExcuse: GetExcuse,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _excuses = MutableStateFlow<List<ExcuseDto>>(emptyList())
    val excuse : StateFlow<List<ExcuseDto>> = _excuses

    private val _uiEvent = Channel<ExcuseUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val category = savedStateHandle.get<String>("category") ?: ""
        val number = savedStateHandle.get<String>("number") ?: "1"
        fetchExcuse(category, number)
    }

    private fun fetchExcuse(category: String, number: String){
        viewModelScope.launch {
            getExcuse(category, number).collectLatest { result ->
                when(result) {
                    is ResultState.Success -> {
                        _excuses.value = result.data
                    }
                    is ResultState.Error -> {
                        sendUiEvent(ExcuseUiEvent.ShowErrorDialog(message = result.message))
                    }

                    is ResultState.Loading -> {
                        sendUiEvent(ExcuseUiEvent.ShowLoadingSnackbar)
                    }
                }
            }
        }
    }



    private fun sendUiEvent(event: ExcuseUiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}

sealed class ExcuseUiEvent {
    class ShowErrorDialog(message: String) : ExcuseUiEvent()
    object ShowLoadingSnackbar: ExcuseUiEvent()
}


