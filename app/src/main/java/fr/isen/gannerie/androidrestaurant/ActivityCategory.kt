package fr.isen.gannerie.androidrestaurant

import CategoryAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.gannerie.androidrestaurant.databinding.ActivityCategoryBinding
import fr.isen.gannerie.androidrestaurant.FoodDataResult
import org.json.JSONObject

class ActivityCategory : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = binding.titleCategory
        val recyclerView = binding.recyclerCategory

        title.text = intent.extras?.getString("titleCategory")?:"No title available"

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = CategoryAdapter(arrayListOf()){
            val intent = Intent(this@ActivityCategory, DetailsActivity::class.java)
            intent.putExtra("item", it)
            startActivity(intent)
        }

        loadDishesFromAPI()
    }
    private fun loadDishesFromAPI() {
        Volley.newRequestQueue(this)

        val url = "http://test.api.catering.bluecodegames.com/menu"
        val jsonObject = JSONObject()
        jsonObject.put("id_shop", "1")
        val jsonRequest = JsonObjectRequest(Request.Method.POST, url, jsonObject, {
            Log.w("CategoryActivity", "response : $it")
            handleAPIData(it.toString())
        }, {
            Log.w("CategoryActivity", "erreur : $it")
            Toast.makeText(this@ActivityCategory, "Erreur API", Toast.LENGTH_SHORT).show()
        })
        Volley.newRequestQueue(this).add(jsonRequest)
    }

    private fun handleAPIData(data: String) {
        val dishesResult = Gson().fromJson(data, FoodDataResult::class.java)
        val dishes = dishesResult.data.firstOrNull {
            it.nameFr == (intent.extras?.getString("titleCategory") ?: "No title available")
        }

        val adapter = binding.recyclerCategory.adapter as CategoryAdapter
        if (dishes != null) {
            adapter.refreshList(dishes.items)
        }
    }
}

