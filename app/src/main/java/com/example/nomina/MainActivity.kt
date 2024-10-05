package com.example.nomina

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var numReciboEt: EditText
    private lateinit var nombreEt: EditText
    private lateinit var diasTrabajadosEt: EditText

    private lateinit var auxiliarRb: RadioButton
    private lateinit var albanilRb: RadioButton
    private lateinit var ingObraRb: RadioButton

    private lateinit var subtotalEt: EditText
    private lateinit var impuestoEt: EditText
    private lateinit var totalEt: EditText

    private lateinit var calcularBtn: Button
    private lateinit var limpiarBtn: Button
    private lateinit var salirBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        iniciarComponentes()
    }

    private fun iniciarComponentes() {
        numReciboEt = findViewById(R.id.noReciboET)
        nombreEt = findViewById(R.id.nombreET)
        diasTrabajadosEt = findViewById(R.id.diasET)

        auxiliarRb = findViewById(R.id.auxiliarRb)
        albanilRb = findViewById(R.id.albanilRb)
        ingObraRb = findViewById(R.id.ingRb)

        subtotalEt = findViewById(R.id.subtotalET)
        impuestoEt = findViewById(R.id.impuestoET)
        totalEt = findViewById(R.id.totalET)

        calcularBtn = findViewById(R.id.calcularBtn)
        limpiarBtn = findViewById(R.id.limpiarBtn)
        salirBtn = findViewById(R.id.terminarBtn)

        calcularBtn.setOnClickListener {
            if (numReciboEt.text.toString().isEmpty() || nombreEt.text.toString().isEmpty()
                || diasTrabajadosEt.text.toString()
                    .isEmpty() || !auxiliarRb.isChecked && !albanilRb.isChecked && !ingObraRb.isChecked
            ) {
                Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_SHORT).show()
            } else{
            val subtotal = calcular()
            val impuesto = subtotal * 0.16

            subtotalEt.setText(subtotal.toString())
            impuestoEt.setText(impuesto.toString())
            totalEt.setText((subtotal - impuesto).toString())
        }
    }
        limpiarBtn.setOnClickListener { limpiar() }
        salirBtn.setOnClickListener {
            val dialogo = AlertDialog.Builder(this)
            dialogo.setTitle("Salir")
            dialogo.setMessage("¿Desea salir de la aplicación?")
            dialogo.setPositiveButton("Sí") { _, _ ->
                finish()
            }
            dialogo.setNegativeButton("No", null)
            dialogo.show()
             }


    }

    private fun limpiar() {

        numReciboEt.setText("")
        nombreEt.setText("")
        diasTrabajadosEt.setText("")
        auxiliarRb.isChecked = false
        albanilRb.isChecked = false
        ingObraRb.isChecked = false
        subtotalEt.setText("")
        impuestoEt.setText("")
        totalEt.setText("")
    }

    private fun calcular() : Double {
        var salarioTotal = 0.0

        if (auxiliarRb.isChecked){
            salarioTotal = (200 * 1.2) * diasTrabajadosEt.text.toString().toInt()
        }else if (albanilRb.isChecked){
            salarioTotal = (200 * 1.5) * diasTrabajadosEt.text.toString().toInt()
        }else if (ingObraRb.isChecked){
            salarioTotal = (200 * 2.0) * diasTrabajadosEt.text.toString().toInt()
        }

        return salarioTotal
    }
}