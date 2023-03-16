package fr.isen.gannerie.androidrestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(val dishes: ArrayList<String>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dishName : TextView = view.findViewById(R.id.dishName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_cell, parent, false)
        return CategoryViewHolder(view)
    }
    override fun getItemCount(): Int = dishes.size

    override fun onBindViewHolder (holder: CategoryViewHolder, position: Int) {
        val dish = dishes[position]
        holder.dishName.text = dish

        holder.itemView.setOnClickListener{

        }

    }
}