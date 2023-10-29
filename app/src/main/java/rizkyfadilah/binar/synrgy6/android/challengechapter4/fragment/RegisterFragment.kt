package rizkyfadilah.binar.synrgy6.android.challengechapter4.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import rizkyfadilah.binar.synrgy6.android.challengechapter4.R
import rizkyfadilah.binar.synrgy6.android.challengechapter4.room.register_room.RegisterDatabase
import rizkyfadilah.binar.synrgy6.android.challengechapter4.databinding.FragmentRegisterBinding
import rizkyfadilah.binar.synrgy6.android.challengechapter4.repo.RegisterRepository
import rizkyfadilah.binar.synrgy6.android.challengechapter4.viewmodel.register_vm.RegisterViewModel
import rizkyfadilah.binar.synrgy6.android.challengechapter4.viewmodel.register_vm.RegisterFactory

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(this.activity).application
        val dao = RegisterDatabase.getInstance(application).registerDatabaseDao
        val repository = RegisterRepository(dao)
        val factory = RegisterFactory(repository, application)

        // Inisialisasi ViewModel
        registerViewModel = ViewModelProvider(this, factory).get(RegisterViewModel::class.java)

        binding.myLoginViewModel = registerViewModel
        binding.lifecycleOwner = this

        // Observer untuk kesalahan
        registerViewModel.errorToast.observe(viewLifecycleOwner, Observer { hasError ->
            if (hasError) {
                Toast.makeText(context, "Error! Cek kembali username, email dan password anda!", Toast.LENGTH_SHORT).show()
                registerViewModel.donetoast()
            }
        })

        // Observer untuk navigasi
        registerViewModel.navigateto.observe(viewLifecycleOwner, Observer { hasFinished ->
            if (hasFinished == true) {
                Log.i("MYTAG", "Insidi observe")
                displayUserList()
            }
        })
    }

    // Fungsi untuk menampilkan daftar pengguna setelah registrasi
    private fun displayUserList() {
        Log.i("MYTAG", "InsidisplayUsersList")
        NavHostFragment.findNavController(this).navigate(R.id.action_registerFragment_to_loginFragment)
    }
}
