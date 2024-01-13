package dev.valvassori.compose.sweeper.core.ext

private val DIGITS = "0123456789"
fun String.isDigit(): Boolean =
    this.isNotEmpty() && all { it in DIGITS }
