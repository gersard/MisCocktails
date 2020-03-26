package cl.gerardomascayano.miscocktails.data.lista

import cl.gerardomascayano.miscocktails.data.model.Cocktail

interface ListaCocktailsRepository {

    suspend fun getCocktails(): MutableList<Cocktail>?

}