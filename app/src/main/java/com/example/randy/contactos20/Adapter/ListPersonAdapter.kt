package com.example.randy.contactos20.Adapter

import android.app.Activity
import android.app.Person
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import com.example.randy.contactos20.R
import com.example.randy.contactos20.nombre
import kotlinx.android.synthetic.main.activity_guardar.view.*

class ListPersonAdapter(internal var activity: Activity,
                        internal var listPerson: List<nombre>,
                        internal var edt_id: EditText,
                        internal var edt_name: EditText,
                        internal var edt_email:EditText):BaseAdapter()


{
    internal var inflater:LayoutInflater
    init {
        inflater =activity.getSystemService( Context.LAYOUT_INFLATER_SERVICE)as LayoutInflater
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView:View
        rowView= inflater.inflate(R.layout.activity_guardar, null)
        rowView.txt_row_id.text = listPerson[position].id.toString()
        rowView.txt_name.text = listPerson[position].name.toString()
        rowView.txt_email.text = listPerson[position].email.toString()

        rowView.setOnClickListener {
             edt_id.setText(rowView.txt_row_id.text.toString())
             edt_name.setText(rowView.txt_name.text.toString())
            edt_email.setText(rowView.txt_email.text.toString())
        }
        return rowView
    }

    override fun getItem(position: Int): Any {
        return listPerson[position]
    }

    override fun getItemId(position: Int): Long {
        return listPerson[position].id.toLong()
    }

    override fun getCount(): Int {
        return listPerson.size
    }
}