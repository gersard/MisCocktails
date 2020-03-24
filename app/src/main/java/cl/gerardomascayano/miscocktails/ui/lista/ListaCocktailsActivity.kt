package cl.gerardomascayano.miscocktails.ui.lista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import cl.gerardomascayano.miscocktails.R
import cl.gerardomascayano.miscocktails.data.lista.ListaCocktailsRepositoryImpl
import cl.gerardomascayano.miscocktails.domain.lista.ListaCocktailsUseCaseImpl

class ListaCocktailsActivity : AppCompatActivity() {

    private val listCocktailsViewModel: ListaCocktailsViewModel by lazy {
        ViewModelProvider(
            this,
            ListaCocktailsViewModelFactory(ListaCocktailsUseCaseImpl(ListaCocktailsRepositoryImpl()))
        ).get(ListaCocktailsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
