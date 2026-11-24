package com.courier.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.courier.entity.TrackingHistory;
import com.courier.service.TrackingService;
import com.courier.service.DeliveryService;
import com.courier.entity.Delivery;

@RestController
@RequestMapping("/api/tracking")
@CrossOrigin
public class TrackingController {

    private final TrackingService trackingService;
    private final DeliveryService deliveryService;

    public TrackingController(TrackingService trackingService,
                              DeliveryService deliveryService) {
        this.trackingService = trackingService;
        this.deliveryService = deliveryService;
    }

    @GetMapping("/{trackingNumber}")
    public List<TrackingHistory> trackDelivery(
            @PathVariable String trackingNumber) {

        List<Delivery> deliveries = deliveryService.getAllDeliveries();

        Delivery delivery = deliveries.stream()
                .filter(d -> d.getTrackingNumber().equals(trackingNumber))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Tracking number not found"));

        return trackingService.getTrackingHistory(delivery.getDeliveryId());
    }

    @PostMapping("/{deliveryId}")
    public TrackingHistory addUpdate(
            @PathVariable Long deliveryId,
            @RequestBody TrackingHistory tracking) {

        tracking.setDeliveryId(deliveryId);
        return trackingService.addTracking(tracking);
    }
}