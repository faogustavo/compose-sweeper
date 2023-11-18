package dev.valvavassori.compose.sweeper.ui.components.nineeightcss

import androidx.compose.runtime.Composable
import dev.valvavassori.compose.sweeper.ui.theme.MineSweeperCSS
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.marginLeft
import org.jetbrains.compose.web.css.marginRight
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLDivElement

@Composable
fun VerticalDivider(
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
) {
    Div(attrs = {
        classes(MineSweeperCSS.higherContainer)
        style {
            height(60.percent)
            width(1.px)

            marginLeft(8.px)
            marginRight(8.px)
        }

        attrs?.invoke(this)
    })
}