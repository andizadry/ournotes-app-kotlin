package rizkyfadilah.binar.synrgy6.android.challengechapter4.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import rizkyfadilah.binar.synrgy6.android.challengechapter4.R
import rizkyfadilah.binar.synrgy6.android.challengechapter4.adapter.DeleteListener
import rizkyfadilah.binar.synrgy6.android.challengechapter4.adapter.EditListener
import rizkyfadilah.binar.synrgy6.android.challengechapter4.adapter.NoteAdapter
import rizkyfadilah.binar.synrgy6.android.challengechapter4.adapter.SelectListener
import rizkyfadilah.binar.synrgy6.android.challengechapter4.room.notes_room.NoteEntity
import rizkyfadilah.binar.synrgy6.android.challengechapter4.databinding.DialogAddBinding
import rizkyfadilah.binar.synrgy6.android.challengechapter4.databinding.DialogDeleteBinding
import rizkyfadilah.binar.synrgy6.android.challengechapter4.databinding.FragmentNotesBinding
import rizkyfadilah.binar.synrgy6.android.challengechapter4.sharepref.SharedPref
import rizkyfadilah.binar.synrgy6.android.challengechapter4.viewmodel.note_vm.NotesFactory
import rizkyfadilah.binar.synrgy6.android.challengechapter4.viewmodel.note_vm.NotesViewModel

class NotesFragment : Fragment(), SelectListener, DeleteListener, EditListener {
    private lateinit var binding: FragmentNotesBinding
    lateinit var viewModel: NotesViewModel
    private var noteId = -1;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mendapatkan username dari SharedPref
        val sharedPref = SharedPref(requireContext())

        // Mengatur teks selamat datang dengan username
        binding.tvWelcomeUser.text = "Selamat Datang, ${sharedPref.getUsername()}!"

        // Membuat fungsi logout
        binding.tvLogout.setOnClickListener {
            sharedPref.clearData()
            findNavController().navigate(R.id.action_notesFragment_to_loginFragment)
        }

        // Membuat RecyclerView
        binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())

        // Membuat adapter untuk RecyclerView
        val adapter = NoteAdapter(requireContext(), this, this, this)
        binding.rvNotes.adapter = adapter

        // Membuat ViewModel
        val application = requireNotNull(this.activity).application
        val factory = NotesFactory(application)
        viewModel = ViewModelProvider(this, factory).get(NotesViewModel::class.java)

        // Menginisialisasi ViewModel
        viewModel.allNote.observe(viewLifecycleOwner, { list ->
            list?.let {
                adapter.updateList(it)
            }
        })

        // Dialog untuk menambah catatan
        binding.fabAddNote.setOnClickListener {
            addDialog()
        }
    }

    // Fungsi untuk mengedit dan menghapus item
    override fun editItem(note: NoteEntity) {
        editDialog(note)
    }

    override fun deleteItem(note: NoteEntity) {
        deleteDialog(note)
    }

    override fun selectItem(note: NoteEntity) {
        editDialog(note)
    }

    // Fungsi untuk menampilkan dialog penambahan catatan
    private fun addDialog() {
        val addDialogBinding: DialogAddBinding = DialogAddBinding.inflate(layoutInflater)
        val addDialog = AlertDialog.Builder(requireContext(), 0).create()

        addDialog.apply {
            setView(addDialogBinding.root)
            setCancelable(false)
        }.show()

        addDialogBinding.btnInput.setOnClickListener {
            val title = addDialogBinding.etTitle.text.toString()
            val desc = addDialogBinding.etDescription.text.toString()
            val note = NoteEntity(title = title, description = desc)
            viewModel.insert(note)
            addDialog.dismiss()
        }

        addDialog.setCanceledOnTouchOutside(true)
    }

    // Fungsi untuk menampilkan dialog penghapusan catatan
    private fun deleteDialog(note: NoteEntity) {
        val deleteDialogBinding: DialogDeleteBinding = DialogDeleteBinding.inflate(layoutInflater)
        val deleteDialog = AlertDialog.Builder(requireContext(), 0).create()

        deleteDialog.apply {
            setView(deleteDialogBinding.root)
            setCancelable(false)
        }.show()

        deleteDialogBinding.btnDelete.setOnClickListener {
            viewModel.deleteNote(note)
            Toast.makeText(requireContext(), "${note.title} Dihapus", Toast.LENGTH_SHORT).show()
            deleteDialog.dismiss()
        }

        deleteDialogBinding.btnCancel.setOnClickListener {
            deleteDialog.dismiss()
        }
        deleteDialog.setCanceledOnTouchOutside(true)
    }

    // Fungsi untuk menampilkan dialog pengeditan catatan
    private fun editDialog(note: NoteEntity) {
        val binding: DialogAddBinding = DialogAddBinding.inflate(layoutInflater)
        val editDialog = AlertDialog.Builder(requireContext(), 0).create()
        val bundle = Bundle()

        editDialog.apply {
            setView(binding.root)
            setCancelable(false)
        }.show()

        binding.tvAddNote.text = "Edit Catatan"
        binding.btnInput.text = "Simpan"
        binding.etTitle.setText(note.title)
        binding.etDescription.setText(note.description)
        noteId = bundle.getInt("noteId")

        binding.btnInput.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val desc = binding.etDescription.text.toString()
            val updateNotes = NoteEntity(note.id, title, desc)
            viewModel.updateNote(updateNotes)
            editDialog.dismiss()
            Toast.makeText(requireContext(), "${note.title} Diperbarui", Toast.LENGTH_SHORT).show()
        }

        editDialog.setCanceledOnTouchOutside(true)
    }
}
