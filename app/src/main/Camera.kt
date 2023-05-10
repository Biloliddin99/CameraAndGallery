import android.os.Environment
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale

class Camera {

    var currentImagePath = ""
    fun createTempFile(): File {
        val format = SimpleDateFormat("yyyyMMdd_HHmmss", java.util.Locale.getDefault()).format()
        val externalFilesDir = getExternalFilesDir(android.os.Environment.DIRECTORY_PICTURES)
        return
    }
}