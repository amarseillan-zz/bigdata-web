package bigdata.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class RealTimeChartBuilder {

	private String title;

	private Map<String, List<Data>> values;

	private Map<String, Long> totalCount;

	private Set<Long> timestamps;

	DateTimeFormatter formatter = DateTimeFormat.longTime();

	private List<RealTimeResult> series;

	public RealTimeChartBuilder() {
		this.timestamps = new HashSet<Long>();
		this.values = new HashMap<String, List<Data>>();
		this.totalCount = new HashMap<String, Long>();
		this.series = new ArrayList<RealTimeResult>();
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void addValue(String metricKey, long timestamp, int count) {
		if (!values.containsKey(metricKey)) {
			values.put(metricKey, new ArrayList<Data>());
		}
		values.get(metricKey).add(new Data(timestamp, count));
		if (!totalCount.containsKey(metricKey)) {
			totalCount.put(metricKey, (long) count);
		} else {
			totalCount.put(metricKey, totalCount.get(metricKey) + count);
		}
		timestamps.add(timestamp);
	}

	private RealTimeChart prepareChart(int limit) {
		int size = timestamps.size();
		List<KeyValue> keyvalues = new ArrayList<KeyValue>();
		for (Entry<String, Long> entry : totalCount.entrySet()) {
			keyvalues.add(new KeyValue(entry.getKey(), entry.getValue()));
		}
		Collections.sort(keyvalues);
		for (int i = 0; i < keyvalues.size() && i < limit; i++) {
			series.add(getRealTimeResult(keyvalues.get(i).key, size));
		}

		return new RealTimeChart(title, series);
	}

	private RealTimeResult getRealTimeResult(String metricKey, int size) {
		List<List<Long>> counts = new ArrayList<List<Long>>(size);
		for (Data data : values.get(metricKey)) {
			List<Long> xs = new ArrayList<Long>(2);
			xs.add(data.timestamp);
			xs.add((long) data.count);
			counts.add(xs);
		}
		Collections.sort(counts, new Comparator<List<Long>>() {
			public int compare(List<Long> s, List<Long> s2) {
				return s.get(0).compareTo(s2.get(0));
			}
		});
		return new RealTimeResult(metricKey, counts);
	}

	public RealTimeChart prepareChartNoLimit() {
		return prepareChart(Integer.MAX_VALUE);
	}

	public RealTimeChart prepareChartWithLimit(int limit) {
		return prepareChart(limit);
	}
	

	private class Data {
		public long timestamp;
		public int count;

		public Data(long timestamp, int count) {
			this.timestamp = timestamp;
			this.count = count;
		}
	}

	private class KeyValue implements Comparable<KeyValue> {
		public String key;
		public long sum;

		public KeyValue(String key, long sum) {
			this.key = key;
			this.sum = sum;
		}

		@Override
		public int compareTo(KeyValue that) {
			return (int) (that.sum - this.sum);
		}
	}

}
