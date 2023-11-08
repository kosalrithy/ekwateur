package com.ekwateur.test.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
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

    @ParameterizedTest
    @CsvFileSource(resources = "/facturationServiceTestData.csv",numLinesToSkip = 1)
    public void testComputeFacturationAmount(String customerRef, String customerType, int revenue, String energyType, double consumption, double priceKWh) {
        Customer customer = new Customer(customerRef, CustomerType.valueOf(customerType), revenue);

        double expectedAmount = priceKWh * consumption;
        double actualAmount = facturationService.computeFacturationAmount(customer, EnergyType.valueOf(energyType), consumption);

        Assertions.assertThat(actualAmount).isEqualTo(expectedAmount);
    }
}
