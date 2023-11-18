package dev.valvavassori.compose.sweeper.core.model

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GameNodeTest {
    @Test
    fun createMine_hasExpectedValues() {
        val mine = GameNode.createMine()

        assertEquals("💣", mine.value)
        assertEquals(MarkType.NONE, mine.mark)
        assertEquals(false, mine.isOpen)
    }

    @Test
    fun createEmpty_hasExpectedValues() {
        val mine = GameNode.createEmpty()

        assertEquals("", mine.value)
        assertEquals(MarkType.NONE, mine.mark)
        assertEquals(false, mine.isOpen)
    }

    @Test
    fun open_changeIsOpen() {
        var node = GameNode(value = "", mark = MarkType.NONE, isOpen = false)

        node = node.open()
        assertTrue(node.isOpen)
    }

    @Test
    fun toggleMark_returnExpectedValue() {
        var node = GameNode(value = "", mark = MarkType.NONE, isOpen = false)

        node = node.toggleMark()
        assertEquals(MarkType.FLAG, node.mark)

        node = node.toggleMark()
        assertEquals(MarkType.QUESTION, node.mark)

        node = node.toggleMark()
        assertEquals(MarkType.NONE, node.mark)
    }

    @Test
    fun inc_returnCorrectValue() {
        var node = GameNode(value = "", mark = MarkType.NONE, isOpen = false)

        node = node.inc()
        assertEquals("1", node.value)

        node = node.inc()
        assertEquals("2", node.value)

        node = node.inc()
        assertEquals("3", node.value)

        node = node.inc()
        assertEquals("4", node.value)

        node = node.inc()
        assertEquals("5", node.value)

        node = node.inc()
        assertEquals("6", node.value)

        node = node.inc()
        assertEquals("7", node.value)

        node = node.inc()
        assertEquals("8", node.value)
    }
}