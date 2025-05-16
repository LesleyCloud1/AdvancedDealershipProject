package com.pluralsight;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static Dealership dealership;
    //private constructor so this class can't be instantiated
    public UserInterface(){
    }

    //creates user input scanner
    static Scanner userInput = new Scanner(System.in);

    public static void display(){

        //calls method that loads the dealership
        init();

        //loop for home menu
        boolean menu = true;
        while (menu){

            //menu list
            System.out.print("""
                    ---------------------------------------------------
                    1 - Find vehicles within a price range
                    2 - Find vehicles by make / model
                    3 - Find vehicles by year range
                    4 - Find vehicles by color
                    5 - Find vehicles by mileage range
                    6 - Find vehicles by type (sedan, SUV, truck)
                    7 - List ALL vehicles
                    8 - Add a vehicle
                    9 - Remove a vehicle
                    10 - View Featured Vehicles (Over $100,000)
                    0 - Exit
                    ---------------------------------------------------
                    """);

            //asks user to choose and stores input for switch statement
            System.out.print("Enter how you would like to view vehicles: ");
            int menuChoice = userInput.nextInt();
            userInput.nextLine();

            switch (menuChoice) {
                // finds by price range
                case 1:
                    processGetByPriceRequest();
                    break;
                // finds my make and model
                case 2:
                    processGetByMakeModelRequest();
                    break;
                // finds by year
                case 3:
                    processGetByYearRequest();
                    break;
                // finds by color
                case 4:
                    processGetByColorRequest();
                    break;
                // find by mileage
                case 5:
                    processGetByMileageRequest();
                    break;
                // find by type
                case 6:
                    processGetByVehicleRequest();
                    break;
                // lists all
                case 7:
                    processGetByAllVehiclesRequest();
                    break;
                // add vehicle
                case 8:
                    processAddVehicleRequest();
                    break;
                // remove vehicle
                case 9:
                    processRemoveVehicleRequest();
                    break;
                // feautured
                case 10:
                    processFeaturedVehiclesRequest();
                    break;

                // quits app
                case 0:
                    System.out.println("\nNow stopping application, thank you.");
                    menu = false;
                    break;
                // when user doesn't choose a valid option
                default:
                    System.out.println("\nNot a valid choice - Please enter 1-9 or 99 to quit");
                    break;
            }
        }
    }

    // ------------------------------------------------------------------------

    //finds vehicle by price range
    public static void processGetByPriceRequest(){

        // default values
        double min = 0;
        double max = 0;
        boolean validated = false;

        //prompts user question
        System.out.println("\nEnter the vehicle price");

        //loop to keep asking until input is valid
        while (!validated) {

            //won't store user input unless it's a double
            System.out.print("Min: ");
            if (userInput.hasNextDouble()) {
                min = userInput.nextDouble();
            } else {
                System.out.println("\nPlease enter a numeric number!");
                userInput.nextLine();
                continue;
            }

            //won't store user input unless it's a double
            System.out.print("Max: ");
            if (userInput.hasNextDouble()) {
                max = userInput.nextDouble();
            } else {
                System.out.println("\nPlease enter a numeric number!");
                userInput.nextLine();
                continue;
            }

            //checks if min is not greater than max
            if (min > max) {
                System.out.println("Minimum price must not be greater than maximum price!");
            } else {
                // breaks loop when all input is valid
                validated = true;
            }

        }

        //displays user's price range
        System.out.printf("Here are vehicles listed from $%.2f to $%.2f\n", min, max);

        //calls filter method to filter through using user input and puts into new list
        ArrayList<Vehicle> priceRangeList = dealership.getVehiclesByPrice(min, max);
        //displays vehicles by user's price range by looping through new list
        displayVehicles(priceRangeList);
    }

    //finds vehicle by make and model
    public static void processGetByMakeModelRequest(){

        //default values
        String make = "";
        String model = "";

        //prompts user a question
        System.out.println("\nEnter the vehicle make and model");

        while (true) {

            //stores user's make input if not empty
            System.out.print("\nMake: ");
            make = userInput.nextLine().trim();

            if (make.isEmpty()) {
                System.out.println("\nMake is empty, please enter a make!");
                continue;
            }

            //stores user's model input if not empty
            System.out.print("Model: ");
            model = userInput.nextLine().trim();
            if (model.isEmpty()) {
                System.out.println("\nModel is empty, please enter a model!");
                continue;
            }

            //checks if the make and model are not the same
            if (make.equalsIgnoreCase(model)) {
                System.out.println("\nMake and model cannot be the same!");
            } else {
                // takes user input and makes first letter at index 0 capital and the rest lowercase
                make = make.substring(0, 1).toUpperCase() + make.substring(1).toLowerCase();
                model = model.substring(0, 1).toUpperCase() + model.substring(1).toLowerCase();
                break;
            }

        }

        //displays user's make and model
        System.out.printf("Here are vehicles listed as %s %s\n", make, model);

        //calls filter method to filter through using user input and puts into new list
        ArrayList<Vehicle> makeModelList = dealership.getVehiclesByMakeModel(make, model);
        //displays vehicles by user's make and model by looping through new list
        displayVehicles(makeModelList);
    }

    //finds vehicle by year
    public static void processGetByYearRequest(){

        int min = 0;
        int max = 0;
        boolean validated = false;

        System.out.println("\nEnter the vehicle years");

        while (!validated) {

            System.out.print("Min: ");
            if (userInput.hasNextInt()) {
                min = userInput.nextInt();
            } else {
                System.out.println("\nPlease enter a numeric number!");
                userInput.nextLine();
                continue;
            }

            System.out.print("Max: ");
            if (userInput.hasNextInt()) {
                max = userInput.nextInt();
            } else {
                System.out.println("\nPlease enter a numeric number!");
                userInput.nextLine();
                continue;
            }

            if (min > max) {
                System.out.println("Minimum year must not be greater than maximum year!");
            } else {
                validated = true;
            }

        }

        //displays user's price range
        System.out.printf("Here are vehicles listed from %d to %d\n", min, max);

        //calls filter method to filter through using user input and puts into new list
        ArrayList<Vehicle> yearList = dealership.getVehiclesByYear(min, max);
        //displays vehicles by user's year range by looping through new list
        displayVehicles(yearList);

    }

    //finds vehicle by color
    public static void processGetByColorRequest(){

        //default values
        String color = "";

        //prompts user a question
        System.out.println("\nEnter the vehicle make and model");

        while (true) {

            //stores user's color input if not empty
            System.out.print("\nColor: ");
            color = userInput.nextLine().trim();
            if (color.isEmpty()) {
                System.out.println("\nColor is empty, please enter a color!");
            } else {
                //takes user input and capitalizes the first letter
                color = color.substring(0, 1).toUpperCase() + color.substring(1).toLowerCase();
                break;
            }
        }

        //displays user's color
        System.out.printf("Here are vehicles with the color %s\n", color);

        //calls filter method to filter through using user input and puts into new list
        ArrayList<Vehicle> colorList = dealership.getVehiclesByColor(color);
        //displays vehicles by user's color by looping through new list
        displayVehicles(colorList);
    }

    //finds vehicle by mileage
    public static void processGetByMileageRequest(){

        int min = 0;
        int max = 0;
        boolean validated = false;

        System.out.println("\nEnter the vehicle mileage range");

        while (!validated) {

            System.out.print("Min: ");
            if (userInput.hasNextInt()) {
                min = userInput.nextInt();
            } else {
                System.out.println("\nPlease enter a numeric number!");
                userInput.nextLine();
                continue;
            }

            System.out.print("Max: ");
            if (userInput.hasNextInt()) {
                max = userInput.nextInt();
            } else {
                System.out.println("\nPlease enter a numeric number!");
                userInput.nextLine();
                continue;
            }

            if (min > max) {
                System.out.println("Minimum mileage must not be greater than maximum mileage!");
            } else {
                validated = true;
            }

        }

        // displays user's price range
        System.out.printf("Here are vehicles listed from %d to %d miles\n", min, max);

        // calls filter method to filter through using user input and puts into new list
        ArrayList<Vehicle> mileageList = dealership.getVehiclesByMileage(min, max);
        // displays vehicles by user's year range by looping through new list
        displayVehicles(mileageList);
    }

    // finds vehicle by vehicle type
    public static void processGetByVehicleRequest(){

        // default values
        String type = "";

        // prompts user a question
        System.out.println("\nEnter the vehicle body style");

        while (true) {

            // stores user's type input if not empty
            System.out.print("\nType: ");
            type = userInput.nextLine().trim();
            if (type.isEmpty()) {
                System.out.println("\nType is empty, please enter a type!");
            } else {
                if (type.equalsIgnoreCase("SUV")){
                    type = type.toUpperCase();
                }
                break;
            }
        }

        // displays user's vehicle type
        System.out.printf("Here are vehicles with the body type %s\n", type);

        // calls filter method to filter through using user input and puts into new list
        ArrayList<Vehicle> typeList = dealership.getVehiclesByType(type);
        // displays vehicles by user's type by looping through new list
        displayVehicles(typeList);
    }
    //feautured vehicles
    public static void processFeaturedVehiclesRequest() {
        double min = 100000;
        double max = Double.MAX_VALUE;

        System.out.println("\nShowing featured vehicles over $100,000:\n");

        List<Vehicle> featuredList = dealership.getVehiclesByPrice(min, max);
        displayVehicles((ArrayList<Vehicle>) featuredList);
    }
    // finds all vehicles
    public static void processGetByAllVehiclesRequest(){

        // calls method of grabbing all vehicles from inventory and stores it into new list
        ArrayList<Vehicle> allVehiclesList = dealership.getAllVehicles();
        // displays all vehicles by looping through new list
        displayVehicles(allVehiclesList);
    }

    // adds a vehicle
    public static void processAddVehicleRequest(){

        // prompts user for details of new vehicle to add
        System.out.println("Enter new vehicle details to add to inventory");

        System.out.print("VIN: ");
        int vin = userInput.nextInt();
        userInput.nextLine();

        System.out.print("Year: ");
        int year = userInput.nextInt();
        userInput.nextLine();

        System.out.print("Make: ");
        String make = userInput.nextLine();

        System.out.print("Model: ");
        String model = userInput.nextLine();

        System.out.print("Type: ");
        String type = userInput.nextLine();

        System.out.print("Color: ");
        String color = userInput.nextLine();

        System.out.print("Odometer: ");
        int odometer = userInput.nextInt();
        userInput.nextLine();

        System.out.print("Price: ");
        double price = userInput.nextDouble();
        userInput.nextLine();

        // creates the new vehicle to from details from user input
        Vehicle addVehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);

        // adds the vehicle to the dealership inventory
        dealership.addVehicle(addVehicle);

        // displays the vehicle user added
        System.out.printf("New vehicle: %d|%d|%s|%s|%s|%s|%d|$%.2f added!\n",
                vin, year, make, model, type, color, odometer, price);

        // creates instance of the dealership file manager and adds vehicle to file
        DealershipFileManager fileManager = new DealershipFileManager();
        fileManager.saveDealership(dealership);
    }

    // removes a vehicle
    public static void processRemoveVehicleRequest(){

        // prompts user for vehicle to remove
        System.out.println("Enter vehicle details to remove from inventory");

        System.out.print("VIN: ");
        int vin = userInput.nextInt();
        userInput.nextLine();

        Vehicle removedVehicle = null;
        // finds the vehicle and vehicles vin in list
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            // if a vehicle vin matches the user inputs vin
            if (vehicle.getVin() == vin) {
                // gets the matching vehicle and stores it
                removedVehicle = vehicle;
            }
        }

        // removes stored vehicle from dealership's inventory list using remove method
        dealership.removeVehicle(removedVehicle);

        // displays the vehicle was removed
        System.out.printf("Vehicle with VIN #%d was removed!\n", vin);

        // saves the dealership after a vehicle is removed
        // creates instance of the dealership file manager and removes vehicle to file
        DealershipFileManager fileManager = new DealershipFileManager();
        fileManager.saveDealership(dealership);
    }

    // ------------------------------------------------------------------------

    // other methods
    private static void init(){

        // creates instance of the dealership file manager
        DealershipFileManager fileManager = new DealershipFileManager();
        dealership = fileManager.getDealership();

    }

    // utility to use in other methods for displaying the vehicles list
    public static void displayVehicles(ArrayList<Vehicle> vehicleList){

        // creates list and assigns to a variable to display the list
        for (Vehicle filteredVehicle : vehicleList) {
            System.out.println(filteredVehicle);
        }
    }
}