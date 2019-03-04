package Practise;

import java.util.*;
import processing.core.*;

//Unfolding libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.utils.MapUtils;

//OPENGL
import de.fhpotsdam.unfolding.core.Coordinate;
import de.fhpotsdam.unfolding.geo.Location;

//Unfolding Functions
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;

//Unfolding Providers
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;


public class MyDefaultEventExample extends PApplet {
	
	private UnfoldingMap map;
	
	public void setup() {
		size(1900, 1200, OPENGL);
		
		//map = new UnfoldingMap(this, 400,100,1400,1000, new Google.GoogleMapProvider());
		map = new UnfoldingMap(this, 400,100,1400,1000, new Microsoft.RoadProvider());	
		//map = new UnfoldingMap(this, 400,100,1400,1000, new OpenStreetMap.OpenStreetMapProvider());
		
		map.zoomToLevel(3);
		MapUtils.createDefaultEventDispatcher(this, map);							
	}	
	
	public void draw() {
		background(10);
		keyPressed();
		mousePressed ();
		mouseClicked ();
		mouseReleased();
		map.draw();	
		drawButtons();
	}
	//Add Button Functionality
	public void mouseReleased() {				//Override Methods to customize them
		if (mouseX > 600 && mouseX < 625		//mouseX mouseY inherited from pApplet
		&& mouseY > 100 && mouseY < 125) {		//check if inside white PB area
		background(255,255,255);
		} else if (mouseX > 600 && mouseX < 625
		&& mouseY > 150 && mouseY < 175){		//check if inside grey PB area
		background(100,100,100);
		}
	}
	//Draw Button Shape, no real function here
	private void drawButtons() {
		fill(255,255,255);
		rect(600,100,25, 25);
		fill(100,100,100);
		rect(600,150,25,25);
	}
	//Key Press Functionality
	public void keyPressed() {			//Override Methods
		if (key == 'w') {
		background(255,255,255);
		}
	}
}
