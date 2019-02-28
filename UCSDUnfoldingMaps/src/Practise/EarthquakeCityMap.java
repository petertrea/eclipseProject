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


public class EarthquakeCityMap extends PApplet {
	
	// You can ignore this.  It's to keep eclipse from generating a warning.
		//private static final long serialVersionUID = 1L;
		// IF YOU ARE WORKING OFFLINE, change the value of this variable to true
		//private static final boolean offline = false;
		
		// Less than this threshold is a light earthquake
		//public static final float THRESHOLD_MODERATE = 5;
		// Less than this threshold is a minor earthquake
		//public static final float THRESHOLD_LIGHT = 4;

		/** This is where to find the local tiles, for working without an Internet connection */
		//public static String mbTilesString = "blankLight-1-3.mbtiles";
		
				
		//feed with magnitude 2.5+ Earthquakes
		//private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";

	private UnfoldingMap map;
	
	public void setup() {
		size(1900, 1200, OPENGL);
		
		//map = new UnfoldingMap(this, 400,100,1400,1000, new Google.GoogleMapProvider());
		map = new UnfoldingMap(this, 400,100,1400,1000, new Microsoft.RoadProvider());	
		//map = new UnfoldingMap(this, 400,100,1400,1000, new OpenStreetMap.OpenStreetMapProvider());
		
		map.zoomToLevel(2);
		MapUtils.createDefaultEventDispatcher(this, map);	
				
	    PointFeature valdiviaEq = new PointFeature(new Location(-38.14f,-73.03f));
	    valdiviaEq.addProperty("title", "Valdivia, Chile");
	    valdiviaEq.addProperty("magnitude", "9.5");
	    valdiviaEq.addProperty("date", "March 22, 1960");
	    valdiviaEq.addProperty("year", 1960);
	    
	    PointFeature alaskaEq = new PointFeature(new Location(61.02f,-147.65f));
	    alaskaEq.addProperty("title", "1964 Great Alaska Earthquake");
	    alaskaEq.addProperty("magnitude", "9.2");
	    alaskaEq.addProperty("date", "March 28, 1964"); 
	    alaskaEq.addProperty("year", 1964);

	    PointFeature sumatraEq = new PointFeature(new Location(3.30f,95.78f));
	    sumatraEq.addProperty("title", "Off the West Coast of Northern Sumatra");
	    sumatraEq.addProperty("magnitude", "9.1");
	    sumatraEq.addProperty("date", "February 26, 2004");
	    sumatraEq.addProperty("year", 2004);

	    
	    PointFeature japanEq = new PointFeature(new Location(38.322f,142.369f));
	    japanEq.addProperty("title", "Near the East Coast of Honshu, Japan");
	    japanEq.addProperty("magnitude", "9.0");
	    japanEq.addProperty("date", "March 11, 2011");
	    japanEq.addProperty("year", 2011);

	    
	    PointFeature kamchatkaEq = new PointFeature(new Location(52.76f,160.06f));
	    kamchatkaEq.addProperty("title", "Kamchatka");
	    kamchatkaEq.addProperty("magnitude", "9.0");
	    kamchatkaEq.addProperty("date", "November 4, 1952");
	    kamchatkaEq.addProperty("year", 1952);

	    
	    List<PointFeature> bigEarthquakes = new ArrayList<PointFeature>();
	    bigEarthquakes.add(valdiviaEq);
	    bigEarthquakes.add(alaskaEq);
	    bigEarthquakes.add(sumatraEq);
	    bigEarthquakes.add(japanEq);
	    bigEarthquakes.add(kamchatkaEq);
	    
	    List<Marker> markers = new ArrayList<Marker>();
		for (PointFeature eq: bigEarthquakes) {
			markers.add(new SimplePointMarker(eq.getLocation(),eq.getProperties()));
		}
			
		map.addMarkers(markers);
		
		int yellow = color(255, 255, 0);
	    int gray = color(150,150,150);
	    
	    for (Marker mk :markers) {
	    	if ( (int) mk.getProperty("year") > 2000 ) {
	    		mk.setColor(yellow);
	    	}
	    	else {
	    		mk.setColor(gray);
	    	}
	    }
	}
	
	
	public void draw() {
		background(10);
		map.draw();
		//addKey();
	}

}
