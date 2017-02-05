package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PersonDao {

	Database database = null;

	public PersonDao() {
		database = new Database();
	}

	public boolean addPerson(Person person) {
		Connection con = database.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("insert into data(name,address,phone) values (?,?,?)");

			ps.setString(1, person.getName());
			ps.setString(2, person.getAddress());
			ps.setString(3, person.getPhone());

			return ps.executeUpdate() == 1 ? true : false;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		} finally {
			database.close(con);
		}
	}

	public boolean updatePerson(Person person) {
		Connection con = database.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("update data set name=?,address=?,phone=? where id = ?");

			ps.setString(1, person.getName());
			ps.setString(2, person.getPhone());
			ps.setString(3, person.getPhone());
			ps.setInt(4, person.getId());

			return ps.executeUpdate() == 1 ? true : false;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		} finally {
			database.close(con);
		}
	}

	public List<Person> getPeople() {
		Connection con = database.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select id,name,address,phone from data");
			ResultSet rs = ps.executeQuery();
			List<Person> list = new ArrayList<>();
			while (rs.next()) {
				Person p = new Person();
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setAddress(rs.getString(3));
				p.setPhone(rs.getString(4));
				list.add(p);
			}
			return list;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		} finally {
			database.close(con);
		}
	}

	public boolean deletePerson(int id) {
		Connection con = database.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("delete from data where id = ?");
			ps.setInt(1, id);
			return ps.executeUpdate() == 1 ? true : false;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		} finally {
			database.close(con);
		}
	}
	
	public Person getPerson(String name) {
		Connection con = database.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select id,name,address,phone from data where name=?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Person p = new Person();
			p.setId(rs.getInt(1));
			p.setName(rs.getString(2));
			p.setAddress(rs.getString(3));
			p.setPhone(rs.getString(4));
			return p;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		} finally {
			database.close(con);
		}
	}
	public static void main(String[] args) {
		PersonDao pd = new PersonDao();
		Person p = new Person();
		p.setName("surendra");
		p.setAddress("amp");
		p.setPhone("9491122299");
	    System.out.println(pd.addPerson(p));
		
	}
}
