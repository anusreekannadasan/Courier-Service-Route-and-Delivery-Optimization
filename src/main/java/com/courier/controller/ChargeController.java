package com.courier.controller;

import org.springframework.web.bind.annotation.*;

import com.courier.entity.*;
import com.courier.service.ChargeCalculator;
import com.courier.service.ParcelCharge;

@RestController
@RequestMapping("/api/charges")
@CrossOrigin
public class ChargeController {

    private final ChargeCalculator calculator;

    public ChargeController(ChargeCalculator calculator) {
        this.calculator = calculator;
    }

    @GetMapping
    public double calculateCharge(
            @RequestParam String type,
            @RequestParam double weight) {

        ParcelCharge parcel;

        switch (type.toLowerCase()) {
            case "standard":
                parcel = new StandardParcel();
                break;

            case "express":
                parcel = new ExpressParcel();
                break;

            case "fragile":
                parcel = new FragileParcel();
                break;

            default:
                throw new IllegalArgumentException("Invalid parcel type");
        }

        return calculator.calculate(parcel, weight);
    }
}