package com.example.tpintegrador;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.MessagePattern;
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

        ArrayAdapter<TipoAlojamiento> tipoAlojamiento = new ArrayAdapter<TipoAlojamiento>(this,android.R.layout.simple_spinner_dropdown_item,ListarTipo());
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
                    if (txtNombre.length()>5)
                        miPropiedad.setNombre(txtNombre.getText().toString());

                    else{
                        txtNombre.requestFocus();
                        Toast.makeText(FormEditarAlojamientoActivity.this, "Nombre Incorrecto", Toast.LENGTH_SHORT).show();
                    }
                miPropiedad.setDescripcion(txtDescripcion.getText().toString());
                miPropiedad.setPrecioDia(Double.valueOf(txtprecio.getText().toString()));


                poseeInternet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        miPropiedad.setPoseeInternet(isChecked); } });


                swMascotas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        miPropiedad.setPermiteMascotas(isChecked); } });



               spTipoAloj.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                   @Override
                   public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String tiposelec = tipoAlojamiento.getItem(position).toString();
                      miPropiedad.setTipoPropiedad(tiposelec);
                   }

                   @Override
                  public void onNothingSelected(AdapterView<?> parent) {

                   }
               });

               miPropiedad.setCapacidadPersonas(Integer.valueOf(txtcapacidad.getText().toString()));


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

            String dbnombre = miPropiedad.getNombre();
            String dbdescripcion = miPropiedad.getDescripcion();
            Double dbpprecio = miPropiedad.getPrecioDia();
            Boolean dbposeeInternet = miPropiedad.getPoseeInternet();
            Boolean dbpermiteMascotas = miPropiedad.getPermiteMascotas();
            String dbtipo = miPropiedad.getTipoPropiedad();
            Integer dbcapacidad = miPropiedad.getCapacidadPersonas();
            long dblatitud = miPropiedad.getLatitud();
            long dblongitud = miPropiedad.getLongitud();

            fila.put("nombre", dbnombre);
            fila.put("descripcion", dbdescripcion);
            fila.put("precio", dbpprecio);
            fila.put("internet", dbposeeInternet);
            fila.put("mascotas", dbpermiteMascotas);
            fila.put("tipo", dbtipo);
            fila.put("personas", dbcapacidad);
            fila.put("latitud", dblatitud);
            fila.put("longitud", dblongitud);

            db.insert("TABLA" , "nombre", fila);

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


    public List<TipoAlojamiento> ListarTipo(){
        List<TipoAlojamiento> listaTipo = new ArrayList<>();


        listaTipo.add(new TipoAlojamiento("Habitacion Hotel"));
        listaTipo.add(new TipoAlojamiento("Casa"));
        listaTipo.add(new TipoAlojamiento("Departamento"));
        listaTipo.add(new TipoAlojamiento("Hostel"));

        return listaTipo;
    }



}