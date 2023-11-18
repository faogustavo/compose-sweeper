package dev.valvavassori.compose.sweeper

import dev.valvavassori.compose.sweeper.App
import org.jetbrains.compose.web.renderComposable

fun main() {
    renderComposable(rootElementId = "root") {
        App()
    }
}