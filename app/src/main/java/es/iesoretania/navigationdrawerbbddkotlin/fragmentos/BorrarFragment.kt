package es.iesoretania.navigationdrawerbbddkotlin.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import es.iesoretania.navigationdrawerbbddkotlin.AdminSQLiteOpenHelper
import es.iesoretania.navigationdrawerbbddkotlin.R
import es.iesoretania.navigationdrawerbbddkotlin.databinding.FragmentBorrarBinding

class BorrarFragment : Fragment() {
    private lateinit var binding: FragmentBorrarBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBorrarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Borrar Empleado"
        binding.BotonAceptarBorrar.setOnClickListener{
            val idEmpleado = binding.edIDBorrar.text.toString()
            borrarEmpleado(idEmpleado)
        }

        binding.BotonCancelarBorrar.setOnClickListener {
            findNavController().navigate(R.id.inicioFragment)
        }
    }

    private fun borrarEmpleado(id: String) {
        val dbHelper = AdminSQLiteOpenHelper(context, "empleados", null, 1)
        val db = dbHelper.writableDatabase

        db.delete("empleado", "id =?", arrayOf(id))

        binding.edIDBorrar.setText("")

        //db.close()
    }
}