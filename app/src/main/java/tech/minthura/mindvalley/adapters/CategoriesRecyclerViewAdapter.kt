package tech.minthura.mindvalley.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tech.minthura.mindvalley.R
import tech.minthura.mindvalley.adapters.models.ParentRVListItem
import tech.minthura.mindvalley.domain.models.Episodes
import tech.minthura.mindvalley.utils.inflate

class CategoriesRecyclerViewAdapter(private val items : List<ParentRVListItem>) : RecyclerView.Adapter<CategoriesRecyclerViewAdapter.CourseItemHolder>() {

    class CourseItemHolder(v: View) : RecyclerView.ViewHolder(v) {
        val title: TextView = v.findViewById(R.id.title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseItemHolder {
        val inflatedView = parent.inflate(R.layout.category_list_item, false)
        return CourseItemHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: CourseItemHolder, position: Int) {
        holder.title.text = items[position].title
    }

    override fun getItemCount(): Int {
        return items.size
    }
}