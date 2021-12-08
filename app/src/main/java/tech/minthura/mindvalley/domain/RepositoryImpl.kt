package tech.minthura.mindvalley.domain

import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.*
import retrofit2.HttpException
import tech.minthura.mindvalley.data.daos.ChannelDao
import tech.minthura.mindvalley.data.daos.EpisodeDao
import tech.minthura.mindvalley.data.daos.MediaDao
import tech.minthura.mindvalley.domain.mappers.ApiMapper
import tech.minthura.mindvalley.domain.models.*
import tech.minthura.mindvalley.domain.services.TAG
import tech.minthura.mindvalley.domain.models.Callback
import java.io.IOException
import javax.inject.Inject
import javax.net.ssl.HttpsURLConnection

class RepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val apiMapper: ApiMapper,
    private val episodeDao: EpisodeDao,
    private val channelDao: ChannelDao,
    private val mediaDao: MediaDao,
    ) : Repository {

    override suspend fun getEpisodes(callback : Callback<Episodes>) {
        coroutineLaunch({
            val episodes = apiHelper.getEpisodes()
            val map = apiMapper.mapEpisodesToDbEpisodes(episodes)
            episodeDao.deleteAll()
            episodeDao.insert(map)
            episodes
        }, callback)
    }

    override suspend fun getChannels(callback: Callback<Channels>) {
        coroutineLaunch({
            val channels = apiHelper.getChannels()
            channels.data?.let { data ->
                data.channels?.let {
                    channelDao.deleteAll()
                    mediaDao.deleteAll()
                    for (channel in it){
                        val dbChannel = apiMapper.mapChannelToDbChannel(channel)
                        val channelId = channelDao.insert(dbChannel)
                        val dbMedias = apiMapper.mapChannelToDbMedia(channel, channelId)
                        mediaDao.insert(dbMedias)
                    }
                }
            }
            channels
        }, callback)
    }

    private suspend fun <T>coroutineLaunch(suspend: suspend () -> T, callback : Callback<T>) {
        withContext(Dispatchers.IO) {
            try {
                val response = suspend()
                withContext(Dispatchers.Main){
                    callback.onSuccess(response)
                }
            } catch (e : Throwable){
                withContext(Dispatchers.Main){
                    handleApiError(e, callback)
                }
            }

        }
    }

    private fun <T>handleApiError(error: Throwable, callback : Callback<T>) {
        if (error is HttpException) {
            Log.e(TAG,error.message())
            when (error.code()) {
                HttpsURLConnection.HTTP_UNAUTHORIZED -> callback.onError(Error(Errors.UNAUTHORIZED, getErrorResponse(error)))
                HttpsURLConnection.HTTP_FORBIDDEN -> callback.onError(Error(Errors.FORBIDDEN, getErrorResponse(error)))
                HttpsURLConnection.HTTP_INTERNAL_ERROR -> callback.onError(Error(Errors.INTERNAL_SERVER_ERROR, getErrorResponse(error)))
                HttpsURLConnection.HTTP_BAD_REQUEST -> callback.onError(Error(Errors.BAD_REQUEST, getErrorResponse(error)))
                else -> callback.onError(Error(Errors.UNKNOWN, getErrorResponse(error)))
            }
        } else {
            error.printStackTrace()
            if (error.message != null){
                callback.onError(Error(Errors.UNKNOWN, ErrorResponse(error.message!!, 500)))
            } else {
                callback.onError(Error(Errors.UNKNOWN, ErrorResponse("Unknown error", 500)))
            }

        }
    }

    private fun buildErrorResponse(json : String) : ErrorResponse? {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(
            ErrorResponse::class.java
        )
        return try {
            jsonAdapter.fromJson(json)
        } catch (e : JsonDataException) {
            ErrorResponse("JsonDataException", 500)
        } catch (e : IOException) {
            ErrorResponse("IOException", 500)
        }

    }

    private fun getErrorResponse(error : HttpException) : ErrorResponse? {
        error.response()?.let { it ->
            it.errorBody()?.let {
                return buildErrorResponse(it.string())
            }
        }
        return null
    }
}