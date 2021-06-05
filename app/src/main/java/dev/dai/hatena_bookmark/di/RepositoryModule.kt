package dev.dai.hatena_bookmark.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.dai.hatena_bookmark.repository.FeedRepository
import dev.dai.hatena_bookmark.repository.FeedRepositoryImpl

@Module(includes = [ApiModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
  @Binds
  abstract fun provideFeedRepository(feedRepositoryImpl: FeedRepositoryImpl): FeedRepository
}
