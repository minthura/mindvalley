package tech.minthura.mindvalley.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.minthura.mindvalley.R
import tech.minthura.mindvalley.domain.models.Episodes
import tech.minthura.mindvalley.utils.inflate

class SeriesRecyclerViewAdapter(private val episodes : Episodes) : RecyclerView.Adapter<SeriesRecyclerViewAdapter.SeriesItemHolder>() {

    class SeriesItemHolder(v: View) : RecyclerView.ViewHolder(v) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesItemHolder {
        val inflatedView = parent.inflate(R.layout.series_list_item, false)
        return SeriesItemHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: SeriesItemHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 6
    }
}