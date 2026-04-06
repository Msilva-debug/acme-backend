package com.acme.service;

import com.acme.dto.OrderRequestRest;
import com.acme.dto.OrderResponse;
import org.springframework.stereotype.Service;

@Service
public interface AcmeService {
    OrderResponse order(OrderRequestRest infoOrder);
}
