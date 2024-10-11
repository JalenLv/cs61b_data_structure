package byog.lab5;

import org.junit.Test;

import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
  /**
   * Draws a single hexagon of type `type` at a given position with size `size`.
   *
   * @param p    indicates the lower left corner of the hexagon.
   * @param size gives the size of the hexagon.
   * @param type specifies the type of the hexagon.
   */
  private static void drawSingleHexagon(
      Position p, int size, TETile type, TETile[][] canvas
  ) {
    // first, draw the lower half of the hexagon.
    for (int i = 0; i < size; i++) {
      Position starter = new Position(
          p.getX() - i,
          p.getY() + i
      );
      for (int j = 0; j < size + i * 2; j++) {
        canvas[starter.getX() + j][starter.getY()] = type;
      }
    }

    // second, draw the upper half of the hexagon.
    for (int i = 0; i < size; i++) {
      Position starter = new Position(
          p.getX() - i,
          (p.getY() + size * 2 - 1) - i
      );
      for (int j = 0; j < size + i * 2; j++) {
        canvas[starter.getX() + j][starter.getY()] = type;
      }
    }
  }

  private static void testDrawSingleHexagon() {
    // initialize the tile rendering engine with size 20 x 20.
    TERenderer ter = new TERenderer();
    ter.initialize(20, 20);

    // initialize the canvas.
    TETile[][] canvas = new TETile[20][20];
    for (int i = 0; i < 20; i++)
      for (int j = 0; j < 20; j++)
        canvas[i][j] = Tileset.PLAYER;

    // draw a single hexagon.
    drawSingleHexagon(new Position(5, 8), 3, Tileset.FLOWER, canvas);

    // draw another hexagon of different size.
    drawSingleHexagon(new Position(10, 15), 2, Tileset.WALL, canvas);

    // draw another hexagon of different size.
    drawSingleHexagon(new Position(10, 0), 5, Tileset.GRASS, canvas);

    // display.
    ter.renderFrame(canvas);
  }

  /**
   * Helper function to calculate the positions of the bottom five hexagons.
   *
   * @param size        specifies the size of the hexagons.
   * @param canvasWidth specifies the width of the canvas.
   */
  private static Position[] calcBottomFive(int size, int canvasWidth) {
    Position[] bottomFive = new Position[5];
    // first calculate the position of the bottom-most hexagon.
    bottomFive[0] = new Position(
        (canvasWidth / 2) - (size / 2) + 1,
        1
    );

    // then, calculate the positions of the other four hexagons.
    bottomFive[1] = new Position(
        bottomFive[0].getX() - 2 * size + 1,
        bottomFive[0].getY() + size
    );
    bottomFive[2] = new Position(
        bottomFive[1].getX() - 2 * size + 1,
        bottomFive[1].getY() + size
    );
    bottomFive[3] = new Position(
        bottomFive[0].getX() + 2 * size - 1,
        bottomFive[0].getY() + size
    );
    bottomFive[4] = new Position(
        bottomFive[3].getX() + 2 * size - 1,
        bottomFive[3].getY() + size
    );
    return bottomFive;
  }

  /**
   * Draws a hexagon world consisting of 19 hexagons that form a regular hexagon
   *
   * @param size specifies the size of the hexagons.
   */
  private static void drawHexagonTessellation(int size) {
    // calculate width and height of the canvas.
    final int canvasWidth = ((2 * size - 1) * 5 + size - 1) + 2;
    final int canvasHeight = 10 * size + 2;

    // initialize the tile rendering engine with size canvasWidth x canvasHeight.
    TERenderer ter = new TERenderer();
    ter.initialize(canvasWidth, canvasHeight);

    // initialize the canvas
    TETile[][] canvas = new TETile[canvasWidth][canvasHeight];
    for (int i = 0; i < canvasWidth; i++)
      for (int j = 0; j < canvasHeight; j++)
        canvas[i][j] = Tileset.NOTHING;

    // calculate the positions of the 19 hexagons respectively.
    // first, calculate the positions of the bottom five hexagons.
    Position[] bottomFive = calcBottomFive(size, canvasWidth);

    // draw hexagons
    int[] numHexPerCol = {5, 4, 3, 4, 3};
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < numHexPerCol[i]; j++) {
        Position p = new Position(
            bottomFive[i].getX(),
            bottomFive[i].getY() + j * size * 2
        );
        drawSingleHexagon(p, size, Tileset.getRandTile(), canvas);
      }
    }

    // display
    ter.renderFrame(canvas);
  }

  public static void main(String[] args) {
    // use command line argument to specify the size of the hexagons.
    if (args.length == 1)
      drawHexagonTessellation(Integer.parseInt(args[0]));
    else if (args.length == 2)
      if (args[0].equals("test") && args[1].equals("drawSingleHexagon"))
        testDrawSingleHexagon();
      else
        System.out.println("Invalid command line arguments.");
    else
      System.out.println("Please enter a single integer argument to specify the size of the hexagons.");
  }
}
