package com.courier.service;

import java.util.List;
import java.util.PriorityQueue;

import org.springframework.stereotype.Service;

import com.courier.entity.Delivery;
import com.courier.entity.DeliveryAgent;
import com.courier.repository.DeliveryRepository;
import com.courier.repository.DeliveryAgentRepository;

@Service
public class DeliveryProcessor {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryAgentRepository agentRepository;

    public DeliveryProcessor(
            DeliveryRepository deliveryRepository,
            DeliveryAgentRepository agentRepository) {
        this.deliveryRepository = deliveryRepository;
        this.agentRepository = agentRepository;
    }

    public void processDeliveries() throws InterruptedException {

        PriorityQueue<Delivery> queue = new PriorityQueue<>(
                (a, b) -> a.getDeliveryId().compareTo(b.getDeliveryId()));

        for (Delivery d : deliveryRepository.findAll()) {
            if ("Pending".equalsIgnoreCase(d.getDeliveryStatus())) {
                queue.add(d);
            }
        }

        List<DeliveryAgent> agents = agentRepository.findAll();

        if (agents.isEmpty()) {
            throw new RuntimeException("No delivery agents available");
        }

        Thread[] threads = new Thread[agents.size()];

        for (int i = 0; i < agents.size(); i++) {
            DeliveryAgent agent = agents.get(i);

            threads[i] = new Thread(() -> {
                while (true) {
                    Delivery delivery;

                    // Safely retrieve one delivery from shared queue
                    synchronized (queue) {
                        delivery = queue.poll();
                    }

                    if (delivery == null) {
                        break;
                    }

                    delivery.setAgentId(agent.getAgentId());
                    delivery.setDeliveryStatus("Processing");

                    deliveryRepository.save(delivery);

                    System.out.println(
                            agent.getAgentName() + " is processing "
                            + delivery.getTrackingNumber());
                }
            });

            threads[i].start();
        }

        // Wait for all agent threads to finish
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("All deliveries processed!");
    }
}