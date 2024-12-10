

import nl.saxion.app.SaxionApp;

public class Application implements Runnable {

    public static void main(String[] args) {
        SaxionApp.start(new Application());
    }

    @Override
    public void run() {
        Battleship.initializeBoard(); // Zet een schip op het bord

        //Jarno
        int boatCount = 0; //Het aantal booten op het bord

        while(boatCount != 4)
            {
                int startX = SaxionApp.getRandomValueBetween(0, 10);
                int startY = SaxionApp.getRandomValueBetween(0,10);
                int length = SaxionApp.getRandomValueBetween(1,4);
                Battleship.placeShip(startX, startY, length, true); //Horizontaal ship op random pos
                boatCount ++;
            }

        //Ian
        // Spel loop
        int turns = 0;
        boolean gameOver = false;

        //Chris
        while (!gameOver) {
            Battleship.printBoard();
            SaxionApp.printLine("Geef je schot in: ");
            String input = SaxionApp.readString();

            // Omzetten van de invoer naar bordcoördinaten
            int x = Integer.parseInt(input.substring(1)) - 1;  // Rijen (1-10)
            int y = input.charAt(0) - 'A';  // Kolommen (A-J)

            // Verwerk het schot
            if (Battleship.shoot(x, y)) {
                // Controleer of het spel is afgelopen
                turns++;
            }

            //Ruben
            // Controleerd of alle schepen zijn gezonken
            gameOver = true;
                // Als je nog schepen hebt, verander dit naar false
            for (int i = 0; i < Battleship.SIZE; i++) {
                for (int j = 0; j < Battleship.SIZE; j++) {
                    if (Battleship.board[i][j] == 'S') {
                        gameOver = false;
                        break;
                    }
                }
            }
        }

        SaxionApp.printLine("Gefeliciteerd, je hebt gewonnen in " + turns + " beurten!");
    }
}

//Chris, Ian
class Battleship {

    // Grootte van het bord
    public static final int SIZE = 10;
    public static char[][] board = new char[SIZE][SIZE];
    public static boolean[][] hit = new boolean[SIZE][SIZE];

    // Zet het bord op het begin
    public static void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = '~'; // Water
            }
        }
    }

    //Ian Chris
    // Print het bord
    public static void printBoard() {
        SaxionApp.printLine("  A B C D E F G H I J");
        for (int i = 0; i < SIZE; i++) {
            SaxionApp.print(i + " ");
            for (int j = 0; j < SIZE; j++) {
                if (hit[i][j]) {
                    SaxionApp.print(board[i][j] + " ");
                } else {
                    SaxionApp.print("~ ");
                }
            }
            SaxionApp.printLine();
        }
    }

    //Jarno, Ruben
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

    //Ian, Ruben
    // Verwerk een schot
    public static boolean shoot(int x, int y) {
        if (board[x][y] == 'S') {
            board[x][y] = 'X';  // Schip geraakt
            hit[x][y] = true;
            SaxionApp.printLine("Raak!");
            return true;
        } else {
            hit[x][y] = true;
            board[x][y] = 'O';
            SaxionApp.printLine("Misser!");
            return false;
        }
    }
}


