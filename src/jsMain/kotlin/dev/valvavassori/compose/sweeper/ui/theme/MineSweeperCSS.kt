package dev.valvavassori.compose.sweeper.ui.theme

import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.marginTop
import org.jetbrains.compose.web.css.minHeight
import org.jetbrains.compose.web.css.minWidth
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width

object MineSweeperCSS: StyleSheet() {
    const val lowerContainer = "lowerContainer"
    const val higherContainer = "higherContainer"

    init {
        "table" style {
            property("border-collapse", "collapse")
            property("border-spacing", "0")
        }

        "td" style {
            padding(0.px)
        }
    }

    val gameBar by style {
        height(56.px)
        display(DisplayStyle.Flex)
        alignItems(AlignItems.Center)
        justifyContent(JustifyContent.SpaceAround)

        "button" style {
            height(36.px)
            width(36.px)
            minHeight(36.px)
            minWidth(36.px)
            fontSize(28.px)
            padding(0.px)
        }

        "span" style {
            fontSize(28.px)
            fontWeight("semi-bold")
        }
    }

    val gameBoard by style {
        marginTop(8.px)
        padding(2.px)
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

        self + className("bt-1") style {
            color(Color.blue)
        }

        self + className("bt-2") style {
            color(Color.darkgreen)
        }

        self + className("bt-3") style {
            color(Color.red)
        }

        self + className("bt-4") style {
            color(Color.darkblue)
        }

        self + className("bt-5") style {
            color(Color.darkred)
        }

        self + className("bt-6") style {
            color(Color.cyan)
        }

        self + className("bt-7") style {
            color(Color.black)
        }

        self + className("bt-8") style {
            color(Color.gray)
        }

        self + className("bt-💣") style {
            backgroundColor(Color("#c52f2f"))
        }
    }
}