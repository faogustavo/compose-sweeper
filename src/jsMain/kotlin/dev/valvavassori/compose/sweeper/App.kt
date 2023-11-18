package dev.valvavassori.compose.sweeper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import dev.valvavassori.compose.sweeper.core.MineSweeperBoard
import dev.valvavassori.compose.sweeper.ui.containers.GameWindow
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun App(game: MineSweeperBoard) {
    GameWindow(game)
}
