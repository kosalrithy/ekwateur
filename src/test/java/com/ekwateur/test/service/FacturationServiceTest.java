package com.ekwateur.test.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import com.ekwateur.test.model.Customer;
import com.ekwateur.test.model.CustomerType;
import com.ekwateur.test.model.EnergyType;

public class FacturationServiceTest {

    private FacturationService facturationService;

    @BeforeEach
    public void setUp() {
        facturationService = new FacturationService();
    }

    // Cas PART avec CA < 1000000
    @Test
    public void testComputeFacturationAmountPART() {
        Customer customer = new Customer("customer_ref_1", CustomerType.PART, 123456);
        EnergyType energyType = EnergyType.ELECTRICITY;
        double consumption = 1600;

        double expectedAmount = FacturationService.PART_ELECTRICITY_PRICE * consumption;
        double actualAmount = facturationService.computeFacturationAmount(customer, energyType, consumption);

        Assertions.assertThat(actualAmount).isEqualTo(expectedAmount);
    }


    // Cas PRO avec CA < 1000000
    @Test
    public void testComputeFacturationAmountPROBelow1M() {
        Customer customer = new Customer("customer_ref_2", CustomerType.PRO, 123456);
        EnergyType energyType = EnergyType.GAZ;
        double consumption = 1700;

        double expectedAmount = FacturationService.PRO_GAZ_PRICE_BELOW_1M * consumption;
        double actualAmount = facturationService.computeFacturationAmount(customer, energyType, consumption);

        Assertions.assertThat(actualAmount).isEqualTo(expectedAmount);
    }

    // Cas PRO avec CA > 1000000
    @Test
    public void testComputeFacturationAmountPROAbove1M() {
        Customer customer = new Customer("customer_ref_3", CustomerType.PRO, 1234567);
        EnergyType energyType = EnergyType.ELECTRICITY;
        double consumption = 1800;

        double expectedAmount = FacturationService.PRO_ELECTRICITY_ABOVE_1M * consumption;
        double actualAmount = facturationService.computeFacturationAmount(customer, energyType, consumption);

        Assertions.assertThat(actualAmount).isEqualTo(expectedAmount);
    }
}
