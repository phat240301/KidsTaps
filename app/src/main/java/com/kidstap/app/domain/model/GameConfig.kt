package com.kidstap.app.domain.model

data class GameConfig(
    val mode: GameMode,
    val childAge: Int,
    val spawnIntervalSeconds: Float = 4.0f,
    val holdDurationSeconds: Float = 4.5f,
    val targetScore: Int = 15,
    val enableBGM: Boolean = true,
    val calmMode: Boolean = false,
) {
    companion object {
        // Suggested default config based on age (teacher can override all)
        fun suggestForAge(age: Int): GameConfig {
            val spawnInterval = maxOf(1.5f, 5.5f - (age * 0.3f))
            val holdDuration = maxOf(2.0f, 6.0f - (age * 0.3f))
            val targetScore = minOf(30, 3 + (age * 2))

            return GameConfig(
                mode = GameMode.SHAPES,
                childAge = age,
                spawnIntervalSeconds = spawnInterval,
                holdDurationSeconds = holdDuration,
                targetScore = targetScore,
            )
        }
    }
}
