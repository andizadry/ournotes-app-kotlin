package rizkyfadilah.binar.synrgy6.android.challengechapter4.viewmodel.login_vm

import android.app.Application
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import rizkyfadilah.binar.synrgy6.android.challengechapter4.sharepref.SharedPref
import rizkyfadilah.binar.synrgy6.android.challengechapter4.repo.RegisterRepository

class LoginViewModel(private val repository: RegisterRepository, private val application: Application) : AndroidViewModel(application), Observable {

    // MutableLiveData untuk input username
    @Bindable
    val userName = MutableLiveData<String>()

    // MutableLiveData untuk input email
    @Bindable
    val email = MutableLiveData<String>()

    // MutableLiveData untuk input password
    @Bindable
    val inputPassword = MutableLiveData<String>()

    // Job dan CoroutineScope untuk penggunaan coroutines
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    // LiveData untuk navigasi ke halaman pendaftaran
    private val _navigatetoRegister = MutableLiveData<Boolean>()
    val navigatetoRegister: LiveData<Boolean>
        get() = _navigatetoRegister

    // LiveData untuk navigasi ke halaman utama (setelah login)
    private val _navigateToHome = MutableLiveData<Boolean>()
    val navigateToHome: LiveData<Boolean>
        get() = _navigateToHome

    // LiveData untuk menampilkan pesan kesalahan
    private val _errorToast = MutableLiveData<Boolean>()
    val errorToast: LiveData<Boolean>
        get() = _errorToast

    // Fungsi yang dipicu ketika tombol "Login" ditekan melalui binding.
    fun loginButton() {
        if (email.value.isNullOrEmpty() || inputPassword.value.isNullOrEmpty()) {
            // Menampilkan pesan kesalahan jika email atau password kosong.
            _errorToast.value = true
        } else {
            uiScope.launch {
                val email = repository.getEmail(email.value!!)
                if (email != null) {
                    if (email.userPassword == inputPassword.value!!) {
                        // Jika email dan password sesuai, maka navigasi ke halaman utama (setelah login).
                        _navigateToHome.value = true
                        val sharedPref = SharedPref(application)
                        sharedPref.saveUser(email.userName, email.userEmail)
                    } else {
                        // Menampilkan pesan kesalahan jika password salah.
                        _errorToast.value = true
                    }
                }
            }
        }
    }

    // Fungsi untuk menavigasi ke halaman pendaftaran
    fun signUP() {
        _navigatetoRegister.value = true
    }

    // Fungsi untuk menghentikan tampilan pesan kesalahan
    fun donetoast() {
        _errorToast.value = false
        Log.i("MYTAG", "Done toasting")
    }

    // Fungsi untuk menghentikan navigasi ke halaman utama atau pendaftaran
    fun doneNavigating() {
        _navigateToHome.value = false
    }

    // Fungsi untuk menambahkan callback pemantauan
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    // Fungsi untuk menghapus callback pemantauan
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
}
