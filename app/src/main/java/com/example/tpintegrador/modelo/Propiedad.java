package com.example.tpintegrador.modelo;

import android.content.Context;
import android.widget.Toast;

public class Propiedad {

    private Integer id;
    private String nombre;
    private String descripcion;
    private Double precioDia;
    private Boolean poseeInternet;
    private Boolean permiteMascotas;
    private TipoAlojamiento tipoPropiedad;
    private Integer capacidadPersonas;
    private long latitud;
    private long longitud;

    public Boolean getPermiteMascotas() {
        return permiteMascotas;
    }

    public void setPermiteMascotas(Boolean permiteMascotas) {
        this.permiteMascotas = permiteMascotas;
    }

    public Propiedad() {
    }


    public Propiedad(Integer id, String nombre, String descripcion, Double precioDia, Boolean poseeInternet, Boolean permiteMascotas, TipoAlojamiento tipoPropiedad, Integer capacidadPersonas, long latitud, long longitud) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioDia = precioDia;
        this.poseeInternet = poseeInternet;
        this.permiteMascotas = permiteMascotas;
        this.tipoPropiedad = tipoPropiedad;
        this.capacidadPersonas = capacidadPersonas;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecioDia() {
        return precioDia;
    }

    public void setPrecioDia(Double precioDia) {
        this.precioDia = precioDia;
    }

    public Boolean getPoseeInternet() {
        return poseeInternet;
    }

    public void setPoseeInternet(Boolean poseeInternet) {
        this.poseeInternet = poseeInternet;
    }

    public TipoAlojamiento getTipoPropiedad() {
        return tipoPropiedad;
    }

    public void setTipoPropiedad(TipoAlojamiento tipoPropiedad) {
        this.tipoPropiedad = tipoPropiedad;
    }

    public Integer getCapacidadPersonas() {
        return capacidadPersonas;
    }

    public void setCapacidadPersonas(Integer capacidadPersonas) {
        this.capacidadPersonas = capacidadPersonas;
    }

    public long getLatitud() {
        return latitud;
    }

    public void setLatitud(long latitud) {
        this.latitud = latitud;
    }

    public long getLongitud() {
        return longitud;
    }

    public void setLongitud(long longitud) {
        this.longitud = longitud;
    }
}
