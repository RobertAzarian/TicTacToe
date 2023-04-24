package tictactoe;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        char playerChar = 'X';
        String grid = scanner.nextLine();

        char[] v = grid.toCharArray();
        char[][] valuesGrid = {
                {v[0], v[1], v[2]},
                {v[3], v[4], v[5]},
                {v[6], v[7], v[8]}};
        System.out.println(getGrid(valuesGrid));
        makeMove(playerChar, valuesGrid);
        System.out.println(getGrid(valuesGrid));
    }


    public static String getGrid(char[][] v) {
        return String.format("""
                ---------
                | %C %C %C |
                | %C %C %C |
                | %C %C %C |
                ---------""",
                v[0][0], v[0][1], v[0][2],
                v[1][0], v[1][1], v[1][2],
                v[2][0], v[2][1], v[2][2]);
    }


    public static void makeMove(char playerChar, char[][] valuesGrid) {
        int y;
        int x;
        do {
            try {
                String coordinateToMove = scanner.nextLine();
                String[] strCoordinate = coordinateToMove.split(" ");
                y = Integer.parseInt(strCoordinate[0]);
                x = Integer.parseInt(strCoordinate[1]);
                if ((y < 1 || y > 3) || (x < 1 || x > 3)) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                } else if (valuesGrid[y-1][x-1] == 'X' || valuesGrid[y-1][x-1] == 'O') {
                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
            }
        } while (true);
        valuesGrid[y-1][x-1] = playerChar;
    }
}