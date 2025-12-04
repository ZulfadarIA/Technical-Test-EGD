package com.zulfadar.technicaltest.ui.screen.quoteoftheday

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zulfadar.technicaltest.data.local.model.FavoriteQuotes
import com.zulfadar.technicaltest.data.remote.response.Quote
import com.zulfadar.technicaltest.data.repository.FavoriteQuotesRepository
import com.zulfadar.technicaltest.data.repository.QuoteRepository
import com.zulfadar.technicaltest.ui.common.UiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class AddFavoriteResult {
    object Success : AddFavoriteResult()
    data class Error(val message: String) : AddFavoriteResult()
}

class QuoteViewModel(
    private val repository: QuoteRepository,
    private val favoriteRepository: FavoriteQuotesRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<UiState<Quote>>(UiState.Loading)
    val uiState: StateFlow<UiState<Quote>> = _uiState

    private val _addFavoriteResult = MutableSharedFlow<AddFavoriteResult>()
    val addFavoriteResult: SharedFlow<AddFavoriteResult> = _addFavoriteResult

    init {
        getQuote()
    }

    fun getQuote() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val response = repository.getQuoteOfTheDay()
                val quote = response.quote
                if (quote != null) {
                    _uiState.emit(UiState.Success(quote))
                } else {
                    _uiState.emit(UiState.Error("Quote is null"))
                }
            } catch (e: Exception) {
                _uiState.emit(UiState.Error(e.localizedMessage ?: "Unknown error"))
            }
        }
    }

    fun addFavorite(favoriteQuotes: FavoriteQuotes) {
        viewModelScope.launch {
            try {
                favoriteRepository.addFavorite(favoriteQuotes)
                _addFavoriteResult.emit(AddFavoriteResult.Success)
            } catch (e: Exception) {
                _addFavoriteResult.emit(AddFavoriteResult.Error(e.localizedMessage ?: "Failed to add favorite"))
            }
        }
    }
}