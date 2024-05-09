package com.example.pruebas_tfg.Model;


public class Equipo implements java.io.Serializable {
    private Integer idEquipo;
    private String nombreEquipo;

    private String ciudad;

    private Integer idEntrenador;

    private String imagen;



    private String presidente;


    public Equipo(String nombre, String ciudad, Integer idEntrenador, String imagen) {
        this.nombreEquipo = nombre;
        this.ciudad = ciudad;
        this.idEntrenador = idEntrenador;
        this.imagen = imagen;
    }

    public Equipo(Integer idEquipo, String nombre, String ciudad, Integer idEntrenador, String imagen) {
        this.idEquipo = idEquipo;
        this.nombreEquipo = nombre;
        this.ciudad = ciudad;
        this.idEntrenador = idEntrenador;
        this.imagen = imagen;
    }

    public Equipo(String nombreEquipo, String ciudad, Integer idEntrenador, String imagen, String presidente) {
        this.nombreEquipo = nombreEquipo;
        this.ciudad = ciudad;
        this.idEntrenador = idEntrenador;
        this.imagen = imagen;
        this.presidente = presidente;
    }

    public Integer getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Integer getIdEntrenador() {
        return idEntrenador;
    }

    public void setIdEntrenador(Integer idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Equipo(Integer idEquipo, String nombreEquipo, String ciudad, Integer idEntrenador, String imagen, String presidente) {
        this.idEquipo = idEquipo;
        this.nombreEquipo = nombreEquipo;
        this.ciudad = ciudad;
        this.idEntrenador = idEntrenador;
        this.imagen = imagen;
        this.presidente = presidente;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return null;
    }

    public String getPresidente() {
        return presidente;
    }

    public void setPresidente(String presidente) {
        this.presidente = presidente;
    }


}
