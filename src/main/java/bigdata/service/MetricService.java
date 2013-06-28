package bigdata.service;

import java.util.List;

import bigdata.model.BatchResult;
import bigdata.model.RealTimeResult;

public interface MetricService {

	List<BatchResult> getBatchMetrics(String metricID);
	
	List<RealTimeResult> getRealTimeMetrics(String metricID, String minute);
	
}
