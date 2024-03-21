package tech.devscast.medifax.data

import androidx.annotation.DrawableRes
import tech.devscast.medifax.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val descripton: String
) {
    data object Fist : OnBoardingPage(
        image = R.drawable.doctor_male,
        descripton = "Trouver un grand nombre de médecins spécialistes en un seul endroit"
    )

    data object Second : OnBoardingPage(
        image = R.drawable.doctor_female,
        descripton = "Obtenez des conseils auprès de médecins de confiance !"
    )
}
