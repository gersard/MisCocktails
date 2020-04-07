package cl.gerardomascayano.miscocktails.ui.mantenedor.ingrediente.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.gerardomascayano.miscocktails.domain.mantenedor.ingrediente.MantenedorIngredienteUseCase

class MantenedorIngredienteViewModelFactory(private val useCase: MantenedorIngredienteUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MantenedorIngredienteUseCase::class.java).newInstance(useCase)
    }
}