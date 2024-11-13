package com.example.myapplicationutn007;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplicationutn007.SQLite.Autor;
import com.example.myapplicationutn007.SQLite.Autores;
import com.example.myapplicationutn007.SQLite.Libro;
import com.example.myapplicationutn007.SQLite.Libros;

import java.util.List;

public class AutorActivity extends AppCompatActivity {

    EditText id, nombres, apellidos, isoPais, edad;
    Autores lstAutores;
    Libros lstLibros;
    TableLayout tblLibros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_autor);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        id = findViewById(R.id.etId);
        nombres = findViewById(R.id.etNombres);
        apellidos = findViewById(R.id.etApellidos);
        isoPais = findViewById(R.id.etIsoPais);
        edad = findViewById(R.id.etEdad);

        tblLibros = findViewById(R.id.tblLibrosAutor);

        lstAutores = new Autores(this, "biblioteca.db", 1);
        lstLibros = new Libros(this, "biblioteca.db", 1);
    }

    public void CrearAutor(View view) {
        try {
            Autor r = lstAutores.Create(
                    Integer.parseInt(id.getText().toString()),
                    nombres.getText().toString(),
                    apellidos.getText().toString(),
                    isoPais.getText().toString(),
                    Integer.parseInt(edad.getText().toString())
            );

            if(r != null)
            {
                Toast.makeText(
                        this,
                        "Autor creado correctamente!!",
                        Toast.LENGTH_LONG
                ).show();
            }
            else
            {
                Toast.makeText(
                        this, "ERROR AL CREAR AUTOR!!",
                        Toast.LENGTH_LONG
                ).show();
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            Toast.makeText(
                    this, "ERROR AL CREAR AUTOR!!",
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    public void LeerAutor(View view) {
        try {
            Autor r = lstAutores.Read_By_Id(Integer.parseInt(id.getText().toString()));
            if(r != null)
            {
                nombres.setText(r.Nombres);
                apellidos.setText(r.Apellidos);
                isoPais.setText(r.IsoPais);
                edad.setText(Integer.toString(r.Edad));

                MostrarLibros(r.Id);
            }
            else
            {
                Toast.makeText(
                        this, "Autor no encontrado!!",
                        Toast.LENGTH_LONG
                ).show();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(
                    this, "ERROR AL LEER AUTOR!!",
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    public void MostrarLibros(int idAutor) {
        try {
            System.out.println("AAAAAAAAA");
            // Limpiar cualquier fila existente
            tblLibros.removeAllViews();
            System.out.println("BBBBBBBBB");
            List<Libro> libros = lstLibros.Books_By_Autor_Id(idAutor);
            // Verificar si se han encontrado libros
            System.out.println("SIZE:"+libros.size());
            if (!libros.isEmpty()) {
                // Crear encabezado de la tabla
                TableRow header = new TableRow(this);
                TextView tvId = new TextView(this);
                tvId.setText("ID");
                tvId.setPadding(8, 8, 8, 8);
                header.addView(tvId);
                TextView tvTitulo = new TextView(this);
                tvTitulo.setText("Título");
                tvTitulo.setPadding(8, 8, 8, 8);
                header.addView(tvTitulo);
                TextView tvAnio = new TextView(this);
                tvAnio.setText("Año");
                tvAnio.setPadding(8, 8, 8, 8);
                header.addView(tvAnio);
                tblLibros.addView(header);

                for (Libro libro : libros) {
                    TableRow row = new TableRow(this);

                    // Crear nuevas vistas de TextView para cada celda
                    tvId = new TextView(this);
                    tvId.setText(Integer.toString(libro.Id));
                    tvId.setPadding(8, 8, 8, 8);

                    tvTitulo = new TextView(this);
                    tvTitulo.setText(libro.Titulo);
                    tvTitulo.setPadding(8, 8, 8, 8);

                    tvAnio = new TextView(this);
                    tvAnio.setText(Integer.toString(libro.AnioPublicacion));
                    tvAnio.setPadding(8, 8, 8, 8);

                    // Agregar los TextViews a la fila
                    row.addView(tvId);
                    row.addView(tvTitulo);
                    row.addView(tvAnio);

                    // Agregar la fila a la tabla
                    tblLibros.addView(row);
                }
            } else {
                // Si no hay libros, puedes mostrar un mensaje o simplemente no hacer nada
                Toast.makeText(this, "No se encontraron libros para este autor", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
        }
    }


    public void ActualizarAutor(View view) {
        try {
            Autor r = lstAutores.Update(
                    Integer.parseInt(id.getText().toString()),
                    nombres.getText().toString(),
                    apellidos.getText().toString(),
                    isoPais.getText().toString(),
                    Integer.parseInt(edad.getText().toString())
            );

            if(r != null)
            {
                Toast.makeText(
                        this,
                        "Autor actualizado correctamente!!",
                        Toast.LENGTH_LONG
                ).show();
            }
            else
            {
                Toast.makeText(
                        this, "ERROR AL ACTUALIZAR AUTOR!!",
                        Toast.LENGTH_LONG
                ).show();
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            Toast.makeText(
                    this, "ERROR AL ACTUALIZAR AUTOR!!",
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    public void EliminarAutor(View view){
        boolean r = lstAutores.Delete(Integer.parseInt(id.getText().toString()));

        if(r)
        {
            nombres.setText("");
            apellidos.setText("");
            isoPais.setText("");
            edad.setText("");
            Toast.makeText(
                    this,
                    "Autor eliminado correctamente!!",
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    public void RegresarActivity(View view)
    {
        finish();
    }
}