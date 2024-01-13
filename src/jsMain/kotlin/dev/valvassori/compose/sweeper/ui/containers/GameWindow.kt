package dev.valvassori.compose.sweeper.ui.containers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.valvassori.compose.sweeper.core.MineSweeperBoard
import dev.valvassori.compose.sweeper.core.model.Difficulty
import dev.valvassori.compose.sweeper.core.model.GameNode
import dev.valvassori.compose.sweeper.core.model.PlayerState
import dev.valvassori.compose.sweeper.ui.components.nineeightcss.CloseButton
import dev.valvassori.compose.sweeper.ui.components.nineeightcss.Container
import dev.valvassori.compose.sweeper.ui.components.nineeightcss.MinimizeButton
import dev.valvassori.compose.sweeper.ui.components.nineeightcss.StatusBar
import dev.valvassori.compose.sweeper.ui.components.nineeightcss.StatusBarField
import dev.valvassori.compose.sweeper.ui.components.nineeightcss.TitleBar
import dev.valvassori.compose.sweeper.ui.components.nineeightcss.Window
import dev.valvassori.compose.sweeper.ui.consts.ImageConstants
import dev.valvassori.compose.sweeper.ui.effects.KeyPressEffect
import dev.valvassori.compose.sweeper.ui.theme.MineSweeperCSS
import kotlinx.browser.document
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Table
import org.jetbrains.compose.web.dom.Td
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Tr
import org.w3c.dom.HTMLElement
import org.w3c.dom.get

@Composable
fun GameWindow(game: MineSweeperBoard) {
    var dialogVisible by remember { mutableStateOf(false) }

    KeyPressEffect(112) { // F1 press
        dialogVisible = true
    }

    Window(
        viewId = "game-window",
        draggable = true,
    ) {
        TitleBar(
            title = "Minesweeper",
            icon = ImageConstants.Applications.mineSweeper,
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

    if (dialogVisible) {
        GameMenu(
            currentDifficulty = game.difficulty,
            onCloseClick = { dialogVisible = false },
            onSave = {
                game.newGame(it)
                dialogVisible = false
            },
        )
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
    val state by game.playerState.collectAsState()

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
                            playerState = state,
                            onClick = { game.open(it) },
                            onToggle = { game.toggleMark(it) },
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
    playerState: PlayerState,
    onClick: (Pair<Int, Int>) -> Unit,
    onToggle: (Pair<Int, Int>) -> Unit,
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
                onClick { onClick(rowIdx to columnIdx) }
                addEventListener("contextmenu") {
                    it.preventDefault()
                    onToggle(rowIdx to columnIdx)
                }
            }
        ) { Text(node.renderValue(playerState)) }
    }
}
