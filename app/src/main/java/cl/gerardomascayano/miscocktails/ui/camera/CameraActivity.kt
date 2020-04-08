package cl.gerardomascayano.miscocktails.ui.camera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cl.gerardomascayano.miscocktails.R
import cl.gerardomascayano.miscocktails.databinding.ActivityCameraBinding
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.PictureResult

class CameraActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityCameraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.viewCamera.setLifecycleOwner(this)
        viewBinding.ibTakePicture.setOnClickListener { takePicture() }
        listenPictureTaking()
    }

    private fun listenPictureTaking() {
        viewBinding.viewCamera.addCameraListener(object : CameraListener() {
            override fun onPictureTaken(result: PictureResult) {
//                result.tofile
            }
        })
    }

    private fun takePicture() {
        viewBinding.viewCamera.takePicture()
    }


}
