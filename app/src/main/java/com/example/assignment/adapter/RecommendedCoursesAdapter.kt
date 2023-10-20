package com.example.assignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.model.RecommendedCourses

class RecommendedCoursesAdapter(
    private val context: Context,
    private val dataset: List<RecommendedCourses>
) : RecyclerView.Adapter<RecommendedCoursesAdapter.RecommendedCoursesViewHolder>() {
    class RecommendedCoursesViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.recommended_courses_title)
    }

    // create new views
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendedCoursesViewHolder {

        // inflate the layout view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_recommended_courses, parent, false)

        return RecommendedCoursesViewHolder(adapterLayout)
    }

    // get dataset size
    override fun getItemCount(): Int {
        return dataset.size
    }

    // replace contents of the list view
    override fun onBindViewHolder(holder: RecommendedCoursesViewHolder, position: Int) {
        val item = dataset[position]

        // update viewholder to show recommended courses
        holder.textView.text = context.resources.getString(item.stringResourceId)
    }
}