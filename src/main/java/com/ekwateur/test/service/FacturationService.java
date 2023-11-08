package com.ekwateur.test.service;

import org.springframework.stereotype.Service;
import com.ekwateur.test.model.Customer;
import com.ekwateur.test.model.EnergyType;

@Service
public class FacturationService {

    public double computeFacturationAmount(Customer customer, EnergyType energyType, double consumption) {
        // TODO
        return 0d;
    }
}