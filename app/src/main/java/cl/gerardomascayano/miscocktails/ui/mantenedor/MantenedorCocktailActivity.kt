package cl.gerardomascayano.miscocktails.ui.mantenedor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import cl.gerardomascayano.miscocktails.data.mantenedor.MantenedorCocktailRepositoryImpl
import cl.gerardomascayano.miscocktails.databinding.ActivityMantenedorCocktailBinding
import cl.gerardomascayano.miscocktails.domain.mantenedor.MantenedorCocktailUseCaseImpl
import cl.gerardomascayano.miscocktails.ui.mantenedor.viewmodel.MantenedorCocktailViewModel
import cl.gerardomascayano.miscocktails.ui.mantenedor.viewmodel.MantenedorCocktailViewModelFactory

class MantenedorCocktailActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMantenedorCocktailBinding
    private val viewModel: MantenedorCocktailViewModel by lazy {
        ViewModelProvider(
            this,
            MantenedorCocktailViewModelFactory(MantenedorCocktailUseCaseImpl(MantenedorCocktailRepositoryImpl()))
        ).get(MantenedorCocktailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMantenedorCocktailBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}
