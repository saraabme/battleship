import java.util.Scanner;

public class workingBattleship {

    static char[][] world1; // Create global world 2D array for player 1
    static char[][] world2; // Create global world 2D array for player 2
    static char[][] history1; // Create global world 2D array for player 1
    static char[][] history2; // Create global world 2D array for player 2
    static Scanner scan;

    public static void main(String[] args) {

        scan = new Scanner(System.in);
        world1 = new char[5][5];
        world2 = new char[5][5];
        history1 = new char[5][5];
        history2 = new char[5][5];

        System.out.println("Welcome to the Battleship! \n");

        // Deploy ships
        deployPlayerShip1(); // Player 1 deploy
        printWorld1();
        for (int i = 1; i <= 100; i++) {
            System.out.println();
        }

        deployPlayerShip2(); // Player 2 deploy
        printWorld2();
        for (int i = 1; i <= 100; i++) {
            System.out.println();
        }

        playGame(); // Play the game

    }

    // Method to create the world map
    // public static char[][] createWorld1(){
    // world = new char[5][5]; // Create a 5 by 5 grid
    // return world;
    // }

    // Print Map1
    public static void printWorld1() {
        System.out.println("  0 1 2 3 4  ");
        for (int i = 0; i < world1.length; i++) {
            System.out.print(i);
            for (int j = 0; j < world1[i].length; j++) {
                // \u0000 is the default entry in a char array if it is not populated
                if (world1[i][j] == '\u0000' || world1[i][j] == '2') {
                    System.out.print(" -");
                } else if (world1[i][j] == '1') {
                    System.out.print(" @");
                } else {
                    System.out.print(" " + world1[i][j]);
                }
            }
            System.out.print("\n");

        }
    }

    // Print Map2
    public static void printWorld2() {
        System.out.println("  0 1 2 3 4  ");
        for (int i = 0; i < world2.length; i++) {
            System.out.print(i);
            for (int j = 0; j < world2[i].length; j++) {
                // \u0000 is the default entry in a char array if it is not populated
                if (world2[i][j] == '\u0000' || world2[i][j] == '2') {
                    System.out.print(" -");
                } else if (world2[i][j] == '1') {
                    System.out.print(" @");
                } else {
                    System.out.print(" " + world2[i][j]);
                }
            }
            System.out.print("\n");
        }
    }

    // Print history map of player 1
    public static void printHistory1() {
        // history1 = world2;
        System.out.println("  0 1 2 3 4  ");
        for (int i = 0; i < history1.length; i++) {
            System.out.print(i);
            for (int j = 0; j < history1[i].length; j++) {
                // \u0000 is the default entry in a char array if it is not populated
                if (history1[i][j] == '\u0000' || history1[i][j] == '2' || history1[i][j] == '1') {
                    System.out.print(" -");
                }
                // else if (history1[i][j] == '\u0000')

                else {
                    System.out.print(" " + history1[i][j]);
                }
            }
            System.out.print("\n");

        }
    }

    // Print history map of player 2
    public static void printHistory2() {
        // history2 = world1;
        System.out.println("  0 1 2 3 4  ");
        for (int i = 0; i < history1.length; i++) {
            System.out.print(i);
            for (int j = 0; j < history2[i].length; j++) {
                // \u0000 is the default entry in a char array if it is not populated
                if (history2[i][j] == '\u0000' || history2[i][j] == '2' || history2[i][j] == '1') {
                    System.out.print(" -");
                }
                // else if (history2[i][j] == '\u0000')

                else {
                    System.out.print(" " + history2[i][j]);
                }
            }
            System.out.print("\n");

        }
    }

    // Method to deploy player 1's ships
    public static char[][] deployPlayerShip1() {
        int count = 0;
        int x, y;
        System.out.print("PLAYER 1, ENTER YOUR SHIPS' COORDINATES: 0-4 0-4. \n");
        // The user will deploy 5 ships so keep prompting the user until 5 ships are
        // deployed
        while (count < 5) {
            int shipno = count + 1;

            System.out.print("Enter ship " + shipno + " location: ");
            String[] Ship = new String[2];
            Ship = scan.nextLine().split(" ");

            x = Integer.parseInt(Ship[0]);
            y = Integer.parseInt(Ship[1]);

            if (checkPosition1(x, y) == 0) {
                world1[x][y] = '1';
                history2[x][y] = '1';
                count++;
            } else if (checkPosition1(x, y) == 1) {
                System.out.println("Invalid coordinates. Choose different coordinates.");
            } else if (checkPosition1(x, y) == 2) {
                System.out.println("You already have a ship there. Choose different coordinates.");
            }
            // System.out.println("Number of ships deployed: " + count + "\n");
        }
        return world1;
    }

