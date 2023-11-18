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

    var pos1 = 0
    var pos2 = 0
    var pos3 = 0
    var pos4 = 0

    titleBar.onmousedown = { downEvent ->
        downEvent.preventDefault()

        pos3 = downEvent.clientX
        pos4 = downEvent.clientY

        document.onmouseup = { _ ->
            document.onmouseup = null
            document.onmousemove = null
            null
        }

        document.onmousemove = { moveEvent ->
            moveEvent.preventDefault()

            pos1 = pos3 - moveEvent.clientX
            pos2 = pos4 - moveEvent.clientY
            pos3 = moveEvent.clientX
            pos4 = moveEvent.clientY

            rootElement.style.left = (rootElement.offsetLeft - pos1).toString() + "px"
            rootElement.style.top = (rootElement.offsetTop - pos2).toString() + "px"

            null
        }

        null
    }
}