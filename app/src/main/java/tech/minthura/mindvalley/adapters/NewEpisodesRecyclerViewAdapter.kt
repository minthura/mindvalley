package tech.minthura.mindvalley.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tech.minthura.mindvalley.R
import tech.minthura.mindvalley.domain.models.Episodes
import tech.minthura.mindvalley.utils.inflate

class NewEpisodesRecyclerViewAdapter(private val episodes : Episodes) : RecyclerView.Adapter<NewEpisodesRecyclerViewAdapter.EpisodeItemHolder>() {

    class EpisodeItemHolder(v: View) : RecyclerView.ViewHolder(v) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeItemHolder {
        val inflatedView = parent.inflate(R.layout.episode_list_item, false)
        return EpisodeItemHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: EpisodeItemHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 6
    }
}