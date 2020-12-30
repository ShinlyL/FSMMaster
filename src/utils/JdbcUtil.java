package utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class JdbcUtil {
	private static String USERNAME;
	private static String PASSWORD;
	private static String DRIVER;
	private static String URL;
	private Connection connection;
	private PreparedStatement pstmt;
	private ResultSet resultSet;
	public JdbcUtil() {}
	static {
		loadConfig();
	}

	public static void loadConfig() {
		try {
			InputStream inStream = JdbcUtil.class.getResourceAsStream("/jdbc.properties");
			Properties prop = new Properties();
			prop.load(inStream);
			USERNAME = prop.getProperty("jdbc.username");
			PASSWORD = prop.getProperty("jdbc.password");
			DRIVER = prop.getProperty("jdbc.driver");
			URL = prop.getProperty("jdbc.url");
		}catch(Exception e) {
			throw new RuntimeException("数据库连接失败");
		}
		
	}
	public Connection getConnection() {
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);	
		}catch(Exception e) {
			throw new RuntimeException("get connection error!",e);
		}
		return connection;
	}
	

	public int updateByPreparedStatement(String sql,List<?> params) throws SQLException{
		int result = -1;
		pstmt = connection.prepareStatement(sql);
		int index = 1;
		if(params != null && !params.isEmpty()) {
			for(int i = 0;i<params.size();i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		result = pstmt.executeUpdate();
		return result;
	}
	public List<Map<String,Object>> findResult(String sql,List<?> params) throws SQLException{
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if(params != null && !params.isEmpty()) {
			for(int i = 0;i<params.size();i++) {
				pstmt.setObject(index++, params.get(i));				
			}
		}
		resultSet = pstmt.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();
		while(resultSet.next()) {
			Map<String,Object> map = new HashMap<String,Object>();
			for(int i = 0;i<cols_len;i++) {
				String cols_name = metaData.getColumnName(i+1);
//				System.out.println("cols_name = "+cols_name);
				
				Object cols_value = resultSet.getObject(cols_name);
				if(cols_value == null) {
					cols_value = "";
				}
//				System.out.println("cols_value = "+cols_value);
				map.put(cols_name, cols_value);
			}
			list.add(map);
		}
		return list;
	}
	public void releaseConnection() {
		if(resultSet != null) {
			try {
				resultSet.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt != null) {
			try {
				pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if(connection != null) {
			try {
				connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		JdbcUtil jdbcutil = new JdbcUtil();
		jdbcutil.getConnection();
		try {
			List<Map<String,Object>> result = jdbcutil.findResult(
					"select * from user", null);
			for(Map<String,Object> m:result) {
				System.out.println(m);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			jdbcutil.releaseConnection();
		}
	}
}
