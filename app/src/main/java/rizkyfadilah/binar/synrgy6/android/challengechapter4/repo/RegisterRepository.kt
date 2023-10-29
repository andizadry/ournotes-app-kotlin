package rizkyfadilah.binar.synrgy6.android.challengechapter4.repo

import rizkyfadilah.binar.synrgy6.android.challengechapter4.room.register_room.RegisterDatabaseDao
import rizkyfadilah.binar.synrgy6.android.challengechapter4.room.register_room.RegisterEntity

/**
 * Kelas `RegisterRepository` bertindak sebagai perantara (repository) antara sumber data dan
 * komponen aplikasi yang membutuhkan data pengguna. Kelas ini menyediakan akses ke operasi-operasi
 * basis data seperti pengambilan (query), penyisipan (insert), dan pencarian pengguna berdasarkan
 * nama pengguna atau alamat email.
 */
class RegisterRepository(private val dao: RegisterDatabaseDao) {

    // LiveData yang akan mengamati perubahan data pengguna
    val users = dao.getAllUsers()

    /**
     * Fungsi untuk menyisipkan pengguna baru ke dalam basis data.
     */
    suspend fun insert(user: RegisterEntity) {
        return dao.insert(user)
    }

    /**
     * Fungsi untuk mendapatkan pengguna berdasarkan nama pengguna.
     */
    suspend fun getUsername(userName: String): RegisterEntity? {
        return dao.getUsername(userName)
    }

    /**
     * Fungsi untuk mendapatkan pengguna berdasarkan alamat email.
     */
    suspend fun getEmail(userEmail: String): RegisterEntity? {
        return dao.getEmail(userEmail)
    }
}
