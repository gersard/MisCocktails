package cl.gerardomascayano.miscocktails.ui.mantenedor.ingrediente.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.gerardomascayano.miscocktails.domain.mantenedor.ingrediente.MantenedorIngredienteUseCase
import cl.gerardomascayano.miscocktails.model.Ingrediente
import cl.gerardomascayano.miscocktails.model.event.MantenedorIngredienteEvent

class MantenedorIngredienteViewModel(private val useCase: MantenedorIngredienteUseCase) : ViewModel() {

    private var _mantenedorIngredienteEvent = MutableLiveData<MantenedorIngredienteEvent>()
    val mantenedorIngredienteEvent: LiveData<MantenedorIngredienteEvent>
        get() = _mantenedorIngredienteEvent

    fun saveIngrediente(nombre: String?, cantidadUm: String?, cantidad: String?) {
        if (validarIngrediente(nombre, cantidadUm, cantidad)) {
            val ingrediente = Ingrediente(useCase.formatNombre(nombre), useCase.formatCantidad(cantidad), cantidadUm!!)
            _mantenedorIngredienteEvent.value = MantenedorIngredienteEvent.Success(ingrediente)
        }
    }

    private fun validarIngrediente(nombre: String?, cantidadUm: String?, cantidad: String?): Boolean {
        if (!useCase.validarNombre(nombre)) {
            _mantenedorIngredienteEvent.value = MantenedorIngredienteEvent.Failure("Debes ingresar el nombre del ingrediente")
            return false
        }
        if (!useCase.validarUnidadMedida(cantidadUm)) {
            _mantenedorIngredienteEvent.value = MantenedorIngredienteEvent.Failure("Debes seleccionar la unidad de medida")
            return false
        }
        if (!useCase.validarCantidad(cantidad)) {
            _mantenedorIngredienteEvent.value = MantenedorIngredienteEvent.Failure("Debes ingresar la cantidad")
            return false
        }
        return true
    }
}