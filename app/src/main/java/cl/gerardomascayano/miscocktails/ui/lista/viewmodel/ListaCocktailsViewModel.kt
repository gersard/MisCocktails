package cl.gerardomascayano.miscocktails.ui.lista.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.gerardomascayano.miscocktails.data.model.ListaCocktailsEvent
import cl.gerardomascayano.miscocktails.domain.lista.ListaCocktailsUseCase
import kotlinx.coroutines.launch

class ListaCocktailsViewModel(private val useCase: ListaCocktailsUseCase) : ViewModel() {

    private var _listaCocktailsEvent = MutableLiveData<ListaCocktailsEvent>()
    val listaCocktailsEvent: LiveData<ListaCocktailsEvent>
        get() = _listaCocktailsEvent

    fun getCocktails() {
        _listaCocktailsEvent.value = ListaCocktailsEvent.Loading(true)
        viewModelScope.launch {
            val listCocktails = useCase.getListCocktails()
            _listaCocktailsEvent.value = ListaCocktailsEvent.Loading(false)
            // Crear modelo response y usarlo en use case
            listCocktails?.let {
                _listaCocktailsEvent.value = ListaCocktailsEvent.Sucess(it)
            } ?: kotlin.run { _listaCocktailsEvent.value = ListaCocktailsEvent.Failure("Ocurrió un error") }

        }
    }

}