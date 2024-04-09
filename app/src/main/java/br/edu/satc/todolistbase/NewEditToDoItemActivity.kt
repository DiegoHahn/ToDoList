package br.edu.satc.todolistbase

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import br.edu.satc.todolistbase.roomdatabase.AppDatabase
import br.edu.satc.todolistbase.roomdatabase.ToDoItem

class NewEditToDoItemActivity : AppCompatActivity() {

    private lateinit var etTitle: EditText
    private lateinit var etDescription: EditText
    private lateinit var btSave: Button
    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_edit_to_do_item)

        initDatabase()
        etTitle = findViewById(R.id.et_title)
        etDescription = findViewById(R.id.et_description)
        btSave = findViewById(R.id.bt_save)

        btSave.setOnClickListener {
            save()
        }
    }
    private fun initDatabase() {
            // Inicializar nosso banco de dados Android Room Persistence com SQLITE
            db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-todolist"
            )
                .allowMainThreadQueries()
                .build()


        }
    private fun save(){
            val toDoItem = ToDoItem(
                null,
                etTitle.text.toString(),
                etDescription.text.toString(),
                false)

        db.toDoItemDao().insertAll(toDoItem)

        Toast.makeText(this, "Tarefa salva", Toast.LENGTH_SHORT).show()

        finish()
        }
    }