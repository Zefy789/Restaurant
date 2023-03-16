package fr.isen.gannerie.androidrestaurant

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.gson.GsonBuilder
import fr.isen.bernet.androidrestaurant.Items
import fr.isen.gannerie.androidrestaurant.databinding.ActivityDetailsBinding
import fr.isen.gannerie.androidrestaurant.Items
import org.json.JSONObject
import java.io.File

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var dish: Items
    private var absTopSubMenus: Menu? = null

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        absTopSubMenus = menu
        menuInflater.inflate(R.menu.main_menu, menu)

        return true
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dish = intent.extras?.getSerializable("item") as Items

        binding.mealTitle.text = dish.nameFr

        for (i in dish.ingredients) {
            binding.ingredientsText.text = binding.ingredientsText.text.toString() + i.nameFr + ", "
        }

        binding.buttonPlus.setOnClickListener {
            clickOnButtonPlus()
            refreshTotalPrice()
        }

        binding.buttonMinus.setOnClickListener {
            clickOnButtonMinus()
            refreshTotalPrice()
        }

        binding.totalPrice.setOnClickListener {
            refreshTotalPrice()
            addToJSON()
        }

        val viewPager = binding.dishPhoto
        val adapter = CustomDishAdapter(dish.images)

        viewPager.adapter = adapter
    }

    @SuppressLint("SetTextI18n")
    fun clickOnButtonPlus() {
        binding.dishNumber.text = (binding.dishNumber.text.toString().toInt() + 1).toString()
    }

    private fun clickOnButtonMinus() {
        if (binding.dishNumber.text.toString().toInt() > 0) {
            binding.dishNumber.text = (binding.dishNumber.text.toString().toInt() - 1).toString()
        }
        else {
            binding.dishNumber.text = "0"
        }
    }

    @SuppressLint("SetTextI18n")
    fun refreshTotalPrice() {
        binding.totalPrice.text = "Total : " + (binding.dishNumber.text.toString().toInt() * (dish.prices[0].price?.toInt()
            ?: 999)).toString() + " €"
    }

    private fun addToJSON() {
        val file = File(filesDir, "cart.json")
        val jsonCart = JSONObject()
        jsonCart.put("name", dish.nameFr)
        val price = binding.dishNumber.text.toString().toInt() * (dish.prices[0].price?.toInt()?: 999)
        jsonCart.put("price", price)
        val cart = GsonBuilder().setPrettyPrinting().create().toJson(jsonCart)
        file.writeText(cart)

        val notif = Snackbar.make(binding.root, "Commande mise à jour", Snackbar.LENGTH_LONG)
        notif.show()
    }
}