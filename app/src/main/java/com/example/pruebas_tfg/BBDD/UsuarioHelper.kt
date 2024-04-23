package com.example.pruebas_tfg.BBDD

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UsuarioHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase) {

        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                USERNAME_COl + " TEXT," +
                PASSWORD_COL + " TEXT," +
                IMAGE_COL + " TEXT, " +
                MAIL_COL + " TEXT"+
                ")")


        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    // This method is for adding data in our database
    fun addName(username : String, password : String, image : String, maiL : String ){


        val values = ContentValues()

        values.put(USERNAME_COl, username)
        values.put(PASSWORD_COL, password)
        values.put(IMAGE_COL, image)
        values.put(MAIL_COL, maiL)

        val db = this.writableDatabase


        db.insert(TABLE_NAME, null, values)


        db.close()
    }


    fun getName(): Cursor? {


        val db = this.readableDatabase

        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

    }

    companion object{

        private val DATABASE_NAME = "Usuarios"


        private val DATABASE_VERSION = 1

        val TABLE_NAME = "Users_table"

        val ID_COL = "id"


        val USERNAME_COl = "username"


        val PASSWORD_COL = "password"

        val IMAGE_COL = "image"

        val MAIL_COL = "mail"

    }
}