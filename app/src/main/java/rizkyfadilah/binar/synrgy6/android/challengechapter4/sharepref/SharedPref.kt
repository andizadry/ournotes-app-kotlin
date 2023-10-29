package rizkyfadilah.binar.synrgy6.android.challengechapter4.sharepref

import android.content.Context

class SharedPref (context: Context) {
    private val preference = context.getSharedPreferences("MYPREF", Context.MODE_PRIVATE)

    // Fungsi untuk menyimpan data pengguna (nama pengguna dan email) dan mengatur status login.
    fun saveUser(username: String, email: String){
        val editor = preference.edit()
        editor.putString("USERNAME", username)
        editor.putString("EMAIL", email)
        editor.putBoolean("ISLOGIN", true)
        editor.apply()
    }

    // Fungsi untuk memeriksa status login.
    fun checkLogin(): Boolean{
        return preference.getBoolean("ISLOGIN", false)
    }

    // Fungsi untuk menghapus semua data yang tersimpan.
    fun clearData(){
        val editor = preference.edit()
        editor.clear()
        editor.apply()
    }

    // Fungsi untuk mendapatkan nama pengguna yang sudah login.
    fun getUsername(): String?{
        return preference.getString("USERNAME", null)
    }

    // Fungsi untuk mendapatkan alamat email yang sudah login.
    fun getEmail(): String?{
        return preference.getString("EMAIL", null)
    }
}
