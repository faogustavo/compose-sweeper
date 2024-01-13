package dev.valvassori.compose.sweeper.ui.components.nineeightcss

import androidx.compose.runtime.Composable
import dev.valvassori.theme.NineEightCSS
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLDivElement

@Composable
fun Container(
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    contentPadding: Boolean = false,
    windowBody: Boolean = true,
    content: @Composable () -> Unit,
) {
    Div({
        attrs?.invoke(this)
        classes(
            setOfNotNull(
                NineEightCSS.windowBody.takeIf { windowBody },
                "padded".takeIf { contentPadding },
            )
        )
    }) {
        content()
    }
}
