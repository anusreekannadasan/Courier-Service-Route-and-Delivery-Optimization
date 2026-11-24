package com.courier.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.courier.entity.Delivery;
import com.courier.service.DeliveryService;

@RestController
@RequestMapping("/api/deliveries")
@CrossOrigin
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping
    public Delivery registerDelivery(@RequestBody Delivery delivery) {
        return deliveryService.registerDelivery(delivery);
    }

    @GetMapping
    public List<Delivery> getAllDeliveries() {
        return deliveryService.getAllDeliveries();
    }

    @GetMapping("/{id}")
    public Delivery getDeliveryById(@PathVariable Long id) {
        return deliveryService.getDeliveryById(id);
    }

    @PutMapping("/{id}/status")
    public Delivery updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return deliveryService.updateStatus(id, status);
    }
}