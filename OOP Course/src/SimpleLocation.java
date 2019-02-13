
public class SimpleLocation {
	public double latitude;
	public double longitude;
	public SimpleLocation(double lat, double lon) {
		this.latitude = lat;
		this.longitude = lon;
	}
	public double diatance(SimpleLocation other) {
		return getDist(this.latitude,this.longitude,
				other.latitude,other.longitude);		
	}

}
