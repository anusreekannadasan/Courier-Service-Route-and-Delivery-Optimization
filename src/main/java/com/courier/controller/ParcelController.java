
package com.courier.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.courier.entity.Parcel;
import com.courier.service.ParcelService;

@RestController
@RequestMapping("/api/parcels")
@CrossOrigin
public class ParcelController {

    private final ParcelService parcelService;

    public ParcelController(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    @PostMapping
    public Parcel registerParcel(@RequestBody Parcel parcel) {
        return parcelService.registerParcel(parcel);
    }

    @GetMapping
    public List<Parcel> getAllParcels() {
        return parcelService.getAllParcels();
    }
}
