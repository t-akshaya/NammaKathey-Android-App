package com.nammakathe.ui.quiz;

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
public final class QuizViewModel_Factory implements Factory<QuizViewModel> {
  private final Provider<NammaKatheyRepository> repositoryProvider;

  public QuizViewModel_Factory(Provider<NammaKatheyRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public QuizViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static QuizViewModel_Factory create(Provider<NammaKatheyRepository> repositoryProvider) {
    return new QuizViewModel_Factory(repositoryProvider);
  }

  public static QuizViewModel newInstance(NammaKatheyRepository repository) {
    return new QuizViewModel(repository);
  }
}
