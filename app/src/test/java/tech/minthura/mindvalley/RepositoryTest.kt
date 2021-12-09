package tech.minthura.mindvalley

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import tech.minthura.mindvalley.domain.Repository
import tech.minthura.mindvalley.domain.models.Callback
import tech.minthura.mindvalley.domain.models.Categories
import tech.minthura.mindvalley.domain.models.Channels
import tech.minthura.mindvalley.domain.models.Episodes
import javax.inject.Inject

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
@LooperMode(LooperMode.Mode.PAUSED)
class RepositoryTest {

    @get:Rule
    var hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: Repository


    @Before
    fun setUp() {
        hiltAndroidRule.inject()
    }

    @Test
    fun getEpisodesTest() : Unit = runBlocking {
        val callback : Callback<Episodes> = mock()
        repository.getEpisodes(callback)
        Mockito.verify(callback).onSuccess(any())
    }

    @Test
    fun getChannelsTest() : Unit = runBlocking {
        val callback : Callback<Channels> = mock()
        repository.getChannels(callback)
        Mockito.verify(callback).onSuccess(any())
    }

    @Test
    fun getCategoriesTest() : Unit = runBlocking {
        val callback : Callback<Categories> = mock()
        repository.getCategories(callback)
        Mockito.verify(callback).onSuccess(any())
    }

}