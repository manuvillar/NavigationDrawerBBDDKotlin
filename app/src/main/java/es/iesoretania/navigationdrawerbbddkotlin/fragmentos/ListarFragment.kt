package es.iesoretania.navigationdrawerbbddkotlin.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import es.iesoretania.navigationdrawerbbddkotlin.AdminSQLiteOpenHelper
import es.iesoretania.navigationdrawerbbddkotlin.R
import es.iesoretania.navigationdrawerbbddkotlin.adaptador.Empleado
import es.iesoretania.navigationdrawerbbddkotlin.adaptador.MiAdaptadorEmpleados
import es.iesoretania.navigationdrawerbbddkotlin.databinding.FragmentBorrarBinding
import es.iesoretania.navigationdrawerbbddkotlin.databinding.FragmentListarBinding

class ListarFragment : Fragment() {
    private lateinit var binding: FragmentListarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Listado de Empleados"
        val dbHelper = AdminSQLiteOpenHelper(view.context, "empleados", null, 1)
        val db = dbHelper.readableDatabase

        val cursor = db.rawQuery("select * from empleado", null)
        val miLista = mutableListOf<Empleado>()
        if(cursor.moveToFirst()){
            do{
                val empleado = Empleado(cursor.getInt(0),
                    cursor.getString(1), cursor.getString(2),
                    cursor.getDouble(3))
                miLista.add(empleado)
            }while (cursor.moveToNext())
        }

        val miadaptador = MiAdaptadorEmpleados(view.context, R.layout.empleado_item, miLista)
        binding.ListadoEmpleados.adapter = miadaptador

    }
}