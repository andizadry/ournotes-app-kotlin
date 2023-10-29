package rizkyfadilah.binar.synrgy6.android.challengechapter4.room.notes_room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Anotasi Database mengidentifikasi bahwa kelas ini adalah database Room
@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    // Fungsi abstrak untuk mengakses Data Access Object (DAO) yang terkait dengan database
    abstract fun getNoteDao(): NotesDao

    // Companion object untuk mengelola instance database
    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        // Fungsi untuk membuat atau mendapatkan instance database Room
        fun getDatabase(context: Context): NoteDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
