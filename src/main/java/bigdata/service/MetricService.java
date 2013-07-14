package bigdata.service;

import bigdata.model.BarChart;
import bigdata.model.PieChart;
import bigdata.model.RealTimeChart;

public interface MetricService {

	RealTimeChart getRealTimeMetrics(String metricID, Long minute);
	
	BarChart getTop10Channels();
	
	BarChart getTop10Categories();
	
	BarChart getTopChannelAds();
	
	BarChart getWorstShows();
	
	RealTimeChart getAvgDurationChannel();
	
	RealTimeChart getAvgDurationCategory();
	
	PieChart getAudiencePerType();
	
	PieChart getAudiencePerFamilyGroup();
	
	Long getTime();
}
