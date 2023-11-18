package dev.valvavassori.compose.sweeper

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.valvassori.theme.NineEightCSS
import dev.valvavassori.compose.sweeper.core.MineSweeperBoard
import dev.valvavassori.compose.sweeper.core.model.Difficulty
import dev.valvavassori.compose.sweeper.ui.theme.MineSweeperCSS
import kotlinx.coroutines.flow.MutableStateFlow
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable

fun main() {
    val game = MutableStateFlow(MineSweeperBoard(Difficulty.BEGINNER))
    renderComposable(rootElementId = "root") {
        Style(NineEightCSS)
        Style(MineSweeperCSS)

        val gameInstance by game.collectAsState()

        App(gameInstance)
    }
}