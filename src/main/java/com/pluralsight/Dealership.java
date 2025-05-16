package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Holds dealership details and list of vehicles
public class Dealership {
    private String name;
    private String address;
    private String phone;
    private List<Vehicle> inventory;

    //Contructor to create new dealship with the info
    public Dealership(String name, String address,String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();//Creates new Inventory list
    }
    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // ------------------------------------------------------------------------

    //derived getters
    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max){

        //creates new list to hold vehicles within a price range
        ArrayList<Vehicle> priceRangeList = new ArrayList<>();

        //for each vehicle in the inventory list
        for (Vehicle vehicle : inventory) {
            // gets vehicle price and compares it to user input min and max
            if (vehicle.getPrice() >= min && vehicle.getPrice() <= max) {
                // adds vehicle to new list
                priceRangeList.add(vehicle);
            }
        }
        //if the list is empty then it displays no matches made
        if (priceRangeList.isEmpty()){
            System.out.println("\nNo matches found in your price range!");
        }
        //returns new list of vehicles that match user input
        return priceRangeList;
    }

    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model){

        ArrayList<Vehicle> makeModelList = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getMake().equals(make) && vehicle.getModel().equals(model)) {
                makeModelList.add(vehicle);
            }
        }

        if (makeModelList.isEmpty()){
            System.out.println("\nNo matches found for make and model!");
        }

        return makeModelList;
    }

    public ArrayList<Vehicle> getVehiclesByYear(int min, int max){

        ArrayList<Vehicle> yearRangeList = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getYear() >= min && vehicle.getYear() <= max) {
                yearRangeList.add(vehicle);
            }
        }

        if (yearRangeList.isEmpty()){
            System.out.println("\nNo matches found in your year range!");
        }

        return yearRangeList;
    }

    public ArrayList<Vehicle> getVehiclesByColor(String color){

        ArrayList<Vehicle> colorList = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getColor().equals(color)) {
                colorList.add(vehicle);
            }
        }

        if (colorList.isEmpty()){
            System.out.println("\nNo matches found for your color!");
        }

        return colorList;
    }

    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max){

        ArrayList<Vehicle> mileageRangeList = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getOdometer() >= min && vehicle.getOdometer() <= max) {
                mileageRangeList.add(vehicle);
            }
        }

        if (mileageRangeList.isEmpty()){
            System.out.println("\nNo matches found in your mileage range!");
        }

        return mileageRangeList;
    }

    public ArrayList<Vehicle> getVehiclesByType(String vehicleType){

        ArrayList<Vehicle> vehicleTypeList = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getVehicleType().equals(vehicleType)) {
                vehicleTypeList.add(vehicle);
            }
        }

        if (vehicleTypeList.isEmpty()){
            System.out.println("\nNo matches found for your type!");
        }

        return vehicleTypeList;
    }

    //returns the whole inventory
    public ArrayList<Vehicle> getAllVehicles(){
        return                                                                                                                                             (ArrayList<Vehicle>) inventory;
    }

    // ------------------------------------------------------------------------

    //adds a vehicle to the inventory
    public void addVehicle(Vehicle vehicle){
        inventory.add(vehicle);
    }

    //removes a vehicle from inventory
    public void removeVehicle(Vehicle vehicle){

        inventory.remove(vehicle);
    }
}