package module5;

import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.AbstractShapeMarker;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.MultiMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import parsing.ParseFeed;
import processing.core.PApplet;

/** EarthquakeCityMap
 * An application with an interactive map displaying earthquake data.
 * Author: UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 * Date: July 17, 2015
 * */
public class EarthquakeCityMap extends PApplet {
	
	// We will use member variables, instead of local variables, to store the data
	// that the setup and draw methods will need to access (as well as other methods)
	// You will use many of these variables, but the only one you should need to add
	// code to modify is countryQuakes, where you will store the number of earthquakes
	// per country.
	
	// You can ignore this.  It's to get rid of eclipse warnings
	private static final long serialVersionUID = 1L;
	
	//feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";
	
	// The files containing city names and info and country names and info
	private String cityFile = "city-data.json";
	private String countryFile = "countries.geo.json";
	
	// The map
	private UnfoldingMap map;
	
	// Markers for each city
	private List<Marker> cityMarkers;
	// Markers for each earthquake
	private List<Marker> quakeMarkers;

	// A List of country markers
	private List<Marker> countryMarkers;
	
	// NEW IN MODULE 5
	private CommonMarker lastSelected;
	private CommonMarker lastClicked;	
	
	public void setup() {		
		// (1) Initializing canvas and map tiles
		size(1800, 1400, OPENGL);
//		map = new UnfoldingMap(this, 400, 100, 1300, 1200, new Google.GoogleMapProvider());
		map = new UnfoldingMap(this, 400,100,1300,1200, new OpenStreetMap.OpenStreetMapProvider());
			// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
		    //earthquakesURL = "2.5_week.atom";
		
		MapUtils.createDefaultEventDispatcher(this, map);		
		
		// (2) Reading in earthquake data and geometric properties
	    //     STEP 1: load country features and markers
		List<Feature> countries = GeoJSONReader.loadData(this, countryFile);
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		
		//     STEP 2: read in city data
		List<Feature> cities = GeoJSONReader.loadData(this, cityFile);
		cityMarkers = new ArrayList<Marker>();
		for(Feature city : cities) {
		  cityMarkers.add(new CityMarker(city));
		}
	    
		//     STEP 3: read in earthquake RSS feed
	    List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
	    quakeMarkers = new ArrayList<Marker>();
	    
	    for(PointFeature feature : earthquakes) {
		  //check if LandQuake
		  if(isLand(feature)) {
		    quakeMarkers.add(new LandQuakeMarker(feature));
		  }
		  // OceanQuakes
		  else {
		    quakeMarkers.add(new OceanQuakeMarker(feature));
		  }
	    }

	    // could be used for debugging
	    printQuakes();
	 		
	    // (3) Add markers to map
	    //     NOTE: Country markers are not added to the map.  They are used
	    //           for their geometric properties
	    map.addMarkers(quakeMarkers);
	    map.addMarkers(cityMarkers);
	    
	}  // End setup
	
	
	public void draw() {
		background(0);
		map.draw();
		addKey();		
	}
	
	/** Event handler that gets called automatically when the 
	 * mouse moves.
	 */
	@Override
	public void mouseMoved() {
		// clear the last selection
		if (lastSelected != null) {
			lastSelected.setSelected(false);
			lastSelected = null;		
		}
		selectMarkerIfHover(quakeMarkers);
		selectMarkerIfHover(cityMarkers);
	}
	
	// If there is a marker under the cursor, and lastSelected is null 
	// set the lastSelected to be the first marker found under the cursor
	// Make sure you do not select two markers.
	// 
	private void selectMarkerIfHover(List<Marker> markers)	{
		// TODO: Implement this method
		for (Marker marker : markers) {
			if (marker.isInside(map, mouseX, mouseY)&&(lastSelected == null)) {
				lastSelected = (CommonMarker) marker;
				lastSelected.setSelected(true);
				break;
			}
		}
	}
	
	/** The event handler for mouse clicks
	 * It will display an earthquake and its threat circle of cities
	 * Or if a city is clicked, it will display all the earthquakes 
	 * where the city is in the threat circle
	 */
	@Override
	public void mouseClicked()	{	
		// Hint: You probably want a helper method or two to keep this code
		// from getting too long/disorganized

		if (lastClicked !=null) {
			unhideMarkers();
			lastClicked = null;
		} else if (lastClicked == null) {
			selectMarkerIfClicked(quakeMarkers);
			selectMarkerIfClicked(cityMarkers);					
		}
	}
	private void selectMarkerIfClicked(List<Marker> markers) {	
		for (Marker marker : markers) {
			if (marker.isSelected()==true &&(lastClicked == null)) {
				lastClicked = (CommonMarker) marker;
				lastClicked.setClicked(true);
				lastClicked.setHidden(false);				
			}else {
				marker.setHidden(true);
			}
		}
	}				

/*	private void selectQuakeMarkerIfClicked(List<Marker> markers) {				
		for (Marker marker : markers) {			
//			(threat distance) double dist = ((EarthquakeMarker) marker).threatCircle();
//			(distance between other marker and location a) double d = otherMarker.getDistanceTo(a)				
			if (marker.isInside(map, mouseX, mouseY)&&(lastClicked == null)) {
				lastClicked = (CommonMarker) marker;
				lastClicked.setClicked(true);
				lastClicked.setHidden(false);
//				double dist = ((EarthquakeMarker) marker).threatCircle();
//				Location a = marker.getLocation();
//			} else if(marker.getDistanceTo(a)<=dist) {
//				marker.setHidden(false);
			} else {
				marker.setHidden(true);
			}		
		}
	}
*/	
	// loop over and unhide all markers
	private void unhideMarkers() {
		for(Marker marker : quakeMarkers) {
			marker.setHidden(false);
		}
			
		for(Marker marker : cityMarkers) {
			marker.setHidden(false);
		}
	}
	
