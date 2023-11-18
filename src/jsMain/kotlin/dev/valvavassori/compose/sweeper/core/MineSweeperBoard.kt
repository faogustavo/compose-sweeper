package dev.valvavassori.compose.sweeper.core

import dev.valvavassori.compose.sweeper.core.ext.isDigit
import dev.valvavassori.compose.sweeper.core.model.Difficulty
import dev.valvavassori.compose.sweeper.core.model.GameNode
import dev.valvavassori.compose.sweeper.core.model.PlayerState
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MineSweeperBoard(difficulty: Difficulty): AutoCloseable {
    private val coroutineScope by lazy { MainScope() }
    private var rows = 0
    private var columns = 0

    private val _playerState = MutableStateFlow(PlayerState.ALIVE)
    val playerState = _playerState.asStateFlow()

    private val _boardState = MutableStateFlow<List<List<GameNode>>>(emptyList())
    val boardState: StateFlow<List<List<GameNode>>> = _boardState.asStateFlow()

    private var tickTask: Job? = null
    private val _gameDuration = MutableStateFlow(0)
    val gameDuration = _gameDuration.asStateFlow()

    private val _playsCount = MutableStateFlow(0)
    val playsCount = _playsCount.asStateFlow()

    init { newGame(difficulty) }

    fun newGame(difficulty: Difficulty) {
        tickTask?.cancel()
        tickTask = null

        rows = difficulty.size.first
        columns = difficulty.size.second

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

        _playerState.value = PlayerState.ALIVE
        _gameDuration.value = 0
        _playsCount.value = 0
        _boardState.value = matrix.map { it.toList() }
    }

    fun toggleMark(position: Pair<Int, Int>) = useMatrix { matrix ->
        // When already dead, does not compute the play
        if (_playerState.value != PlayerState.ALIVE) return@useMatrix

        val node = matrix[position.first][position.second]

        // When already open, do nothing
        if (node.isOpen) return@useMatrix

        _playsCount.value += 1
        matrix[position.first][position.second] = node.toggleMark()
    }

    fun open(position: Pair<Int, Int>) = useMatrix { matrix ->
        // When already dead, does not compute the play
        if (_playerState.value != PlayerState.ALIVE) return@useMatrix
        if (!openInternal(matrix, position)) return@useMatrix

        _playsCount.value += 1

        val allNumbersOpen = matrix
            .flatten()
            .filter { it.value.isDigit() }
            .none { !it.isOpen }

        if (allNumbersOpen) _playerState.value = PlayerState.VICTORY
    }

    override fun close() {
        coroutineScope.cancel()
    }

    private fun openInternal(
        matrix: List<MutableList<GameNode>>,
        position: Pair<Int, Int>,
    ): Boolean {
        val node = matrix[position.first][position.second]

        // If node is already open, nothing is required
        if (node.isOpen) return false

        matrix[position.first][position.second] = node.open()

        if (node.isMine) {
            _playerState.value = PlayerState.DEAD
            tickTask?.cancel()
            tickTask = null
            return false
        }

        if (node.isEmpty) expandOnEmpty(matrix, position)
        return true
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

    private fun startTimer() {
        if (tickTask != null) return
        tickTask = coroutineScope.launch {
            while(true) {
                _gameDuration.value += 1
                delay(1000)
            }
        }
    }

    private fun useMatrix(block: (List<MutableList<GameNode>>) -> Unit) {
        if (_playerState.value == PlayerState.ALIVE) startTimer()

        val matrix = _boardState.value.map { it.toMutableList() }
        block(matrix)
        _boardState.value = matrix.map { it.toList() }
    }
}

private val aroundIdx = arrayOf(
    -1 to -1, -1 to 0, -1 to 1,
    0 to -1, 0 to 1,
    1 to -1, 1 to 0, 1 to 1,
)