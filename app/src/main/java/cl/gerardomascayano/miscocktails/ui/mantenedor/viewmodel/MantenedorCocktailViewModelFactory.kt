package cl.gerardomascayano.miscocktails.ui.mantenedor.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.gerardomascayano.miscocktails.domain.mantenedor.MantenedorCocktailUseCase

class MantenedorCocktailViewModelFactory(private val useCase: MantenedorCocktailUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MantenedorCocktailUseCase::class.java).newInstance(useCase)
    }
}