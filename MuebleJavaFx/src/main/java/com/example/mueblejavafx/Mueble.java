package com.example.mueblejavafx;

public class Mueble {
    private int id;
    private String tipo;
    private String material;
    private int precio;

    public Mueble(){
    }

    public Mueble(int id, String tipo, String material, int precio) {
        this.id = id;
        this.tipo = tipo;
        this.material = material;
        this.precio = precio;
    }

    public Mueble(String tipo, String material, int precio) {
        this.tipo = tipo;
        this.material = material;
        this.precio = precio;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Mueble{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", material='" + material + '\'' +
                ", precio=" + precio +
                '}';
    }

}
