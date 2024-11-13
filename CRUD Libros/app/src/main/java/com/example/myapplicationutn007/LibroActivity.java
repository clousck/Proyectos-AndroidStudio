package com.example.myapplicationutn007;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplicationutn007.SQLite.Libro;
import com.example.myapplicationutn007.SQLite.Libros;

public class LibroActivity extends AppCompatActivity {

    EditText id, titulo, idAutor, isbn, anioPublicacion, revision, nroHojas;
    Libros lstLibros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_libro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lstLibros = new Libros(this, "biblioteca.db", 1);

        id = findViewById(R.id.etId);
        titulo = findViewById(R.id.etTitulo);
        idAutor = findViewById(R.id.etIdAutor);
        isbn = findViewById(R.id.etISBN);
        anioPublicacion = findViewById(R.id.etAnioPublicacion);
        revision = findViewById(R.id.etRevision);
        nroHojas = findViewById(R.id.etNroHojas);
    }

    public void CrearLibro(View view) {
        try {
            Libro l = lstLibros.Create(
                    Integer.parseInt(id.getText().toString()),
                    titulo.getText().toString(),
                    Integer.parseInt(idAutor.getText().toString()),
                    isbn.getText().toString(),
                    Integer.parseInt(anioPublicacion.getText().toString()),
                    Integer.parseInt(revision.getText().toString()),
                    Integer.parseInt(nroHojas.getText().toString())
            );

            if(l != null)
            {
                Toast.makeText(
                        this,
                        "Libro creado correctamente!!",
                        Toast.LENGTH_LONG
                ).show();
            }
            else
            {
                Toast.makeText(
                        this, "ERROR AL CREAR LIBRO!!",
                        Toast.LENGTH_LONG
                ).show();
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            Toast.makeText(
                    this, "ERROR AL CREAR LIBRO!!",
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    public void LeerLibro(View view) {
        try {
            Libro l = lstLibros.Read_By_Id(Integer.parseInt(id.getText().toString()));
            if(l != null)
            {
                titulo.setText(l.Titulo);
                idAutor.setText(Integer.toString(l.IdAutor));
                isbn.setText(l.Isbn);
                anioPublicacion.setText(Integer.toString(l.AnioPublicacion));
                revision.setText(Integer.toString(l.Revision));
                nroHojas.setText(Integer.toString(l.NroHojas));
                Toast.makeText(
                        this,
                        "Libro encontrado!!",
                        Toast.LENGTH_LONG
                ).show();
            }
            else
            {
                Toast.makeText(
                        this, "Libro no encontrado!!",
                        Toast.LENGTH_LONG
                ).show();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(
                    this, "ERROR AL LEER LIBRO!!",
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    public void ActualizarLibro(View view) {
        try {
                Libro l = lstLibros.Update(
                Integer.parseInt(id.getText().toString()),
                titulo.getText().toString(),
                Integer.parseInt(idAutor.getText().toString()),
                isbn.getText().toString(),
                Integer.parseInt(anioPublicacion.getText().toString()),
                Integer.parseInt(revision.getText().toString()),
                Integer.parseInt(nroHojas.getText().toString())
            );

            if(l != null)
            {
                Toast.makeText(
                        this,
                        "Libro actualizado correctamente!!",
                        Toast.LENGTH_LONG
                ).show();
            }
            else
            {
                Toast.makeText(
                        this, "ERROR AL ACTUALIZAR LIBRO!!",
                        Toast.LENGTH_LONG
                ).show();
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            Toast.makeText(
                    this, "ERROR AL ACTUALIZAR LIBRO!!",
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    public void EliminarLibro(View view){
        boolean l = lstLibros.Delete(Integer.parseInt(id.getText().toString()));

        if(l)
        {
            id.setText("");
            titulo.setText("");
            idAutor.setText("");
            isbn.setText("");
            anioPublicacion.setText("");
            revision.setText("");
            nroHojas.setText("");

            Toast.makeText(
                    this,
                    "Libro eliminado correctamente!!",
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    public void RegresarActivity(View view)
    {
        finish();
    }
}