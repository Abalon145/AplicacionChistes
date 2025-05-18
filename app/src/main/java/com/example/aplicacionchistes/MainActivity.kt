package com.example.aplicacionchistes

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionchistes.Adaptadores.AdaptadorCategorias
import com.example.aplicacionchistes.Fragmentos.Main
import com.example.aplicacionchistes.ui.theme.AplicacionChistesTheme

//Actividad principal, muestra la lista de categorías de chistes (RecyclerView) y los chistes (Fragmento)

class MainActivity : AppCompatActivity() {

    //Variables de clase
    //RecyclerView que contiene la lista de categorias
    lateinit var listCat: RecyclerView

    //Lista con todas las categorias de chistes
    lateinit var categorias: List<String>

    //Adaptador de la lista de categorias
    lateinit var adaptador: AdaptadorCategorias

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.barraCabecera))

        //Lista con todas las categorias de chistes
        categorias = listOf(
            "Cualquiera",
            "Programación",
            "Misceláneo",
            "Humor negro",
            "Juegos de palabras",
            "Terror",
            "Navidad"
        )

        //Se corresponde con "categoriasList", el RecyclerView en "activity_main.xml"
        listCat = findViewById(R.id.categoriasList)
        adaptador = AdaptadorCategorias(categorias)

        //Declaración del LayoutManager y de su orientación (Horizontal)
        listCat.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
            false))

        //Declaración del adaptador a utilizar
        listCat.setAdapter(adaptador)

        //Declaración del manager de fragmento "fragment.main"
        val manager: FragmentManager = supportFragmentManager

        //Declaración y realización de la transacción que le pedirá a la API los chistes
        val transaccion: FragmentTransaction = manager.beginTransaction().replace(R.id.fragment_container, Main(
            getString(R.string.Url_Api_Base)+"Any?amount=10"))
        transaccion.commit()

    }
}
