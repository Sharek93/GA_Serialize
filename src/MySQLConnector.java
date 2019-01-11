import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Person> LoadData() {
		
		System.out.println("fetching...");
		List<Person> persons = new ArrayList<Person>();
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/sys", "root", "123qwe");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM person");
			while (rs.next()) {
				Person person = new Person(rs.getInt("id"), rs.getString("name"), rs.getString("lastName"), rs.getInt("age"), rs.getBoolean("isActive"));
				persons.add(person);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return persons;
	}
	
	public static void SaveData(Person persons) {
		
		System.out.println("saving...");
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/sys", "root", "123qwe");
			
			String query = "insert into `sys`.`person` (`name`, `lastName`, `age`, `isActive`)"
					+ " values (?, ?, ?, ?)";
			
			System.out.println(persons.getId() + " " + String.valueOf(persons.getName()) + " "
					+ String.valueOf(persons.getlastName()) + " "
					+ String.valueOf(persons.getAge()) + " "
					+ String.valueOf(persons.getActive()));
			
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, persons.getName());
			preparedStmt.setString(2, persons.getlastName());
			preparedStmt.setInt(3, persons.getAge());
			preparedStmt.setBoolean(4, persons.getActive());

			preparedStmt.execute();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}					
	}
	
	public static boolean ChechData(int personId) {
		
		System.out.println("checking...");
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/sys", "root", "123qwe");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM person");
			while (rs.next()) {
				if(rs.getInt("id") == personId)
				{
					con.close();
					System.out.println("person found");
					return true;
				}		
			}	
			con.close();
			System.out.println("person not found");
			return false;
		} catch (SQLException e) {		
			System.out.println("person not found");
			System.out.println(e);
			return false;
		}
	}
	
	public static void UpdateDate(Person person) {
		System.out.println("updating...");
		Connection con;	
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/sys", "root", "123qwe");
			
			String query = "update person set `name` = ?, `lastName` = ?, `age` = ? where id = ?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
		    preparedStmt.setString(1, person.getName());
		    preparedStmt.setString(2, person.getlastName());
		    preparedStmt.setInt(3, person.getAge());
		    preparedStmt.setInt(4, person.getId());
		    
		    preparedStmt.execute();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
	}

}
