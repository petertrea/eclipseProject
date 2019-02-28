package guimodule;

import processing.core.PApplet;

public class MyDisplay extends PApplet{
	public void setup() {
		size(400,400);
		background(200,200,200);
		
	}
	public void draw() {
		fill(255,255,0);			//fill yellow color
		ellipse(200,200,390,390);	//draw face (a round ellipse)
		fill(0,0,0);				//fill black color
		ellipse(120,130, 50,70);	//draw eyes (two small ellipse)
		ellipse(280,130, 50,70);
		noFill();					//background color
		arc(200,280,100,100,0,PI);	//draw mouth (arc)
	}

}
