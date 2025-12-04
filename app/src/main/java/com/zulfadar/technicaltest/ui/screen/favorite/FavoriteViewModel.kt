package com.zulfadar.technicaltest.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zulfadar.technicaltest.data.local.model.FavoriteQuotes
import com.zulfadar.technicaltest.data.repository.FavoriteQuotesRepository
import com.zulfadar.technicaltest.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repository: FavoriteQuotesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<FavoriteQuotes>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<FavoriteQuotes>>> = _uiState

    init {
        getFavorites()
    }

    fun getFavorites() {
        viewModelScope.launch {
            repository.getAllFavorites().collect { list ->
                _uiState.value = UiState.Success(list)
            }
        }
    }

    fun deleteFavorite(item: FavoriteQuotes) {
        viewModelScope.launch {
            repository.deleteFavorite(item)
        }
    }
}