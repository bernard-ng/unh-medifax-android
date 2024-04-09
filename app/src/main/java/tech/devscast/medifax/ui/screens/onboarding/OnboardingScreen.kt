package tech.devscast.medifax.ui.screens.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import tech.devscast.medifax.ui.screens.onboarding.components.PagerContainer
import tech.devscast.medifax.ui.screens.onboarding.components.PagerIndicator
import tech.devscast.medifax.ui.theme.MedifaxTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    onBoardingCompleted: () -> Unit = {},
    viewModel: OnboardingViewModel = hiltViewModel()
) {
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
            PagerContainer(pages[position], pagerState)
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
                FilledIconButton(
                    onClick = {
                        pagerScrollScope.launch {
                            pagerState.scrollToPage(pagerState.currentPage + 1)
                        }
                    }
                ) {
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
                onClick = {
                    viewModel.saveOnBoardingState(true)
                    onBoardingCompleted()
                },
                shape = MaterialTheme.shapes.medium,
            ) {
                Text(
                    text = "Commencer",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(6.dp)
                )
            }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true, device = "id:pixel_8_pro")
@Composable
fun PreviewOnBoardingScreen() {
    MedifaxTheme {
        // OnBoardingScreen(onBoardingCompleted = {})
    }
}