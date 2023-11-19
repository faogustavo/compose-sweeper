package dev.valvavassori.compose.sweeper.ui.effects

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import kotlinx.browser.document
import org.w3c.dom.events.KeyboardEvent

@Composable
fun KeyPressEffect(keyCode: Int, callback: (KeyboardEvent) -> Unit) {
    SideEffect {
        document.addEventListener(
            type = "keydown",
            callback = {
                // F1 press
                if (it is KeyboardEvent && it.keyCode == keyCode) {
                    callback(it)
                }
            },
        )
    }
}