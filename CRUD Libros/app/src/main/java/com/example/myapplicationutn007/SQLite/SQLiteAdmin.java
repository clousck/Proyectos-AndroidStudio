package com.example.myapplicationutn007.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SQLiteAdmin extends SQLiteOpenHelper {

    public SQLiteAdmin(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS autores(id INT not null primary key, nombres TEXT(50), apellidos TEXT(50), isoPais TEXT(5), edad INT)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS libros(id INT not null primary key, titulo TEXT(100), idAutor INT, isbn TEXT(30), anioPublicacion INT, revision  INT, nroHojas INT)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS usuarios(id INT not null primary key, usuario TEXT(50), contrasenia TEXT(50))");

        sqLiteDatabase.execSQL("INSERT INTO usuarios(id, usuario, contrasenia) VALUES(1, 'admin', 'admin')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}