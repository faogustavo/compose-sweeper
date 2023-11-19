package dev.valvavassori.compose.sweeper.ui.containers

import androidx.compose.runtime.Composable
import dev.valvavassori.compose.sweeper.ui.components.nineeightcss.CloseButton
import dev.valvavassori.compose.sweeper.ui.components.nineeightcss.TitleBar
import dev.valvavassori.compose.sweeper.ui.components.nineeightcss.Window

@Composable
fun GameMenu(
    onCloseClick: () -> Unit,
) {
    Window(
        viewId = "game-menu",
        draggable = true,
        attrs = { classes("game-menu") },
    ) {
        TitleBar(
            title = "Minesweeper Menu",
            buttons = {
                CloseButton(attrs = {
                    onClick { onCloseClick() }
                })
            }
        )
    }
}