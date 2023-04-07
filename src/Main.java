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
            field.printFoggedMap();
            while (true) {
                try {
                    if (field.takeAShot(scanner.next())) {
                        field.printFoggedMap();
                        System.out.print("You hit a ship!");
                    } else {
                        field.printFoggedMap();
                        System.out.print("You missed.");
                    }
                    System.out.println(" Try again:");
                    field.printFoggedMap();
                    continue;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Faulty coordinates!");
                    continue;
                } catch (Exception e) {
                    field.printMap();
                    System.out.println("You sank the last ship. You won. Congratulations!");
                }
                break;
            }
        }
    }
}