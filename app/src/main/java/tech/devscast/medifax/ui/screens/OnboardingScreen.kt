package tech.devscast.medifax.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Button
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import tech.devscast.medifax.data.OnBoardingPage
import tech.devscast.medifax.ui.theme.MedifaxTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen() {
    val pages = listOf(
        OnBoardingPage.Fist,
        OnBoardingPage.Second
    )
    val pagerState = rememberPagerState(0, pageCount = { pages.size })
    val pagerScrollScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(48.dp)
    ) {
        HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.Top,
        ) { position ->
            PagerScreen(pages[position], pagerState)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            PagerIndicator(state = pagerState)
            AnimatedVisibility(pagerState.currentPage < pagerState.pageCount - 1) {
                FilledIconButton(onClick = { pagerScrollScope.launch { pagerState.scrollBy(100f) } }) {
                    Icon(Icons.Default.ChevronRight, contentDescription = "Suivant")
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == pages.size - 1
        ) {
            Button(
                onClick = { },
                shape = MaterialTheme.shapes.medium,
            ) {
                Text(text = "Commencer", modifier = Modifier.padding(6.dp))
            }
        }
    }
}

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


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerScreen(page: OnBoardingPage, state: PagerState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = page.image),
            contentDescription = "Illustration",
            modifier = Modifier.size(500.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = page.descripton,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
        )
    }
}


@Preview(showSystemUi = true, showBackground = true, device = "id:pixel_8_pro")
@Composable
fun PreviewOnBoardingScreen() {
    MedifaxTheme {
        OnBoardingScreen()
    }
}