package com.ekwateur.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestParam;
import com.ekwateur.test.service.FacturationService;
import com.ekwateur.test.model.Customer;
import com.ekwateur.test.model.CustomerType;
import com.ekwateur.test.model.EnergyType;

public class FacturationControllerTest {

    @Mock
    private FacturationService facturationService;

    @InjectMocks
    private FacturationController facturationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testComputeFacturationAmountSuccess() {
        // Valeurs de test
        double consumption = 1000;
        double expectedAmount = 133.0; // Remplacez par la valeur attendue

        // Config du mock
        Customer customer = new Customer("customer_ref_1", CustomerType.PARTICULAR);
        Mockito.when(facturationService.computeFacturationAmount(customer, EnergyType.ELECTRICITY, consumption))
                .thenReturn(expectedAmount);

        // Appel de la méthode du contrôleur
        // Vérification de la réponse
        ResponseEntity<Double> response = facturationController.computeFacturationAmount("customer_ref_1", "PARTICULAR", "ELECTRICITY", consumption);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isEqualTo(expectedAmount);
    }

    @Test
    public void testComputeFacturationAmountInvalidInput() {
        // Appel de la méthode du contrôleur avec des valeurs invalides
        // Vérification de la réponse
        ResponseEntity<Double> response = facturationController.computeFacturationAmount("customer_ref_2", "NONE", "ELECTRICITY", 1000);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        Assertions.assertThat(response.getBody()).isNull();
    }
}
