package bigdata.model;

import java.util.List;

public class RealTimeChart {
	
	private String title;
	
	private List<RealTimeResult> series;
	
	
	public RealTimeChart(String title, List<RealTimeResult> series) {
		this.title = title;
		this.series = series;
	}

}
