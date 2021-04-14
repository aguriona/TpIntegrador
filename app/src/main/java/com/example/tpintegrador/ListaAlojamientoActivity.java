package com.example.tpintegrador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.example.tpintegrador.modelo.Propiedad;

import java.util.ArrayList;
import java.util.List;

public class ListaAlojamientoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    Propiedad listaPropiedad=new Propiedad();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alojamiento);
        Bundle extraParams = getIntent().getExtras();

        BroadcastReceiver br = new MyReciver();
       // IntentFilter filtro = new IntentFilter();
        //filtro.addAction(MyReciver.EVENTO_01);
       // getApplication().getApplicationContext()
       //         .registerReceiver(br,null);



        if(extraParams!=null){
            listaPropiedad.setId(extraParams.getInt("id",999));
            listaPropiedad.setNombre(extraParams.getString("nombre","Sin Nombre"));
            listaPropiedad.setPrecioDia(extraParams.getDouble("precio",0));
            listaPropiedad.setPermiteMascotas(extraParams.getBoolean("mascotas",false));

            Log.d("L01", "extraparam id: "+ extraParams.getInt("id"));
            Log.d("L01", "extraparam nombre: "+ extraParams.getString("nombre"));
            Log.d("L01", "extraparam precio: "+ extraParams.getDouble("precio"));
            Log.d("L01", "extraparam mascotas: "+ extraParams.getBoolean("mascotas"));

            Log.d("L01", "listapropiedadID: "+ listaPropiedad.getId());
            Log.d("L01", "listapropiedad Nombre: "+ listaPropiedad.getNombre());
            Log.d("L01", "listapropiedad Precio: "+ listaPropiedad.getPrecioDia());
            Log.d("L01", "listapropiedad Mascotas: "+ listaPropiedad.getPermiteMascotas());
        }
        else{Log.d("L01", "SIN extras ");}

        recyclerView = (RecyclerView) findViewById(R.id.listaPropiedadesRecycler);
        recyclerView.setHasFixedSize(true);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView


        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<Propiedad> listaPropiedades = new ArrayList<>();

        listaPropiedades.add(listaPropiedad);

        Log.d("L01", "lista size: "+ listaPropiedades.size());
        Log.d("L01", "listapropiedades: "+ listaPropiedades.get(0));
      //  Log.d("BA01", "nombre en listapropiedad: "+ listaPropiedad.getNombre());
        // specify an adapter (see also next example)
        mAdapter = new PropiedadesRecycler(listaPropiedades,this);
        recyclerView.setAdapter(mAdapter);
    }
}