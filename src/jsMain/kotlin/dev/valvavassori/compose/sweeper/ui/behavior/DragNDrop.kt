package dev.valvavassori.compose.sweeper.ui.behavior

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import kotlinx.browser.document
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.get

@Composable
fun DragAndDropEffect(viewId: String) = SideEffect {
    val rootElement = document.getElementById(viewId) as? HTMLElement ?: return@SideEffect
    val titleBar = rootElement.getElementsByClassName("title-bar")[0] as? HTMLDivElement ?: return@SideEffect

    var offsetX = 0
    var offsetY = 0
    var currentPositionX = 0
    var currentPositionY = 0

    titleBar.onmousedown = { downEvent ->
        downEvent.preventDefault()

        currentPositionX = downEvent.clientX
        currentPositionY = downEvent.clientY

        document.onmouseup = { _ ->
            document.onmouseup = null
            document.onmousemove = null
            null
        }

        document.onmousemove = { moveEvent ->
            moveEvent.preventDefault()

            offsetX = currentPositionX - moveEvent.clientX
            offsetY = currentPositionY - moveEvent.clientY
            currentPositionX = moveEvent.clientX
            currentPositionY = moveEvent.clientY

            rootElement.style.left = (rootElement.offsetLeft - offsetX).toString() + "px"
            rootElement.style.top = (rootElement.offsetTop - offsetY).toString() + "px"

            null
        }

        null
    }
}
