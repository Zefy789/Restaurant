package fr.isen.gannerie.androidrestaurant

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    public fun on_button_entree_click(v: View) {
        val toast: Toast = Toast.makeText(this, "Commençons par le commencement !", Toast.LENGTH_LONG)
        toast.show()
        val intent = Intent(this, category_entree::class.java)
        intent.putExtra("categorie", "Entrees")
        startActivity(intent)
    }
    public fun on_button_plats_click(v: View) {
        val toast: Toast = Toast.makeText(this, "Place aux plats !", Toast.LENGTH_LONG)
        toast.show()
        val intent = Intent(this, category_plats::class.java)
        intent.putExtra("categorie", "Plats")
        startActivity(intent)
    }
    public fun on_button_desert_click(v: View) {
        val toast: Toast = Toast.makeText(this, "Le dessert !", Toast.LENGTH_LONG)
        toast.show()
        val intent = Intent(this, category_desert::class.java)
        intent.putExtra("categorie", "Dessert")
        startActivity(intent)
    }
}