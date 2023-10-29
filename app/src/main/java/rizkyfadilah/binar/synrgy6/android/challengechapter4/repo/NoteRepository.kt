package rizkyfadilah.binar.synrgy6.android.challengechapter4.repo

import androidx.lifecycle.LiveData
import rizkyfadilah.binar.synrgy6.android.challengechapter4.room.notes_room.NoteEntity
import rizkyfadilah.binar.synrgy6.android.challengechapter4.room.notes_room.NotesDao

/**
 * Kelas `NoteRepository` bertindak sebagai perantara (repository) antara sumber data dan
 * bagian aplikasi yang membutuhkan data catatan. Kelas ini menyediakan akses ke operasi-operasi
 * basis data seperti pengambilan (query), penyisipan (insert), penghapusan (delete), dan
 * pembaruan (update) catatan.
 */
class NoteRepository(private val noteDao: NotesDao) {

    // TODO 11 : Membuat fungsi untuk menyisipkan, menghapus, dan memperbarui catatan

    // LiveData yang akan mengamati perubahan data catatan
    val allNotes: LiveData<List<NoteEntity>> = noteDao.getAllNotes()

    /**
     * Fungsi untuk menyisipkan catatan baru ke dalam basis data.
     */
    suspend fun insert(note: NoteEntity) {
        noteDao.insert(note)
    }

    /**
     * Fungsi untuk menghapus catatan dari basis data.
     */
    suspend fun delete(note: NoteEntity) {
        noteDao.delete(note)
    }

    /**
     * Fungsi untuk memperbarui catatan yang ada dalam basis data.
     */
    suspend fun update(note: NoteEntity) {
        noteDao.update(note)
    }

    /**
     * Fungsi untuk mendapatkan catatan berdasarkan ID.
     */
    suspend fun getNoteById(noteId: Int): NoteEntity {
        return noteDao.getNoteById(noteId)
    }
}
