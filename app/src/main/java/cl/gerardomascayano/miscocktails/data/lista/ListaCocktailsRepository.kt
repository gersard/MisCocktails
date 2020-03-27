package cl.gerardomascayano.miscocktails.data.lista

import cl.gerardomascayano.miscocktails.model.Cocktail

interface ListaCocktailsRepository {

    suspend fun getCocktails(): MutableList<Cocktail>?

}