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
    private var dataset: MutableList<String>
) : RecyclerView.Adapter<CourseCatalogAdapter.CourseCatalogViewHolder>() {
    // initialise filtered dataset as copy of original dataset
    private var filteredDataset: MutableList<String> = dataset.toMutableList()

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
        return filteredDataset.size
    }

    // replace contents of list view
    override fun onBindViewHolder(holder: CourseCatalogViewHolder, position: Int) {
        val item = filteredDataset[position]

        // update viewholder to how course catalog
        holder.textView.text = item
    }

    // update adapter data
    fun updateData(newData: List<String>) {
        filteredDataset = newData.toMutableList()
        notifyDataSetChanged()
    }

    // set adapter data
    fun setData(newData: List<String>) {
        dataset.addAll(newData)
        filteredDataset.addAll(newData)
        notifyDataSetChanged()
    }

    // filter function
    fun filter(query: String) {
        if (query.isEmpty()) {
            // if query is empty, show all items
            filteredDataset.clear()
            filteredDataset.addAll(dataset)
        } else {
            filteredDataset.clear()
            for (item in dataset) {
                if (item.contains(query, ignoreCase = true)) {
                    filteredDataset.add(item)
                }
            }
            updateData(filteredDataset)
        }
        notifyDataSetChanged()
    }
}