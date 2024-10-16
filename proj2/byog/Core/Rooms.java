package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * This class is used to generate rooms in the world.
 * It contains a list of rooms with their positions and sizes.
 */
public class Rooms {
  private int numRooms;
  private Room[] rooms;
  private int numAttempts;

  private final int WIDTH = Game.WIDTH;
  private final int HEIGHT = Game.HEIGHT;

  /**
   * Constructor of the Rooms class. It generates rooms in the world.
   *
   * @param random the random object used to generate rooms.
   */
  public Rooms(Random random) {
    // Initialize the member variables.
    numRooms = 5 + RandomUtils.geometric(random, 0.1);
    rooms = new Room[numRooms];
    numAttempts = 0;

    // Generate rooms.
    generateRooms(random);

    // Capture the case where the number of attempts exceeds 1000000.
    while (numAttempts > 1000000) {
      numRooms = 5 + RandomUtils.geometric(random, 0.1);
      rooms = new Room[numRooms];
      numAttempts = 0;
      generateRooms(random);
    }
  }

  /**
   * Draws rooms in the world.
   *
   * @param world the world to draw rooms in.
   */
  public void drawRooms(TETile[][] world) {
    for (int i = 0; i < numRooms; i++) {
      Room room = rooms[i];

      // first draw walls
      for (int x = room.getUpperLeft().getX(); x <= room.getLowerRight().getX(); x++) {
        world[x][room.getUpperLeft().getY()] = Tileset.WALL;
        world[x][room.getLowerRight().getY()] = Tileset.WALL;
      }
      for (int y = room.getLowerRight().getY(); y <= room.getUpperLeft().getY(); y++) {
        world[room.getUpperLeft().getX()][y] = Tileset.WALL;
        world[room.getLowerRight().getX()][y] = Tileset.WALL;
      }

      // then draw floors
      for (int x = room.getUpperLeft().getX() + 1; x < room.getLowerRight().getX(); x++) {
        for (int y = room.getLowerRight().getY() + 1; y < room.getUpperLeft().getY(); y++) {
          world[x][y] = Tileset.FLOOR;
        }
      }
    }
  }

  /**
   * Returns the number of rooms in the world.
   */
  public int getNumRooms() {
    return numRooms;
  }

  /**
   * Returns the room at index i.
   *
   * @param i the index of the room to be returned.
   */
  public Room getRoom(int i) {
    return rooms[i];
  }

  /**
   * Generates rooms in the world.
   *
   * @param random the random object used to generate rooms.
   */
  private void generateRooms(Random random) {
    int i = 0;
    while (i < numRooms) {
      // Generate a room.
      Position tempUpperLeft = new Position(
          RandomUtils.uniform(random, WIDTH),
          RandomUtils.uniform(random, HEIGHT)
      );
      Size tempSize = new Size(
          (int) RandomUtils.gaussian(random, 10, 3),
          (int) RandomUtils.gaussian(random, 10, 3)
      );
      Position tempLowerRight = new Position(
          tempUpperLeft.getX() + tempSize.getWidth(),
          tempUpperLeft.getY() - tempSize.getHeight()
      );
      Room tempRoom = new Room(tempUpperLeft, tempLowerRight);

      // Update the number of attempts.
      // If the number of attempts exceeds 1000000, return.
      numAttempts++;
      if (numAttempts > 1000000) {
        return;
      }

      // Check whether the newly generated room is valid.
      if (checkRoom(tempRoom, i)) {
        rooms[i] = tempRoom;
        i++;
      }
    }
  }

  /**
   * Checks whether an overlap occurs, whether the newly generated room is out of bound or whether the
   * relative position of the corners of the newly generated room is correct. It also checks the gap
   * between the newly generated room and the existing rooms. The gap should be at least 1.
   * Returns true if no error occurs, false otherwise.
   *
   * @param tempRoom the room to be checked.
   * @param i        the index of the room to be checked.
   */
  private boolean checkRoom(Room tempRoom, int i) {
    // Check the relative position of the corners of the newly generated room.
    if (tempRoom.getUpperLeft().getX() >= tempRoom.getLowerRight().getX()
        || tempRoom.getUpperLeft().getY() <= tempRoom.getLowerRight().getY()) {
      return false;
    }

    // Size of the room should be at least 2.
    if (tempRoom.getLowerRight().getX() - tempRoom.getUpperLeft().getX() <= 2
        || tempRoom.getUpperLeft().getY() - tempRoom.getLowerRight().getY() <= 2) {
      return false;
    }

    // Check whether the newly generated room is out of bound.
    if (tempRoom.getUpperLeft().getX() < 0 || tempRoom.getUpperLeft().getX() >= WIDTH
        || tempRoom.getUpperLeft().getY() >= HEIGHT || tempRoom.getUpperLeft().getY() < 0
        || tempRoom.getLowerRight().getX() < 0 || tempRoom.getLowerRight().getX() >= WIDTH
        || tempRoom.getLowerRight().getY() >= HEIGHT || tempRoom.getLowerRight().getY() < 0) {
      return false;
    }

    // Check whether an overlap occurs.
    for (int j = 0; j < i; j++) {
      Room room = rooms[j];

      if (isInRoom(tempRoom.getUpperLeft(), room)) {
        return false;
      }

      if ((isLeft(tempRoom.getUpperLeft(), room) || isAbove(tempRoom.getUpperLeft(), room) || isUpperLeft(tempRoom.getUpperLeft(), room))
          && (tempRoom.getLowerRight().getX() >= room.getUpperLeft().getX() && tempRoom.getLowerRight().getY() <= room.getUpperLeft().getY())) {
        return false;
      }
    }

    // Check the gap between the newly generated room and the existing rooms.
    for (int j = 0; j < i; j++) {
      Room room = rooms[j];

      if (tempRoom.getUpperLeft().getX() - room.getLowerRight().getX() == 1
          || room.getUpperLeft().getX() - tempRoom.getLowerRight().getX() == 1
          || tempRoom.getLowerRight().getY() - room.getUpperLeft().getY() == 1
          || room.getLowerRight().getY() - tempRoom.getUpperLeft().getY() == 1) {
        return false;
      }
    }

    return true;
  }

  /**
   * Check whether a point is to the left of a room
   *
   * @param p    the point to be checked
   * @param room the room to be checked
   */
  private boolean isLeft(Position p, Room room) {
    return p.getX() < room.getUpperLeft().getX() && p.getY() <= room.getUpperLeft().getY() && p.getY() >= room.getLowerRight().getY();
  }

  /**
   * Check whether a point is above a room
   *
   * @param p    the point to be checked
   * @param room the room to be checked
   */
  private boolean isAbove(Position p, Room room) {
    return p.getY() > room.getUpperLeft().getY() && p.getX() >= room.getUpperLeft().getX() && p.getX() <= room.getLowerRight().getX();
  }

  /**
   * Check whether a point is in the upper left corner of a room
   *
   * @param p    the point to be checked
   * @param room the room to be checked
   */
  private boolean isUpperLeft(Position p, Room room) {
    return p.getX() < room.getUpperLeft().getX() && p.getY() > room.getUpperLeft().getY();
  }

  /**
   * Check whether a point is in a room
   *
   * @param p    the point to be checked
   * @param room the room to be checked
   */
  private boolean isInRoom(Position p, Room room) {
    return p.getX() >= room.getUpperLeft().getX() && p.getX() <= room.getLowerRight().getX()
        && p.getY() <= room.getUpperLeft().getY() && p.getY() >= room.getLowerRight().getY();
  }
}
