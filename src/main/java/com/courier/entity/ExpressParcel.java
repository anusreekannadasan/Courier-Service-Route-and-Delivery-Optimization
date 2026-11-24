package com.courier.entity;

import com.courier.service.ParcelCharge;

public class ExpressParcel implements ParcelCharge {

    @Override
    public double calculateCharge(double weight) {
        return weight * 50;
    }
}