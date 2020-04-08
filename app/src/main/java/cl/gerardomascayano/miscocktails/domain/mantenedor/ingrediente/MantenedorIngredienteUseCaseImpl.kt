package cl.gerardomascayano.miscocktails.domain.mantenedor.ingrediente

import android.annotation.SuppressLint
import android.text.TextUtils

class MantenedorIngredienteUseCaseImpl : MantenedorIngredienteUseCase {
    override fun validarNombre(nombre: String?): Boolean = !TextUtils.isEmpty(nombre)
    override fun validarUnidadMedida(um: String?): Boolean = !TextUtils.isEmpty(um)

    override fun validarCantidad(cantidad: String?): Boolean {
        if (TextUtils.isEmpty(cantidad)) return false
        if ((cantidad!!.toInt()) < 0) return false
        return true
    }
    @SuppressLint("DefaultLocale")
    override fun formatNombre(nombre: String?): String = nombre!!.capitalize()
    override fun formatCantidad(cantidad: String?): Int = cantidad!!.toInt()
}