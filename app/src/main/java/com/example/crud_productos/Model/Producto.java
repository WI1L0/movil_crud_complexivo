package com.example.crud_productos.Model;

public class Producto {

    private String Codigo;
    private String Nombre;
    private Double Precio;

    public Producto(String codigo, String nombre, Double precio) {
        Codigo = codigo;
        Nombre = nombre;
        Precio = precio;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Double getPrecio() {
        return Precio;
    }

    public void setPrecio(Double precio) {
        Precio = precio;
    }
}
