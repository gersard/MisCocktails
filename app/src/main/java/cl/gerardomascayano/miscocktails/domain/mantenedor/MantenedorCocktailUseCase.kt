package cl.gerardomascayano.miscocktails.domain.mantenedor

import cl.gerardomascayano.miscocktails.model.Ingrediente

interface MantenedorCocktailUseCase {

    fun validateNombre(nombre: String?): Boolean
    fun formatNombre(nombre: String): String
    fun validateIngredientes(ingredientes: List<Ingrediente>): Boolean
    fun validateUrlImagen(url: String?): Boolean
}