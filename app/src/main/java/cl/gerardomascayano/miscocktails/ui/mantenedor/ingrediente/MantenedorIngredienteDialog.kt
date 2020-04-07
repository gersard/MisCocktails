package cl.gerardomascayano.miscocktails.ui.mantenedor.ingrediente

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import cl.gerardomascayano.miscocktails.databinding.DialogMantenedorIngredienteDialogBinding

class MantenedorIngredienteDialog : DialogFragment() {

    private var _viewBinding: DialogMantenedorIngredienteDialogBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onStart() {
        super.onStart()
        setStyle(DialogFragment.STYLE_NO_FRAME, 0)
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _viewBinding = DialogMantenedorIngredienteDialogBinding.inflate(inflater, container, false)
        viewBinding.btnCancelar.setOnClickListener { dismiss() }
        return viewBinding.root
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