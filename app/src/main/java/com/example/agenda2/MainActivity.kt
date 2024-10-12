package com.example.agenda2
import ContactAdapter
import ContactoModel
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ContactAdapter
    private lateinit var contactos: MutableList<ContactoModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contactos = mutableListOf(
            ContactoModel("Maria Sofia F", "5549201863", "FrejieNovak.4@gmail.com", R.drawable.ic_profile),
            ContactoModel("DR Simi", "5544669922", "Simichad.1999@gmail.com", R.drawable.ic_profile2),
            ContactoModel("Ana Sofia", "5599226644", "anitasoft@gmail.com", R.drawable.ic_profile3),
            ContactoModel("Kanye Goat West","5544881616", "TheGOAT@gmail.com",R.drawable.ic_profile4),
            ContactoModel("Maria Sofia F", "5549201863", "FrejieNovak.4@gmail.com", R.drawable.ic_profile)
        )

        val listView: ListView = findViewById(R.id.listView)
        adapter = ContactAdapter(this, contactos)
        listView.adapter = adapter

        val searchView: SearchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredContacts = contactos.filter {
                    it.nombre.contains(newText ?: "", ignoreCase = true) || it.telefono.contains(newText ?: "")
                }
                adapter = ContactAdapter(this@MainActivity, filteredContacts)
                listView.adapter = adapter
                return true
            }
        })
    }
}
