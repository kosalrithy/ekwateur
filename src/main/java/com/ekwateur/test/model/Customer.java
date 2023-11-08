package com.ekwateur.test.model;
public record Customer(String customerReference, CustomerType customerType,
                       String registrationNumber, String companyName,
                       double revenue, String title, String lastname,
                       String firstname) {
    public Customer(String customerReference, CustomerType customerType) {
        this(customerReference, customerType, null, null, 0.0, null, null, null);
    }
    public Customer(String customerReference, CustomerType customerType, double revenue) {
        this(customerReference, customerType, null, null, revenue, null, null, null);
    }
}
