import java.util.ArrayList;
import java.util.Scanner;

public class Field {
    private final char[][] map;
    private final char[][] foggedMap;
    private final ArrayList<Ship> ships = new ArrayList<>();

    public Field() {
        map = new char[10][10];
        foggedMap = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                map[i][j] = '~';
                foggedMap[i][j] = '~';
            }
        }
    }

    public void printMap() {
        print(map);
    }

    public void printFoggedMap() {
        print(foggedMap);
    }

    private void print(char[][] map) {
        System.out.println("\n  1 2 3 4 5 6 7 8 9 10");
        char[] firstColumn = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        int i = 0;
        for (char[] row : map) {
            System.out.print(firstColumn[i++] + " ");
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public void loadShip(int size, Scanner scanner) {
        while (true) {
            try {
                int[] formattedCoords = formatCoordinates(scanner.next(), scanner.next());
                Ship newShip = new Ship(formattedCoords[0], formattedCoords[1],
                        formattedCoords[2], formattedCoords[3], size);
                checkIfCoordsAreNotOccupied(newShip);
                putShipOnMap(newShip);
            } catch (ShipPlacementException exception) {
                System.out.println("Error! " + exception.getMessage() + " Try again:");
                continue;
            } catch (NumberFormatException exception) {
                System.out.println("Wrong format of coordinates!");
                continue;
            } catch (Exception e) {
                System.out.println("Wrong format");
                continue;
            }
            return;
        }
    }

    private int[] formatCoordinates(String starting, String ending) {
        return new int[]{starting.toUpperCase().charAt(0) - '@' - 1, ending.toUpperCase().charAt(0) - '@' - 1,
                Integer.parseInt(starting.substring(1)) - 1, Integer.parseInt(ending.substring(1)) - 1};
    }

    private int[] formatCoordinates(String coords) {
        return new int[]{coords.toUpperCase().charAt(0) - '@' - 1, Integer.parseInt(coords.substring(1)) - 1};
    }

    private void checkIfCoordsAreNotOccupied(Ship ship) throws ShipPlacementException {
        int y = ship.getyStartingShipCoordinate();
        int x = ship.getxStartingShipCoordinate();
        if (ship.getPositioning().equals("vertically")) {
            checkIfCoordsAreNotOccupiedVertically(ship, y, x);
        } else {
            checkIfCoordsAreNotOccupiedHorizontally(ship, y, x);
        }
    }

    private void checkIfCoordsAreNotOccupiedVertically(Ship ship, int y, int x)
            throws ShipPlacementException {

        try {
            if (map[y - 1][x] == 'O') {
                throw new ShipPlacementException("You placed it too close to another one.");
            }
        } catch (IndexOutOfBoundsException e) {
        }
        for (int i = 0; i < ship.getSize(); i++, y++) {
            if (map[y][x] == 'O') {
                throw new ShipPlacementException("Coordinate already occupied!");
            }
            try {
                if (map[y][x - 1] == 'O') {
                    throw new ShipPlacementException("You placed it too close to another one.");
                }
            } catch (IndexOutOfBoundsException e) {
            }
            try {
                if (map[y][x + 1] == 'O') {
                    throw new ShipPlacementException("You placed it too close to another one.");
                }
            } catch (IndexOutOfBoundsException e) {
            }
        }
        try {
            if (map[y][x] == 'O') {
                throw new ShipPlacementException("You placed it too close to another one.");
            }
        } catch (IndexOutOfBoundsException e) {
        }
    }

    private void checkIfCoordsAreNotOccupiedHorizontally(Ship ship, int y, int x)
            throws ShipPlacementException {

        try {
            if (map[y][x - 1] == 'O') {
                throw new ShipPlacementException("You placed it too close to another one.");
            }
        } catch (IndexOutOfBoundsException e) {
        }
        for (int i = 0; i < ship.getSize(); i++, x++) {
            if (map[y][x] == 'O') {
                throw new ShipPlacementException("Coordinate already occupied!");
            }
            try {
                if (map[y - 1][x] == 'O') {
                    throw new ShipPlacementException("You placed it too close to another one.");
                }
            } catch (IndexOutOfBoundsException e) {
            }
            try {
                if (map[y + 1][x] == 'O') {
                    throw new ShipPlacementException("You placed it too close to another one.");
                }
            } catch (IndexOutOfBoundsException e) {
            }
        }
        try {
            if (map[y][x] == 'O') {
                throw new ShipPlacementException("You placed it too close to another one.");
            }
        } catch (IndexOutOfBoundsException e) {
        }
    }

    private void putShipOnMap(Ship ship) {
        int y = ship.getyStartingShipCoordinate();
        int x = ship.getxStartingShipCoordinate();
        if (ship.getPositioning().equals("vertically")) {
            putShipOnMapVertically(ship, y, x);
        } else {
            putShipOnMapHorizontally(ship, y, x);
        }
        ships.add(ship);
    }

    private void putShipOnMapVertically(Ship ship, int y, int x) {
        for (int i = 0; i < ship.getSize(); i++, y++) {
            map[y][x] = 'O';
        }
    }

    private void putShipOnMapHorizontally(Ship ship, int y, int x) {
        for (int i = 0; i < ship.getSize(); i++, x++) {
            map[y][x] = 'O';
        }
    }

    public boolean takeAShot(String startingCoords) throws Exception {
        int[] formattedCoords = formatCoordinates(startingCoords);
        if (map[formattedCoords[0]][formattedCoords[1]] == 'O') {
            map[formattedCoords[0]][formattedCoords[1]] = 'X';
            foggedMap[formattedCoords[0]][formattedCoords[1]] = 'X';
            if (checkIfGameOver()) {
                throw new Exception();
            }
            return true;
        } else if (map[formattedCoords[0]][formattedCoords[1]] == 'X') {
            return true;
        }
        map[formattedCoords[0]][formattedCoords[1]] = 'M';
        foggedMap[formattedCoords[0]][formattedCoords[1]] = 'M';
        return false;
    }

    private boolean checkIfGameOver() {
        for (Ship ship : ships) {
            if (checkIfShipIsAlive(ship)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkIfShipIsAlive(Ship ship) {
        int x = ship.getxStartingShipCoordinate(),
                y = ship.getyStartingShipCoordinate();
        if (ship.getPositioning().equals("horizontally")) {
            for (int i = 0; i < ship.getSize(); i++) {
                if (map[y][x++] != 'X') {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < ship.getSize(); i++) {
                if (map[y++][x] != 'X') {
                    return true;
                }
            }
        }
        return false;
    }
}
