package cl.gerardomascayano.miscocktails.domain.mantenedor.ingrediente

interface MantenedorIngredienteUseCase {
    fun validarNombre(nombre: String?): Boolean
    fun validarUnidadMedida(um: String?): Boolean
    fun validarCantidad(cantidad: String?): Boolean
    fun formatNombre(nombre: String): String
    fun formatCantidad(cantidad: String): Float
}