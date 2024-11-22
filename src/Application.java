import nl.saxion.app.SaxionApp;

import java.util.Scanner;

public class Application implements Runnable {

    public static void main(String[] args) {
        SaxionApp.start(new Application());
    }

    @Override
    public void run() {

    }


    public class Battleship {

        // Grootte van het bord
        public static final int SIZE = 10;
        private static char[][] board = new char[SIZE][SIZE];
        private static boolean[][] hit = new boolean[SIZE][SIZE];

        // Zet het bord op het begin
        public static void initializeBoard() {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    board[i][j] = '~'; // Water
                }
            }
        }

        // Print het bord
        public static void printBoard() {
            System.out.println("  A B C D E F G H I J");
            for (int i = 0; i < SIZE; i++) {
                System.out.print(i + " ");
                for (int j = 0; j < SIZE; j++) {
                    if (hit[i][j]) {
                        System.out.print(board[i][j] + " ");
                    } else {
                        System.out.print("~ ");
                    }
                }
                System.out.println();
            }
        }

        // Zet een schip op het bord
        public static void placeShip(int startX, int startY, int length, boolean horizontal) {
            for (int i = 0; i < length; i++) {
                if (horizontal) {
                    board[startX][startY + i] = 'S';
                } else {
                    board[startX + i][startY] = 'S';
                }
            }
        }

        // Verwerk een schot
        public static boolean shoot(int x, int y) {
            if (board[x][y] == 'S') {
                board[x][y] = 'X';  // Schip geraakt
                hit[x][y] = true;
                System.out.println("Raak!");
                return true;
            } else {
                hit[x][y] = true;
                System.out.println("Misser!");
                return false;
            }
        }

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            // Initialiseer het bord
            initializeBoard();

            // Zet een schip op het bord (bijvoorbeeld een schip van lengte 3)
            placeShip(2, 2, 3, true); // Horizontaal schip van lengte 3 op positie (2,2)

            // Spel loop
            int turns = 0;
            boolean gameOver = false;

            while (!gameOver) {
                printBoard();
                System.out.println("Geef je schot in (bijvoorbeeld A5): ");
                String input = scanner.nextLine();

                // Omzetten van de invoer naar bordcoördinaten
                int x = Integer.parseInt(input.substring(1)) - 1;  // Rijen (1-10)
                int y = input.charAt(0) - 'A';  // Kolommen (A-J)

                // Verwerk het schot
                if (shoot(x, y)) {
                    // Controleer of het spel is afgelopen (bijvoorbeeld als het schip van lengte 3 is gezonken)
                    // Dit is een vereenvoudigde versie, in een compleet spel moet je alle schepen controleren.
                    turns++;
                }

                // Controleer of alle schepen zijn gezonken
                gameOver = true; // Als je nog schepen hebt, verander dit naar false
                for (int i = 0; i < SIZE; i++) {
                    for (int j = 0; j < SIZE; j++) {
                        if (board[i][j] == 'S') {
                            gameOver = false;
                            break;
                        }
                    }
                }
            }

            System.out.println("Gefeliciteerd, je hebt gewonnen in " + turns + " beurten!");
            scanner.close();
        }
    }
}