package tech.minthura.mindvalley.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.minthura.mindvalley.R
import tech.minthura.mindvalley.domain.models.Episodes
import tech.minthura.mindvalley.utils.inflate

class ParentRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val newEpisodesViewType = 0
    private val courseViewType = 1
    private val seriesViewType = 2
    private val categoriesViewType = 3
    private val newEpisodesRecyclerViewAdapter = NewEpisodesRecyclerViewAdapter(Episodes(null))
    private val coursesRecyclerViewAdapter = CoursesRecyclerViewAdapter(Episodes(null))
    private val seriesRecyclerViewAdapter = SeriesRecyclerViewAdapter(Episodes(null))
    private val categoriesRecyclerViewAdapter = CategoriesRecyclerViewAdapter(Episodes(null))

    class EpisodeItemHolder(v: View) : RecyclerView.ViewHolder(v) {
        val episodesRecyclerView: RecyclerView = v.findViewById(R.id.episodes_recycler_view)
    }
    class CourseItemHolder(v: View) : RecyclerView.ViewHolder(v) {
        val coursesRecyclerView: RecyclerView = v.findViewById(R.id.courses_recycler_view)
    }
    class SeriesItemHolder(v: View) : RecyclerView.ViewHolder(v) {
        val seriesRecyclerView: RecyclerView = v.findViewById(R.id.series_recycler_view)
    }
    class CategoryItemHolder(v: View) : RecyclerView.ViewHolder(v) {
        val categoriesRecyclerView: RecyclerView = v.findViewById(R.id.categories_recycler_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            newEpisodesViewType -> {
                val inflatedView = parent.inflate(R.layout.episode_category_item, false)
                return EpisodeItemHolder(inflatedView)
            }
            courseViewType -> {
                val inflatedView = parent.inflate(R.layout.course_category_item, false)
                return CourseItemHolder(inflatedView)
            }
            seriesViewType -> {
                val inflatedView = parent.inflate(R.layout.series_category_item, false)
                return SeriesItemHolder(inflatedView)
            }
            else -> {
                val inflatedView = parent.inflate(R.layout.browse_category_item, false)
                return CategoryItemHolder(inflatedView)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is EpisodeItemHolder -> {
                holder.episodesRecyclerView.adapter = newEpisodesRecyclerViewAdapter
            }
            is CourseItemHolder -> {
                holder.coursesRecyclerView.adapter = coursesRecyclerViewAdapter
            }
            is SeriesItemHolder -> {
                holder.seriesRecyclerView.adapter = seriesRecyclerViewAdapter
            }
            is CategoryItemHolder -> {
                holder.categoriesRecyclerView.adapter = categoriesRecyclerViewAdapter
            }
        }
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 5) return categoriesViewType
        return if(position % 2 == 0) seriesViewType else courseViewType
    }
}