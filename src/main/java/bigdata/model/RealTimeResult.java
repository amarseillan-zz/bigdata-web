package bigdata.model;

public class RealTimeResult {
	private String metricKey;
	private String minute;
	private String quantity;
	
	public RealTimeResult( String metricKey, String minute, String quantity) {
		this.metricKey = metricKey;
		this.minute = minute;
		this.quantity = quantity;
	}

	public String getMetricKey() {
		return metricKey;
	}

	public String getMinute() {
		return minute;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setMetricKey(String metricKey) {
		this.metricKey = metricKey;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
}
