package fr.isen.gannerie.androidrestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.TextView
import fr.isen.gannerie.androidrestaurant.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEntree.setOnClickListener {
            switchActivity(binding.btnEntree)
        }

        binding.btnPlats.setOnClickListener {
            switchActivity(binding.btnPlats)
        }

        binding.btnDesert.setOnClickListener {
            switchActivity(binding.btnDesert)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Destroy", "Activity $this has been destroyed")
    }

    private fun switchActivity(textView: TextView){
        val intent = Intent(this@HomeActivity, ActivityCategory::class.java)
        intent.putExtra("titleCategory", textView.text)
        startActivity(intent)
    }
}