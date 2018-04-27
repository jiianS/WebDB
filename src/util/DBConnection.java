package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBConnection {
	// DB관련 정보 private-> 해당 클래스에서만 사용하기 위함 . 상속/생성자 해도 DBConnection에서만 사용
	private static String url = "jdbc:mysql://192.168.1.228:3306/test";
	private static String user = "root";
	private static String pwd = "1234";
	private static List<HashMap<String, Object>> list = null;

	// DB연결하기 위한 메소드
	public static Connection openDB() throws Exception {
		// DB 연결상태 확인 및 연결한 후의 정보!
		Class.forName("org.mariadb.jdbc.Driver");
		return DriverManager.getConnection(url, user, pwd);
	}

	// connection 정보를 list로 받아오자
	public static List<HashMap<String, Object>> select(Connection con, String sql) throws Exception {
		list = new ArrayList<HashMap<String, Object>>();

		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData rm = rs.getMetaData();

		while (rs.next()) {
			// 키와 value. 자바빈과 비슷한 형태
			HashMap<String, Object> map = new HashMap<String, Object>();
			for (int i = 1; i <= rm.getColumnCount(); i++) {
				String column = rm.getColumnName(i);
				//System.out.println(rm.getColumnClassName(i));
				
				Object value="";
				
				if("java.lang.String".equals(rm.getColumnClassName(i))) {
					value=rs.getString(column);					
				}else if("java.lang.Integer".equals(rm.getColumnClassName(i))) {
					value=rs.getInt(column);
				}
				//String value = rs.getString(column);
				map.put(column, value);
			}

			list.add(map);
		}
		rs.close();
		ps.close();

		return list;
	}
	
	public static int edit(Connection con, String sql, List datalist) throws Exception {
		PreparedStatement ps = con.prepareStatement(sql);
		for(int i=1; i<=datalist.size(); i++) {
			ps.setString(i, datalist.get(i-1).toString());
			
		}
		int result = ps.executeUpdate();
		ps.close();
		return result;		
	}
	
}
