package dev.valvavassori.compose.sweeper.core

import dev.valvavassori.compose.sweeper.core.ext.isDigit
import dev.valvavassori.compose.sweeper.core.model.Difficulty
import dev.valvavassori.compose.sweeper.core.model.GameNode
import dev.valvavassori.compose.sweeper.core.model.PlayerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MineSweeperBoard(private val difficulty: Difficulty) {
    private val rows = difficulty.size.first
    private val columns = difficulty.size.second

    private val _playerState = MutableStateFlow(PlayerState.ALIVE)
    val playerState = _playerState.asStateFlow()

    private val _boardState: MutableStateFlow<List<List<GameNode>>>
    val boardState: StateFlow<List<List<GameNode>>>

    init {
        // Sorting bombs
        var mineCount = 0
        val rowRange = (0..<rows)
        val columRange = (0..<columns)

        val matrix = List(rows) { MutableList(columns) { GameNode.createEmpty() } }

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

        _boardState = MutableStateFlow(matrix.map { it.toList() })
        boardState = _boardState.asStateFlow()
    }

    fun toggleMark(position: Pair<Int, Int>) {
        // When already dead, does not compute the play
        if (_playerState.value != PlayerState.ALIVE) return

        val matrix = _boardState.value.map { it.toMutableList() }
        val node = matrix[position.first][position.second]

        // When already open, do nothing
        if (node.isOpen) return

        matrix[position.first][position.second] = node.toggleMark()
        _boardState.value = matrix.map { it.toList() }
    }

    fun open(position: Pair<Int, Int>) {
        // When already dead, does not compute the play
        if (_playerState.value != PlayerState.ALIVE) return

        val matrix = _boardState.value.map { it.toMutableList() }
        openInternal(matrix, position)

        val allNumbersOpen = matrix
            .flatten()
            .filter { it.value.isDigit() }
            .none { !it.isOpen }

        if (allNumbersOpen) _playerState.value = PlayerState.VICTORY
        _boardState.value = matrix.map { it.toList() }
    }

    private fun openInternal(
        matrix: List<MutableList<GameNode>>,
        position: Pair<Int, Int>,
    ) {
        val node = matrix[position.first][position.second]

        // If node is already open, nothing is required
        if (node.isOpen) return

        matrix[position.first][position.second] = node.open()

        if (node.isMine) {
            _playerState.value = PlayerState.DEAD
            return
        }

        if (node.isEmpty) expandOnEmpty(matrix, position)
    }

    private fun expandOnEmpty(
        matrix: List<MutableList<GameNode>>,
        position: Pair<Int, Int>
    ) {
        val (currentRow, currentColumn) = position
        aroundIdx.forEach { (row, column) ->
            val nextRow = currentRow + row
            val nextColumn = currentColumn + column

            if (nextRow >= rows || nextRow < 0) return@forEach
            if (nextColumn >= columns || nextColumn < 0) return@forEach

            openInternal(matrix, nextRow to nextColumn)
        }
    }
}

private val aroundIdx = arrayOf(
    -1 to -1, -1 to 0, -1 to 1,
    0 to -1, 0 to 1,
    1 to -1, 1 to 0, 1 to 1,
)