package dev.valvavassori.compose.sweeper.core.model

private const val BOMB_EMOJI = "💣"

data class GameNode(
    val value: String,
    val mark: MarkType = MarkType.NONE,
    val isOpen: Boolean = false,
) {
    val isMine: Boolean = value == BOMB_EMOJI
    val isEmpty: Boolean = value.isEmpty()

    fun renderValue(playerState: PlayerState) = when {
        isOpen -> value
        playerState == PlayerState.DEAD && isMine -> value
        playerState == PlayerState.VICTORY && isMine -> "🚩"
        else -> mark.symbol
    }

    internal fun open() = copy(isOpen = true)

    internal fun toggleMark() = copy(
        mark = MarkType.entries.toTypedArray().let { allMarks ->
            allMarks[(mark.ordinal + 1) % allMarks.size]
        }
    )

    internal fun inc() = if (isMine) this else copy(
        value = when {
            value.isEmpty() -> "1"
            value.toIntOrNull() != null -> {
                value.toInt().inc()
                    .coerceAtMost(8)
                    .toString()
            }
            else -> value
        }
    )

    companion object {
        internal fun createMine() = GameNode(value = BOMB_EMOJI)
        internal fun createEmpty() = GameNode(value = "")
    }
}
