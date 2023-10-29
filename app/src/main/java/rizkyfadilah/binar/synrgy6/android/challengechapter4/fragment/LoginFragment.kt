package rizkyfadilah.binar.synrgy6.android.challengechapter4.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import rizkyfadilah.binar.synrgy6.android.challengechapter4.R
import rizkyfadilah.binar.synrgy6.android.challengechapter4.room.register_room.RegisterDatabase
import rizkyfadilah.binar.synrgy6.android.challengechapter4.databinding.FragmentLoginBinding
import rizkyfadilah.binar.synrgy6.android.challengechapter4.repo.RegisterRepository
import rizkyfadilah.binar.synrgy6.android.challengechapter4.viewmodel.login_vm.LoginFactory
import rizkyfadilah.binar.synrgy6.android.challengechapter4.viewmodel.login_vm.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Mengisi layout untuk fragment ini
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi ViewModel dan repository
        val application = requireNotNull(this.activity).application
        val dao = RegisterDatabase.getInstance(application).registerDatabaseDao
        val repository = RegisterRepository(dao)
        val factory = LoginFactory(repository, application)

        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)

        // Menghubungkan ViewModel dengan layout binding
        binding.myLoginViewModel = loginViewModel

        // Menghubungkan binding dengan pemilik siklus
        binding.lifecycleOwner = this

        // Mengamati LiveData untuk menampilkan pesan kesalahan saat login gagal
        loginViewModel.errorToast.observe(viewLifecycleOwner, { hasError ->
            if (hasError == true) {
                Toast.makeText(requireContext(), "Login Gagal", Toast.LENGTH_SHORT).show()
                loginViewModel.donetoast()
            }
        })

        // Mengamati LiveData untuk mengarahkan pengguna ke tampilan beranda setelah login berhasil
        loginViewModel.navigateToHome.observe(viewLifecycleOwner, { hasFinished ->
            if (hasFinished == true) {
                Log.i("MYTAG", "Masuk ke observe")
                navigateToHome()
                loginViewModel.doneNavigating()
                Toast.makeText(requireContext(), "Login Berhasil", Toast.LENGTH_SHORT).show()
            }
        })

        // Mengamati LiveData untuk mengarahkan pengguna ke tampilan pendaftaran
        loginViewModel.navigatetoRegister.observe(viewLifecycleOwner) { hasFinished ->
            if (hasFinished == true) {
                Log.i("MYTAG", "Masuk ke observe")
                navigateToRegister()
                loginViewModel.doneNavigating()
            } else {
                !(hasFinished ?: true)
            }
        }

        // Mengatur latar belakang input email saat fokus berubah
        binding.etEmail.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                binding.etEmail.setBackgroundResource(R.drawable.bg_edittext)
            } else {
                binding.etEmail.setBackgroundResource(R.drawable.bg_input_active)
            }
        }

        // Mengatur latar belakang input password saat fokus berubah
        binding.etPassword.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                binding.etPassword.setBackgroundResource(R.drawable.bg_edittext)
            } else {
                binding.etPassword.setBackgroundResource(R.drawable.bg_input_active)
            }
        }
    }

    // Fungsi untuk mengarahkan ke tampilan beranda
    private fun navigateToHome() {
        Log.i("MYTAG", "Masuk ke navigate")
        NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_notesFragment)
    }

    // Fungsi untuk mengarahkan ke tampilan pendaftaran
    private fun navigateToRegister() {
        NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_registerFragment)
    }
}
