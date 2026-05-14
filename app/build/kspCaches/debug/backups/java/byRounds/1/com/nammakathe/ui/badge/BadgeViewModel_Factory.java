package com.nammakathe.ui.badge;

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
public final class BadgeViewModel_Factory implements Factory<BadgeViewModel> {
  private final Provider<NammaKatheyRepository> repositoryProvider;

  public BadgeViewModel_Factory(Provider<NammaKatheyRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public BadgeViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static BadgeViewModel_Factory create(Provider<NammaKatheyRepository> repositoryProvider) {
    return new BadgeViewModel_Factory(repositoryProvider);
  }

  public static BadgeViewModel newInstance(NammaKatheyRepository repository) {
    return new BadgeViewModel(repository);
  }
}
