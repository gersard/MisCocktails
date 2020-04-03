package cl.gerardomascayano.miscocktails.model.event

sealed class MantenedorCocktailEvent {
    data class Loading(val isLoading: Boolean) : MantenedorCocktailEvent()
    object Success : MantenedorCocktailEvent()
    data class Failure(val message: String) : MantenedorCocktailEvent()
}