import java.util.Scanner;

public class Player {
    private char[][] playerMap = {
        {'A', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
        {'B', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
        {'C', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
        {'D', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
        {'E', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
        {'F', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
        {'G', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
        {'H', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
        {'I', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
        {'J', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
    };
    private int totalShips = 0;

    public char[][] getPlayerMap() {
        return playerMap;
    }
    public void printMap() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (char[] row : playerMap) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
    public boolean loadShip(int size){
        try (Scanner scanner = new Scanner(System.in)) {
            String startingCoords = scanner.next();
            String endingCoords = scanner.next();
            char startRow = startingCoords.charAt(0);
            char endRow = endingCoords.charAt(0);
            int startCol = Integer.parseInt(String.valueOf(startingCoords.charAt(1)));
            int endCol = Integer.parseInt(String.valueOf(endingCoords.charAt(1)));
            Ship newShip;
            try {
                newShip = new Ship(startRow, endRow, startCol, endCol, size);
            }
            catch (IllegalArgumentException e) {
                //todo zmienic?
                throw new NumberFormatException();
            }
            checkIfCoordsAreNotOccupied(newShip);
        }
        catch (NumberFormatException exception){
            System.out.println("Error! Wrong format of coordinates! Try again");
            return false;
        }
        return true;
    }
    private boolean checkIfCoordsAreNotOccupied(Ship ship) {
        if (ship.getPositioning().equals("vertically")) {

        }
        return true;
    }
/*    private boolean checkIfAddingShipPossible(char startRow, char endRow, int startCol, int endCol, int size) {
        if (startRow!=endRow && startCol!=endCol) {
            return false;
        }
        if (startRow-endRow!=size && startCol-endCol!=size) {
            return false;
        }



        return checkIfCoordsAreNotOccupied(startRow, endRow, startCol, endCol);

    }*/

}
