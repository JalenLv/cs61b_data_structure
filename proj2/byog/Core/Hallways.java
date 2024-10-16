package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * This class is used to generate hallways in the world.
 */
public class Hallways {
  private final int numRooms;
  private final int numHallways;
  private Hallway[] hallways;

  private final int WIDTH = Game.WIDTH;
  private final int HEIGHT = Game.HEIGHT;

  /**
   * Constructor of the Hallways class. It generates hallways in the world.
   *
   * @param random the random object used to generate hallways.
   * @param rooms  the rooms to connect with hallways.
   */
  public Hallways(Random random, Rooms rooms) {
    numRooms = rooms.getNumRooms();
    numHallways = numRooms - 1;
    hallways = new Hallway[numHallways];

    // Generate hallways.
    generateHallways(random, rooms);
  }

  /**
   * Draws hallways in the world.
   *
   * @param world the world to draw hallways in.
   */
  public void drawHallways(TETile[][] world) {
  }

  /**
   * Generates hallways in the world.
   *
   * @param random the random object used to generate hallways.
   * @param rooms  the rooms to connect with hallways.
   */
  private void generateHallways(Random random, Rooms rooms) {
    Deque<Integer> connectedRooms = new ArrayDeque<>();
    int startRoomIndex = RandomUtils.uniform(random, numRooms);
    connectedRooms.addLast(startRoomIndex);
  }
}
