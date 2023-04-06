public class Ship {
    private final int size;
    private final String positioning;
    private int xStartingShipCoordinate;
    private int yStartingShipCoordinate;

    public Ship(int startRow, int endRow, int startCol, int endCol, int size) throws ShipPlacementException {
        if (startRow != endRow && startCol != endCol) {
            throw new ShipPlacementException("Wrong ship location!");
        }
        if (Math.abs(startRow - endRow) + 1 != size && Math.abs(startCol - endCol) + 1 != size) {
            throw new ShipPlacementException("Wrong length of the Submarine!");
        }
        positioning = startRow == endRow ? "horizontally" : "vertically";
        this.size = size;

        if (positioning.equals("horizontally")) {
            if (endCol < startCol) {
                setCoordinates(endRow, endCol);
                return;
            }
        } else {
            if (endRow < startRow) {
                setCoordinates(endRow, endCol);
                return;
            }
        }

        setCoordinates(startRow, startCol);
    }

    private void setCoordinates(int y, int x) {
        yStartingShipCoordinate = y;
        xStartingShipCoordinate = x;
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

}
