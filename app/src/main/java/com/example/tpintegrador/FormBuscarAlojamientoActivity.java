package com.example.tpintegrador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.example.tpintegrador.modelo.Propiedad;
import com.example.tpintegrador.modelo.TipoAlojamiento;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FormBuscarAlojamientoActivity extends AppCompatActivity {

    EditText txtNombre, txtprecio;
    Switch swMascotas;
    Button btnbuscar;
    MiDBOpenHelper dbOpenHelper;
    boolean mascotas = false;

    public Propiedad listaPropiedad= new Propiedad();






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_buscar_alojamiento);

        txtNombre = findViewById(R.id.txtNombre);
        txtprecio = findViewById(R.id.txtprecio);
        swMascotas = findViewById(R.id.swMascotas2);
        btnbuscar = findViewById(R.id.btnBuscar);



        dbOpenHelper = new MiDBOpenHelper(FormBuscarAlojamientoActivity.this);



        swMascotas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mascotas = isChecked;
                Log.d("BA01", "onClick: "+ mascotas);
            } });



          btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mascoaint = mascotas ? 1 : 0;
                String nombre = txtNombre.getText().toString();
                String precio = txtprecio.getText().toString();
                String masco = String.valueOf(mascoaint);


                String[] consNombre = {nombre,precio,masco};
                SQLiteDatabase db = dbOpenHelper.getReadableDatabase();


       Cursor resultado = db.rawQuery("select _id, nombre, descripcion, precio, internet, mascotas, tipo, personas from TABLA where nombre=? or (precio=? or mascotas=?) ", consNombre );


                while (resultado.moveToNext()) {
                    listaPropiedad.setId(resultado.getInt(0));
                    listaPropiedad.setNombre(resultado.getString(1));
                    listaPropiedad.setDescripcion(resultado.getString(2));
                    listaPropiedad.setPrecioDia(resultado.getDouble(3));
                    listaPropiedad.setPoseeInternet(Boolean.valueOf(resultado.getString(4)));
                    listaPropiedad.setPermiteMascotas(Boolean.valueOf(resultado.getString(5)));
                    listaPropiedad.setTipoPropiedad(resultado.getString(6));
                    listaPropiedad.setCapacidadPersonas(resultado.getInt(7));
                    Log.d("BA01", "ID: "+ listaPropiedad.getId());
                    Log.d("BA01", "Nombre: "+ listaPropiedad.getNombre());


                }
                Intent i1 = new Intent(FormBuscarAlojamientoActivity.this,ListaAlojamientoActivity.class);
                i1.putExtra("id",listaPropiedad.getId());
                startActivity(i1);

            } });






    }
}