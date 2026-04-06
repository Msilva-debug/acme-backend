package com.acme.dto;

public class OrderResponse {
    private String codigoEnvio;
    private String estado;

    public OrderResponse(String codigoEnvio, String estado) {
        this.codigoEnvio = codigoEnvio;
        this.estado = estado;
    }
}
