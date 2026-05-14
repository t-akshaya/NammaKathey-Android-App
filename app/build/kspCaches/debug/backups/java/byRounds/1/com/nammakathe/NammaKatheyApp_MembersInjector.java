package com.nammakathe;

import androidx.hilt.work.HiltWorkerFactory;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class NammaKatheyApp_MembersInjector implements MembersInjector<NammaKatheyApp> {
  private final Provider<HiltWorkerFactory> workerFactoryProvider;

  public NammaKatheyApp_MembersInjector(Provider<HiltWorkerFactory> workerFactoryProvider) {
    this.workerFactoryProvider = workerFactoryProvider;
  }

  public static MembersInjector<NammaKatheyApp> create(
      Provider<HiltWorkerFactory> workerFactoryProvider) {
    return new NammaKatheyApp_MembersInjector(workerFactoryProvider);
  }

  @Override
  public void injectMembers(NammaKatheyApp instance) {
    injectWorkerFactory(instance, workerFactoryProvider.get());
  }

  @InjectedFieldSignature("com.nammakathe.NammaKatheyApp.workerFactory")
  public static void injectWorkerFactory(NammaKatheyApp instance, HiltWorkerFactory workerFactory) {
    instance.workerFactory = workerFactory;
  }
}
