package byog.Core;

import java.util.Random;

/**
 * This class is used to generate rooms in the world.
 * It contains a list of rooms with their positions and sizes.
 */

/**
 * TODO: Implement the checkRoom method.
 * TODO: Change positions and sizes to upperLeft and lowerRight.
 * TODO: Add Room class.
 */

public class Rooms {
  private final int numRooms;
  private Position[] positions;
  private Size[] sizes;

  private int WIDTH = Game.WIDTH;
  private int HEIGHT = Game.HEIGHT;

  /**
   * Constructor of the Rooms class. It generates rooms in the world.
   *
   * @param random the random object used to generate rooms.
   */
  public Rooms(Random random) {
    // Initialize the member variables.
    numRooms = 5 + RandomUtils.geometric(random, 0.1);
    positions = new Position[numRooms];
    sizes = new Size[numRooms];

    // Generate rooms.
    generateRooms(random);

    // Each time a room generated, check whether an overlap occurs.
  }

  /**
   * Generate rooms in the world.
   *
   * @param random the random object used to generate rooms.
   */
  private void generateRooms(Random random) {
    int i = 0;
    while (i < numRooms) {
      // Generate a room.
      Position tempPosition = new Position(
          RandomUtils.uniform(random, WIDTH),
          RandomUtils.uniform(random, HEIGHT)
      );
      Size tempSize = new Size(
          (int) RandomUtils.gaussian(random, 10, 1),
          (int) RandomUtils.gaussian(random, 10, 1)
      );

      // Check whether an overlap occurs or whether the newly generated room is out of bound.
      if (checkRoom(tempPosition, tempSize, i)) {
        continue;
      } else {
        positions[i] = tempPosition;
        sizes[i] = tempSize;
        i++;
      }
    }
  }

  /**
   * Check whether an overlap occurs or whether the newly generated room is out of bound.
   * Returns true if an overlap occurs, false otherwise.
   *
   * @param tempPosition the position of the room to be checked.
   * @param tempSize     the size of the room to be checked.
   * @param i            the index of the room to be checked.
   */
  private boolean checkRoom(Position tempPosition, Size tempSize, int i) {

  }
}
