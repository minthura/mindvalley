package tech.minthura.mindvalley.domain.services

import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.HttpException
import tech.minthura.mindvalley.domain.models.ErrorResponse
import tech.minthura.mindvalley.domain.models.Errors
import java.io.IOException
import javax.net.ssl.HttpsURLConnection
import tech.minthura.mindvalley.domain.models.Error

const val TAG = "Mindvalley"

open class BaseService {
    open fun handleApiError(error: Throwable, onError : (error : Error) -> Unit) {
        if (error is HttpException) {
            Log.e(TAG,error.message())
            when (error.code()) {
                HttpsURLConnection.HTTP_UNAUTHORIZED -> onError(Error(Errors.UNAUTHORIZED, getErrorResponse(error)))
                HttpsURLConnection.HTTP_FORBIDDEN -> onError(Error(Errors.FORBIDDEN, getErrorResponse(error)))
                HttpsURLConnection.HTTP_INTERNAL_ERROR -> onError(Error(Errors.INTERNAL_SERVER_ERROR, getErrorResponse(error)))
                HttpsURLConnection.HTTP_BAD_REQUEST -> onError(Error(Errors.BAD_REQUEST, getErrorResponse(error)))
                else -> onError(Error(Errors.UNKNOWN, getErrorResponse(error)))
            }
        } else {
            error.printStackTrace()
            if (error.message != null){
                onError(Error(Errors.UNKNOWN, ErrorResponse(error.message!!, 500)))
            } else {
                onError(Error(Errors.UNKNOWN, ErrorResponse("Unknown error", 500)))
            }

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

}