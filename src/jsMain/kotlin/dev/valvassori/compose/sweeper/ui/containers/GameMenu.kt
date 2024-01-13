package dev.valvassori.compose.sweeper.ui.containers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.valvassori.compose.sweeper.core.model.Difficulty
import dev.valvassori.compose.sweeper.ui.components.nineeightcss.CloseButton
import dev.valvassori.compose.sweeper.ui.components.nineeightcss.Container
import dev.valvassori.compose.sweeper.ui.components.nineeightcss.RadioGroup
import dev.valvassori.compose.sweeper.ui.components.nineeightcss.TitleBar
import dev.valvassori.compose.sweeper.ui.components.nineeightcss.Window
import dev.valvassori.compose.sweeper.ui.theme.MineSweeperCSS
import org.jetbrains.compose.web.css.marginRight
import org.jetbrains.compose.web.css.minWidth
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

@Composable
fun GameMenu(
    currentDifficulty: Difficulty,
    onSave: (Difficulty) -> Unit,
    onCloseClick: () -> Unit,
) {
    var difficulty by remember { mutableStateOf(currentDifficulty) }

    Window(
        viewId = "minesweeper-game-menu",
        draggable = true,
        attrs = {
            style {
                minWidth(250.px)
            }
        }
    ) {
        TitleBar(
            title = "Minesweeper Menu",
            buttons = {
                CloseButton(attrs = {
                    onClick { onCloseClick() }
                })
            }
        )
        Container(contentPadding = true) {
            RadioGroup(
                setName = "difficulty",
                options = Difficulty.entries.map { it.name to it.prettyName },
                value = difficulty.name,
                legend = "Difficulty",
                onSelect = { difficulty = Difficulty.valueOf(it) },
            )
        }

        Div({ classes(MineSweeperCSS.buttonGroup) }) {
            Button(attrs = {
                style { marginRight(6.px) }
                onClick { onCloseClick() }
            }) {
                Text("Cancel")
            }
            Button(attrs = {
                onClick { onSave(difficulty) }
            }) {
                Text("Save")
            }
        }
    }
}
