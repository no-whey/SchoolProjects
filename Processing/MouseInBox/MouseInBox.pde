//Sean Gordon
//CMPS 5J
//pa5 MouseIsBox.pde

//Global Variable Declarations
int i, j, n, m, c;
int xpos, ypos;
float x, y, w, h;

void setup() {
  size(500, 500);
  smooth();
  background(255);
  n = 25; //column #
  m = 25; //row #
  x = y = 0; //top left corner of each box
  w = width/float(n);
  h = height/float(m);
  c = 255;
  rectMode(CORNER);
  //Lines making the grid
  for (i=0; i<n; i++) {
    for (j=0; j<m; j++) {
      fill(255);
      rect(x+w*i, y+h*j, w, h);
    }
  }
}

void draw() {
  if (mousePressed) {
    drawPattern(mouseX, mouseY);
  }
}

void keyPressed() {
  setup();
}

void drawPattern(int xpos, int ypos) {
  fill(255-c);
  rect(((xpos-20)/20)*20, ((ypos-20)/20)*20, 20, 20);
  rect(((xpos+20)/20)*20, ((ypos-20)/20)*20, 20, 20);
  rect(((xpos)/20)*20, ((ypos)/20)*20, 20, 20);
  rect(((xpos-20)/20)*20, ((ypos+20)/20)*20, 20, 20);
  rect(((xpos+20)/20)*20, ((ypos+20)/20)*20, 20, 20);
}

//621
//4 8 15 16 23 42
