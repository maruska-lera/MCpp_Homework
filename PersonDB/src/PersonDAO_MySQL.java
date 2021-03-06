import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO_MySQL implements PersonDAO {
	private static final String url = "jdbc:mysql://localhost:3306/person";
	private static final String user = "root";
	private static final String password = "";

	Connection connection = null;
	Statement st = null;

	@Override
	public void create(Person p) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement st = conn.createStatement();
			String sql = "insert into person (id, fname, lname, age) values (" + p.id + ",'" + p.fname + "','" + p.lname
					+ "'," + p.age + ")";
			st.executeUpdate(sql);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Person> read() {
		ArrayList<Person> pp = new ArrayList<Person>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Person");
			while (rs.next()) {
				Person p = new Person(rs.getInt("id"), rs.getString(2), rs.getString(3), rs.getInt("age"));
				pp.add(p);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return pp;
	}

	@Override
	public void update(Person p) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement st = conn.createStatement();
			String sql = "UPDATE PERSON SET FNAME='" + p.fname + "', LNAME='" + p.lname + "', AGE=" + p.age + " WHERE ID=" + p.id;
			st.executeUpdate(sql);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Person p) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			st = connection.createStatement();
			String sql = "DELETE FROM PERSON WHERE ID=" + p.id;
			st.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
