package tech.minthura.mindvalley.di.module

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import tech.minthura.mindvalley.BuildConfig
import tech.minthura.mindvalley.R
import tech.minthura.mindvalley.data.daos.CategoryDao
import tech.minthura.mindvalley.data.daos.ChannelDao
import tech.minthura.mindvalley.data.daos.EpisodeDao
import tech.minthura.mindvalley.data.daos.MediaDao
import tech.minthura.mindvalley.data.database.AppDatabase
import tech.minthura.mindvalley.domain.ApiHelper
import tech.minthura.mindvalley.domain.ApiHelperImpl
import tech.minthura.mindvalley.domain.Repository
import tech.minthura.mindvalley.domain.RepositoryImpl
import tech.minthura.mindvalley.domain.mappers.ApiMapper
import tech.minthura.mindvalley.domain.mappers.ApiMapperImpl
import tech.minthura.mindvalley.domain.services.ApiService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideApiService(@ApplicationContext context: Context) : ApiService {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        return Retrofit.Builder()
            .baseUrl(context.getString(R.string.api_base_url))
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDb(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "mvdb"
        ).build()
    }

    @Provides
    @Singleton
    fun provideEpisodeDao(appDatabase: AppDatabase): EpisodeDao {
        return appDatabase.episodesDao()
    }

    @Provides
    @Singleton
    fun provideChannelsDao(appDatabase: AppDatabase): ChannelDao {
        return appDatabase.channelsDao()
    }

    @Provides
    @Singleton
    fun provideMediasDao(appDatabase: AppDatabase): MediaDao {
        return appDatabase.mediasDao()
    }

    @Provides
    @Singleton
    fun provideCategoriesDao(appDatabase: AppDatabase): CategoryDao {
        return appDatabase.categoryDao()
    }

    @Provides
    @Singleton
    fun provideApiMapper(apiMapper: ApiMapperImpl): ApiMapper = apiMapper

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

    @Provides
    @Singleton
    fun provideRepository(repository: RepositoryImpl): Repository = repository

}
