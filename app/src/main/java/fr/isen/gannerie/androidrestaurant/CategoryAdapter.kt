import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.bernet.androidrestaurant.Items
import fr.isen.gannerie.androidrestaurant.R
import fr.isen.gannerie.androidrestaurant.Data
import fr.isen.gannerie.androidrestaurant.Items

class CategoryAdapter(var mList: ArrayList<Items>, val onItemClickListener: (mealTitle: Items) -> Unit) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_cell, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]

        holder.textView.text = itemsViewModel.nameFr

        holder.itemView.setOnClickListener() {
            onItemClickListener(itemsViewModel)
        }

        holder.price.text = itemsViewModel.prices[0].price + " €"

        val firstImage = itemsViewModel.images[0]
        if (firstImage.isNotEmpty()) {
            Picasso.get().load(firstImage).resize(150,150).into(holder.images)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.cardTitle)
        val price: TextView = itemView.findViewById(R.id.priceView)
        val images: ImageView = itemView.findViewById(R.id.imageView)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshList(dishesFromAPI: ArrayList<Items>) {
        mList = dishesFromAPI
        notifyDataSetChanged()
    }
}
