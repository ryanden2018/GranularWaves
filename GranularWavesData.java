class GranularWavesData {
  Mass[] masses;
  Spring[] springs;
  double dt;
  int cursorX;
  int cursorY;
  double width;

  GranularWavesData(double mass, int N, double dt, double width) {
    this.dt = dt;
    //int numSprings = 2*(N-2)*(N-3) + 2*(N-2)*(N-3) + 4*(N-2) + (N-2);
    int numSprings = (N-1)*(N-1)*4;
    springs = new Spring[numSprings];
    masses = new Mass[N*N];
    cursorX = (int) (width/2);
    cursorY = (int) (width/2);
    this.width = width;
    double dx = width/(N-1);

    // initialize all masses with proper assignment of `fixed` parameter
    for(int i = 0; i < N; i++) {
      for(int j=0; j<N; j++) {
        masses[N*i+j] = new Mass(1.0, dx*(1.0*i), dx*(1.0*j), 
          0.0, 0.0, (i==0)||(j==0)||(i==N-1)||(j==N-1) );
      }
    }

    int idx = 0; // spring index

    for( int i = 0; i < N-1; i++) {
      for(int j = 0; j < N-1; j++) {
        springs[idx] = new Spring( masses[N*i+j], masses[N*i+(j+1)], dx, 5.0);
        idx++;
        springs[idx] = new Spring( masses[N*i+j], masses[N*(i+1)+j], dx, 5.0);
        idx++;
        springs[idx] = new Spring( masses[N*i+j], masses[N*(i+1)+(j+1)], dx, 5.0);
        idx++;
        springs[idx] = new Spring( masses[N*i+(j+1)], masses[N*(i+1)+j], dx, 5.0);
        idx++;
      }
    }

    // // interior vertical springs
    // for( int i = 1; i < N-2; i++) {
    //   for(int j=1; j < N-1; j++) {
    //     springs[idx] = new Spring( masses[N*i+j], masses[N*(i+1)+j], dx, 5.0);
    //     idx++;
    //     springs[idx] = new Spring( masses[N*i+j], masses[N*(i+1)+(j+1)], dx, 5.0);
    //     idx++;
    //     springs[idx] = new Spring( masses[N*i+(j+1)], masses[N*(i+1)+j], dx, 5.0);
    //     idx++;
    //   }
    // }

    // // interior horizontal springs
    // for( int i = 1; i < N-1; i++) {
    //   for(int j = 1; j < N-2; j++) {
    //     springs[idx] = new Spring( masses[N*i+j], masses[N*i+(j+1)], dx, 5.0);
    //     idx++;
    //   }
    // }

    // // left and right side springs
    // for( int i = 1; i < N-1; i++) {
    //   springs[idx] = new Spring( masses[N*i], masses[N*i+1], dx, 1.0);
    //   idx++;
    //   springs[idx] = new Spring( masses[N*i+(N-2)], masses[N*i+(N-1)], dx, 5.0);
    //   idx++;
    //   springs[idx] = new Spring( masses[N*i+(N-2)], masses[N*(i+1)+(N-1)],dx,5.0);
    //   idx++;
    // }

    // // upper and lower side springs
    // for( int j = 1; j < N-1; j++) {
    //   springs[idx] = new Spring( masses[j], masses[N+j], dx, 1.0);
    //   idx++;
    //   springs[idx] = new Spring( masses[N*(N-2)+j], masses[N*(N-1)+j], dx, 5.0);
    //   idx++;
    // }
  }

  // update the internal data by one timestep following Newton's laws
  void update() {
    for( int i= 0; i < masses.length; i++) {
      masses[i].posX += masses[i].vx*dt;
      masses[i].posY += masses[i].vy*dt;
    }

    for( int i = 0; i < springs.length; i++) {
      if( !springs[i].mass1.fixed ) {
        springs[i].mass1.vx += ( springs[i].forceX() + nx(springs[i].mass1)*Math.pow(dist(springs[i].mass1)/(width*Math.sqrt(2)),2) - 0.005*springs[i].mass1.vx ) * dt;
        springs[i].mass1.vy += ( springs[i].forceY() +  ny(springs[i].mass1)*Math.pow(dist(springs[i].mass1)/(width*Math.sqrt(2)),2) - 0.005*springs[i].mass1.vy ) * dt;
      }

      if( !springs[i].mass2.fixed ) {
        springs[i].mass2.vx += ( (-1)*springs[i].forceX() + nx(springs[i].mass2)*Math.pow(dist(springs[i].mass1)/(width*Math.sqrt(2)),2) - 0.005*springs[i].mass2.vx ) * dt;
        springs[i].mass2.vy += ( (-1)*springs[i].forceY() + ny(springs[i].mass2)*Math.pow(dist(springs[i].mass1)/(width*Math.sqrt(2)),2) - 0.005*springs[i].mass2.vy ) * dt;
      }
    }
  }

  double dist(Mass mass) {
    return Math.sqrt(Math.pow(mass.posX-cursorX,2)+Math.pow(mass.posY-cursorY,2));
  }

  double nx(Mass mass) {
    return (cursorX-mass.posX)/dist(mass);
  }

  double ny(Mass mass) {
    return (cursorY-mass.posY)/dist(mass);
  }
}
