import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)){
            Player player = new Player();
            player.printMap();

            System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):");
            player.loadShip(5, scanner);
            player.printMap();

            System.out.println("Enter the coordinates of the Battleship (4 cells):");
            player.loadShip(4, scanner);
            player.printMap();

            System.out.println("Enter the coordinates of the Submarine (3 cells):");
            player.loadShip(3, scanner);
            player.printMap();

            System.out.println("Enter the coordinates of the Cruiser (3 cells):");
            player.loadShip(3, scanner);
            player.printMap();

            System.out.println("Enter the coordinates of the Destroyer (2 cells):");
            player.loadShip(2, scanner);
            player.printMap();
        }
    }
}