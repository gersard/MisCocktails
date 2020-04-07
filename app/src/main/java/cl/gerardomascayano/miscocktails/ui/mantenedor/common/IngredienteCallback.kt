package cl.gerardomascayano.miscocktails.ui.mantenedor.common

import cl.gerardomascayano.miscocktails.model.Ingrediente

interface IngredienteCallback {
    fun ingredienteAdded(ingrediente: Ingrediente)
}