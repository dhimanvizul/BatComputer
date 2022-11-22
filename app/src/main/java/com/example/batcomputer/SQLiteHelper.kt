package com.example.batcomputer

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SQLiteHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "batdatabase.db"
        private const val TBL_CRIMINAL = "tbl_criminal"
        private const val CASES = "cases"
        private const val NAME = "name"
        private const val AGE = "age"
        private const val CRIME = "crime"
        private const val IMPRISONMENT = "imprisonment"
        private const val IS_SOLVED = "solved"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTbLCriminal = "CREATE TABLE $TBL_CRIMINAL" +
                " ( " +
                "$CASES INTEGER PRIMARY KEY NOT NULL, $NAME TEXT, $AGE INTEGER,$CRIME TEXT, $IMPRISONMENT INTEGER, $IS_SOLVED TEXT " +
                " ) "
        db?.execSQL(createTbLCriminal)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_CRIMINAL")
        onCreate(db)
    }

    fun insertCrimeData(crime: CriminalModel): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(CASES, crime.case)
        contentValues.put(NAME, crime.name)
        contentValues.put(AGE, crime.age)
        contentValues.put(CRIME, crime.crime)
        contentValues.put(IMPRISONMENT, crime.imprisonment)
        contentValues.put(IS_SOLVED, crime.isSolved)

        val success = db.insert(TBL_CRIMINAL,null,contentValues)
        db.close()
        return success
    }

    fun updateCrimeData(crime: CriminalModel): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(CASES, crime.case)
        contentValues.put(NAME, crime.name)
        contentValues.put(AGE, crime.age)
        contentValues.put(CRIME, crime.crime)
        contentValues.put(IMPRISONMENT, crime.imprisonment)
        contentValues.put(IS_SOLVED, crime.isSolved)

        val success = db.update(TBL_CRIMINAL,contentValues, "$CASES=" + crime.case, null)
        db.close()
        return success
    }

    fun getCrimeData(): ArrayList<CriminalModel> {
        val crimeList: ArrayList<CriminalModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_CRIMINAL"
        val db = this.readableDatabase

        val cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        }
        catch (e: java.lang.Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var cases: Int
        var name: String
        var age: Int
        var crime: String
        var imprisonment: Int
        var is_solved: String

        try {
            if(cursor.moveToFirst()) {
                do {
                    cases = cursor.getInt(cursor.getColumnIndexOrThrow(CASES))
                    name = cursor.getString(cursor.getColumnIndexOrThrow(NAME))
                    age = cursor.getInt(cursor.getColumnIndexOrThrow(AGE))
                    crime = cursor.getString(cursor.getColumnIndexOrThrow(CRIME))
                    imprisonment = cursor.getInt(cursor.getColumnIndexOrThrow(IMPRISONMENT))
                    is_solved = cursor.getString(cursor.getColumnIndexOrThrow(IS_SOLVED))

                    val crr = CriminalModel(case=cases,name=name,age=age,crime=crime,imprisonment=imprisonment,isSolved=is_solved)
                    crimeList.add(crr)
                } while (cursor.moveToNext())
            }
        }
        catch (e: Exception) {
        Log.d(TAG, "Error while trying to get posts from database")
        }
        return crimeList
    }
    fun deleteRecordById(case: Int): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(CASES, case)

        val success = db.delete(TBL_CRIMINAL, "$CASES=$case",null)
        db.close()
        return success
    }
}