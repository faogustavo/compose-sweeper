package dev.valvavassori.compose.sweeper.ui.components.nineeightcss

import androidx.compose.runtime.Composable
import dev.valvassori.theme.NineEightCSS
import dev.valvavassori.compose.sweeper.ui.behavior.DragAndDropEffect
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLDivElement

@Composable
fun Window(
    viewId: String? = null,
    draggable: Boolean = false,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    content: @Composable () -> Unit
) {
    if (draggable && viewId != null) {
        DragAndDropEffect(viewId)
    }

    Div({
        attrs?.invoke(this)
        viewId?.let(::id)
        classes(NineEightCSS.window)
    }) {
        content()
    }
}
