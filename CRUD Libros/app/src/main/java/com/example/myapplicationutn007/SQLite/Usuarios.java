package com.example.myapplicationutn007.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Usuarios {

    private SQLiteAdmin sqlAdmin;
    private SQLiteDatabase db;

    public Usuarios(Context ctx, String nombreBdd, int version) {
        sqlAdmin = new SQLiteAdmin(ctx, nombreBdd, null, version);
        db = sqlAdmin.getWritableDatabase();
    }

    // Método para crear un nuevo usuario
    public Usuario Create(int id, String usuario, String contrasenia) {
        ContentValues datos = new ContentValues();
        datos.put("id", id);
        datos.put("usuario", usuario);
        datos.put("contrasenia", contrasenia);

        long result = db.insert("usuarios", null, datos);
        if (result == -1) {
            return null;
        } else {
            return new Usuario(id, usuario, contrasenia);
        }
    }

    // Método para actualizar un usuario existente
    public Usuario Update(int id, String usuario, String contrasenia) {
        ContentValues datos = new ContentValues();
        datos.put("usuario", usuario);
        datos.put("contrasenia", contrasenia);

        int result = db.update("usuarios", datos, "id=?", new String[]{String.valueOf(id)});
        if (result == 0) {
            return null;
        } else {
            return new Usuario(id, usuario, contrasenia);
        }
    }

    // Método para obtener un usuario por su ID
    public Usuario Read_By_Id(int id) {
        Usuario usuario = null;
        Cursor cursor = db.rawQuery("SELECT * FROM usuarios WHERE id=?", new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            usuario = new Usuario(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        }
        cursor.close();
        return usuario;
    }

    // Método para obtener todos los usuarios
    public Usuario[] Read_All() {
        Cursor cursor = db.rawQuery("SELECT * FROM usuarios ORDER BY usuario", null);
        Usuario[] usuarios = new Usuario[cursor.getCount()];
        int i = 0;

        while (cursor.moveToNext()) {
            usuarios[i++] = new Usuario(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        }
        cursor.close();
        return usuarios;
    }

    // Método para eliminar un usuario por su ID
    public boolean Delete(int id) {
        int result = db.delete("usuarios", "id=?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    public boolean login(String usuario, String contrasenia) {
        Cursor cursor = db.rawQuery("SELECT * FROM usuarios WHERE usuario = ? AND contrasenia = ?", new String[]{usuario, contrasenia});

        boolean isAuthenticated = cursor.getCount() > 0;
        cursor.close();

        return isAuthenticated;
    }
}
