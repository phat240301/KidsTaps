package com.kidstap.app.ui.game;

import com.kidstap.app.audio.SoundManager;
import com.kidstap.app.data.SettingsRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class GameViewModel_Factory implements Factory<GameViewModel> {
  private final Provider<SettingsRepository> settingsRepositoryProvider;

  private final Provider<SoundManager> soundManagerProvider;

  public GameViewModel_Factory(Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<SoundManager> soundManagerProvider) {
    this.settingsRepositoryProvider = settingsRepositoryProvider;
    this.soundManagerProvider = soundManagerProvider;
  }

  @Override
  public GameViewModel get() {
    return newInstance(settingsRepositoryProvider.get(), soundManagerProvider.get());
  }

  public static GameViewModel_Factory create(
      Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<SoundManager> soundManagerProvider) {
    return new GameViewModel_Factory(settingsRepositoryProvider, soundManagerProvider);
  }

  public static GameViewModel newInstance(SettingsRepository settingsRepository,
      SoundManager soundManager) {
    return new GameViewModel(settingsRepository, soundManager);
  }
}
