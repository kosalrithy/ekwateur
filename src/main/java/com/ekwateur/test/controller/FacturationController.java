package com.ekwateur.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ekwateur.test.service.FacturationService;
import com.ekwateur.test.model.Customer;
import com.ekwateur.test.model.CustomerType;
import com.ekwateur.test.model.EnergyType;

@RestController
@RequestMapping("/v1/facturation")
public class FacturationController {

    private final FacturationService facturationService;
    @Autowired
    public FacturationController(FacturationService facturationService) {
        this.facturationService = facturationService;
    }

    @GetMapping("/computeFacturationAmount")
    public ResponseEntity<Double> computeFacturationAmount(
            @RequestParam String customerReference,
            @RequestParam String customerType,
            @RequestParam String energyType,
            @RequestParam double consumption) {

        try {
            // Appel du service pour effectuer le montant de la facturation
            Customer customer = new Customer(customerReference, CustomerType.valueOf(customerType));
            double facturationAmount = facturationService.computeFacturationAmount(customer, EnergyType.valueOf(energyType), consumption);
            return ResponseEntity.ok(facturationAmount);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}