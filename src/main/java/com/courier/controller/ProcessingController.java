package com.courier.controller;

import org.springframework.web.bind.annotation.*;
import com.courier.service.DeliveryProcessor;

@RestController
@RequestMapping("/api/process")
@CrossOrigin
public class ProcessingController {

    private final DeliveryProcessor processor;

    public ProcessingController(DeliveryProcessor processor) {
        this.processor = processor;
    }

    @PostMapping
    public String processDeliveries() throws InterruptedException {
        processor.processDeliveries();
        return "All pending deliveries processed successfully!";
    }
}