package es.iesoretania.navigationdrawerbbddkotlin

import android.content.ContentValues
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import es.iesoretania.navigationdrawerbbddkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            dialogoInsertar(view)
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.contenedorPrincipal)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.inicioFragment, R.id.insertarFragment, R.id.borrarFragment, R.id.listarFragment
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        supportActionBar?.title = "Inicio"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.contenedorPrincipal)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun dialogoInsertar (view: View){
        val builder = AlertDialog.Builder(view.context)
        val inflater = layoutInflater
        builder.setTitle("Datos del nuevo empleado")

        val dialogLayout = inflater.inflate(R.layout.layout_insertar, null)
        val edNombreEmpleado = dialogLayout.findViewById<EditText>(R.id.edNombreEmpleado)
        val edApellidosEmpleado = dialogLayout.findViewById<EditText>(R.id.edApellidosEmpleado)
        val edSalarioEmpleado = dialogLayout.findViewById<EditText>(R.id.edSalarioEmepleado)

        builder.setView(dialogLayout)
        builder.setPositiveButton("Aceptar") { dialogInterface, i ->
            val nombre = edNombreEmpleado.text.toString()
            val apellidos = edApellidosEmpleado.text.toString()
            val salario = edSalarioEmpleado.text.toString().toDouble()
            insertar(view, nombre, apellidos, salario)
        }
        builder.setNegativeButton("Cancelar") { dialogInterface, i ->
            dialogInterface.dismiss()
        }
        builder.show()
    }

    private fun insertar (view: View, nombre: String, apellidos: String, salario: Double){
        val dbHelper = AdminSQLiteOpenHelper(view.context, "empleados", null, 1)
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put("nombre", nombre)
            put("apellido", apellidos)
            put("salario", salario)
        }

        db.insert("empleado", null, values)
        //db.close()
    }
}