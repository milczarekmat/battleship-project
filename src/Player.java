import java.security.SignatureException;
import java.util.Scanner;

public class Player {
    private char[][] playerMap = {
        {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
        {'~', 'O', '~', '~', '~', '~', '~', '~', '~', '~'},
        {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
        {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
        {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
        {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
        {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
        {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
        {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
        {'~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
    };
    private int totalShips = 0;

    public char[][] getPlayerMap() {
        return playerMap;
    }
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
    public boolean loadShip(int size){
        try (Scanner scanner = new Scanner(System.in)) {
            do {
                try {
                    String startingCoords = scanner.next();
                    String endingCoords = scanner.next();
                    char startRow = startingCoords.charAt(0);
                    char endRow = endingCoords.charAt(0);
                    int startCol = Integer.parseInt(String.valueOf(startingCoords.charAt(1)));
                    int endCol = Integer.parseInt(String.valueOf(endingCoords.charAt(1)));
                    Ship newShip = new Ship(startRow, endRow, startCol, endCol, size);
                    checkIfCoordsAreNotOccupied(newShip);
                    totalShips++;
                    return true;
                } catch (ShipPlacementException exception) {
                    System.out.println("Error! " + exception.getMessage() + " Try again:");
                } catch (NumberFormatException exception) {
                    System.out.println("Wrong format of coordinates!");
                } catch (Exception e) {
                    System.out.println("Wrong format");
                }

            } while (true);
        }
    }
    private void checkIfCoordsAreNotOccupied(Ship ship) throws ShipPlacementException {
        if (ship.getPositioning().equals("vertically")) {
            checkIfCoordsAreNotOccupiedVertically(ship);
        }
        else {
            checkIfCoordsAreNotOccupiedHorizontally(ship);
        }
    }
    private void checkIfCoordsAreNotOccupiedVertically(Ship ship) throws ShipPlacementException {
        int y = ship.getyStartingShipCoordinate();
        int x = ship.getxStartingShipCoordinate();
        //todo last coded
        try {
            if (playerMap[y-1][x] == 'O') {
                throw new ShipPlacementException("You placed it too close to another one.");
            }
        }
        catch (IndexOutOfBoundsException e) {}
        for (int i=0; i<ship.getSize(); i++, y++) {
            if (playerMap[y][x] == 'O') {
                throw new ShipPlacementException("Coordinate already occupied!");
            }
            try {
                if (playerMap[y][x-1] == 'O'){
                    throw new ShipPlacementException("You placed it too close to another one.");
                }
            }
            catch (IndexOutOfBoundsException e) {}
            try {
                if (playerMap[y][x+1] == 'O'){
                    throw new ShipPlacementException("You placed it too close to another one.");
                }
            }
            catch (IndexOutOfBoundsException e) {}
        }
        try {
            if (playerMap[y+1][x] == 'O') {
                throw new ShipPlacementException("You placed it too close to another one.");
            }
        }
        catch (IndexOutOfBoundsException e) {}
    }
    private void checkIfCoordsAreNotOccupiedHorizontally(Ship ship) throws ShipPlacementException {

    }

    private void putShipOnMap(Ship ship) {
        if (ship.getPositioning().equals("vertically")) {
            putShipOnMapVertically();
        }
        else {
            putShipOnMapHorizontally();
        }
    }


}
