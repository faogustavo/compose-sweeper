package dev.valvassori.compose.sweeper.ui.containers

import androidx.compose.runtime.Composable
import dev.valvassori.compose.sweeper.ui.components.nineeightcss.VerticalDivider
import dev.valvassori.compose.sweeper.ui.consts.ImageConstants
import dev.valvassori.compose.sweeper.ui.theme.MineSweeperCSS
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Text

@Composable
fun TaskBar() {
    Div(attrs = { classes(MineSweeperCSS.taskBar, MineSweeperCSS.higherContainer) }) {
        StartButton()
        VerticalDivider()
        Task(
            icon = ImageConstants.Applications.mineSweeper,
            title = "Minesweeper",
            isActive = true
        )
    }
}

@Composable
private fun StartButton() {
    Button({
        id("startButton")
    }) {
        Img(src = ImageConstants.startIcon)
        Text("Start")
    }
}

@Composable
private fun Task(
    icon: String,
    title: String,
    isActive: Boolean,
) {
    Button(attrs = {
        classes(
            setOfNotNull(
                "task",
                "active".takeIf { isActive },
            )
        )
    }) {
        Img(src = icon)
        Text(title)
    }
}
