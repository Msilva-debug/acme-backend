package com.acme.service.impl;

import com.acme.dto.OrderRequestRest;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;import com.acme.dto.OrderResponse;
import com.acme.service.AcmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class AcmeServiceImpl implements AcmeService {
    @Value("${url.prueba}")
    private String urlPrueba;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public OrderResponse order(OrderRequestRest infoOrder) {
        log.info("Order: {}", infoOrder);
        String responseApi = sendXml(buildXml(infoOrder));
        log.info("Response API: {}", responseApi);
        return parseXml(responseApi);
    }

    public String sendXml(String xml) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
        headers.set("Accept", "text/xml");

        HttpEntity<String> entity = new HttpEntity<>(xml, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                urlPrueba,
                HttpMethod.GET,
                entity,
                String.class
        );

        return response.getBody();
    }

    public String buildXml(OrderRequestRest dto) {
        return
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                        "xmlns:env=\"http://WSDLs/EnvioPedidos/EnvioPedidosAcme\">" +

                        "<soapenv:Header/>" +

                        "<soapenv:Body>" +
                        "<env:EnvioPedidoAcme>" +
                        "<EnvioPedidoRequest>" +

                        "<pedido>" + dto.getNumPedido() + "</pedido>" +
                        "<Cantidad>" + dto.getCantidadPedido() + "</Cantidad>" +
                        "<EAN>" + dto.getCodigoEAN() + "</EAN>" +
                        "<Producto>" + dto.getNombreProducto() + "</Producto>" +
                        "<Cedula>" + dto.getNumDocumento() + "</Cedula>" +
                        "<Direccion>" + dto.getDireccion() + "</Direccion>" +

                        "</EnvioPedidoRequest>" +
                        "</env:EnvioPedidoAcme>" +
                        "</soapenv:Body>" +

                        "</soapenv:Envelope>";
    }

    public OrderResponse parseXml(String xml) {
        try {
            Document doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .parse(new ByteArrayInputStream(xml.getBytes()));

            doc.getDocumentElement().normalize();

            String codigo = doc.getElementsByTagName("Codigo").item(0).getTextContent();
            String mensaje = doc.getElementsByTagName("Mensaje").item(0).getTextContent();
            return new OrderResponse(codigo, mensaje);

        } catch (Exception e) {
            throw new RuntimeException("Error parseando XML", e);
        }
    }
}
