package es.iesoretania.navigationdrawerbbddkotlin.adaptador

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import es.iesoretania.navigationdrawerbbddkotlin.databinding.EmpleadoItemBinding

class MiAdaptadorEmpleados(
    private  val context: Context,
    val resource: Int,
    private val listaempleados: List<Empleado>
) :
    ArrayAdapter<Empleado>(context, resource, listaempleados) {
        private lateinit var binding: EmpleadoItemBinding
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        binding = EmpleadoItemBinding.inflate(LayoutInflater.from(context), parent, false)
        val v = binding.root
        val elementoActual: Empleado = listaempleados[position]

        binding.textViewID.text = elementoActual.id.toString()
        binding.textViewNombre.text = elementoActual.nombre
        binding.textViewApellidos.text = elementoActual.apellidos
        binding.textViewSalario.text = elementoActual.salario.toString()

        return v
    }
}