	// helper method to draw key in GUI
	private void addKey() {	
		// Remember you can use Processing's graphics methods here
		fill(255, 250, 240);
		
		int xbase = 50;
		int ybase = 100;
		
		rect(xbase, ybase, 300, 500);
		
		fill(0);
		textAlign(LEFT, CENTER);
		textSize(24);
		text("Earthquake Key", xbase+50, ybase+50);
		
		fill(150, 30, 30);
		int tri_xbase = xbase + 70;
		int tri_ybase = ybase + 100;
		triangle(tri_xbase, tri_ybase-CityMarker.TRI_SIZE, tri_xbase-CityMarker.TRI_SIZE, 
				tri_ybase+CityMarker.TRI_SIZE, tri_xbase+CityMarker.TRI_SIZE, 
				tri_ybase+CityMarker.TRI_SIZE);

		fill(0, 0, 0);
		textAlign(LEFT, CENTER);
		text("City Marker", tri_xbase + 30, tri_ybase);
		
		text("Land Quake", xbase+100, ybase+140);
		text("Ocean Quake", xbase+100, ybase+180);
		text("Size ~ Magnitude", xbase+50, ybase+220);
		
		fill(255, 255, 255);
		ellipse(xbase+70, 
				ybase+140, 
				20, 
				20);
		rect(xbase+70-10, ybase+180-10, 20, 20);
		
		fill(color(255, 255, 0));
		ellipse(xbase+70, ybase+280, 24, 24);
		fill(color(0, 0, 255));
		ellipse(xbase+70, ybase+320, 24, 24);
		fill(color(255, 0, 0));
		ellipse(xbase+70, ybase+360, 24, 24);
		
		textAlign(LEFT, CENTER);
		fill(0, 0, 0);
		text("Shallow", xbase+100, ybase+280);
		text("Intermediate", xbase+100, ybase+320);
		text("Deep", xbase+100, ybase+360);

		text("Past hour", xbase+100, ybase+400);
		
		fill(255, 255, 255);
		int centerx = xbase+70;
		int centery = ybase+400;
		ellipse(centerx, centery, 24, 24);

		strokeWeight(2);
		line(centerx-16, centery-16, centerx+16, centery+16);
		line(centerx-16, centery+16, centerx+16, centery-16);			
	}	
	
	// Checks whether this quake occurred on land.  If it did, it sets the 
	// "country" property of its PointFeature to the country where it occurred
	// and returns true.  Notice that the helper method isInCountry will
	// set this "country" property already.  Otherwise it returns false.	
	
	private boolean isLand(PointFeature earthquake) {		
		// IMPLEMENT THIS: loop over all countries to check if location is in any of them
		// If it is, add 1 to the entry in countryQuakes corresponding to this country.
		for (Marker country : countryMarkers) {
			if (isInCountry(earthquake, country)) {
				return true;
			}
		}
		
		// not inside any country
		return false;
	}
	
	// prints countries with number of earthquakes
	private void printQuakes() {
		int totalWaterQuakes = quakeMarkers.size();
		for (Marker country : countryMarkers) {
			String countryName = country.getStringProperty("name");
			int numQuakes = 0;
			for (Marker marker : quakeMarkers)
			{
				EarthquakeMarker eqMarker = (EarthquakeMarker)marker;
				if (eqMarker.isOnLand()) {
					if (countryName.equals(eqMarker.getStringProperty("country"))) {
						numQuakes++;
					}
				}
			}
			if (numQuakes > 0) {
				totalWaterQuakes -= numQuakes;
				System.out.println(countryName + ": " + numQuakes);
			}
		}
		System.out.println("OCEAN QUAKES: " + totalWaterQuakes);
	}
	
	
	
	// helper method to test whether a given earthquake is in a given country
	// This will also add the country property to the properties of the earthquake feature if 
	// it's in one of the countries.
	// You should not have to modify this code
	private boolean isInCountry(PointFeature earthquake, Marker country) {
		// getting location of feature
		Location checkLoc = earthquake.getLocation();

		// some countries represented it as MultiMarker
		// looping over SimplePolygonMarkers which make them up to use isInsideByLoc
		if(country.getClass() == MultiMarker.class) {
				
			// looping over markers making up MultiMarker
			for(Marker marker : ((MultiMarker)country).getMarkers()) {
					
				// checking if inside
				if(((AbstractShapeMarker)marker).isInsideByLocation(checkLoc)) {
					earthquake.addProperty("country", country.getProperty("name"));
						
					// return if is inside one
					return true;
				}
			}
		}
			
		// check if inside country represented by SimplePolygonMarker
		else if(((AbstractShapeMarker)country).isInsideByLocation(checkLoc)) {
			earthquake.addProperty("country", country.getProperty("name"));
			
			return true;
		}
		return false;
	}

}
