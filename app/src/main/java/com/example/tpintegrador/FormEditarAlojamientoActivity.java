package com.example.tpintegrador;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.tpintegrador.modelo.Propiedad;
import com.example.tpintegrador.modelo.TipoAlojamiento;

import java.util.ArrayList;
import java.util.List;

public class FormEditarAlojamientoActivity extends AppCompatActivity {

    Button botonBuscarCoordenadas;
    Button botonGuardar;
    EditText txtNombre, txtDescripcion, txtprecio, txtcapacidad ;
    CheckBox poseeInternet;
    Switch swMascotas;
    Spinner spTipoAloj;
    MiDBOpenHelper dbOpenHelper;
    Propiedad miPropiedad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_alojamiento);
        botonBuscarCoordenadas = findViewById(R.id.btnBuscarCoordenadas);
        botonGuardar = findViewById(R.id.btnGuardarAlojamiento);
        dbOpenHelper = new MiDBOpenHelper(FormEditarAlojamientoActivity.this);
        txtNombre = findViewById(R.id.txtNombre);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        txtprecio = findViewById(R.id.precio);
        poseeInternet = findViewById(R.id.poseeInternet);
        swMascotas = findViewById(R.id.swMascotas);
        spTipoAloj = findViewById(R.id.cmbTipoPropiedad);
        txtcapacidad = findViewById(R.id.capacidad);
        //TipoAlojamiento tipo = new TipoAlojamiento();
        miPropiedad = new Propiedad();

        ArrayAdapter<TipoAlojamiento> tipoAlojamiento = new ArrayAdapter<TipoAlojamiento>(this,android.R.layout.simple_spinner_dropdown_item,listarTipo());
        spTipoAloj.setAdapter(tipoAlojamiento);


        botonBuscarCoordenadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FormEditarAlojamientoActivity.this,MapsActivity.class);
                i.setAction(MainActivity.ACCION_BUSCAR_COORDENADAS);
                startActivityForResult(i,MainActivity.REQUEST_CODE_BUSCAR_MAPA);
            }
        });

        botonGuardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               // Propiedad miPropiedad = new Propiedad();
                    if (txtNombre.length()>5) {
                        miPropiedad.setNombre(txtNombre.toString());
                    }
                    else{
                        txtNombre.requestFocus();
                           // Toast.makeText(this, "Nombre Incorrecto", Toast.LENGTH_SHORT).show();
                    }
                miPropiedad.setNombre(txtDescripcion.toString());
                miPropiedad.setPrecioDia(Double.valueOf(txtprecio.toString()));

                poseeInternet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        miPropiedad.setPoseeInternet(poseeInternet.isChecked());
                    }
                });


                swMascotas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                miPropiedad.setPermiteMascotas(swMascotas.isChecked()); }


                });

               spTipoAloj.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                   @Override
                   public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                       TipoAlojamiento elejido = tipoAlojamiento.getItem(position);
                       miPropiedad.setDescripcion(elejido.getTipo());
                   }

                   @Override
                   public void onNothingSelected(AdapterView<?> parent) {

                   }
               });

                miPropiedad.setCapacidadPersonas(Integer.valueOf(txtcapacidad.toString()));


                AsyncTaskGuardarAlojamiento tarea = new AsyncTaskGuardarAlojamiento();
                tarea.execute(miPropiedad);


            }

            });
        }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_OK){
            if(requestCode==MainActivity.REQUEST_CODE_BUSCAR_MAPA) {
                // setear las coordenadas
            }
        }
    }

    public class AsyncTaskGuardarAlojamiento extends AsyncTask<Propiedad, Integer, Long> {

        @Override
        protected Long doInBackground(Propiedad... propiedad) {
            SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
            ContentValues fila = new ContentValues();

            fila.put("Nombre", miPropiedad.getNombre());
            fila.put("Descripcion", miPropiedad.getDescripcion());
            fila.put("Precio", miPropiedad.getPrecioDia());
            fila.put("Internet", miPropiedad.getPoseeInternet());
            fila.put("Mascotas", miPropiedad.getPermiteMascotas());
            fila.put("Internet", miPropiedad.getPoseeInternet());
            fila.put("Personas", miPropiedad.getCapacidadPersonas());

            db.insert("alojamientoDB" , "nombre", fila);




            // ejecutar el insert
            return null;
        }

        @Override
        protected void onPreExecute() {

            // actualizar la interface ANTES de guardar
        }

        @Override
        protected void onPostExecute(Long aLong) {

            // actualizar la interface DESPUES  de guardar

        }

    }


    public List<TipoAlojamiento> listarTipo(){
        List<TipoAlojamiento> listaTipo = new ArrayList<>();


        listaTipo.add(new TipoAlojamiento("Habitacion Hotel"));
        listaTipo.add(new TipoAlojamiento("Casa"));
        listaTipo.add(new TipoAlojamiento("Departamento"));
        listaTipo.add(new TipoAlojamiento("Hostel"));

        return listaTipo;
    }



}