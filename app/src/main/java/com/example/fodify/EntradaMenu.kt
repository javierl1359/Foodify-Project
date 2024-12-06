package com.example.fodify

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import java.util.Locale

class EntradaMenu : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var mList = ArrayList<FoodData>()
    private lateinit var adapter: FoodAdapter
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var buttonDrawerToggle: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrada_menu)

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
                "Consome",
                R.drawable.consome,
                """
        Ingredientes:
        - 1 litro de agua
        - 1 pechuga de pollo o piezas con hueso (para mayor sabor)
        - 1 zanahoria, pelada y en rodajas
        - 1 rama de apio, en trozos
        - 1/4 de cebolla
        - 1 diente de ajo
        - Sal al gusto
        - Perejil fresco (opcional, para decorar)

        Preparación:
        1. En una olla grande, coloca el agua, el pollo, la zanahoria, el apio, la cebolla y el ajo.
        2. Añade sal al gusto y cocina a fuego medio-alto hasta que comience a hervir.
        3. Reduce el fuego y cocina a fuego lento durante 30-40 minutos, retirando la espuma que se forme en la superficie.
        4. Una vez cocido, retira el pollo y cuela el consomé si lo prefieres más claro.
        5. Sirve caliente en un tazón y, si lo deseas, decora con perejil fresco.
        6. El **Consomé Sencillo** es una receta clásica y reconfortante, ideal para cualquier momento del día o para acompañar otros platillos.
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Pechuga Asada",
                R.drawable.pechugaasada,
                """
        Ingredientes:
        - 1 pechuga de pollo (sin piel y deshuesada)
        - 1 cucharada de aceite de oliva
        - 1 diente de ajo, finamente picado (opcional)
        - Jugo de medio limón
        - Sal y pimienta al gusto
        - Especias al gusto (orégano, tomillo, paprika, etc.)

        Preparación:
        1. Limpia la pechuga de pollo y sécala con papel absorbente.
        2. En un recipiente, mezcla el aceite de oliva, el jugo de limón, el ajo y las especias. Unta esta mezcla sobre la pechuga por ambos lados.
        3. Calienta una sartén o parrilla a fuego medio-alto. Una vez caliente, coloca la pechuga y cocina por 5-7 minutos por cada lado, o hasta que esté bien cocida y dorada.
        4. Retira del fuego y deja reposar la pechuga durante 2-3 minutos antes de cortarla para que conserve sus jugos.
        5. Sirve la **Pechuga Asada** acompañada de vegetales, arroz o ensalada según tu preferencia.
        6. Este plato es una opción saludable, fácil de preparar y muy versátil, ideal para incluir en cualquier comida del día.
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Pechuga Suiza",
                R.drawable.pechugasuiza,
                """
        Ingredientes:
        - 1 pechuga de pollo (sin piel y deshuesada)
        - 1 taza de crema ácida
        - 1/2 taza de queso rallado (tipo suizo o mozzarella)
        - 1/2 taza de caldo de pollo
        - 1/4 de cebolla, finamente picada
        - 1 diente de ajo, picado
        - 1 cucharada de mantequilla
        - Sal y pimienta al gusto
        - Perejil fresco para decorar (opcional)

        Preparación:
        1. Limpia la pechuga de pollo y sazónala con sal y pimienta.
        2. En una sartén, derrite la mantequilla a fuego medio y cocina la pechuga hasta que esté dorada por ambos lados. Retírala del sartén y resérvala.
        3. En la misma sartén, sofríe la cebolla y el ajo hasta que estén transparentes.
        4. Agrega la crema ácida y el caldo de pollo, mezclando bien para formar una salsa homogénea. Cocina por 2-3 minutos.
        5. Regresa la pechuga al sartén, cúbrela con la salsa y espolvorea el queso rallado por encima.
        6. Tapa la sartén y cocina a fuego bajo hasta que el queso se derrita y la pechuga esté completamente cocida (aproximadamente 10 minutos).
        7. Sirve la **Pechuga Suiza** caliente, decorada con perejil fresco si lo deseas. Acompaña con arroz, ensalada o pasta.
        8. Este platillo combina la suavidad del pollo con una salsa cremosa y un toque de queso derretido, ofreciendo un sabor reconfortante y sofisticado.
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Pechuga Empanizada",
                R.drawable.pechugaempanizada,
                """
        Ingredientes:
        - 1 pechuga de pollo (sin piel y deshuesada)
        - 1 taza de pan molido
        - 1/2 taza de harina de trigo
        - 2 huevos
        - Sal y pimienta al gusto
        - 1/2 cucharadita de ajo en polvo (opcional)
        - Aceite para freír

        Preparación:
        1. Limpia la pechuga de pollo, córtala en filetes si es necesario, y sazónala con sal, pimienta y ajo en polvo.
        2. Coloca la harina en un plato, los huevos batidos en otro, y el pan molido en un tercero.
        3. Pasa cada filete primero por la harina, luego por el huevo batido, y finalmente por el pan molido, asegurándote de que quede bien cubierto.
        4. Calienta suficiente aceite en una sartén a fuego medio. Fríe los filetes empanizados por ambos lados hasta que estén dorados y crujientes (aproximadamente 4-5 minutos por lado).
        5. Retira del aceite y colócalos sobre papel absorbente para eliminar el exceso de grasa.
        6. Sirve la **Pechuga Empanizada** acompañada de ensalada, puré de papa o arroz, según tu preferencia.
        7. Este platillo es ideal para toda la familia, combinando una textura crujiente por fuera y jugosa por dentro, con un sabor irresistible.
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Pechuga Cordon Blue",
                R.drawable.cordonblue,
                """
        Ingredientes:
        - 2 pechugas de pollo (sin piel y deshuesadas)
        - 4 rebanadas de jamón
        - 4 rebanadas de queso (tipo suizo, mozzarella o gouda)
        - 1 taza de pan molido
        - 1/2 taza de harina de trigo
        - 2 huevos
        - Sal y pimienta al gusto
        - Aceite para freír

        Preparación:
        1. Limpia las pechugas y córtalas en filetes delgados. Si es necesario, colócalas entre plástico y aplánalas ligeramente con un rodillo.
        2. Sazona cada filete con sal y pimienta. Coloca una rebanada de jamón y una de queso en el centro de cada filete.
        3. Dobla o enrolla las pechugas para envolver el relleno. Asegúralas con palillos de dientes.
        4. Pasa cada pechuga por harina, asegurándote de cubrir bien. Luego sumérgelas en huevo batido y cúbrelas con pan molido.
        5. Calienta aceite en una sartén a fuego medio y fríe las pechugas hasta que estén doradas y crujientes por fuera. Alternativamente, puedes hornearlas a 180 °C durante 25-30 minutos.
        6. Retira los palillos de dientes antes de servir. Acompaña la **Pechuga Cordon Bleu** con ensalada, puré de papa o arroz.
        7. Este platillo combina una capa crujiente con un centro cremoso y lleno de sabor, ideal para ocasiones especiales o comidas gourmet en casa.
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Huarache con Pastor",
                R.drawable.huarachepastor,
                """
        Ingredientes:
        - 1 huarache de maíz (puedes comprarlo listo o prepararlo en casa)
        - 200 g de carne al pastor
        - 1/2 taza de frijoles refritos
        - 1/2 taza de queso rallado (opcional)
        - 1/4 de taza de cebolla picada
        - 1/4 de taza de cilantro picado
        - Salsa al gusto (roja, verde o de habanero)
        - Limones para acompañar

        Preparación:
        1. Si el huarache está crudo, cocínalo en un comal caliente hasta que esté bien dorado por ambos lados. Resérvalo.
        2. Calienta los frijoles refritos y úntalos sobre el huarache caliente.
        3. Cocina la carne al pastor en un sartén hasta que esté dorada y bien sazonada. Colócala encima de los frijoles.
        4. Agrega el queso rallado, la cebolla y el cilantro encima de la carne.
        5. Sirve el huarache acompañado de tu salsa favorita y gajos de limón.
        6. Este **Huarache con Pastor** combina sabores tradicionales con una presentación irresistible, ideal para una comida llena de sabor mexicano.
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Fajitas de Pollo",
                R.drawable.fajitasdepollo,
                """
        Ingredientes:
        - 500 g de pechuga de pollo (cortada en tiras)
        - 1 pimiento rojo (en tiras)
        - 1 pimiento verde (en tiras)
        - 1 pimiento amarillo (en tiras)
        - 1 cebolla (en tiras)
        - 2 dientes de ajo (picados)
        - 2 cucharadas de aceite de oliva
        - 1 cucharadita de comino en polvo
        - 1/2 cucharadita de pimentón (paprika)
        - Sal y pimienta al gusto
        - Tortillas de harina o maíz para servir
        - Limones y salsa al gusto para acompañar

        Preparación:
        1. Calienta el aceite de oliva en una sartén grande a fuego medio.
        2. Añade el ajo picado y cocina hasta que esté fragante.
        3. Agrega las tiras de pollo y sazónalas con comino, pimentón, sal y pimienta. Cocina hasta que el pollo esté bien dorado y cocido.
        4. Incorpora los pimientos y la cebolla. Saltea por 5-7 minutos o hasta que las verduras estén suaves pero ligeramente crujientes.
        5. Sirve las fajitas calientes acompañadas de tortillas de harina o maíz. Puedes agregar limones y salsa al gusto.
        6. Estas **Fajitas de Pollo** son fáciles de preparar, coloridas y llenas de sabor, perfectas para una comida completa y saludable.
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Tampiqueña",
                R.drawable.tampiquena,
                """
        Ingredientes:
        - 1 bistec de res (aproximadamente 200 g)
        - 1/2 taza de frijoles refritos
        - 1 enchilada (rellena de queso o pollo)
        - 1/2 taza de arroz rojo
        - 1 aguacate (rebanado)
        - 1 chile asado (opcional)
        - Aceite para cocinar
        - Sal y pimienta al gusto

        Preparación:
        1. Sazona el bistec con sal y pimienta al gusto. En un sartén caliente con un poco de aceite, cocina el bistec hasta que esté dorado y cocido a tu gusto. Resérvalo.
        2. Calienta los frijoles refritos y el arroz rojo. También prepara la enchilada con el relleno de tu preferencia y caliéntala en un sartén o comal.
        3. Si lo deseas, asa el chile en el comal hasta que esté suave y ligeramente quemado.
        4. En un plato grande, acomoda el bistec, los frijoles, el arroz, la enchilada, el chile asado y las rebanadas de aguacate.
        5. Sirve la **Tampiqueña** acompañada de tortillas calientes. Disfruta de este platillo completo y tradicional mexicano, ideal para una comida llena de sabor.
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Milanesa de Res",
                R.drawable.milanesaderes,
                """
        Ingredientes:
        - 500 g de bistec de res (cortado delgado)
        - 1 taza de pan molido
        - 1/2 taza de harina de trigo
        - 2 huevos
        - 1/2 cucharadita de ajo en polvo
        - 1/2 cucharadita de pimienta
        - Sal al gusto
        - Aceite para freír

        Preparación:
        1. Sazona los bistecs de res con sal, pimienta y ajo en polvo por ambos lados.
        2. Prepara tres recipientes: uno con harina, otro con los huevos batidos y el último con el pan molido.
        3. Pasa cada bistec primero por la harina, luego por el huevo batido y finalmente cúbrelo con pan molido, asegurándote de que quede bien recubierto.
        4. Calienta aceite en una sartén a fuego medio. Fríe las milanesas por ambos lados hasta que estén doradas y crujientes. Colócalas sobre papel absorbente para retirar el exceso de grasa.
        5. Sirve la **Milanesa de Res** caliente, acompañada de ensalada, puré de papa, arroz o frijoles. Es un platillo sencillo, versátil y lleno de sabor.
        """.trimIndent()
            )
        )
    }
}