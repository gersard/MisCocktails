package cl.gerardomascayano.miscocktails.ui.camera

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import cl.gerardomascayano.miscocktails.databinding.ActivityCameraBinding
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.PictureResult
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

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
                result.toBitmap { saveImage(it!!) }
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
        if (requestCode == REQUEST_PERMISSION) {
            takePicture()
        }
    }

    private fun isPermissionGranted(): Boolean =
        ContextCompat.checkSelfPermission(this, Manifest.permission_group.STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission_group.CAMERA) == PackageManager.PERMISSION_GRANTED

    private fun saveImage(bitmap: Bitmap) {
        val fos: OutputStream?

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val contentValues = ContentValues()
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "fotoprueba.jpeg")
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            val imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            fos = contentResolver.openOutputStream(imageUri!!)
        } else {
            val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()
            val imageFile = File(dir, "fotoprueba.jpeg")
            fos = FileOutputStream(imageFile)
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos)
    }

    companion object {
        const val REQUEST_PERMISSION = 100
    }

}
