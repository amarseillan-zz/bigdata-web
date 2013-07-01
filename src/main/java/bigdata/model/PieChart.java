package bigdata.model;

import java.util.ArrayList;
import java.util.List;

public class PieChart {
	
	private List<PieChartItem> series;
	
	public PieChart() {
		series = new ArrayList<PieChartItem>();
	}
	
	public void addItem(String name, int hits) {
		series.add(new PieChartItem(name, hits));
	}

}
