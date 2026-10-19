package com.student.recipes

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvRecipes = findViewById<TextView>(R.id.tvRecipes)

        try {
            val jsonString = assets.open("recipes.json").bufferedReader().use { it.readText() }
            val jsonArray = JSONArray(jsonString)
            val builder = StringBuilder()

            for (i in 0 until jsonArray.length()) {
                val item = jsonArray.getJSONObject(i)
                val name = item.getString("name")
                val category = item.getString("category")
                val calories = item.getInt("calories")
                val isVegan = if (item.getBoolean("vegan")) "Evet" else "Hayır"
                
                builder.append("🍲 Yemek: $name\n")
                builder.append(" Kategori: $category\n")
                builder.append(" Kalori: $calories kcal\n")
                builder.append(" Vegan: $isVegan\n\n")
            }

            tvRecipes.text = builder.toString()
        } catch (e: Exception) {
            tvRecipes.text = "Hata oluştu: ${e.message}"
        }
    }
}
