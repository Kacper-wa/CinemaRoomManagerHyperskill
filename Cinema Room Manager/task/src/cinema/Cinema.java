package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of rows: ");
        int rows = scan.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        int cols = scan.nextInt();

        App app = new App(rows, cols);
        app.run();
    }
}