package dev.valvassori.compose.sweeper.core.model

enum class Difficulty(
    val prettyName: String,
    val size: Pair<Int, Int>,
    val mines: Int,
    val initialScore: Int,
    val scoreReductionMultiplier: Int,
) {
    BEGINNER(
        prettyName = "Beginner",
        size = 10 to 10,
        mines = 10,
        initialScore = 1000,
        scoreReductionMultiplier = 3,
    ),
    INTERMEDIATE(
        prettyName = "Intermediate",
        size = 16 to 16,
        mines = 40,
        initialScore = 2000,
        scoreReductionMultiplier = 2,
    ),
    EXPERT(
        prettyName = "Expert",
        size = 16 to 30,
        mines = 99,
        initialScore = 3000,
        scoreReductionMultiplier = 1,
    );

    companion object {
        fun parse(value: String?): Difficulty =
            values().find { it.name == value || it.prettyName == value } ?: BEGINNER
    }
}
