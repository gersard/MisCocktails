package cl.gerardomascayano.miscocktails.ui.mantenedor.cocktail

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
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
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import timber.log.Timber

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
        startActivityForResult(Intent(this, CameraActivity::class.java), CameraActivity.REQUEST_CODE_PHOTO)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CameraActivity.REQUEST_CODE_PHOTO) {
            data?.let {
                val uri = Uri.parse(it.getStringExtra(CameraActivity.RESULT_URI))
                Glide.with(this)
                    .load(uri)
                    .centerCrop()
                    .into(viewBinding.ivFoto)
            }
        }
    }

}
