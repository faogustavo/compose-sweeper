package dev.valvavassori.compose.sweeper.core

import dev.valvavassori.compose.sweeper.core.model.Difficulty
import kotlin.test.Test

class MineSweeperBoardTest {
    @Test
    fun init_withBeginner_runsAsExpected() {
        MineSweeperBoard(Difficulty.BEGINNER)
    }

    @Test
    fun init_withIntermediate_runsAsExpected() {
        MineSweeperBoard(Difficulty.INTERMEDIATE)
    }

    @Test
    fun init_withExpert_runsAsExpected() {
        MineSweeperBoard(Difficulty.EXPERT)
    }
}