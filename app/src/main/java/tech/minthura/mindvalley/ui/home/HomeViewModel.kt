package tech.minthura.mindvalley.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch
import tech.minthura.mindvalley.data.daos.CategoryDao
import tech.minthura.mindvalley.data.daos.ChannelDao
import tech.minthura.mindvalley.data.daos.EpisodeDao
import tech.minthura.mindvalley.data.entities.DbNewEpisode
import tech.minthura.mindvalley.domain.Repository
import tech.minthura.mindvalley.domain.models.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val episodeDao: EpisodeDao,
    private val channelDao: ChannelDao,
    private val categoryDao: CategoryDao,
    private val repository: Repository
    ): ViewModel() {

    val episodes = liveData {
        episodeDao.getEpisodes().collect {
            emit(it)
        }
    }

    val channels = liveData {
        channelDao.getChannelsWithMedias().collect {
            emit(it)
        }
    }

    val categories = liveData {
        categoryDao.getCategories().collect {
            emit(it)
        }
    }

    fun getEpisodes(){
        viewModelScope.launch {
            repository.getEpisodes(callback = object : Callback<Episodes> {
                override fun onSuccess(t: Episodes) {
                    Log.d("HomeViewModel", "onSuccess getEpisodes")
                }
                override fun onError(error: Error) {
                    Log.e("HomeViewModel", error.toString())
                }
            })
        }
    }

    fun getChannels(){
        viewModelScope.launch {
            repository.getChannels(callback = object : Callback<Channels> {
                override fun onSuccess(t: Channels) {
                    Log.d("HomeViewModel", "onSuccess getChannels")
                }
                override fun onError(error: Error) {
                    Log.e("HomeViewModel", error.toString())
                }
            })
        }
    }

    fun getCategories(){
        viewModelScope.launch {
            repository.getCategories(callback = object : Callback<Categories> {
                override fun onSuccess(t: Categories) {
                    Log.d("HomeViewModel", "onSuccess getCategories")
                }
                override fun onError(error: Error) {
                    Log.e("HomeViewModel", error.toString())
                }
            })
        }
    }

}
