package bigdata.model;

import java.util.List;

public class RealTimeResult {

	private String metricKey;
	private List<Double> point;

	public RealTimeResult(String metricKey, List<Double> point) {
		super();
		this.metricKey = metricKey;
		this.point = point;
	}

	public String getMetricKey() {
		return metricKey;
	}

	public List<Double> getPoint() {
		return this.point;
	}

	public void setMetricKey(String metricKey) {
		this.metricKey = metricKey;
	}

	public void setX(List<Double> point) {
		this.point = point;
	}

}
