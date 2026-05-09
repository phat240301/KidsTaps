package com.kidstap.app.di;

import android.content.Context;
import com.kidstap.app.audio.SoundManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class AppModule_ProvideSoundManagerFactory implements Factory<SoundManager> {
  private final Provider<Context> contextProvider;

  public AppModule_ProvideSoundManagerFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public SoundManager get() {
    return provideSoundManager(contextProvider.get());
  }

  public static AppModule_ProvideSoundManagerFactory create(Provider<Context> contextProvider) {
    return new AppModule_ProvideSoundManagerFactory(contextProvider);
  }

  public static SoundManager provideSoundManager(Context context) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideSoundManager(context));
  }
}
