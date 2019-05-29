class Mass {
  double mass;
  double posX;
  double posY;
  double vx;
  double vy;
  boolean fixed;

  Mass(double mass, double posX, double posY, double vx, double vy, boolean fixed) {
    this.mass = mass;
    this.posX = posX;
    this.posY = posY;
    this.vx = vx;
    this.vy = vy;
    this.fixed = fixed;
  }
}
