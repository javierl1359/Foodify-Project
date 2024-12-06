package com.example.fodify

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.SearchView
import android.widget.Toast
import android.widget.ImageButton
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class MenuPrincipalTotal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal_total)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewCategories)

        // Lista de categorías
        val categories = listOf(
            CategoryData("Desayunos", R.drawable.breakfastmenu),
            CategoryData("Bebidas", R.drawable.bebidas),
            CategoryData("Entradas", R.drawable.carta)
        )

        // Configurar el RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CategoryAdapter(categories) { category ->
            // Acción al hacer clic en una categoría
            when (category.name) {
                "Desayunos" -> {
                    val intent = Intent(this, desayunoMenu::class.java)
                    startActivity(intent)
                }
                "Bebidas" -> {
                    val intent = Intent(this, BebidasMenu::class.java)
                    startActivity(intent)
                }
                "Entradas" -> {
                    val intent = Intent(this, EntradaMenu::class.java)
                    startActivity(intent)
                }
                else -> {
                    Toast.makeText(this, "Categoría no disponible", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}