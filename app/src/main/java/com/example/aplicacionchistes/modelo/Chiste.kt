package com.example.aplicacionchistes.modelo


//Clase que describe los atributos que queremos recoger de un chiste pedido a la api
class Chiste () {

    //Tipo de chiste, su categoría
    var tipo: String = ""

    //Primera parte del chiste, solo se utiliza si el chiste tiene dos partes
    var setup: String = ""

    //Segunda parte del chiste, solo se utiliza si el chiste tiene dos partes
    var payoff: String = ""

    //Chiste completo, solo se utiliza si el chiste tiene una única parte
    var chiste: String = ""


}