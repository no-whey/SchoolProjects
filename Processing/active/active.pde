//Sean Gordon
//CMPS 5J
//pa3 - active

//variable declarations
float lineX1 = 0;
float lineY1 = 0;
float lineX2 = 0;
float lineY2 = 50;

void setup() {
  size(800, 600);
  background(0, 0, 255);
  smooth();
}

void draw() {
  background(0, 0, 255);

  //Frowny Face (Changes through user input)

  //Face
  stroke(0);
  strokeWeight(3);
  fill(mouseX, mouseY, 0);
  ellipse (mouseX, mouseY, 100, 100);

  //Mouth
  arc(mouseX, mouseY+25, 45, 20, PI, TWO_PI);

  //Eyes
  fill(0);
  strokeWeight(1);
  ellipse(mouseX-15, mouseY-10, 10, 20);
  ellipse(mouseX+15, mouseY-10, 10, 20);

  //HEY LOOK IT'S RAIN (Changes frame by frame)
  stroke(0);
  strokeWeight(3);
  line(lineX1, lineY1, lineX2, lineY2);
  line(lineX1 + 50, lineY1, lineX2 + 50, lineY2);
  line(lineX1 + 100, lineY1, lineX2 + 100, lineY2);
  line(lineX1 + 150, lineY1, lineX2 + 150, lineY2);
  line(lineX1 + 200, lineY1, lineX2 + 200, lineY2);
  line(lineX1 + 250, lineY1, lineX2 + 250, lineY2);
  line(lineX1 + 300, lineY1, lineX2 + 300, lineY2);
  line(lineX1 + 350, lineY1, lineX2 + 350, lineY2);
  line(lineX1 + 400, lineY1, lineX2 + 400, lineY2);
  line(lineX1 + 450, lineY1, lineX2 + 450, lineY2);
  line(lineX1 + 500, lineY1, lineX2 + 500, lineY2);
  line(lineX1 + 550, lineY1, lineX2 + 550, lineY2);
  line(lineX1 + 600, lineY1, lineX2 + 600, lineY2);
  line(lineX1 + 650, lineY1, lineX2 + 650, lineY2);
  line(lineX1 + 700, lineY1, lineX2 + 700, lineY2);
  line(lineX1 + 750, lineY1, lineX2 + 750, lineY2);
  line(lineX1 + 790, lineY1, lineX2 + 790, lineY2);

  if (lineY1 == 600) {
    lineY1 = 0;
    lineY2 = 50;
  }
  
  //Moving the Rain
  lineY1 = (lineY1 + 1);
  lineY2 = (lineY2 + 1);
}

//Pressing the Mouse

void mousePressed() {
  println("I don't like the rain.");
}

//Thanks for Watching!
