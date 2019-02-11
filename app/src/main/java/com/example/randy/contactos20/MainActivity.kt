package com.example.randy.contactos20

import android.app.Person
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.randy.contactos20.Adapter.ListPersonAdapter
import com.example.randy.contactos20.DBHelper.DBHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    internal lateinit var  db:DBHelper
    internal var listPersons: List<nombre> = ArrayList<nombre>(

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db= DBHelper( this)
        refreshData()

        btn_add.setOnClickListener {
            val nombre = nombre(
                Integer.parseInt(edt_id.text.toString()),
                edt_name.text.toString(),
                edt_email.text.toString()
            )
            db.addPerson(nombre)
            refreshData()
        }
        btn_update.setOnClickListener {
            val nombre = nombre(
                Integer.parseInt(edt_id.text.toString()),
                edt_name.text.toString(),
                edt_email.text.toString()
            )
            db.updatePerson(nombre)
            refreshData()
            btn_delete.setOnClickListener{
                val nombre = nombre(
                    Integer.parseInt(edt_id.text.toString()),
                    edt_name.text.toString(),
                    edt_email.text.toString()
                )
                db.deletePerson(nombre)
                refreshData()
            }
        }

    }

    private fun refreshData() {
        listPersons = db.allPerson
        val adapter = ListPersonAdapter ( this@MainActivity,listPersons,edt_id,edt_name, edt_email)
        list_persons.adapter= adapter

    }
}
