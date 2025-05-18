package com.example.aplicacionchistes.Adaptadores

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionchistes.Fragmentos.Main
import com.example.aplicacionchistes.R


//Clase encargada de crear y manejar las vistas relacionadas con la lista de categorias
public class AdaptadorCategorias: RecyclerView.Adapter<AdaptadorCategorias.ViewHolder> {

    //Variable que contendrá la lista de categorias de chistes
    var listCategorias: List<String> = listOf()

    //Variable que indicará la última categoria elegida
    //Por defecto es "0", para la categoria "Cualquiera"
    var posicionCat: Int = 0

    constructor(listCat: List<String>) {
        listCategorias = listCat
    }

    //Método que crea el ViewHolder
    //Devuelve un objeto tipo vista (View)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.categoria_view, parent, false)
        return ViewHolder(v)
    }

    //Método que devuelve el tamaño de la lista "listCategorias"
    override fun getItemCount(): Int {
        return listCategorias.size
    }

    //Método que insertará la información de la lista en la vista
    override fun onBindViewHolder(holder: AdaptadorCategorias.ViewHolder, position: Int) {
        holder.nombreCat.setText(listCategorias.get(position))

    }

    //Clase ViewHolder que enlaza una variable TextView y CardView con sus respectivos componentes en la view
    //Identificados por su ID
    inner public class ViewHolder: RecyclerView.ViewHolder, View.OnClickListener {

        var nombreCat: TextView
        var cardItem: CardView

        //Declaración de constructor con objeto "itemView", necesario para el funcionamiento de ViewHolder
        constructor(itemView: View) : super(itemView) {
            super.itemView

            nombreCat = itemView.findViewById(R.id.nombreCat)
            cardItem = itemView.findViewById(R.id.cardItem)

            //Declaración de un listener en "itemView"
            itemView.setOnClickListener(this)
        }

        //Método que al hacer click en el componente (en este caso, un botón de categoría) cargará
        //el fragmento que contiene los chistes de la categoría elegida
        override fun onClick(v: View) {

            //Se notifica el cambio de valor en "posicionCat"
            notifyItemChanged(posicionCat)

            //Se le da a "posicionCat" su nuevo valor
            posicionCat = absoluteAdapterPosition

            //Se notifica el cambio de valor en "posicionCat"
            notifyItemChanged(posicionCat)

            //Estructura condicional donde, dado el contenido de "listCategorias" en la posicion
            //igual a "posicionCat", se mostrará un Toast indicando el botón pulsado y se cargará
            //el fragmento con la lista de chistes de la categoría elegida
            when (listCategorias[posicionCat]) {

                "Cualquiera" -> {Toast.makeText(v.context,"Mostrando categoría cualquiera", Toast.LENGTH_SHORT).show()
                    loadFragment(v, Main(v.context.getString(R.string.Url_Api_Base)+"Any?amount=10"))
                }

                "Programación" -> {Toast.makeText(v.context,"Mostrando categoría programación", Toast.LENGTH_SHORT).show()
                    loadFragment(v, Main(v.context.getString(R.string.Url_Api_Base)+"Programming?amount=10"))
                }
                "Misceláneo" -> {Toast.makeText(v.context,"Mostrando categoría misceláneo", Toast.LENGTH_SHORT).show()
                    loadFragment(v, Main(v.context.getString(R.string.Url_Api_Base)+"Miscellaneous?amount=10"))
                }
                "Humor negro" -> {Toast.makeText(v.context,"Mostrando categoría humor negro", Toast.LENGTH_SHORT).show()
                    loadFragment(v, Main(v.context.getString(R.string.Url_Api_Base)+"Dark?amount=10"))

                }
                "Juegos de palabras" -> {Toast.makeText(v.context,"Mostrando categoría juegos de palabras", Toast.LENGTH_SHORT).show()
                    loadFragment(v, Main(v.context.getString(R.string.Url_Api_Base)+"Pun?amount=10"))
                }
                "Terror" -> {Toast.makeText(v.context,"Mostrando categoría terror", Toast.LENGTH_SHORT).show()
                    loadFragment(v, Main(v.context.getString(R.string.Url_Api_Base)+"Spooky?amount=10"))
                }
                "Navidad" -> {Toast.makeText(v.context,"Mostrando categoría navidad", Toast.LENGTH_SHORT).show()
                    loadFragment(v, Main(v.context.getString(R.string.Url_Api_Base)+"Christmas?amount=10"))
                }
            }

        }

        //Función encargada de cargar los fragmentos y de realizar las transacciones con la API de chistes
        fun loadFragment (v: View, fragment: Fragment){
            val activity: AppCompatActivity = v.context as AppCompatActivity
            val manager: FragmentManager = activity.supportFragmentManager
            val transaccion: FragmentTransaction = manager.beginTransaction().replace(R.id.fragment_container,
                fragment)
            transaccion.commit()

        }







    }



}