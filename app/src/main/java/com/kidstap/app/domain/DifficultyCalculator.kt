package com.kidstap.app.domain

import com.kidstap.app.domain.model.GameConfig

/**
 * Calculates difficulty settings based on child's age.
 * Formula optimized for children with developmental delays (lenient/generous).
 *
 * spawnInterval  = max(1.5, 5.5 - age × 0.3)  seconds
 * holdDuration   = max(2.0, 6.0 - age × 0.3)  seconds
 * targetScore    = min(30,  3 + age × 2)       points
 */
object DifficultyCalculator {
    fun calculateForAge(age: Int, mode: com.kidstap.app.domain.model.GameMode): GameConfig {
        val clampedAge = age.coerceIn(3, 12)

        // Formula for special needs children (lenient)
        val spawnInterval = maxOf(1.5f, 5.5f - (clampedAge * 0.3f))
        val holdDuration = maxOf(2.0f, 6.0f - (clampedAge * 0.3f))
        val targetScore = minOf(30, 3 + (clampedAge * 2))

        return GameConfig(
            mode = mode,
            childAge = clampedAge,
            spawnIntervalSeconds = "%.1f".format(spawnInterval).toFloat(),
            holdDurationSeconds = "%.1f".format(holdDuration).toFloat(),
            targetScore = targetScore,
            enableBGM = true,
            calmMode = false,
        )
    }

    // Clamp user-adjusted values to safe ranges
    fun clampSpawnInterval(value: Float): Float = value.coerceIn(1.5f, 8.0f)
    fun clampHoldDuration(value: Float): Float = value.coerceIn(2.0f, 10.0f)
    fun clampTargetScore(value: Int): Int = value.coerceIn(5, 30)
}
