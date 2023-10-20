package com.example.assignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R

class CourseCatalogAdapter(
    private val context: Context,
    private val dataset: MutableList<String>
) : RecyclerView.Adapter<CourseCatalogAdapter.CourseCatalogViewHolder>() {
    class CourseCatalogViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.courses_catalog_title)
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseCatalogViewHolder {

        // inflate the layout view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.course_catalog, parent, false)

        return CourseCatalogViewHolder(adapterLayout)
    }

    // get dataset size
    override fun getItemCount(): Int {
        return dataset.size
    }

    // replace contents of list view
    override fun onBindViewHolder(holder: CourseCatalogViewHolder, position: Int) {
        val item = dataset[position]

        // update viewholder to how course catalog
        holder.textView.text = item
    }

    // update adapter data
    fun updateData(newData: List<String>) {
        dataset.clear()
        dataset.addAll(newData)
        notifyDataSetChanged()
    }
}