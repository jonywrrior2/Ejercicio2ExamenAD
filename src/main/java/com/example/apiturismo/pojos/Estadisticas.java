package com.example.apiturismo.pojos;

public class Estadisticas {
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getPromedio() {
        return promedio;
    }

    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }

    public Long getInactivos() {
        return inactivos;
    }

    public void setInactivos(Long inactivos) {
        this.inactivos = inactivos;
    }

    private Double total;
    private Double promedio;
    private Long inactivos;


}