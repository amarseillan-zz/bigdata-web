package bigdata.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bigdata.model.RealTimeResult;

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

	public List<RealTimeResult> getMetrics(String metricID, String minute) {
		Connection connection = null;
		try {
			connection = this.connect();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM " + metricID
					+ " WHERE MINUTE = '" + minute + "';");
			List<RealTimeResult> realTimeResults = new ArrayList<RealTimeResult>();
			while (rs.next()) {
				List<Double> point = new ArrayList<Double>(2);
				point.add(Double.valueOf(rs.getString("minute")));
				point.add(Double.valueOf(rs.getString("quantity")));
				realTimeResults.add(new RealTimeResult(rs
						.getString("metric_key"), point));
			}
			connection.close();
			return realTimeResults;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	public Long getTime(){
		try {
			Connection connection = this.connect();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT MINUTE FROM TotalViewers ORDER BY MINUTE ASC");
			if (rs.next()) {
				return Long.valueOf(rs.getString("minute"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
