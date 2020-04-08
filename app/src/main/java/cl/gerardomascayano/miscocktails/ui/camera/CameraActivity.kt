package cl.gerardomascayano.miscocktails.ui.camera

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
        if (isPermissionGranted()) {
            viewBinding.viewCamera.takePicture()
        } else {
            requestPermission()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission_group.STORAGE, Manifest.permission_group.CAMERA), REQUEST_PERMISSION)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION){

        }
    }

    private fun isPermissionGranted(): Boolean =
        ContextCompat.checkSelfPermission(this, Manifest.permission_group.STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission_group.CAMERA) == PackageManager.PERMISSION_GRANTED

    companion object {
        const val REQUEST_PERMISSION = 100
    }

}
