package com.kidstap.app.audio

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import android.util.Log

/**
 * Manages all game audio:
 * - SoundPool for short SFX (correct, wrong, miss, etc.) — low latency
 * - MediaPlayer for BGM (long looping background music)
 *
 * Note: Audio files go into res/raw/
 * Download from Freesound.org, OpenGameArt.org, or similar (CC0/MIT license)
 */
class SoundManager(private val context: Context) {

    private lateinit var soundPool: SoundPool
    private var mediaPlayer: MediaPlayer? = null

    // Sound IDs (loaded from assets)
    private var soundCorrect = 0
    private var soundWrong = 0
    private var soundMiss = 0
    private var soundWin = 0
    private var soundCountdown = 0

    private var bgmVolume = 0.3f
    private var sfxVolume = 1.0f

    // Anti-overlap: track last play time for same sound
    private var lastCorrectPlayTime = 0L
    private val SOUND_COOLDOWN_MS = 200

    fun init() {
        // Setup SoundPool with priority
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(4)
            .setAudioAttributes(audioAttributes)
            .build()

        // Load SFX assets
        // TODO: Add your audio files to res/raw/ and uncomment these lines
        try {
            // soundCorrect = soundPool.load(context, R.raw.correct, 1)
            // soundWrong = soundPool.load(context, R.raw.wrong, 1)
            // soundMiss = soundPool.load(context, R.raw.miss, 1)
            // soundWin = soundPool.load(context, R.raw.win, 1)
            // soundCountdown = soundPool.load(context, R.raw.countdown, 1)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Setup MediaPlayer for BGM
        mediaPlayer = MediaPlayer()
    }

    fun playCorrect() {
        val now = System.currentTimeMillis()
        if (now - lastCorrectPlayTime < SOUND_COOLDOWN_MS) return
        lastCorrectPlayTime = now

        if (soundCorrect != 0) soundPool.play(soundCorrect, sfxVolume, sfxVolume, 1, 0, 1.0f)
    }

    fun playWrong() {
        if (soundWrong != 0) soundPool.play(soundWrong, sfxVolume, sfxVolume, 1, 0, 1.0f)
    }

    fun playMiss() {
        if (soundMiss != 0) soundPool.play(soundMiss, sfxVolume, sfxVolume, 1, 0, 1.0f)
    }

    fun playWin() {
        if (soundWin != 0) soundPool.play(soundWin, sfxVolume, sfxVolume, 1, 0, 1.0f)
    }

    fun playCountdown() {
        if (soundCountdown != 0) soundPool.play(soundCountdown, sfxVolume, sfxVolume, 1, 0, 1.0f)
    }

    fun playBGM() {
        try {
            // TODO: Uncomment when bgm.mp3 is added to res/raw/
            /*
            mediaPlayer = MediaPlayer.create(context, R.raw.bgm)
            mediaPlayer?.isLooping = true
            mediaPlayer?.setVolume(bgmVolume, bgmVolume)
            mediaPlayer?.start()
            */
            Log.d("SoundManager", "Playing BGM")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun stopBGM() {
        mediaPlayer?.pause()
    }

    fun setBGMVolume(volume: Float) {
        bgmVolume = volume.coerceIn(0f, 1f)
        mediaPlayer?.setVolume(bgmVolume, bgmVolume)
    }

    fun setSFXVolume(volume: Float) {
        sfxVolume = volume.coerceIn(0f, 1f)
    }

    fun release() {
        soundPool.release()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
