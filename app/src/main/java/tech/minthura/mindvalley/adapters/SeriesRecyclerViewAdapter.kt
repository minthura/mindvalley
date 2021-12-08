package tech.minthura.mindvalley.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import tech.minthura.mindvalley.R
import tech.minthura.mindvalley.adapters.models.ParentRVListItem
import tech.minthura.mindvalley.domain.models.Episodes
import tech.minthura.mindvalley.utils.inflate

class SeriesRecyclerViewAdapter(private val items : List<ParentRVListItem>) : RecyclerView.Adapter<SeriesRecyclerViewAdapter.SeriesItemHolder>() {

    class SeriesItemHolder(v: View) : RecyclerView.ViewHolder(v) {
        val imageView: ImageView = v.findViewById(R.id.image_view)
        val title: TextView = v.findViewById(R.id.title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesItemHolder {
        val inflatedView = parent.inflate(R.layout.series_list_item, false)
        return SeriesItemHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: SeriesItemHolder, position: Int) {
        Glide.with(holder.imageView)
            .load(items[position].imageUrl)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(holder.imageView);
        holder.title.text = items[position].title
    }

    override fun getItemCount(): Int {
        return items.size
    }
}