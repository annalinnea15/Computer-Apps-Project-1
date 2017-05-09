import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat.Field;
import java.util.ArrayList;


import java.util.Iterator;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PowerSystemSQL {
	// JDBC driver name and database URL
	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	 static final String DB_URL = "jdbc:mysql://localhost/";
	 // Database credentials
	 static final String USER = "root";
	 static final String PASS = "root"; // insert the password to SQL server
	 
	 
	 
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		
		try{
			 // Register JDBC driver
			 Class.forName(JDBC_DRIVER);
			 // Open a connection
			 System.out.println("Connecting to SQL server...");
			 conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// execute a query to create database
			 System.out.println("Creating database...");
			 stmt = conn.createStatement();
			 String sql = "CREATE database if not exists PowerSystem"; // create database PowerSystem
			 stmt.executeUpdate(sql);
			 sql ="use PowerSystem"; // use the database
				 stmt.executeUpdate(sql) ; // execute query
			 System.out.println("Database created successfully...");

			 // Connect to the created database PowerSystem and create table Breakers
			 conn = DriverManager.getConnection(DB_URL + "PowerSystem", USER, PASS);
			 
			 addTable(stmt, "BaseVoltage(rdfID String, nominal_value double, primary key (rdfID)");
			 addTable(stmt, "Substation(rdfID String, name String, region String, primary key (rdfID)");
			 addTable(stmt, "VoltageLevel(rdfID String, name String, substation_rdf String, baseVoltage_rdf String, primary key (rdfID)");
			 addTable(stmt, "GeneratingUnit(rdfID String, name String, maxP double, minP double, equipmentContainer_rdf String, primary key (rdfID)");
			 addTable(stmt, "SynchronousMachine(rdfID String, name String, ratedS double, P double, Q double, genUnit_rdf String, regControl_rdf String, equipmentContainer String, primary key (rdfID)");
			 addTable(stmt, "RegulatingControl(rdfID String, name String, targetValue double, primary key (rdfID)");
			 addTable(stmt, "PowerTransformer(rdfID String, name String, equipmentContainer_rdf String, primary key (rdfID)");
			 addTable(stmt, "EnergyConsumer(rdfID String, name String, P double, Q double, equipmentContainer_rdf String, primary key (rdfID)");
			 addTable(stmt, "PowerTransformerEnd(rdfID String, name String, Transformer.r double, Transformer.x double, baseVoltage_rdf String, primary key (rdfID)");
			 addTable(stmt, "Breaker(rdfID String, name String, state(open) String, equipmentContainer_rdf String, primary key (rdfID)");
			 addTable(stmt, "RatioTapChanger(rdfID String, name String, step double, primary key (rdfID)");
			 
			 // insert values into the table
			 sql = "INSERT INTO Breakers " +
			 "VALUES (100,'A',15,'Open')";
			 stmt.executeUpdate(sql) ; // repeat the procedure for all rows of the table
			 sql = "INSERT INTO Breakers " +
					 "VALUES (101,'B',10,'Close')";
			 stmt.executeUpdate(sql) ; 
			 sql = "INSERT INTO Breakers " +
					 "VALUES (102,'A',20,'Close')";
			 stmt.executeUpdate(sql) ; 
			 sql = "INSERT INTO Breakers " +
					 "VALUES (103,'A',15,'Open')";
			 stmt.executeUpdate(sql) ; 
			 

			 System.out.println("Inserted records into the table...");

			 // create the java mysql update preparedstatement
			 String query = "update Breakers SET status=? WHERE ID=?"; // update status of Breaker, id=103
			 PreparedStatement preparedStmt = conn.prepareStatement(query);
			 preparedStmt.setString(1, "Close");
			 preparedStmt.setDouble(2, 103);
			 preparedStmt.executeUpdate(); // execute PreparedStatement

			 
			 // insert a new values to the table with preparedstatement
			 query = "insert into Breakers values(?,?,?,?)";
			 // finish the statement
			 preparedStmt = conn.prepareStatement(query);
			 preparedStmt.setDouble(1, 104);
			 preparedStmt.setString(2, "B");
			 preparedStmt.setDouble(3, 10);
			 preparedStmt.setString(4, "Open");
			 preparedStmt.executeUpdate(); // execute PreparedStatement
			 System.out.println("The table is updated...");

			 conn.close();

			 }catch(SQLException se){
			 //Handle errors for JDBC
			 se.printStackTrace();
			 }catch(Exception e){
			 //Handle errors for Class.forName
			 e.printStackTrace();}
			 System.out.println("Goodbye!");
			}
	
	public void addTable(Statement s, String tableName, ArrayList array) {
		String sq="CREATE TABLE if not exists "+tableName;
		try {
			s.executeUpdate(sq) ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // execute query

		 System.out.println("Created table in given database successfully...");

	 }
	
	public void makeBaseVoltageTable(Statement s, String tableName, ArrayList array){
		for (int i=0; i<array.size(); i++){//go through each Base Voltage
			String sql = "INSERT INTO BaseVoltage " +
					 "VALUES (";
			//Field[] fields=array.get(i).getClass().getDeclaredFields();
			
			for (java.lang.reflect.Field f: array.get(i).getClass().getDeclaredFields()){
			//for (int j=0; j<array.get(i).getClass(); i++){
				f.setAccessible(true);
				sql=sql+array.get(i).get(f);
			}
					 stmt.executeUpdate(sql) ; // repeat the procedure for all rows of the table
		}
		
	}

}
