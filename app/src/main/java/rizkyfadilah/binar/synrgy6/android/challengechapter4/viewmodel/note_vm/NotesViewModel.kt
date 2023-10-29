package rizkyfadilah.binar.synrgy6.android.challengechapter4.viewmodel.note_vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import rizkyfadilah.binar.synrgy6.android.challengechapter4.room.notes_room.NoteDatabase
import rizkyfadilah.binar.synrgy6.android.challengechapter4.room.notes_room.NoteEntity
import rizkyfadilah.binar.synrgy6.android.challengechapter4.repo.NoteRepository

// Kelas ViewModel untuk NotesFragment.
class NotesViewModel(private val application: Application) : AndroidViewModel(application) {

    // Variabel LiveData untuk semua catatan dan repository yang digunakan.
    val allNote: LiveData<List<NoteEntity>>
    val repository: NoteRepository

    // Menginisialisasi repository dan allNote dalam blok init.
    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(dao)
        allNote = repository.allNotes
    }

    // Fungsi-fungsi untuk menghapus, memperbarui, dan menyisipkan catatan ke dalam database.
    // Fungsi ini dijalankan di latar belakang menggunakan Coroutine.
    fun deleteNote(note: NoteEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun updateNote(note: NoteEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }

    fun insert(note: NoteEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

    // Fungsi untuk mendapatkan catatan berdasarkan ID.
    // Fungsi ini dijalankan di latar belakang menggunakan Coroutine.
    fun getNoteById(noteId: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.getNoteById(noteId)
    }
}
