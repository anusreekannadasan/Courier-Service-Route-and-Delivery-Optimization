package com.courier.entity;

import com.courier.service.ParcelCharge;

public class FragileParcel implements ParcelCharge {

    @Override
    public double calculateCharge(double weight) {
        return weight * 70;
    }
}