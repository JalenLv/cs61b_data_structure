public class NBody {
  public static double readRadius(String filename) {
    In in = new In(filename);
    in.readInt();
    double radius = in.readDouble();
    return radius;
  }

  public static Planet[] readPlanets(String filename) {
    In in = new In(filename);
    int n = in.readInt();
    in.readDouble();

    Planet[] planets = new Planet[n];
    for (int i = 0; i < n; i++) {
      double xPos = in.readDouble();
      double yPos = in.readDouble();
      double xVel = in.readDouble();
      double yVel = in.readDouble();
      double m = in.readDouble();
      String img = in.readString();

      planets[i] = new Planet(xPos, yPos, xVel, yVel, m, img);
    }

    return planets;
  }

  public static void main(String[] args) {
    if (args.length != 3) {
      System.out.println("Usage: java NBody T dt filename");
      System.exit(1);
    }
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];

    Planet[] planets = readPlanets(filename);
    double radius = readRadius(filename);

    StdDraw.setScale(-radius, radius);
    StdDraw.enableDoubleBuffering();

    StdDraw.clear();
    StdDraw.picture(0, 0, "./images/starfield.jpg", 2 * radius, 2 * radius);

    for (int i = 0; i < planets.length; i++) {
      planets[i].draw();
    }

    StdDraw.show();

    double t = 0;
    while (t < T) {
      int n = planets.length;

      double[] xForces = new double[n];
      double[] yForces = new double[n];
      for (int i = 0; i < n; i++) {
        xForces[i] = planets[i].calcNetForceExertedByX(planets);
        yForces[i] = planets[i].calcNetForceExertedByY(planets);
      }

      for (int i = 0; i < n; i++) {
        planets[i].update(dt, xForces[i], yForces[i]);
      }

      StdDraw.clear();
      StdDraw.picture(0, 0, "./images/starfield.jpg", 2 * radius, 2 * radius);
      for (int i = 0; i < planets.length; i++) {
        planets[i].draw();
      }

      StdDraw.show();
      StdDraw.pause(10);

      t += dt;
    }

    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < planets.length; i++) {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
              planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
              planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
    }
  }
}
