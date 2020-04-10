package cl.gerardomascayano.miscocktails.ui.camera

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otaliastudios.cameraview.PictureResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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