public class ShipPlacementException extends Exception {
    private final String message;

    ShipPlacementException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
