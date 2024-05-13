package com.example.pruebas_tfg.Model;

import java.io.Serializable;

public class Entrenador implements Serializable {
    private Integer idEntrenador;
    private String nombre;
    private String apellido;
    private String nacionalidad;
    private String fechaDeNacimiento;

    public int getIdEntrenador() {
        return idEntrenador;
    }

    public Entrenador(int idEntrenador, String nombre, String apellido, String nacionalidad, String fechaDeNacimiento) {
        this.idEntrenador = idEntrenador;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public void setIdEntrenador(int idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Entrenador(String nombre, String apellido, String nacionalidad, String fechaDeNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
        this.fechaDeNacimiento = fechaDeNacimiento;
    }
}
