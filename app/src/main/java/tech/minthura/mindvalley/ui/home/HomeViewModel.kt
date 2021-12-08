package tech.minthura.mindvalley.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch
import tech.minthura.mindvalley.data.daos.EpisodeDao
import tech.minthura.mindvalley.data.entities.DbNewEpisode
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val episodeDao: EpisodeDao): ViewModel() {
    val episodes = liveData {
        episodeDao.getEpisodes().collect {
            emit(it)
        }
    }

    fun loadDummy(){
        viewModelScope.launch(Dispatchers.IO) {
            episodeDao.insert(listOf(
                DbNewEpisode(
                    "Conscious Parenting",
                    "https://assets.mindvalley.com/api/v1/assets/5bdbdd0e-3bd3-432b-b8cb-3d3556c58c94.jpg?transform=w_1080",
                    "Little Humans"
                ),
                DbNewEpisode(
                    "Raising Kids With Healthy Beliefs",
                    "https://assets.mindvalley.com/api/v1/assets/cb8c79d9-af35-4727-9c4c-6e9eee5af1c3.jpg?transform=w_1080",
                    "Little Humans"
                ),
                DbNewEpisode(
                    "Attachment Parenting: Nurturing Our Children",
                    "https://assets.mindvalley.com/api/v1/assets/9accb76c-fc05-4455-b20c-a39f947184a7.jpg?transform=w_1080",
                    "Little Humans"
                )
            ))
        }
    }
}