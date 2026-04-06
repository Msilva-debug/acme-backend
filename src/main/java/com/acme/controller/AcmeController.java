package com.acme.controller;

import com.acme.dto.OrderRequestRest;
import com.acme.dto.OrderResponse;
import com.acme.service.AcmeService;
import com.acme.utils.dto.GenericDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class AcmeController {
    private final AcmeService acmeService;
    @PostMapping
    public ResponseEntity<GenericDto<OrderResponse>> order(@RequestBody OrderRequestRest inforOrder) {
        return ResponseEntity.ok(GenericDto.success(acmeService.order(inforOrder)));
    }
}
