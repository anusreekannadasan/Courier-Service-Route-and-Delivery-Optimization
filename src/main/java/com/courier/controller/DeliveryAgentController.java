
package com.courier.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.courier.entity.DeliveryAgent;
import com.courier.service.DeliveryAgentService;

@RestController
@RequestMapping("/api/agents")
@CrossOrigin
public class DeliveryAgentController {

    private final DeliveryAgentService agentService;

    public DeliveryAgentController(DeliveryAgentService agentService) {
        this.agentService = agentService;
    }

    // Register a delivery agent
    @PostMapping
    public DeliveryAgent registerAgent(@RequestBody DeliveryAgent agent) {
        return agentService.registerAgent(agent);
    }

    // Get all delivery agents
    @GetMapping
    public List<DeliveryAgent> getAllAgents() {
        return agentService.getAllAgents();
    }

    // Get agent by ID
    @GetMapping("/{id}")
    public DeliveryAgent getAgentById(@PathVariable Long id) {
        return agentService.getAgentById(id);
    }

    // Delete an agent
    @DeleteMapping("/{id}")
    public void deleteAgent(@PathVariable Long id) {
        agentService.deleteAgent(id);
    }
}
