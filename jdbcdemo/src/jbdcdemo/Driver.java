package jbdcdemo;
import java.util.*;
import java.sql.*;

public class Driver {
	

	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Select a prompt: ");
		System.out.print("a. Search for order by email\n"
				+ "b. Add a new order\n");

		String prompt = input.next();
		
		if(prompt.contains("a")) {
			searchEmail();
		}
		if(prompt.contains("b")) {
			addOrder();
		}
	}
	public static void searchEmail() {
		
		String url = "jdbc:mysql://localhost:3306/customerorderdatabase";
		String username = "root";
		String password = "Spilled";
		
	
		Scanner scanner = new Scanner(System.in);
		
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			
			System.out.println("Enter Customer Email: ");
			String email = scanner.next();
			
			//Statement myStmt = connection.createStatement();
			PreparedStatement myStmt = connection.prepareStatement("Select * FROM customer_orders WHERE cust_email = '" + email + "'");
		
			
			ResultSet myRs = myStmt.executeQuery();
			
			
			while (myRs.next()) {
				System.out.println();
				System.out.println("Date: " + myRs.getString("date"));
				System.out.println("Email: " + myRs.getString("cust_email"));
				System.out.println("Zip Code: " + myRs.getString("cust_location"));
				System.out.println("Product ID: " + myRs.getString("product_id"));
				System.out.println("Quantity: " + myRs.getInt("product_quantity"));
				System.out.println();
			}
			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			System.out.println("oops, error!");
			e.printStackTrace();
		}
		}
	
		
		public static void addOrder() {
			
			String url = "jdbc:mysql://localhost:3306/customerorderdatabase";
			String username = "root";
			String password = "Spilled";
			
			
		
			Scanner scanner = new Scanner(System.in);
			Connection connection = null;
			Statement myStmt = null;
			
			try {
				
				connection = DriverManager.getConnection(url, username, password);
				
				String query = " INSERT INTO customer_orders (date, cust_email, cust_location, product_id, product_quantity)" +
						" VALUES (?, ?, ?, ?, ?)";
						
				System.out.println();
				System.out.println("Enter the date(format 01-01-2021): ");
				String date = scanner.next();
				System.out.println();
				
				System.out.println();
				System.out.println("Enter customer email: ");
				String custEmail = scanner.next();
				System.out.println();
				
				System.out.println();
				System.out.println("Enter customer zip code: ");
				String custZip = scanner.next();
				System.out.println();
				
				System.out.println();
				System.out.println("Enter the product id: ");
				String productId = scanner.next();
				System.out.println();
				
				System.out.println();
				System.out.println("Enter the quantity: ");
				int quantity = scanner.nextInt();
				System.out.println();
				
				
				//myStmt = connection.createStatement();
				PreparedStatement prepStmt = connection.prepareStatement(query);
				prepStmt.setString (1, date);
				prepStmt.setString (2, custEmail);
				prepStmt.setString (3, custZip);
				prepStmt.setString (4, productId);
				prepStmt.setInt (5, quantity);
				//String sql = ("INSERT INTO customer_orders " +
				//		 "VALUES('01-01-2021', 'josh@gmail.com', '12345', '123456789012', 2" );
				
				prepStmt.execute();
				

				
			} catch (SQLException se) {
				
				// TODO Auto-generated catch block
				System.out.println("oops, error!");
				se.printStackTrace();
				
			} 
			try {
				if(connection!=null)
					connection.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			
	}

}
