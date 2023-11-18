package dev.valvavassori.compose.sweeper

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.valvavassori.compose.sweeper.core.MineSweeperBoard
import dev.valvavassori.compose.sweeper.core.model.Difficulty
import kotlinx.coroutines.flow.MutableStateFlow
import org.jetbrains.compose.web.renderComposable

fun main() {
    val game = MutableStateFlow(MineSweeperBoard(Difficulty.BEGINNER))
    renderComposable(rootElementId = "root") {
        val game by game.collectAsState()

        App(game)
    }
}