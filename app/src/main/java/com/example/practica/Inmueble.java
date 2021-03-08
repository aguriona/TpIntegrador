package com.example.practica;

public class Inmueble<integer, text> {
    private integer codigo;
    private text nombre;
    private text descripcion;
    private integer precio;

    public Inmueble() {
    }

    public integer getCodigo() {
        return codigo;
    }

    public void setCodigo(integer codigo) {
        this.codigo = codigo;
    }

    public text getNombre() {
        return nombre;
    }

    public void setNombre(text nombre) {
        this.nombre = nombre;
    }

    public text getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(text descripcion) {
        this.descripcion = descripcion;
    }

    public integer getPrecio() {
        return precio;
    }

    public void setPrecio(integer precio) {
        this.precio = precio;
    }

    public Inmueble(integer codigo, text nombre, text descripcion, integer precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }
}
