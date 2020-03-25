package cl.gerardomascayano.miscocktails.data.model

import com.google.gson.annotations.SerializedName

data class Ingrediente(
    val nombre: String = "",
    val cantidad: Int = 0,
    @SerializedName("cantidad_um")
    val cantidadUm: String = ""
)