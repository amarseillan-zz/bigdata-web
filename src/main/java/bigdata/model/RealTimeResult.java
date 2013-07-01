package bigdata.model;

import java.util.List;

public class RealTimeResult {

	private String name;
	private List<List<Long>> data;

	public RealTimeResult(String metricKey, List<List<Long>> counts) {
		this.name = metricKey;
		this.data = counts;
	}

	public String getMetricKey() {
		return name;
	}

}
