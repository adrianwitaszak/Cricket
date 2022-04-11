package com.adwi.cricket.feature.onboarding.ui.sections

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adwi.cricket.components.theme.CricketTheme
import com.adwi.cricket.feature.onboarding.ui.OnBoardingViewModel
import org.koin.androidx.compose.get

@Composable
internal fun CertificateSection(
    viewModel: OnBoardingViewModel = get(),
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding()
//            .wrapContentWidth()
    ) {
        Text(
            text = "How much experience do you have with coding so far?"
        )
        ExperienceItem(text = "No experience")
        ExperienceItem(text = "Some experience")
        ExperienceItem(text = "A lot of experience")
    }
}

@Composable
private fun ExperienceItem(
    modifier: Modifier = Modifier,
    text: String,
) {
    Surface(
        border = BorderStroke(1.dp, Color.LightGray),
        modifier = modifier.padding(4.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(24.dp)
        )
    }
}

@Preview(showBackground = true, name = "Light")
@Composable
private fun CertificateSectionLight() {
    CricketTheme(
        darkTheme = false,
    ) {
        Box(modifier = Modifier.padding(12.dp)) {
            CertificateSection()
        }
    }
}

@Preview(showBackground = true, name = "Dark", widthDp = 600, heightDp = 600)
@Composable
private fun CertificateSectionDark() {
    CricketTheme(
        darkTheme = true,
    ) {
        Box(modifier = Modifier.padding(12.dp)) {
            CertificateSection()
        }
    }
}
