package rizkyfadilah.binar.synrgy6.android.challengechapter4.viewmodel.register_vm

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import rizkyfadilah.binar.synrgy6.android.challengechapter4.repo.RegisterRepository

// Kelas yang mengimplementasikan ViewModelProvider.Factory untuk membuat ViewModel RegisterViewModel.
class RegisterFactory(
    private val repository: RegisterRepository,
    private val application: Application,
) : ViewModelProvider.Factory {

    // Fungsi ini digunakan untuk membuat instance dari RegisterViewModel.
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Memeriksa apakah kelas model yang diminta adalah RegisterViewModel.
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            // Jika iya, maka buat instance RegisterViewModel dengan menggunakan repository dan aplikasi (Application) yang diberikan.
            return RegisterViewModel(repository, application) as T
        }
        // Jika kelas model yang diminta tidak dikenal, maka akan dilemparkan pengecualian.
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
