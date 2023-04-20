package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printGrid(scanner.nextLine());
    }

    public static void printGrid(String input) {
        char[] v = input.toCharArray();     // values
        System.out.printf("""
                ---------
                | %C %C %C |
                | %C %C %C |
                | %C %C %C |
                ---------""",
                v[0], v[1], v[2],
                v[3], v[4], v[5],
                v[6], v[7], v[8]);
    }
}