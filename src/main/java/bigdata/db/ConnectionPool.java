package bigdata.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConnectionPool {

	private BlockingQueue<Connection> bq;
	
	@Autowired
	ConnectionPool(@Value("${username}") String username,@Value("${password}") String password, @Value("${connectionString}")String connectionString,@Value("${connectionSize}") int connectionSize) {
		bq = new ArrayBlockingQueue<Connection>(connectionSize);
		Properties connectionProps = new Properties();
		connectionProps.put("user", username);
		connectionProps.put("password", password);
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Error initializen bd driver", e);
		}
		System.out.println("Creating connection pool...");
		for (int i = 0; i < connectionSize; i++) {
			try {
				System.out.println("Creating connection: " + i + "/" + connectionSize);
				bq.put(makeConnection(connectionString, connectionProps));
			} catch (Exception e) {
				throw new IllegalStateException(
						"Could not initialize connections with db", e);
			}
		}
	}

	public Connection getConnection() throws InterruptedException {
		try {
			return bq.take();
		} catch (InterruptedException e) {
			throw new IllegalStateException("Could not retrieve connection", e);
		}
	}

	public void releaseConnection(Connection con) {
		try {
			bq.put(con);
		} catch (InterruptedException e) {
			throw new IllegalStateException("Could not queue connection", e);
		}
	}

	private Connection makeConnection(String connectionString,
			Properties connectionProps) throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection(connectionString, connectionProps);
		return conn;
	}

}