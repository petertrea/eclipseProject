package guimodule;

import processing.core.*;

public class MypApplet extends PApplet {
	private String URL = "http://cseweb.ucsd.edu/~minnes/palmTrees.jpg";
	private PImage img;
	
	public void setup() {
		size(800,800);		//set canvas size
		background(255);	//set canvas color
		stroke(0);			//set pen color
		img = loadImage(URL, "jpg");
		img.resize(0,height);	//resize loaded image to full heigth of cavas
		image(img,0,0);	//display image
	}
	
	public void draw() {
		int[] color = sunColorSec(second());		// calculate color code for sun
		
		fill(color[0],color[1],color[2]);
		ellipse(width/4,height/5, width/5, height/5);
	}
	
	public int[] sunColorSec(float seconds) {
		int[] rgb = new int [3];
		//Scale the brigthness of the yellow based on the seconds. 
		// 30 seconds is black. 0 seconds is bright yellow
		float diffFrom30 = Math.abs(30-seconds);
		
		float ratio = diffFrom30/30;
		rgb[0] = (int)(255*ratio);
		rgb[1] = (int)(255*ratio);
		rgb[2] = 0;
		return rgb;
	}

}
