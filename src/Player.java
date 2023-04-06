import java.util.Scanner;

public class Player {
    private final char[][] playerMap = {
            {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
            {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
            {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
            {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
            {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
            {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
            {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
            {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
            {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
            {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
    };

    public void printMap() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        MapCoordinatesLegend[] firstColumn = MapCoordinatesLegend.values();
        int i = 0;
        for (char[] row : playerMap) {
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
                String startingCoords = scanner.next();
                String endingCoords = scanner.next();
                Ship newShip = new Ship(
                        startingCoords.charAt(0),
                        endingCoords.charAt(0),
                        Integer.parseInt(startingCoords.substring(1)),
                        Integer.parseInt(endingCoords.substring(1)), size);
                checkIfCoordsAreNotOccupied(newShip);
                putShipOnMap(newShip);
            } catch (ShipPlacementException exception) {
                System.out.println("Error! " + exception.getMessage() + " Try again:");
                continue;
            } catch (NumberFormatException exception) {
                System.out.println("Wrong format of coordinates!");
            } catch (Exception e) {
                System.out.println("Wrong format");
            }
            return;
        }
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
            if (playerMap[y - 1][x] == 'O') {
                throw new ShipPlacementException("You placed it too close to another one.");
            }
        } catch (IndexOutOfBoundsException e) {
        }
        for (int i = 0; i < ship.getSize(); i++, y++) {
            if (playerMap[y][x] == 'O') {
                throw new ShipPlacementException("Coordinate already occupied!");
            }
            try {
                if (playerMap[y][x - 1] == 'O') {
                    throw new ShipPlacementException("You placed it too close to another one.");
                }
            } catch (IndexOutOfBoundsException e) {
            }
            try {
                if (playerMap[y][x + 1] == 'O') {
                    throw new ShipPlacementException("You placed it too close to another one.");
                }
            } catch (IndexOutOfBoundsException e) {
            }
        }
        try {
            if (playerMap[y][x] == 'O') {
                throw new ShipPlacementException("You placed it too close to another one.");
            }
        } catch (IndexOutOfBoundsException e) {
        }
    }

    private void checkIfCoordsAreNotOccupiedHorizontally(Ship ship, int y, int x)
            throws ShipPlacementException {

        try {
            if (playerMap[y][x - 1] == 'O') {
                throw new ShipPlacementException("You placed it too close to another one.");
            }
        } catch (IndexOutOfBoundsException e) {
        }
        for (int i = 0; i < ship.getSize(); i++, x++) {
            if (playerMap[y][x] == 'O') {
                throw new ShipPlacementException("Coordinate already occupied!");
            }
            try {
                if (playerMap[y - 1][x] == 'O') {
                    throw new ShipPlacementException("You placed it too close to another one.");
                }
            } catch (IndexOutOfBoundsException e) {
            }
            try {
                if (playerMap[y + 1][x] == 'O') {
                    throw new ShipPlacementException("You placed it too close to another one.");
                }
            } catch (IndexOutOfBoundsException e) {
            }
        }
        try {
            if (playerMap[y][x] == 'O') {
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
    }

    private void putShipOnMapVertically(Ship ship, int y, int x) {
        for (int i = 0; i < ship.getSize(); i++, y++) {
            playerMap[y][x] = 'O';
        }
    }

    private void putShipOnMapHorizontally(Ship ship, int y, int x) {
        for (int i = 0; i < ship.getSize(); i++, x++) {
            playerMap[y][x] = 'O';
        }
    }
}
