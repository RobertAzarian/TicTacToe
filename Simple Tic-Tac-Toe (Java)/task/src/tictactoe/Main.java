package tictactoe;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int numberOfMoves = 0;
        char playerChar;
        String gameStatus;
        String grid = "_________";

        char[] v = grid.toCharArray();
        char[][] valuesGrid = {
                {v[0], v[1], v[2]},
                {v[3], v[4], v[5]},
                {v[6], v[7], v[8]}};
        System.out.println(getGrid(valuesGrid));
        do {
            numberOfMoves++;
            playerChar = numberOfMoves % 2 == 0 ? 'O' : 'X';
            makeMove(playerChar, valuesGrid);
            System.out.println(getGrid(valuesGrid));
            gameStatus = getStatus(valuesGrid);
        } while ("".equals(gameStatus));
        System.out.println(gameStatus);
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


    public static String getStatus(char[][] valuesGrid) {
        int countX = 0;
        int countO = 0;
        int countSpace = 0;
        int xWins = 0;
        int oWins = 0;

        int countInLineX = 0;
        int countInLineO = 0;

        // horizontal check
        for (int i = 0; i < valuesGrid.length; i++) {
            for (int j = 0; j < valuesGrid[i].length; j++) {
                switch (valuesGrid[i][j]) {
                    case 'X' -> {
                        countX++;
                        countInLineX++;
                    }
                    case 'O' -> {
                        countO++;
                        countInLineO++;
                    }
                    default -> {
                        countSpace++;
                    }
                }
            }
            if (countInLineX == 3) {
                xWins++;
            } else if (countInLineO == 3) {
                oWins++;
            }
            countInLineX = 0;
            countInLineO = 0;
        }

        // vertical check
        for (int j = 0; j < valuesGrid[0].length; j++) {
            for (int i = 0; i < valuesGrid.length; i++) {
                switch (valuesGrid[i][j]) {
                    case 'X' -> countInLineX++;
                    case 'O' -> countInLineO++;
                }
                if (countInLineX == 3) {
                    xWins++;
                } else if (countInLineO == 3) {
                    oWins++;
                }
            }
            countInLineX = 0;
            countInLineO = 0;
        }

        // diagonals check (toRight)
        for (int i = 0; i < valuesGrid.length; i++) {
            switch (valuesGrid[i][i]) {
                case 'X' -> countInLineX++;
                case 'O' -> countInLineO++;
            }
        }
        if (countInLineX == 3) {
            xWins++;
        } else if (countInLineO == 3) {
            oWins++;
        }
        countInLineX = 0;
        countInLineO = 0;

        // diagonals check (toLeft)
        for (int i = 2, j = 0; j < valuesGrid.length; i--, j++) {
            switch (valuesGrid[i][j]) {
                case 'X' -> countInLineX++;
                case 'O' -> countInLineO++;
            }
        }
        if (countInLineX == 3) {
            xWins++;
        } else if (countInLineO == 3) {
            oWins++;
        }

        // return status
        if (((countX - countO) < -1 || (countX - countO) > 1) || (xWins > 0 && oWins > 0)) {
            return "Impossible";
        } else if (countSpace == 0 && (xWins == 0 && oWins == 0)) {
            return "Draw";
        } else if (xWins == 1) {
            return "X wins";
        } else if (oWins == 1) {
            return "O wins";
        } else {
            return "";
        }
    }
}