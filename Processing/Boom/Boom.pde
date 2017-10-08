//------------------------------------------------------------------
// Boom.pde
// CMPS 5J
//------------------------------------------------------------------

int n = 20;         // grid size
float prob = 0.15;  // probability that a cell has a bomb
float side;         // width and height of each cell
Cell[][] C = new Cell[n][n];  // array of cells
boolean gameOver = false;

void setup() {
  int i, j;
  size(500, 500);
  smooth();
  
  side = width/float(n);
  
  // allocate individual cells
  for (i=0; i<n; i++) {
    for (j=0; j<n; j++) {
      C[i][j] = new Cell(side*i, side*j, side, prob);
    }
  }
  
  // determine the bomb count for each cell
  for (i=0; i<n; i++) {
    for (j=0; j<n; j++) {
      if ( C[i][j].hasBomb ) {
        if ( i>0   && j>0   ) C[i-1][j-1].incrementBombCount();
        if ( i>0            ) C[i-1][j].incrementBombCount();
        if ( i>0   && j<n-1 ) C[i-1][j+1].incrementBombCount();
        if (          j>0   ) C[i][j-1].incrementBombCount();
        if (          j<n-1 ) C[i][j+1].incrementBombCount();
        if ( i<n-1 && j>0   ) C[i+1][j-1].incrementBombCount();
        if ( i<n-1          ) C[i+1][j].incrementBombCount();
        if ( i<n-1 && j<n-1 ) C[i+1][j+1].incrementBombCount();
      }
    }
  }
}

void draw() {
  // draw cells
  for (int i=0; i<n; i++) {
    for (int j = 0; j<n; j++) {
      C[i][j].display();
    }
  }
  // stop if game over
  if( gameOver ){
    goBoom();
    noLoop();
  }
}

void mousePressed() {
  for (int i=0; i<n; i++) {
    for (int j=0; j<n; j++) {
      if ( C[i][j].mouseOn() ) {
        if ( mouseButton==LEFT && C[i][j].hasBomb ) {
          C[i][j].state = 3;  // explode
          gameOver = true;
        }
        else if ( mouseButton==LEFT && C[i][j].state==0 ) {
          C[i][j].state = 2;  // open
        }
        else if( mouseButton==RIGHT && C[i][j].state==0 ){
          C[i][j].state = 1;  // mark
        }
        else if( mouseButton==RIGHT && C[i][j].state==1 ){
          C[i][j].state = 0;  // close
        }
      }
    }
  }
}

void goBoom() {
  fill(255, 0, 0);
  textSize(85);
  textAlign(CENTER);
  text("BOOM!", width/2, height/2);
}

