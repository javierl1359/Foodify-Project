package com.example.fodify

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import android.widget.ImageButton
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class desayunoMenu : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var mList = ArrayList<FoodData>()
    private lateinit var adapter: FoodAdapter
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var buttonDrawerToggle: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desayuno_menu)

        recyclerView = findViewById(R.id.cardView)
        searchView = findViewById(R.id.searchView)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        addDataToList()
        adapter = FoodAdapter(mList)
        recyclerView.adapter = adapter

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
                "Huevos al gusto",
                R.drawable.huevosalgusto,
                """
        Ingredientes:
        - 2 huevos
        - 50 g de jamón (opcional)
        - 1 salchicha (opcional)
        - 50 g de chorizo (opcional)
        - 1 jitomate (para estilo ranchero o a la mexicana)
        - ¼ de cebolla (para estilo a la mexicana)
        - 1 chile serrano (opcional, para estilo a la mexicana)
        - Salsa roja, verde o habanero (opcional)
        - Aceite o mantequilla
        - Sal al gusto
        
        Preparación:
        1. Prepara los ingredientes según el estilo de huevos deseado.
        2. Calienta el sartén con un poco de aceite o mantequilla.
        3. Sofríe el acompañamiento elegido (jamón, salchicha o chorizo).
        4. Agrega los huevos según la preparación deseada: revueltos, estrellados, rancheros o a la mexicana.
        5. Sirve caliente y acompaña con frijoles, tortillas o pan.
        """.trimIndent()
            )
        )

        mList.add(
            FoodData(
                "Omelette Toluqueño",
                R.drawable.omelettetoluqueno,
                """
        Ingredientes:
        - 2 huevos
        - 50 g de chorizo de Toluca
        - 50 g de queso (opcional: manchego, gouda o panela)
        - ¼ de cebolla picada
        - ¼ de pimiento rojo o verde picado
        - Aceite o mantequilla
        - Sal y pimienta al gusto
        - Frijoles y tortillas (para acompañar)
        
        Preparación:
        1. En un sartén, calienta un poco de aceite y sofríe el chorizo hasta que esté dorado. Retira y reserva.
        2. En el mismo sartén, saltea la cebolla y el pimiento hasta que estén tiernos.
        3. Bate los huevos con una pizca de sal y pimienta, y viértelos en el sartén caliente con un poco de mantequilla.
        4. Cocina los huevos a fuego bajo hasta que se comiencen a cuajar. Agrega el chorizo, las verduras salteadas y el queso en una mitad del omelette.
        5. Dobla el omelette cuidadosamente y cocina por 1-2 minutos más para que el queso se derrita.
        6. Sirve caliente, acompañado de frijoles y tortillas. ¡Disfruta de un desayuno lleno de tradición!
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Omelette de Champiñones",
                R.drawable.omelettechampis,
                """
        Ingredientes:
        - 2 huevos
        - 100 g de champiñones frescos, limpios y rebanados
        - 50 g de queso (opcional: manchego, gouda o mozzarella)
        - ¼ de cebolla picada (opcional)
        - 1 diente de ajo picado (opcional)
        - Aceite o mantequilla
        - Sal y pimienta al gusto
        - Frijoles y tortillas (para acompañar)
        
        Preparación:
        1. Calienta un poco de aceite en un sartén y sofríe el ajo y la cebolla (si los usas) hasta que estén ligeramente dorados.
        2. Agrega los champiñones y cocina hasta que suelten su jugo y estén tiernos. Retira y reserva.
        3. Bate los huevos con una pizca de sal y pimienta, y viértelos en un sartén caliente con un poco de mantequilla.
        4. Cocina los huevos a fuego bajo hasta que comiencen a cuajar. Agrega los champiñones salteados y el queso en una mitad del omelette.
        5. Dobla cuidadosamente el omelette y cocina por 1-2 minutos más para que el queso se derrita.
        6. Sirve caliente, acompañado de frijoles y tortillas. ¡Un desayuno ligero, delicioso y lleno de energía!
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Hotcakes",
                R.drawable.hotcakes,
                """
        Ingredientes:
        - 1 taza de harina para hotcakes
        - ¾ de taza de leche
        - 1 huevo
        - 1 cucharada de mantequilla derretida (más un poco para el sartén)
        - Miel de maple (para servir)
        - Mantequilla (para servir)
        
        Preparación:
        1. En un tazón, mezcla la harina para hotcakes, la leche, el huevo y la mantequilla derretida hasta obtener una masa suave y sin grumos.
        2. Calienta un sartén o comal a fuego medio y engrasa ligeramente con mantequilla.
        3. Vierte una porción de la mezcla en el sartén (aproximadamente ¼ de taza) y cocina hasta que aparezcan burbujas en la superficie y los bordes estén dorados.
        4. Voltea el hotcake y cocina por 1-2 minutos más hasta que esté bien cocido.
        5. Sirve los hotcakes apilados, acompañados de mantequilla derretida y miel de maple. ¡Disfruta de un desayuno clásico y delicioso!
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Hotcakes con tocino",
                R.drawable.hotcaketocino,
                """
        Ingredientes:
        - 1 taza de harina para hotcakes
        - ¾ de taza de leche
        - 1 huevo
        - 1 cucharada de mantequilla derretida (más un poco para el sartén)
        - 4-5 tiras de tocino
        - Miel de maple (para servir)
        - Mantequilla (para servir)
        
        Preparación:
        1. Cocina el tocino en un sartén a fuego medio hasta que esté crujiente. Retira y coloca sobre papel absorbente para eliminar el exceso de grasa.
        2. En un tazón, mezcla la harina para hotcakes, la leche, el huevo y la mantequilla derretida hasta obtener una masa suave y sin grumos.
        3. Calienta un sartén o comal a fuego medio y engrasa ligeramente con mantequilla.
        4. Vierte una porción de la mezcla en el sartén (aproximadamente ¼ de taza) y cocina hasta que aparezcan burbujas en la superficie y los bordes estén dorados.
        5. Voltea el hotcake y cocina por 1-2 minutos más hasta que esté bien cocido.
        6. Sirve los hotcakes apilados, acompañados de las tiras de tocino crujiente, mantequilla derretida y miel de maple. ¡Un desayuno clásico con un toque irresistible!
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Molletes",
                R.drawable.molletes,
                """
        Ingredientes:
        - 1 bolillo o pan francés
        - 50 g de frijoles refritos
        - 100 g de queso rallado (opcional: queso manchego o gouda)
        - 1 cucharada de mantequilla (opcional, para dorar el pan)
        - Salsa (opcional: pico de gallo, salsa roja o verde)
        
        Preparación:
        1. Corta el bolillo a la mitad y, si deseas, unta con mantequilla en la parte interna.
        2. Coloca las mitades de pan en un sartén caliente, con el lado de la mantequilla hacia abajo, para dorarlas ligeramente.
        3. Unta los frijoles refritos en la parte superior de cada mitad de bolillo.
        4. Espolvorea el queso rallado sobre los frijoles.
        5. Coloca las mitades en un sartén o en el horno para que el queso se derrita y el pan quede crujiente.
        6. Sirve los molletes acompañados de salsa al gusto, ya sea pico de gallo, salsa roja o verde. ¡Disfruta de un desayuno delicioso y reconfortante!
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Molletes con Chorizo",
                R.drawable.molleteschorizo,
                """
        Ingredientes:
        - 1 bolillo o pan francés
        - 50 g de frijoles refritos
        - 100 g de queso rallado (opcional: queso manchego o gouda)
        - 100 g de chorizo
        - 1 cucharada de mantequilla (opcional, para dorar el pan)
        - Salsa (opcional: pico de gallo, salsa roja o verde)
        
        Preparación:
        1. Cocina el chorizo en un sartén a fuego medio, desmenuzándolo mientras se cocina, hasta que esté bien dorado y crujiente. Retira el exceso de grasa.
        2. Corta el bolillo a la mitad y, si deseas, unta con mantequilla en la parte interna.
        3. Coloca las mitades de pan en un sartén caliente, con el lado de la mantequilla hacia abajo, para dorarlas ligeramente.
        4. Unta los frijoles refritos en la parte superior de cada mitad de bolillo.
        5. Espolvorea el queso rallado sobre los frijoles.
        6. Coloca las mitades en un sartén o en el horno para que el queso se derrita y el pan quede crujiente.
        7. Sirve los molletes con el chorizo dorado encima y acompáñalos con salsa al gusto. ¡Una deliciosa combinación de sabores para tu desayuno!
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Molletes con Tocino",
                R.drawable.molletestocino,
                """
        Ingredientes:
        - 1 bolillo o pan francés
        - 50 g de frijoles refritos
        - 100 g de queso rallado (opcional: queso manchego o gouda)
        - 4-5 tiras de tocino
        - 1 cucharada de mantequilla (opcional, para dorar el pan)
        - Salsa (opcional: pico de gallo, salsa roja o verde)
        
        Preparación:
        1. Cocina el tocino en un sartén a fuego medio hasta que esté crujiente. Retira y coloca sobre papel absorbente para eliminar el exceso de grasa.
        2. Corta el bolillo a la mitad y, si deseas, unta con mantequilla en la parte interna.
        3. Coloca las mitades de pan en un sartén caliente, con el lado de la mantequilla hacia abajo, para dorarlas ligeramente.
        4. Unta los frijoles refritos en la parte superior de cada mitad de bolillo.
        5. Espolvorea el queso rallado sobre los frijoles.
        6. Coloca las mitades en un sartén o en el horno para que el queso se derrita y el pan quede crujiente.
        7. Sirve los molletes con las tiras de tocino crujiente encima y acompáñalos con salsa al gusto. ¡Un desayuno sabroso y completo para empezar el día!
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Mollequiles",
                R.drawable.mollequilesrojos,
                """
        Ingredientes:
        - 4 tortillas de maíz
        - 100 g de pollo desmenuzado (opcional)
        - 50 g de queso rallado (opcional: queso fresco o manchego)
        - 1 taza de salsa verde, roja, habanera o suiza
        - 1 cucharada de aceite o mantequilla
        - 1 cucharadita de crema (opcional)
        - 1 huevo (opcional)
        - 1 cucharada de cebolla picada (opcional)
        - Cilantro fresco al gusto (opcional)
        
        Preparación:
        1. Corta las tortillas en triángulos o tiras, y fríelas en el aceite o mantequilla caliente hasta que estén crujientes. Colócalas sobre papel absorbente para eliminar el exceso de grasa.
        2. En una sartén, calienta la salsa de tu elección (verde, roja, habanera o suiza) hasta que esté caliente.
        3. Añade las tortillas fritas a la salsa caliente y mezcla para que se impregnen bien.
        4. Si deseas, agrega el pollo desmenuzado y mezcla bien.
        5. Cocina un huevo al gusto (puede ser estrellado o revuelto) y colócalo encima de los mollequiles.
        6. Sirve con queso rallado, crema, cebolla picada y cilantro fresco al gusto. ¡Disfruta de este delicioso platillo mexicano con un toque especial!
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Quesadillas de Harina",
                R.drawable.quesadillaharina,
                """
        Ingredientes:
        - 2 tortillas de harina grandes
        - 100 g de queso rallado (opcional: queso Oaxaca, manchego o cheddar)
        - 50 g de algún ingrediente adicional (opcional: champiñones, flor de calabaza, huitlacoche, chicharrón prensado, o carne de tu elección)
        - 1 cucharada de mantequilla o aceite
        - Salsa al gusto (opcional: salsa roja, verde o pico de gallo)
        
        Preparación:
        1. Calienta las tortillas de harina en un sartén a fuego medio sin agregar grasa hasta que se suavicen.
        2. Coloca el queso rallado en el centro de cada tortilla.
        3. Agrega el ingrediente adicional de tu elección (chicharrón prensado, champiñones, etc.) sobre el queso.
        4. Dobla la tortilla por la mitad para cubrir el relleno y forma una media luna.
        5. En el sartén, añade la mantequilla o aceite y cocina las quesadillas por ambos lados hasta que estén doradas y el queso se haya derretido.
        6. Sirve las quesadillas con salsa al gusto. ¡Disfruta de este platillo delicioso y versátil, perfecto para cualquier momento del día!
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Sincronizada",
                R.drawable.sincronizada,
                """
        Ingredientes:
        - 2 tortillas de harina grandes
        - 100 g de jamón (o pollo, carne de res o cerdo)
        - 100 g de queso rallado (opcional: queso Oaxaca, manchego o cheddar)
        - 1 cucharada de mantequilla o aceite
        - Salsa al gusto (opcional: salsa roja, verde o pico de gallo)
        
        Preparación:
        1. Coloca una tortilla de harina en un sartén a fuego medio sin grasa y caliéntala ligeramente.
        2. Sobre la tortilla, coloca una capa de queso rallado y una capa de jamón (o el ingrediente elegido).
        3. Coloca la segunda tortilla encima para formar un sándwich.
        4. Añade un poco de mantequilla o aceite en el sartén y cocina la sincronizada por ambos lados hasta que esté dorada y el queso se haya derretido.
        5. Sirve con salsa al gusto. ¡Una receta simple, pero deliciosa, ideal para el desayuno o una comida ligera!
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Gringa de Pastor",
                R.drawable.gringapastor,
                """
        Ingredientes:
        - 2 tortillas de harina grandes
        - 150 g de carne al pastor (puede ser cerdo o pollo)
        - 50 g de queso rallado (opcional: queso Oaxaca o mozzarella)
        - 1/4 de cebolla morada (opcional: picada finamente)
        - 1 cucharada de cilantro fresco (opcional)
        - Salsa al gusto (opcional: salsa verde, roja o pico de gallo)
        - 1 cucharadita de aceite o mantequilla para dorar las tortillas
        
        Preparación:
        1. Calienta las tortillas de harina en un sartén a fuego medio sin grasa hasta que se suavicen.
        2. Coloca una capa de carne al pastor sobre una de las tortillas.
        3. Agrega el queso rallado sobre la carne y cubre con la otra tortilla.
        4. En el sartén, agrega un poco de aceite o mantequilla y cocina la gringa por ambos lados hasta que el queso se derrita y las tortillas estén doradas y crujientes.
        5. Sirve con cebolla morada y cilantro fresco al gusto, y acompáñala con salsa de tu elección. ¡Una deliciosa y jugosa gringa que será un éxito en cualquier momento del día!
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Enchiladas",
                R.drawable.enchiladas,
                """
        Ingredientes:
        - 6 tortillas de maíz
        - 1 taza de salsa roja, verde o habanera (puede ser casera o comprada)
        - 200 g de pollo desmenuzado (o carne de tu elección)
        - 100 g de queso rallado (opcional: queso fresco o manchego)
        - 1/4 de cebolla morada picada
        - 1 cucharada de crema (opcional)
        - Cilantro fresco al gusto
        - Aceite para freír las tortillas
        
        Preparación:
        1. En un sartén, calienta un poco de aceite y fríe las tortillas de maíz por unos segundos de cada lado, solo hasta que estén suaves (no crujientes).
        2. Calienta la salsa elegida (roja, verde o habanera) en una olla hasta que esté caliente.
        3. Coloca las tortillas fritas en la salsa caliente, cubriéndolas bien con la salsa.
        4. Coloca una porción de pollo desmenuzado (o el relleno de tu preferencia) en el centro de cada tortilla.
        5. Enrolla las tortillas para formar las enchiladas.
        6. Sirve las enchiladas en un plato, cubriéndolas con más salsa, queso rallado, cebolla morada picada, cilantro y un toque de crema. ¡Listas para disfrutar!
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Enchiladas de Cochinita y Habanero",
                R.drawable.enchiladashabanero,
                """
        Ingredientes:
        - 6 tortillas de maíz
        - 200 g de cochinita pibil desmenuzada
        - 1 taza de salsa habanera (puede ser casera o comprada)
        - 100 g de queso rallado (opcional: queso fresco o manchego)
        - 1/4 de cebolla morada picada
        - 1 cucharada de crema (opcional)
        - Cilantro fresco al gusto
        - Aceite para freír las tortillas
        
        Preparación:
        1. En un sartén, calienta un poco de aceite y fríe las tortillas de maíz por unos segundos de cada lado, solo hasta que estén suaves (no crujientes).
        2. Calienta la salsa habanera en una olla hasta que esté bien caliente.
        3. Coloca las tortillas fritas en la salsa caliente, cubriéndolas bien con la salsa.
        4. Rellena las tortillas con una porción de cochinita pibil desmenuzada.
        5. Enrolla las tortillas para formar las enchiladas.
        6. Sirve las enchiladas en un plato, cubriéndolas con más salsa habanera, queso rallado, cebolla morada picada, cilantro y un toque de crema. ¡Disfruta de este platillo picante y lleno de sabor!
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Enchiladas Suizas",
                R.drawable.enchiladasuizas,
                """
        Ingredientes:
        - 6 tortillas de maíz
        - 200 g de pollo desmenuzado (o carne de tu elección)
        - 1 taza de salsa verde suave o cremosa
        - 100 g de queso suizo o manchego rallado
        - 1/4 de cebolla morada picada (opcional)
        - 1 cucharada de crema
        - Cilantro fresco al gusto
        - Aceite para freír las tortillas
        
        Preparación:
        1. En un sartén, calienta un poco de aceite y fríe las tortillas de maíz por unos segundos de cada lado, solo hasta que estén suaves (no crujientes).
        2. Calienta la salsa verde en una olla hasta que esté caliente.
        3. Coloca las tortillas fritas en la salsa caliente, cubriéndolas bien con la salsa.
        4. Rellena las tortillas con el pollo desmenuzado (o el relleno de tu preferencia).
        5. Enrolla las tortillas para formar las enchiladas.
        6. Coloca las enchiladas en un sartén o fuente para hornear, cubriéndolas con salsa verde, queso rallado y crema.
        7. Cocina a fuego bajo hasta que el queso se derrita y las enchiladas estén bien calientes.
        8. Sirve las enchiladas con cebolla morada picada, cilantro fresco y un toque de crema. ¡Listas para disfrutar con una combinación suave y deliciosa!
        """.trimIndent()
            )
        )
    }
}