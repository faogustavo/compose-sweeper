package dev.valvavassori.compose.sweeper.core

import dev.valvavassori.compose.sweeper.core.model.Difficulty
import dev.valvavassori.compose.sweeper.core.model.GameState
import dev.valvavassori.compose.sweeper.core.model.GameNode
import dev.valvavassori.compose.sweeper.core.ext.isDigit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MineSweeperBoard(private val difficulty: Difficulty) {
    private val rows = difficulty.size.first
    private val columns = difficulty.size.second

    private val _state = MutableStateFlow(GameState.ALIVE)
    private val state = _state.asStateFlow()

    val matrix = Array(rows) {
        Array(columns) { GameNode.createEmpty() }
    }

    init {
        // Sorting bombs
        var mineCount = 0
        val rowRange = (0 until rows)
        val columRange = (0 until columns)

        while (mineCount < difficulty.mines) {
            val nextRow = rowRange.random()
            val nextColumn = columRange.random()

            if (matrix[nextRow][nextColumn].isMine)
                continue

            matrix[nextRow][nextColumn] = GameNode.createMine()

            aroundIdx.forEach { (row, column) ->
                if (nextRow + row >= rows || nextRow + row < 0)
                    return@forEach

                if (nextColumn + column >= columns || nextColumn + column < 0)
                    return@forEach

                matrix[nextRow + row][nextColumn + column] =
                    matrix[nextRow + row][nextColumn + column].inc()
            }

            mineCount += 1
        }
    }

    fun toggleMark(position: Pair<Int, Int>) {
        // When already dead, does not compute the play
        if (_state.value != GameState.ALIVE) return

        val node = matrix[position.first][position.second]

        // When already open, do nothing
        if (node.isOpen) return

        matrix[position.first][position.second] = node.toggleMark()
    }

    fun open(position: Pair<Int, Int>) {
        // When already dead, does not compute the play
        if (_state.value != GameState.ALIVE) return

        val node = matrix[position.first][position.second]
        matrix[position.first][position.second] = node.open()

        if (node.isMine) {
            _state.value = GameState.DEAD
            return
        }

        if (node.isEmpty) expandOnEmpty(position)

        val allNumbersOpen = matrix
            .flatten()
            .filter { it.value.isDigit() }
            .none { !it.isOpen }

        if (allNumbersOpen) _state.value = GameState.VICTORY
    }

    private fun expandOnEmpty(position: Pair<Int, Int>) {
        val (currentRow, currentColumn) = position
        aroundIdx.forEach { (row, column) ->
            val nextRow = currentRow + row
            val nextColumn = currentColumn + column

            if (nextRow >= rows || nextRow < 0) return@forEach
            if (nextColumn >= columns || nextColumn < 0) return@forEach

            open(nextRow to nextColumn)
        }
    }
}

private val aroundIdx = arrayOf(
    -1 to -1, -1 to 0, -1 to 1,
    0 to -1, 0 to 1,
    1 to -1, 1 to 0, 1 to 1,
)