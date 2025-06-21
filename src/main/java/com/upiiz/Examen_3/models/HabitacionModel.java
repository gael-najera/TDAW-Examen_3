package com.upiiz.Examen_3.models;

import java.math.BigDecimal;

public class HabitacionModel {
    private Long id;
    private String tipo;
    private BigDecimal precioNoche;
    private boolean disponible;

    public HabitacionModel(String tipo, BigDecimal precioNoche, boolean disponible) {
        this.tipo = tipo;
        this.precioNoche = precioNoche;
        this.disponible = disponible;
    }

    public HabitacionModel() {}

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(BigDecimal precioNoche) {
        this.precioNoche = precioNoche;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
