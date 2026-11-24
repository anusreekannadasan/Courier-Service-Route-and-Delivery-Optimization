package com.courier.entity;

import com.courier.service.ParcelCharge;

public class StandardParcel implements ParcelCharge {

    @Override
    public double calculateCharge(double weight) {
        return weight * 30;
    }
}