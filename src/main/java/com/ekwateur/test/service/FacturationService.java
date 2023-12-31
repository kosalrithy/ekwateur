package com.ekwateur.test.service;

import org.springframework.stereotype.Service;
import com.ekwateur.test.model.Customer;
import com.ekwateur.test.model.EnergyType;
import com.ekwateur.test.model.CustomerType;

@Service
public class FacturationService {

    final static double PARTICULAR_ELECTRICITY_PRICE = 0.133;
    final static double PARTICULAR_GAZ_PRICE =0.108;
    final static double PRO_ELECTRICITY_BELOW_1M = 0.112;
    final static double PRO_GAZ_PRICE_BELOW_1M = 0.117;
    final static double PRO_ELECTRICITY_ABOVE_1M = 0.110;
    final static double PRO_GAZ_PRICE_ABOVE_1M = 0.123;

    public double computeFacturationAmount(Customer customer, EnergyType energyType, double consumption) {
        double pricePerKWh = getPricePerKWh(customer, energyType);
        return pricePerKWh * consumption;
    }

    private double getPricePerKWh(Customer customer, EnergyType energyType) {
        switch (customer.customerType()) {
            case PRO:
                if (customer.revenue() > 1000000) {
                    return (energyType == EnergyType.ELECTRICITY) ? PRO_ELECTRICITY_ABOVE_1M : PRO_GAZ_PRICE_ABOVE_1M;
                } else {
                    return (energyType == EnergyType.ELECTRICITY) ? PRO_ELECTRICITY_BELOW_1M : PRO_GAZ_PRICE_BELOW_1M;
                }
            case PARTICULAR:
                return (energyType == EnergyType.ELECTRICITY) ? PARTICULAR_ELECTRICITY_PRICE : PARTICULAR_GAZ_PRICE;
            // Ajoutez d'autres cas si nécessaire
            default:
                return (energyType == EnergyType.ELECTRICITY) ? PARTICULAR_ELECTRICITY_PRICE : PARTICULAR_GAZ_PRICE;
        }
    }
}