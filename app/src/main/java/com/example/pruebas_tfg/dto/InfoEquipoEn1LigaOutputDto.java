package com.example.pruebas_tfg.dto;

import java.io.Serializable;

public class InfoEquipoEn1LigaOutputDto implements Serializable {

    private Integer codEquipo;
    private Integer codLiga;
    private Integer puntos;
    private Integer partidosJugados;
    private Integer partidosGanados;
    private Integer partidosPerdidos;
    private Integer partidosEmpatados;
    private Integer golesFavor;
    private Integer golesContra;

    public Integer getCodEquipo() {
        return codEquipo;
    }


    public void setCodEquipo(Integer codEquipo) {
        this.codEquipo = codEquipo;
    }

    public Integer getCodLiga() {
        return codLiga;
    }

    public void setCodLiga(Integer codLiga) {
        this.codLiga = codLiga;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public Integer getPartidosJugados() {
        return partidosJugados;
    }

    public void setPartidosJugados(Integer partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public Integer getPartidosGanados() {
        return partidosGanados;
    }

    public void setPartidosGanados(Integer partidosGanados) {
        this.partidosGanados = partidosGanados;
    }

    public Integer getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public void setPartidosPerdidos(Integer partidosPerdidos) {
        this.partidosPerdidos = partidosPerdidos;
    }

    public Integer getPartidosEmpatados() {
        return partidosEmpatados;
    }

    public void setPartidosEmpatados(Integer partidosEmpatados) {
        this.partidosEmpatados = partidosEmpatados;
    }

    public Integer getGolesFavor() {
        return golesFavor;
    }

    public void setGolesFavor(Integer golesFavor) {
        this.golesFavor = golesFavor;
    }

    public Integer getGolesContra() {
        return golesContra;
    }

    public void setGolesContra(Integer golesContra) {
        this.golesContra = golesContra;
    }

    public InfoEquipoEn1LigaOutputDto(Integer codEquipo, Integer codLiga, Integer puntos, Integer partidosJugados, Integer partidosGanados, Integer partidosPerdidos, Integer partidosEmpatados, Integer golesFavor, Integer golesContra) {
        this.codEquipo = codEquipo;
        this.codLiga = codLiga;
        this.puntos = puntos;
        this.partidosJugados = partidosJugados;
        this.partidosGanados = partidosGanados;
        this.partidosPerdidos = partidosPerdidos;
        this.partidosEmpatados = partidosEmpatados;
        this.golesFavor = golesFavor;
        this.golesContra = golesContra;
    }
}
