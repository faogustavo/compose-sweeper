package dev.valvavassori.compose.sweeper.ui.components.nineeightcss

import androidx.compose.runtime.Composable
import dev.valvassori.theme.NineEightCSS
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLDivElement

@Composable
fun TitleBar(
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    title: String,
    buttons: @Composable () -> Unit = {},
) {
    Div({
        classes(NineEightCSS.titleBar)
        attrs?.invoke(this)
    }) {
        Div({ classes(NineEightCSS.titleBarText) }) { Text(title) }
        Div({ classes(NineEightCSS.titleBarControls) }) { buttons() }
    }
}
