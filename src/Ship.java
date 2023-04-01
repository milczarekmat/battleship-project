public class Ship {
    private final int size;
    private final String positioning;

    private final int xStartingShipCoordinate;
    private final int yStartingShipCoordinate;
    private final int xEndingShipCoordinate;
    private final int yEndingShipCoordinate;

    public Ship(char startRow, char endRow, int startCol, int endCol, int size) throws IllegalArgumentException{
        if (startRow!=endRow && startCol!=endCol) {
            throw new IllegalArgumentException();
        }
        if (startRow-endRow!=size && Math.abs(startCol-endCol)+1!=size) {
            throw new IllegalArgumentException();
        }
        yStartingShipCoordinate = MapCoordinatesLegend.fromChar(startRow).yCoord;
        yEndingShipCoordinate = MapCoordinatesLegend.fromChar(endRow).yCoord;
        xStartingShipCoordinate = startCol - 1;
        xEndingShipCoordinate = endCol - 1;
        this.size = size;
        positioning = startRow == endRow ? "horizontally" : "vertically";
    }

    public int getSize() {
        return size;
    }

    public String getPositioning() {
        return positioning;
    }

    public int getxStartingShipCoordinate() {
        return xStartingShipCoordinate;
    }

    public int getyStartingShipCoordinate() {
        return yStartingShipCoordinate;
    }

    public int getxEndingShipCoordinate() {
        return xEndingShipCoordinate;
    }

    public int getyEndingShipCoordinate() {
        return yEndingShipCoordinate;
    }
}
