class Spring {
  Mass mass1;
  Mass mass2;
  double restingLength;
  double springConstant;

  Spring(Mass mass1, Mass mass2, double restingLength, double springConstant) {
    this.mass1 = mass1;
    this.mass2 = mass2;
    this.restingLength = restingLength;
    this.springConstant = springConstant;
  }

  double dist() {
    return Math.sqrt( Math.pow(mass1.posX-mass2.posX,2) + Math.pow(mass1.posY-mass2.posY,2) );
  }

  double forceScalar() {
    return springConstant * (restingLength-dist());
  }

  double forceX() {
    return forceScalar()*nx();
  }

  double forceY() {
    return forceScalar()*ny();
  }

  double nx() {
    return (mass1.posX-mass2.posX)/dist();
  }

  double ny() {
    return (mass1.posY-mass2.posY)/dist();
  }
}
