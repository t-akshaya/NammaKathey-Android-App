package com.nammakathe.di;

import com.nammakathe.core.data.local.dao.UserProgressDao;
import com.nammakathe.core.data.local.database.NammaKatheyDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AppModule_ProvideUserProgressDaoFactory implements Factory<UserProgressDao> {
  private final Provider<NammaKatheyDatabase> dbProvider;

  public AppModule_ProvideUserProgressDaoFactory(Provider<NammaKatheyDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public UserProgressDao get() {
    return provideUserProgressDao(dbProvider.get());
  }

  public static AppModule_ProvideUserProgressDaoFactory create(
      Provider<NammaKatheyDatabase> dbProvider) {
    return new AppModule_ProvideUserProgressDaoFactory(dbProvider);
  }

  public static UserProgressDao provideUserProgressDao(NammaKatheyDatabase db) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideUserProgressDao(db));
  }
}
