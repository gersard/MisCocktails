package cl.gerardomascayano.miscocktails.ui.camera

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otaliastudios.cameraview.PictureResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class CameraViewModel : ViewModel() {

    private val _processImageResult = MutableLiveData<Bitmap?>()
    val processImageResult: LiveData<Bitmap?> get() = _processImageResult

    fun processBitmap(result: PictureResult) {
        viewModelScope.launch(Dispatchers.Main) {
            result.toBitmap {
                _processImageResult.value = it
            }
        }
    }

    fun scanFile(context: Context?, file: File) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            val mediaScannerIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
            val fileContentUri = Uri.fromFile(file)
            mediaScannerIntent.data = fileContentUri
            context?.sendBroadcast(mediaScannerIntent)
        }
    }


//    private lateinit var job: Job
//    override val coroutineContext: CoroutineContext
//        get() = Dispatchers.IO + job
//
//    init {
//        job = SupervisorJob()
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        job.cancel()
//    }


}