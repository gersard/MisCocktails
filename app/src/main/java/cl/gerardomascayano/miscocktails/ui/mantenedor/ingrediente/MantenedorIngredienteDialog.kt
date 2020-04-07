package cl.gerardomascayano.miscocktails.ui.mantenedor.ingrediente

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import cl.gerardomascayano.miscocktails.databinding.DialogMantenedorIngredienteDialogBinding
import cl.gerardomascayano.miscocktails.ui.mantenedor.common.IngredienteCallback

class MantenedorIngredienteDialog : DialogFragment() {

    private var _viewBinding: DialogMantenedorIngredienteDialogBinding? = null
    private val viewBinding get() = _viewBinding!!
    private var ingredienteListener: IngredienteCallback? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ingredienteListener = context as IngredienteCallback
    }

    override fun onStart() {
        super.onStart()
        setStyle(STYLE_NO_FRAME, 0)
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _viewBinding = DialogMantenedorIngredienteDialogBinding.inflate(inflater, container, false)
        viewBinding.btnCancelar.setOnClickListener { dismiss() }
        viewBinding.btnAnadir.setOnClickListener { anadirIngrediente() }
        return viewBinding.root
    }

    private fun anadirIngrediente() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }


    companion object {
        fun newInstance() = MantenedorIngredienteDialog()
    }
}