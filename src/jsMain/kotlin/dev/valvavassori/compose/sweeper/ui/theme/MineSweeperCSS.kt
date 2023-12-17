package dev.valvavassori.compose.sweeper.ui.theme

import dev.valvavassori.compose.sweeper.ui.consts.ImageConstants
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.AlignSelf
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.alignSelf
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.backgroundImage
import org.jetbrains.compose.web.css.backgroundPosition
import org.jetbrains.compose.web.css.backgroundRepeat
import org.jetbrains.compose.web.css.backgroundSize
import org.jetbrains.compose.web.css.bottom
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.justifyContent
import org.jetbrains.compose.web.css.left
import org.jetbrains.compose.web.css.marginLeft
import org.jetbrains.compose.web.css.marginRight
import org.jetbrains.compose.web.css.marginTop
import org.jetbrains.compose.web.css.minHeight
import org.jetbrains.compose.web.css.minWidth
import org.jetbrains.compose.web.css.overflow
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width

object MineSweeperCSS: StyleSheet() {
    init {
        "html, body" style {
            height(98.percent)
            width(100.percent)
            overflow("hidden")
        }

        "body" style {
            backgroundImage("url(${ImageConstants.background})")
            backgroundSize("cover")
            backgroundRepeat("no-repeat")
            backgroundPosition("center")
        }

        "table" style {
            property("border-collapse", "collapse")
            property("border-spacing", "0")
        }

        "td" style {
            padding(0.px)
        }
    }

    val higherContainer by style {
        property("box-shadow", "inset -1px -1px #0a0a0a, inset 1px 1px #dfdfdf, inset -2px -2px grey, inset 2px 2px #ffffff")
    }

    val lowerContainer by style {
        property("box-shadow", "inset -1px -1px #ffffff, inset 1px 1px #0a0a0a, inset -2px -2px #dfdfdf, inset 2px 2px #808080")
    }

    val taskBar by style {
        position(Position.Absolute)
        display(DisplayStyle.Flex)
        alignItems(AlignItems.Center)
        justifyContent(JustifyContent.FlexStart)

        left(0.px)
        bottom(0.px)

        width(100.percent)
        height(32.px)

        backgroundColor(Color.silver)
        property("z-index", "999")

        "#startButton" style {
            fontWeight("bold")
            marginLeft(4.px)
        }

        "button" style {
            display(DisplayStyle.Flex)
            alignItems(AlignItems.Center)
            justifyContent(JustifyContent.FlexStart)

            "img" style {
                width(20.px)
                height(20.px)
                marginRight(4.px)
            }
        }

        "button.task" style {
            width(200.px)
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
            color(Color.black)
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
            property("box-shadow", "inset -1px -1px #ffffff, inset 1px 1px #0a0a0a, inset -2px -2px #dfdfdf, inset 2px 2px #808080")
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

    val buttonGroup by style {
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.Row)
        justifyContent(JustifyContent.End)
        padding(0.px, 10.px, 6.px)
    }
}
