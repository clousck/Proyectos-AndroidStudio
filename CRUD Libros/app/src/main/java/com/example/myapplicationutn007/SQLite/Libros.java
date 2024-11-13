package com.example.myapplicationutn007.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Libros {

    private SQLiteAdmin sqlAdmin;
    private SQLiteDatabase db;

    public Libros(Context ctx, String nombreBdd, int version) {
        sqlAdmin = new SQLiteAdmin(ctx, nombreBdd, null, version);
        db = sqlAdmin.getWritableDatabase();
    }

    public Libro Create(int id, String titulo, int idAutor, String isbn, int anioPublicacion, int revision, int nroHojas) {
        ContentValues datos = new ContentValues();
        datos.put("id", id);
        datos.put("titulo", titulo);
        datos.put("idAutor", idAutor);
        datos.put("isbn", isbn);
        datos.put("anioPublicacion", anioPublicacion);
        datos.put("revision", revision);
        datos.put("nroHojas", nroHojas);

        long r = db.insert("libros", null, datos);
        if (r == -1)
            return null;
        else
            return new Libro(id, titulo, idAutor, isbn, anioPublicacion, revision, nroHojas);
    }

    public Libro Update(int id, String titulo, int idAutor, String isbn, int anioPublicacion, int revision, int nroHojas) {
        ContentValues datos = new ContentValues();
        datos.put("titulo", titulo);
        datos.put("idAutor", idAutor);
        datos.put("isbn", isbn);
        datos.put("anioPublicacion", anioPublicacion);
        datos.put("revision", revision);
        datos.put("nroHojas", nroHojas);

        int r = db.update("libros", datos, "id=" + id, null);
        return r == 0 ? null : new Libro(id, titulo, idAutor, isbn, anioPublicacion, revision, nroHojas);
    }

    public Libro Read_By_Id(int id) {
        Libro registro = null;
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT * FROM libros WHERE id=" + id, null);
            if (cursor.moveToFirst()) {
                registro = new Libro(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5), cursor.getInt(6));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return registro;
    }

    public List<Libro> Books_By_Autor_Id(int id) {
        List<Libro> registros = new ArrayList<Libro>();
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT * FROM libros WHERE idAutor = " + id, null);

            // Verificar si el cursor tiene datos y moverlo a la primera posición
            if (cursor.moveToFirst()) {
                do {
                    Libro registro = new Libro(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getInt(2),
                            cursor.getString(3),
                            cursor.getInt(4),
                            cursor.getInt(5),
                            cursor.getInt(6));
                    registros.add(registro);
                } while (cursor.moveToNext()); // Usar `moveToNext` para iterar sobre el cursor
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return registros;
    }


    public Libro[] Read_All() {
        Cursor cursor = db.rawQuery("SELECT * FROM libros ORDER BY titulo", null);
        Libro[] datos = new Libro[cursor.getCount()];
        int i = 0;

        while (cursor.moveToNext()) {
            datos[i++] = new Libro(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5), cursor.getInt(6));
        }
        cursor.close();
        return datos;
    }

    public boolean Delete(int id) {
        int r = db.delete("libros", "id=" + id, null);
        return r > 0;
    }
}