package com.acme.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderRequestRest {
    private String numPedido;
    private String cantidadPedido;
    private String codigoEAN;
    private String nombreProducto;
    private String numDocumento;
    private String direccion;
}
