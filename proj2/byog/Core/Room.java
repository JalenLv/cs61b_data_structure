package byog.Core;

public class Room {
  private final Position upperLeft;
  private final Position lowerRight;

  public Room(Position upperLeft, Position lowerRight) {
    this.upperLeft = upperLeft;
    this.lowerRight = lowerRight;
  }

  public Position getUpperLeft() {
    return upperLeft;
  }

  public Position getLowerRight() {
    return lowerRight;
  }
}
