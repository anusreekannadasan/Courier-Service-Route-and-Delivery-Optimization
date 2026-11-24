package com.courier.service;

import org.springframework.stereotype.Service;

@Service
public class ChargeCalculator {

    public double calculate(ParcelCharge parcel, double weight) {
        return parcel.calculateCharge(weight);
    }
}