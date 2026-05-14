package com.nammakathe.ui.home;

import com.nammakathe.core.data.repository.AiStoryService;
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
public final class MainViewModel_Factory implements Factory<MainViewModel> {
  private final Provider<NammaKatheyRepository> repositoryProvider;

  private final Provider<AiStoryService> aiStoryServiceProvider;

  public MainViewModel_Factory(Provider<NammaKatheyRepository> repositoryProvider,
      Provider<AiStoryService> aiStoryServiceProvider) {
    this.repositoryProvider = repositoryProvider;
    this.aiStoryServiceProvider = aiStoryServiceProvider;
  }

  @Override
  public MainViewModel get() {
    return newInstance(repositoryProvider.get(), aiStoryServiceProvider.get());
  }

  public static MainViewModel_Factory create(Provider<NammaKatheyRepository> repositoryProvider,
      Provider<AiStoryService> aiStoryServiceProvider) {
    return new MainViewModel_Factory(repositoryProvider, aiStoryServiceProvider);
  }

  public static MainViewModel newInstance(NammaKatheyRepository repository,
      AiStoryService aiStoryService) {
    return new MainViewModel(repository, aiStoryService);
  }
}
