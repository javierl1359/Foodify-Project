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

class BebidasMenu : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var mList = ArrayList<FoodData>()
    private lateinit var adapter: FoodAdapter
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var buttonDrawerToggle: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bebidas_menu)
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
                "Expresso Sencillo",
                R.drawable.espresso,
                """
        Ingredientes:
        - 1 taza de agua filtrada
        - 7-9 g de café molido de grano fino (preferentemente de tu elección)
        
        Preparación:
        1. Calienta el agua en una máquina de espresso hasta que alcance la temperatura adecuada (aproximadamente 90-96°C).
        2. Coloca el café molido en el portafiltro y compacta ligeramente con el tamper.
        3. Inserta el portafiltro en la máquina y comienza el proceso de extracción.
        4. El espresso debe ser servido en una taza pequeña (aproximadamente 30 ml).
        5. Disfruta de un espresso fuerte, concentrado y de sabor profundo, con una capa de crema dorada en la parte superior. ¡Perfecto para un impulso rápido de energía!
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Expresso Doble",
                R.drawable.expressodoble,
                """
        Ingredientes:
        - 2 tazas de agua filtrada
        - 14-18 g de café molido de grano fino (preferentemente de tu elección)
        
        Preparación:
        1. Calienta el agua en una máquina de espresso hasta que alcance la temperatura adecuada (aproximadamente 90-96°C).
        2. Coloca el café molido en el portafiltro y compacta ligeramente con el tamper.
        3. Inserta el portafiltro en la máquina y comienza el proceso de extracción.
        4. El espresso debe ser servido en una taza pequeña (aproximadamente 60 ml).
        5. Disfruta de un espresso doble, con el doble de intensidad y sabor que un espresso sencillo, perfecto para los amantes del café fuerte.
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Expresso Cortado Sencillo",
                R.drawable.expressocortadosencillo,
                """
        Ingredientes:
        - 1 taza de agua filtrada
        - 7-9 g de café molido de grano fino (preferentemente de tu elección)
        - 30 ml de leche (puede ser leche entera o descremada, al gusto)
        
        Preparación:
        1. Calienta el agua en una máquina de espresso hasta que alcance la temperatura adecuada (aproximadamente 90-96°C).
        2. Coloca el café molido en el portafiltro y compacta ligeramente con el tamper.
        3. Inserta el portafiltro en la máquina y comienza el proceso de extracción del espresso.
        4. Mientras se extrae el espresso, calienta la leche a fuego lento o en una vaporera hasta que esté suave y ligeramente espumosa.
        5. Sirve el espresso en una taza pequeña (aproximadamente 30 ml) y agrega la leche caliente.
        6. El **expresso cortado sencillo** tiene una proporción de 1:1 entre el espresso y la leche, creando una bebida suave pero con el sabor característico del café espresso, ideal para quienes disfrutan de un toque lácteo sin perder la intensidad del café.
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Expresso Cortado Doble",
                R.drawable.expressocortadodoble,
                """
        Ingredientes:
        - 1 taza de agua filtrada
        - 7-9 g de café molido de grano fino (preferentemente de tu elección)
        - 30 ml de leche (puede ser leche entera o descremada, al gusto)
        
        Preparación:
        1. Calienta el agua en una máquina de espresso hasta que alcance la temperatura adecuada (aproximadamente 90-96°C).
        2. Coloca el café molido en el portafiltro y compacta ligeramente con el tamper.
        3. Inserta el portafiltro en la máquina y comienza el proceso de extracción del espresso.
        4. Mientras se extrae el espresso, calienta la leche a fuego lento o en una vaporera hasta que esté suave y ligeramente espumosa.
        5. Sirve el espresso en una taza pequeña (aproximadamente 30 ml) y agrega la leche caliente.
        6. El **expresso cortado sencillo** tiene una proporción de 1:1 entre el espresso y la leche, creando una bebida suave pero con el sabor característico del café espresso, ideal para quienes disfrutan de un toque lácteo sin perder la intensidad del café.
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Americano",
                R.drawable.americano,
                """
        Ingredientes:
        - 1 taza de agua filtrada
        - 7-9 g de café molido de grano fino (preferentemente de tu elección)
        
        Preparación:
        1. Calienta el agua en una máquina de espresso hasta que alcance la temperatura adecuada (aproximadamente 90-96°C).
        2. Coloca el café molido en el portafiltro y compacta ligeramente con el tamper.
        3. Inserta el portafiltro en la máquina y comienza el proceso de extracción del espresso.
        4. Una vez que el espresso esté listo, agrega agua caliente (aproximadamente 60-90 ml, dependiendo de la intensidad deseada).
        5. El **Americano** es una bebida sencilla y suave, con un sabor menos intenso que un espresso, pero manteniendo sus notas profundas. Ideal para quienes prefieren un café largo sin perder la esencia del espresso.
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Americano Cortado",
                R.drawable.americanocortado,
                """
        Ingredientes:
        - 1 taza de agua filtrada
        - 7-9 g de café molido de grano fino (preferentemente de tu elección)
        - 30-40 ml de leche (puede ser leche entera o descremada, al gusto)
        
        Preparación:
        1. Calienta el agua en una máquina de espresso hasta que alcance la temperatura adecuada (aproximadamente 90-96°C).
        2. Coloca el café molido en el portafiltro y compacta ligeramente con el tamper.
        3. Inserta el portafiltro en la máquina y comienza el proceso de extracción del espresso.
        4. Una vez que el espresso esté listo, agrega agua caliente (aproximadamente 60-90 ml) para crear un **Americano**.
        5. Calienta la leche a fuego lento o en una vaporera hasta que esté suave y ligeramente espumosa.
        6. Añade la leche al **Americano** (aproximadamente 30-40 ml), equilibrando el sabor fuerte del café con la suavidad de la leche.
        7. El **Americano Cortado** tiene una combinación perfecta entre el sabor suave del Americano y el toque lácteo del cortado, ideal para quienes disfrutan de un café suave pero con la intensidad del espresso.
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Capuccino",
                R.drawable.capuccino,
                """
        Ingredientes:
        - 1 taza de agua filtrada
        - 7-9 g de café molido de grano fino (preferentemente de tu elección)
        - 60 ml de leche (leche entera, descremada o vegetal, al gusto)
        
        Preparación:
        1. Calienta el agua en una máquina de espresso hasta que alcance la temperatura adecuada (aproximadamente 90-96°C).
        2. Coloca el café molido en el portafiltro y compacta ligeramente con el tamper.
        3. Inserta el portafiltro en la máquina y comienza el proceso de extracción del espresso.
        4. Mientras se extrae el espresso, calienta la leche a fuego lento o en una vaporera hasta que esté espumosa y cremosa.
        5. Sirve el espresso en una taza pequeña (aproximadamente 30 ml) y agrega la leche espumosa (aproximadamente 60 ml).
        6. El **Capuccino** tiene una proporción de 1/3 espresso, 1/3 leche vaporizada y 1/3 espuma de leche, creando una bebida suave con una rica capa de espuma en la parte superior, ideal para quienes disfrutan de un café cremoso y equilibrado.
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Capuccino de sabor",
                R.drawable.capuccinodesabor,
                """
        Ingredientes:
        - 1 taza de agua filtrada
        - 7-9 g de café molido de grano fino (preferentemente de tu elección)
        - 60 ml de leche (leche entera, descremada o vegetal, al gusto)
        - Sirope de sabor (vainilla, caramelo, avellana, chocolate, etc., al gusto)
        
        Preparación:
        1. Calienta el agua en una máquina de espresso hasta que alcance la temperatura adecuada (aproximadamente 90-96°C).
        2. Coloca el café molido en el portafiltro y compacta ligeramente con el tamper.
        3. Inserta el portafiltro en la máquina y comienza el proceso de extracción del espresso.
        4. Mientras se extrae el espresso, calienta la leche a fuego lento o en una vaporera hasta que esté espumosa y cremosa.
        5. Sirve el espresso en una taza pequeña (aproximadamente 30 ml) y agrega la leche espumosa (aproximadamente 60 ml).
        6. Añade un toque de sirope de tu sabor favorito (vainilla, caramelo, avellana, chocolate, etc.) al gusto.
        7. El **Capuccino de Sabor** es una versión más dulce y aromática del clásico capuccino, con una mezcla perfecta de espresso, leche cremosa y un toque de sabor que lo hace irresistible para los amantes de los cafés especiales.
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Mocaccino",
                R.drawable.mocaccino,
                """
        Ingredientes:
        - 1 taza de agua filtrada
        - 7-9 g de café molido de grano fino (preferentemente de tu elección)
        - 60 ml de leche (leche entera, descremada o vegetal, al gusto)
        - 1-2 cucharadas de sirope de chocolate o cacao en polvo
        - Crema batida (opcional)
        
        Preparación:
        1. Calienta el agua en una máquina de espresso hasta que alcance la temperatura adecuada (aproximadamente 90-96°C).
        2. Coloca el café molido en el portafiltro y compacta ligeramente con el tamper.
        3. Inserta el portafiltro en la máquina y comienza el proceso de extracción del espresso.
        4. Mientras se extrae el espresso, calienta la leche a fuego lento o en una vaporera hasta que esté espumosa y cremosa.
        5. Sirve el espresso en una taza y agrega una o dos cucharadas de sirope de chocolate o cacao en polvo al gusto.
        6. Añade la leche espumosa a la taza, mezclando bien el café con el chocolate.
        7. Si lo deseas, cubre el mocaccino con crema batida para darle un toque más indulgente.
        8. El **Mocaccino** es una deliciosa combinación de café espresso, chocolate y leche espumosa, ideal para quienes aman el sabor del chocolate en su café, creando una bebida rica, cremosa y perfecta para cualquier momento del día.
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Te",
                R.drawable.te,
                """
        Ingredientes:
        - 1 bolsita de té (negro, verde, blanco, o de hierbas al gusto)
        - 200 ml de agua filtrada
        - Miel o azúcar (opcional)
        - Limón (opcional)
        
        Preparación:
        1. Calienta el agua en una tetera o en el microondas hasta que esté casi hirviendo (aproximadamente 90-95°C).
        2. Coloca la bolsita de té en una taza.
        3. Vierte el agua caliente sobre la bolsita de té y deja reposar durante 3-5 minutos (dependiendo de la intensidad deseada).
        4. Retira la bolsita de té y agrega miel o azúcar al gusto, si lo prefieres.
        5. Si deseas, puedes añadir una rodaja de limón para un toque refrescante.
        6. El **Té** es una bebida reconfortante y saludable, ideal para disfrutar a cualquier hora del día, con sus múltiples variedades y beneficios para la salud.
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Chai",
                R.drawable.chai,
                """
        Ingredientes:
        - 1 bolsita de té chai o 1 cucharadita de mezcla de especias chai (canela, cardamomo, jengibre, clavo)
        - 200 ml de agua filtrada
        - 60 ml de leche (leche entera, descremada o vegetal, al gusto)
        - Miel o azúcar (opcional)
        
        Preparación:
        1. Calienta el agua en una tetera hasta que esté casi hirviendo (aproximadamente 90-95°C).
        2. Coloca la bolsita de té chai o la mezcla de especias chai en una taza.
        3. Vierte el agua caliente sobre el té o las especias y deja reposar durante 3-5 minutos, permitiendo que los sabores se infundan bien.
        4. Mientras se infunde el chai, calienta la leche a fuego lento o en una vaporera hasta que esté espumosa.
        5. Retira la bolsita de té o las especias y agrega la leche espumosa a la taza con el chai.
        6. Si lo deseas, endulza con miel o azúcar al gusto.
        7. El **Chai** es una bebida reconfortante y aromática, llena de especias cálidas que la hacen perfecta para cualquier momento del día. Su sabor exótico y su suavidad hacen de esta bebida una experiencia única.
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Chocolate",
                R.drawable.chocolate,
                """
        Ingredientes:
        - 2 cucharadas de cacao en polvo sin azúcar o chocolate en trozos
        - 200 ml de leche (leche entera, descremada o vegetal, al gusto)
        - 1 cucharadita de azúcar o miel (opcional)
        - Un toque de canela o vainilla (opcional)
        
        Preparación:
        1. Calienta la leche en una olla a fuego medio, asegurándote de no dejarla hervir.
        2. Agrega el cacao en polvo o el chocolate en trozos a la leche caliente y mezcla bien hasta que se disuelva completamente.
        3. Si deseas un toque más dulce, agrega azúcar o miel al gusto y mezcla bien.
        4. Para un extra de sabor, puedes añadir una pizca de canela o unas gotas de vainilla.
        5. Sirve caliente en una taza y disfruta de una bebida reconfortante, perfecta para cualquier momento del día.
        6. El **Chocolate** es una opción deliciosa y cálida, ideal para los amantes del cacao, que disfrutan de su sabor profundo y reconfortante.
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Chocolate Blanco",
                R.drawable.chocolateblanco,
                """
        Ingredientes:
        - 2 cucharadas de chocolate blanco en trozos o chips
        - 200 ml de leche (leche entera, descremada o vegetal, al gusto)
        - 1 cucharadita de azúcar o miel (opcional)
        - Un toque de vainilla o canela (opcional)
        
        Preparación:
        1. Calienta la leche en una olla a fuego medio, vigilando que no llegue a hervir.
        2. Agrega el chocolate blanco en trozos a la leche caliente y mezcla bien hasta que se disuelva completamente.
        3. Si prefieres un toque más dulce, añade azúcar o miel al gusto y revuelve hasta que se disuelva.
        4. Si deseas un sabor adicional, puedes agregar unas gotas de vainilla o una pizca de canela.
        5. Sirve el chocolate blanco caliente en una taza y disfruta de su suavidad y dulzura.
        6. El **Chocolate Blanco** es una bebida cremoso y delicada, perfecta para quienes disfrutan de un sabor suave y dulce, ideal para un momento reconfortante.
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Vainilla Francesa",
                R.drawable.vainillafrnacesa,
                """
        Ingredientes:
        - 1 taza de leche (leche entera, descremada o vegetal, al gusto)
        - 1 cucharada de esencia de vainilla
        - 1-2 cucharaditas de azúcar o miel (opcional)
        - 1 cucharada de crema batida (opcional, para decorar)
        
        Preparación:
        1. Calienta la leche en una olla a fuego medio, sin dejarla hervir.
        2. Agrega la esencia de vainilla y mezcla bien.
        3. Si deseas un toque más dulce, añade azúcar o miel al gusto y mezcla hasta que se disuelva completamente.
        4. Sirve la bebida caliente en una taza.
        5. Si lo prefieres, cubre con crema batida para darle un toque extra de suavidad y sabor.
        6. La **Vainilla Francesa** es una bebida suave y dulce, con un delicioso aroma y sabor a vainilla, perfecta para disfrutar a cualquier hora del día.
        """.trimIndent()
            )
        )
        mList.add(
            FoodData(
                "Matcha",
                R.drawable.matcha,
                """
        Ingredientes:
        - 1 cucharadita de polvo de matcha
        - 200 ml de leche (leche entera, descremada o vegetal, al gusto)
        - 1 cucharadita de azúcar o miel (opcional)
        - Un toque de vainilla (opcional)

        Preparación:
        1. Calienta la leche en una olla a fuego medio, evitando que hierva.
        2. En un tazón pequeño, agrega el polvo de matcha.
        3. Vierte un poco de leche caliente sobre el matcha y revuelve bien hasta que se disuelva y forme una pasta suave.
        4. Añade el resto de la leche caliente y mezcla hasta obtener una bebida uniforme.
        5. Si prefieres una bebida más dulce, agrega azúcar o miel al gusto y mezcla bien.
        6. Sirve el matcha caliente en una taza y, si lo deseas, puedes añadir un toque de vainilla para un sabor más suave.
        7. El **Matcha** es una bebida energética y refrescante, rica en antioxidantes y con un sabor único que combina el frescor de la verdura con una suavidad reconfortante.
        """.trimIndent()
            )
        )
    }
}