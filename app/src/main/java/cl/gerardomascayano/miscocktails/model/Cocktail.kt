package cl.gerardomascayano.miscocktails.model

import com.google.gson.annotations.SerializedName

data class Cocktail(
    val id: String = "",
    val nombre: String = "",
    val cantidad: Int = 0,
    @SerializedName("cantidad_um")
    val cantidadUm: String = "oz",
    val imagen: String? = null,
    val ingredientes: List<Ingrediente>? = null,
    val preparacion: String = "",
    val notas: String = ""
)