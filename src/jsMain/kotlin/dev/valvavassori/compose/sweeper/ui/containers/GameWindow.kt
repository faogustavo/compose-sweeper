package dev.valvavassori.compose.sweeper.ui.containers

import androidx.compose.runtime.Composable
import dev.valvavassori.compose.sweeper.core.MineSweeperBoard
import dev.valvavassori.compose.sweeper.ui.components.Board
import dev.valvavassori.compose.sweeper.ui.components.nineeightcss.CloseButton
import dev.valvavassori.compose.sweeper.ui.components.nineeightcss.Container
import dev.valvavassori.compose.sweeper.ui.components.nineeightcss.MinimizeButton
import dev.valvavassori.compose.sweeper.ui.components.nineeightcss.StatusBar
import dev.valvavassori.compose.sweeper.ui.components.nineeightcss.StatusBarField
import dev.valvavassori.compose.sweeper.ui.components.nineeightcss.TitleBar
import dev.valvavassori.compose.sweeper.ui.components.nineeightcss.Window
import org.jetbrains.compose.web.dom.Text

@Composable
fun GameWindow(game: MineSweeperBoard) {
    Window {
        TitleBar(
            title = "Compose Mine Sweeper",
            buttons = {
                MinimizeButton()
                CloseButton()
            }
        )
        Container(contentPadding = true) {
            Board(game)
        }
        StatusBar {
            StatusBarField { Text("Press F1 to open menu") }
        }
    }
}