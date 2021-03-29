package com.example.tpintegrador.modelo;

import java.util.ArrayList;
import java.util.List;

public class TipoAlojamiento {

    private String Tipo;

    public TipoAlojamiento(String tipo) {
        Tipo = tipo;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    @Override
    public String toString() {
        return Tipo;
    }

    // HABITACION_HOTEL,CASA,DEPARTAMENTO,HOSTEL

}