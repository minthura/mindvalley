package tech.minthura.mindvalley

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import tech.minthura.mindvalley.data.daos.CategoryDao
import tech.minthura.mindvalley.data.daos.ChannelDao
import tech.minthura.mindvalley.data.daos.EpisodeDao
import tech.minthura.mindvalley.data.daos.MediaDao
import tech.minthura.mindvalley.data.database.AppDatabase
import tech.minthura.mindvalley.data.entities.DbCategory
import tech.minthura.mindvalley.data.entities.DbChannel
import tech.minthura.mindvalley.data.entities.DbMedia
import tech.minthura.mindvalley.data.entities.DbNewEpisode
import java.io.IOException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
@LooperMode(LooperMode.Mode.PAUSED)
class RoomDbTest {

    private lateinit var db: AppDatabase
    private lateinit var episodeDao: EpisodeDao
    private lateinit var channelDao: ChannelDao
    private lateinit var mediaDao: MediaDao
    private lateinit var categoryDao: CategoryDao

    @get:Rule
    var hiltAndroidRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).allowMainThreadQueries().build()
        episodeDao = db.episodesDao()
        channelDao = db.channelsDao()
        mediaDao = db.mediasDao()
        categoryDao = db.categoryDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun episodeDaoInsertTest() : Unit = runBlocking {
        val latch = CountDownLatch(1)
        val job = launch(Dispatchers.IO) {
            db.clearAllTables()
            episodeDao.insert(listOf(
                DbNewEpisode("Title1", "Url1", "ChannelTitle1"),
                DbNewEpisode("Title2", "Url2", "ChannelTitle2"),
                DbNewEpisode("Title3", "Url3", "ChannelTitle3"),
            )
            )
            episodeDao.getEpisodes().collect {
                Assert.assertTrue(it.size == 3)
                latch.countDown()
            }
        }
        latch.await(5, TimeUnit.SECONDS)
        job.cancel()
    }

    @Test
    @Throws(Exception::class)
    fun episodeDaoGetEpisodesTest() : Unit = runBlocking {
        val latch = CountDownLatch(1)
        val job = launch(Dispatchers.IO) {
            db.clearAllTables()
            episodeDao.insert(listOf(
                DbNewEpisode("Title1", "Url1", "ChannelTitle1"),
                DbNewEpisode("Title2", "Url2", "ChannelTitle2"),
                DbNewEpisode("Title3", "Url3", "ChannelTitle3"),
            )
            )
            episodeDao.getEpisodes().collect {
                Assert.assertTrue(it.size == 3 && it[0].title == "Title1" && it[1].title == "Title2" && it[2].title == "Title3")
                latch.countDown()
            }
        }
        latch.await(5, TimeUnit.SECONDS)
        job.cancel()
    }

    @Test
    @Throws(Exception::class)
    fun episodeDaoDeleteAllTest() : Unit = runBlocking {
        val latch = CountDownLatch(1)
        val job = launch(Dispatchers.IO) {
            episodeDao.deleteAll()
            episodeDao.getEpisodes().collect {
                Assert.assertTrue(it.isEmpty())
                latch.countDown()
            }
        }
        latch.await(5, TimeUnit.SECONDS)
        job.cancel()
    }

    @Test
    @Throws(Exception::class)
    fun channelDaoInsertTest() : Unit = runBlocking {
        val latch = CountDownLatch(1)
        val job = launch(Dispatchers.IO) {
            db.clearAllTables()
            channelDao.insert(DbChannel("Title1", 1, "Url1", 0))
            channelDao.getChannelsWithMedias().collect {
                Assert.assertTrue(it.size == 1 && it[0].dbChannel.title == "Title1")
                latch.countDown()
            }
        }
        latch.await(5, TimeUnit.SECONDS)
        job.cancel()
    }

    @Test
    @Throws(Exception::class)
    fun channelDaoGetChannelsWithMediasTest() : Unit = runBlocking {
        val latch = CountDownLatch(1)
        val job = launch(Dispatchers.IO) {
            db.clearAllTables()
            val id = channelDao.insert(DbChannel("Title1", 1, "Url1", 0))
            mediaDao.insert(
                listOf(
                    DbMedia("MTitle1", "MUrl1", id),
                    DbMedia("MTitle2", "MUrl2", id),
                    DbMedia("MTitle3", "MUrl3", id)
                )
            )
            channelDao.getChannelsWithMedias().collect {
                Assert.assertTrue(it.size == 1 && it[0].dbMedia.size == 3 && it[0].dbMedia[0].title == "MTitle1" && it[0].dbMedia[1].title == "MTitle2" && it[0].dbMedia[2].title == "MTitle3")
                latch.countDown()
            }
        }
        latch.await(5, TimeUnit.SECONDS)
        job.cancel()
    }

    @Test
    @Throws(Exception::class)
    fun channelDaoDeleteAllTest() : Unit = runBlocking {
        val latch = CountDownLatch(1)
        val job = launch(Dispatchers.IO) {
            channelDao.deleteAll()
            channelDao.getChannelsWithMedias().collect {
                Assert.assertTrue(it.isEmpty())
                latch.countDown()
            }
        }
        latch.await(5, TimeUnit.SECONDS)
        job.cancel()
    }

    @Test
    @Throws(Exception::class)
    fun mediaDaoInsertTest() : Unit = runBlocking {
        val latch = CountDownLatch(1)
        val job = launch(Dispatchers.IO) {
            db.clearAllTables()
            val ids = mediaDao.insert(
                listOf(
                    DbMedia("MTitle1", "MUrl1", 1),
                    DbMedia("MTitle2", "MUrl2", 2),
                    DbMedia("MTitle3", "MUrl3", 3)
                )
            )
            Assert.assertTrue(ids.size == 3)
            latch.countDown()
        }
        latch.await(5, TimeUnit.SECONDS)
        job.cancel()
    }

    @Test
    @Throws(Exception::class)
    fun mediaDaoDeleteAllTest() : Unit = runBlocking {
        val latch = CountDownLatch(1)
        val job = launch(Dispatchers.IO) {
            val ids = mediaDao.insert(
                listOf(
                    DbMedia("MTitle1", "MUrl1", 1),
                    DbMedia("MTitle2", "MUrl2", 2),
                    DbMedia("MTitle3", "MUrl3", 3)
                )
            )
            val deletedRows = mediaDao.deleteAll()
            Assert.assertTrue(ids.size == deletedRows)
            latch.countDown()
        }
        latch.await(5, TimeUnit.SECONDS)
        job.cancel()
    }

    @Test
    @Throws(Exception::class)
    fun categoryDaoInsertTest() : Unit = runBlocking {
        val latch = CountDownLatch(1)
        val job = launch(Dispatchers.IO) {
            db.clearAllTables()
            categoryDao.insert(listOf(
                DbCategory("Title1"),
                DbCategory("Title2"),
                DbCategory("Title3")
                )
            )
            categoryDao.getCategories().collect {
                Assert.assertTrue(it.size == 3)
                latch.countDown()
            }
        }
        latch.await(5, TimeUnit.SECONDS)
        job.cancel()
    }

    @Test
    @Throws(Exception::class)
    fun categoryDaoGetCategoriesTest() : Unit = runBlocking {
        val latch = CountDownLatch(1)
        val job = launch(Dispatchers.IO) {
            db.clearAllTables()
            categoryDao.insert(listOf(
                DbCategory("Title1"),
                DbCategory("Title2"),
                DbCategory("Title3")
                )
            )
            categoryDao.getCategories().collect {
                Assert.assertTrue(it.size == 3 && it[0].name == "Title1" && it[1].name == "Title2" && it[2].name == "Title3")
                latch.countDown()
            }
        }
        latch.await(5, TimeUnit.SECONDS)
        job.cancel()
    }

    @Test
    @Throws(Exception::class)
    fun categoryDaoDeleteAllTest() : Unit = runBlocking {
        val latch = CountDownLatch(1)
        val job = launch(Dispatchers.IO) {
            categoryDao.deleteAll()
            categoryDao.getCategories().collect {
                Assert.assertTrue(it.isEmpty())
                latch.countDown()
            }
        }
        latch.await(5, TimeUnit.SECONDS)
        job.cancel()
    }

}