package tech.devscast.medifax.ui.screens.onboarding.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import tech.devscast.medifax.ui.screens.onboarding.OnBoardingPage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerContainer(page: OnBoardingPage, state: PagerState) {
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