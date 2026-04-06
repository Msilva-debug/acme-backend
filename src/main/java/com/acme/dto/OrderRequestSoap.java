package com.acme.dto;

import lombok.Data;

@Data
public class OrderRequestSoap {
    private String pedido;
    private String Cantidad;
    private String EAN;
    private String Producto;
    private String Cedula;
    private String Direccion;

}
