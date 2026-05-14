package com.nammakathe.core.util;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.nammakathe.core.data.repository.NammaKatheyRepository;
import dagger.internal.DaggerGenerated;
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
public final class DailyReminderWorker_Factory {
  private final Provider<NammaKatheyRepository> repositoryProvider;

  public DailyReminderWorker_Factory(Provider<NammaKatheyRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  public DailyReminderWorker get(Context context, WorkerParameters workerParams) {
    return newInstance(context, workerParams, repositoryProvider.get());
  }

  public static DailyReminderWorker_Factory create(
      Provider<NammaKatheyRepository> repositoryProvider) {
    return new DailyReminderWorker_Factory(repositoryProvider);
  }

  public static DailyReminderWorker newInstance(Context context, WorkerParameters workerParams,
      NammaKatheyRepository repository) {
    return new DailyReminderWorker(context, workerParams, repository);
  }
}
