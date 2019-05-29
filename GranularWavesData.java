class GranularWavesData {
  Mass[] masses;
  Spring[] springs;
  double dt;
  int cursorX;
  int cursorY;
  double width;

  GranularWavesData(double mass, int N, double dt, double width) {
    this.dt = dt;
    int numSprings = 2*(N-2)*(N-3) + 4*(N-2);
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

    // interior vertical springs
    for( int i = 1; i < N-2; i++) {
      for(int j=1; j < N-1; j++) {
        springs[idx] = new Spring( masses[N*i+j], masses[N*(i+1)+j], dx, 1.0);
        idx++;
      }
    }

    // interior horizontal springs
    for( int i = 1; i < N-1; i++) {
      for(int j = 1; j < N-2; j++) {
        springs[idx] = new Spring( masses[N*i+j], masses[N*i+(j+1)], dx, 1.0);
        idx++;
      }
    }

    // left and right side springs
    for( int i = 1; i < N-1; i++) {
      springs[idx] = new Spring( masses[N*i], masses[N*i+1], dx, 1.0);
      idx++;
      springs[idx] = new Spring( masses[N*i+(N-2)], masses[N*i+(N-1)], dx, 1.0);
      idx++;
    }

    // upper and lower side springs
    for( int j = 1; j < N-1; j++) {
      springs[idx] = new Spring( masses[j], masses[N+j], dx, 1.0);
      idx++;
      springs[idx] = new Spring( masses[N*(N-2)+j], masses[N*(N-1)+j], dx, 1.0);
      idx++;
    }
  }

  void update() {
  }
}
