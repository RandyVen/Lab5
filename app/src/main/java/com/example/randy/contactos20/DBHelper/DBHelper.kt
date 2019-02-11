package com.example.randy.contactos20.DBHelper

import android.app.Person
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteTransactionListener
import com.example.randy.contactos20.nombre

class DBHelper (context:Context): SQLiteOpenHelper(context,DATABASE_NAME, null, DATABASE_VER) {
    companion object {
        private val DATABASE_VER = 1
        private val DATABASE_NAME = "EDMTDB.db"

        private val TABLE_NAME = "Persona"
        private val COL_ID = "id"
        private val COL_NAME = "Name"
        private val COL_EMAIL = "Email"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY =
            ("CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY,$COL_NAME TEXT,+$COL_EMAIL TEXT")
        db!!.execSQL(CREATE_TABLE_QUERY);
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }


    val allPerson: List<nombre>
        get () {
            val lstPerson = ArrayList<nombre>()
            val selectQuery = "SELECT * FROM $TABLE_NAME"
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst())
            {
                do {
                    val nombre = nombre()
                    nombre.id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                    nombre.name = cursor.getString(cursor.getColumnIndex(COL_NAME))
                    nombre.email = cursor.getString(cursor.getColumnIndex(COL_EMAIL))
                    lstPerson.add(nombre)
                } while (cursor.moveToNext())

            }
            db.close()
            return lstPerson
        }
    fun addPerson (nombre:nombre){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID,nombre.id)
        values.put(COL_NAME,nombre.name)
        values.put(COL_EMAIL,nombre.email)
        db.insert(TABLE_NAME,null,values)
        db.close()
    }
    fun updatePerson (nombre:nombre):Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID,nombre.id)
        values.put(COL_NAME,nombre.name)
        values.put(COL_EMAIL,nombre.email)
        return db.update(TABLE_NAME,values,"$COL_ID=?", arrayOf(nombre.id.toString()))

    }
    fun deletePerson (nombre:nombre) {
        val db = this.writableDatabase

         db.delete(TABLE_NAME,"$COL_ID=?",arrayOf(nombre.id.toString()))
        db.close()

    }
}