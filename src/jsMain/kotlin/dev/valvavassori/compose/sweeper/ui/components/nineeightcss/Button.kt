package dev.valvavassori.compose.sweeper.ui.components.nineeightcss

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.Button
import org.w3c.dom.HTMLButtonElement

@Composable
fun CloseButton(
    attrs: AttrBuilderContext<HTMLButtonElement>? = null
) = PreDefinedButton("Close", attrs)

@Composable
fun MaximizeButton(
    attrs: AttrBuilderContext<HTMLButtonElement>? = null
) = PreDefinedButton("Maximize", attrs)

@Composable
fun RestoreButton(
    attrs: AttrBuilderContext<HTMLButtonElement>? = null
) = PreDefinedButton("Restore", attrs)

@Composable
fun MinimizeButton(
    attrs: AttrBuilderContext<HTMLButtonElement>? = null
) = PreDefinedButton("Minimize", attrs)

@Composable
fun HelpButton(
    attrs: AttrBuilderContext<HTMLButtonElement>? = null
) = PreDefinedButton("Help", attrs)

@Composable
private fun PreDefinedButton(
    name: String,
    attrs: AttrBuilderContext<HTMLButtonElement>?,
) {
    Button({
        attrs?.invoke(this)
        attr("aria-label", name)
    })
}
