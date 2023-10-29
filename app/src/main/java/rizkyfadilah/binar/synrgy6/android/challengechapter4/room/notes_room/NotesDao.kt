package rizkyfadilah.binar.synrgy6.android.challengechapter4.room.notes_room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

// Buat Data Access Object (DAO) untuk Catatan
@Dao
interface NotesDao {

    // Fungsi 'insert' digunakan untuk menyisipkan catatan ke database dengan mengatasi konflik
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: NoteEntity)

    // Fungsi 'delete' digunakan untuk menghapus catatan dari database
    @Delete
    suspend fun delete(note: NoteEntity)

    // Fungsi 'getAllNotes' digunakan untuk mendapatkan semua catatan dalam bentuk LiveData
    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<NoteEntity>>

    // Fungsi 'update' digunakan untuk memperbarui catatan yang ada dalam database
    @Update
    suspend fun update(note: NoteEntity)

    // Fungsi 'getNoteById' digunakan untuk mendapatkan catatan berdasarkan ID catatan
    @Query("SELECT * FROM notes WHERE id = :noteId")
    suspend fun getNoteById(noteId: Int): NoteEntity
}
