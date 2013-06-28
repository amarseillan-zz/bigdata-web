package bigdata.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import bigdata.db.HiveMetricDAO;
import bigdata.db.MySqlMetricDAO;
import bigdata.model.BatchResult;
import bigdata.model.RealTimeResult;
import bigdata.service.MetricService;

public class MetricServiceImpl implements MetricService{

	@Override
	public List<BatchResult> getBatchMetrics(String metricID) {
		Assert.notNull(metricID);
		
			HiveMetricDAO metricDAO = HiveMetricDAO.getInstance();
			
		return new ArrayList<BatchResult>();
	}

	@Override
	public List<RealTimeResult> getRealTimeMetrics(String metricID, String minute) {
		Assert.notNull(metricID);
		Assert.notNull(minute);
		
		MySqlMetricDAO metricDAO = MySqlMetricDAO.getInstance();
		List<RealTimeResult> realTimeResults = metricDAO.getMetrics(metricID, minute);
		return realTimeResults;
	}

}
