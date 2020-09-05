import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean gameLoop = true;
        boolean xWins = false;
        char currentTurn = 'X';

        char[][] cellArr = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cellArr[i][j] = '_';
            }
        }
        drawField(cellArr);

        while (gameLoop) {
            int[] coordinates = getCoordinates(scanner);
            while (!isValidCord(coordinates, cellArr)) {
                coordinates = getCoordinates(scanner);
            }
            coordinates = convertCoordinates(coordinates);
            cellArr[coordinates[1]][coordinates[0]] = currentTurn;
            if (currentTurn == 'X') {
                currentTurn = 'O';
            } else {
                currentTurn = 'X';
            }
            drawField(cellArr);

            if (checkRows(cellArr, 'X')) {
                xWins = true;
                gameLoop = false;
            } else if (checkRows(cellArr, 'O')) {
                gameLoop = false;
            } else if (checkColumns(cellArr, 'X')) {
                xWins = true;
                gameLoop = false;
            } else if (checkColumns(cellArr, 'O')) {
                gameLoop = false;
            } else if (checkDiagonals(cellArr, 'X')) {
                xWins = true;
                gameLoop = false;
            } else if (checkDiagonals(cellArr, 'O')) {
                gameLoop = false;
            }
        }

        if (xWins) {
            System.out.println("X wins");
        } else {
            System.out.println("O wins");
        }

    }

    public static boolean checkRows(char[][] arr, char winner) {
        for (int i = 0; i < 3; i++) {
            if (arr[i][1] == arr[i][0] && arr[i][1] == arr[i][2] && arr[i][1] == winner) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkColumns(char[][] arr, char winner) {
        for (int i = 0; i < 3; i++) {
            if (arr[1][i] == arr[0][i] && arr[1][i] == arr[2][i] && arr[1][i] == winner) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkDiagonals(char[][] arr, char winner) {
        if (arr[0][0] == arr[1][1] && arr[0][0] == arr[2][2] && arr[0][0] == winner) {
            return true;
        } else {
            return arr[0][2] == arr[1][1] && arr[0][2] == arr[2][0] && arr[0][2] == winner;
        }
    }

    public static void drawField(char[][] arr) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                if (arr[i][j] == '_') {
                    System.out.print("  ");
                } else {
                    System.out.print(arr[i][j] + " ");
                }
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static int[] getCoordinates(Scanner scanner) {
        int[] coordinates = new int[2];
        System.out.print("Enter the coordinates: ");

        for (int i = 0; i < coordinates.length; i++) {
            try {
                coordinates[i] = Integer.parseInt(scanner.next());
            } catch (Exception e) {
                return coordinates;
            }
        }

        return coordinates;
    }

    public static boolean isValidCord(int[] coordinates, char[][] arr) {
        for (int coordinate : coordinates) {
            if (coordinate < 1) {
                System.out.println("You should enter numbers!");
                return false;
            } else if (coordinate > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                return false;
            }
        }

        coordinates = convertCoordinates(coordinates);
        if (arr[coordinates[1]][coordinates[0]] != '_') {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }

        return true;
    }

    public static int[] convertCoordinates(int[] arr) {
        int[] coordinates = new int[arr.length];
        System.arraycopy(arr, 0, coordinates, 0, arr.length);

        coordinates[0] = --coordinates[0];
        if (coordinates[1] == 3) {
            coordinates[1] = 0;
        } else if (coordinates[1] == 2) {
            coordinates[1] = 1;
        } else {
            coordinates[1] = 2;
        }

        return coordinates;
    }
}
