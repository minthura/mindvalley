package tech.minthura.mindvalley.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tech.minthura.mindvalley.R
import tech.minthura.mindvalley.adapters.models.ParentRVData
import tech.minthura.mindvalley.adapters.models.ParentRVDataType
import tech.minthura.mindvalley.adapters.models.ParentRVListItem
import tech.minthura.mindvalley.data.entities.ChannelWithMedias
import tech.minthura.mindvalley.data.entities.DbChannel
import tech.minthura.mindvalley.data.entities.DbNewEpisode
import tech.minthura.mindvalley.domain.models.Episodes
import tech.minthura.mindvalley.utils.inflate

class ParentRecyclerViewAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val newEpisodesViewType = 0
    private val courseViewType = 1
    private val seriesViewType = 2
    private val categoriesViewType = 3

    private val items = mutableListOf<ParentRVData>()

//    private val newEpisodesRecyclerViewAdapter = NewEpisodesRecyclerViewAdapter(mutableListOf())
//    private val coursesRecyclerViewAdapter = CoursesRecyclerViewAdapter(Episodes(null))
//    private val seriesRecyclerViewAdapter = SeriesRecyclerViewAdapter(Episodes(null))
    private val categoriesRecyclerViewAdapter = CategoriesRecyclerViewAdapter(Episodes(null))


    class EpisodeItemHolder(v: View) : RecyclerView.ViewHolder(v) {
        val episodesRecyclerView: RecyclerView = v.findViewById(R.id.episodes_recycler_view)
    }
    class CourseItemHolder(v: View) : RecyclerView.ViewHolder(v) {
        val imageView: ImageView = v.findViewById(R.id.image_view)
        val coursesRecyclerView: RecyclerView = v.findViewById(R.id.courses_recycler_view)
        val title: TextView = v.findViewById(R.id.title)
        val mediaCount: TextView = v.findViewById(R.id.media_count)
    }
    class SeriesItemHolder(v: View) : RecyclerView.ViewHolder(v) {
        val imageView: ImageView = v.findViewById(R.id.image_view)
        val seriesRecyclerView: RecyclerView = v.findViewById(R.id.series_recycler_view)
        val title: TextView = v.findViewById(R.id.title)
        val mediaCount: TextView = v.findViewById(R.id.media_count)
    }
    class CategoryItemHolder(v: View) : RecyclerView.ViewHolder(v) {
        val categoriesRecyclerView: RecyclerView = v.findViewById(R.id.categories_recycler_view)
    }

    fun setEpisodes(episodes: List<DbNewEpisode>){
        val data = mutableListOf<ParentRVListItem>()
        for (episode in episodes){
            data.add(ParentRVListItem(episode.title, episode.channelTitle, episode.assetUrl))
        }
        val filter = items.filter { it.type == ParentRVDataType.EPISODES }
        items.removeAll(filter)
        items.add(ParentRVData(ParentRVDataType.EPISODES, null, null, null, data))
        notifyDataSetChanged()
    }

    fun setChannels(channels: List<ChannelWithMedias>){
        val filter1 = items.filter { it.type == ParentRVDataType.COURSE }
        val filter2 = items.filter { it.type == ParentRVDataType.SERIES }
        items.removeAll(filter1)
        items.removeAll(filter2)
        for (channelWithMedias in channels){
            val data = mutableListOf<ParentRVListItem>()
            for (media in channelWithMedias.dbMedia){
                data.add(ParentRVListItem(media.title, null, media.assetUrl))
            }
            if (channelWithMedias.dbChannel.type == 0){
                items.add(ParentRVData( ParentRVDataType.COURSE, channelWithMedias.dbChannel.title, "${channelWithMedias.dbChannel.mediaCount} episodes", channelWithMedias.dbChannel.iconAsset, data))
            } else {
                items.add(ParentRVData( ParentRVDataType.SERIES, channelWithMedias.dbChannel.title, "${channelWithMedias.dbChannel.mediaCount} series", channelWithMedias.dbChannel.iconAsset, data))
            }
        }
        notifyDataSetChanged()
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
                holder.episodesRecyclerView.adapter = NewEpisodesRecyclerViewAdapter(items[position].data)
            }
            is CourseItemHolder -> {
                holder.coursesRecyclerView.adapter = CoursesRecyclerViewAdapter(items[position].data)
                Glide.with(holder.imageView).load(items[position].iconAsset).error(R.mipmap.ic_launcher).centerCrop().into(holder.imageView);
                holder.title.text = items[position].title
                holder.mediaCount.text = items[position].mediaCount
            }
            is SeriesItemHolder -> {
                holder.seriesRecyclerView.adapter = SeriesRecyclerViewAdapter(items[position].data)
                Glide.with(holder.imageView).load(items[position].iconAsset).error(R.mipmap.ic_launcher).centerCrop().into(holder.imageView);
                holder.title.text = items[position].title
                holder.mediaCount.text = items[position].mediaCount
            }
            is CategoryItemHolder -> {
                holder.categoriesRecyclerView.adapter = categoriesRecyclerViewAdapter
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(items[position].type) {
            ParentRVDataType.EPISODES -> newEpisodesViewType
            ParentRVDataType.COURSE -> courseViewType
            ParentRVDataType.SERIES -> seriesViewType
            else -> categoriesViewType
        }
    }

}