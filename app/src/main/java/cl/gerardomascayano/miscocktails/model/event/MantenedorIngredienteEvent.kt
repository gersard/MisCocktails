package cl.gerardomascayano.miscocktails.model.event

sealed class MantenedorIngredienteEvent {
    data class Loading(val isLoading: Boolean) : MantenedorIngredienteEvent()
    object Success : MantenedorIngredienteEvent()
    data class Failure(val message: String) : MantenedorIngredienteEvent()
}