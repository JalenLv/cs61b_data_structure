public class Planet {
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;

  public Planet(double xP, double yP, double xV, double yV, double m, String img) {
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }

  public Planet(Planet p) {
    xxPos = p.xxPos;
    yyPos = p.yyPos;
    xxVel = p.xxVel;
    yyVel = p.yyVel;
    mass = p.mass;
    imgFileName = p.imgFileName;
  }

  public double calcDistance(Planet other) {
    double dx = xxPos - other.xxPos;
    double dy = yyPos - other.yyPos;
    double dist = Math.sqrt(dx * dx + dy * dy);
    return dist;
  }

  public double calcForceExertedBy(Planet other) {
    double G = 6.67e-11;
    double dist = this.calcDistance(other);
    double force = G * mass * other.mass / (dist * dist);
    return force;
  }

  public double calcForceExertedByX(Planet other) {
    double force = this.calcForceExertedBy(other);
    double dx = other.xxPos - xxPos;
    double dist = this.calcDistance(other);
    double force_x = force * dx / dist;
    return force_x;
  }

  public double calcForceExertedByY(Planet other) {
    double force = this.calcForceExertedBy(other);
    double dy = other.yyPos - yyPos;
    double dist = this.calcDistance(other);
    double force_y = force * dy / dist;
    return force_y;
  }

  public double calcNetForceExertedByX(Planet[] L) {
    double netForce_x = 0;
    for (int i = 0; i < L.length; i++) {
      Planet other = L[i];

      if (this.equals(other))
        continue;

      netForce_x += this.calcForceExertedByX(other);
    }
    return netForce_x;
  }

  public double calcNetForceExertedByY(Planet[] L) {
    double netForce_y = 0;
    for (int i = 0; i < L.length; i++) {
      Planet other = L[i];

      if (this.equals(other))
        continue;

      netForce_y += this.calcForceExertedByY(other);
    }
    return netForce_y;
  }

  public void update(double dt, double force_x, double force_y) {
    double a_x = force_x / mass;
    double a_y = force_y / mass;

    xxVel += dt * a_x;
    yyVel += dt * a_y;

    xxPos += dt * xxVel;
    yyPos += dt * yyVel;
  }

  public void draw() {
    StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
  }
}
