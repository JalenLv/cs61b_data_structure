package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

import org.junit.Test;

/**
 * Generate a random 2d tile-based world with a given seed.
 * If no seed is given, using system time as the seed.
 * Both methods return a 2d TETile array.
 */
public class WorldGenerator {
  /**
   * Generate a random 2d tile-based world with a given seed.
   *
   * @param seed the seed to generate the world.
   */
  public static TETile[][] generateWithSeed(long seed) {
    // Initialize the world.
    final int WIDTH = Game.WIDTH;
    final int HEIGHT = Game.HEIGHT;
    TETile[][] world = new TETile[WIDTH][HEIGHT];
    for (int i = 0; i < WIDTH; i++) {
      for (int j = 0; j < HEIGHT; j++) {
        world[i][j] = Tileset.NOTHING;
      }
    }

    // Initialize Random object.
    Random random = new Random(seed);

    // Randomly generate rooms.
    Rooms rooms = new Rooms(random);
    rooms.drawRooms(world);

    // Connect rooms with random hallways.
    Hallways hallways = new Hallways(random, rooms);
    hallways.drawHallways(world);

    return world;
  }

  /**
   * Generate a random 2d tile-based world with system time as the seed.
   */
  public static TETile[][] generateWithoutSeed() {
    long seed = System.currentTimeMillis();
    return generateWithSeed(seed);
  }

  public static void main(String[] args) {
    TETile[][] world = generateWithoutSeed();
    System.out.println(TETile.toString(world));
  }
}
