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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cl.gerardomascayano.miscocktails.R
import cl.gerardomascayano.miscocktails.databinding.ActivityCameraBinding
import cl.gerardomascayano.miscocktails.ui.mantenedor.cocktail.MantenedorCocktailActivity
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.PictureResult
import com.otaliastudios.cameraview.controls.Flash
import com.otaliastudios.cameraview.size.Size
import com.otaliastudios.cameraview.size.SizeSelector
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.util.*

class CameraActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityCameraBinding
    private val viewModel by lazy { ViewModelProvider(this).get(CameraViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.viewCamera.setLifecycleOwner(this)
        viewBinding.ibTakePicture.setOnClickListener { takePicture() }
        viewBinding.ibFlash.setOnClickListener { handleFlash() }
        listenPictureTaking()
        observeImageResult()
        configPictureSize()
    }

    private fun handleFlash() {
        viewBinding.viewCamera.flash = when (viewBinding.viewCamera.flash) {
            Flash.ON -> {
                viewBinding.ibFlash.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_flash_disabled))
                Flash.OFF
            }
            Flash.OFF -> {
                viewBinding.ibFlash.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_flash_torch))
                Flash.TORCH
            }
            Flash.AUTO -> {
                viewBinding.ibFlash.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_flash_torch))
                Flash.TORCH
            }
            Flash.TORCH -> {
                viewBinding.ibFlash.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_flash_automatic))
                Flash.ON
            }
        }
    }

    private fun configPictureSize() {
        viewBinding.viewCamera.setPictureSize { source ->
            source.forEach { Timber.d("Size: ${it.width}x${it.height}") }
            source
        }
    }

    private fun observeImageResult() {
        viewModel.processImageResult.observe(this, Observer { bitmap ->
            bitmap?.let { saveImage(bitmap) }
                ?: kotlin.run { Toast.makeText(this, "Error al procesar la imágen", Toast.LENGTH_LONG).show() }
        })
    }

    private fun listenPictureTaking() {
        viewBinding.viewCamera.addCameraListener(object : CameraListener() {
            override fun onPictureTaken(result: PictureResult) {
                viewModel.processBitmap(result)
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
        ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

    private fun saveImage(bitmap: Bitmap) {
        val fos: OutputStream?
        val uri: Uri?

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val contentValues = ContentValues()
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "${UUID.randomUUID()}.jpeg")
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            val imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            fos = contentResolver.openOutputStream(imageUri!!)
            uri = imageUri
        } else {
            val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            if (!dir.exists()) dir.mkdir()
            val imageFile = File(dir, "${UUID.randomUUID()}.jpeg")
            fos = FileOutputStream(imageFile)
            viewModel.scanFile(this, imageFile)
            uri = Uri.fromFile(imageFile)
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos)
        fos?.flush()
        fos?.close()
        finishScreen(uri)
    }

    private fun finishScreen(uri: Uri?) {
        val intent = Intent()
        intent.putExtra(RESULT_URI, uri?.toString())
        setResult(REQUEST_CODE_PHOTO, intent)
        finish()
    }

    companion object {
        const val REQUEST_PERMISSION = 100

        const val REQUEST_CODE_PHOTO = 1
        const val RESULT_URI = "result_uri"

    }

}
