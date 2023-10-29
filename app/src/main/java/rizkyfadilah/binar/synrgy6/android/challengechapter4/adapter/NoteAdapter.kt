package rizkyfadilah.binar.synrgy6.android.challengechapter4.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rizkyfadilah.binar.synrgy6.android.challengechapter4.databinding.ItemNotesBinding
import rizkyfadilah.binar.synrgy6.android.challengechapter4.room.notes_room.NoteEntity

class NoteAdapter(
    val context: Context,
    val selectListener: SelectListener,
    val deleteListener: DeleteListener,
    val editListener: EditListener,
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private val allNotes = ArrayList<NoteEntity>()

    class NoteViewHolder(val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        // Membuat dan mengembalikan instance viewHolder
        return NoteViewHolder(
            ItemNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        // Mengisi data tampilan item daftar catatan
        val note = allNotes[position]

        holder.binding.tvTittle.text = note.title
        holder.binding.tvDescription.text = note.description

        // Menangani klik tombol edit
        holder.binding.ivEdit.setOnClickListener{
            editListener.editItem(note)
        }

        // Menangani klik tombol hapus
        holder.binding.ivDelete.setOnClickListener{
            deleteListener.deleteItem(note)
        }

        // Menangani klik item catatan
        holder.itemView.setOnClickListener {
            selectListener.selectItem(note)
        }
    }

    override fun getItemCount(): Int {
        // Mengembalikan jumlah item dalam daftar catatan
        return allNotes.size
    }

    // Memperbarui daftar catatan dengan data yang baru
    fun updateList(newList: List<NoteEntity>) {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}

// Antarmuka untuk mendengar pemilihan item catatan
interface SelectListener {
    fun selectItem(note: NoteEntity)
}

// Antarmuka untuk mendengar permintaan edit catatan
interface EditListener {
    fun editItem(note: NoteEntity)
}

// Antarmuka untuk mendengar permintaan hapus catatan
interface DeleteListener {
    fun deleteItem(note: NoteEntity)
}
