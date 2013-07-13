package bigdata.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import bigdata.db.MySqlMetricDAO;
import bigdata.model.BarChart;
import bigdata.model.PieChart;
import bigdata.model.RealTimeChart;
import bigdata.service.MetricService;

@Service
public class MetricServiceImpl implements MetricService {

	@Autowired
	private MySqlMetricDAO metricDAO;

	@Override
	public RealTimeChart getRealTimeMetrics(String metricID) {
		Assert.notNull(metricID);

		return metricDAO.getMetrics(metricID);
	}

	@Override
	public BarChart getTop10Channels() {
		return metricDAO.getTop10Channels();
	}

	@Override
	public BarChart getTop10Categories() {
		return metricDAO.getTop10Categories();
	}

	@Override
	public BarChart getWorstShows() {
		return metricDAO.getWorstShows();
	}

	@Override
	public RealTimeChart getAvgDurationChannel() {
		return metricDAO.getAvgDurationChannel();
	}

	@Override
	public RealTimeChart getAvgDurationCategory() {
		return metricDAO.getAvgDurationCategory();
	}

	@Override
	public BarChart getTopChannelAds() {
		return metricDAO.getTopChannelAds();
	}

	@Override
	public PieChart getAudiencePerType() {
		return metricDAO.getAudiencePerType();
	}

	@Override
	public PieChart getAudiencePerFamilyGroup() {
		return metricDAO.getAudiencePerFamilyGroup();
	}

	@Override
	public Long getTime() {
		return metricDAO.getTime();
	}

}
