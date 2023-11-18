package dev.valvavassori.compose.sweeper.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.valvavassori.compose.sweeper.core.MineSweeperBoard
import dev.valvavassori.compose.sweeper.core.model.GameNode
import dev.valvavassori.compose.sweeper.ui.theme.MineSweeperCSS
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Table
import org.jetbrains.compose.web.dom.Td
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Tr

@Composable
fun Board(game: MineSweeperBoard) {
    val board by game.boardState.collectAsState()
    Table {
        board.forEachIndexed { rowIdx, data ->
            Tr {
                data.forEachIndexed { columnIdx, node ->
                    BoardNode(
                        rowIdx = rowIdx,
                        columnIdx = columnIdx,
                        node = node,
                        onClick = { game.open(it) },
                    )
                }
            }
        }
    }
}

@Composable
private fun BoardNode(
    rowIdx: Int,
    columnIdx: Int,
    node: GameNode,
    onClick: (Pair<Int, Int>) -> Unit,
) {
    Td {
        Button(
            attrs = {
                classes(
                    setOfNotNull(
                        MineSweeperCSS.gameButton,
                        "active".takeIf { node.isOpen },
                    )
                )
                onClick {
                    onClick(rowIdx to columnIdx)
                }
            }
        ) {
            Text(if (node.isOpen) node.value else "")
        }
    }
}