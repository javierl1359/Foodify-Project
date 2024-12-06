package com.example.fodify

import android.os.Bundle
import android.view.View
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.Toast
import android.widget.ImageButton
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import java.util.Locale

class PrincipalFoodMenu : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var buttonDrawerToggle: ImageButton
    private lateinit var navigationView: NavigationView
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var mList = ArrayList<FoodData>()
    private lateinit var adapter: FoodAdapter
    lateinit var menuBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal_food_menu)

        val tvResult = findViewById<TextView>(R.id.tvResult)
        val uname: String = intent.extras?.getString("EXTRA_NAME").orEmpty()
        tvResult.text = "Hola $uname, ¿Qué quieres comer hoy?"

        recyclerView = findViewById(R.id.cardView)
        searchView = findViewById(R.id.searchView)
        menuBtn = findViewById(R.id.menu_btn)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        addDataToList()
        adapter = FoodAdapter(mList)
        recyclerView.adapter = adapter

        menuBtn.setOnClickListener {
                // Lógica para iniciar sesión
                Log.i("PrincipalFoodMenu", "Boton de menu pulsado")
                val intent = Intent(this, MenuPrincipalTotal::class.java)
                startActivity(intent)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })

        // Inicializar vistas
        drawerLayout = findViewById(R.id.drawerlayout)
        buttonDrawerToggle = findViewById(R.id.buttonDrawerToggle)
        navigationView = findViewById(R.id.navigationView)

        // Configurar el botón para abrir el DrawerLayout
        buttonDrawerToggle.setOnClickListener {
            drawerLayout.open()
        }

        // Configurar el listener para los elementos del NavigationView
        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_account -> {
                    Toast.makeText(this, "Account clicked", Toast.LENGTH_SHORT).show()
                }

                R.id.nav_menu -> {
                    Toast.makeText(this, "Menu clicked", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MenuPrincipalTotal::class.java)
                    startActivity(intent)
                }

                R.id.nav_favorite -> {
                    Toast.makeText(this, "Logout clicked", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java )
                    startActivity(intent)
                }





            }
            drawerLayout.close()
            false
        }
    }

    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<FoodData>()
            for (i in mList) {
                if (i.title.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }

    private fun addDataToList() {
        mList.add(
            FoodData(
                "Desayunos",
                R.drawable.breakfastmenu,
                "Inicia el día con nuestras recetas de desayuno fáciles y deliciosas. Aprende a preparar huevos al gusto, hotcakes esponjosos, molletes clásicos y muchas otras opciones que llenarán tus mañanas de sabor. Con ingredientes accesibles y pasos sencillos, disfruta de un desayuno casero y nutritivo."
            )
        )
        mList.add(
            FoodData(
                "A la Carta",
                R.drawable.carta,
                "Explora nuestras recetas de la carta ideales para cualquier ocasión. Desde opciones tradicionales como gringas y quesadillas, hasta creaciones frescas y ligeras. Encuentra instrucciones detalladas y consejos prácticos para preparar entradas deliciosas que deleitarán a tus invitados. ¡Perfectas para empezar cualquier comida!"
            )
        )
        mList.add(
            FoodData(
                "Bebidas",
                R.drawable.bebidas,
                "Descubre cómo preparar una variedad de bebidas, desde clásicos como capuchinos y chai, hasta recetas innovadoras de smoothies, chocolate caliente o tés aromáticos. Encuentra ideas para cada momento del día y acompaña tus comidas con una bebida perfecta hecha en casa."
            )
        )
    }
}