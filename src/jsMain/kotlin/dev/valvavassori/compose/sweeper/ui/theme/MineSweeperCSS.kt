package dev.valvavassori.compose.sweeper.ui.theme

import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.minHeight
import org.jetbrains.compose.web.css.minWidth
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width

object MineSweeperCSS: StyleSheet() {
    init {
        "table" style {
            property("border-collapse", "collapse")
            property("border-spacing", "0")
        }

        "td" style {
            padding(0.px)
        }
    }

    val gameButton by style {
        height(28.px)
        width(28.px)
        minHeight(28.px)
        minWidth(28.px)
        padding(0.px, 6.px, 0.px, 6.px)
        color(Color.black)

        self + className("active") style {
            fontWeight("bold")
        }
    }
}