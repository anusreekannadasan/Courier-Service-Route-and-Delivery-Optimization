
package com.courier.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.courier.entity.DeliveryAgent;
import com.courier.repository.DeliveryAgentRepository;

@Service
public class DeliveryAgentService {

    private final DeliveryAgentRepository agentRepository;

    public DeliveryAgentService(DeliveryAgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    // Register a new delivery agent
    public DeliveryAgent registerAgent(DeliveryAgent agent) {
        return agentRepository.save(agent);
    }

    // Get all delivery agents
    public List<DeliveryAgent> getAllAgents() {
        return agentRepository.findAll();
    }

    // Get agent by ID
    public DeliveryAgent getAgentById(Long id) {
        return agentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agent not found"));
    }

    // Delete an agent
    public void deleteAgent(Long id) {
        agentRepository.deleteById(id);
    }
}
