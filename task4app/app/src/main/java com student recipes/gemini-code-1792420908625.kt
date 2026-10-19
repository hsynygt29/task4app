package com.student.recipes

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.tvRecipes)
        
        try {
            val jsonString = assets.open("recipes.json").bufferedReader().use { it.readText() }
            val jsonArray = JSONArray(jsonString)
            
            val builder = StringBuilder()
            for (i in 0 until jsonArray.length()) {
                val obj = jsonArray.getJSONObject(i)
                builder.append("Yemek: ").append(obj.getString("name")).append("\n")
                builder.append("Kategori: ").append(obj.getString("category")).append("\n")
                builder.append("Kalori: ").append(obj.getInt("calories")).append(" kcal\n")
                builder.append("----------------------------\n\n")
            }
            textView.text = builder.toString()
        } catch (e: Exception) {
            textView.text = "Tarifler yüklenirken hata oluştu: ${e.message}"
        }
    }
}