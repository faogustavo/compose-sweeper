package dev.valvassori.compose.sweeper

import dev.valvassori.theme.NineEightCSS
import dev.valvassori.compose.sweeper.core.MineSweeperBoard
import dev.valvassori.compose.sweeper.core.model.Difficulty
import dev.valvassori.compose.sweeper.ui.containers.GameWindow
import dev.valvassori.compose.sweeper.ui.containers.TaskBar
import dev.valvassori.compose.sweeper.ui.theme.MineSweeperCSS
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable

fun main() {
    val gameInstance = MineSweeperBoard(Difficulty.BEGINNER)

    renderComposable(rootElementId = "root") {
        Style(NineEightCSS)
        Style(MineSweeperCSS)

        TaskBar()
        GameWindow(gameInstance)
    }
}
