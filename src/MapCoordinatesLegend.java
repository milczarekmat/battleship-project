public enum MapCoordinatesLegend {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7),
    I(8),
    J(9);
    final int yCoord;

    MapCoordinatesLegend(int yCoord) {
        this.yCoord = yCoord;
    }

    public static MapCoordinatesLegend fromChar(char c) {
        String charAsString = Character.toString(c);
        return MapCoordinatesLegend.valueOf(charAsString);
    }
}
