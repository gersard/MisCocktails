package cl.gerardomascayano.miscocktails.ui.mantenedor.cocktail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cl.gerardomascayano.miscocktails.data.mantenedor.MantenedorCocktailRepositoryImpl
import cl.gerardomascayano.miscocktails.databinding.ActivityMantenedorCocktailBinding
import cl.gerardomascayano.miscocktails.domain.mantenedor.cocktail.MantenedorCocktailUseCaseImpl
import cl.gerardomascayano.miscocktails.model.Ingrediente
import cl.gerardomascayano.miscocktails.ui.camera.CameraActivity
import cl.gerardomascayano.miscocktails.ui.mantenedor.cocktail.adapter.IngredienteMantenedorAdapter
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
        viewBinding.btnTomarFoto.setOnClickListener { showCamera() }
        configureRecyclerView()
    }

    private fun showCamera() {
        startActivity(Intent(this, CameraActivity::class.java))
    }

    private fun configureRecyclerView() {
        viewBinding.rvIngredientes.setHasFixedSize(true)
        viewBinding.rvIngredientes.layoutManager = LinearLayoutManager(this)
        viewBinding.rvIngredientes.adapter = IngredienteMantenedorAdapter(viewModel.ingredientes)
    }

    private fun showIngredientDialog() {
        MantenedorIngredienteDialog.newInstance().show(supportFragmentManager, "")
    }

    override fun ingredienteAdded(ingrediente: Ingrediente) {
        viewModel.addIngrediente(ingrediente)
        viewBinding.rvIngredientes.adapter?.notifyItemInserted(0)
    }
}
