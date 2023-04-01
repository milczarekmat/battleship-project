public class Ship {
    private final int size;
    private final String positioning;
    private int xStartingShipCoordinate;
    private int yStartingShipCoordinate;
    private int xEndingShipCoordinate;
    private int yEndingShipCoordinate;

    public Ship(char startRow, char endRow, int startCol, int endCol, int size) throws ShipPlacementException{
        if (startRow!=endRow && startCol!=endCol) {
            throw new ShipPlacementException("Wrong ship location!");
        }
        if (startRow-endRow!=size && Math.abs(startCol-endCol)+1 !=size) {
            throw new ShipPlacementException("Wrong length of the Submarine!");
        }
        positioning = startRow == endRow ? "horizontally" : "vertically";
        this.size = size;

        boolean reverse = false;
        if (positioning.equals("horizontally")) {
            if (endCol < startCol) {
                reverse = true;
            }
        }
        else {
            if (endRow < startRow) {
                reverse = true;
            }
        }

        if (!reverse) {
            setCoordinates(startRow, endRow, startCol, endCol);
        }
        else {
            setCoordinates(endRow, startRow, endCol, startCol);
        }
    }

    private void setCoordinates(char startRow, char endRow, int startCol, int endCol) {
        this.yStartingShipCoordinate = MapCoordinatesLegend.fromChar(startRow).yCoord;
        yEndingShipCoordinate = MapCoordinatesLegend.fromChar(endRow).yCoord;
        xStartingShipCoordinate = startCol - 1;
        xEndingShipCoordinate = endCol - 1;
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
