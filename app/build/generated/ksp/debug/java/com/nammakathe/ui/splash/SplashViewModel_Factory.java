package com.nammakathe.ui.splash;

import com.nammakathe.core.data.repository.NammaKatheyRepository;
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
    "KotlinInternalInJava"
})
public final class SplashViewModel_Factory implements Factory<SplashViewModel> {
  private final Provider<NammaKatheyRepository> repositoryProvider;

  public SplashViewModel_Factory(Provider<NammaKatheyRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public SplashViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static SplashViewModel_Factory create(Provider<NammaKatheyRepository> repositoryProvider) {
    return new SplashViewModel_Factory(repositoryProvider);
  }

  public static SplashViewModel newInstance(NammaKatheyRepository repository) {
    return new SplashViewModel(repository);
  }
}
