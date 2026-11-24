
package com.courier.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.courier.entity.Parcel;
import com.courier.repository.ParcelRepository;

@Service
public class ParcelService {

    private final ParcelRepository parcelRepository;

    public ParcelService(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    public Parcel registerParcel(Parcel parcel) {
        return parcelRepository.save(parcel);
    }

    public List<Parcel> getAllParcels() {
        return parcelRepository.findAll();
    }
}
