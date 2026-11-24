package com.courier.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.courier.entity.Delivery;
import com.courier.repository.DeliveryRepository;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public Delivery registerDelivery(Delivery delivery) {
        delivery.setDeliveryStatus("Pending");
        return deliveryRepository.save(delivery);
    }

    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    public Delivery getDeliveryById(Long id) {
        return deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));
    }

    public Delivery updateStatus(Long id, String status) {
        Delivery delivery = getDeliveryById(id);
        delivery.setDeliveryStatus(status);
        return deliveryRepository.save(delivery);
    }
}