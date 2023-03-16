package fr.isen.gannerie.androidrestaurant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.gannerie.androidrestaurant.databinding.ActivityCategoryBinding


class ActivityCategory : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = binding.titleCategory
        val recyclerView = binding.recyclerCategory

        title.text = intent.extras?.getString("titleCategory") ?: "No title available"

        recyclerView.layoutManager = LinearLayoutManager(this)

        val list = resources.getStringArray(R.array.dishes).toList()
        recyclerView.adapter = CategoryAdapter(list as ArrayList<String>)
    }}