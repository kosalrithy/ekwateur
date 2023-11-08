package com.ekwateur.test.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import com.ekwateur.test.model.Customer;
import com.ekwateur.test.model.CustomerType;
import com.ekwateur.test.model.EnergyType;

public class FacturationServiceTest {

    final static double PART_ELECTRICITY_PRICE = 0.133;
    final static double PART_GAZ_PRICE =0.108;
    final static double PRO_ELECTRICITY_BELOW_1M = 0.112;
    final static double PRO_GAZ_PRICE_BELOW_1M = 0.117;
    final static double PRO_ELECTRICITY_ABOVE_1M = 0.110;
    final static double PRO_GAZ_PRICE_ABOVE_1M = 0.123;

    private FacturationService facturationService;

    @BeforeEach
    public void setUp() {
        facturationService = new FacturationService();
    }

    // Cas PART avec CA < 1000000
    @Test
    public void testComputeFacturationAmountPART() {
        Customer customer = new Customer("customer_ref_1", CustomerType.PART);
        customer.setRevenue(123456);
        EnergyType energyType = EnergyType.ELECTRICITY;
        double consumption = 1600;

        double expectedAmount = this.PART_ELECTRICITY_PRICE * consumption;
        double actualAmount = facturationService.computeFacturationAmount(customer, energyType, consumption);

        Assertions.assertThat(actualAmount).isEqualTo(expectedAmount);
    }


    // Cas PRO avec CA < 1000000
    @Test
    public void testComputeFacturationAmountPROBelow1M() {
        Customer customer = new Customer("customer_ref_2", CustomerType.PRO);
        customer.setRevenue(123456);
        EnergyType energyType = EnergyType.GAZ;
        double consumption = 1700;

        double expectedAmount = this.PRO_GAZ_PRICE_BELOW_1M * consumption;
        double actualAmount = facturationService.computeFacturationAmount(customer, energyType, consumption);

        Assertions.assertThat(actualAmount).isEqualTo(expectedAmount);
    }

    // Cas PRO avec CA > 1000000
    @Test
    public void testComputeFacturationAmountPROAbove1M() {
        Customer customer = new Customer("customer_ref_3", CustomerType.PRO);
        customer.setRevenue(1234567);
        EnergyType energyType = EnergyType.ELECTRICITY;
        double consumption = 1800;

        double expectedAmount = this.PRO_ELECTRICITY_ABOVE_1M * consumption;
        double actualAmount = facturationService.computeFacturationAmount(customer, energyType, consumption);

        Assertions.assertThat(actualAmount).isEqualTo(expectedAmount);
    }
}
