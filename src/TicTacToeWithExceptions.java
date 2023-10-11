import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class TicTacToeWithExceptions {
    static String[] board;
    static String turn;

    public static void main(String[] args) {
        board = new String[9];
        turn = "X";


        for (int a = 0; a < 9; a++) { //Schleife für das Spielfeld
            board[a] = String.valueOf(a + 1);
        }

        System.out.println("Welcome to Tic - Tac - Toe!");
        System.out.println();
        printGameMode();
        selectedGameMode();
        System.out.println();

    }

    // METHODS


    static void printBoard() { //Spielfeld
        System.out.println("|---|---|---|");
        System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
        System.out.println("|---|---|---|");
    }


    static void printGameMode() { //Spielmodus
        System.out.println("[1] - Player VS Player");
        System.out.println("[2] - Player VS COM");
        System.out.println("[3] - COM VS COM");
        System.out.print("Please choose your GameMode: ");
    }


    static void selectedGameMode() { //Spielmodus auswählen
        int selectedMode;
        Scanner playerInput = new Scanner(System.in);
        selectedMode = playerInput.nextInt();
        if (selectedMode == 1) {
            System.out.println("You selected following GameMode: Player VS Player");
            playVSplay();
        } else if (selectedMode == 2) {
            System.out.println("You selected following GameMode: Player VS COM");
            playVSCOM();
        } else if (selectedMode == 3) {
            System.out.println("You selected following GameMode: COM VS COM");
        } else {
            System.out.println("Your input is not valid. Please try again!");
            System.out.print("Please choose your GameMode: ");
            selectedGameMode();
        }
    }


    static void playVSplay() { //Spielen
        printBoard();
        Scanner userInput = new Scanner(System.in);
        String winner = null;
        System.out.println("X will play first. Enter a slot number to place X in: ");
        while (winner == null) {
            int numInput;

            try {
                numInput = userInput.nextInt();
                if (!(numInput > 0 && numInput <= 9)) {
                    System.out.println("Invalid input; re-enter slot number:");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input; re-enter slot number:");
                continue;
            }

            //zwei Spieler
            if (board[numInput - 1].equals(String.valueOf(numInput))) {
                board[numInput - 1] = turn;
                if (turn.equals("X")) {
                    turn = "O";
                } else {
                    turn = "X";
                }
                printBoard();
                winner = checkWinner();
            } else {
                System.out.println("Slot already taken; re-enter slot number:");
            }
        }
        // wenn keiner gewinnt
        if (winner.equalsIgnoreCase("draw")) {
            System.out.println("It's a draw! Thanks for playing.");
        }


        else { // für den Gewinner
            System.out.println("Congratulations! " + winner + "'s have won! Thanks for playing.");
        }
        userInput.close();
    }

    // Methoden für COM VS COM
    static void playVSCOM() {
        printBoard();
        Scanner userInput = new Scanner(System.in);
        String winner = null;
        System.out.println("X will play first. Enter a slot number to place X in: ");
        while (winner == null) {
            int numInput;


            try {
                numInput = userInput.nextInt();
                if (!(numInput > 0 && numInput <= 9)) {
                    System.out.println("Invalid input; re-enter slot number:");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input; re-enter slot number:");
                continue;
            }

            // für den Spieler
            if (board[numInput - 1].equals(String.valueOf(numInput))) {
                board[numInput - 1] = turn;
                if (turn.equals("X")) {
                    turn = "O";
                    getCOMTip();
                } else {
                    turn = "X";
                }
                printBoard();
                winner = checkWinner();
            } else {
                System.out.println("Slot already taken; re-enter slot number:");
            }
        }
        // wenn keiner gewinnt
        if (winner.equalsIgnoreCase("draw")) {
            System.out.println("It's a draw! Thanks for playing.");
        }

        // für den Gewinner
        else {
            System.out.println("Congratulations! " + winner + "'s have won! Thanks for playing.");
        }
        userInput.close();
    }

    // Methode für den Gewinner check
    static String checkWinner() {
        for (int a = 0; a < 8; a++) {
            String line = null;
            switch (a) {
                case 0:
                    line = board[0] + board[1] + board[2];
                    break;
                case 1:
                    line = board[3] + board[4] + board[5];
                    break;
                case 2:
                    line = board[6] + board[7] + board[8];
                    break;
                case 3:
                    line = board[0] + board[3] + board[6];
                    break;
                case 4:
                    line = board[1] + board[4] + board[7];
                    break;
                case 5:
                    line = board[2] + board[5] + board[8];
                    break;
                case 6:
                    line = board[0] + board[4] + board[8];
                    break;
                case 7:
                    line = board[2] + board[4] + board[6];
                    break;
            }

            if (line.equals("XXX")) {
                return "X";
            }


            else if (line.equals("OOO")) {
                return "O";
            }
        }

        for (int a = 0; a < 9; a++) {
            if (Arrays.asList(board).contains(String.valueOf(a + 1))) {
                break;
            } else if (a == 8) {
                return "draw";
            }
        }


        System.out.println(turn + "'s turn; enter a slot number to place " + turn + " in:");
        return null;
    }

    // Methode für den COM Tip
    static void getCOMTip() {
        int maxNumber = board.length;
        Random rand = new Random();
        int randomTip = (rand.nextInt(maxNumber)) + 1;
        System.out.println(randomTip);
    }

}