package cl.gerardomascayano.miscocktails.domain.mantenedor

import android.annotation.SuppressLint
import android.text.TextUtils
import android.util.Patterns
import cl.gerardomascayano.miscocktails.data.mantenedor.MantenedorCocktailRepository
import cl.gerardomascayano.miscocktails.model.Ingrediente

class MantenedorCocktailUseCaseImpl(val repository: MantenedorCocktailRepository) : MantenedorCocktailUseCase {

    override fun validateNombre(nombre: String?): Boolean = !TextUtils.isEmpty(nombre)

    @SuppressLint("DefaultLocale")
    override fun formatNombre(nombre: String): String = nombre.capitalize()
    override fun validateIngredientes(ingredientes: List<Ingrediente>): Boolean = ingredientes.size >= 2
    override fun validateUrlImagen(url: String?): Boolean = if (url != null) Patterns.WEB_URL.matcher(url).matches() else false

}