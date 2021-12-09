package tech.minthura.mindvalley.adapters

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import tech.minthura.mindvalley.R
import tech.minthura.mindvalley.adapters.models.ParentRVListItem
import tech.minthura.mindvalley.utils.inflate

class NewEpisodesRecyclerViewAdapter(private val episodes : List<ParentRVListItem>) : RecyclerView.Adapter<NewEpisodesRecyclerViewAdapter.EpisodeItemHolder>() {

    class EpisodeItemHolder(v: View) : RecyclerView.ViewHolder(v) {
        val imageView: ImageView = v.findViewById(R.id.image_view)
        val title: TextView = v.findViewById(R.id.title)
        val channelTitle: TextView = v.findViewById(R.id.channel_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeItemHolder {
        val inflatedView = parent.inflate(R.layout.episode_list_item, false)
        return EpisodeItemHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: EpisodeItemHolder, position: Int) {
        Glide.with(holder.imageView)
            .load(episodes[position].imageUrl)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(holder.imageView);
        holder.title.text = episodes[position].title
        holder.channelTitle.text = episodes[position].channelTitle
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int {
        return episodes.size
    }
}