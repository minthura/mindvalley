package tech.minthura.mindvalley

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import tech.minthura.mindvalley.domain.ApiHelper
import tech.minthura.mindvalley.domain.services.ApiService
import javax.inject.Inject

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
@LooperMode(LooperMode.Mode.PAUSED)
class ApiServiceTest {

    @get:Rule
    var hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    lateinit var apiService: ApiService

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
    }

    @Test
    fun getEpisodesTest() : Unit = runBlocking {
        val episodes = apiService.getEpisodes()
        Assert.assertNotNull(episodes)
    }

    @Test
    fun getChannelsTest() : Unit = runBlocking {
        val channels = apiService.getChannels()
        Assert.assertNotNull(channels)
    }

    @Test
    fun getCategoriesTest() : Unit = runBlocking {
        val categories = apiService.getCategories()
        Assert.assertNotNull(categories)
    }

}