package dev.valvavassori.compose.sweeper.ui.components.nineeightcss

import androidx.compose.runtime.Composable
import dev.valvassori.theme.NineEightCSS
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

@Composable
fun TitleBar(
    title: String,
    buttons: @Composable () -> Unit = {},
) {
    Div({ classes(NineEightCSS.titleBar) }) {
        Div({ classes(NineEightCSS.titleBarText) }) { Text(title) }
        Div({ classes(NineEightCSS.titleBarControls) }) { buttons() }
    }
}
