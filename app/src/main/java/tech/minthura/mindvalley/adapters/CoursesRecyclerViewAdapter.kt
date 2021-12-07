package tech.minthura.mindvalley.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.minthura.mindvalley.R
import tech.minthura.mindvalley.domain.models.Episodes
import tech.minthura.mindvalley.utils.inflate

class CoursesRecyclerViewAdapter(private val episodes : Episodes) : RecyclerView.Adapter<CoursesRecyclerViewAdapter.CourseItemHolder>() {

    class CourseItemHolder(v: View) : RecyclerView.ViewHolder(v) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseItemHolder {
        val inflatedView = parent.inflate(R.layout.course_list_item, false)
        return CourseItemHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: CourseItemHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 6
    }
}