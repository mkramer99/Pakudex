import java.util.Scanner;

public class PakuriProgram {

    static int input;
    static Scanner sc;

    public static void main(String args[]) {
        sc = new Scanner(System.in);
        int maxCapacity = 0;

        // display welcome message
        System.out.println("Welcome to Pakudex: Tracker Extraordinaire!");
        System.out.println("Enter max capacity of the Pakudex: ");
        // until valid int input for capacity
        do {
            if (sc.hasNextInt()) {
                maxCapacity = sc.nextInt();
            }
            if (maxCapacity > 0) {
                break;
            }
            if (maxCapacity < 0) {
                maxCapacity = 0;
                System.out.println("Please enter a valid size.");
                System.out.println("Enter max capacity of the Pakudex: ");
            }
            else {
                maxCapacity = 0;
                System.out.println("Please enter a valid size.");
                System.out.println("Enter max capacity of the Pakudex: ");
                sc.next();
            }
        } while (maxCapacity == 0);

        // new Pakudex object
        Pakudex pak = new Pakudex(maxCapacity);
        System.out.println("The Pakudex can hold " + maxCapacity + " species of Pakuri.");
        // display the menu and get input
        menu();
        input = selection();
        // Main Menu while loop for input
        // exit if input is 6
        while (input != 6) {
            // list all Pakuri
            if (input == 1) {
                // null check
                if (pak.getSpeciesArray() != null) {
                    // print species array
                    String[] printPak = pak.getSpeciesArray();
                    System.out.println("Pakuri In Pakudex:");
                    for (int i = 0; i < printPak.length; i++) {
                        System.out.println((i + 1) + ". " + printPak[i]);
                    }
                } else {
                    System.out.println("No Pakuri in Pakudex yet!");
                }
                menu();
                input = selection();
            }
            // display a Pakuri
            if (input == 2) {
                // prompt for species then display information
                System.out.print("Enter the name of the species to display: ");
                String species = sc.next();
                int[] stats = pak.getStats(species);
                if (stats != null) {
                    System.out.println("\nSpecies: " + species);
                    System.out.println("Attack: " + stats[0]);
                    System.out.println("Defense: " + stats[1]);
                    System.out.println("Speed: " + stats[2] + "\n");
                } else {
                    System.out.println("Error: No such Pakuri!\n");
                }
                menu();
                input = selection();
            }
            // add a Pakuri
            if (input == 3) {
                // prompt for species name to add
                // display confirmation of success or failure
                if (pak.getSize() == pak.getCapacity()) {
                    System.out.print("Error: Pakudex is full!\n");
                } else {
                    System.out.println("Enter the name of the species to add: ");
                    String species = sc.next();
                    Boolean b = pak.addPakuri(species);
                    if (b) {
                        System.out.println("Pakuri species " + species + " successfully added!");
                    }
                }
                menu();
                input = selection();
            }
            // evolve a Pakuri
            if (input == 4) {
                // evolve pakuri
                System.out.println("Enter the name of the species to evolve: ");
                String species = sc.next();
                // if returns true, evolved
                if (pak.evolveSpecies(species)) {
                    System.out.println(species + " has evolved!");
                } else {
                    System.out.println("Error: No such Pakuri!");
                }
                menu();
               input = selection();
            }
            // sort the Pakuri
            if (input == 5) {
                pak.sortPakuri();
                System.out.println("Pakuri have been sorted!");
                menu();
                input = selection();
            }
        }
        System.out.print("Thanks for using Pakudex! Bye!");
    }

    // method displays menu options
    public static void menu() {
        System.out.println("\nPakudex Main Menu");
        System.out.println("-----------------");
        System.out.println("1. List Pakuri");
        System.out.println("2. Show Pakuri");
        System.out.println("3. Add Pakuri");
        System.out.println("4. Evolve Pakuri");
        System.out.println("5. Sort Pakuri");
        System.out.println("6. Exit\n");
        System.out.print("What would you like to do?");
    }
    // method checks for valid input
    public static int selection() {
        if (sc.hasNextInt()) {
            input = sc.nextInt();
            if (input < 7 && input > 0) {
                input = input;
            }
            // recall selection()
            else {
                System.out.println("Unrecognized menu selection!");
                menu();
                selection();
            }
        }
        // recall selection()
        else {
            System.out.println("Unrecognized menu selection!");
            sc.next();
            menu();
            selection();
        }
        return input;
    }
}

