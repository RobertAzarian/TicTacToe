package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputValues = scanner.nextLine();

        System.out.println(getGrid(inputValues));
        System.out.println(getStatus(inputValues));
    }


    public static String getGrid(String input) {
        char[] v = input.toCharArray();     // values
        return String.format("""
                ---------
                | %C %C %C |
                | %C %C %C |
                | %C %C %C |
                ---------""",
                v[0], v[1], v[2],
                v[3], v[4], v[5],
                v[6], v[7], v[8]);
    }


    public static String getStatus(String input) {
        char[] v = input.toCharArray();     // values
        char[][] valuesGrid = {
                {v[0], v[1], v[2]},
                {v[3], v[4], v[5]},
                {v[6], v[7], v[8]}};
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
        } else if (countSpace != 0 && (xWins == 0 && oWins == 0)) {
            return "Game not finished";
        } else if (countSpace == 0 && (xWins == 0 && oWins == 0)) {
            return "Draw";
        } else if (xWins == 1) {
            return "X wins";
        } else if (oWins == 1) {
            return "O wins";
        } else {
            return "error";
        }
    }
}