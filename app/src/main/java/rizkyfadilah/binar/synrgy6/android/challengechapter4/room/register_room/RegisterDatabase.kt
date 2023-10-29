package rizkyfadilah.binar.synrgy6.android.challengechapter4.room.register_room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// TODO 3: Buat Database untuk Pendaftaran
@Database(entities = [RegisterEntity::class], version = 1, exportSchema = false)
// TODO 4: Buat kelas abstrak untuk Pendaftaran
abstract class RegisterDatabase : RoomDatabase() {

    // TODO 5: Buat val abstrak untuk Pendaftaran
    abstract val registerDatabaseDao: RegisterDatabaseDao

    // TODO 6: Buat objek companion untuk Pendaftaran
    companion object {
        @Volatile
        private var INSTANCE: RegisterDatabase? = null

        // TODO 7: Buat fungsi getInstance untuk Pendaftaran
        fun getInstance(context: Context): RegisterDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RegisterDatabase::class.java,
                        "register_users_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
