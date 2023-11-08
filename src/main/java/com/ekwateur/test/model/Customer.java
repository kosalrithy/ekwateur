package com.ekwateur.test.model;

import lombok.Data;
@Data
public class Customer {

    public Customer(String customerReference, CustomerType customerType) {
        this.customerReference = customerReference;
        this.customerType = customerType;
    }

    // Type de client
    private String customerReference;

    // Type de client
    private CustomerType customerType;

    // Numéro de siret
    private String registrationNumber;

    // Raison sociale
    private String companyName;

    // Chiffre d'affaire
    private double revenue;

    // Civilité
    private String title;

    // Nom
    private String lastname;

    // Prénom
    private String firstname;

    // Ajoutez les autres propriétés spécifiques aux clients ici
}
