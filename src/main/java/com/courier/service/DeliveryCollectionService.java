package com.courier.service;

import java.util.*;
import org.springframework.stereotype.Service;

import com.courier.entity.Delivery;
import com.courier.entity.DeliveryAgent;
import com.courier.repository.DeliveryRepository;
import com.courier.repository.DeliveryAgentRepository;

@Service
public class DeliveryCollectionService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryAgentRepository agentRepository;

    public DeliveryCollectionService(
            DeliveryRepository deliveryRepository,
            DeliveryAgentRepository agentRepository) {
        this.deliveryRepository = deliveryRepository;
        this.agentRepository = agentRepository;
    }

    public Map<String, Object> getCollectionSummary() {

        // PriorityQueue stores pending deliveries
        PriorityQueue<Delivery> pendingQueue =
                new PriorityQueue<>(
                    Comparator.comparing(Delivery::getDeliveryId));

        // HashMap stores agent ID and agent name
        HashMap<Long, String> agents = new HashMap<>();

        // ArrayList stores all tracking numbers
        ArrayList<String> deliveryRecords = new ArrayList<>();

        List<Delivery> deliveries = deliveryRepository.findAll();

        for (Delivery d : deliveries) {
            deliveryRecords.add(d.getTrackingNumber());

            if ("Pending".equalsIgnoreCase(d.getDeliveryStatus())) {
                pendingQueue.add(d);
            }
        }

        List<DeliveryAgent> agentList = agentRepository.findAll();

        for (DeliveryAgent agent : agentList) {
            agents.put(agent.getAgentId(), agent.getAgentName());
        }

        Map<String, Object> result = new LinkedHashMap<>();

        result.put("Pending Deliveries", pendingQueue.size());
        result.put("Agent Details", agents);
        result.put("Delivery Records", deliveryRecords);

        return result;
    }
}