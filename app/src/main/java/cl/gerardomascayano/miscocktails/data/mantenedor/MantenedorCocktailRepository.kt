package cl.gerardomascayano.miscocktails.data.mantenedor

import cl.gerardomascayano.miscocktails.model.Cocktail

interface MantenedorCocktailRepository {

    fun saveCocktail(cocktail: Cocktail)

}