package com.nammakathe.core.data.repository;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
    "KotlinInternalInJava"
})
public final class HeroDataSource_Factory implements Factory<HeroDataSource> {
  private final Provider<Context> contextProvider;

  public HeroDataSource_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public HeroDataSource get() {
    return newInstance(contextProvider.get());
  }

  public static HeroDataSource_Factory create(Provider<Context> contextProvider) {
    return new HeroDataSource_Factory(contextProvider);
  }

  public static HeroDataSource newInstance(Context context) {
    return new HeroDataSource(context);
  }
}
