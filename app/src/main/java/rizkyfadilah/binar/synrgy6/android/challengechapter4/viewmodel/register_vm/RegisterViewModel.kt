package rizkyfadilah.binar.synrgy6.android.challengechapter4.viewmodel.register_vm

import android.app.Application
import androidx.databinding.Observable
import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import rizkyfadilah.binar.synrgy6.android.challengechapter4.room.register_room.RegisterEntity
import rizkyfadilah.binar.synrgy6.android.challengechapter4.repo.RegisterRepository

// Kelas ViewModel untuk proses pendaftaran pengguna.
class RegisterViewModel(private val repository: RegisterRepository, application: Application) :
    AndroidViewModel(application), Observable {

    // LiveData untuk menyimpan input dari pengguna
    @Bindable
    val userName = MutableLiveData<String>()

    @Bindable
    val userEmail = MutableLiveData<String>()

    @Bindable
    val userPassword = MutableLiveData<String>()

    @Bindable
    val userConfirmPassword = MutableLiveData<String>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    // LiveData untuk navigasi
    private val _navigateto = MutableLiveData<Boolean>()
    val navigateto: LiveData<Boolean>
        get() = _navigateto

    // LiveData untuk menampilkan pesan kesalahan
    private val _errorToast = MutableLiveData<Boolean>()
    val errorToast: LiveData<Boolean>
        get() = _errorToast

    // LiveData untuk menampilkan pesan kesalahan jika nama pengguna sudah ada
    private val _errorToastUsername = MutableLiveData<Boolean>()

    // Fungsi yang dipanggil saat tombol "Submit" ditekan
    fun submitButton() {
        if (userName.value.isNullOrEmpty() || userEmail.value.isNullOrEmpty() || userPassword.value.isNullOrEmpty() || userConfirmPassword.value.isNullOrEmpty() || userPassword.value != userConfirmPassword.value) {
            // Validasi input pengguna
            _errorToast.value = true
        } else {
            uiScope.launch {
                val userNames = repository.getUsername(userName.value!!)
                if (userNames != null) {
                    // Nama pengguna sudah ada dalam basis data
                    _errorToastUsername.value = true
                } else {
                    val userNames = userName.value!!
                    val userEmails = userEmail.value!!
                    val userPasswords = userPassword.value!!
                    val userConfirmPasswords = userConfirmPassword.value!!
                    // Memasukkan data pengguna baru ke basis data
                    insert(RegisterEntity(0, userNames, userEmails, userPasswords, userConfirmPasswords))
                    userNames.isEmpty()
                    userEmails.isEmpty()
                    userPasswords.isEmpty()
                    userConfirmPasswords.isEmpty()
                    // Mengarahkan ke tampilan selanjutnya setelah pendaftaran sukses
                    _navigateto.value = true
                }
            }
        }
    }

    // Fungsi yang dipanggil untuk menghilangkan pesan kesalahan
    fun donetoast() {
        _errorToast.value = false
        Log.i("MYTAG", "Done toasting ")
    }

    // Fungsi yang dipanggil saat pengguna ingin melakukan login
    fun login() {
        _navigateto.value = true
    }

    // Fungsi untuk memasukkan data pengguna ke basis data
    private fun insert(user: RegisterEntity): Job = viewModelScope.launch {
        repository.insert(user)
    }

    // Implementasi dari antarmuka Observable
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}
