import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Field field = new Field();
        playerContest(field);
    }

    private static void playerContest(Field field) {
        try (Scanner scanner = new Scanner(System.in)) {
            field.printMap();

            System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):");
            field.loadShip(5, scanner);
            field.printMap();

            System.out.println("Enter the coordinates of the Battleship (4 cells):");
            field.loadShip(4, scanner);
            field.printMap();

            System.out.println("Enter the coordinates of the Submarine (3 cells):");
            field.loadShip(3, scanner);
            field.printMap();

            System.out.println("Enter the coordinates of the Cruiser (3 cells):");
            field.loadShip(3, scanner);
            field.printMap();

            System.out.println("Enter the coordinates of the Destroyer (2 cells):");
            field.loadShip(2, scanner);
            field.printMap();

            System.out.println("\nThe game starts!");
            field.printMap();
            while (true) {
                try {
                    if (field.takeAShot(scanner.next())) {
                        field.printMap();
                        System.out.println("You hit a ship!");
                    } else {
                        field.printMap();
                        System.out.println("You missed");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Faulty coordinates!");
                    continue;
                }
                break;
            }
        }
    }
}