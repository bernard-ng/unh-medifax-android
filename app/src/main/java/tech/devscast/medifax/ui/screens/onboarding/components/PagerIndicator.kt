package tech.devscast.medifax.ui.screens.onboarding.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerIndicator(state: PagerState) {
    Row {
        repeat(state.pageCount) { i ->
            val color =
                if (state.currentPage == i) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer
            Box(
                modifier = Modifier
                    .padding(3.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(color)
                    .width(20.dp)
                    .height(6.dp),
            )
        }
    }
}
