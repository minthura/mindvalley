package tech.minthura.mindvalley

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import tech.minthura.mindvalley.adapters.ParentRecyclerViewAdapter

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class HomeFragmentInstrumentedTest {

    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkTitleIsDisplayed() {
        Espresso.onView(withId(R.id.textView))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun checkTitleIsCorrect() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Espresso.onView(withId(R.id.textView))
            .check(ViewAssertions.matches(withText(appContext.getString(R.string.title_channels))))
    }

    @Test
    fun checkRecyclerViewIsDisplayed() {
        Espresso.onView(withId(R.id.recycler_view))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun checkRecyclerViewSwipeRefresh() {
        Espresso.onView(withId(R.id.recycler_view)).perform(
            ViewActions.swipeDown()
        )
    }

    @Test
    fun checkRecyclerViewBrowseByCategoriesTitle() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Espresso.onView(withId(R.id.recycler_view)).perform(
            RecyclerViewActions.scrollTo<ParentRecyclerViewAdapter.CategoryItemHolder>(hasDescendant(
                withText(appContext.getString(R.string.title_browse_by_categories))))
        )
    }

    @Test
    fun checkRecyclerViewNewEpisodesTitle() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Espresso.onView(withId(R.id.recycler_view)).perform(
            RecyclerViewActions.scrollTo<ParentRecyclerViewAdapter.EpisodeItemHolder>(hasDescendant(
                withText(appContext.getString(R.string.title_new_episodes))))
        )
    }

    @Test
    fun checkRecyclerViewNewEpisodesScroll() {
        Espresso.onView(withId(R.id.episodes_recycler_view)).perform(
            RecyclerViewActions.scrollToPosition<ParentRecyclerViewAdapter.EpisodeItemHolder>(
                5
        ))
    }

    @Test
    fun checkRecyclerViewCoursesScroll() {
        Espresso.onView(withId(R.id.courses_recycler_view)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                5
            ))
    }

}
