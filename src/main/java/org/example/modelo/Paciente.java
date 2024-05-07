package org.example.modelo;

public class Paciente {
    private long id;
    private String nombre;

    private String raza;
    private Long peso;

    @Override
    public String toString() {
        return   id +
                " | " +
                nombre +
                " | " +
                 raza +
                " | " +
                peso ;
    }

    public Paciente() {

    }

    public Paciente(long id, String nombre, String raza, Long peso) {
        this.id = id;
        this.nombre = nombre;
        this.raza = raza;
        this.peso = peso;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Long getPeso() {
        return peso;
    }

    public void setPeso(Long peso) {
        this.peso = peso;
    }


}
