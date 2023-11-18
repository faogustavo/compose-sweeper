package dev.valvavassori.compose.sweeper.ui.containers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.valvavassori.compose.sweeper.core.MineSweeperBoard
import dev.valvavassori.compose.sweeper.core.model.Difficulty
import dev.valvavassori.compose.sweeper.core.model.GameNode
import dev.valvavassori.compose.sweeper.ui.components.nineeightcss.CloseButton
import dev.valvavassori.compose.sweeper.ui.components.nineeightcss.Container
import dev.valvavassori.compose.sweeper.ui.components.nineeightcss.MinimizeButton
import dev.valvavassori.compose.sweeper.ui.components.nineeightcss.StatusBar
import dev.valvavassori.compose.sweeper.ui.components.nineeightcss.StatusBarField
import dev.valvavassori.compose.sweeper.ui.components.nineeightcss.TitleBar
import dev.valvavassori.compose.sweeper.ui.components.nineeightcss.Window
import dev.valvavassori.compose.sweeper.ui.theme.MineSweeperCSS
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Table
import org.jetbrains.compose.web.dom.Td
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Tr

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
            Header(game)
            Board(game)
        }
        StatusBar {
            StatusBarField { Text("Press F1 to open menu") }
        }
    }
}

@Composable
private fun Header(game: MineSweeperBoard) {
    val time by game.gameDuration.collectAsState()
    val state by game.playerState.collectAsState()
    val playsCount by game.playsCount.collectAsState()

    Container(
        windowBody = false,
        attrs = {
            classes(
                MineSweeperCSS.lowerContainer,
                MineSweeperCSS.gameBar,
            )
        }
    ) {
        Span {
            Text(time.toString().padStart(4, '0'))
        }
        Button({ onClick { game.newGame(Difficulty.BEGINNER) } }) {
            Text(state.emoji)
        }
        Span {
            Text(playsCount.toString().padStart(4, '0'))
        }
    }
}

@Composable
private fun Board(game: MineSweeperBoard) {
    val board by game.boardState.collectAsState()
    Container(
        windowBody = false,
        attrs = {
            classes(
                MineSweeperCSS.lowerContainer,
                MineSweeperCSS.gameBoard,
            )
        }
    ) {
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
                        "bt-${node.value}".takeIf { node.isOpen },
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