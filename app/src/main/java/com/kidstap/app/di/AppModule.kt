package com.kidstap.app.di

import android.content.Context
import com.kidstap.app.audio.SoundManager
import com.kidstap.app.data.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSettingsRepository(
        @ApplicationContext context: Context,
    ): SettingsRepository = SettingsRepository(context)

    @Singleton
    @Provides
    fun provideSoundManager(
        @ApplicationContext context: Context,
    ): SoundManager {
        val soundManager = SoundManager(context)
        soundManager.init()
        return soundManager
    }
}
