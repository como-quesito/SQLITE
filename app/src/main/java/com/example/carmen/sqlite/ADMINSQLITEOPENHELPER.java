package com.example.carmen.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by CARMEN on 12/07/2016.
 */
public class ADMINSQLITEOPENHELPER extends SQLiteOpenHelper
{
    public ADMINSQLITEOPENHELPER(Context context, String  nombre, SQLiteDatabase.CursorFactory factory,int version  ){
                super(context,nombre,factory,version);


    }
    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL("create table articulos(codigo integer primary key, descripcion text,existencias integer,ubicacion text) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     db.execSQL("drop table if exists articulos");
         db.execSQL("create table articulos(Codigo integer primary key, Descripcion text, Existencias integer, Ubicacion text)");
    }
}
