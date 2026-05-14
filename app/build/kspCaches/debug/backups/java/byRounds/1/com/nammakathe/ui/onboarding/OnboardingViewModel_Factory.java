package com.nammakathe.ui.onboarding;

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
public final class OnboardingViewModel_Factory implements Factory<OnboardingViewModel> {
  private final Provider<NammaKatheyRepository> repositoryProvider;

  public OnboardingViewModel_Factory(Provider<NammaKatheyRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public OnboardingViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static OnboardingViewModel_Factory create(
      Provider<NammaKatheyRepository> repositoryProvider) {
    return new OnboardingViewModel_Factory(repositoryProvider);
  }

  public static OnboardingViewModel newInstance(NammaKatheyRepository repository) {
    return new OnboardingViewModel(repository);
  }
}
