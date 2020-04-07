package cl.gerardomascayano.miscocktails.ui.mantenedor.cocktail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.gerardomascayano.miscocktails.domain.mantenedor.MantenedorCocktailUseCase
import cl.gerardomascayano.miscocktails.model.Ingrediente
import cl.gerardomascayano.miscocktails.model.event.MantenedorCocktailEvent

class MantenedorCocktailViewModel(private val useCase: MantenedorCocktailUseCase) : ViewModel() {

    private var _mantenedorCocktailEvent = MutableLiveData<MantenedorCocktailEvent>()
    val mantenedorCocktailEvent: LiveData<MantenedorCocktailEvent>
        get() = _mantenedorCocktailEvent


    fun validateCocktail(nombre: String, ingredientes: List<Ingrediente>, urlImagen: String?) {
        if (!useCase.validateNombre(nombre)) {
            _mantenedorCocktailEvent.value = MantenedorCocktailEvent.Failure("Debes ingresar el nombre del cocktail")
            return
        }
        if (!useCase.validateIngredientes(ingredientes)) {
            _mantenedorCocktailEvent.value = MantenedorCocktailEvent.Failure("Debes ingresar al menos 2 ingredientes")
            return
        }
        if (!useCase.validateUrlImagen(urlImagen)) {
            _mantenedorCocktailEvent.value = MantenedorCocktailEvent.Failure("Debes tomar una fotografía o ingresar su url")
            return
        }
    }

}