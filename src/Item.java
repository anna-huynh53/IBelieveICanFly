
public interface Item {
	
	public static final String COIN = "coin";
	public static final String CHEST = "chest";
	
	Point getLocation();
	
	void setLocation(Point p);
}
