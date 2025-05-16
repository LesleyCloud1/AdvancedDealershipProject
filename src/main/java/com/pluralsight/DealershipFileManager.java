package com.pluralsight;

import java.io.*;
import java.util.List;
import java.util.Scanner;

//This class is responsible for reading and saving dealership info to a file
public class DealershipFileManager {
    private static final String FILE_PATH = "src/main/resources/inventory.csv";

    //This method reads the inventory file and builds a Dealership object
    public Dealership getDealership() {
        Dealership dealership = null;

        try {
            //Open the file using Scanner for simple line-based parsing
            Scanner scanner = new Scanner(new File(FILE_PATH));
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));

            //Read the first line, dealership info
            if (scanner.hasNextLine()) {
                String headerLine = scanner.nextLine();
                String[] parts = headerLine.split("\\|");
                String name = parts[0];
                String address = parts[1];
                String phone = parts[2];

                //Create a Dealership object with the data
                dealership = new Dealership(name, address, phone);
            }

            //Read the rest of the lines each line is a vehicle
            while (scanner.hasNextLine()) {
                String vehicleLine = scanner.nextLine();
                String[] parts = vehicleLine.split("\\|");

                int vin = Integer.parseInt(parts[0]);
                int year = Integer.parseInt(parts[1]);
                String make = parts[2];
                String model = parts[3];
                String type = parts[4];
                String color = parts[5];
                int odometer = Integer.parseInt(parts[6]);
                double price = Double.parseDouble(parts[7]);

                //Takes the parts of the vehicle from the file and puts them together creating a new vehicle.
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
                dealership.addVehicle(vehicle);//adds the vehicle to the dealership inv.
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error: inventory.csv file not found.");
            e.printStackTrace();
        }

        return dealership;
    }

        // This method saves the dealership and its vehicles to a file
        public void saveDealership(Dealership dealership) {
            try {
                //creates file and buffered writer and passes in the file path
                BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));

                //writes file's header row using the dealership properties
                writer.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
                //writes a new line
                writer.newLine();

                //Write each vehicle in the inventory
                List<Vehicle> inventory = dealership.getAllVehicles();
                for (Vehicle vehicle : inventory) {
                    String line = vehicle.getVin() + "|" + vehicle.getYear() + "|" + vehicle.getMake() + "|" +
                            vehicle.getModel() + "|" + vehicle.getVehicleType() + "|" + vehicle.getColor() + "|" +
                            vehicle.getOdometer() + "|" + vehicle.getPrice();
                    writer.write(line);
                    writer.newLine();
                }

                writer.close();
            } catch (IOException e) {
                System.out.println("Error writing dealership file: " + e.getMessage());
            }
        }
    }