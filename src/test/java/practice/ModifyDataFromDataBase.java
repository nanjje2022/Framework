package practice;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ModifyDataFromDataBase {

	public static void main(String[] args) throws SQLException {
		// Step1: Create instance for Driver
		Driver dbDriver = new Driver();

		// Step2: Register driver to JDBC
		DriverManager.registerDriver(dbDriver);

		// Step3: Establish database connection
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/advsel", "root", "root");

		// Step4: Create statement
		Statement statement = connection.createStatement();

		// Step5: Execute Update Query
		int result = statement.executeUpdate("insert into student(id,name,address) values(8,'anoj','manya');");
		System.out.println(result);

		// Step6: Close database connection
		connection.close();
	}
}