package com.example.aplicacionchistes.Adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionchistes.R
import com.example.aplicacionchistes.modelo.Chiste


//Clase encargada de crear y manejar las vistas relacionadas con las listas de chistes
class AdaptadorChistes: RecyclerView.Adapter<AdaptadorChistes.ViewHolder> {

    //Variable donde se almacenarán los chistes a mostrar
    var listChistes: List<Chiste> = listOf()

    //Constructor de la clase
    constructor(chistes: List<Chiste>) {
        this.listChistes = chistes

    }

    //Método que crea el ViewHolder
    //Devuelve un objeto tipo vista (View)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v: View = LayoutInflater.from(parent.context).inflate(R.layout.chistes_view, parent, false)
        return ViewHolder(v)
    }

    //Método que devuelve el tamaño de la lista
    override fun getItemCount(): Int {
        return listChistes.size
    }

    //Método que inserta el contenido de la lista "listChistes" en la vista
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //Si el chiste es de tipo "single", se tomará el valor de "chiste"
        //Si el chiste es de tipo "twopart"(clausula else, en este caso), se tomarán los valores de "setup" y "payoff"
        if (listChistes.get(position).tipo.equals("single")) {

            holder.primerLinea.setText(listChistes.get(position).chiste)
            holder.segundaLinea.setText("")
        } else {
            holder.primerLinea.setText(listChistes.get(position).setup)
            holder.segundaLinea.setText(listChistes.get(position).payoff)

        }
    }

    //Clase ViewHolder que enlaza dos variables TextView con sus respectivos componentes en la view
    //Identificados por su ID
    inner class ViewHolder: RecyclerView.ViewHolder {

        var primerLinea: TextView
        var segundaLinea: TextView

        constructor(itemView: View) : super(itemView) {
            super.itemView

            primerLinea = itemView.findViewById(R.id.primerLinea)
            segundaLinea = itemView.findViewById(R.id.segundaLinea)

        }
    }
}