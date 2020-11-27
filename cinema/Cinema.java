package cinema;

import java.util.Scanner;

public class Cinema {


    public static Scanner sc = new Scanner(System.in);
    public static int rows = 0;
    public static int seats = 0;
    public static String[][] theatre;
    public static String freeSeat = "S";
    public static String soldSeats = "B";

    public static void main(String[] args) {
        // Write your code here
        boolean exit = false;
        initTheatre();

        while (!exit) {
            printMenu();
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    printTheatre();
                    break;
                case 2:
                    ticketPrice();
                    break;
                case 3:
                    statistics();
                    break;
                case 0:
                    exit = true;
                    break;

            }
        }
    }


    public static void printMenu() {
        System.out.printf(
                "%s\n%s\n%s\n%s\n",
                "1. Show the seats",
                "2. Buy a ticket",
                "3. Statistics",
                "0. Exit"
        );
    }


    public static void initTheatre() {
        System.out.println("Enter the number of rows:");
        rows = Integer.parseInt(sc.nextLine());
        System.out.println("Enter the number of seats in each row:");
        seats = Integer.parseInt(sc.nextLine());

        theatre = new String[rows][seats];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                theatre[i][j] = freeSeat;
            }
        }
    }


    public static void printTheatre() {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i <= seats; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < theatre.length; i++) { // return rows
            System.out.printf("%d", i + 1);
            for (int j = 0; j < theatre[i].length; j++) { //return columns
                System.out.print(" " + theatre[i][j]);
            }
            System.out.println("");

        }
    }


    public static void ticketPrice() {
        int rowNum;
        int sNum;
        boolean free;

        while (true) {

            System.out.println("Enter a row number:");
            rowNum = Integer.parseInt(sc.nextLine());
            System.out.println("Enter a seat number in that row:");
            sNum = Integer.parseInt(sc.nextLine());


            if (rowNum <= 0 || rowNum > theatre.length || sNum <= 0 || sNum > theatre[0].length) {
                System.out.println("Wrong input!");

            } else {
                if ("S".equals(theatre[rowNum - 1][sNum - 1])) {
                    theatre[rowNum - 1][sNum - 1] = soldSeats;
                    int ticketPrice = theatre.length * theatre[0].length <= 60 ? 10 : rowNum <= theatre.length / 2 ? 10 : 8;
                    System.out.printf("Ticket price: $%d\n", ticketPrice);
                    break;

                } else {
                    System.out.println("That ticket has already been purchased!");
                }
            }
        }
    }




    public static void statistics() {


        double percentage;
        int purchasedTickets = 0;
        int currentInc = 0;
        int totalInc = theatre.length * theatre[0].length <= 60 ?
                (theatre.length * theatre[0].length) * 10 : theatre.length % 2 == 0 ?
                ((theatre.length / 2) * theatre[0].length * 10) + (theatre.length / 2) * theatre[0].length * 8 :
                ((theatre.length / 2) * theatre[0].length * 10) + ((theatre.length / 2) + 1) * theatre[0].length * 8;

        for (int i = 0; i < theatre.length; i++) {
            for (int j = 0; j < theatre[i].length; j++) {
                if ("B".equals(theatre[i][j])) {
                    purchasedTickets++;

                    if (theatre.length * theatre[0].length <= 60) {
                        currentInc += 10;
                    } else {
                        currentInc += i <= theatre.length / 2 - 1 ? 10 : 8;
                    }
                }
            }
        }


        percentage = (double) purchasedTickets / (theatre.length * theatre[0].length) * 100;

        System.out.println("\nNumber of purchased tickets: " + purchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("Current income: $"  + currentInc);
        System.out.println("Total income: $" + totalInc);



    }




}