    // Method to deploy player 2's ships
    public static char[][] deployPlayerShip2() {
        int count = 0;
        int x, y;
        System.out.print("PLAYER 2, ENTER YOUR SHIPS' COORDINATES: 0-4 0-4. \n");
        // The user will deploy 5 ships so keep prompting the user until 5 ships are
        // deployed
        while (count < 5) {
            int shipno = count + 1;

            System.out.print("Enter ship " + shipno + " location: ");
            String[] Ship = new String[2];
            Ship = scan.nextLine().split(" ");
            x = Integer.parseInt(Ship[0]);
            y = Integer.parseInt(Ship[1]);

            if (checkPosition2(x, y) == 0) {
                world2[x][y] = '1';
                history1[x][y] = '1';
                count++;
            } else if (checkPosition2(x, y) == 1) {
                System.out.println("Invalid coordinates. Choose different coordinates.");
            } else if (checkPosition2(x, y) == 2) {
                System.out.println("You already have a ship there. Choose different coordinates.");
            }
            // System.out.println("Number of ships deployed: " + count + "\n");
        }
        return world2;
    }

    // Check Map 1
    public static int checkPosition1(int x, int y) {
        // Return 1 if the coordinates are outside the map, 2 if a user ship is there, 3
        // if a computer ship is there
        // Return 0 if the position is not filled
        if (x > 5 || y > 5) {
            return 1;
        } else if (history2[x][y] == '1') {
            return 2;
        } else if (history2[x][y] == 'X' || history2[x][y] == 'O') {
            return 4; // if the ship has already been hit
        }
        return 0;
    }

    // Check Map 2
    public static int checkPosition2(int x, int y) {
        // Return 1 if the coordinates are outside the map, 2 if a user ship is there, 3
        // if a computer ship is there
        // Return 0 if the position is not filled
        if (x > 5 || y > 5) {
            return 1;
        } else if (history1[x][y] == '1') {
            return 2;
        } else if (history1[x][y] == 'X' || history1[x][y] == 'O') {
            return 4; // if the ship has already been hit
        }
        return 0;
    }

    // Method to enable a user to take a turn and try sink a ship
    public static int[] player1Turn(int[] ships) {
        // Get user to guess x and y coordinates

        boolean correct = false;
        int x, y, check;

        while (!correct) {
            System.out.println("Player 1, enter hit row/column: ");
            String[] Ship = new String[2];
            Ship = scan.nextLine().split(" ");
            x = Integer.parseInt(Ship[0]);
            y = Integer.parseInt(Ship[1]);

            check = checkPosition2(x, y); // Check if the coordinates are valid
            switch (check) {
                case 1:
                    System.out.println("Invalid coordinates. Choose different coordinates.");
                    break;
                case 2:
                    System.out.println("PLAYER 1 HIT PLAYER 2's SHIP!");
                    history1[x][y] = 'X'; // update history map
                    ships[1] = ships[1] - 1; // remove 1 ship from player2's.
                    correct = true;
                    break;
                case 4:
                    System.out.println("You already fired on this spot. Choose different coordinates.");
                    break;
                default:
                    // if no ship is found
                    System.out.println("PLAYER 1 MISSED!");
                    history1[x][y] = 'O'; // update history map
                    correct = true;
            }
        }
        return ships;
    }

    // Method to enable a user to take a turn and try sink a ship
    public static int[] player2Turn(int[] ships) {
        // Get user to guess x and y coordinates

        boolean correct = false;
        int x, y, check;

        while (!correct) {
            System.out.println("Player 2, enter hit row/column: ");
            String[] Ship = new String[2];
            Ship = scan.nextLine().split(" ");
            x = Integer.parseInt(Ship[0]);
            y = Integer.parseInt(Ship[1]);

            check = checkPosition1(x, y); // Check if the coordinates are valid
            switch (check) {
                case 1:
                    System.out.println("Invalid coordinates. Choose different coordinates.");
                    break;
                case 2:
                    System.out.println("PLAYER 2 HIT PLAYER 1's SHIP!");
                    history2[x][y] = 'X'; // update history map
                    ships[0] = ships[0] - 1; // remove 1 ship from player1's.
                    correct = true;
                    break;
                case 4:
                    System.out.println("You already fired on this spot. Choose different coordinates.");
                    break;
                default:
                    // if no ship is found
                    System.out.println("PLAYER 2 MISSED!");
                    history2[x][y] = 'O'; // update history map
                    correct = true;
            }
        }
        return ships;
    }

    // Method to play the game
    public static void playGame() {
        // calls above methods to play the game
        int[] ships = { 5, 5 }; // populate ships array with 5 user ships and 5 computer ships
        int playerShips1 = ships[0];
        int playerShips2 = ships[1];
        while (playerShips1 > 0 && playerShips2 > 0) {
            ships = player1Turn(ships);
            printHistory1();
            playerShips1 = ships[0];

            ships = player2Turn(ships);
            printHistory2();
            playerShips2 = ships[1];

        }

        if (playerShips1 == 0) {
            System.out.println("PLAYER 2 WINS! YOU SUNK ALL OF YOUR OPPONENT'S SHIPS!");
        } else if (playerShips2 == 0) {
            System.out.println("PLAYER 1 WINS! YOU SUNK ALL OF YOUR OPPONENT'S SHIPS!");
        }
    }
}
