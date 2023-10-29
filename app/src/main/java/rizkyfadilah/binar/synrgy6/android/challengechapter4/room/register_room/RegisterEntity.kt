package rizkyfadilah.binar.synrgy6.android.challengechapter4.room.register_room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// TODO 1: Buat Entity untuk Pendaftaran
/**
 * Kelas entitas yang mewakili data pendaftaran pengguna yang akan disimpan dalam database.
 *
 * @param userId ID pengguna yang dihasilkan secara otomatis.
 * @param userName Nama pengguna.
 * @param userEmail Alamat email pengguna.
 * @param userPassword Kata sandi pengguna.
 * @param userConfirmPassword Konfirmasi kata sandi pengguna.
 */
@Entity(tableName = "register_users_table")
data class RegisterEntity(

    // TODO 2: Buat Kolom untuk Pendaftaran
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0,

    @ColumnInfo(name = "user_name")
    var userName: String,

    @ColumnInfo(name = "user_email")
    var userEmail: String,

    @ColumnInfo(name = "user_password")
    var userPassword: String,

    @ColumnInfo(name = "user_confirm_password")
    var userConfirmPassword: String
)
