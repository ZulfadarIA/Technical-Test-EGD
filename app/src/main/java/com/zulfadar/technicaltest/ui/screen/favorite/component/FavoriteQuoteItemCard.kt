package com.zulfadar.technicaltest.ui.screen.favorite.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zulfadar.technicaltest.ui.theme.TechnicalTestTheme

@Composable
fun FavoriteQuoteItemCard(
    modifier: Modifier,
    quote: String,
    author: String,
    onDelete: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = quote,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
            )

            Text(
                text = "- ${author.ifEmpty { "Unknown" }} -",
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        IconButton(
            modifier = Modifier.padding(start = 8.dp),
            onClick = onDelete
        ) {
            Icon(
                Icons.Default.Delete,
                contentDescription = "Delete",
                tint = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Preview
@Composable
private fun FavoriteQuoteIttemCardPrev() {
    TechnicalTestTheme {
        FavoriteQuoteItemCard(
            quote = "asdasdadsasdadsad asdasd asd asdasdasfas asfasfasfa asfasfasfa asfasfa fasfasf asfasf",
            author = "zulfadar",
            onDelete = {},
            modifier = Modifier
        )
    }
}