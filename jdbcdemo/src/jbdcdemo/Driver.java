package jbdcdemo;
import java.util.*;
import java.sql.*;

public class Driver {
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Select a prompt: ");
		System.out.print("a. Search for orders\n"
				+ "b. Add a new order\n"
				+ "c. Update an order\n"
				+ "d. Delete an order\n");;

		String prompt = input.next();
		
		if(prompt.contains("a")) {
			searchOrder();
		}
		if(prompt.contains("b")) {
			addOrder();
		}
		if(prompt.contains("c")) {
			updateOrder();
		}
		
		if(prompt.contains("d")) {
			deleteOrder();
		}
	}
	public static void searchOrder() {
		
		String url = "jdbc:mysql://192.254.233.63:3306/fbacon_spilledcoffee_main";
		String username = "fbacon_josht";
		String password = "spilled123";
		
	
		Scanner scanner = new Scanner(System.in);
		
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			
			System.out.println();
			System.out.println("What field would you like to search by: ");
			System.out.println("a. Customer email.");
			System.out.println("b. Zip code of customer.");
			System.out.println("c. Product ID");
			System.out.println("d. Date of Order.");
			System.out.println("e. Order ID");
			System.out.println();
			String select = scanner.next();
			
			
			
			
			if(select.contains("a")) {
				
			
				System.out.println("Enter Customer Email: ");
				String email = scanner.next();
			
				//Statement myStmt = connection.createStatement();
				PreparedStatement myStmt = connection.prepareStatement("Select * FROM customer_orders2 WHERE cust_email = '" + email + "'");
		
			
				ResultSet myRs = myStmt.executeQuery();
			
			
				while (myRs.next()) {
					System.out.println();
					System.out.println("Date: " + myRs.getString("date"));
					System.out.println("Email: " + myRs.getString("cust_email"));
					System.out.println("Zip Code: " + myRs.getString("cust_location"));
					System.out.println("Product ID: " + myRs.getString("product_id"));
					System.out.println("Quantity: " + myRs.getInt("product_quantity"));
					System.out.println("Order ID: " + myRs.getString("order_id"));
					System.out.println();
			}
				
				
				
				
				
				
			}
			
			if(select.contains("b")) {
				
				
				System.out.println("Enter 5 digit ZIP code: ");
				String zip = scanner.next();
			
				//Statement myStmt = connection.createStatement();
				PreparedStatement myStmt = connection.prepareStatement("Select * FROM customer_orders2 WHERE cust_location = '" + zip + "'");
		
			
				ResultSet myRs = myStmt.executeQuery();
			
			
				while (myRs.next()) {
					System.out.println();
					System.out.println("Date: " + myRs.getString("date"));
					System.out.println("Email: " + myRs.getString("cust_email"));
					System.out.println("Zip Code: " + myRs.getString("cust_location"));
					System.out.println("Product ID: " + myRs.getString("product_id"));
					System.out.println("Quantity: " + myRs.getInt("product_quantity"));
					System.out.println("Order ID: " + myRs.getString("order_id"));
					System.out.println();
			}
				
				
				
			}
			
			if(select.contains("c")) {
				
				
				System.out.println("Enter product ID: ");
				String pid = scanner.next();
			
				//Statement myStmt = connection.createStatement();
				PreparedStatement myStmt = connection.prepareStatement("Select * FROM customer_orders2 WHERE product_id = '" + pid + "'");
		
			
				ResultSet myRs = myStmt.executeQuery();
			
			
				while (myRs.next()) {
					System.out.println();
					System.out.println("Date: " + myRs.getString("date"));
					System.out.println("Email: " + myRs.getString("cust_email"));
					System.out.println("Zip Code: " + myRs.getString("cust_location"));
					System.out.println("Product ID: " + myRs.getString("product_id"));
					System.out.println("Quantity: " + myRs.getInt("product_quantity"));
					System.out.println("Order ID: " + myRs.getString("order_id"));
					System.out.println();
			}
				
				
				
			}
			
			if(select.contains("d")) {
				
				
				System.out.println("Enter Date(ex: 1/1/2021): ");
				String date = scanner.next();
			

				PreparedStatement myStmt = connection.prepareStatement("Select * FROM customer_orders2 WHERE date = '" + date + "'");
		
			
				ResultSet myRs = myStmt.executeQuery();
			
			
				while (myRs.next()) {
					System.out.println();
					System.out.println("Date: " + myRs.getString("date"));
					System.out.println("Email: " + myRs.getString("cust_email"));
					System.out.println("Zip Code: " + myRs.getString("cust_location"));
					System.out.println("Product ID: " + myRs.getString("product_id"));
					System.out.println("Quantity: " + myRs.getInt("product_quantity"));
					System.out.println("Order ID: " + myRs.getString("order_id"));
					System.out.println();
			}
				
				
			}
			
			if(select.contains("e")) {
				
				
				System.out.println("Enter Order ID: ");
				String oid = scanner.next();
			
				//Statement myStmt = connection.createStatement();
				PreparedStatement myStmt = connection.prepareStatement("Select * FROM customer_orders2 WHERE order_id = '" + oid + "'");
		
			
				ResultSet myRs = myStmt.executeQuery();
			
			
				while (myRs.next()) {
					System.out.println();
					System.out.println("Date: " + myRs.getString("date"));
					System.out.println("Email: " + myRs.getString("cust_email"));
					System.out.println("Zip Code: " + myRs.getString("cust_location"));
					System.out.println("Product ID: " + myRs.getString("product_id"));
					System.out.println("Quantity: " + myRs.getInt("product_quantity"));
					System.out.println("Order ID: " + myRs.getString("order_id"));
					System.out.println();
			}
				
				
			}
			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			System.out.println("oops, error!");
			e.printStackTrace();
		}
		}
	
		
		public static void addOrder() {
			
			String url = "jdbc:mysql://192.254.233.63:3306/fbacon_spilledcoffee_main";
			String username = "fbacon_josht";
			String password = "spilled123";
			
		
			Scanner scanner = new Scanner(System.in);
			Connection connection = null;
			Statement myStmt = null;
			
			try {
				
				connection = DriverManager.getConnection(url, username, password);
				
				String query = " INSERT INTO customer_orders2 (date, cust_email, cust_location, product_id, product_quantity)" +
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
							
				

				PreparedStatement prepStmt = connection.prepareStatement(query);
				prepStmt.setString (1, date);
				prepStmt.setString (2, custEmail);
				prepStmt.setString (3, custZip);
				prepStmt.setString (4, productId);
				prepStmt.setInt (5, quantity);
	
				
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
		public static void updateOrder() {
			
			String url = "jdbc:mysql://192.254.233.63:3306/fbacon_spilledcoffee_main";
			String username = "fbacon_josht";
			String password = "spilled123";
			
			
		
			Scanner scanner = new Scanner(System.in);
			Connection connection = null;
			Statement myStmt = null;
			
			try {
				
				connection = DriverManager.getConnection(url, username, password);
				
				
				System.out.println();
				System.out.println("Search for the order you'd like to update by: ");
				System.out.println("a. Customer email.");
				System.out.println();
				String select = scanner.next();
				
				
				
				
				if(select.contains("a")) {
					
				
					System.out.println("Enter Customer Email: ");
					String email = scanner.next();
				
				
					PreparedStatement upStmt = connection.prepareStatement("Select * FROM customer_orders2 WHERE cust_email = '" + email + "'");
			
				
					ResultSet myRs = upStmt.executeQuery();
				
				
					while (myRs.next()) {
						System.out.println();
						System.out.println("Date: " + myRs.getString("date"));
						System.out.println("Email: " + myRs.getString("cust_email"));
						System.out.println("Zip Code: " + myRs.getString("cust_location"));
						System.out.println("Product ID: " + myRs.getString("product_id"));
						System.out.println("Quantity: " + myRs.getInt("product_quantity"));
						System.out.println("Order ID: " + myRs.getString("order_id"));
						System.out.println();
					}
					System.out.println("Enter the order ID of the order you would like to update: ");
					int oid = scanner.nextInt();
					System.out.println();
					System.out.println();
					System.out.println("Which field would you like to update? ");
					System.out.println("a. Date");
					System.out.println("b. Zip Code");
					System.out.println("c. Product ID");
					System.out.println("d. Quantity");
					System.out.println("e. Email");
					System.out.println();
					
					String ans = scanner.next();
					
					if(ans.contains("a")) {
						
						System.out.println();
						
						System.out.println("Enter the new date(ex: 1/1/2020): ");
						System.out.println();
						String date = scanner.next();
						
						PreparedStatement upStmt2 = connection.prepareStatement("UPDATE customer_orders2 SET date = '" + date +"' WHERE order_id = '"+ oid +"'");
						upStmt2.execute();
						
					}
					
					if(ans.contains("b")) {
						
						System.out.println();
						
						System.out.println("Enter the new zip code: ");
						System.out.println();
						String zip = scanner.next();
						
						PreparedStatement upStmt2 = connection.prepareStatement("UPDATE customer_orders2 SET cust_location = '" + zip +"' WHERE order_id = '"+ oid +"'");
						upStmt2.execute();
						
					}
					
					if(ans.contains("c")) {
						
						System.out.println();
						
						System.out.println("Enter the new Product ID: ");
						System.out.println();
						String pid = scanner.next();
						
						PreparedStatement upStmt2 = connection.prepareStatement("UPDATE customer_orders2 SET product_id = '" + pid +"' WHERE order_id = '"+ oid +"'");
						upStmt2.execute();
						
					}
							
					if(ans.contains("d")) {
						
						System.out.println();
						
						System.out.println("Enter the new quantity: ");
						System.out.println();
						String pq = scanner.next();
						
						PreparedStatement upStmt2 = connection.prepareStatement("UPDATE customer_orders2 SET product_quantity = '" + pq +"' WHERE order_id = '"+ oid +"'");
						upStmt2.execute();
						
					}
					if(ans.contains("e")) {
						
						System.out.println();
						
						System.out.println("Enter the new email: ");
						System.out.println();
						String email2 = scanner.next();
						
						PreparedStatement upStmt2 = connection.prepareStatement("UPDATE customer_orders2 SET date = '" + email2 +"' WHERE order_id = '"+ oid +"'");
						upStmt2.execute();
						
					}
					
				
					
				
				}
				
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
		
		public static void deleteOrder() {
			
			String url = "jdbc:mysql://192.254.233.63:3306/fbacon_spilledcoffee_main";
			String username = "fbacon_josht";
			String password = "spilled123";
			
			
		
			Scanner scanner = new Scanner(System.in);
			Connection connection = null;
			Statement myStmt = null;
			
			try {
				
				connection = DriverManager.getConnection(url, username, password);
				
				System.out.println();
				System.out.println("Search order to delete.");
				System.out.println("Enter the selection to search by: ");
				System.out.println("a. Customer email.");

				System.out.println();
				String select = scanner.next();
				
				if(select.contains("a")) {
					
					
					System.out.println("Enter Customer Email: ");
					String email = scanner.next();
				
				
					//Statement myStmt = connection.createStatement();
					PreparedStatement searchStmt = connection.prepareStatement("Select * FROM customer_orders2 WHERE cust_email = '" + email + "'");
			
				
					ResultSet myRs = searchStmt.executeQuery();
				
				
					while (myRs.next()) {
						System.out.println();
						System.out.println("Date: " + myRs.getString("date"));
						System.out.println("Email: " + myRs.getString("cust_email"));
						System.out.println("Zip Code: " + myRs.getString("cust_location"));
						System.out.println("Product ID: " + myRs.getString("product_id"));
						System.out.println("Quantity: " + myRs.getInt("product_quantity"));
						System.out.println("Order ID: " + myRs.getString("order_id"));
						System.out.println();
						System.out.println();
						
					System.out.println("Enter the order ID of the order you would like deleted.");
					int oid = scanner.nextInt();
					System.out.println();
					
					System.out.println("Are you sure you want to delete this order?");
					String ans = scanner.next();
					
					if(ans.contains("y")) {
						String query = "DELETE FROM customer_orders2 WHERE order_id = '" + oid + "'";
					
						PreparedStatement delStmt = connection.prepareStatement(query);
						delStmt.execute();
					}
				}
					
					
					
				}
				

				
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
