package com.pluralsight;

public abstract class Contract {
    private String date;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicle;

    //constructor
    public Contract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicle = vehicle;
    }
    //Abstract methods for subclasses to implement
    public abstract double getTotalPrice();
    public abstract double getMonthlyPayment();

    //Getters
    public String getDate() {
        return date;
    }
    public String getCustomerName() {
        return customerName;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
}
