import java.sql.*;
import java.io.*;
import java.util.ArrayList;
import java.util.*;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

public class DatabaseQuery {
	static String username, password, level;
	
	static Connection connection;
	static ResultSet result;
	
	public DatabaseQuery(){
		username = "admin";
		password = "novand123";
	}
	public String getUserLevel(){
		return level;
	}
	
	public static void connect(){
		try {
			Class.forName("com.mckoi.JDBCDriver").newInstance();
		}
		catch (Exception e) {
			System.out.println(
					"Unable to register the JDBC Driver.\n" +
					"Make sure the JDBC driver is in the\n" +
		        	"classpath.\n");
			System.exit(1);
		}

		// This URL specifies we are connecting with a local database
		// within the file system.  './db.conf' is the path of the
		// configuration file of the database to embed.
		String url = "jdbc:mckoi://localhost/";

		// Make a connection with the local database.
		    
		try {
			connection = DriverManager.getConnection(url, username, password);//konek ke db
		    System.out.print("Terkoneksi ke database");
		}
		catch (SQLException e) {
			System.out.println(
		        "Unable to make a connection to the database.\n" +
		        "The reason: " + e.getMessage());
		    System.exit(1);
		    return;
		}
	}
	
	public static Connection getConnection(){
		return connection;
	}
	public boolean checkUser(String userName, String passwd){
		boolean hasil = true;
		try{
		 	Statement statement = connection.createStatement();		    
		    String query = "select * from users where user_name = '" + userName + "' and password = '"+passwd+"'";
		    result = statement.executeQuery(query);
		    if (!result.next()) {                            //if rs.next() returns false
                //then there are no rows.
				hasil = false;	
			}
			else {
				do {
				// Get data from the current row and use it
					level = result.getString("level");
				} 
				while (result.next());
				hasil = true;
			}
		    //System.out.println("All people that live in Africa:");
		    //while (result.next()) {
		    	//System.out.println(result.getString("user_id")+"," + result.getString(user_name)+","+result.getString(3));
			//}
		}
		catch (SQLException e) {
			System.out.println(
		    	"An error occured\n" +
		    	"The SQLException message is: " + e.getMessage());
		}
		return hasil;
	}
	
	public static void close(){
		try {
		    // Close the connection when finished,
			connection.close();
		}
		catch (SQLException e) {
			System.out.println(
			        "An error occured\n" +
			        "The SQLException message is: " + e.getMessage());
			return;
		}
	}
}
