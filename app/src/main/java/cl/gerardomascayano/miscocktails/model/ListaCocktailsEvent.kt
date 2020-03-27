package cl.gerardomascayano.miscocktails.model

sealed class ListaCocktailsEvent {
    data class Loading(val isLoading: Boolean) : ListaCocktailsEvent()
    data class Sucess(val listCocktails: List<Cocktail>): ListaCocktailsEvent()
    data class Failure(val message: String): ListaCocktailsEvent()
}