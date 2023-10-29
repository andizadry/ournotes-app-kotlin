package rizkyfadilah.binar.synrgy6.android.challengechapter4.viewmodel.note_vm

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// Kelas yang mengimplementasikan ViewModelProvider.Factory untuk membuat ViewModel NotesViewModel.
class NotesFactory(
    private val application: Application,
) : ViewModelProvider.Factory {

    // Fungsi ini digunakan untuk membuat instance dari NotesViewModel.
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Memeriksa apakah kelas model yang diminta adalah NotesViewModel.
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            // Jika iya, maka buat instance NotesViewModel dengan menggunakan aplikasi (Application) yang diberikan.
            return NotesViewModel(application) as T
        }
        // Jika kelas model yang diminta tidak dikenal, lemparkan pengecualian.
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
