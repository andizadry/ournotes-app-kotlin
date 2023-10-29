package rizkyfadilah.binar.synrgy6.android.challengechapter4.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import rizkyfadilah.binar.synrgy6.android.challengechapter4.R
import rizkyfadilah.binar.synrgy6.android.challengechapter4.databinding.FragmentSplashBinding
import rizkyfadilah.binar.synrgy6.android.challengechapter4.sharepref.SharedPref

/**
 * `SplashFragment` adalah fragment pertama yang ditampilkan ketika aplikasi dijalankan.
 * Fragment ini bertindak sebagai layar penyambutan (splash screen) dan mengarahkan pengguna
 * ke halaman NotesFragment jika mereka sudah login atau ke halaman LoginFragment jika belum.
 */
class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi SharedPref untuk memeriksa status login
        val sharedPref = SharedPref(requireContext())
        val isLogin = sharedPref.checkLogin()

        // Delay navigasi selama 5 detik
        Handler(Looper.getMainLooper()).postDelayed({
        // Memeriksa status login dan mengarahkan ke tindakan yang sesuai
            if (isLogin) {
                // Jika sudah login, arahkan ke halaman NotesFragment
                findNavController().navigate(R.id.action_splashFragment_to_notesFragment)
            } else {
                // Jika belum login, arahkan ke halaman LoginFragment
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }
        }, 2000) // 2000 milidetik (2 detik)
    }
}
