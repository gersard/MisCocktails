package cl.gerardomascayano.miscocktails.ui.lista.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.gerardomascayano.miscocktails.domain.lista.ListaCocktailsUseCase

class ListaCocktailsViewModelFactory(private val useCase: ListaCocktailsUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ListaCocktailsUseCase::class.java).newInstance(useCase)
    }
}