package bigdata.model;

import java.util.ArrayList;
import java.util.List;

public class BarChart {
	
	private List<Integer> series;
	
	private List<String> categories;
	
	public BarChart() {
		series = new ArrayList<Integer>();
		categories = new ArrayList<String>();
	}
	
	public void addItem(String name, int y) {
		series.add(y);
		categories.add(name);
	}
	

}
