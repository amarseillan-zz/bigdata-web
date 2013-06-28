package bigdata.db;

import java.util.ArrayList;
import java.util.List;

public class HiveMetricDAO {
	
	static private HiveMetricDAO instance;
	
	synchronized static public HiveMetricDAO getInstance() {
		if( instance == null ){
			instance = new HiveMetricDAO();
		}
		return instance;
	}
	
	private HiveMetricDAO() {
	}
	
	public List<String> getMetrics(String metricID){
		return new ArrayList<String>();
	}
	
}
