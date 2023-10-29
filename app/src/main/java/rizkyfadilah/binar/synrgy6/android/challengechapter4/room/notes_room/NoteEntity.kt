package rizkyfadilah.binar.synrgy6.android.challengechapter4.room.notes_room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Anotasi Entity mendefinisikan bahwa kelas ini adalah entitas dalam database Room
@Entity(tableName = "notes")
class NoteEntity(
    // Anotasi PrimaryKey menandakan bahwa 'id' adalah kunci utama dengan opsi auto-generate
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    // Anotasi ColumnInfo digunakan untuk menentukan nama kolom dalam tabel
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
)
