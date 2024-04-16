package tech.devscast.medifax.presentation.screens.sign.vaidables

import tech.devscast.validable.BaseValidable

class LengthValidable(length: Int = 6) : BaseValidable(
    validator = { value -> value.length >= length },
    errorFor = { _ -> "Doit avoir au moins $length caractères" }
)
