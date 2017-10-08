//Sean Gordon
//CMPS 5J
//pa4 - ColorButtons

//Variables Declared
boolean redOn = false;
boolean greenOn = false;
boolean blueOn = false;
boolean mouseInRed, mouseInGreen, mouseInBlue;
int redX, redY, greenX, greenY, blueX, blueY, w, h;
int r = 0, g = 0, b = 0;

void setup() {
  size(500, 500);
  smooth();
  redX = width/5;
  redY = height/2;
  greenX = width/2;
  greenY = height/2;
  blueX = width*4/5;
  blueY = height/2;
  w = 100;
  h = 100;
  rectMode(CENTER);
}
void draw() {
  //Background Color
  if (redOn)
    r = 255;
  else
    r = 0;
  if (greenOn)
    g = 255;
  else
    g = 0;
  if (blueOn)
    b = 255;
  else
    b = 0;
  background(r, g, b);

  //Grey Border on Buttons
  stroke(170);
  strokeWeight(12);

  //Red Button
  fill(255, 0, 0);
  rect(redX, redY, w, h);

  //Green Button
  fill(0, 255, 0);
  rect(greenX, greenY, w, h);

  //Blue Button
  fill(0, 0, 255);
  rect(blueX, blueY, w, h);

  //Inner Border to tell if Button is on
  stroke(0);
  strokeWeight(2);
  noFill();
  if (redOn)
    rect(redX, redY, w, h);
  if (greenOn)
    rect(greenX, greenY, w, h);
  if (blueOn)
    rect(blueX, blueY, w, h);
}

void mousePressed() {
  mouseInRed = (redX-w/2<mouseX) && (redX+w/2>mouseX) && (redY-h/2<mouseY) && (redY+h/2>mouseY);
  if (mouseInRed) redOn = !redOn;
  mouseInGreen = (greenX-w/2<mouseX) && (greenX+w/2>mouseX) && (greenY-h/2<mouseY) && (greenY+h/2>mouseY);
  if (mouseInGreen) greenOn = !greenOn;
  mouseInBlue = (blueX-w/2<mouseX) && (blueX+w/2>mouseX) && (blueY-h/2<mouseY) && (blueY+h/2>mouseY);
  if (mouseInBlue) blueOn = !blueOn;
}

//4 8 15 16 23 42
