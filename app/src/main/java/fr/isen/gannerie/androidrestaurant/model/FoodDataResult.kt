package fr.isen.gannerie.androidrestaurant.model

import com.google.gson.annotations.SerializedName

data class FoodDataResult (

  @SerializedName("data" ) var data : ArrayList<Data> = arrayListOf()

) : java.io.Serializable