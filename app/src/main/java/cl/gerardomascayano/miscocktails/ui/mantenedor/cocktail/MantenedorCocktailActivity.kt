package cl.gerardomascayano.miscocktails.ui.mantenedor.cocktail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import cl.gerardomascayano.miscocktails.data.mantenedor.MantenedorCocktailRepositoryImpl
import cl.gerardomascayano.miscocktails.databinding.ActivityMantenedorCocktailBinding
import cl.gerardomascayano.miscocktails.domain.mantenedor.cocktail.MantenedorCocktailUseCaseImpl
import cl.gerardomascayano.miscocktails.model.Ingrediente
import cl.gerardomascayano.miscocktails.ui.mantenedor.cocktail.viewmodel.MantenedorCocktailViewModel
import cl.gerardomascayano.miscocktails.ui.mantenedor.cocktail.viewmodel.MantenedorCocktailViewModelFactory
import cl.gerardomascayano.miscocktails.ui.mantenedor.common.IngredienteCallback
import cl.gerardomascayano.miscocktails.ui.mantenedor.ingrediente.MantenedorIngredienteDialog

class MantenedorCocktailActivity : AppCompatActivity(), IngredienteCallback {

    private lateinit var viewBinding: ActivityMantenedorCocktailBinding
    private val viewModel: MantenedorCocktailViewModel by lazy {
        ViewModelProvider(
            this,
            MantenedorCocktailViewModelFactory(
                MantenedorCocktailUseCaseImpl(
                    MantenedorCocktailRepositoryImpl()
                )
            )
        ).get(MantenedorCocktailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMantenedorCocktailBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.ibAddIngrediente.setOnClickListener { showIngredientDialog() }
    }

    private fun showIngredientDialog() {
        MantenedorIngredienteDialog.newInstance().show(supportFragmentManager, "")
    }

    override fun ingredienteAdded(ingrediente: Ingrediente) {

    }
}
