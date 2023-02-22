package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class App {

    private final String[][] seats;
    int rows;
    int cols;
    int totalSeats;
    private static int purchasedTickets = 0;
    private static int currentIncome = 0;


    public Scanner scan = new Scanner(System.in);

    public App(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        seats = createEmptySeats(rows, cols);
        totalSeats = rows * cols;
    }

    public static String[][] createEmptySeats(int rows, int cols) {
        String[][] seats = new String[rows][];

        for (int i = 0; i < rows; i++) {
            String[] row = new String[cols];
            Arrays.fill(row, "S");
            seats[i] = row;
        }
        return seats;
    }

    public void run() {
        boolean loopCondition = true;
        while (loopCondition) {
            menu();
            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    showSeats();
                    break;
                case 2:
                    buyTicket();
                    break;
                case 3:
                    statistics();
                    break;
                case 0:
                    loopCondition = false;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }
        }
    }

    private void menu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    private void statistics() {
        System.out.printf("Number of purchased tickets: %d\n", purchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", (double) purchasedTickets * 100 / totalSeats);
        System.out.printf("Current income: $%d\n", currentIncome);
        System.out.printf("Total income: $%d\n", totalIncome());
    }

    private void showSeats() {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= cols; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 1; i <= rows; i++) {
            System.out.print(i + " ");
            for (int j = 1; j <= cols; j++) {
                if (seats[i - 1][j - 1].equals("B")) {
                    System.out.print("B" + " ");
                    continue;
                }
                System.out.print("S" + " ");
            }
            System.out.println();
        }
    }

    public void buyTicket() {

        try {
            System.out.println("Enter a row number: ");
            int row = scan.nextInt();
            System.out.println("Enter a seat number in that row: ");
            int seat = scan.nextInt();
            int ticketPrice;

            if (seats[row - 1][seat - 1].equals("B")) {
                System.out.println("That ticket has already been purchased!");
                System.out.println("You need to pick different seat number!");
                buyTicket();
            }

            purchasedTickets++;
            seats[row - 1][seat - 1] = "B";

            if (totalSeats > 60) {
                if (row <= (rows / 2)) {
                    ticketPrice = 10;
                    System.out.println("Ticket price: $" + ticketPrice);
                } else {
                    ticketPrice = 8;
                    System.out.println("Ticket price: $" + ticketPrice);
                }
            } else {
                ticketPrice = 10;
                System.out.println("Ticket price: $" + ticketPrice);
            }
            currentIncome += ticketPrice;

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong input!");
            System.out.println("You need to pick different seat number!");
            buyTicket();
        }
    }

    public int totalIncome() {
        int total = 0;
        int front;
        int back;
        boolean isEven = true;
        if ((cols * rows) <= 60) {
            total = 10 * rows * cols;
        } else if (cols * rows > 60) {
            if (rows % 2 != 0) {
                isEven = false;
            }

            front = rows / 2;
            total = 10 * front * cols;
            back = isEven ? front : front + 1;
            total += 8 * back * cols;
        }
        return total;
    }
}