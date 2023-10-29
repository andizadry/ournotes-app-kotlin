package rizkyfadilah.binar.synrgy6.android.challengechapter4.viewmodel.login_vm

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import rizkyfadilah.binar.synrgy6.android.challengechapter4.repo.RegisterRepository

// Kelas yang mengimplementasikan ViewModelProvider.Factory untuk membuat ViewModel LoginViewModel.
class LoginFactory(
    private val repository: RegisterRepository,
    private val application: Application,
) : ViewModelProvider.Factory {

    // Fungsi ini digunakan untuk membuat instance dari LoginViewModel.
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Memeriksa apakah kelas model yang diminta adalah LoginViewModel.
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            // Jika iya, maka buat instance LoginViewModel dengan menggunakan repository dan application yang diberikan.
            return LoginViewModel(repository, application) as T
        }
        // Jika kelas model yang diminta tidak dikenal, lemparkan pengecualian.
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
