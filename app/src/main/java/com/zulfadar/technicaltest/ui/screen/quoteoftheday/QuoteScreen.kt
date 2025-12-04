package com.zulfadar.technicaltest.ui.screen.quoteoftheday

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zulfadar.technicaltest.data.local.model.FavoriteQuotes
import com.zulfadar.technicaltest.data.remote.response.Quote
import com.zulfadar.technicaltest.ui.common.UiState
import com.zulfadar.technicaltest.ui.screen.quoteoftheday.component.QuoteCard
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteScreen(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val viewModel: QuoteViewModel = viewModel(
        factory = QuoteViewModelFactory(context)
    )

    LaunchedEffect(Unit) {
        viewModel.addFavoriteResult.collectLatest { result ->
            when (result) {
                is AddFavoriteResult.Success -> {
                    Toast.makeText(context, "Berhasil ditambahkan ke favorit", Toast.LENGTH_SHORT).show()
                }
                is AddFavoriteResult.Error -> {
                    Toast.makeText(context, "Gagal ditambahkan ke favorit", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    val quoteState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Quote of The Day",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (quoteState) {
                is UiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is UiState.Success -> {
                    val quote = (quoteState as UiState.Success<Quote>).data
                    QuoteContent(
                        quote = quote,
                        onRefreshClick = { viewModel.getQuote() },
                        onFavoriteClick = {viewModel.addFavorite(
                            favoriteQuotes = FavoriteQuotes(
                                quote = quote.body.orEmpty(),
                                author = quote.author.orEmpty(),
                            )
                        ) }
                    )
                }

                is UiState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Terjadi kesalahan")
                    }
                }
            }
        }
    }
}

@Composable
fun QuoteContent(
    quote: Quote,
    onRefreshClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        QuoteCard(
            quote = quote.body ?: "",
            author = quote.author ?: "Unknown",
            onFavoriteButtonClick = onFavoriteClick
        )

        Button(
            onClick = onRefreshClick,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Refresh")
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//private fun QuoteScreenPrev() {
//    TechnicalTestTheme {
//        QuoteScreen()
//    }
//}