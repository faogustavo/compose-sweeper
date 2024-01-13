package dev.valvassori.compose.sweeper.ui.ext

import org.jetbrains.compose.web.css.*

fun StyleScope.horizontalPadding(padding: CSSNumeric) {
    paddingLeft(padding)
    paddingRight(padding)
}

fun StyleScope.verticalPadding(padding: CSSNumeric) {
    paddingTop(padding)
    paddingBottom(padding)
}
