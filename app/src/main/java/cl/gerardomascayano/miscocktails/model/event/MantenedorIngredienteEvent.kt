package cl.gerardomascayano.miscocktails.model.event

import cl.gerardomascayano.miscocktails.model.Ingrediente

sealed class MantenedorIngredienteEvent {
    data class Loading(val isLoading: Boolean) : MantenedorIngredienteEvent()
    data class Success(val ingrediente: Ingrediente) : MantenedorIngredienteEvent()
    data class Failure(val message: String) : MantenedorIngredienteEvent()
}