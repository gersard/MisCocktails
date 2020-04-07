package cl.gerardomascayano.miscocktails.ui.mantenedor.ingrediente.viewmodel

import androidx.lifecycle.ViewModel
import cl.gerardomascayano.miscocktails.domain.mantenedor.ingrediente.MantenedorIngredienteUseCase
import cl.gerardomascayano.miscocktails.model.Ingrediente

class MantenedorIngredienteViewModel(private val useCase: MantenedorIngredienteUseCase) : ViewModel() {

    private val ingrediente by lazy { Ingrediente() }



}