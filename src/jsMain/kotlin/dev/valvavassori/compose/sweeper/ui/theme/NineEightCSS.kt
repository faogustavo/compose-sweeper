package dev.valvassori.theme

import org.jetbrains.compose.web.css.*

object NineEightCSS : StyleSheet() {
    const val window = "window"
    const val titleBar = "title-bar"
    const val titleBarText = "title-bar-text"
    const val titleBarControls = "title-bar-controls"
    const val windowBody = "window-body"
    const val statusBar = "status-bar"
    const val statusBarField = "status-bar-field"
    const val fieldRowStack = "field-row-stacked"

    init {
        className(statusBarField) style {
            lineHeight(16.px)
        }

        className(window) style {
            property("inline-size", "min-content")
        }

        className(windowBody) style {
            margin(0.px)

            className("padded") style {
                padding(12.px)
            }
        }

        className(fieldRowStack) style {
            width(100.percent)
            marginBottom(12.px)
        }

        className(titleBar) style {
            "img" style {
                width(12.px)
                height(12.px)
                marginRight(4.px)
            }
        }
    }
}
