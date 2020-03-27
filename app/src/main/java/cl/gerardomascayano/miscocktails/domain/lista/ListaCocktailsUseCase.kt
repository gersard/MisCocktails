package cl.gerardomascayano.miscocktails.domain.lista

import cl.gerardomascayano.miscocktails.model.Cocktail

interface ListaCocktailsUseCase {

    suspend fun getListCocktails(): MutableList<Cocktail>?

}