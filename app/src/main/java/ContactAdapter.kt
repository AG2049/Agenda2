import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.agenda2.R

class ContactAdapter(private val context: Context, private val contactos: List<ContactoModel>) : BaseAdapter() {

    override fun getCount(): Int = contactos.size

    override fun getItem(position: Int): Any = contactos[position]

    override fun getItemId(position: Int): Long = position.toLong()
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.list_item_contact, parent, false)

        val contacto = contactos[position]

        // Referencias a las vistas del layout
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val tvNombre: TextView = view.findViewById(R.id.tvNombre)
        val tvTelefono: TextView = view.findViewById(R.id.tvTelefono)
        val tvEmail: TextView = view.findViewById(R.id.tvEmail)
        val btnEmail: Button = view.findViewById(R.id.btnEmail)

        // Asignación de datos
        imageView.setImageResource(contacto.imagenId)
        tvNombre.text = contacto.nombre
        tvTelefono.text = contacto.telefono
        tvEmail.text = contacto.email

        // Verificación de duplicados (número de teléfono)
        val esDuplicado = contactos.count { it.telefono == contacto.telefono } > 1
        if (esDuplicado) {
            view.setBackgroundColor(context.getColor(android.R.color.holo_orange_light))
        } else {
            // Restablecer el color de fondo para contactos no duplicados
            view.setBackgroundColor(context.getColor(android.R.color.transparent))
        }

        // Evento del botón para enviar un correo
        btnEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:${contacto.email}")
            }
            context.startActivity(intent)
        }

        return view
    }
}