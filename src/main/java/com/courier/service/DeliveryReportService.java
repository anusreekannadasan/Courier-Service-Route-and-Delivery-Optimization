package com.courier.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.courier.entity.Delivery;
import com.courier.repository.DeliveryRepository;

@Service
public class DeliveryReportService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryReportService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public String generateReport() throws IOException {

        List<Delivery> deliveries = deliveryRepository.findAll();

        try (BufferedWriter writer =
                new BufferedWriter(new FileWriter("delivery-report.txt"))) {

            writer.write("COURIER DELIVERY REPORT");
            writer.newLine();
            writer.write("=======================");
            writer.newLine();

            for (Delivery d : deliveries) {
                writer.write("Delivery ID: " + d.getDeliveryId());
                writer.newLine();

                writer.write("Tracking Number: " + d.getTrackingNumber());
                writer.newLine();

                writer.write("Customer ID: " + d.getCustomerId());
                writer.newLine();

                writer.write("Parcel ID: " + d.getParcelId());
                writer.newLine();

                writer.write("Agent ID: " + d.getAgentId());
                writer.newLine();

                writer.write("Status: " + d.getDeliveryStatus());
                writer.newLine();

                writer.write("-----------------------");
                writer.newLine();
            }
        }

        return "Delivery report generated successfully!";
    }
}