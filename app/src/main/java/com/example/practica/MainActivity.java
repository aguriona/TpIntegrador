package com.example.practica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText codigo,nombre,descripcion,precio;
    Button guardar;
    Spinner tipo;
    BaseDatos basedatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tipo = findViewById(R.id.spinTipo);
        ArrayAdapter<Tipo> adapter = new ArrayAdapter<>( this,android.R.layout.simple_spinner_dropdown_item,listarTipo());
        tipo.setAdapter(adapter);

        codigo = findViewById(R.id.txtcodigo);
        nombre = findViewById(R.id.txtnombre);
        descripcion = findViewById(R.id.txtdescripcion);
        precio = findViewById(R.id.txtprecio);
        guardar = findViewById(R.id.btnguardar);

        basedatos = new BaseDatos(this );

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = basedatos.getWritableDatabase();
                String dbcodigo = codigo.getText().toString();
                String dbnombre = nombre.getText().toString();
                String dbdescripcion = descripcion.getText().toString();
                Integer dbprecio = Integer.valueOf(precio.getText().toString());
                ContentValues fila = new ContentValues();
                fila.put("Codigo", dbcodigo);
                fila.put("Nombre", dbnombre);
                fila.put("Descripcion", dbdescripcion);
                fila.put("Precio", dbprecio);
                db.insert("Inmueble" , "nombre", fila);

            }

        });


    }

    private static List<Tipo> listarTipo(){
        List<Tipo> listaTipo = new ArrayList<>();

        Integer id;
        String descripcion;

        listaTipo.add(new Tipo( id= 1 , descripcion= "Habitacion_Hotel"));
        listaTipo.add(new Tipo( id= 2 , descripcion= "Casa"));
        listaTipo.add(new Tipo( id= 3 , descripcion= "Departamento"));
        listaTipo.add(new Tipo( id= 4 , descripcion= "Hostel"));

        return listaTipo;
    }

}