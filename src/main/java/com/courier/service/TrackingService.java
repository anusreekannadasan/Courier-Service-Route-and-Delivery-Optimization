package com.courier.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.courier.entity.TrackingHistory;
import com.courier.repository.TrackingHistoryRepository;

@Service
public class TrackingService {

    private final TrackingHistoryRepository trackingRepository;

    public TrackingService(TrackingHistoryRepository trackingRepository) {
        this.trackingRepository = trackingRepository;
    }

    public TrackingHistory addTracking(TrackingHistory tracking) {
        return trackingRepository.save(tracking);
    }

    public List<TrackingHistory> getTrackingHistory(Long deliveryId) {
        return trackingRepository
                .findByDeliveryIdOrderByUpdatedAtDesc(deliveryId);
    }
}