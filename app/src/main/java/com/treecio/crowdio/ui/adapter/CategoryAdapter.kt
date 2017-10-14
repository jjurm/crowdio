package com.treecio.crowdio.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.treecio.crowdio.R
import com.treecio.crowdio.model.Category

class CategoryAdapter(context: Context) : ArrayAdapter<Category>(context, R.layout.list_item_category, Category.values()) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // Get the data item for this position
        val category = getItem(position)
        // Check if an existing view is being reused, otherwise inflate the view
        val newView = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_category, parent, false)
        // Lookup view for data population
        val image = newView.findViewById<ImageView>(R.id.list_item_image)
        val text = newView.findViewById<TextView>(R.id.list_item_text)
        // Populate the data into the template view using the data object
        image.setImageResource(category.icon)
        image.setColorFilter(ContextCompat.getColor(context, category.color), android.graphics.PorterDuff.Mode.MULTIPLY)
        text.setText(category.title)
        // Return the completed view to render on screen
        return newView
    }


}
