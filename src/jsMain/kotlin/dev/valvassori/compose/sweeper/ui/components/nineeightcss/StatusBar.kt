package dev.valvassori.compose.sweeper.ui.components.nineeightcss

import androidx.compose.runtime.Composable
import dev.valvassori.theme.NineEightCSS
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.ElementScope
import org.jetbrains.compose.web.dom.P
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLParagraphElement

@Composable
fun StatusBar(
    content: @Composable ElementScope<HTMLDivElement>.() -> Unit,
) {
    Div({ classes(NineEightCSS.statusBar) }) { content() }
}

@Composable
fun StatusBarField(
    content: @Composable ElementScope<HTMLParagraphElement>.() -> Unit,
) {
    P({ classes(NineEightCSS.statusBarField) }) { content() }
}
