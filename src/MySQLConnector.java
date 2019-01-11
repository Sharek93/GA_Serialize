import java.sql.*;

public class MySQLConnector {

	public static void Connect() {
		System.out.println("trying to aquire connection...");

		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/sys", "root", "123qwe");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM person");
			while (rs.next()) {
				System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " + rs.getString("lastName")  + " " + rs.getInt("age") + " " + rs.getBoolean("isActive"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
