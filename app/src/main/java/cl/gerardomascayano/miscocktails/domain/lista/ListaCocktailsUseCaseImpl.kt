package cl.gerardomascayano.miscocktails.domain.lista

import cl.gerardomascayano.miscocktails.data.lista.ListaCocktailsRepository
import cl.gerardomascayano.miscocktails.data.model.Cocktail

class ListaCocktailsUseCaseImpl(private val listCokctailsRepository: ListaCocktailsRepository) : ListaCocktailsUseCase {

    override suspend fun getListCocktails(): MutableList<Cocktail>? {
        return listCokctailsRepository.getCocktails()
    }


}