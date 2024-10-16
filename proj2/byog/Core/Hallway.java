package byog.Core;

public class Hallway {
  public enum Kind {
    Straight,
    LShape
  }

  private Kind kind;
  private Position p1;
  private Position p2;

  // For Straight hallways, p3 is null.
  // For LShape hallways, p3 is the corner position.
  private Position p3;

  public Hallway(Kind kind, Position p1, Position p2, Position p3) {
    this.kind = kind;
    this.p1 = p1;
    this.p2 = p2;
    this.p3 = p3;
  }

  public Kind getKind() {
    return kind;
  }

  public Position getP1() {
    return p1;
  }

  public Position getP2() {
    return p2;
  }

  public Position getP3() {
    return p3;
  }
}
