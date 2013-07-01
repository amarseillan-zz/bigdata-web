package bigdata.service.impl;

import org.springframework.util.Assert;

import bigdata.db.MySqlMetricDAO;
import bigdata.model.BarChart;
import bigdata.model.PieChart;
import bigdata.model.RealTimeChart;
import bigdata.service.MetricService;

public class MetricServiceImpl implements MetricService{

	@Override
	public RealTimeChart getRealTimeMetrics(String metricID) {
		Assert.notNull(metricID);
		
		MySqlMetricDAO metricDAO = MySqlMetricDAO.getInstance();
		return metricDAO.getMetrics(metricID);
	}

	@Override
	public BarChart getTop10Channels() {
		MySqlMetricDAO metricDAO = MySqlMetricDAO.getInstance();
		return metricDAO.getTop10Channels();
	}

	@Override
	public BarChart getTop10Categories() {
		MySqlMetricDAO metricDAO = MySqlMetricDAO.getInstance();
		return metricDAO.getTop10Categories();
	}
	
	@Override
	public BarChart getWorstShows() {
		MySqlMetricDAO metricDAO = MySqlMetricDAO.getInstance();
		return metricDAO.getWorstShows();
	}

	@Override
	public RealTimeChart getAvgDurationChannel() {
		MySqlMetricDAO metricDAO = MySqlMetricDAO.getInstance();
		return metricDAO.getAvgDurationChannel();
	}

	@Override
	public RealTimeChart getAvgDurationCategory() {
		MySqlMetricDAO metricDAO = MySqlMetricDAO.getInstance();
		return metricDAO.getAvgDurationCategory();
	}

	@Override
	public BarChart getTopChannelAds() {
		MySqlMetricDAO metricDAO = MySqlMetricDAO.getInstance();
		return metricDAO.getTopChannelAds();
	}

	@Override
	public PieChart getAudiencePerType() {
		MySqlMetricDAO metricDAO = MySqlMetricDAO.getInstance();
		return metricDAO.getAudiencePerType();
	}

	@Override
	public PieChart getAudiencePerFamilyGroup() {
		MySqlMetricDAO metricDAO = MySqlMetricDAO.getInstance();
		return metricDAO.getAudiencePerFamilyGroup();
	}
	
	@Override
	public Long getTime() {
		MySqlMetricDAO metricDAO = MySqlMetricDAO.getInstance();
		return metricDAO.getTime();
	}

}
