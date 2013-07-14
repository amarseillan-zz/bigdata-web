package bigdata.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bigdata.model.BarChart;
import bigdata.model.PieChart;
import bigdata.model.RealTimeChart;
import bigdata.model.RealTimeChartBuilder;

public class MySqlMetricDAO {

	static private MySqlMetricDAO instance;

	private String connectionString;
	private String user;

	private String password;

	synchronized static public MySqlMetricDAO getInstance() {
		if (instance == null) {
			instance = new MySqlMetricDAO();
		}
		return instance;
	}

	private MySqlMetricDAO() {
	}

	public RealTimeChart getMetrics(String metricID, Long minute) {
		if (minute == -1) {
			minute = this.getTime();
		}
		RealTimeChartBuilder builder = new RealTimeChartBuilder();
		Connection connection = null;
		try {
			Long count = 1L;
			connection = this.connect();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT COUNT(DISTINCT metric_key) as count FROM " + metricID + ";");
			if (rs.next()){
				count = rs.getLong("count");
				//count = count > 10 ? 10 : count;
			}
			rs.close();
			rs = statement.executeQuery("SELECT * FROM " + metricID
					+ " WHERE MINUTE >= " + minute / 60000
					+ " ORDER BY MINUTE ASC LIMIT "+ count * 10 + ";");
			System.out.println("Getting data for minute >=" + minute / 60000);
			while (rs.next()) {
				builder.addValue(rs.getString("metric_key"),
						rs.getLong("minute") * 60000, rs.getInt("quantity"));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		builder.setTitle(metricID);
		return builder.prepareChartWithLimit(10);

	}

	public Long getTime() {
		try {
			Connection connection = this.connect();
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("SELECT MINUTE FROM TotalViewers ORDER BY MINUTE ASC");
			if (rs.next()) {
				return Long.valueOf(rs.getLong("minute") * 60000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1L;
	}

	private RealTimeChart getAvgDurationMetrics(String metric) {
		RealTimeChartBuilder builder = new RealTimeChartBuilder();
		Connection connection = null;
		try {
			connection = this.connect();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM " + metric
					+ ";");
			while (rs.next()) {
				String[] date = rs.getString("date").split("-");
				Date d = new Date(Integer.valueOf(date[0]),
						Integer.valueOf(date[1]), Integer.valueOf(date[2]));
				builder.addValue(rs.getString("name"), d.getTime(),
						rs.getInt("hits"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		builder.setTitle(metric);
		return builder.prepareChartWithLimit(10);
	}

	private BarChart getBarChartChannels(String tableName) {
		BarChart chart = new BarChart();
		Connection connection = null;
		try {
			connection = this.connect();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM " + tableName
					+ " order by hits desc;");
			while (rs.next()) {
				chart.addItem(rs.getString("name"), rs.getInt("hits"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return chart;
	}

	private BarChart getBarChartChannelsForAds(String tableName) {
		BarChart chart = new BarChart();
		Connection connection = null;
		try {
			connection = this.connect();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM " + tableName
					+ " order by hits desc;");
			while (rs.next()) {
				chart.addItem(rs.getString("channel_id"), rs.getInt("hits"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return chart;
	}

	private PieChart getPieChartChannels(String tableName) {
		PieChart chart = new PieChart();
		Connection connection = null;
		try {
			connection = this.connect();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM " + tableName
					+ ";");
			while (rs.next()) {
				chart.addItem(rs.getString("name"), rs.getInt("hits"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return chart;
	}

	public BarChart getTop10Channels() {
		return getBarChartChannels("top10channels");
	}

	public BarChart getTop10Categories() {
		return getBarChartChannels("top10categories");
	}

	public BarChart getWorstShows() {
		return getBarChartChannels("worst_shows");
	}

	public BarChart getTopChannelAds() {
		return getBarChartChannelsForAds("ads_per_channel");
	}

	public RealTimeChart getAvgDurationChannel() {
		return getAvgDurationMetrics("avg_duration_channel");
	}

	public RealTimeChart getAvgDurationCategory() {
		return getAvgDurationMetrics("avg_duration_category");
	}

	public PieChart getAudiencePerType() {
		return getPieChartChannels("audience_per_type");
	}

	public PieChart getAudiencePerFamilyGroup() {
		return getPieChartChannels("audience_per_fg");
	}

	private Connection connect() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = null;

		conn = DriverManager.getConnection(connectionString, user, password);

		return conn;
	}

	public void setConnectionString(String connectionString) {
		this.connectionString = connectionString;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
