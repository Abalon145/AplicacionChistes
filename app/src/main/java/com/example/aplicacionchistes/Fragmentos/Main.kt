package com.example.aplicacionchistes.Fragmentos

//Clase que contiene toda la funcionalidad del fragmento "fragment_main.xml"

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.aplicacionchistes.Adaptadores.AdaptadorChistes
import com.example.aplicacionchistes.R
import com.example.aplicacionchistes.modelo.Chiste
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

//Clase que hereda de Fragment, para su construcción toma un valor String que funcionará como URL a la que hacer peticiones
public class Main constructor(url: String): Fragment() {

        //Declaración de variables
        //Contiene la URL a la que se harán las peticiones
        var chisteApiURL: String = url

        //RecyclerView que contiene todos los chistes recibidos por la API
        lateinit var listaChistes: RecyclerView

        //Adaptador de la lista de chistes
        lateinit var chisteAdapter: AdaptadorChistes

        //ArrayList que contiene los chistes que se mostrarán en el fragmento
        var listChistes: MutableList<Chiste> = arrayListOf()

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val v: View = inflater.inflate(R.layout.fragment_main, container, false)
            listaChistes = v.findViewById(R.id.listaChistes)
            listaChistes.setLayoutManager(LinearLayoutManager(v.context))

            //Se hace la petición a la URL
            getChistes(chisteApiURL)

            //Declaración de adaptador
            chisteAdapter = AdaptadorChistes(listChistes)
            listaChistes.setAdapter(chisteAdapter)
            return v
        }

    //Función que dada una URL, hará una petición GET hacia dicha URL
    fun getChistes (url: String) {

        //Uso de Volley para hacer las peticiones
        //Se le pide a la API un objeto JSON que contenga todos los chistes
        var queue: RequestQueue = Volley.newRequestQueue(context)
        var request: JsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url,
            null, { response ->

                //Se inicia un bloque "try" en caso de que haya algún problema con los objetos JSON
                try {

                    //Variable que funcionará como contador en el bucle que almacenará los chistes en
                    //"listChistes" para ser mostrados por pantalla
                    var i: Int = 0

                    val cantidad: Int = response.getString("amount").toInt()

                    var arrayChistes: JSONArray = response.getJSONArray("jokes")

                    while (i < cantidad) {
                        var chistes: JSONObject = arrayChistes.getJSONObject(i)

                        var c = Chiste()

                        c.tipo = chistes.getString("type")

                        //Se comprueba si el chiste está compuesto por una parte (type = single) o
                        //por dos partes (else)
                        if (chistes.getString("type").equals("single")) {
                            c.chiste = chistes.getString("joke")
                        } else {
                            c.setup = chistes.getString("setup")
                            c.payoff = chistes.getString("payoff")
                        }

                        //Se añade el chiste a la lista y se notifica al adaptador que ha habido
                        //un cambio en la información
                        listChistes.add(c)
                        chisteAdapter.notifyDataSetChanged()

                        //Se aumenta el valor de "i" para que el bucle continue
                        i++
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error ->

            })

        //Se añade a la cola de peticiones de Volley la petición de JSON
        queue.add(request)


    }
}