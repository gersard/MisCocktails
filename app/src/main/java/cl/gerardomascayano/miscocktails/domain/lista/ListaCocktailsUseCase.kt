package cl.gerardomascayano.miscocktails.domain.lista

import cl.gerardomascayano.miscocktails.data.model.Cocktail

interface ListaCocktailsUseCase {

    suspend fun getListCocktails(): MutableList<Cocktail>?

}