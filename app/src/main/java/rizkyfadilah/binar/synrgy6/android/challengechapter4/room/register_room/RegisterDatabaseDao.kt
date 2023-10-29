package rizkyfadilah.binar.synrgy6.android.challengechapter4.room.register_room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

// TODO 3: Buat Dao untuk Pendaftaran
@Dao
interface RegisterDatabaseDao {

    // TODO 4: Buat Kueri untuk Pendaftaran
    @Insert
    suspend fun insert(registerEntity: RegisterEntity)

    // Mengambil semua pengguna yang terdaftar dan mengembalikan sebagai LiveData
    @Query("SELECT * FROM register_users_table ORDER BY userId DESC")
    fun getAllUsers(): LiveData<List<RegisterEntity>>

    // Mencari pengguna berdasarkan nama pengguna
    @Query("SELECT * FROM register_users_table WHERE user_name LIKE :userName")
    suspend fun getUsername(userName: String): RegisterEntity?

    // Mencari pengguna berdasarkan alamat email
    @Query("SELECT * FROM register_users_table WHERE user_email LIKE :userEmail")
    suspend fun getEmail(userEmail: String): RegisterEntity?
}
