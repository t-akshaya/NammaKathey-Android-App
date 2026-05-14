package com.nammakathe.core.data.repository;

import com.nammakathe.core.data.local.dao.UserDao;
import com.nammakathe.core.data.local.dao.UserProgressDao;
import com.nammakathe.core.data.local.database.PreferencesManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class NammaKatheyRepository_Factory implements Factory<NammaKatheyRepository> {
  private final Provider<HeroDataSource> heroDataSourceProvider;

  private final Provider<UserProgressDao> progressDaoProvider;

  private final Provider<UserDao> userDaoProvider;

  private final Provider<PreferencesManager> prefsManagerProvider;

  public NammaKatheyRepository_Factory(Provider<HeroDataSource> heroDataSourceProvider,
      Provider<UserProgressDao> progressDaoProvider, Provider<UserDao> userDaoProvider,
      Provider<PreferencesManager> prefsManagerProvider) {
    this.heroDataSourceProvider = heroDataSourceProvider;
    this.progressDaoProvider = progressDaoProvider;
    this.userDaoProvider = userDaoProvider;
    this.prefsManagerProvider = prefsManagerProvider;
  }

  @Override
  public NammaKatheyRepository get() {
    return newInstance(heroDataSourceProvider.get(), progressDaoProvider.get(), userDaoProvider.get(), prefsManagerProvider.get());
  }

  public static NammaKatheyRepository_Factory create(
      Provider<HeroDataSource> heroDataSourceProvider,
      Provider<UserProgressDao> progressDaoProvider, Provider<UserDao> userDaoProvider,
      Provider<PreferencesManager> prefsManagerProvider) {
    return new NammaKatheyRepository_Factory(heroDataSourceProvider, progressDaoProvider, userDaoProvider, prefsManagerProvider);
  }

  public static NammaKatheyRepository newInstance(HeroDataSource heroDataSource,
      UserProgressDao progressDao, UserDao userDao, PreferencesManager prefsManager) {
    return new NammaKatheyRepository(heroDataSource, progressDao, userDao, prefsManager);
  }
}
